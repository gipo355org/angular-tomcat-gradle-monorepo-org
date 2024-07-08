package io.github.gipo355.smispi.implementations;

import io.github.gipo355.smispi.NamedService;
import io.github.gipo355.smispi.interfaces.NamedService002;

public class Ns002Custom implements NamedService002 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_CUSTOM_NAME;
  }
}
