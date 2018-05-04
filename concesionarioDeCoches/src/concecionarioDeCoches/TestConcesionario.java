package concecionarioDeCoches;

import java.io.FileNotFoundException;
import java.io.IOException;

import utiles.Menu;
import utiles.Teclado;

public class TestConcesionario {
	static Modificado modificado = new Modificado();
	static Fichero fichero;
	static Concesionario concesionario = new Concesionario();
	static Menu menuFichero = new Menu("Gestion de Ficheros",
			new String[] { "Nuevo", "Abrir", "Guardar", "Guardar como" });

	public static void main(String[] arg) {

		Menu menuCoche = new Menu("Concesionario de coches", new String[] { "Alta", "Baja", "Mostrar un coche",
				"Mostrar un concecionario", "numero de coches", "coches de un mismo color", "Gestionar ficheros" });

		do {
			switch (menuCoche.gestionar()) {
			case 1:
				alta();
				break;

			case 2:
				baja();
				break;

			case 3:
				getCoche();
				break;

			case 4:
				System.out.println(concesionario.toString());
				break;

			case 5:
				System.out.println("Numero de coches es: " + concesionario.size());
				break;

			case 6:
				buscarCocheColores();
				break;

			case 7:
				GestionarFicheros();
				break;

			default:
				System.out.println("Saliendo...");
				return;
			}

		} while (true);

	}

	private static void GestionarFicheros() {

		do {
			switch (menuFichero.gestionar()) {
			case 1:
				nuevo();
				break;

			case 2:
				abrir();
				break;

			case 3:
				guardar();
				break;

			case 4:
				guardarComo();
				break;

			case 5:
				System.out.println("Numero de coches es: " + concesionario.size());
				break;

			case 6:
				buscarCocheColores();
				break;

			case 7:
				GestionarFicheros();
				break;

			default:
				System.out.println("Saliendo...");
				return;
			}

		} while (true);
	}

	private static void guardarComo() {
		try {
			fichero.guardarComo(concesionario, Teclado.leerCadena("Introduce el nombre de fichero que desea guardar"));
		} catch (FileNotFoundException e) {
			System.err.println("Error al guardar");
		} catch (IOException e) {
			System.err.println("Error al guardar");
		}
	}

	private static void guardar() {
		if (fichero == null) {
			guardarComo();
		}

		else
			try {
				fichero.guardar(concesionario,  Teclado.leerCadena("Introduce el nombre del fichero"));
			} catch (FileNotFoundException e) {
				System.err.println("Error al guardar el fichero");
			} catch (IOException e) {
				System.err.println("Error al guardar el fichero");
			}
	}

	private static void abrir() {
		try {
			if (modificado.isModificado()) {
				respuesta();
				if (respuesta())// == true) {
					fichero.guardar(concesionario, Teclado.leerCadena("Introduce el nombre del fichero"));
				//}
			}
			fichero.abrir( Teclado.leerCadena("Introduce el nombre del fichero"));
		} catch (FileNotFoundException e) {
			System.err.println("Error al abrir el fichero ");
		} catch (ClassNotFoundException e) {
			System.err.println("Error al abrir el fichero ");
		} catch (IOException e) {
			System.err.println("Error al abrir el fichero ");
		}

	}

	public static boolean respuesta() {
		char respuesta;
		respuesta = Teclado.leerCaracter("Introduce 's' O 'S', si desea guardar y 'n' o 'N' en caso contrario");
		if (respuesta == 's' || respuesta == 'S')
			return true;
		else
			return false;
	}

	private static void nuevo() {
		if (modificado.isModificado()) {
			respuesta();
			if (respuesta() == true) {
				try {
					fichero.guardar(concesionario,Teclado.leerCadena("Introduce el nombre del fichero"));
				} catch (FileNotFoundException e) {
					System.err.println("Error al guardar el fichero");
				} catch (IOException e) {
					System.err.println("Error al guardar el fichero");

				}
			}
		}
		Concesionario concesionario = new Concesionario();

	}

	private static void buscarCocheColores() {
		try {
			System.out.println(concesionario.buscarCocheColor(pedirColor()));
		} catch (NoExisteCocheColor e) {
			System.err.println(e.getMessage());
		}
	}

	private static void getCoche() {

		try {
			System.out.println(concesionario.getCoche(pedirMatricula()));
		} catch (MatriculaNoValidaException | IndiceErroneoException e) {

			System.err.println(e.getMessage());
		}

	}

	private static void baja() {

		try {
			if (concesionario.baja(pedirMatricula()))
				System.out.println("El coche se ha eliminado correctamente");
			else
				System.err.println("El coche no se ha podido eliminar. La matricula no existe");
		} catch (MatriculaNoValidaException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void alta() {

		try {
			concesionario.alta(pedirMatricula(), pedirColor(), pedirModelo());
		} catch (colorNullException | modeloNullException | MatriculaNoValidaException | cocheIdenticoException e) {
			System.err.println(e.getMessage());
		}

	}

	private static Modelo pedirModelo() {
		Menu menuModelo = new Menu("Elige un modelo", Modelo.toArray());

		switch (menuModelo.gestionar()) {
		case 1:
			return Modelo.CORDOBA;
		case 2:
			return Modelo.TOLEDO;
		case 3:
			return Modelo.IBIZA;
		case 4:
			return Modelo.SERIE;
		case 5:
			return Modelo.SERIE2;
		default:
			return Modelo.SERIE5;

		}

	}

	private static Color pedirColor() {

		Menu menuColor = new Menu("Elige un color para el coche", Color.toArray());

		switch (menuColor.gestionar()) {
		case 1:
			return Color.PLATA;
		case 2:
			return Color.ROJO;
		default:
			return Color.AZUL;

		}

	}

	private static String pedirMatricula() {
		return Teclado.leerCadena("Introduzca una matricula");

	}
}
