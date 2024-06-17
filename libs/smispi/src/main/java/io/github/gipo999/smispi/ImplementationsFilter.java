package io.github.gipo999.smispi;

import java.util.List;
import java.util.Optional;

/** Interface for filtering implementations. */
@FunctionalInterface
public interface ImplementationsFilter {

  /**
   * Filters the implementations of a service.
   *
   * @param clazz the class of the service
   * @param implementations the implementations of the service
   * @param params the parameters for filtering the implementations
   * @return the filtered implementations
   * @param <T> the type of the service
   */
  <T extends PrioritizedService> List<T> filter(
      Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params);
}
