package leiphotos.domain.albums;

import leiphotos.domain.core.MainLibrary;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import leiphotos.domain.facade.IPhoto;

/**
 * Catalogo de albuns indexado por nome.
 */
public class AlbumsCatalog implements IAlbumsCatalog {

  private Map<String, IAlbum> nameToAlbum;
  private MainLibrary lib;

  /**
   * Cria um catalogo de albuns para a biblioteca principal indicada.
   *
   * @param library biblioteca principal
   */
  public AlbumsCatalog(MainLibrary library) {
    this.nameToAlbum = new HashMap<>();
    this.lib = library;
  }

  @Override
  public boolean createAlbum(String albumName) {
    if (this.nameToAlbum.containsKey(albumName)) {
      return false;
    }
    Album newAlbum = new Album(albumName, lib);
    this.nameToAlbum.put(albumName, newAlbum);
    return true;
  }

  @Override
  public boolean deleteAlbum(String albumName) {
    if (this.nameToAlbum.containsKey(albumName)) {
      IAlbum albumToRemove = this.nameToAlbum.get(albumName);
      this.nameToAlbum.remove(albumName, albumToRemove);
      return true;
    }
    return false;
  }

  @Override
  public boolean containsAlbum(String albumName) {
    return this.nameToAlbum.containsKey(albumName);
  }

  @Override
  public boolean addPhotos(String albumName, Set<IPhoto> selectedPhotos) {
    if (this.nameToAlbum.containsKey(albumName)) {
      this.nameToAlbum.get(albumName).addPhotos(selectedPhotos);
      return true;
    }
    return false;
  }

  @Override
  public boolean removePhotos(String albumName, Set<IPhoto> selectedPhotos) {
    if (this.nameToAlbum.containsKey(albumName)) {
      this.nameToAlbum.get(albumName).removePhotos(selectedPhotos);
      return true;
    }
    return false;
  }

  @Override
  public List<IPhoto> getPhotos(String albumName) {
    IAlbum album = nameToAlbum.get(albumName);
    return (albumName != null) ? album.getPhotos() : Collections.emptyList();
  }

  @Override
  public Set<String> getAlbumNames() {
    return this.nameToAlbum.keySet();
  }
};
