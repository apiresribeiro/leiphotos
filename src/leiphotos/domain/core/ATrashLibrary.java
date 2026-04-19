package leiphotos.domain.core;

/**
 * Implementacao base para bibliotecas de lixo com limpeza automatica.
 */
public abstract class ATrashLibrary implements TrashLibrary {
  /**
   * Executa a limpeza das fotos que ja nao devem permanecer no lixo.
   */
  protected abstract void clean();

  /**
   * Indica se e momento de executar a limpeza automatica.
   *
   * @return true se deve limpar, false caso contrario
   */
  protected abstract boolean cleaningTime();
};
