package jcolonia.daw2026.catalogogallinas;

/**
 * Se lanza cuando se escribe por consola un texto incorrecto
 * @author Arturo Gregori
 */
public class ExcepcionES extends RuntimeException {
  /**
   * Mensaje de la excepcion
   * @param message Mensaje que se lanza
   */
	public ExcepcionES(String message) {
		super(message);
	}
}
