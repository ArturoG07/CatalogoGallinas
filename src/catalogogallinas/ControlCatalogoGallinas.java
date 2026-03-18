package catalogogallinas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Núcleo de aplicación de consola de texto con menús. Aplicación
 * de texto usando gallinas. Gestiona un catálogo de gallinas con
 * operaciones como mostrar, cambiar, modificar, listar, añadir,
 * exportar e importar gallinas.
 */
public class ControlCatalogoGallinas {

	/**
	 * Formato para la ruta del archivo de exportación de una gallina individual.
	 */
	public static final String FORMATO_RUTA_ARCHIVO_EXPORTACIÓN= "gallina %s.txt";

	/**
	 * Lista de opciones disponibles en el menú principal.
	 */
	List<String> OPCIONES_MENÚ_PRINCIPAL = new ArrayList<>(Arrays.asList(
			"Mostrar la gallina",
			"Cambiar la gallina",
			"Modificar gallina",
			"Listar gallinas",
			"Añadir gallina manualmente",
			"Exportar lista de gallinas",
			"Importar lista de gallinas",
			"Exportar gallina individual",
			"Importar gallina individual",
			"Salir"
	));
	/**
	 * Lista que guarda a las gallinas
	 */
	List<Gallina> gallinas = new ArrayList<>();

	/**
	 * Gallina activa
	 */
	private Gallina gallina;

	/**
	 * Vista general para mostrar gallinas y menús
	 */
	VistaGeneral vistaGeneral = new VistaGeneral();

	/**
	 * Inicializador del control de las gallinas
	 */
	public ControlCatalogoGallinas() {
		init();
	}


	/**
	 * Pide al usuario una gallina y prepara la primera gallina
	 */
	public void init() {
		VistaMenú menú;
		menú = new VistaMenú("Catalogo de gallinas", OPCIONES_MENÚ_PRINCIPAL);
		menú.mostrarTitulo();
		cambiarGallina();
	}

	/**
	 * Gestión del menú principal. Desde este menú
	 * se ejecutan las opciones disponibles a elección del usuario.
	 * A la salida del menú se finaliza el programa.
	 */
	public void buclePrincipal() {
		VistaMenú menú;
		int opción = -1;

		menú = new VistaMenú("Catalogo de gallinas", OPCIONES_MENÚ_PRINCIPAL);

		do {
			System.out.println("Esta es la gallina " + gallina.getNombre());
			vistaGeneral.mostrarTitulo("Menu Principal");
			menú.mostrarOpciones();
			try {
				opción = menú.pedirOpcion();
			} catch (ExcepcionES e) {
				vistaGeneral.mostrarAviso("Introduzca una opción en formato numérico");
				opción = -1;
			}
			switch (opción) {
				case 1:
					mostrarGallina();
					break;
				case 2:
					cambiarGallina();
					break;
				case 3:
					ponerHuevos();
					break;
				case 4:
					listarGallinas();
					break;
				case 5:
					añadirGallinaManualmente();
					break;
				case 6:
					exportarListaGallinas();
					break;
				case 7:
					importarListaGallinas();
					break;
				case 8:
					exportarGallina();
					break;
				case 9:
					importarGallinaIndividual();
					break;
				case 10:
					break;
				default:
					opciónNoDisponible();
					break;
			}

		} while (opción != 10);
		if (vistaGeneral.pedirConfirmacion("¿Estas seguro que quieres salir?")) {
			vistaGeneral.mostrarAviso("FIN");
		} else {
			buclePrincipal();
		}
	}
	/**
	 * Modifica la gallina activa añadiendo la cantidad de huevos especificada por el usuario.
	 */
	public void ponerHuevos() {
		boolean correcto = false;
		int n = 0;
		while (!correcto) {
			try {
				n = vistaGeneral.pedirNúmero("Introduzca la cantidad de huevos introducidos por la gallina");
				correcto = true;
			} catch (ExcepcionES e) {
				vistaGeneral.mostrarAviso("Introduzca un número válido");
			}
		}
		for (int i = 0; i <= n; i++) {
			gallina.ponerHuevo();
		}
	}
	/**
	 * Muestra la información detallada de la gallina activa.
	 */
	public void mostrarGallina() {
		vistaGeneral.mostrarTitulo("Gallina " + gallina.getNombre());
		vistaGeneral.mostrarTexto("Edad: " + gallina.getEdad() + " años");
		vistaGeneral.mostrarTexto("Raza: " + gallina.getRaza());
		vistaGeneral.mostrarTexto("Huevos puestos: " + gallina.getHuevosPuestos());
		vistaGeneral.mostrarTexto("Color de plumas: " + gallina.getColorPlumas());
	}

	/**
	 * Exporta la gallina activa a un archivo de texto.
	 */
	public void exportarGallina() {
		ExportaciónArchivo export;
		String rutaArchivo = String.format(FORMATO_RUTA_ARCHIVO_EXPORTACIÓN, gallina.getNombre());
		export = new ExportaciónArchivo(rutaArchivo);
		export.guardar(gallina.toListaExportacion());
	}

	/**
	 * Cambia la gallina activa por otra existente en la lista, basada en el nombre proporcionado por el usuario.
	 */
	public void cambiarGallina() {
		String nombreGallina = vistaGeneral.pedirTexto("Introduce el nombre de la gallina a mostrar");
		for (Gallina g : gallinas) {
			if (g.getNombre().equalsIgnoreCase(nombreGallina)) {
				gallina = g;
				return;
			}
		}
		vistaGeneral.mostrarAviso("No se ha encontrado una gallina con ese nombre");
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
		gallinas.add(nuevaGallina);
		gallina = nuevaGallina;
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
					gallina = gallinaImportada;
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
		String rutaArchivo = String.format(FORMATO_RUTA_ARCHIVO_EXPORTACIÓN, nombreGallina);
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
			gallina = gallinaImportada;
			vistaGeneral.mostrarAviso("Gallina '" + nombre + "' importada correctamente");
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			vistaGeneral.mostrarAviso("Error al importar la gallina. Formato incorrecto en el archivo");
		}
	}

	/**
	 * Muestra un aviso cuando se selecciona una opción no disponible.
	 */
	public void opciónNoDisponible() {
		vistaGeneral.mostrarAviso("Opción no disponible");
	}
}
