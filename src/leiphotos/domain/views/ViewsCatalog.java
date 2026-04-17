package leiphotos.domain.views;

import java.time.LocalDateTime;
import java.util.Comparator;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.facade.ViewsType;

public class ViewsCatalog implements IViewsCatalog {
  private final Comparator<IPhoto> defaultFilter = Comparator.comparing(IPhoto::size);
  private final MainLibrary mainLib;
  private final TrashLibrary trashLib;

  public ViewsCatalog(MainLibrary mainLib, TrashLibrary trashLib) {
    this.mainLib = mainLib;
    this.trashLib = trashLib;
  }

  @Override
  public ILibraryView getView(ViewsType t) {
    return switch (t) {
      case ALL_MAIN -> new MainLibraryView(mainLib, p -> true, defaultFilter);

      case ALL_TRASH -> new TrashLibraryView(trashLib, p -> true, defaultFilter);

      case FAVOURITES -> new MainLibraryView(mainLib, p -> p.isFavourite(), defaultFilter);

      case MOST_RECENT -> new MainLibraryView(
          mainLib,
          p -> p.capturedDate().isAfter(LocalDateTime.now().minusMonths(12)),
          defaultFilter);
    };
  }
};
