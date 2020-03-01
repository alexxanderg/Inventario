package clases;

import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import mysql.consultas;

public class Distribuidores {
	ResultSet rs;

	int	iddistrib;
	String tipodoc;
	String nrodoc;
	String nombre;
	String direccion;
	String contacto;
	String telefono;
	String correo;
	
	public Distribuidores(){}           
	public Distribuidores(int iddistrib, String tipodoc, String nrodoc, String nombre, String direccion, String contacto,String telefono, String correo){
		this.iddistrib = iddistrib;
		this.tipodoc = tipodoc;
		this.nrodoc = nrodoc;
		this.nombre = nombre;
		this.direccion = direccion;
		this.contacto = contacto;
		this.telefono = telefono;
		this.correo = correo;
	}
	
	public void cargarDistribuidores(JComboBox<Distribuidores> cbDistribuidor){
		consultas consult = new consultas();
		rs = consult.cargarDistribuidores();
		try {
			while(rs.next())
				cbDistribuidor.addItem(
						new Distribuidores(
								rs.getInt("iddistrib"),
								rs.getString("tipodoc"),
								rs.getString("nrodoc"),
								rs.getString("nombre"),
								rs.getString("direccion"),
								rs.getString("perscontact"),
								rs.getString("telefono"),
								rs.getString("correo")
								)
				);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}		
	}
	@Override
	public String toString(){
		return nombre;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public int getIddist() {
		return iddistrib;
	}
	public void setIddist(int iddist) {
		this.iddistrib = iddist;
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
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
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
	
	
	
}
