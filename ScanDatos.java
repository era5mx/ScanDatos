/**
 * Clase para realizar el scan de un directorio
 * y contar las lineas de los archivos .txt, .TXT contenidos en este
 */
package net.puenteg.extend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lee el directorio indicado y obtiene la lista de archivos luego cuenta las
 * lineas de los archivos .txt o .TXT y finalmente presenta el total de
 * registros procesados
 *
 * @author david.rengifo
 */
public class ScanDatos {
	/**
	 * El path absoluto donde estan los archivos, se debe escapar con \\ y no
	 * colocar \\ al final
	 */
	private static final String DATOS_DIR = "{AQUI_VA_EL_PATH_DONDE_ESTAN_LOS_ARCHIVOS}";

	/**
	 * Constructor
	 */
	public ScanDatos() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File dir = new File(DATOS_DIR);
		String[] ficheros = dir.list();
		long totalReg = 0;
		long ignoreFiles = 0;
		if (ficheros == null)
			System.out.println("No hay ficheros en el directorio especificado");
		else {
			System.out.println("Hay " + ficheros.length
					+ " archivos en el directorio Datos\n");
			for (int i = 0; i < ficheros.length; i++) {
				String nameFile = ficheros[i];
				if (nameFile.endsWith("txt") || nameFile.endsWith("TXT")
						|| nameFile.endsWith(".log")
						|| nameFile.endsWith(".LOG")) {
					System.out.print("El archivo [" + nameFile);
					long linesFile = contarLineas(nameFile);
					if (linesFile > 0) {
						totalReg = totalReg + linesFile;
						System.out.println("] tiene " + linesFile + " lineas");
					} else {
						System.out.println("] esta vacio.");
					}
				} else {
					ignoreFiles++;
				}
			}
			if (totalReg > 0) {
				System.out.println("\nSe procesaron " + totalReg
						+ " registros en total.");
			} else {
				System.out.println("\nNo se procesaron registros.");
			}
			if (ignoreFiles > 0) {
				System.out.println("\nSe ignoraron " + ignoreFiles
						+ " archivos del directorio Datos.");
			}
		}
	}

	/**
	 * Abre el archivo indicado y cuenta las lineas que contiene
	 *
	 * @param string
	 *            - Nombre del archivo
	 * @return long
	 */
	private static long contarLineas(String string) {
		File archivo = new File(DATOS_DIR.concat("\\").concat(string));
		FileReader fr = null;
		BufferedReader br = null;
		long lNumeroLineas = 0;
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			while ((br.readLine()) != null) {
				lNumeroLineas++;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getCause());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getCause());
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				System.out.println(e.getCause());
				e.printStackTrace();
			}
		}
		return lNumeroLineas;
	}
}
