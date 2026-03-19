package jcolonia.daw2026.catalogogallinas;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Vista general para la gestion de las tablas de multiplicar
 * @author Arturo Gregori
 */
public class VistaGeneral {
	/**
	 * Formato para los textos
	 */
	private String FORMATO_PRINTF_MOSTRARTEXTO;
	/**
	 * Formato para los avisos
	 */
	private String FORMATO_PRINTF_MOSTRARAVISO;

	/**
	 * Iniciacion de los formatos
	 */
	public VistaGeneral() {
		// Formato para texto normal: texto seguido de un salto de línea
		FORMATO_PRINTF_MOSTRARTEXTO = "%s %n";

		// Formato para avisos: rodeado de asteriscos y con salto de línea
		FORMATO_PRINTF_MOSTRARAVISO = "!!! %s !!!%n";
	}

	/**
	 * Muestra por pantalla un texto
	 * @param texto Texto que se quiere mostrar
	 * */
	public void mostrarTexto(String texto) {
		System.out.printf(FORMATO_PRINTF_MOSTRARTEXTO, texto);
	}

	/**
	 * Muestra por pantalla un aviso
	 * @param texto Aviso que se quiere mostrar
	 */
	public void mostrarAviso(String texto) {
		System.out.printf(FORMATO_PRINTF_MOSTRARAVISO, texto);
	}

	/**
	 * Muestra por pantalla un titulo
	 * @param titulo Titulo que se quiere mostrar
	 */
	public void mostrarTitulo(String titulo) {
		System.out.println(titulo);
		for (int i= 0; i < titulo.length(); i++) {
			System.out.print("_");
		}
		System.out.println();
	}

	/**
	 * Muestra por pantalla un titulo
	 * @param texto Titulo que se quiere mostrar
	 */
	public void mostrarTitulo2(String texto) {
		System.out.printf(texto);
	}

	/**
	 * Pide un numero por consola
	 * @param texto Mensaje para pedir el numero
	 * @return Num Numero escrito por consola
	 */
	public int pedirNúmero(String texto){
		Scanner sc = getScEntrada();
		int num = 0;
		boolean correcto = false;

		System.out.printf(FORMATO_PRINTF_MOSTRARTEXTO, texto);
		while (!correcto) {
			try {
				num = sc.nextInt();
				correcto = true;
			} catch (InputMismatchException e) {
				sc.nextLine();
				mostrarAviso("Introduzca un número válido en formato numérico (1, 2, 3)");
				correcto = false;
			}
		}

		sc.nextLine();
		return num;
	}

	/**
	 * Pausa la ejecución hasta que el usuario pulse Enter
	 * @param texto Mensaje que se muestra antes de la pausa
	 */
	public void pausa(String texto) {
		Scanner sc;
		sc = getScEntrada();
		System.out.printf(FORMATO_PRINTF_MOSTRARTEXTO, texto);
		sc.nextLine();
	}

	/**
	 * Pide confirmacion para operaciones
	 * @param texto Texto para pedir confirmacion
	 * @return true o false
	 */
	public boolean pedirConfirmacion(String texto) {
		Scanner sc = getScEntrada();
		String entrada;
		boolean confirmado = false;
		boolean salir = false;

		while (!salir) {
			System.out.printf(FORMATO_PRINTF_MOSTRARTEXTO, texto);
			entrada = sc.nextLine();

			if (entrada.equalsIgnoreCase("si") || entrada.equalsIgnoreCase("true")) {
				confirmado = true;
				salir = true;
			} else if (entrada.equalsIgnoreCase("no") || entrada.equalsIgnoreCase("false")) {
				confirmado = false;
				salir = true;
			} else {
				System.out.printf(FORMATO_PRINTF_MOSTRARAVISO, "Introduzca si o no");
			}
		}
		return confirmado;
	}

	/**
	 * Muestra una lista de textos por pantalla
	 * @param lista Lista de textos a mostrar
	 */
	public void mostrarLista(List<String> lista) {
		for (String elemento : lista) {
			System.out.printf(FORMATO_PRINTF_MOSTRARTEXTO, elemento);
		}
	}

	/**
	 * Devuelve un escaner de entrada
	 * @return Escaner de entrada
	 */
	public Scanner getScEntrada() {
		Scanner sc;
		sc = new Scanner(System.in);
		return sc;
	}

	public String pedirTexto(String texto) {
		Scanner sc = getScEntrada();
		mostrarTexto(texto);
		return sc.nextLine();
	}
}
