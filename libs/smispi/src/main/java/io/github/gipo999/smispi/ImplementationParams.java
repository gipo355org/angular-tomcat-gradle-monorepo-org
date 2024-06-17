package io.github.gipo999.smispi;

/** Interface for the parameters of an implementation. */
@FunctionalInterface
public interface ImplementationParams {

  /**
   * Returns the cache key.
   *
   * @return the cache key
   */
  String getCacheKey();
}
