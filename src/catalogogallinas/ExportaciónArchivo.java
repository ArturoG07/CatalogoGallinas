package catalogogallinas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Exportacion de contenidos a un archivo
 * @author Arturo Gregori
 */
public class ExportaciónArchivo {
	/**
	 * Ruta al archivo al que se quiere exportar
	 */
	private Path refArchivo;

	/**
	 * Crea una referenci de archivo para exportar posteriormente
	 * @param rutaArchivo Ruta del archivo
	 */
	public ExportaciónArchivo(String rutaArchivo) {
		this.refArchivo = Path.of(rutaArchivo);
	}

	/**
	 * Guarda el contenido en un archivo, con la ruta asignada previamente al objeto
	 * @param contenidos Lista de contenidos que se desea guardar
	 */
	public void guardar(List<String> contenidos) {
		try {
			Files.write(refArchivo, contenidos);
		} catch (IOException e) {
			System.err.println("Error al guardar el archivo: " + e.getMessage());
		}
	}
}