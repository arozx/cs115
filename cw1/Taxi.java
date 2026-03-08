/** Represents a taxi in the taxi operator's fleet. */
public class Taxi {
  private static final int STARTING_TAXI_ID = 499101; // From CW1, incr by 1 per new instance
  private static final double ELECTRIC_DISCOUNT_MULTIPLIER = 0.7;
  private static int nextTaxiId = STARTING_TAXI_ID;

  private int taxiId;
  private String numberPlate;
  private int mileage;
  private boolean electricVehicle;
  private Driver driver;
  private TaxiType type;

  /**
   * Creates a taxi without an assigned driver.
   *
   * @param numberPlate the number plate of the taxi
   * @param mileage the mileage of the taxi
   * @param electricVehicle whether the taxi is electric
   * @param type the type of the taxi
   */
  public Taxi(String numberPlate, int mileage, boolean electricVehicle, TaxiType type) {
    this(numberPlate, mileage, electricVehicle, type, null);
  }

  /**
   * Creates a taxi with an assigned driver.
   *
   * @param numberPlate the number plate of the taxi
   * @param mileage the mileage of the taxi
   * @param electricVehicle whether the taxi is electric
   * @param type the type of the taxi
   * @param driver the assigned driver
   */
  public Taxi(
      String numberPlate, int mileage, boolean electricVehicle, TaxiType type, Driver driver) {
    taxiId = nextTaxiId;
    nextTaxiId++; // set new ID
    this.numberPlate = numberPlate;
    this.mileage = mileage;
    this.electricVehicle = electricVehicle;
    this.type = type;
    this.driver = driver;
  }

  /**
   * Returns the taxi's internal id.
   *
   * @return the taxi id
   */
  public int getTaxiId() {
    return taxiId;
  }

  /**
   * Returns the taxi's number plate.
   *
   * @return the number plate
   */
  public String getNumberPlate() {
    return numberPlate;
  }

  /**
   * Sets the taxi's number plate.
   *
   * @param numberPlate the new number plate
   */
  public void setNumberPlate(String numberPlate) {
    this.numberPlate = numberPlate;
  }

  /**
   * Returns the taxi's mileage.
   *
   * @return the mileage
   */
  public int getMileage() {
    return mileage;
  }

  /**
   * Sets the taxi's mileage.
   *
   * @param mileage the new mileage
   */
  public void setMileage(int mileage) {
    this.mileage = mileage;
  }

  /**
   * Returns whether the taxi is electric.
   *
   * @return true if the taxi is electric, otherwise false
   */
  public boolean getElectricVehicle() {
    return electricVehicle;
  }

  /**
   * Sets whether the taxi is electric.
   *
   * @param electricVehicle the new electric flag
   */
  public void setElectricVehicle(boolean electricVehicle) {
    this.electricVehicle = electricVehicle;
  }

  /**
   * Returns the driver assigned to the taxi.
   *
   * @return the driver, or NULL if none is assigned
   */
  public Driver getDriver() {
    return driver;
  }

  /**
   * Sets the driver assigned to the taxi.
   *
   * @param driver the new driver
   */
  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  /**
   * Returns the taxi type.
   *
   * @return the taxi type
   */
  public TaxiType getType() {
    return type;
  }

  /**
   * Calculates the ride cost for a distance in miles.
   *
   * @param miles the distance to travel
   * @return the calculated cost
   */
  public double calculateCost(double miles) {
    double cost = miles * type.getRatePerMile();

    if (electricVehicle) {
      cost = cost * ELECTRIC_DISCOUNT_MULTIPLIER; // 30% discunt for electric
    }

    return cost;
  }

  /**
   * Returns a string representation of the taxi.
   *
   * @return the formatted taxi string
   */
  @Override
  public String toString() {
    return "{" + taxiId + "; " + numberPlate + "; " + mileage + "; " + electricVehicle + "}";
  }
}
