package leiphotos.domain.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import java.time.Duration;
import java.time.LocalDateTime;
import leiphotos.domain.facade.IPhoto;

public class RecentlyDeletedLibrary extends ATrashLibrary {
  private Collection<IPhoto> photosApagadas;
  private HashMap<IPhoto, LocalDateTime> tempoTrash;
  private final int MAX_TIME = 15;
  private LocalDateTime lastClean;

  public RecentlyDeletedLibrary() {
    this.photosApagadas = new ArrayList<>();
    this.lastClean = LocalDateTime.now();
    this.tempoTrash = new HashMap<>();
  }

  @Override
  public Collection<IPhoto> getPhotos() {
    if (cleaningTime()) {
      clean();
    }
    return new ArrayList<>(photosApagadas);
  }

  @Override
  public boolean deleteAll() {
    if (this.photosApagadas.isEmpty()) {
      return false;
    }

    this.photosApagadas.clear();
    tempoTrash.clear();
    return true;
  }

  @Override
  public int getNumberOfPhotos() {
    return this.photosApagadas.size();
  }

  @Override
  public boolean addPhoto(IPhoto photo) {
    if (photo == null || photosApagadas.contains(photo)) {
      return false;
    }
    boolean added = photosApagadas.add(photo);
    if (added) {
      tempoTrash.put(photo, LocalDateTime.now());
    }
    return added;
  }

  @Override
  public boolean deletePhoto(IPhoto photo) {
    boolean removed = photosApagadas.remove(photo);
    if (removed) {
      tempoTrash.remove(photo);
    }
    return removed;
  }

  @Override
  public Collection<IPhoto> getMatches(String regexp) {
    Collection<IPhoto> matches = new ArrayList<>();
    for (IPhoto photoInLib : this.photosApagadas) {
      if (photoInLib.matches(regexp))
        matches.add(photoInLib);
    }
    return matches;
  }

  @Override
  protected void clean() {
    List<IPhoto> toRemove = new ArrayList<>();
    LocalDateTime now = LocalDateTime.now();

    for (IPhoto p : this.photosApagadas) {
      LocalDateTime trashTime = tempoTrash.get(p);
      if (trashTime != null) {
        Duration d = Duration.between(trashTime, now);
        if (d.toSeconds() >= MAX_TIME) {
          toRemove.add(p);
        }
      }
    }

    for (IPhoto p : toRemove) {
      this.photosApagadas.remove(p);
      this.tempoTrash.remove(p);
    }
    lastClean = now;
  }

  @Override
  protected boolean cleaningTime() {
    Duration duration = Duration.between(this.lastClean, LocalDateTime.now());
    return duration.toSeconds() >= MAX_TIME;
  }
};
