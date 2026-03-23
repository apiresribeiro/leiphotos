package leiphotos.domain.core;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;

import leiphotos.domain.facade.GPSCoordinates;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.RegExpMatchable;

public class Photo implements IPhoto, RegExpMatchable {
  private LocalDateTime addedDate;
  private LocalDateTime capturedDate;
  private File file;
  private boolean isFavourite;
  private String title;
  private GPSCoordinates location;

  Photo(String title, LocalDateTime dateAddedToLib, PhotoMetadata metadata, File pathToFile) { // TODO: pathToFile não
                                                                                               // devia ser só file?
    this.title = title;
    this.addedDate = dateAddedToLib;
    this.capturedDate = metadata.date();
    this.location = metadata.location();
    this.file = pathToFile;

  }

  @Override
  public LocalDateTime addedDate() {
    return this.addedDate;
  }

  @Override
  public LocalDateTime capturedDate() {
    return this.capturedDate;
  }

  @Override
  public File file() {
    return this.file; // TODO: Dúvida?
  }

  @Override
  public Optional<? extends GPSCoordinates> getPlace() {
    return Optional.ofNullable(this.location); // A foto pode não ter localização!
  }

  @Override
  public boolean isFavourite() {
    return this.isFavourite;
  }

  @Override
  public boolean matches(String regexp) {
    return false;
  }

  @Override
  public long size() {
    return this.file.length();
  }

  @Override
  public String title() {
    return this.title;
  }

  @Override
  public void toggleFavourite() {
    this.isFavourite = true;
  }
};
