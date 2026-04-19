package leiphotos.domain.albums;
import leiphotos.domain.core.MainLibrary;

/**
 * Album simples de fotos.
 */
public class Album extends AAlbum {

  /**
   * Cria um album simples associado a biblioteca principal.
   *
   * @param nome nome do album
   * @param library biblioteca principal
   */
  public Album(String nome, MainLibrary library) {
    super(nome, library);
  }

};
