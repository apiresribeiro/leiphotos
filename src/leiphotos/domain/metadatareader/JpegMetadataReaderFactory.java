package leiphotos.domain.metadatareader;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Fabrica de leitores de metadados JPEG.
 */
public enum JpegMetadataReaderFactory {
  INSTACE;

  /**
   * Cria um leitor de metadados para o ficheiro indicado.
   *
   * @param file ficheiro JPEG
   * @return leitor de metadados
   * @throws JpegMetadataException se houver erro ao interpretar metadados
   * @throws FileNotFoundException se o ficheiro nao existir
   */
  public JpegMetadataReader createMetadataReader(File file) throws JpegMetadataException, FileNotFoundException {
    return new JavaXTMetadataReaderAdapter(file);
  }
};
