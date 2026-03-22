package leiphotos.domain.core;

public abstract class ATrashLibrary implements TrashLibrary {
  protected abstract void clean();

  protected abstract boolean cleaningTime();
};
