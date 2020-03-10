package clases;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import mysql.consultas;

public class Categoria {
	String categoria;
	ResultSet rs;
	consultas consulta = new consultas();
	
	public Categoria(){}
	
	public Categoria(String categoria){
		this.categoria = categoria;
	}
	
	public void cargarCategorias(JComboBox<Categoria> cbCategoria){
		consulta.iniciar();
		rs = consulta.cargarCategoria();
		try {
			while(rs.next())
				cbCategoria.addItem(
						new Categoria(
								rs.getString("categoria")
								)
				);
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

