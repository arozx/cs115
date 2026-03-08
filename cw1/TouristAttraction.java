import java.util.ArrayList;
import java.util.List;

/** Represents a tourist attraction and the attractions that follow it on the route. */
public class TouristAttraction {
  private String name;
  private List<TouristAttraction> next;

  /**
   * Creates a tourist attraction with the given name.
   *
   * @param name the name of the attraction
   */
  public TouristAttraction(String name) {
    this.name = name;
    next = new ArrayList<TouristAttraction>();
  }

  /**
   * Returns the name of the attraction.
   *
   * @return the attraction name
   */
  public String getName() {
    return name;
  }

  /**
   * Adds an attraction that comes after this attraction.
   *
   * @param touristAttraction the next attraction to add
   */
  public void addNext(TouristAttraction touristAttraction) {
    next.add(touristAttraction);
  }

  /**
   * Returns the list of attractions that follow this one.
   *
   * @return the next attractions
   */
  public List<TouristAttraction> getNext() {
    return new ArrayList<TouristAttraction>(next);
  }
}
