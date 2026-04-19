package leiphotos.domain.views;

import java.util.Comparator;
import java.util.function.Predicate;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.IPhoto;

/**
 * Vista para a biblioteca de lixo.
 */
public class TrashLibraryView extends ALibraryView {
  /**
   * Cria uma vista para o lixo.
   *
   * @param library biblioteca de lixo
   * @param filter filtro de fotos
   * @param comparator criterio de ordenacao
   */
  public TrashLibraryView(TrashLibrary library, Predicate<IPhoto> filter, Comparator<IPhoto> comparator) {
    super(library, filter, comparator);
  }
};
