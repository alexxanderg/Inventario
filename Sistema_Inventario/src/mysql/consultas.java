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

	// public int registrarUsuarioIngreso(String usu){
	// Connection con = MySQLConexion.getConection();
	// java.sql.Statement st;
	// ResultSet rs = null;
	//
	// java.util.Date date = new Date();
	// //Object date2 = new java.sql.Timestamp(date.getTime());
	//
	// try {
	// st = con.createStatement();
	// String sql = "insert into
	// tb_registro_ingreso(fecha_registro,usuario)values(now(),?)";
	// PreparedStatement prepareStmt = con.prepareStatement(sql);
	// prepareStmt.setString(1, usu);
	// prepareStmt.execute();
	// } catch (Exception e) {
	// JOptionPane.showMessageDialog(null, "ERROR al añadir stock: " + e);
	// }
	// return 0;
	// }

	public ResultSet registrarUsuarioIngreso(String usu) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st = null;
		java.sql.Statement st1 = null;
		ResultSet rs = null;
		try {
			st1 = con.createStatement();
			rs = st1.executeQuery("select * from db_inventario.tb_registro_ingreso where usuario='" + usu
					+ "' and day(fecha_registro)=day(now())");
			if (rs.next() == true) {
				// JOptionPane.showMessageDialog(null, "Ya creado");
			} else {
				st = con.createStatement();
				String sql = "insert into tb_registro_ingreso(fecha_registro,usuario)values(now(),?)";
				PreparedStatement prepareStmt = con.prepareStatement(sql);
				prepareStmt.setString(1, usu);
				prepareStmt.execute();
				// JOptionPane.showMessageDialog(null, "USUARIO CREADO
				// CORRECTAMENTE");
			}
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Error");
		}
		return rs;
	}

	public ResultSet cargarProductos() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from db_inventario.tb_productos order by producto");
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
			rs = st.executeQuery("select distinct categoria from db_inventario.tb_productos order by categoria");
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

	public int ingresarProducto(String cod, String prod, String det, String cat, String lab, java.sql.Date fec_venc, String nrolote,
			String umed, float cant, float prec, float prev, String promo1, float cpromo1, float ppromo1, String promo2,
			float cpromo2, float ppromo2, String marca, String color) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_productos (codproducto, producto, detalles,categoria,laboratorio,fechaVenc, nrolote, unimedida, cantidad, precioCo,  precioVe, promo1, cantp1, prep1, promo2, cantp2, prep2,marca,color)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, cod);
			prepareStmt.setString(2, prod);
			prepareStmt.setString(3, det);
			prepareStmt.setString(4, cat);
			prepareStmt.setString(5, lab);
			prepareStmt.setDate(6, fec_venc);
			prepareStmt.setFloat(7, Float.parseFloat(nrolote));
			prepareStmt.setString(8, umed);
			prepareStmt.setFloat(9, cant);
			prepareStmt.setFloat(10, prec);
			prepareStmt.setFloat(11, prev);
			prepareStmt.setString(12, promo1);
			prepareStmt.setFloat(13, cpromo1);
			prepareStmt.setFloat(14, ppromo1);
			prepareStmt.setString(15, promo2);
			prepareStmt.setFloat(16, cpromo2);
			prepareStmt.setFloat(17, ppromo2);
			prepareStmt.setString(18, marca);
			prepareStmt.setString(19, color);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "AGREGADO CORRECTAMENTE");
			return 0;// 0= se creo correctamente
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "ERROR: " + e);
			return 1; // 1= encontró producto con mismo codigo
		}

	}

	public ResultSet modificarProducto(String cod, String newcod, String prod, String det, String cat, String laboratorio,
			java.sql.Date fecVen, String nroLote, String umed, float cant, float prec, float prev, String promo1,
			float cpromo1, float ppromo1, String promo2, float cpromo2, float ppromo2, String marca, String color) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_productos set codproducto = ?, producto=?, detalles=?, categoria=?, laboratorio = ?,fechaVenc=?, nrolote=?, unimedida=?, cantidad=?, precioCo=?, precioVe=?, promo1=?, cantp1=?, prep1=?, promo2=?, cantp2=?, prep2=?,marca=?,color=? where codproducto=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, newcod);
			prepareStmt.setString(2, prod);
			prepareStmt.setString(3, det);
			prepareStmt.setString(4, cat);
			prepareStmt.setString(5, laboratorio);
			prepareStmt.setDate(6, fecVen);
			prepareStmt.setFloat(7, Float.parseFloat(nroLote));
			prepareStmt.setString(8, umed);
			prepareStmt.setFloat(9, cant);
			prepareStmt.setFloat(10, prec);
			prepareStmt.setFloat(11, prev);
			prepareStmt.setString(12, promo1);
			prepareStmt.setFloat(13, cpromo1);
			prepareStmt.setFloat(14, ppromo1);
			prepareStmt.setString(15, promo2);
			prepareStmt.setFloat(16, cpromo2);
			prepareStmt.setFloat(17, ppromo2);
			prepareStmt.setString(18, marca);
			prepareStmt.setString(19, color);
			prepareStmt.setString(20, cod);
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

	public int registrarFechaIngreso(String cod, float cant, float precioCo, float precioVe, String usuario) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		// java.util.Date date = new Date();
		// Object date2 = new java.sql.Timestamp(date.getTime());

		java.util.Date date = new Date();
		Object date2 = new java.sql.Timestamp(date.getTime());

		try {
			st = con.createStatement();
			String sql = "insert into tb_ingreso_productos (codproducto,cantidad,precioCo,precioVe,fechaingreso,nombreusu)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, cod);
			prepareStmt.setFloat(2, cant);
			prepareStmt.setFloat(3, precioCo);
			prepareStmt.setFloat(4, precioVe);
			prepareStmt.setObject(5, date2);
			prepareStmt.setString(6, usuario);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
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
			rs = st.executeQuery("select * from tb_usuarios");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet ingresarUsuario(String usu, String pass, String nom, int tipo) {
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

	public ResultSet modificarUsuario(String usu, String newusu, String pass, String nom, int tip) {
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
}
