package leiphotos.domain.core;

import java.util.Arrays;

import leiphotos.utils.RegExpMatchable;

public record GPSLocation(
    double[] coordinates,
    String description) implements RegExpMatchable {

  @Override
  public boolean matches(String regexp) {
    boolean matchesCoordinates = (this.coordinates != null) && Arrays.toString(coordinates).matches(regexp);
    boolean matchesDescription = (this.description != null) && this.description.matches(regexp);

    return matchesCoordinates || matchesDescription;
  }
}
