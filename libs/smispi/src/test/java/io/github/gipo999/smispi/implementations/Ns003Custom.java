package io.github.gipo355.smispi.implementations;

import io.github.gipo355.smispi.NamedService;
import io.github.gipo355.smispi.interfaces.NamedService003;

public class Ns003Custom implements NamedService003 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_CUSTOM_NAME;
  }
}
