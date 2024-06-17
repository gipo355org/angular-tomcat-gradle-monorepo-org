package io.github.gipo999.smispi.filters;

import io.github.gipo999.smispi.ImplementationParams;
import io.github.gipo999.smispi.ImplementationsFilter;
import io.github.gipo999.smispi.PrioritizedService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmptyFilter implements ImplementationsFilter {

  @Override
  public <T extends PrioritizedService> List<T> filter(
      Class<T> clazz, List<T> implementations, Optional<ImplementationParams> params) {
    return Collections.emptyList();
  }
}
