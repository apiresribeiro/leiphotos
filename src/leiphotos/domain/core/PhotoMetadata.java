package leiphotos.domain.core;

import java.time.LocalDateTime;
import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.utils.RegExpMatchable;

public record PhotoMetadata(
    GPSCoordinates location,
    LocalDateTime date,
    String camera,
    String fabricante) implements RegExpMatchable {

  @Override
  public boolean matches(String regexp) {
    boolean matchesLocation = (this.location != null) && this.location.toString().matches(regexp);
    boolean matchesDate = (this.date != null) && this.date.toString().matches(regexp);
    boolean matchesCamera = (this.camera != null) && this.camera.matches(regexp);
    boolean matchesFabricante = (this.fabricante != null) && this.fabricante.matches(regexp);

    return matchesLocation || matchesDate || matchesCamera || matchesFabricante;
  }
};
