package io.github.gipo999.smispi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import org.slf4j.Logger;

/** Utility class for loading implementations of services. */
public final class Implementation {

  /** The logger for this class. */
  private static final Logger LOGGER = SmiSpi.LOGGER;

  /** The cache for implementations. */
  private static final Map<String, NamedService> CACHE = new HashMap<>();

  /** The cache for implementations with parameters. */
  private static final Map<String, Map<String, NamedService>> PARAMS_CACHE = new HashMap<>();

  /** The global implementation selector. */
  private static ImplementationSelector globalSelector;

  /** Prevents instantiation of this utility class. */
  private Implementation() {}

  /**
   * Sets the global implementation selector.
   *
   * @param selector the global implementation selector
   */
  public static void setGlobalSelector(ImplementationSelector selector) {
    Implementation.globalSelector = selector;
  }

  /**
   * Returns the global implementation selector.
   *
   * @return the global implementation selector
   */
  private static ImplementationSelector getGlobalSelector() {
    if (globalSelector == null) {
      globalSelector = new DefaultImplementationSelector();
    }

    return globalSelector;
  }

  /**
   * Returns the implementation selector.
   *
   * @param selector the implementation selector
   * @return the implementation selector
   */
  private static ImplementationSelector getSelector(ImplementationSelector selector) {
    if (selector != null) {
      return selector;
    }

    return getGlobalSelector();
  }

  /**
   * Returns the implementation of a service.
   *
   * @param clazz the class of the service
   * @param <T> the type of the service
   * @return the implementation of the service
   */
  public static <T extends NamedService> Optional<T> of(Class<T> clazz) {

    return of(clazz, null);
  }

  /**
   * Returns the implementation of a service.
   *
   * @param clazz the class of the service
   * @param selector the selector for the implementation
   * @param <T> the type of the service
   * @return the implementation of the service
   */
  public static <T extends NamedService> Optional<T> of(
      Class<T> clazz, ImplementationSelector selector) {

    return of(clazz, selector, null);
  }

  /**
   * Returns the implementation of a service.
   *
   * @param clazz the class of the service
   * @param selector the selector for the implementation
   * @param params the parameters for the implementation
   * @param <T> the type of the service
   * @return the implementation of the service
   */
  @SuppressWarnings("unchecked")
  public static <T extends NamedService> Optional<T> of(
      Class<T> clazz, ImplementationSelector selector, ImplementationParams params) {

    Map<String, NamedService> cacheObject;

    String cacheKey;

    String className = getClassName(clazz);

    if (params == null) {
      cacheObject = CACHE;

      cacheKey = className;
    } else {
      cacheKey = params.getCacheKey();

      cacheObject = PARAMS_CACHE.computeIfAbsent(className, k -> new HashMap<>());
    }

    if (cacheObject.containsKey(cacheKey)) {

      return Optional.of((T) cacheObject.get(cacheKey));
    }

    Optional<T> selected = ofNew(clazz, selector, params);

    selected.ifPresent(t -> cacheObject.put(cacheKey, t));

    return selected;
  }

  /**
   * Returns the class name of a service.
   *
   * @param clazz the class of the service
   * @param <T> the type of the service
   * @return the class name of the service
   */
  private static <T extends NamedService> String getClassName(Class<T> clazz) {

    return clazz.getCanonicalName();
  }

  /**
   * Returns a new implementation of a service.
   *
   * @param clazz the class of the service
   * @param <T> the type of the service
   * @return the new implementation of the service
   */
  public static <T extends NamedService> Optional<T> ofNew(Class<T> clazz) {

    return ofNew(clazz, null);
  }

  /**
   * Returns a new implementation of a service.
   *
   * @param clazz the class of the service
   * @param selector the selector for the implementation
   * @param <T> the type of the service
   * @return the new implementation of the service
   */
  public static <T extends NamedService> Optional<T> ofNew(
      Class<T> clazz, ImplementationSelector selector) {
    return ofNew(clazz, selector, null);
  }

  /**
   * Returns a new implementation of a service.
   *
   * @param clazz the class of the service
   * @param selector the selector for the implementation
   * @param params the parameters for the implementation
   * @param <T> the type of the service
   * @return the new implementation of the service
   */
  public static <T extends NamedService> Optional<T> ofNew(
      Class<T> clazz, ImplementationSelector selector, ImplementationParams params) {

    List<T> list = loadList(clazz);

    Optional<T> selected;

    if (list.isEmpty()) {

      selected = Optional.empty();
    } else {

      selected = getSelector(selector).choose(clazz, list, Optional.ofNullable(params));
    }

    if (LOGGER.isDebugEnabled()) {

      if (selected.isPresent()) {

        LOGGER.debug(
            "Selected implementation is : {}", selected.get().getServiceImplementationName());
      } else {

        LOGGER.warn("No implementation selected for : {}", getClassName(clazz));
      }
    }

    return selected;
  }

  /**
   * Loads the implementations of a service.
   *
   * @param clazz the class of the service
   * @param <T> the type of the service
   * @return the implementations of the service
   */
  private static <T extends NamedService> List<T> loadList(Class<T> clazz) {

    if (LOGGER.isDebugEnabled()) {

      LOGGER.debug("Loading implementations for class <{}> :", getClassName(clazz));
    }

    final List<T> implementations = new ArrayList<>();

    final ServiceLoader<? extends T> implementationsLoader = ServiceLoader.load(clazz);

    for (T impl : implementationsLoader) {

      implementations.add(impl);

      if (LOGGER.isDebugEnabled()) {

        LOGGER.debug("\t{} : {}", impl.getServiceImplementationName(), impl.getClass().getName());
      }
    }

    if (LOGGER.isDebugEnabled() && implementations.isEmpty()) {

      LOGGER.debug("\tNo implementations found.");
    }

    return Collections.unmodifiableList(implementations);
  }
}
