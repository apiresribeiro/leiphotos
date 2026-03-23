package leiphotos.domain.metadatareader;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import leiphotos.services.JavaXTJpegMetadataReader;

public class JavaXTMetadataReaderAdapter implements JpegMetadataReader {
  private JavaXTJpegMetadataReader metadata;

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
    // TODO: Porquê String e não LocalDateTime??
    return LocalDateTime.parse(this.metadata.getDate());
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
