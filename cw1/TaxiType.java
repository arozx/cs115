/** Represents the available taxi types and their price per mile. */
public enum TaxiType {
  SMALL(0.93),
  REGULAR(1.21),
  MINIBUS(2.55);

  private final double ratePerMile;

  /**
   * Creates a taxi type with its cost per mile.
   *
   * @param ratePerMile the rate charged per mile
   */
  TaxiType(double ratePerMile) {
    this.ratePerMile = ratePerMile;
  }

  /**
   * Returns the price per mile for this taxi type.
   *
   * @return the rate per mile
   */
  public double getRatePerMile() {
    return ratePerMile;
  }
}
