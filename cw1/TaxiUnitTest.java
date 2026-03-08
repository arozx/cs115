/** Provides simple console-based tests for the Taxi class. */
public class TaxiUnitTest {
  /** Tests the Taxi constructor and getter methods. */
  public static void testConstructorAndGetters() {
    Taxi taxi = new Taxi("AA00BBB", 1234, true, TaxiType.REGULAR);

    printResult("numberPlate", "AA00BBB", taxi.getNumberPlate());
    printResult("mileage", 1234, taxi.getMileage());
    printResult("electricVehicle", true, taxi.getElectricVehicle());
    printResult("type", TaxiType.REGULAR, taxi.getType());
  }

  /** Tests the Taxi setter methods. */
  public static void testSetters() {
    Driver driver = new Driver("Jane Doe", "ABCDEFGMRS");
    Taxi taxi = new Taxi("BB11CCC", 5000, false, TaxiType.SMALL);

    taxi.setNumberPlate("CE12GIK");
    taxi.setMileage(20000);
    taxi.setElectricVehicle(true);
    taxi.setDriver(driver);

    printResult("numberPlate", "CE12GIK", taxi.getNumberPlate());
    printResult("mileage", 20000, taxi.getMileage());
    printResult("electricVehicle", true, taxi.getElectricVehicle());
    printResult("driverAssigned", true, taxi.getDriver() == driver);
  }

  /** Tests that taxi ids increase by one for each new taxi. */
  public static void testTaxiIds() {
    Taxi firstTaxi = new Taxi("DD22EEE", 100, false, TaxiType.SMALL);
    Taxi secondTaxi = new Taxi("EE33FFF", 200, false, TaxiType.REGULAR);
    Taxi thirdTaxi = new Taxi("FF44GGG", 300, true, TaxiType.MINIBUS);

    printResult("secondTaxiId", firstTaxi.getTaxiId() + 1, secondTaxi.getTaxiId());
    printResult("thirdTaxiId", secondTaxi.getTaxiId() + 1, thirdTaxi.getTaxiId());
  }

  /** Tests the Taxi toString method. */
  public static void testToString() {
    Taxi firstTaxi = new Taxi("GG55HHH", 4321, true, TaxiType.SMALL);
    String firstExpected = "{" + firstTaxi.getTaxiId() + "; GG55HHH; 4321; true}";

    printResult("toStringConstructorValues", firstExpected, firstTaxi.toString());

    Taxi secondTaxi = new Taxi("AB12CDE", 0, false, TaxiType.MINIBUS);
    String secondExpected = "{" + secondTaxi.getTaxiId() + "; AB12CDE; 0; false}";

    printResult("toStringDifferentValues", secondExpected, secondTaxi.toString());

    secondTaxi.setNumberPlate("ZX99YYY");
    secondTaxi.setMileage(98765);
    secondTaxi.setElectricVehicle(true);

    String updatedExpected = "{" + secondTaxi.getTaxiId() + "; ZX99YYY; 98765; true}";

    printResult("toStringAfterSetters", updatedExpected, secondTaxi.toString());
  }

  /**
   * Runs all Taxi tests.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    testConstructorAndGetters();
    testSetters();
    testTaxiIds();
    testToString();
  }

  /**
   * Prints a formatted expected versus actual test result string.
   *
   * @param dataName the name of the tested data
   * @param expected the expected value
   * @param actual the actual value
   */
  private static void printResult(String dataName, Object expected, Object actual) {
    String outcome = "FAIL";

    if (expected.equals(actual)) {
      outcome = "PASS";
    }

    System.out.println(
        dataName + "; Expected: " + expected + "; Actual: " + actual + "; " + outcome);
  }
}
