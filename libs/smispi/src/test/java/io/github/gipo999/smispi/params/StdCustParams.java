package io.github.gipo355.smispi.params;

import io.github.gipo355.smispi.ImplementationParams;

public class StdCustParams implements ImplementationParams {

  private final String param;

  public StdCustParams(String param) {
    this.param = param;
  }

  @Override
  public String getCacheKey() {
    return param;
  }
}
