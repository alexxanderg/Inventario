package mysql;

import java.awt.HeadlessException;
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
	public Usuarios obtenerUsuario(Usuarios u) {
		Usuarios usuario = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = null;
			con = MySQLConexion.getConection();
			String sql = "select * from tb_usuarios where usuario = BINARY ? and pass = BINARY ? ";
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

	public ResultSet cargarProductos() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_productos  where estado = 1 order by producto");
		} catch (Exception e) {
		}
		return rs;
	}
	//
	public ResultSet cargarCategoria() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select distinct categoria from tb_productos order by categoria");
		} catch (Exception e) {
		}
		return rs;
	}
	public ResultSet cargarAlmacen() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select distinct almacen from tb_productos order by almacen");
		} catch (Exception e) {
		}
		return rs;
	}
	public ResultSet cargarUnidadesMed() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select distinct unimedida from tb_productos order by unimedida");
		} catch (Exception e) {
		}
		return rs;
	}
	

	public ResultSet cargarProductosSinStock() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from db_inventario.tb_productos where cantidad > 0 order by producto");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet buscarProducto(String Prod) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from tb_productos where codproducto = '" + Prod + "' or producto = '" + Prod + "'");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet buscarProductoDetalle(String prod, String det) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from db_inventario.tb_productos where producto like '" + prod
					+ "' and detalles like '" + det + "';");
		} catch (Exception e) {
		}
		return rs;
	}

	public int ingresarProducto(String codbarra, String nombreprod, String descripcion, String umedida, String categoria, String almacen,
			String marca, String color, double stockini, double stockmin, double preco, double ptjgana, double preve, java.sql.Date fec_venc, String laboratiorio,
			String lote, String nombrePromo1, double cantPromo1, double prePromo1, String nombrePromo2, double cantPromo2, double prePromo2, int primeravez) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_productos (codproducto, codbarra, producto, detalles, marca, color, lote, laboratorio, unimedida, fechaVenc, categoria, almacen, cantidad, cantmin, precioCo, precioVe, ptjganancia, estado, promo1, cantp1, prep1, promo2, cantp2, prep2)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setString(2, codbarra);
			prepareStmt.setString(3, nombreprod);
			prepareStmt.setString(4, descripcion);
			prepareStmt.setString(5, marca);
			prepareStmt.setString(6, color);
			prepareStmt.setString(7, lote);
			prepareStmt.setString(8, laboratiorio);
			prepareStmt.setString(9, umedida);
			prepareStmt.setDate(10, fec_venc);
			prepareStmt.setString(11, categoria);
			prepareStmt.setString(12, almacen);
			prepareStmt.setDouble(13, stockini);
			prepareStmt.setDouble(14, stockmin);
			prepareStmt.setDouble(15, preco);
			prepareStmt.setDouble(16, preve);
			prepareStmt.setDouble(17, ptjgana);
			prepareStmt.setInt(18, 1);
			prepareStmt.setString(19, nombrePromo1);
			prepareStmt.setDouble(20, cantPromo1);
			prepareStmt.setDouble(21, prePromo1);
			prepareStmt.setString(22, nombrePromo2);
			prepareStmt.setDouble(23, cantPromo2);
			prepareStmt.setDouble(24, prePromo2);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "AGREGADO CORRECTAMENTE");
			return 0;// 0= se creo correctamente
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return 1; // 1= encontró producto con mismo codigo
		}
	}
	public int ingresarProductoPrimeraVez(String codbarra, String nombreprod, String descripcion, String umedida, String categoria, String almacen,
			String marca, String color, double stockini, double stockmin, double preco, double ptjgana, double preve, java.sql.Date fec_venc, String laboratiorio,
			String lote, String nombrePromo1, double cantPromo1, double prePromo1, String nombrePromo2, double cantPromo2, double prePromo2, int primeravez) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_productos (codproducto, codbarra, producto, detalles, marca, color, lote, laboratorio, unimedida, fechaVenc, categoria, almacen, cantidad, cantmin, precioCo, precioVe, ptjganancia, estado, promo1, cantp1, prep1, promo2, cantp2, prep2)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setString(2, codbarra);
			prepareStmt.setString(3, nombreprod);
			prepareStmt.setString(4, descripcion);
			prepareStmt.setString(5, marca);
			prepareStmt.setString(6, color);
			prepareStmt.setString(7, lote);
			prepareStmt.setString(8, laboratiorio);
			prepareStmt.setString(9, umedida);
			prepareStmt.setDate(10, fec_venc);
			prepareStmt.setString(11, categoria);
			prepareStmt.setString(12, almacen);
			prepareStmt.setDouble(13, stockini);
			prepareStmt.setDouble(14, stockmin);
			prepareStmt.setDouble(15, preco);
			prepareStmt.setDouble(16, preve);
			prepareStmt.setDouble(17, ptjgana);
			prepareStmt.setInt(18, 0);
			prepareStmt.setString(19, nombrePromo1);
			prepareStmt.setDouble(20, cantPromo1);
			prepareStmt.setDouble(21, prePromo1);
			prepareStmt.setString(22, nombrePromo2);
			prepareStmt.setDouble(23, cantPromo2);
			prepareStmt.setDouble(24, prePromo2);
			prepareStmt.execute();
			return 0;// 0= se creo correctamente
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return 1; // 1= encontró producto con mismo codigo
		}
	}

	public ResultSet modificarProducto(String codbarra, String nombreprod, String descripcion, String umedida, String categoria, String almacen,
			String marca, String color, double stockini, double stockmin, double preco, double ptjgana, double preve, java.sql.Date fec_venc, String laboratiorio,
			String lote, String nombrePromo1, double cantPromo1, double prePromo1, String nombrePromo2, double cantPromo2, double prePromo2,int cod) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			//String sql = "update tb_productos set codproducto = ?, producto=?, detalles=?, categoria=?, laboratorio = ?,fechaVenc=?, nrolote=?, unimedida=?, cantidad=?, precioCo=?, precioVe=?, promo1=?, cantp1=?, prep1=?, promo2=?, cantp2=?, prep2=?,marca=?,color=? where codproducto=?";
			String sql = "update tb_productos set codbarra =?, producto=?, detalles=?, marca=?, color=?, lote=?, laboratorio=?, unimedida=?, fechaVenc=?, categoria=?, almacen=?, cantidad=?, cantmin=?, precioCo=?, precioVe=?, ptjganancia=?, promo1=?, cantp1=?, prep1=?, promo2=?, cantp2=?, prep2=? where codproducto=?";

			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, codbarra);
			prepareStmt.setString(2, nombreprod);
			prepareStmt.setString(3, descripcion);
			prepareStmt.setString(4, marca);
			prepareStmt.setString(5, color);
			prepareStmt.setString(6, lote);
			prepareStmt.setString(7, laboratiorio);
			prepareStmt.setString(8, umedida);
			prepareStmt.setDate(9, fec_venc);
			prepareStmt.setString(10, categoria);
			prepareStmt.setString(11, almacen);
			prepareStmt.setDouble(12, stockini);
			prepareStmt.setDouble(13, stockmin);
			prepareStmt.setDouble(14, preco);
			prepareStmt.setDouble(15, preve);
			prepareStmt.setDouble(16, ptjgana);
			prepareStmt.setString(17, nombrePromo1);
			prepareStmt.setDouble(18, cantPromo1);
			prepareStmt.setDouble(19, prePromo1);
			prepareStmt.setString(20, nombrePromo2);
			prepareStmt.setDouble(21, cantPromo2);
			prepareStmt.setDouble(22, prePromo2);
			prepareStmt.setInt(23, cod);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, " PRODUCTO MODIFICADO CORRECTAMENTE ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	

	public ResultSet modificarPC_PV(String cod, float prec, float prev) {
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

	public ResultSet ingresarStock(String cod, float cant) {
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

	public int registrarFechaIngreso(int id, double stockini, double precioCoOld, double precioVeOld, double precioCoNew, double precioVeNew, String nombreusu, Object fActual) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		
		try {
			st = con.createStatement();
			String sql = "insert into tb_ingreso_productos (coding, codproducto, cantidad, precioCoOld, precioVeOld, precioCoNew, precioVeNew, nombreusu, fechaingreso)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setInt(2, id);
			prepareStmt.setDouble(3, stockini);
			prepareStmt.setDouble(4, precioCoOld);
			prepareStmt.setDouble(5, precioVeOld);
			prepareStmt.setDouble(6, precioCoNew);
			prepareStmt.setDouble(7, precioVeNew);
			prepareStmt.setString(8, nombreusu);
			prepareStmt.setObject(9, fActual);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "Registrado correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar Fecha ingreso: " + e);
		}
		return 0;
	}

	public ResultSet eliminarProducto(String cod, String nom) {
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

	public ResultSet eliminarProductoDetalle(String cod, String nom) {
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

	public ResultSet eliminarProductoIngreso(String cod, String nom) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "delete from tb_ingreso_productos where codproducto = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, cod);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}

	public ResultSet cargarHistorialKardex() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_kardex order by idkardex desc");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet cargarUsuarios() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_usuarios where estado=1 order by nombre");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet crearUsuario(String usu, String pass, String nom, int tipo) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_usuarios (idusuario, usuario, pass, nombre, tipo, estado)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setString(2, usu);
			prepareStmt.setString(3, pass);
			prepareStmt.setString(4, nom);
			prepareStmt.setInt(5, tipo);
			prepareStmt.setInt(6, 1); //ACTIVO
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "USUARIO CREADO CORRECTAMENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al crear usuario: " + e);
		}
		return rs;
	}

	public ResultSet modificarUsuario(int idusuario, String newusu, String pass, String nom, int tip) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_usuarios set usuario=?, pass=?, nombre=?, tipo=? where idusuario=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, newusu);
			prepareStmt.setString(2, pass);
			prepareStmt.setString(3, nom);
			prepareStmt.setInt(4, tip);
			prepareStmt.setInt(5, idusuario);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}

	public ResultSet eliminarUsuario(String usu) {
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
	
	public ResultSet cargarDistribuidores() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_distribuidores where estado=1 order by nombre");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet cargarDistribuidoresId(int id) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from tb_distribuidores where iddistrib="+id);
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet crearDistribuidor(String tipodoc, String nrodoc, String nombre, String direccion, String telefono, String contacto, String correo) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_distribuidores (iddistrib, tipodoc, nrodoc, nombre, direccion, perscontact, telefono, correo, estado)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setString(2, tipodoc);
			prepareStmt.setString(3, nrodoc);
			prepareStmt.setString(4, nombre);
			prepareStmt.setString(5, direccion);
			prepareStmt.setString(6, contacto);
			prepareStmt.setString(7, telefono);
			prepareStmt.setString(8, correo);
			prepareStmt.setInt(9, 1); //ACTIVO
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "DISTRIBUIDOR CREADO CORRECTAMENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al crear usuario: " + e);
		}
		return rs;
	}
	
	public ResultSet modificarDistribuidor(int iddistribuidor, String tipodoc, String nrodoc, String nombre, String direccion, String telefono, String contacto, String correo) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_distribuidores set tipodoc=?, nrodoc=?, nombre=?, direccion=?, perscontact=?, telefono=?, correo=? where iddistrib=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, tipodoc);
			prepareStmt.setString(2, nrodoc);
			prepareStmt.setString(3, nombre);
			prepareStmt.setString(4, direccion);
			prepareStmt.setString(5, contacto);
			prepareStmt.setString(6, telefono);
			prepareStmt.setString(7, correo);
			prepareStmt.setInt(8, iddistribuidor);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "Distribuidor modificado correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}

	public ResultSet buscarCliente(String id) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_clientes where id = '" + id + "'");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet cargarClientes() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_clientes order by nombre");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet cargarUltimoCliente() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_clientes order by id desc limit 1");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet cargarUltimoDistribuidor() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_distribuidores order by iddistrib desc limit 1");
		} catch (Exception e) {
		}
		return rs;
	}

	public int crearCliente(String nombre, String documento, String nroDocumento, String direccion, String correo,
			String telefono) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_clientes (id, tipodoc, nrodoc, nombre, direccion, telefono, correo)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setString(2, documento);
			prepareStmt.setString(3, nroDocumento);
			prepareStmt.setString(4, nombre);
			prepareStmt.setString(5, direccion);
			prepareStmt.setString(6, telefono);
			prepareStmt.setString(7, correo);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "CLIENTE CREADO CORRECTAMENTE");
			return 0;// 0= se creo correctamente
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: CLIENTE EXISTENTE");
			return 1;// 1= NO se creo correctamente
		}
	}

	public ResultSet modificarCliente(int id, String nombre, String documento, String nroDocumento, String direccion,
			String correo, String telefono) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_clientes set tipodoc=?, nrodoc=?, nombre=?, direccion=?, telefono=?, correo=? where id=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, documento);
			prepareStmt.setString(2, nroDocumento);
			prepareStmt.setString(3, nombre);
			prepareStmt.setString(4, direccion);
			prepareStmt.setString(5, telefono);
			prepareStmt.setString(6, correo);
			prepareStmt.setInt(7, id);
			prepareStmt.execute();

			JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}

	public ResultSet eliminarCliente(String usu) {
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

	public void registrarKardex(Object date2, String nota) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_kardex (idkardex, fecha, nota)" + " values (?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setObject(2, date2);
			prepareStmt.setString(3, nota);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "KARDEX REGISTRADO CORRECTAMENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: KARDEX EXISTENTE");
		}
	}

	public ResultSet ObtenerUltimoKardex() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select idkardex from tb_kardex order by idkardex desc limit 1");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet ObtenerNombreProducto(String cod) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select producto from tb_productos where codproducto like '" + cod + "%' limit 1");
		} catch (Exception e) {
		}
		return rs;
	}

	public void registrarDetallesKardex(int idkardex, String codigoProducto, int registros) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_kardex_detalles (idkardex, codproducto, registros)" + " values (?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, idkardex);
			prepareStmt.setString(2, codigoProducto);
			prepareStmt.setInt(3, registros);
			prepareStmt.execute();
			// JOptionPane.showMessageDialog(null, "DETALLE DE KARDEX REGISTRADO
			// CORRECTAMENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR EN DETALLE DE  KARDEX " + e);
		}
	}

	public ResultSet Vender(String cliente, String usuario, double totCompra, double totVenta, double ganancia,
			int idcliente, String nota, int metpago) {
		Connection con = MySQLConexion.getConection();
		
		java.sql.Statement st;
		java.util.Date d = new java.util.Date();
		// java.sql.Date date2 = new java.sql.Date(d.getTime());
		java.util.Date date = new Date();
		Object date2 = new java.sql.Timestamp(date.getTime());

		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_ventas (codventa, cliente, fecha, usuario, totcompra, totventa, ganancia, idcliente, nota, metpago)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setString(2, cliente);
			prepareStmt.setObject(3, date2);
			prepareStmt.setString(4, usuario);
			prepareStmt.setDouble(5, totCompra);
			prepareStmt.setDouble(6, totVenta);
			prepareStmt.setDouble(7, ganancia);
			prepareStmt.setDouble(8, idcliente);
			prepareStmt.setString(9, nota);
			prepareStmt.setInt(10, metpago);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}

	public ResultSet ObtenerUltimoCodigo() {
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

	public ResultSet RegistarDetalleVenta(int codVenta, String codProducto, float cantidad, double preUnidadOriginal,
			double preTotalUnidadOriginal, double preUnidadFinal, double preTotalUnidadFinal) {

		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			// codVenta, codProducto, cantventa, preUnidadOriginal,
			// preTotalUnidadOriginal, preUnidadFinal, preTotalUnidadFinal);
			String sql = "insert into tb_ventas_detalle (codventa, codproducto, cantidad, prevenOri, totvenOri, prevenFin, totvenFin)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			// JOptionPane.showMessageDialog(null, cantidad);
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

	public ResultSet RealizarDescuentoStock(String codProducto, float cantVenta) {
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

	public ResultSet VerificarVenta(int numVenta) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_ventas where codventa = '" + numVenta + "'");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	public ResultSet modificarInformacion(String info, int id){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_ventas set nota=? where codventa=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, info);
			prepareStmt.setInt(2, id);
			prepareStmt.execute();

			JOptionPane.showMessageDialog(null, "Información Alterada");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}

	public ResultSet ProductosVendidos(int numVenta) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_ventas_detalle where codventa = '" + numVenta + "'");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}

	public void eliminarVentaDetalle(String cod_prod, String cod_vd) {
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

	public void eliminarVenta(String cod_vd) {
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

	public void reingresarProductos(String codProducto, float cantidad) {
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

	public ResultSet ultimoProducto() {
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
	
	public ResultSet cargarAtributosProd() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_configuraciones");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet modificarAtributosProductos(String atributos) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_configuraciones set atributosprod = ? where idconfig = 1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, atributos);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "Atributos corregidos correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar atributos: " + e);
		}
		return rs;
	}
	
	public ResultSet cargarID() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select codproducto from tb_productos order by codproducto desc limit 1");
		} catch (Exception e) {
		}
		return rs;
	}
	public ResultSet cargarProdId(String Prod) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from tb_productos where codproducto ="+Prod);
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet deshabilitarProducto(int cod) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			//String sql = "update tb_productos set codproducto = ?, producto=?, detalles=?, categoria=?, laboratorio = ?,fechaVenc=?, nrolote=?, unimedida=?, cantidad=?, precioCo=?, precioVe=?, promo1=?, cantp1=?, prep1=?, promo2=?, cantp2=?, prep2=?,marca=?,color=? where codproducto=?";
			String sql = "update tb_productos set estado = 1 where codproducto=?";

			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, cod);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, " PRODUCTO ELIMINADO");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	public ResultSet habilitarProducto(int cod) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			//String sql = "update tb_productos set codproducto = ?, producto=?, detalles=?, categoria=?, laboratorio = ?,fechaVenc=?, nrolote=?, unimedida=?, cantidad=?, precioCo=?, precioVe=?, promo1=?, cantp1=?, prep1=?, promo2=?, cantp2=?, prep2=?,marca=?,color=? where codproducto=?";
			String sql = "update tb_productos set estado = 0 where codproducto=?";

			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, cod);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, " PRODUCTO HABILITADO ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al eliminar producto: " + e);
		}
		return rs;
	}
	
	public ResultSet cargarUsu(int idusu) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from tb_usuarios where idusuario ='"+idusu+"'");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet deshabilitarUsuario(int usu) {
		Connection con = MySQLConexion.getConection();
		ResultSet rs = null;
		try {
			String sql = "update tb_usuarios set estado = 0 where idusuario='"+usu+"'";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, " USUARIO ELIMINADO ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al eliminar usuario: " + e);
		}
		return rs;
	}
	public ResultSet deshabilitarDistrib(int distrib) {
		Connection con = MySQLConexion.getConection();
		ResultSet rs = null;
		try {
			String sql = "update tb_distribuidores set estado = 0 where iddistrib='"+distrib+"'";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, " DISTRIBUIDOR ELIMINADO ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al eliminar DISTRIBUIDOR: " + e);
		}
		return rs;
	}
}
