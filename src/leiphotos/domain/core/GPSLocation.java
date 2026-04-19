package leiphotos.domain.core;

import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.utils.RegExpMatchable;

/**
 * Representa uma localizacao geografica associada a uma foto.
 *
 * @param coordinates coordenadas no formato [longitude, latitude]
 * @param description descricao textual opcional do local
 */
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

  @Override
  public String toString() {
    if (coordinates == null || coordinates.length < 2) {
      return "No Location";
    }
    return "{Lat:" + coordinates[1] + " Long:" + coordinates[0] + " Desc:" + description + "}";
  }
}
