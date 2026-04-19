package leiphotos.domain.core.events;

import leiphotos.domain.core.Library;
import leiphotos.domain.facade.IPhoto;

/**
 * Evento emitido quando uma foto e adicionada a uma biblioteca.
 */
public class PhotoAddedLibraryEvent extends PhotoLibraryEvent {
  /**
   * Cria um evento de adicao de foto.
   *
   * @param photo foto adicionada
   * @param lib biblioteca onde a foto foi adicionada
   */
  public PhotoAddedLibraryEvent(IPhoto photo, Library lib) {
    super(photo, lib);
  }
}
