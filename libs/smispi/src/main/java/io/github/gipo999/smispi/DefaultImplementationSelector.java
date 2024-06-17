package io.github.gipo999.smispi;

import java.util.List;
import java.util.Optional;

/** Default implementation selector. */
public class DefaultImplementationSelector implements ImplementationSelector {

  /**
   * Chooses an implementation of a service.
   *
   * @param clazz the class of the service
   * @param implementations the implementations of the service
   * @param params the parameters for choosing the implementation
   * @param <T> the type of the service
   * @return the chosen implementation
   */
  @Override
  public <T extends NamedService> Optional<T> choose(
      Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params) {

    if (implementations == null || implementations.isEmpty()) {
      return Optional.empty();
    }

    T standard = null;

    T custom = null;

    for (T impl : implementations) {

      String implName = impl.getServiceImplementationName();

      if (NamedService.IMPL_STANDARD_NAME.equals(implName)) {
        standard = impl;
      } else if (NamedService.IMPL_CUSTOM_NAME.equals(implName)) {
        custom = impl;
      }
    }

    if (custom != null) {

      return Optional.of(custom);
    } else if (standard != null) {

      return Optional.of(standard);
    }

    return Optional.empty();
  }
}
