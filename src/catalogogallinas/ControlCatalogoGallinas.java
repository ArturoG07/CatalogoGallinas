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
			"Mostrar la gallina seleccionada",
			"Cambiar la gallina",
			"Modificar la gallina seleccionada",
			"Listar gallinas",
			"Añadir gallina manualmente",
			"Exportar lista de gallinas",
			"Importar lista de gallinas",
			"Exportar gallina seleccionada",
			"Importar gallina individual",
			"Borrar gallina seleccionada",
			"Salir"
	));
	/**
	 * Lista que guarda a las gallinas
	 */
	ConjuntoGallinas gallinas = new ConjuntoGallinas();

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
		gallinas.importarListaGallinas();
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
		gallina = gallinas.seleccionarGallina(0);

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
					gallinas.cambiarGallina();
					break;
				case 3:
					// gallinas.modificarGallina();
					break;
				case 4:
					gallinas.listarGallinas();
					vistaGeneral.pausa("Pulse enter para continuar");
					break;
				case 5:
					gallinas.añadirGallinaManualmente();
					break;
				case 6:
					gallinas.exportarListaGallinas();
					break;
				case 7:
					if (vistaGeneral.pedirConfirmacion("¿Estas seguro que quiere importar el archivo? Esto reemplazará la lista actual de gallinas")) {
						gallinas.importarListaGallinas();
					}
					break;
				case 8:
					exportarGallina();
					break;
				case 9:
					gallinas.importarGallinaIndividual();
					break;
				case 10:
					// borrarGallina();
					break;
				case 0:
					break;
				default:
					gallinas.opciónNoDisponible();
					break;
			}

		} while (opción != 0);
		if (vistaGeneral.pedirConfirmacion("¿Estas seguro que quieres salir?")) {
			vistaGeneral.mostrarAviso("FIN");
		} else {
			buclePrincipal();
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
		vistaGeneral.pausa("Pulse enter para continuar");
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
}
