package leiphotos.domain.views;

import leiphotos.domain.core.Library;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import leiphotos.domain.facade.IPhoto;

public abstract class ALibraryView implements ILibraryView {
  private final Library library;
  protected Predicate<IPhoto> filter;
  protected Comparator<IPhoto> comparator = Comparator.comparing(IPhoto::size);

  protected ALibraryView(Library library, Predicate<IPhoto> filter) {
    this.library = library;
    this.filter = filter;
  }

  @Override
  public List<IPhoto> getMatches(String regexp) {
    List<IPhoto> result = new ArrayList<>();

    for (IPhoto photo : getPhotos()) {
      if (photo.matches(regexp)) {
        result.add(photo);
      }
    }
    return result;
  }

  @Override
  public List<IPhoto> getPhotos() {
    List<IPhoto> result = new ArrayList<>();
    for (IPhoto photo : library.getPhotos()) {
      if (filter.test(photo)) {
        result.add(photo);
      }
    }
    result.sort(comparator);
    return result;
  }

  @Override
  public int numberOfPhotos() {
    return getPhotos().size();
  }

  @Override
  public void setComparator(Comparator<IPhoto> C) {
    this.comparator = C;
  }
};
