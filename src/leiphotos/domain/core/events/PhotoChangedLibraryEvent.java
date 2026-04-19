package leiphotos.domain.core.events;

import leiphotos.domain.core.Library;
import leiphotos.domain.facade.IPhoto;

/**
 * Evento emitido quando uma foto e alterada numa biblioteca.
 */
public class PhotoChangedLibraryEvent extends PhotoLibraryEvent {
  /**
   * Cria um evento de alteracao de foto.
   *
   * @param photo foto alterada
   * @param lib biblioteca onde ocorreu a alteracao
   */
  public PhotoChangedLibraryEvent(IPhoto photo, Library lib) {
    super(photo, lib);
  }
};
