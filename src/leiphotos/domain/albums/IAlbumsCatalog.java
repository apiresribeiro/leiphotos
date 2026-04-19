package leiphotos.domain.albums;

import java.util.List;
import java.util.Set;

import leiphotos.domain.facade.IPhoto;

public interface IAlbumsCatalog {

  /**
   * Cria um album com um dado nome 
   * 
   * @param albumName nome do album que se pretende criar
   * @return true se o album for criado, false caso contrario
   * @ensures que o Album criado está vazio && o album criado tem o nome albumName
   */
  boolean createAlbum(String albumName);

  /**
   * Apaga o album com um dado nome
   * 
   * @param albumName nome do album a ser apagado
   * @return true se um album for apagado, false caso contrario
   * @ensures que nao existem mais albuns com o nome albumName
   */
  boolean deleteAlbum(String albumName);

  /**
   * Afere se um dado album já existe no catalogo
   * 
   * @param albumName nome do album que se pretende verificar a existencia 
   * @return true se o album existir no catalogo, false caso contrario
   */
  boolean containsAlbum(String albumName);

  /**
   * Acrescenta fotos IPhoto a um album
   * 
   * @param albumName nome do album ao qual vao ser acrescentadas as fotos
   * @param selectedPhotos um Set de fotos a serem acrescentadas
   * @return true se as fotos forem acrescentadas ao album, false caso contrario
   */
  boolean addPhotos(String albumName, Set<IPhoto> selectedPhotos);

  /**
   * Remove certas fotos IPhoto de um album
   * 
   * @param albumName nome do album a que se pretende remover as fotos
   * @param selectedPhotos um Set de fotos a serem removidas
   * @return true se as fotos forem removidas, false caso contrario
   */
  boolean removePhotos(String albumName, Set<IPhoto> selectedPhotos);

  /**
   * Permite resgatar as fotos de um dado album
   * 
   * @param albumName nome do album a que se pretende ir buscar as fotos
   * @return uma lista das fotos de um dado album 
   * @ensures sao retornadas todas as fotos do album
   */
  List<IPhoto> getPhotos(String albumName);
  
  /**
   * Permite resgatar os nomes dos albuns existentes
   * 
   * @return um Set dos nomes de albuns existentes 
   * @ensures a lista contem todos os nomes de albuns existentes
   */
  Set<String> getAlbumNames();
};
