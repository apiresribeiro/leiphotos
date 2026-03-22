package leiphotos.domain.core;

public enum PhotoFactory {
  INSTANCE;

  Photo createPhoto(String title, String pathToPhotoFile) throws java.io.FileNotFoundException {
  };
};
