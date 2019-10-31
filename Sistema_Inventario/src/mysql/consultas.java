package mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Statement;
import clases.RegistroVentas;
import clases.Usuarios;
import mysql.MySQLConexion;

public class consultas {
	public Usuarios obtenerUsuario(Usuarios u){
		Usuarios usuario = null;		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = null;
			con = MySQLConexion.getConection();
			String sql = "select * from tb_usuarios where usuario = ? and pass = ? ";
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getUsuario());
			pst.setString(2, u.getPassword());
			rs = pst.executeQuery();
			while (rs.next()) {
				usuario = new Usuarios(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}			
		} catch (Exception e) {
			System.out.println("Error en obtener usuario");
		}
		return usuario;
	}
	
	public ResultSet cargarProductos(){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_productos order by producto");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet buscarProducto(String Prod){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_productos where codproducto = '" + Prod + "' or producto = '" + Prod + "'");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet buscarProductoDetalle(String prod, String det){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_productos where producto = '" + prod + "' and detalles = '" + det + "'" );
		} catch (Exception e) {
		}
		return rs;
	}
	
	public int ingresarProducto(String cod, String prod, String det, String umed, float cant, float prec, float prev){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_productos (codproducto, producto, detalles, unimedida, cantidad, precioCo,  precioVe)" + " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, cod);
			prepareStmt.setString(2, prod);
			prepareStmt.setString(3, det);
			prepareStmt.setString(4, umed);
			prepareStmt.setFloat(5, cant);
			prepareStmt.setFloat(6, prec);
			prepareStmt.setFloat(7, prev);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "AGREGADO CORRECTAMENTE");
			return 0;//0= se creo correctamente
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "ERROR: " + e);
			return 1; //1= encontró producto con mismo codigo 
		}
		
	}
	
	public ResultSet modificarProducto(String cod, String newcod, String prod, String det, String umed, float cant, float prec, float prev){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_productos set codproducto = ?, producto=?, detalles=?, unimedida=?, cantidad=?, precioCo=?, precioVe=? where codproducto=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, newcod);
			prepareStmt.setString(2, prod);
			prepareStmt.setString(3, det);
			prepareStmt.setString(4, umed);
			prepareStmt.setFloat(5, cant);
			prepareStmt.setFloat(6, prec);
			prepareStmt.setFloat(7, prev);
			prepareStmt.setString(8, cod);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, " PRODUCTO MODIFICADO CORRECTAMENTE ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	public ResultSet modificarPC_PV(String cod, float prec, float prev){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_productos set precioCo=?, precioVe=? where codproducto=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setFloat(1, prec);
			prepareStmt.setFloat(2, prev);
			prepareStmt.setString(3, cod);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	
	public ResultSet ingresarStock(String cod, float cant){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_productos set cantidad = ? where codproducto=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setFloat(1, cant);
			prepareStmt.setString(2, cod);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, " AGREGADO CORRECTAMENTE ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	public int registrarFechaIngreso(String cod, float cant,float precioCo,float precioVe,Object date2,String usuario){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		
		java.util.Date date = new Date();
		//Object date2 = new java.sql.Timestamp(date.getTime());
		
		try {
			st = con.createStatement();
			String sql = "insert into tb_ingreso_productos (codproducto,cantidad,precioCo,precioVe,fechaingreso,nombreusu)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, cod);
			prepareStmt.setFloat(2, cant);
			prepareStmt.setFloat(3, precioCo);
			prepareStmt.setFloat(4, precioVe);
			prepareStmt.setObject(5, date2);
			prepareStmt.setString(6, usuario);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al añadir stock: " + e);
		}
		return 0;
	}
	
	
	public ResultSet eliminarProducto(String cod, String nom){ 
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "delete from tb_productos where codproducto = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, cod);		
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	public ResultSet eliminarProductoDetalle(String cod, String nom){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "delete from tb_ventas_detalle where codproducto = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, cod);		
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	public ResultSet cargarUsuarios(){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_usuarios");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet ingresarUsuario(String usu, String pass, String nom, int tipo){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_usuarios (usuario, pass, nombre, tipo)" + " values (?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, usu);
			prepareStmt.setString(2, pass);
			prepareStmt.setString(3, nom);
			prepareStmt.setInt(4, tipo);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "USUARIO CREADO CORRECTAMENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: USUARIO EXISTENTE");
		}
		return rs;
	}
	
	public ResultSet modificarUsuario(String usu, String newusu, String pass, String nom, int tip){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_usuarios set usuario=?, pass=?, nombre=?, tipo=? where usuario=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, newusu);
			prepareStmt.setString(2, pass);
			prepareStmt.setString(3, nom);
			prepareStmt.setInt(4, tip);
			prepareStmt.setString(5, usu);
			prepareStmt.execute();

			JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	public ResultSet eliminarUsuario(String usu){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "delete from tb_usuarios where usuario = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, usu);		
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	public ResultSet Vender(String cliente, String usuario, double totCompra, double totVenta, double ganancia){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		java.util.Date d = new java.util.Date();
		//java.sql.Date date2 = new java.sql.Date(d.getTime());
		java.util.Date date = new Date();
		Object date2 = new java.sql.Timestamp(date.getTime());
		
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_ventas (codventa, cliente, fecha, usuario, totcompra, totventa, ganancia)" + " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setString(2, cliente);
			prepareStmt.setObject(3, date2);
			prepareStmt.setString(4, usuario);
			prepareStmt.setDouble(5, totCompra);
			prepareStmt.setDouble(6, totVenta);
			prepareStmt.setDouble(7, ganancia);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}		
		return rs;		
	}
	
	public ResultSet ObtenerUltimoCodigo(){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select codventa from tb_ventas order by codventa desc limit 1");
		} catch (Exception e) {
		}
		return rs;
	}
	
	
	public ResultSet RegistarDetalleVenta(int codVenta, String codProducto, float cantidad, double preUnidadOriginal, double preTotalUnidadOriginal, double preUnidadFinal, double preTotalUnidadFinal){
		
		
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			//codVenta, codProducto, cantventa, preUnidadOriginal, preTotalUnidadOriginal, preUnidadFinal, preTotalUnidadFinal);
			String sql = "insert into tb_ventas_detalle (codventa, codproducto, cantidad, prevenOri, totvenOri, prevenFin, totvenFin)" + " values (?, ?, ?, ?, ?, ?, ?)";
			//JOptionPane.showMessageDialog(null, cantidad);
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, codVenta);
			prepareStmt.setString(2, codProducto);
			prepareStmt.setFloat(3, cantidad);
			prepareStmt.setDouble(4, preUnidadOriginal);
			prepareStmt.setDouble(5, preTotalUnidadOriginal);
			prepareStmt.setDouble(6, preUnidadFinal);
			prepareStmt.setDouble(7, preTotalUnidadFinal);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	public ResultSet RealizarDescuentoStock(String codProducto, float cantVenta){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_productos set cantidad=cantidad-? where codProducto=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setFloat(1, cantVenta);
			prepareStmt.setString(2, codProducto);
			prepareStmt.execute();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	public ResultSet VerificarVenta(int numVenta){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_ventas where codventa = '" + numVenta+ "'");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	public ResultSet ProductosVendidos(int numVenta){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_ventas_detalle where codventa = '" + numVenta+ "'");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;		
	}
	
	public void eliminarVentaDetalle(String cod_prod, String cod_vd){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		try {
			st = con.createStatement();
			String sql = "update tb_ventas_detalle set cantidad=0, prevenOri=0, totvenOri=0, prevenFin=0, totvenFin=0 where codproducto = ? and codventa = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, cod_prod);
			prepareStmt.setString(2, cod_vd);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}
	
	public void eliminarVenta(String cod_vd){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		try {
			st = con.createStatement();
			String sql = "update tb_ventas set totcompra=0, totventa=0, ganancia=0 where codventa = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, cod_vd);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}
	
	public void reingresarProductos(String codProducto, float cantidad){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		try {
			st = con.createStatement();
			String sql = "update tb_productos set cantidad=cantidad + ? where codproducto=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setFloat(1, cantidad);
			prepareStmt.setString(2, codProducto);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}	
	
	public ResultSet ultimoProducto(){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_productos");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error model");
		}
		return rs;
	}
}
