package clases;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import mysql.consultas;

public class Categoria {
	String categoria;
	ResultSet rs;

	public Categoria(){}
	public Categoria(String categoria){
		this.categoria = categoria;
	}
	
	public void cargarCategorias(JComboBox<Categoria> cbCategoria){
		consultas consult = new consultas();
		rs = consult.cargarCategoria();
		try {
			while(rs.next())
				cbCategoria.addItem(
						new Categoria(
								rs.getString("categoria")
								)
				);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}		
	}
	
	@Override
	public String toString(){
		return categoria;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
		
}

