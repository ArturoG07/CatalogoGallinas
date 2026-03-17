package catalogogallinas;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase para creacion de menús
 */
public class VistaMenú {
	/**
	 * Titulo del menu
	 */
	private String titulo;
	/**
	 * Opciones de formato
	 */
	private List<String> opciones;

	/**
	 * Constructor de la clase, con opciones asigna los formatos correspondientes
	 * @param titulo Titulo del menú
	 * @param opciones Lista con las opciones
	 */
	public VistaMenú(String titulo, List<String> opciones) {
		this.titulo = titulo;
		this.opciones = new ArrayList<String>(opciones);
	}

	/**
	 * Imprime el titulo por pantalla
	 */
	public void mostrarTitulo() {
		System.out.println(titulo);
		for (int i= 0; i < titulo.length(); i++) {
			System.out.print("_");
		}
		System.out.println();
	}

	/**
	 * Muestra por pantalla todas las opciones
	 */
	public void mostrarOpciones() {
		for (int i= 0; i < opciones.size(); i++) {
			if (i+1 != opciones.size()) {
			System.out.print(i+1 + ")"); } else {
				System.out.print(0 + ")");
			}
			System.out.print(" " + opciones.get(i));
			System.out.println();
		}
	}

	/**
	 * Pide por consola la opcion al usuario
	 * @return num Numero de opcion leido por consola
	 */
	public int pedirOpcion() {
		Scanner sc;
		sc = new Scanner(System.in);
		int numero;
			try {
				System.out.print("Elija la opcion");
				numero = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.nextLine(); // Consumir la entrada inválida
				throw new ExcepcionES("Introduzca un numero valido");
			}
		return numero;
	}
}
