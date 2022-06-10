package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import mysql.consultas;

public class Cliente {
	int	id;
	String tipodoc;
	String nrodoc;
	String nombre;
	String direccion;
	String telefono;
	String correo;
	ResultSet rs;
	consultas consulta = new consultas();
	
	public Cliente(){}        
	
	public Cliente(String cliente){
		this.nombre = cliente;
	}
	
	public Cliente(int id, String tipodoc, String nrodoc, String nombre, String direccion, String telefono, String correo){
		this.id = id;
		this.tipodoc = tipodoc;
		this.nrodoc = nrodoc;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
	}
	
	public void cargarClientes(JComboBox<Cliente> cbClientes){
		consulta.iniciar();
		rs = consulta.cargarClientes();
		try {
			while(rs.next())
				cbClientes.addItem(
						new Cliente(
								rs.getInt("idcliente"),
								rs.getString("tipodoc"),
								rs.getString("nrodoc"),
								rs.getString("nombre"),
								rs.getString("direccion"),
								rs.getString("telefono"),
								rs.getString("correo")
								)
				);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		finally {
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipodoc() {
		return tipodoc;
	}
	public void setTipodoc(String tipodoc) {
		this.tipodoc = tipodoc;
	}
	public String getNrodoc() {
		return nrodoc;
	}
	public void setNrodoc(String nrodoc) {
		this.nrodoc = nrodoc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
}
