package leiphotos.domain.core;

import java.time.LocalDateTime;
import java.util.Optional;
import leiphotos.utils.RegExpMatchable;

/**
 * Metadados de uma foto: localizacao, data, camara e fabricante.
 *
 * @param location localizacao GPS opcional
 * @param date data de captura
 * @param camera modelo da camara
 * @param fabricante fabricante da camara
 */
public record PhotoMetadata(
    Optional<GPSLocation> location,
    LocalDateTime date,
    String camera,
    String fabricante) implements RegExpMatchable {

  /**
   * Cria metadados a partir de uma localizacao opcional.
   *
   * @param location localizacao GPS, pode ser null
   * @param date data de captura
   * @param camera modelo da camara
   * @param fabricante fabricante da camara
   */
  public PhotoMetadata(GPSLocation location, LocalDateTime date, String camera, String fabricante) {
    this(Optional.ofNullable(location), date, camera, fabricante);
  }

  @Override
  public boolean matches(String regexp) {
    boolean matchesLocation = location.map(l -> l.toString().matches(regexp)).orElse(false);
    boolean matchesDate = (this.date != null) && this.date.toString().matches(regexp);
    boolean matchesCamera = (this.camera != null) && this.camera.matches(regexp);
    boolean matchesFabricante = (this.fabricante != null) && this.fabricante.matches(regexp);

    return matchesLocation || matchesDate || matchesCamera || matchesFabricante;
  }
};
