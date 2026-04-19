package leiphotos.domain.views;

import java.time.LocalDateTime;
import java.util.Comparator;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.facade.ViewsType;

/**
 * Catalogo de vistas pre-definidas sobre biblioteca principal e lixo.
 */
public class ViewsCatalog implements IViewsCatalog {
  private final Comparator<IPhoto> defaultFilter = Comparator.comparing(IPhoto::size);

  private final ILibraryView allMainView;
  private final ILibraryView allTrashView;
  private final ILibraryView favouritesView;
  private final ILibraryView mostRecentView;

  /**
   * Cria o catalogo de vistas a partir das bibliotecas principal e lixo.
   *
   * @param mainLib biblioteca principal
   * @param trashLib biblioteca de lixo
   */
  public ViewsCatalog(MainLibrary mainLib, TrashLibrary trashLib) {
    this.allMainView = new MainLibraryView(mainLib, p -> true, defaultFilter);
    this.allTrashView = new TrashLibraryView(trashLib, p -> true, defaultFilter);
    this.favouritesView = new MainLibraryView(mainLib, p -> p.isFavourite(), defaultFilter);
    this.mostRecentView = new MainLibraryView(
        mainLib,
        p -> p.capturedDate().isAfter(LocalDateTime.now().minusMonths(12)),
        defaultFilter);

  }

  @Override
  public ILibraryView getView(ViewsType t) {
    return switch (t) {
      case ALL_MAIN -> allMainView;
      case ALL_TRASH -> allTrashView;
      case FAVOURITES -> favouritesView;
      case MOST_RECENT -> mostRecentView;
    };
  }
};
