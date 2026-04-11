package leiphotos.domain.albums;

public class Album extends AAlbum {
  private String nomeAlbum; 
  private MainLibrary libPertencente;

  public AAlbum(String name, MainLibrary lib) {
    this.nomeAlbum = name; 
    this.lib = libPertencente;
  };
};
