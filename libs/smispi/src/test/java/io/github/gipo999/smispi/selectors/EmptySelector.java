package io.github.gipo999.smispi.selectors;

import io.github.gipo999.smispi.ImplementationParams;
import io.github.gipo999.smispi.ImplementationSelector;
import io.github.gipo999.smispi.NamedService;
import java.util.List;
import java.util.Optional;

public class EmptySelector implements ImplementationSelector {

  @Override
  public <T extends NamedService> Optional<T> choose(
      Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params) {
    return Optional.empty();
  }
}
