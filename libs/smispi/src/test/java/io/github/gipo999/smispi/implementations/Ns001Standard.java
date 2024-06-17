package io.github.gipo999.smispi.implementations;

import io.github.gipo999.smispi.NamedService;
import io.github.gipo999.smispi.interfaces.NamedService001;

public class Ns001Standard implements NamedService001 {

  @Override
  public String getServiceImplementationName() {
    return NamedService.IMPL_STANDARD_NAME;
  }
}
