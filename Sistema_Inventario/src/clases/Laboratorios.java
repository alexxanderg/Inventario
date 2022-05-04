package clases;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import mysql.consultas;

public class Laboratorios {
	String laboratorios;
	ResultSet rs;
	consultas consulta = new consultas();
	
	public Laboratorios(){}
	
	public Laboratorios(String laboratorios){
		this.laboratorios = laboratorios;
	}
	
	public void cargarLaboratorios(JComboBox<Laboratorios> cbLaboratorios){
		consulta.iniciar();
		rs = consulta.cargarLaboratorios();
		try {
			while(rs.next())
				try {
					if(rs.getString("laboratorio").equals("") || rs.getString("laboratorio").equals(" ")){}
					else{
						cbLaboratorios.addItem(
								new Laboratorios(
										rs.getString("laboratorio")
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
		return laboratorios;
	}

	public String getLaboratorios() {
		return laboratorios;
	}

	public void setLaboratorios(String laboratorios) {
		this.laboratorios = laboratorios;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
}
