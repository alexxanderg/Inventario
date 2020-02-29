package clases;

import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import mysql.consultas;

public class Distribuidores {
	ResultSet rs;
	String dist;

	public Distribuidores(){
	}
	
	public Distribuidores(String dist){
		this.dist = dist;
	}
	
	public void cargarDistribuidores(JComboBox<Distribuidores> cbDistribuidor){
		consultas consult = new consultas();
		rs = consult.cargarDistribuidores();
		try {
			while(rs.next())
				cbDistribuidor.addItem(
						new Distribuidores(
								rs.getString("nombre")
								)
				);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}		
	}
	@Override
	public String toString(){
		return dist;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}
	
}
