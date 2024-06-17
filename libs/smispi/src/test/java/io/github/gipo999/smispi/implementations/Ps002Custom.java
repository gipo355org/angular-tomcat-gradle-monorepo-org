package io.github.gipo999.smispi.implementations;

import io.github.gipo999.smispi.NamedService;
import io.github.gipo999.smispi.interfaces.PrioritizedService002;

public class Ps002Custom implements PrioritizedService002 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_CUSTOM_NAME;
  }
}
