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
	 * Exporta la lista completa de gallinas a un archivo. (Método no implementado)
	 */
	public void exportarListaGallinas() {}

	/**
	 * Importa una lista de gallinas desde un archivo. (Método no implementado)
	 */
	public void importarListaGallinas() {}

	/**
	 * Importa una gallina individual desde un archivo. (Método no implementado)
	 */
	public void importarGallinaIndividual() {}

	/**
	 * Muestra un aviso cuando se selecciona una opción no disponible.
	 */
	public void opciónNoDisponible() {
		vistaGeneral.mostrarAviso("Opción no disponible");
	}
}
