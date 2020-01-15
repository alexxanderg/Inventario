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
	/* Pryebaskasdlñasdcasasdfasdf*/
	String direccion;
	String telefono;
	String correo;
	ResultSet rs;
	
	public Cliente(){}           
	public Cliente(int id, String tipodoc, String nrodoc, String nombre, String direccion, String telefono, String correo){
		this.id = id;
		this.tipodoc = tipodoc;
		this.nrodoc = nrodoc;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
	}
	
	public void cargarEmpresas(JComboBox<Cliente> cbEmpresa){
		consultas consult = new consultas();
		rs = consult.cargarClientes();
		try {
			while(rs.next())
				cbEmpresa.addItem(
						new Cliente(
								rs.getInt("id"),
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
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
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