package catalogogallinas;

import java.util.ArrayList;
import java.util.List;

/**
 * Guarda un conjunto de gallinas y sus metodos
 * @author Arturo Gregori
 */
public class ConjuntoGallinas {
	/**
	 * Formato para la ruta del archivo de importacion de una gallina individual.
	 */
	public static final String FORMATO_RUTA_ARCHIVO_IMPORTACION= "gallina %s.txt";

	/**
	 * Lista de gallinas
	 */
	List<Gallina> gallinas;
	/**
	 * Vista general para mostrar mensajes
	 */
	VistaGeneral vistaGeneral = new VistaGeneral();

	/**
	 * Inicializador del conjunto de gallinas, creando una lista vacía.
	 */
	public ConjuntoGallinas() {
		gallinas = new ArrayList<>();
	}

	/**
	 * Devuelve una gallina dada su posicion en el conjunto
	 * @param posicion Indice de la gallina en el conjunto
	 * @return Gallina del indice
	 */
	public Gallina seleccionarGallina(int posicion) {
		return gallinas.get(posicion);
	}

	/**
	 * Busca una gallina por su nombre
	 * @param nombre Nombre de la Gallina
	 * @return posicion Indice de la gallina dentro del conjunto
	 */
	public int buscarGallina(String nombre) {
		int posicion = -1;
		for (Gallina gallina : gallinas) {
			if (gallina.getNombre().equals(nombre)) {
				posicion = gallinas.indexOf(gallina);
			}
		}
		if (posicion == -1) {
			vistaGeneral.mostrarAviso("No se ha encontrado la gallina " + nombre);
			vistaGeneral.mostrarAviso("Se seleccionará la primera gallina de la lista");
			posicion = 0;
		}
		return posicion;
	}

	/**
	 * Añade una gallina al listado
	 * @param gallina Objeto de tipo Gallina que se desea añadir
	 */
	public void añadirGallina(Gallina gallina) {
		gallinas.add(gallina);
	}

	/**
	 * Cambia la gallina activa por otra existente en la lista, basada en el nombre proporcionado por el usuario.
	 */
	public void cambiarGallina() {
		listarGallinas();
		String nombreGallina = vistaGeneral.pedirTexto("Introduce el nombre de la gallina a mostrar");
		seleccionarGallina(buscarGallina(nombreGallina));
	}

	/**
	 * Lista todas las gallinas en la colección, mostrando sus nombres.
	 */
	public void listarGallinas() {
		vistaGeneral.mostrarTitulo("Lista de gallinas");
		for (Gallina g : gallinas) {
			vistaGeneral.mostrarTexto("- " + g.getNombre());
		}
	}

	/**
	 * Añade una nueva gallina a la lista de forma manual, solicitando los datos al usuario.
	 */
	public void añadirGallinaManualmente() {
		String nombre = vistaGeneral.pedirTexto("Introduzca el nombre de la gallina");
		int edad = vistaGeneral.pedirNúmero("Introduzca la edad de la gallina");
		String raza = vistaGeneral.pedirTexto("Introduzca la raza de la gallina");
		int huevosPuestos = vistaGeneral.pedirNúmero("Introduzca la cantidad de huevos puestos por la gallina");
		String colorPlumas = vistaGeneral.pedirTexto("Introduzca el color de las plumas de la gallina");
		Gallina nuevaGallina = new Gallina(nombre, edad, raza, huevosPuestos, colorPlumas);
		añadirGallina(nuevaGallina);
		seleccionarGallina(gallinas.indexOf(nuevaGallina));
	}

	/**
	 * Exporta la lista completa de gallinas a un archivo de texto.
	 * Cada línea contiene los datos de una gallina separados por comas.
	 */
	public void exportarListaGallinas() {
		ExportaciónArchivo export = new ExportaciónArchivo("listadoGallinas.txt");
		List<String> contenidos = new ArrayList<>();

		for (Gallina g : gallinas) {
			List<String> datosGallina = g.toListaExportacion();
			contenidos.add(String.join(",", datosGallina));
		}

		if (contenidos.isEmpty()) {
			vistaGeneral.mostrarAviso("No hay gallinas para exportar");
		} else {
			export.guardar(contenidos);
			vistaGeneral.mostrarAviso("Lista de gallinas exportada correctamente a 'listadoGallinas.txt'");
		}
	}
	/**
	 * Importa una lista de gallinas desde un archivo de texto.
	 * Cada línea debe contener los datos de una gallina separados por comas.
	 */
	public void importarListaGallinas() {
		ImportacionArchivo importar = new ImportacionArchivo("listadoGallinas.txt");
		List<String> lineas = importar.cargar();

		if (lineas.isEmpty()) {
			vistaGeneral.mostrarAviso("No se pudo cargar el archivo o está vacío");
			return;
		}
		gallinas.clear();
		int contadorCargadas = 0;
		for (String linea : lineas) {
			String[] datos = linea.split(",");
			if (datos.length == 5) {
				try {
					String nombre = datos[0];
					int edad = Integer.parseInt(datos[1]);
					String raza = datos[2];
					int huevosPuestos = Integer.parseInt(datos[3]);
					String colorPlumas = datos[4];

					Gallina gallinaImportada = new Gallina(nombre, edad, raza, huevosPuestos, colorPlumas);
					gallinas.add(gallinaImportada);
					seleccionarGallina(gallinas.indexOf(gallinaImportada));
					contadorCargadas++;
				} catch (NumberFormatException e) {
					System.err.println("Error al parsear la línea: " + linea);
				}
			}
		}

		vistaGeneral.mostrarAviso("Se cargaron " + contadorCargadas + " gallinas correctamente");
	}

	/**
	 * Importa una gallina individual desde un archivo específico.
	 * El usuario debe proporcionar el nombre de la gallina a importar.
	 */
	public void importarGallinaIndividual() {
		String nombreGallina = vistaGeneral.pedirTexto("Introduce el nombre de la gallina a importar");
		String rutaArchivo = String.format(FORMATO_RUTA_ARCHIVO_IMPORTACION, nombreGallina);
		ImportacionArchivo importar = new ImportacionArchivo(rutaArchivo);
		List<String> lineas = importar.cargar();

		if (lineas.isEmpty()) {
			vistaGeneral.mostrarAviso("No se pudo cargar el archivo: " + rutaArchivo);
			return;
		}

		try {
			String nombre = lineas.get(0);
			int edad = Integer.parseInt(lineas.get(1));
			String raza = lineas.get(2);
			int huevosPuestos = Integer.parseInt(lineas.get(3));
			String colorPlumas = lineas.get(4);

			Gallina gallinaImportada = new Gallina(nombre, edad, raza, huevosPuestos, colorPlumas);
			gallinas.add(gallinaImportada);
			seleccionarGallina(gallinas.indexOf(gallinaImportada));
			vistaGeneral.mostrarAviso("Gallina '" + nombre + "' importada correctamente");
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			vistaGeneral.mostrarAviso("Error al importar la gallina. Formato incorrecto en el archivo");
		}
	}

	/**
	 * Elimina una gallina del conjunto
	 * @param pos Posicion de la gallina a eliminar
	 */
	public void eliminarGallina(int pos) {
		try {
			gallinas.remove(pos);
		}
		catch (IndexOutOfBoundsException e) {
			vistaGeneral.mostrarAviso("No se ha encontrado la gallina en la posición indicada");
		}
	}

	/**
	 * Muestra un aviso cuando se selecciona una opción no disponible.
	 */
	public void opciónNoDisponible() {
		vistaGeneral.mostrarAviso("Opción no disponible");
	}


}
