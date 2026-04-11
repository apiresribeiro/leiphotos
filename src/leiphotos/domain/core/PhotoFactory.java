package leiphotos.domain.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import leiphotos.domain.metadatareader.JpegMetadataException;
import leiphotos.domain.metadatareader.JpegMetadataReader;
import leiphotos.domain.metadatareader.JpegMetadataReaderFactory;

public enum PhotoFactory {
  INSTANCE;

  Photo createPhoto(String title, String pathToPhotoFile) throws java.io.FileNotFoundException {
    File file = new File(pathToPhotoFile);
    try {
      JpegMetadataReader reader = JpegMetadataReaderFactory.INSTACE.createMetadataReader(file);
      double[] coords = reader.getGpsLocation();

      // TODO: A descrição é inicializada a null?

      // Se a foto não tiver localização, então GPSLocation é null
      GPSLocation location = (coords != null) ? new GPSLocation(coords, null) : null;
      PhotoMetadata metadata = new PhotoMetadata(
          location,
          reader.getDate(),
          reader.getCamera(),
          reader.getManufacturer());

      LocalDateTime now = LocalDateTime.now();
      return new Photo(title, now, metadata, file);

    } catch (JpegMetadataException | FileNotFoundException e) {
      return null;
    }
  };
};
