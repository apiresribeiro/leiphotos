package leiphotos.domain.albums;
import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.events.PhotoLibraryEvent;

public class Album extends AAlbum {
  private String nomeAlbum; 
  private MainLibrary libPertencente;

  public Album(String nome, MainLibrary library) {
    super(nome, library);
  }

  @Override
  public void processEvent(PhotoLibraryEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'processEvent'");
  };
};
