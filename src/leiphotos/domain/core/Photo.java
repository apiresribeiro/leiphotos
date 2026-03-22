package leiphotos.domain.core;

import java.io.File;
import java.time.LocalDateTime;

import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.RegExpMatchable;

public class Photo implements IPhoto, RegExpMatchable {
  Photo(String title, LocalDateTime dateAddedToLib, PhotoMetadata metadata, File pathToFile) {

  };
};
