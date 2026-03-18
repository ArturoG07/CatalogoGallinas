package catalogogallinas;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GallinaTest {

	@Test
	void testConstructorYGetters() {
		Gallina g = new Gallina("Lola", 2, "Andaluza", 10, "Blanco");

		assertEquals("Lola", g.getNombre());
		assertEquals(2, g.getEdad());
		assertEquals("Andaluza", g.getRaza());
		assertEquals(10, g.getHuevosPuestos());
		assertEquals("Blanco", g.getColorPlumas());
	}

	@Test
	void testSetters() {
		Gallina g = new Gallina("Lola", 2, "Andaluza", 10, "Blanco");

		g.setNombre("Pepita");
		g.setEdad(3);
		g.setRaza("Leghorn");
		g.setHuevosPuestos(20);
		g.setColorPlumas("Negro");

		assertEquals("Pepita", g.getNombre());
		assertEquals(3, g.getEdad());
		assertEquals("Leghorn", g.getRaza());
		assertEquals(20, g.getHuevosPuestos());
		assertEquals("Negro", g.getColorPlumas());
	}

	@Test
	void testToListaExportacion() {
		Gallina g = new Gallina("Lola", 2, "Andaluza", 10, "Blanco");

		List<String> lista = g.toListaExportacion();

		assertEquals(5, lista.size());
		assertEquals("Lola", lista.get(0));
		assertEquals("2", lista.get(1));
		assertEquals("Andaluza", lista.get(2));
		assertEquals("10", lista.get(3));
		assertEquals("Blanco", lista.get(4));
	}

	@Test
	void testToListaExportacionFormatoNumeros() {
		Gallina g = new Gallina("A", 0, "Raza", 0, "Color");

		List<String> lista = g.toListaExportacion();

		assertEquals("0", lista.get(1)); // edad
		assertEquals("0", lista.get(3)); // huevos
	}

	@Test
	void testCambiosReflejadosEnListaExportacion() {
		Gallina g = new Gallina("Lola", 2, "Andaluza", 10, "Blanco");

		g.setNombre("Nueva");
		g.setEdad(5);

		List<String> lista = g.toListaExportacion();

		assertEquals("Nueva", lista.get(0));
		assertEquals("5", lista.get(1));
	}
}
