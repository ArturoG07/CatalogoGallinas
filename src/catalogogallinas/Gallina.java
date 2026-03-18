package catalogogallinas;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una gallina con atributos como nombre, edad, raza, huevos puestos y color de plumas.
 * Esta clase es un modelo simple para almacenar información sobre una gallina.
 * @author Arturo Gregori
 */
public class Gallina {
	/**
	 * Nombre de la gallina
	 */
	private String nombre;
	/**
	 * Edad de la gallina
	 */
	private int edad;
	/**
	 * Raza de la gallina
	 */
	private String raza;
	/**
	 * Cantidad de huevos puestos por la gallina
	 */
	private int huevosPuestos;
	/**
	 * Color de las plumas de la gallina
	 */
	private String colorPlumas;

	/**
	 * Construye una nueva Gallina con los atributos especificados.
	 *
	 * @param nombre el nombre de la gallina
	 * @param edad la edad de la gallina en años
	 * @param raza la raza de la gallina
	 * @param huevosPuestos el número de huevos puestos por la gallina
	 * @param colorPlumas el color de las plumas de la gallina
	 */
	public Gallina(String nombre, int edad, String raza, int huevosPuestos, String colorPlumas) {
		this.nombre = nombre;
		this.edad = edad;
		this.raza = raza;
		this.huevosPuestos = huevosPuestos;
		this.colorPlumas = colorPlumas;
	}
	/**
	 * Devuelve el nombre de la gallina
	 * @return nombre Nombre de la gallina
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Convierte los atributos de la gallina en una lista de cadenas para exportación.
	 * @return lista de cadenas con nombre, edad, raza, huevos puestos y color de plumas
	 */
	public List<String> toListaExportacion() {
		List<String> lista = new ArrayList<>();
		lista.add(nombre);
		lista.add(String.valueOf(edad));
		lista.add(raza);
		lista.add(String.valueOf(huevosPuestos));
		lista.add(colorPlumas);
		return lista;
	}

	/**
	 * Devuelve la edad de la gallina.
	 * @return edad de la gallina
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Devuelve la raza de la gallina.
	 * @return raza de la gallina
	 */
	public String getRaza() {
		return raza;
	}

	/**
	 * Devuelve la cantidad de huevos puestos por la gallina.
	 * @return cantidad de huevos puestos
	 */
	public int getHuevosPuestos() {
		return huevosPuestos;
	}

	/**
	 * Devuelve el color de las plumas de la gallina.
	 * @return color de las plumas
	 */
	public String getColorPlumas() {
		return colorPlumas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public void setHuevosPuestos(int huevosPuestos) {
		this.huevosPuestos = huevosPuestos;
	}

	public void setColorPlumas(String colorPlumas) {
		this.colorPlumas = colorPlumas;
	}
}
