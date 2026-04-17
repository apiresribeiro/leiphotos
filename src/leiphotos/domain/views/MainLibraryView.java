package leiphotos.domain.views;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.events.PhotoLibraryEvent;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.Listener;

public class MainLibraryView extends ALibraryView implements Listener<PhotoLibraryEvent> {
  private List<IPhoto> cache;

  public MainLibraryView(MainLibrary library, Predicate<IPhoto> filter, Comparator<IPhoto> comparator) {
    super(library, filter, comparator);
    cache = null;
    library.registerListener(this);
  }

  @Override
  public void processEvent(PhotoLibraryEvent e) {
    if (e.equals(PhotoAddedLibraryEvent)) {

    }
  }
};
