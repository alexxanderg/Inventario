package clases;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import mysql.consultas;

public class Almacen {
	String almacen;
	ResultSet rs;

	public Almacen(){}
	public Almacen(String almacen){
		this.almacen = almacen;
	}
	
	public void cargarAlmacenes(JComboBox<Almacen> cbAlmacen){
		consultas consult = new consultas();
		rs = consult.cargarAlmacen();
		try {
			while(rs.next())
				cbAlmacen.addItem(
						new Almacen(
								rs.getString("almacen")
								)
				);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}		
	}
	
	@Override
	public String toString(){
		return almacen;
	}
	public String getAlmacen() {
		return almacen;
	}
	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}		
}

