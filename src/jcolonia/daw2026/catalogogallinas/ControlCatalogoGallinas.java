package jcolonia.daw2026.catalogogallinas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Núcleo de aplicación de consola de texto con menús. Aplicación
 * de texto usando gallinas. Gestiona un catálogo de gallinas con
 * operaciones como mostrar, cambiar, modificar, listar, añadir,
 * exportar e importar gallinas.
 * @author Arturo Gregori
 */
public class ControlCatalogoGallinas {
	/**
	 * Formato para la ruta del archivo de exportación de una gallina individual.
	 */
	public static final String FORMATO_RUTA_ARCHIVO_EXPORTACIÓN = "gallina %s.txt";

	/**
	 * Lista de opciones disponibles en el menú principal.
	 */
	List<String> OPCIONES_MENÚ_PRINCIPAL = new ArrayList<>(Arrays.asList(
			"Mostrar la gallina seleccionada",
			"Cambiar la gallina",
			"Modificar gallina",
			"Listar gallinas",
			"Añadir gallina manualmente",
			"Exportar lista de gallinas",
			"Importar lista de gallinas",
			"Exportar gallina seleccionada",
			"Importar gallina individual",
			"Borrar gallina",
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
					modificarGallina();
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
					borrarGallina();
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

	/**
	 * Modifica uno o varios atributos de una gallina
	 */
	public void modificarGallina() {
		List<String> OPCIONES_MODIFICACION = new ArrayList<>(Arrays.asList(
				"Nombre",
				"Edad",
				"Raza",
				"Huevos puestos",
				"Color de plumas",
				"Cancelar"
		));
		gallinas.listarGallinas();
		String nombreGallina = vistaGeneral.pedirTexto("Introduce el nombre de la gallina a modificar");
		int g = gallinas.buscarGallina(nombreGallina);
		int opcion;

		do {
			VistaMenú menuMod = new VistaMenú("Moficaciones", OPCIONES_MODIFICACION);
			menuMod.mostrarTitulo();
			menuMod.mostrarOpciones();
			try {
				opcion = menuMod.pedirOpcion();
			} catch (ExcepcionES e) {
				vistaGeneral.mostrarAviso("Introduzca una opción en formato numérico");
				opcion = -1;
			}
			switch (opcion) {
				case 1:
					String nombre = vistaGeneral.pedirTexto("Introduce el nuevo nombre de la gallina");
					if (vistaGeneral.pedirConfirmacion("¿Estas seguro que quieres cambiar el nombre a " + nombre)) {
						gallinas.seleccionarGallina(g).setNombre(nombre);
						vistaGeneral.mostrarAviso("Nombre cambiado con exito");
					}
					break;
				case 2:
					int edad = vistaGeneral.pedirNúmero("Introduce la nueva edad de la gallina");
					if (vistaGeneral.pedirConfirmacion("¿Estas seguro que quieres cambiar la edad a " + edad)) {
						gallinas.seleccionarGallina(g).setEdad(edad);
						vistaGeneral.mostrarAviso("Edad cambiada con exito");
					}
					break;
				case 3:
					String raza = vistaGeneral.pedirTexto("Introduce la nueva raza de de la gallina");
					if (vistaGeneral.pedirConfirmacion("¿Estas seguro que quieres cambiar la raza a  " + raza)) {
						gallinas.seleccionarGallina(g).setRaza(raza);
						vistaGeneral.mostrarAviso("Raza cambiada con exito");
					}
					break;
				case 4:
					int huevos = vistaGeneral.pedirNúmero("Introduce la nueva cantidad de huevos");
					if (vistaGeneral.pedirConfirmacion("¿Estas seguro que quieres cambiar la cantidade de huevos a " + huevos)) {
						gallinas.seleccionarGallina(g).setHuevosPuestos(huevos);
						vistaGeneral.mostrarAviso("Cantidad de huevos cambiado con exito");
					}
					break;
				case 5:
					String color = vistaGeneral.pedirTexto("Introduce el nuevo color de la gallina");
					if (vistaGeneral.pedirConfirmacion("¿Estas seguro que quieres cambiar el color a  " + color)) {
						gallinas.seleccionarGallina(g).setColorPlumas(color);
						vistaGeneral.mostrarAviso("Color cambiado con exito");
					}
					break;
				case 0:
					break;
				default:
					gallinas.opciónNoDisponible();
					break;
			}
		} while (opcion != 0);

	}

	/**
	 * Borra la gallina indicada
	 */
	public void borrarGallina() {
		gallinas.listarGallinas();
		gallina = gallinas.seleccionarGallina(gallinas.buscarGallina(vistaGeneral.pedirTexto("Introduce el nombre de la gallina a borrar")));
		if (vistaGeneral.pedirConfirmacion("¿Estas seguro que quieres borrar la gallina " + gallina.getNombre() + "?")) {
			gallinas.eliminarGallina((gallinas.buscarGallina(gallina.getNombre())));
			vistaGeneral.mostrarAviso("Gallina borrada con exito");
		}
	}
}
