package io.github.gipo355.smispi.implementations;

import io.github.gipo355.smispi.NamedService;
import io.github.gipo355.smispi.interfaces.PrioritizedService002;

public class Ps002Custom implements PrioritizedService002 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_CUSTOM_NAME;
  }
}
