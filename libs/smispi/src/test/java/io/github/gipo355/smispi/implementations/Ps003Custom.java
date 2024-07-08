package io.github.gipo355.smispi.implementations;

import io.github.gipo355.smispi.NamedService;
import io.github.gipo355.smispi.interfaces.PrioritizedService003;

public class Ps003Custom implements PrioritizedService003 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_CUSTOM_NAME;
  }

  @Override
  public int getPriority() {
    return 1;
  }
}
