package gui_configuracion;

import java.io.InputStream;

/**
 * Esta clase muestra en la consola los procesos (errores o no)
 * @author Garcia
 * @version 19/5/2021
 * @see <a href="http://chuwiki.chuidiang.org/index.php?title=Backup_de_MySQL_con_Java"/> Más información a cerca de esta clase
 *
 */
public class HiloLector extends Thread {
	//Campos de la clase
	private InputStream entrada;
	
	/**
	 * Constructor para leer los procesos
	 * @param entrada El parametro "entrada" contiene el Process para leerlo 
	 */
	public HiloLector(InputStream entrada) {
		this.entrada = entrada;
	}

	@Override
	public void run() {
		try {
			byte[] buffer = new byte[1000];
			int leido = entrada.read(buffer);
			while (leido > 0) {
				String texto = new String(buffer, 0, leido);
				System.out.print(texto);
				leido = entrada.read(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}