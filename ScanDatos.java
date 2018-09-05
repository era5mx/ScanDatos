/**
 * Clase para realizar el scan de un directorio
 * y contar las lineas de los archivos .txt, .TXT, .log o .LOG contenidos en este
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Lee el directorio indicado y obtiene la lista de archivos luego cuenta las
 * lineas de los archivos .txt o .TXT y finalmente presenta el total de
 * registros procesados
 *
 * @author <a href="mailto:david@rengifo.mx">david.rengifo</a>
 */
public class ScanDatos {
	
	/**
	 * El path absoluto donde estan los archivos, se debe escapar con \\ y no
	 * colocar \\ al final
	 */
	//private static final String DATOS_DIR = "{AQUI_VA_EL_PATH_DONDE_ESTAN_LOS_ARCHIVOS}";
	private static final String DATOS_DIR = "C:\\Users\\alexander\\Downloads\\icons";
	
	/** Logger */
	private static Logger logger = Logger.getLogger(ScanDatos.class.getName());

	/**
	 * Constructor
	 */
	public ScanDatos() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		logger.info("Ejecutando ScanDatos sobre el directorio [" + DATOS_DIR + "]\n");
		
		File dir = new File(DATOS_DIR);
		String[] ficheros = dir.list();
		long totalReg = 0;
		long ignoreFiles = 0;
		if (ficheros == null) {
			logger.info("No se encontro el directorio [" + DATOS_DIR + "], o no hay archivos en el");
		} else {
			logger.info("Hay " + ficheros.length + " archivos en el directorio [" + DATOS_DIR + "] \n");
			for (int i = 0; i < ficheros.length; i++) {
				String nameFile = ficheros[i];
				if (nameFile.endsWith("txt") || nameFile.endsWith("TXT")
						|| nameFile.endsWith(".log")
						|| nameFile.endsWith(".LOG")) {
					logger.info("El archivo [" + nameFile);
					long linesFile = contarLineas(nameFile);
					if (linesFile > 0) {
						totalReg = totalReg + linesFile;
						System.out.println("] tiene " + linesFile + " lineas");
						logger.info("] tiene " + linesFile + " lineas");
					} else {
						System.out.println("] esta vacio.");
						logger.info("] esta vacio.");
					}
				} else {
					ignoreFiles++;
				}
			}
			if (totalReg > 0) {
				logger.info("Se procesaron " + totalReg + " registros en total.\n");
			} else {
				logger.info("No se procesaron registros.\n");
			}
			if (ignoreFiles > 0) {
				logger.info("Se ignoraron " + ignoreFiles + " archivos del directorio [" + DATOS_DIR + "].");
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
			printLog(e);
		} catch (IOException e) {
			printLog(e);
		} finally {
			try {
				if (br != null) { br.close(); }
				if (fr != null) { fr.close(); }
			} catch (IOException e) {
				printLog(e);
			}
		}
		return lNumeroLineas;
	}
	
	/** Print log */
	private static void printLog(Exception e) {
		logger.severe(e.getClass().getTypeName());
		logger.severe(e.getMessage());
		logger.severe(e.getCause().getMessage());
	}
	
}
