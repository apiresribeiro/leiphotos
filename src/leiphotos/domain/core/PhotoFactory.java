package leiphotos.domain.core;

import java.io.File;
import java.time.LocalDateTime;

import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.domain.metadatareader.JpegMetadataReader;
import leiphotos.domain.metadatareader.JpegMetadataReaderFactory;

public enum PhotoFactory {
  INSTANCE;

  Photo createPhoto(String title, String pathToPhotoFile) throws java.io.FileNotFoundException {
    File file = new File(pathToPhotoFile);
    JpegMetadataReader reader = JpegMetadataReaderFactory.INSTACE.createMetadataReader(file);
    double[] gps = reader.getGpsLocation();
    GPSCoordinates location = (gps != null)

    PhotoMetadata metadata = new PhotoMetadata(
        location,
        reader.getDate(),
        reader.getCamera(),
        reader.getManufacturer());

    LocalDateTime now = LocalDateTime.now();
    return new Photo(title, now, metadata, file);
  };
};
