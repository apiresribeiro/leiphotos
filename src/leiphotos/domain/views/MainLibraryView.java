package leiphotos.domain.views;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.events.PhotoAddedLibraryEvent;
import leiphotos.domain.core.events.PhotoChangedLibraryEvent;
import leiphotos.domain.core.events.PhotoLibraryEvent;
import leiphotos.domain.core.events.PhotoRemovedLibraryEvent;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.Listener;

/**
 * Vista da biblioteca principal com cache e atualizacao por eventos.
 */
public class MainLibraryView extends ALibraryView implements Listener<PhotoLibraryEvent> {
  private List<IPhoto> cache = new ArrayList<>();

  /**
   * Cria uma vista da biblioteca principal.
   *
   * @param library biblioteca principal observada
   * @param filter filtro de fotos
   * @param comparator criterio de ordenacao
   */
  public MainLibraryView(MainLibrary library, Predicate<IPhoto> filter, Comparator<IPhoto> comparator) {
    super(library, filter, comparator);
    library.registerListener(this);
    rebuildCache();
  }

  private boolean passesFilter(IPhoto photo) {
    return filter == null || filter.test(photo);
  }

  private void rebuildCache() {
    cache.clear();
    for (IPhoto p : library.getPhotos()) {
      if (passesFilter(p)) {
        cache.add(p);
      }
    }
    cache.sort(comparator);
  }

  @Override
  public List<IPhoto> getPhotos() {
    return new ArrayList<>(cache);
  }

  @Override
  public List<IPhoto> getMatches(String regexp) {
    List<IPhoto> result = new ArrayList<>();
    for (IPhoto p : cache) {
      if (p.matches(regexp)) {
        result.add(p);
      }
    }
    return result;
  }

  @Override
  public void setComparator(Comparator<IPhoto> c) {
    super.setComparator(c);
    cache.sort(c);
  }

  @Override
  public void processEvent(PhotoLibraryEvent e) {
    IPhoto photo = e.getPhoto();
    if (e instanceof PhotoAddedLibraryEvent) {
      if (passesFilter(photo) && !cache.contains(photo)) {
        cache.add(photo);
        cache.sort(comparator);
      }
      return;
    }
    if (e instanceof PhotoRemovedLibraryEvent) {
      cache.remove(photo);
      return;
    }
    if (e instanceof PhotoChangedLibraryEvent) {
      boolean inCache = cache.contains(photo);
      boolean passes = passesFilter(photo);

      if (passes && !inCache) {
        cache.add(photo);
      } else if (!passes && inCache) {
        cache.remove(photo);
      }
      if (passes) {
        cache.sort(comparator);
      }
      return;
    }
    rebuildCache();
  }
};
