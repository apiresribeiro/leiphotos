package leiphotos.domain.core.events;

import leiphotos.domain.core.Library;
import leiphotos.domain.facade.IPhoto;

/**
 * Evento emitido quando uma foto e removida de uma biblioteca.
 */
public class PhotoRemovedLibraryEvent extends PhotoLibraryEvent {
  /**
   * Cria um evento de remocao de foto.
   *
   * @param photo foto removida
   * @param lib biblioteca de onde a foto foi removida
   */
  public PhotoRemovedLibraryEvent(IPhoto photo, Library lib) {
    super(photo, lib);
  }
}
