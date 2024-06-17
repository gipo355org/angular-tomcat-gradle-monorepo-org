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
public final class Implementations {

  /** The logger for this class. */
  private static final Logger LOGGER = SmiSpi.LOGGER;

  /** The cache for implementations. */
  private static final Map<String, List<PrioritizedService>> CACHE = new HashMap<>();

  /** The cache for implementations with parameters. */
  private static final Map<String, Map<String, List<PrioritizedService>>> PARAMS_CACHE =
      new HashMap<>();

  /** Prevents instantiation of this utility class. */
  private Implementations() {}

  /**
   * Returns the implementations of a service.
   *
   * @param clazz the class of the service
   * @param <T> the type of the service
   * @return the implementations of the service
   */
  public static <T extends PrioritizedService> List<T> of(Class<T> clazz) {
    return of(clazz, null);
  }

  /**
   * Returns the implementations of a service.
   *
   * @param clazz the class of the service
   * @param filter the filter for the implementations
   * @param <T> the type of the service
   * @return the implementations of the service
   */
  public static <T extends PrioritizedService> List<T> of(
      Class<T> clazz, ImplementationsFilter filter) {
    return of(clazz, filter, null);
  }

  /**
   * Returns the implementations of a service.
   *
   * @param clazz the class of the service
   * @param filter the filter for the implementations
   * @param params the parameters for the implementations
   * @param <T> the type of the service
   * @return the implementations of the service
   */
  @SuppressWarnings("unchecked")
  public static <T extends PrioritizedService> List<T> of(
      Class<T> clazz, ImplementationsFilter filter, ImplementationParams params) {

    Map<String, List<PrioritizedService>> cacheObject;

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

      return (List<T>) cacheObject.get(cacheKey);
    }

    List<T> selected = ofNew(clazz, filter, params);

    if (!selected.isEmpty()) {
      cacheObject.put(cacheKey, (List<PrioritizedService>) selected);
    }

    return selected;
  }

  /**
   * Returns the class name of a service.
   *
   * @param clazz the class of the service
   * @param <T> the type of the service
   * @return the class name of the service
   */
  static <T extends NamedService> String getClassName(Class<T> clazz) {

    return clazz.getCanonicalName();
  }

  /**
   * Returns the implementations of a service.
   *
   * @param clazz the class of the service
   * @param <T> the type of the service
   * @return the implementations of the service
   */
  public static <T extends PrioritizedService> List<T> ofNew(Class<T> clazz) {

    return ofNew(clazz, null);
  }

  /**
   * Returns the implementations of a service.
   *
   * @param clazz the class of the service
   * @param filter the filter for the implementations
   * @param <T> the type of the service
   * @return the implementations of the service
   */
  public static <T extends PrioritizedService> List<T> ofNew(
      Class<T> clazz, ImplementationsFilter filter) {

    return ofNew(clazz, filter, null);
  }

  /**
   * Returns the implementations of a service.
   *
   * @param clazz the class of the service
   * @param filter the filter for the implementations
   * @param params the parameters for the implementations
   * @param <T> the type of the service
   * @return the implementations of the service
   */
  public static <T extends PrioritizedService> List<T> ofNew(
      Class<T> clazz, ImplementationsFilter filter, ImplementationParams params) {

    List<T> list = loadList(clazz);

    List<T> selected;

    if (list.isEmpty() || filter == null) {

      selected = list;

    } else {

      selected =
          Collections.unmodifiableList(filter.filter(clazz, list, Optional.ofNullable(params)));
    }

    if (LOGGER.isDebugEnabled()) {

      if (selected.isEmpty()) {

        LOGGER.warn("No implementation selected for : {}", getClassName(clazz));

      } else {

        LOGGER.debug("Filtered implementations are :");

        for (T impl : selected) {

          LOGGER.debug(
              "\t#{} {} : {}",
              impl.getPriority(),
              impl.getServiceImplementationName(),
              impl.getClass().getName());
        }
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
  private static <T extends PrioritizedService> List<T> loadList(Class<T> clazz) {

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Loading implementations for class <{}> :", getClassName(clazz));
    }

    final List<T> implementations = new ArrayList<>();

    final ServiceLoader<? extends T> implementationsLoader = ServiceLoader.load(clazz);

    for (T impl : implementationsLoader) {
      implementations.add(impl);
    }

    Collections.sort(implementations);

    if (LOGGER.isDebugEnabled()) {

      if (implementations.isEmpty()) {

        LOGGER.debug("\tNo implementations found.");

      } else {

        for (T impl : implementationsLoader) {

          LOGGER.debug(
              "\t#{} {} : {}",
              impl.getPriority(),
              impl.getServiceImplementationName(),
              impl.getClass().getName());
        }
      }
    }

    return Collections.unmodifiableList(implementations);
  }
}
