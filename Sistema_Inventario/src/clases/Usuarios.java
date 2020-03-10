package clases;

import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import mysql.consultas;

public class Usuarios {
	int idusuario;
	String usuario;
	String password;
	String nombre;
	int tipo;
	ResultSet rs;
	consultas consulta = new consultas();

	public Usuarios(){}
	public Usuarios(int idusuario, String usuario, String password, String nombre, int tipo){
		this.idusuario = idusuario;
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public void cargarUsuarios(JComboBox<Usuarios> cbUsuarios){
		consulta.iniciar();
		rs = consulta.cargarUsuarios();
		try {
			while(rs.next())
				cbUsuarios.addItem(
						new Usuarios(
								rs.getInt("idusuario"),
								rs.getString("usuario"),
								rs.getString("pass"),
								rs.getString("nombre"),
								rs.getInt("tipo")
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
		return nombre;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
	
}

