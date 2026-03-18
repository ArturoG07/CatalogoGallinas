package catalogogallinas;

/**
 * Lanzador de aplicación de consola de texto con menús. Aplicación
 * de texto usando catalogo de gallinas.
 * @author Arturo Gregori
 */
public class LanzadorCatalogoGallinas {
	/**
	 * Inicia el menú principal del programa.
	 * @param argumentos opciones de ejecución -no se usan-
	 */
	public static void main (String[] argumentos){
		ControlCatalogoGallinas programa;

		programa = new ControlCatalogoGallinas();

		programa.buclePrincipal();
	}
}