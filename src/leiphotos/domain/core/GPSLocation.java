package leiphotos.domain.core;

import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.utils.RegExpMatchable;

public record GPSLocation(
    double[] coordinates,
    String description) implements GPSCoordinates, RegExpMatchable {

  @Override
  public boolean matches(String regexp) {
    boolean matchesDescription = (this.description != null) && this.description.matches(regexp);

    return matchesDescription;
  }

  @Override
  public double latitude() {
    return coordinates[1];
  }

  @Override
  public double longitude() {
    return coordinates[0];
  }
}
