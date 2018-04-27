package concecionarioDeCoches;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Fichero {

	private static String nombreFichero;

	public Fichero(String nombreFichero) {
		setNombreFichero(nombreFichero);
	}

	public static void setNombreFichero(String nombreFichero) {
		nombreFichero = nombreFichero;

	}

	public static void guardar(Concesionario concesionario, String nombreFichero)
			throws FileNotFoundException, IOException {
		try (ObjectOutputStream fichero = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(nombreFichero)))) {
			fichero.writeObject(concesionario);
			setNombreFichero(nombreFichero);
		}
	}

	public static Concesionario abrir(String nombreFichero)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream fichero = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(nombreFichero)))) {
			Concesionario concesionario = (Concesionario) fichero.readObject();
			setNombreFichero(nombreFichero);
			return concesionario;
		}
	}

	public void guardarComo(Concesionario concesionario, String ficheroNombre)
			throws FileNotFoundException, IOException {
		setNombreFichero(ficheroNombre);
		guardar(concesionario, ficheroNombre);
	}

}