package leiphotos.domain.controllers;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Set;

import leiphotos.domain.facade.ILibrariesController;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.PhotoFactory;
import leiphotos.domain.core.TrashLibrary;

public class LibrariesController implements ILibrariesController {

  MainLibrary mainLibrary;
  TrashLibrary trashLibrary;

  public LibrariesController(MainLibrary main, TrashLibrary trash) {
    this.mainLibrary = main;
    this.trashLibrary = trash;
  }

  @Override
  public Optional<IPhoto> importPhoto(String title, String pathToPhotoFile) {
    try {
      IPhoto newPhoto = PhotoFactory.INSTANCE.createPhoto(title, pathToPhotoFile);
      if (newPhoto == null) {
        return Optional.empty();
      }

      if (!this.mainLibrary.addPhoto(newPhoto)) {
        return Optional.empty();
      }

      return Optional.of(newPhoto);
    } catch (FileNotFoundException e) {
      return Optional.empty();
    }
  }

  @Override
  public void deletePhotos(Set<IPhoto> selectedPhotos) {
    for (IPhoto photo : selectedPhotos) {
      if (this.mainLibrary.deletePhoto(photo)) {
        this.trashLibrary.addPhoto(photo);
      }
    }
  }

  @Override
  public void emptyTrash() {
    this.trashLibrary.deleteAll();
  }

  @Override
  public void toggleFavourite(Set<IPhoto> selectedPhotos) {
    for (IPhoto photo : selectedPhotos) {
      if (this.mainLibrary.getPhotos().contains(photo)) {
        photo.toggleFavourite();
        this.mainLibrary.photoChanged(photo);
      }
    }
  }

@Override
  public Iterable<IPhoto> getMatches(String regExp) {
    return this.mainLibrary.getMatches(regExp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("***** MAIN PHOTO LIBRARY: ");
    int count = 0;
    for (IPhoto p : mainLibrary.getMatches(".*")) {
      count++;
    }
    sb.append(count).append(" photos *****\n");
    for (IPhoto p : mainLibrary.getMatches(".*")) {
      String fav = p.isFavourite() ? "   FAV" : "";
      sb.append(" File:").append(p.file().getName()).append("\n");
      sb.append("   Title:").append(p.title()).append("  Added:").append(p.addedDate()).append("  Size:").append(p.size()).append(fav).append("\n");
      sb.append("   ").append(p.getPlace()).append("  ").append(p.capturedDate()).append("\n");
    }
    sb.append("***** TRASH PHOTO LIBRARY: ");
    int trashCount = 0;
    for (IPhoto p : trashLibrary.getPhotos()) {
      trashCount++;
    }
    sb.append(trashCount).append(" photos *****\n");
    return sb.toString();
  }
};
