package catalogogallinas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConjuntoGallinasTest {

	private ConjuntoGallinas conjunto;

	@BeforeEach
	public void setUp() {
		conjunto = new ConjuntoGallinas();
	}

	@Test
	public void testAñadirGallina() {
		Gallina g = new Gallina("Lola", 2, "Andaluza", 10, "Blanco");

		conjunto.añadirGallina(g);

		assertEquals(1, conjunto.gallinas.size());
		assertEquals("Lola", conjunto.gallinas.get(0).getNombre());
	}

	@Test
	public void testSeleccionarGallina() {
		Gallina g = new Gallina("Pepita", 3, "Leghorn", 15, "Marrón");
		conjunto.añadirGallina(g);

		Gallina seleccionada = conjunto.seleccionarGallina(0);

		assertEquals("Pepita", seleccionada.getNombre());
	}

	@Test
	public void testBuscarGallinaExistente() {
		Gallina g = new Gallina("Clara", 1, "Sussex", 5, "Negro");
		conjunto.añadirGallina(g);

		int posicion = conjunto.buscarGallina("Clara");

		assertEquals(0, posicion);
	}

	@Test
	public void testBuscarGallinaNoExistente() {
		Gallina g = new Gallina("Clara", 1, "Sussex", 5, "Negro");
		conjunto.añadirGallina(g);

		int posicion = conjunto.buscarGallina("Otra");

		assertEquals(0, posicion); // Devuelve 0 si no encuentra
	}

	@Test
	public void testEliminarGallina() {
		Gallina g = new Gallina("Lola", 2, "Andaluza", 10, "Blanco");
		conjunto.añadirGallina(g);

		conjunto.eliminarGallina(0);

		assertEquals(0, conjunto.gallinas.size());
	}

	@Test
	public void testEliminarGallinaPosicionIncorrecta() {
		assertDoesNotThrow(() -> conjunto.eliminarGallina(5));
	}

	@Test
	public void testAñadirVariasGallinas() {
		conjunto.añadirGallina(new Gallina("A", 1, "Raza1", 1, "Rojo"));
		conjunto.añadirGallina(new Gallina("B", 2, "Raza2", 2, "Azul"));

		assertEquals(2, conjunto.gallinas.size());
	}

	@Test
	public void testBuscarEntreVariasGallinas() {
		conjunto.añadirGallina(new Gallina("A", 1, "Raza1", 1, "Rojo"));
		conjunto.añadirGallina(new Gallina("B", 2, "Raza2", 2, "Azul"));

		int posicion = conjunto.buscarGallina("B");

		assertEquals(1, posicion);
	}

	@Test
	public void testSeleccionarGallinaIndiceIncorrecto() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			conjunto.seleccionarGallina(0);
		});
	}
}