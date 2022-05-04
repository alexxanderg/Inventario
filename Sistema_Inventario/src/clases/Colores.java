package clases;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import mysql.consultas;

public class Colores{
	String colores;
	ResultSet rs;
	consultas consulta = new consultas();
	
	public Colores(){}
	
	public Colores(String colores){
		this.colores = colores;
	}
	
	public void cargarColores(JComboBox<Colores> cbColores){
		consulta.iniciar();
		rs = consulta.cargarColores();
		try {
			while(rs.next())
				try {
					if(rs.getString("color").equals("") || rs.getString("color").equals(" ")){}
					else{
						cbColores.addItem(
								new Colores(
										rs.getString("color")
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
		return colores;
	}

	public String getColores() {
		return colores;
	}

	public void setColores(String color) {
		this.colores = color;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
}
