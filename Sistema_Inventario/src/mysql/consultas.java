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
			String sql = "select * from tb_usuarios where usuario = BINARY ? and pass = BINARY ? and estado = 1";
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getUsuario());
			pst.setString(2, u.getPassword());
			rs = pst.executeQuery();
			while (rs.next()) {
				usuario = new Usuarios(rs.getInt("idusuario"), rs.getString("usuario"), rs.getString("pass"), rs.getString("nombre"), rs.getInt("tipo"));
			}
		} catch (Exception e) {
			System.out.println("Error en obtener usuario " + e);
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
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar productos: " + e);
		}
		return rs;
	}
	public ResultSet cargarVentasUsuario() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select v.codventa, c.nombre ncliente, u.nombre nusuario, v.nota, DATE_FORMAT(v.fecha,'%d-%m-%Y %h:%m') as fecha, v.descuento, v.saldo, v.totventa from tb_ventas v inner join tb_clientes c on c.idcliente = v.idcliente inner join tb_usuarios u on u.idusuario = v.idusuario where v.estado = 1 order by v.fecha desc;");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar productos: " + e);
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

	public ResultSet buscarProductoBarras(String codbarra) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from tb_productos where codbarra = '" + codbarra + "'");
		} catch (Exception e) {
		}
		return rs;
	}
	public ResultSet buscarDistribuidor(int iddistrib) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from tb_distribuidores where iddistrib = " + iddistrib);
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
	
	public ResultSet buscarProductoID(int idprod) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_productos where codproducto = '" + idprod + "' and estado = 1 ");
		} catch (Exception e) {
		}
		return rs;
	}

	public int ingresarProducto(String codbarra, String nombreprod, String descripcion, String umedida, String categoria, String almacen, int iddistrib,
			String marca, String color, double stockini, double stockmin, double preco, double ptjgana, double preve, java.sql.Date fec_venc, String laboratiorio,
			String lote, String nombrePromo1, double cantPromo1, double prePromo1, String nombrePromo2, double cantPromo2, double prePromo2, int primeravez) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_productos (codproducto, codbarra, producto, detalles, marca, color, lote, laboratorio, unimedida, fechaVenc, categoria, almacen, iddistrib, cantidad, cantmin, precioCo, precioVe, ptjganancia, estado, promo1, cantp1, prep1, promo2, cantp2, prep2)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			prepareStmt.setInt(13, iddistrib);
			prepareStmt.setDouble(14, stockini);
			prepareStmt.setDouble(15, stockmin);
			prepareStmt.setDouble(16, preco);
			prepareStmt.setDouble(17, preve);
			prepareStmt.setDouble(18, ptjgana);
			prepareStmt.setInt(19, 1);
			prepareStmt.setString(20, nombrePromo1);
			prepareStmt.setDouble(21, cantPromo1);
			prepareStmt.setDouble(22, prePromo1);
			prepareStmt.setString(23, nombrePromo2);
			prepareStmt.setDouble(24, cantPromo2);
			prepareStmt.setDouble(25, prePromo2);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "AGREGADO CORRECTAMENTE");
			return 0;// 0= se creo correctamente
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return 1; // 1= encontr� producto con mismo codigo
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

	public int registrarIngreso(int id, double stockini, double precioCoOld, double precioVeOld, double precioCoNew, double precioVeNew, String nombreusu, Object fActual) {
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
	
	public ResultSet cargarCompras() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select cp.idcompra, cp.serie, cp.nroSerie, d.nombre, cp.nota, cp.fechaEmision, cp.fechaVencimiento, cp.tot, cp.saldo from  tb_compras cp inner join tb_distribuidores d on cp.idDistrib = d.iddistrib order by cp.idcompra desc");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar compras: " + e);
		}
		return rs;
	}

	public int registrarCompra(int tipComprobante, String serie, String nroSerie, int idDistrib, String moneda, String tc, String nota, String metPago, Object fechaEmision, Object fechaVencimiento, int idusuario,
			double total, double pagado, double saldo) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		
		try {
			st = con.createStatement();
			String sql = "insert into tb_compras (idcompra, tipComprobante, serie, nroSerie, idDistrib, moneda, tc, nota, metPago, fechaEmision, fechaVencimiento, idusuario, tot, pagado, 	saldo)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setInt(2, tipComprobante);
			prepareStmt.setString(3, serie);
			prepareStmt.setString(4, nroSerie);
			prepareStmt.setInt(5, idDistrib);
			prepareStmt.setString(6, moneda);
			prepareStmt.setString(7, tc);
			prepareStmt.setString(8, nota);
			prepareStmt.setString(9, metPago);
			prepareStmt.setObject(10, fechaEmision);
			prepareStmt.setObject(11, fechaVencimiento);
			prepareStmt.setInt(12, idusuario);
			prepareStmt.setDouble(13, total);
			prepareStmt.setDouble(14, pagado);
			prepareStmt.setDouble(15, saldo);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "Registrado correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar compra: " + e);
		}
		return 0;
	}
	public int registrarCompraDetalles(int idCompra, int idProd, double cantProd, double preIndivProd, double preSubTotProd) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		
		try {
			st = con.createStatement();
			String sql = "insert into tb_compras_detalles (idcompra, idprod, cantidad, preUni, preSubT)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, idCompra);
			prepareStmt.setInt(2, idProd);
			prepareStmt.setDouble(3, cantProd);
			prepareStmt.setDouble(4, preIndivProd);
			prepareStmt.setDouble(5, preSubTotProd);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "Registrado correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar compra detalles: " + e);
		}
		return 0;
	}
	public ResultSet anadirStockProducto(int idProd, double cantProd) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_productos SET cantidad = cantidad+? where codproducto = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setDouble(1, cantProd);
			prepareStmt.setInt(2, idProd);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "Sumado correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
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
			rs = st.executeQuery("select * from tb_clientes where idCLIENTE = '" + id + "'");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet cargarClienteId(int id) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from tb_clientes where idcliente="+id);
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
			rs = st.executeQuery("select * from tb_clientes where estado = 1 order by nombre");
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
			rs = st.executeQuery("select * from tb_clientes order by idcliente desc limit 1");
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

	public ResultSet crearCliente(String nombre, String documento, String nroDocumento, String direccion, String correo,
			String telefono) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_clientes (idcliente, tipodoc, nrodoc, nombre, direccion, telefono, correo, estado)"
					+ " values (?, ?, ?, ?, ?, ?, ?, 1)";
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
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: CLIENTE EXISTENTE " + e);
		}
		return rs;
	}

	public ResultSet modificarCliente(int id, String nombre, String documento, String nroDocumento, String direccion,
			String correo, String telefono) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_clientes set tipodoc=?, nrodoc=?, nombre=?, direccion=?, telefono=?, correo=? where idcliente=?";
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
	
	public ResultSet deshabilitarCliente(int idcliente) {
		Connection con = MySQLConexion.getConection();
		ResultSet rs = null;
		try {
			String sql = "update tb_clientes set estado = 0 where idcliente='"+idcliente+"'";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, " CLIENTE ELIMINADO ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al eliminar DISTRIBUIDOR: " + e);
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
	
	public ResultSet Vender(int idcliente, int idusuario, double pretotC, double preTotalVentaFinal, double gananciaFinal, double descTotal, String nota, int metpago1, double monto1, int metpago2, double monto2) {
		Connection con = MySQLConexion.getConection();
		
		java.sql.Statement st;
		java.util.Date d = new java.util.Date();
		// java.sql.Date date2 = new java.sql.Date(d.getTime());
		java.util.Date date = new Date();
		Object date2 = new java.sql.Timestamp(date.getTime());

		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_ventas (codventa, idcliente, fecha, idusuario, totcompra, totventa, ganancia, descuento, nota, metpago1, montpago1, metpago2, montpago2, estado)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setInt(2, idcliente);
			prepareStmt.setObject(3, date2);
			prepareStmt.setInt(4, idusuario);
			prepareStmt.setDouble(5, pretotC);
			prepareStmt.setDouble(6, preTotalVentaFinal);
			prepareStmt.setDouble(7, gananciaFinal);
			prepareStmt.setDouble(8, descTotal);
			prepareStmt.setString(9, nota);
			prepareStmt.setInt(10, metpago1);
			prepareStmt.setDouble(11, monto1);
			prepareStmt.setInt(12, metpago2);
			prepareStmt.setDouble(13, monto2);
			prepareStmt.setInt(14, 1);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar venta: " + e);
		}
		return rs;
	}
	public ResultSet Vender2(int idcliente, int idusuario, double pretotC, double preTotalVentaFinal, double gananciaFinal, double descTotal, String nota, int metpago1, double monto1, int metpago2, double monto2, Object fechaElegida) {
		Connection con = MySQLConexion.getConection();
		
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_ventas (codventa, idcliente, fecha, idusuario, totcompra, totventa, ganancia, descuento, nota, metpago1, montpago1, metpago2, montpago2, estado)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setInt(2, idcliente);
			prepareStmt.setObject(3, fechaElegida);
			prepareStmt.setInt(4, idusuario);
			prepareStmt.setDouble(5, pretotC);
			prepareStmt.setDouble(6, preTotalVentaFinal);
			prepareStmt.setDouble(7, gananciaFinal);
			prepareStmt.setDouble(8, descTotal);
			prepareStmt.setString(9, nota);
			prepareStmt.setInt(10, metpago1);
			prepareStmt.setDouble(11, monto1);
			prepareStmt.setInt(12, metpago2);
			prepareStmt.setDouble(13, monto2);
			prepareStmt.setInt(14, 1);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar venta: " + e);
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
	
	public ResultSet ObtenerUltimoCodigoCompra() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select idcompra from tb_compras order by idcompra desc limit 1");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet RegistarDetalleVenta(int codventa, int codproducto, double cantidad, double preVeSDInd,
			double preVeSDTot, double descIndiv, double descTotal, double subTotal, double ganancia, String uMedidaUsada) {
		
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "insert into tb_ventas_detalle (codventa, codproducto, cantidad, preVeSDInd, preVeSDTot, descIndiv, descTotal, subTotal, ganancia, uMedidaUsada)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// JOptionPane.showMessageDialog(null, cantidad);
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, codventa);
			prepareStmt.setInt(2, codproducto);
			prepareStmt.setDouble(3, cantidad);
			prepareStmt.setDouble(4, preVeSDInd);
			prepareStmt.setDouble(5, preVeSDTot);
			prepareStmt.setDouble(6, descIndiv);
			prepareStmt.setDouble(7, descTotal);
			prepareStmt.setDouble(8, subTotal);
			prepareStmt.setDouble(9, ganancia);
			prepareStmt.setString(10, uMedidaUsada);
			
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al registrar detalle de venta: " + e);
		}
		return rs;
	}

	public ResultSet RealizarDescuentoStock(int codProducto, double cantVenta) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_productos set cantidad=cantidad-? where codProducto=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setDouble(1, cantVenta);
			prepareStmt.setInt(2, codProducto);
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
	
	public ResultSet verificarVentaSinStock() {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select ventasinstock from tb_configuraciones");
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

			JOptionPane.showMessageDialog(null, "Informaci�n Alterada");
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
	
	public ResultSet cargarConfiguraciones() {
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
	
	public void modificarAtributosProductos(String atributos) {
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
	}
	public void modificarVentaSinStock(int eleccion) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_configuraciones set ventasinstock = ? where idconfig = 1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, eleccion);
			prepareStmt.execute();
			if(eleccion == 1)
				JOptionPane.showMessageDialog(null, "CAMBIO REALIZADO: Ahora puede vender sin restricci�n del stock");
			if(eleccion == 0)
				JOptionPane.showMessageDialog(null, "CAMBIO REALIZADO: Ahora no puede vender stock menor a 0");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar ventasinstock: " + e);
		}
	}
	public void modificarReducirAlVender(int eleccion) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_configuraciones set reducirstock = ? where idconfig = 1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, eleccion);
			prepareStmt.execute();
			if(eleccion == 1)
				JOptionPane.showMessageDialog(null, "CAMBIO REALIZADO: Ahora, cuando venda, si se descontar� de su stock");
			if(eleccion == 0)
				JOptionPane.showMessageDialog(null, "CAMBIO REALIZADO: Ahora puede vender sin que su stock se vea afectado");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar reducirstock: " + e);
		}
	}
	public void modificarFechaAlVender(int eleccion) {
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "update tb_configuraciones set fechaVauto = ? where idconfig = 1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, eleccion);
			prepareStmt.execute();
			if(eleccion == 1)
				JOptionPane.showMessageDialog(null, "CAMBIO REALIZADO: Ahora, cuando venda, podr� escoger con que fecha registrarla");
			if(eleccion == 0)
				JOptionPane.showMessageDialog(null, "CAMBIO REALIZADO: Ahora, cuando venda, se registrar� con la fecha actual");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar reducirstock: " + e);
		}
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
			String sql = "update tb_productos set estado = 0 where codproducto=?";

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
