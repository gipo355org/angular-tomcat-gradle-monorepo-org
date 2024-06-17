package io.github.gipo999.smispi;

/** Interface for services that have a priority. */
@FunctionalInterface
public interface PrioritizedService extends NamedService, Comparable<PrioritizedService> {

  /** Default priority for services. */
  int DEFAULT_PRIORIY = 500;

  /**
   * Returns the priority of the service.
   *
   * @return the priority of the service
   */
  default int getPriority() {
    return DEFAULT_PRIORIY;
  }

  /**
   * Compares this object with the specified object for order. Returns a negative integer, zero, or
   * a positive integer as this object is less than, equal to, or greater than the specified object.
   *
   * @param o the object to be compared
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   *     or greater than the specified object
   */
  @Override
  default int compareTo(PrioritizedService o) {
    return Integer.compare(getPriority(), o.getPriority());
  }
}
