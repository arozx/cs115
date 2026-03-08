import java.util.List;

/** Calculates the most profitable tourist route for a taxi. */
public class TaxiRouter {
  private static final double MILES_PER_STOP = 1.5;

  /**
   * Calculates the cost of the longest route starting from a tourist attraction.
   *
   * @param touristAttraction the starting attraction
   * @param taxi the taxi used for the route
   * @return the cost of the most profitable route
   */
  public static double calculateMostProfitableRouteCost(
      TouristAttraction touristAttraction, Taxi taxi) {
    int longestRouteStops = calculateLongestRouteLength(touristAttraction);
    double miles = longestRouteStops * MILES_PER_STOP;

    return taxi.calculateCost(miles);
  }

  /**
   * Recursively calculates the longest route length from an attraction.
   *
   * @param touristAttraction the current attraction
   * @return the length of the longest route from this attraction
   */
  private static int calculateLongestRouteLength(TouristAttraction touristAttraction) {
    List<TouristAttraction> nextAttractions = touristAttraction.getNext();

    if (nextAttractions.isEmpty()) {
      return 0;
    }

    int longestLength = 0;

    // Explore each branch recursively keep longest.

    for (TouristAttraction nextAttraction : nextAttractions) {
      int candidateLength = calculateLongestRouteLength(nextAttraction);

      if (candidateLength > longestLength) {
        longestLength = candidateLength;
      }
    }

    return longestLength + 1;
  }
}
