package leiphotos.domain.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import leiphotos.domain.metadatareader.JpegMetadataException;
import leiphotos.domain.metadatareader.JpegMetadataReader;
import leiphotos.domain.metadatareader.JpegMetadataReaderFactory;

/**
 * Fabrica de fotos a partir de ficheiros JPEG e respetivos metadados.
 */
public enum PhotoFactory {
  INSTANCE;

  /**
   * Cria uma foto a partir do titulo e caminho de ficheiro indicados.
   *
   * @param title titulo da foto
   * @param pathToPhotoFile caminho para o ficheiro JPEG
   * @return foto criada, ou null se houver erro de leitura de metadados
   * @throws FileNotFoundException se o ficheiro nao existir
   */
  public Photo createPhoto(String title, String pathToPhotoFile) throws java.io.FileNotFoundException {
    File file = new File(pathToPhotoFile);
    try {
      JpegMetadataReader reader = JpegMetadataReaderFactory.INSTACE.createMetadataReader(file);
      double[] coords = reader.getGpsLocation();

      // Se a foto não tiver localização, então GPSLocation é null
      GPSLocation location = (coords != null) ? new GPSLocation(coords, null) : null;
      PhotoMetadata metadata = new PhotoMetadata(
          location,
          reader.getDate(),
          reader.getCamera(),
          reader.getManufacturer());

      LocalDateTime now = LocalDateTime.now();
      return new Photo(title, now, metadata, file);

    } catch (JpegMetadataException | FileNotFoundException | IllegalArgumentException e) {
      return null;
    }
  };
};
