import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Loads taxi and driver data from a text file. */
public class FileDataLoader {
  private static final int NUMBER_PLATE_INDEX = 0;
  private static final int MILEAGE_INDEX = 1;
  private static final int ELECTRIC_VEHICLE_INDEX = 2;
  private static final int TAXI_TYPE_INDEX = 3;
  private static final int DRIVER_NAME_INDEX = 4;
  private static final int LICENSE_CODE_INDEX = 5;

  /**
   * Reads taxi data from a file and returns it as a list of Taxi objects.
   *
   * @param file the file to read
   * @return the list of taxis, or null if an exception occurs
   */
  public static List<Taxi> loadTaxis(File file) {
    try {
      Scanner scanner = new Scanner(file);
      List<Taxi> taxis = new ArrayList<Taxi>();
      List<Driver> drivers = new ArrayList<Driver>();

      // Loop until EOF.
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        String numberPlate = parts[NUMBER_PLATE_INDEX];
        int mileage = Integer.parseInt(parts[MILEAGE_INDEX]);
        boolean electricVehicle = Boolean.parseBoolean(parts[ELECTRIC_VEHICLE_INDEX]);
        TaxiType type = TaxiType.valueOf(parts[TAXI_TYPE_INDEX]);
        String driverName = parts[DRIVER_NAME_INDEX];
        String licenseCode = parts[LICENSE_CODE_INDEX];

        // Note on top marks this is to reuse driver objects where possible.
        Driver driver = findDriver(driverName, licenseCode, drivers);

        if (driver == null) {
          // Create new driver if non exists.
          driver = new Driver(driverName, licenseCode);
          drivers.add(driver);
        }

        taxis.add(new Taxi(numberPlate, mileage, electricVehicle, type, driver));
      }

      scanner.close();
      return taxis;
      // Catch all exceptions and fail give stacktrace for debugging.
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    }
  }

  /**
   * Finds a matching driver already loaded from the file.
   *
   * @param fullName the driver's full name
   * @param licenseCode the driver's license code
   * @param drivers the loaded drivers to search
   * @return the matching driver, or null if no match exists
   */
  private static Driver findDriver(String fullName, String licenseCode, List<Driver> drivers) {
    // Iterate though existing divers.
    for (Driver driver : drivers) {
      if (driver.getFullName().equals(fullName) && driver.getLicenseCode().equals(licenseCode)) {
        return driver;
      }
    }

    return null;
  }
}
