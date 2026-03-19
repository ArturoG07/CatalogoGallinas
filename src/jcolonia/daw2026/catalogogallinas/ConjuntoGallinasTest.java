package jcolonia.daw2026.catalogogallinas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas unitarias para la clase {@link ConjuntoGallinas}.
 * @author Arturo Gregori
 */
public class ConjuntoGallinasTest {

	/** Conjunto de gallinas utilizado en las pruebas */
	private ConjuntoGallinas conjunto;

	/**
	 * Inicializa un nuevo conjunto antes de cada prueba.
	 */
	@BeforeEach
	public void setUp() {
		conjunto = new ConjuntoGallinas();
	}

	/**
	 * Prueba que se pueda añadir una gallina al conjunto correctamente.
	 * Comprueba que:
	 * <ul>
	 *   <li>El tamaño del conjunto aumente</li>
	 *   <li>El nombre de la gallina añadida sea correcto</li>
	 * </ul>
	 */
	@Test
	public void testAñadirGallina() {
		Gallina g = new Gallina("Lola", 2, "Andaluza", 10, "Blanco");
		conjunto.añadirGallina(g);
		assertEquals(1, conjunto.gallinas.size());
		assertEquals("Lola", conjunto.gallinas.get(0).getNombre());
	}

	/**
	 * Prueba la selección de una gallina por índice.
	 * Comprueba que se devuelve la gallina correcta.
	 */
	@Test
	public void testSeleccionarGallina() {
		Gallina g = new Gallina("Pepita", 3, "Leghorn", 15, "Marrón");
		conjunto.añadirGallina(g);
		Gallina seleccionada = conjunto.seleccionarGallina(0);
		assertEquals("Pepita", seleccionada.getNombre());
	}

	/**
	 * Prueba la búsqueda de una gallina existente por nombre.
	 * Comprueba que se devuelve la posición correcta.
	 */
	@Test
	public void testBuscarGallinaExistente() {
		Gallina g = new Gallina("Clara", 1, "Sussex", 5, "Negro");
		conjunto.añadirGallina(g);
		int posicion = conjunto.buscarGallina("Clara");
		assertEquals(0, posicion);
	}

	/**
	 * Prueba la búsqueda de una gallina que no existe en el conjunto.
	 * Según la implementación actual, devuelve 0 si no se encuentra.
	 */
	@Test
	public void testBuscarGallinaNoExistente() {
		Gallina g = new Gallina("Clara", 1, "Sussex", 5, "Negro");
		conjunto.añadirGallina(g);
		int posicion = conjunto.buscarGallina("Otra");
		assertEquals(0, posicion);
	}

	/**
	 * Prueba la eliminación de una gallina por índice.
	 * Comprueba que el conjunto quede vacío después de eliminar.
	 */
	@Test
	public void testEliminarGallina() {
		Gallina g = new Gallina("Lola", 2, "Andaluza", 10, "Blanco");
		conjunto.añadirGallina(g);
		conjunto.eliminarGallina(0);
		assertEquals(0, conjunto.gallinas.size());
	}

	/**
	 * Prueba la eliminación de una gallina con un índice incorrecto.
	 * Verifica que no se lance ninguna excepción.
	 */
	@Test
	public void testEliminarGallinaPosicionIncorrecta() {
		assertDoesNotThrow(() -> conjunto.eliminarGallina(5));
	}

	/**
	 * Prueba añadir varias gallinas al conjunto.
	 * Comprueba que el tamaño del conjunto sea el esperado.
	 */
	@Test
	public void testAñadirVariasGallinas() {
		conjunto.añadirGallina(new Gallina("A", 1, "Raza1", 1, "Rojo"));
		conjunto.añadirGallina(new Gallina("B", 2, "Raza2", 2, "Azul"));
		assertEquals(2, conjunto.gallinas.size());
	}

	/**
	 * Prueba la búsqueda entre varias gallinas.
	 * Comprueba que la posición devuelta corresponda al nombre buscado.
	 */
	@Test
	public void testBuscarEntreVariasGallinas() {
		conjunto.añadirGallina(new Gallina("A", 1, "Raza1", 1, "Rojo"));
		conjunto.añadirGallina(new Gallina("B", 2, "Raza2", 2, "Azul"));
		int posicion = conjunto.buscarGallina("B");
		assertEquals(1, posicion);
	}

	/**
	 * Prueba seleccionar una gallina con un índice incorrecto.
	 * Verifica que se lance una excepción {@link IndexOutOfBoundsException}.
	 */
	@Test
	public void testSeleccionarGallinaIndiceIncorrecto() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			conjunto.seleccionarGallina(0);
		});
	}
}