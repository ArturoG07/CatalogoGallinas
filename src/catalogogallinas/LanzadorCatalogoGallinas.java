package catalogogallinas;

/**
 * Lanzador de aplicación de consola de texto con menús. Aplicación
 * de texto usando catalogo de gallinas.
 */

// TODO AÑADIR METODOS DE BORRAR Y MODIFICAR Y COMPROBAR GALLINAS DUPLICADAS AL IMPORTAR
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