package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilitarios {
	// ----------------------------------------------
	// funcion convierte texto en fecha "dia/mes/año"
	public static Date textoAFecha(String sFecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyy-MM-dd");
		// String sFecha ="2019-04-05";
		Date fecha = null;
		try {
			fecha = formato.parse(sFecha);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return fecha;

	}

}
