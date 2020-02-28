package clases;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import mysql.consultas;

public class UnidadMed {
	String unimed;
	ResultSet rs;

	public UnidadMed(){}
	public UnidadMed(String unimed){
		this.unimed = unimed;
	}
	
	public void cargarUnidadesMed(JComboBox<UnidadMed> cbUnidadMedida){
		consultas consult = new consultas();
		rs = consult.cargarUnidadesMed();
		try {
			while(rs.next())
				cbUnidadMedida.addItem(
						new UnidadMed(
								rs.getString("unimedida")
								)
				);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}		
	}
	
	@Override
	public String toString(){
		return unimed;
	}
	public String getAlmacen() {
		return unimed;
	}
	public void setAlmacen(String almacen) {
		this.unimed = almacen;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}		
}

