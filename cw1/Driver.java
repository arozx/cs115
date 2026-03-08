/** Represents a taxi driver and the vehicles there licensed to drive. */
public class Driver {
  private static final int MINIBUS_INDEX = 7;
  private static final int REGULAR_INDEX = 8;
  private static final int SMALL_INDEX = 9;

  private String fullName;
  private String licenseCode;

  /**
   * Creates a driver with a name and license code.
   *
   * @param fullName the driver's full name
   * @param licenseCode the driver's license code
   */
  public Driver(String fullName, String licenseCode) {
    this.fullName = fullName;
    this.licenseCode = licenseCode;
  }

  /**
   * Returns the driver's full name.
   *
   * @return the full name
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Sets the driver's full name.
   *
   * @param fullName the new full name
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * Returns the driver's license code.
   *
   * @return the license code
   */
  public String getLicenseCode() {
    return licenseCode;
  }

  /**
   * Sets the driver's license code.
   *
   * @param licenseCode the new license code
   */
  public void setLicenseCode(String licenseCode) {
    this.licenseCode = licenseCode;
  }

  /**
   * Returns whether this driver can drive the given taxi type.
   *
   * @param taxiType the taxi type to check
   * @return true if the driver can drive the type, otherwise false
   */
  public boolean canDrive(TaxiType taxiType) {
    switch (taxiType) {
      case MINIBUS:
        return licenseCode.charAt(MINIBUS_INDEX) == 'M';
      case REGULAR:
        return licenseCode.charAt(REGULAR_INDEX) == 'R';
      case SMALL:
        return licenseCode.charAt(SMALL_INDEX) == 'S';
      default:
        return false;
    }
  }
}
