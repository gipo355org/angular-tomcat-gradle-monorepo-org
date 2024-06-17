package io.github.gipo999.smispi;

/** Interface for services that have a priority. */
@FunctionalInterface
public interface NamedService {

  /** Returns the name of the service implementation. */
  String IMPL_STANDARD_NAME = "standard";

  /** Returns the name of the custom service implementation. */
  String IMPL_CUSTOM_NAME = "custom";

  /**
   * Returns the name of the service implementation.
   *
   * @return the name of the service implementation
   */
  String getServiceImplementationName();
}
