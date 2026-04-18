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

  Photo(String title, LocalDateTime dateAddedToLib, PhotoMetadata metadata, File pathToFile) {
    this.title = title;
    this.addedDate = dateAddedToLib;
    this.capturedDate = metadata.date();
    this.location = metadata.location().orElse(null);
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
    return this.file;
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
    boolean matchesTitle = (this.title != null) && this.title.matches(regexp);
    boolean matchesFileName = (this.file != null) && this.file.getName().matches(regexp);
    boolean matchesCapturedDate = (this.capturedDate != null) && this.capturedDate.toString().matches(regexp);
    boolean matchesLocation = (this.location != null) && this.location.toString().matches(regexp);

    return matchesTitle || matchesCapturedDate || matchesLocation || matchesFileName;
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
    this.isFavourite = !this.isFavourite;
  }
};
