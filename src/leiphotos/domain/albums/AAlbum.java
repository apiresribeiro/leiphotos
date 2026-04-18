package leiphotos.domain.albums;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.events.PhotoLibraryEvent;
import leiphotos.domain.core.events.PhotoRemovedLibraryEvent;

public abstract class AAlbum implements IAlbum {
  private String nomeAlbum;
  private List<IPhoto> photosAlbum;
  private MainLibrary lib;

  public AAlbum(String nome, MainLibrary library) {
    this.nomeAlbum = nome;
    this.photosAlbum = new ArrayList<>();
    this.lib = library;
    lib.registerListener(this);
  }

  @Override
  public int numberOfPhotos() {
    return this.photosAlbum.size();
  };

  @Override
  public String getName() {
    return this.nomeAlbum;
  }

  @Override
  public List<IPhoto> getPhotos() {
    return this.photosAlbum;
  }

  @Override
  public boolean addPhotos(Set<IPhoto> photos) {
    int jaExistente = 0;
    for (IPhoto photo : photos) {
      if (!this.photosAlbum.contains(photo)) {
        this.photosAlbum.add(photo);
      } else {
        jaExistente++;
      }
    }

    if (jaExistente == photos.size()) {
      return false;
    }
    return true;
  }

  @Override
  public boolean removePhotos(Set<IPhoto> photos) {
    int naoExistente = 0;
    for (IPhoto photo : photos) {
      if (this.photosAlbum.contains(photo)) {
        this.photosAlbum.remove(photo);
      } else {
        naoExistente++;
      }
    }
    if (naoExistente == photos.size()) {
      return false;
    }
    return true;
  }

  @Override
  public void processEvent(PhotoLibraryEvent e) {
    if (e instanceof PhotoRemovedLibraryEvent) {
      this.photosAlbum.remove(e.getPhoto());
    }
  }
}
