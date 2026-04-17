package leiphotos.domain.views;

import java.util.Comparator;
import java.util.function.Predicate;
import leiphotos.domain.core.TrashLibrary;
import leiphotos.domain.facade.IPhoto;

public class TrashLibraryView extends ALibraryView {
  public TrashLibraryView(TrashLibrary library, Predicate<IPhoto> filter, Comparator<IPhoto> comparator) {
    super(library, filter, comparator);
  }
};
