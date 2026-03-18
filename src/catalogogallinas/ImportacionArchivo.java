package catalogogallinas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Importación de contenidos desde un archivo
 * @author Arturo Gregori
 */
public class ImportacionArchivo {
	/**
	 * Ruta al archivo del que se quiere importar
	 */
	private Path refArchivo;

	/**
	 * Crea una referencia de archivo para importar posteriormente
	 * @param rutaArchivo Ruta del archivo
	 */
	public ImportacionArchivo(String rutaArchivo) {
		this.refArchivo = Path.of(rutaArchivo);
	}

	/**
	 * Lee el contenido desde un archivo con la ruta asignada previamente al objeto
	 * @return Lista de contenidos leídos del archivo, o lista vacía si hay error
	 */
	public List<String> cargar() {
		try {
			return Files.readAllLines(refArchivo);
		} catch (IOException e) {
			System.err.println("Error al cargar el archivo: " + e.getMessage());
			return new ArrayList<>();
		}
	}
}
