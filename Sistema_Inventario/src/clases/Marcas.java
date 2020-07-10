package clases;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import mysql.consultas;

public class Marcas {
	String marcas;
	ResultSet rs;
	consultas consulta = new consultas();
	
	public Marcas(){}
	
	public Marcas(String marcas){
		this.marcas = marcas;
	}
	
	public void cargarMarcas(JComboBox<Marcas> cbMarcas){
		consulta.iniciar();
		rs = consulta.cargarMarcas();
		try {
			while(rs.next())
				try {
					if(rs.getString("marca").equals("") || rs.getString("marca").equals(" ")){}
					else{
						cbMarcas.addItem(
								new Marcas(
										rs.getString("marca")
										)
						);
					}
				} catch (Exception e) {
				}
				
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}	finally {
			try {
				if (rs != null)
					rs.close();
				if (consulta != null)
					consulta.reset();
            } catch (Exception ex) {
            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
            }
		}	
	}
	
	@Override
	public String toString(){
		return marcas;
	}

	public String getMarcas() {
		return marcas;
	}

	public void setMarcas(String marcas) {
		this.marcas = marcas;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
}
