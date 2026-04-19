package leiphotos.domain.core;

import java.util.ArrayList;
import java.util.Collection;

import leiphotos.domain.core.events.PhotoAddedLibraryEvent;
import leiphotos.domain.core.events.PhotoChangedLibraryEvent;
import leiphotos.domain.core.events.PhotoLibraryEvent;
import leiphotos.domain.core.events.PhotoRemovedLibraryEvent;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.AbsSubject;

/**
 * Biblioteca principal de fotos com emissao de eventos de alteracao.
 */
public class MainLibrary extends AbsSubject<PhotoLibraryEvent> implements Library {
  private final Collection<IPhoto> lib;

  /**
   * Cria uma biblioteca principal vazia.
   */
  public MainLibrary() {
    this.lib = new ArrayList<>();
  }

  @Override
  public int getNumberOfPhotos() {
    return lib.size();
  }

  @Override
  public boolean addPhoto(IPhoto photo) {
    if (lib.contains(photo))
      return false;
    boolean added = lib.add(photo);
    if (added) {
      emitEvent(new PhotoAddedLibraryEvent(photo, this));
    }
    return added;
  }

  @Override
  public boolean deletePhoto(IPhoto photo) {
    boolean removed = lib.remove(photo);
    if (removed) {
      emitEvent(new PhotoRemovedLibraryEvent(photo, this));
    }
    return removed;
  }

  /**
   * Notifica que uma foto existente na biblioteca foi alterada.
   *
   * @param photo foto alterada
   */
  public void photoChanged(IPhoto photo) {
    if (lib.contains(photo)) {
      emitEvent(new PhotoChangedLibraryEvent(photo, this));
    }
  }

  @Override
  public Collection<IPhoto> getPhotos() {
    return new ArrayList<>(lib);
  }

  @Override
  public Collection<IPhoto> getMatches(String regexp) {
    Collection<IPhoto> matches = new ArrayList<>();
    for (IPhoto photoInLib : lib) {
      if (photoInLib.matches(regexp)) {
        matches.add(photoInLib);
      }
    }
    return matches;
  }
};
