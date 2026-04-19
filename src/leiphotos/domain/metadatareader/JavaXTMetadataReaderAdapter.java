package leiphotos.domain.metadatareader;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import leiphotos.services.JavaXTJpegMetadataReader;

/**
 * Adaptador da biblioteca JavaXT para a interface de leitura de metadados JPEG.
 */
public class JavaXTMetadataReaderAdapter implements JpegMetadataReader {
  private JavaXTJpegMetadataReader metadata;
  private static final DateTimeFormatter METADATA_FORMAT = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");

  /**
   * Cria um leitor de metadados para o ficheiro indicado.
   *
   * @param file ficheiro JPEG a ler
   * @throws JpegMetadataException se nao for possivel ler metadados
   * @throws FileNotFoundException se o ficheiro nao existir
   */
  public JavaXTMetadataReaderAdapter(File file) throws JpegMetadataException, FileNotFoundException {
    this.metadata = new JavaXTJpegMetadataReader(file);
  }

  @Override
  public String getAperture() {
    return metadata.getAperture();
  }

  @Override
  public String getCamera() {
    return metadata.getCamera();
  }

  @Override
  public LocalDateTime getDate() {
    String dateStr = this.metadata.getDate();
    if (dateStr == null || dateStr.isEmpty()) {
      return LocalDateTime.of(1970, 1, 1, 0, 0); // data por omissão
    }
    return LocalDateTime.parse(dateStr, METADATA_FORMAT);
  }

  @Override
  public double[] getGpsLocation() {
    return this.metadata.getGPS();
  }

  @Override
  public String getManufacturer() {
    return this.metadata.getManufacturer();
  }
};
