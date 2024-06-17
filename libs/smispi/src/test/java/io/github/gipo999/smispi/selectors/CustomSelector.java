package io.github.gipo999.smispi.selectors;

import io.github.gipo999.smispi.ImplementationParams;
import io.github.gipo999.smispi.ImplementationSelector;
import io.github.gipo999.smispi.NamedService;
import java.util.List;
import java.util.Optional;

public class CustomSelector implements ImplementationSelector {

  @Override
  public <T extends NamedService> Optional<T> choose(
      Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params) {
    if (implementations == null || implementations.isEmpty() || params.isEmpty()) {
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
    String key = params.get().getCacheKey();
    if ("s".equals(key)) {
      return Optional.ofNullable(standard);
    } else if ("c".equals(key)) {
      return Optional.ofNullable(custom);
    }
    return Optional.empty();
  }
}
