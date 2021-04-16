package mysql;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import clases.Usuarios;
import gui_productos.Inventario;
import mysql.MySQLConexion;

public class consultas {
	
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	consultas consultas = null;
	
	public void iniciar(){
		try {
			con = MySQLConexion.getConection();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al iniciar conexion");
		}
	}
	 
	
	public void reset(){
		try {
			if(con!=null)con.close();
			if(st!=null)st.close();
			if(rs!=null)rs.close();
			if(pst!=null)pst.close();
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "Error al resetear conexion");
		}
	}
	
	public static String ObtenerFechaHora(){
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String fechahora = hourdateFormat.format(date);
		return fechahora;
	}
	
	public static void RegistrarError(String errorMsj) {
		File directorio=new File("C:\\LogErrores_SistemaInventario"); 
		directorio.mkdirs(); 
		String nombreArchivo= "C:\\LogErrores_SistemaInventario\\log.txt";
		
		BufferedWriter bw = null;
	    FileWriter fw = null;
        File file = new File(nombreArchivo);
        try {
	        if (!file.exists())
				file.createNewFile();
	        fw = new FileWriter(file.getAbsoluteFile(), true);
	        bw = new BufferedWriter(fw);
	        bw.write(errorMsj);
        } catch (IOException e) {
			//JOptionPane.showMessageDialog(null, "Error al registrar error1: " + ObtenerFechaHora() + " " + e.getMessage());
		}
        finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
            	//JOptionPane.showMessageDialog(null, "Error al registrar error2: " + ObtenerFechaHora() + " " + ex.getMessage());
            }
        }
	}	
	
	public Usuarios obtenerUsuario(Usuarios u) {
		Usuarios usuario = null;
		Connection con = null;
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
			RegistrarError("\n Error en obtener usuario: " + ObtenerFechaHora() + " " + e);
		}
		return usuario;
	}

	public ResultSet cargarProductos() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_productos where estado = 1 order by producto");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar productos: " + e);
		}
		return rs;
	}
	
	public ResultSet cargarProductosCodBarra() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select DISTINCT codbarra from tb_productos where estado = 1");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar productos BARRA: " + e);
		}
		return rs;
	}
	public ResultSet cargarProductosProd() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select DISTINCT producto from tb_productos where estado = 1");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar productos: " + e);
		}
		return rs;
	}
	public ResultSet cargarProductosDeta() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select DISTINCT detalles from tb_productos where estado = 1");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar productos: " + e);
		}
		return rs;
	}
	public ResultSet cargarProductosMarca() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select DISTINCT marca from tb_productos where estado = 1");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar productos: " + e);
		}
		return rs;
	}
	public ResultSet cargarProductosColor() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select DISTINCT color from tb_productos where estado = 1");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar productos: " + e);
		}
		return rs;
	}
	public ResultSet cargarProductosLab() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select DISTINCT laboratorio from tb_productos where estado = 1");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar productos: " + e);
		}
		return rs;
	}
	
	public ResultSet cargarProductoParticular(String prod) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_productos where codproducto like '%" + prod + "%' or codbarra like '%" + prod + "%' or producto like '%" + prod + "%' or detalles like '%" + prod + "%' or marca like '%" + prod + "%' or color like '%" + prod + "%' or categoria like '%" + prod + "%' order by producto");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar productos: " + e);
		}
		return rs;
	}
	
	public ResultSet cargarVenta(int codVenta){
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_ventas where codventa = " + codVenta);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar venta: " + e);
		}
		return rs;
	}
	public ResultSet cargarVentaDetalles(int nroVentaModificar){
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_ventas_detalle where codventa = " + nroVentaModificar);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar venta detalles: " + e);
		}
		return rs;
	}
	public ResultSet cargarVentaDetallesProducto(int codVenta){
		try {
			st = con.createStatement();
			rs = st.executeQuery("select vd.cantidad, p.producto, p.detalles, p.marca, p.color, vd.preVeSDInd, vd.preVeSDTot, vd.descIndiv, vd.descTotal, vd.subTotal, vd.subTotal, vd.ganancia, vd.uMedidaUsada from tb_ventas_detalle vd inner join tb_productos p on p.codproducto = vd.codproducto where codventa = " + codVenta);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar venta detalles: " + e);
		}
		return rs;
	}
	
	public ResultSet cargarVentasUsuarioTodos(Object fechai, Object fechaf) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select v.codventa, c.nombre ncliente, u.nombre nusuario, v.nota, DATE_FORMAT(v.fecha,'%d-%m-%Y %h:%i %p') as fecha, v.descuento, v.saldo, v.totventa from tb_ventas v inner join tb_clientes c on c.idcliente = v.idcliente inner join tb_usuarios u on u.idusuario = v.idusuario where  v.fecha between '" + fechai + "' and '" + fechaf + "' order by v.fecha desc;");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar productos: " + e);
		}
		return rs;
	}
	public ResultSet cargarVentasUsuario(int idusuario, Object fechai, Object fechaf) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select v.codventa, c.nombre ncliente, u.nombre nusuario, v.nota, DATE_FORMAT(v.fecha, '%d-%m-%Y %h:%i %p') as fecha, v.descuento, v.saldo, v.totventa from tb_ventas v inner join tb_clientes c on c.idcliente = v.idcliente inner join tb_usuarios u on u.idusuario = v.idusuario where  u.idusuario = " + idusuario + " and v.fecha between '" + fechai + "' and '" + fechaf + "' order by v.fecha desc;");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar productos: " + e);
		}
		return rs;
	}
	
	public ResultSet cargarCategoria() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select distinct categoria from tb_productos order by categoria");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet cargarMarcas() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select distinct marca from tb_productos order by marca");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet cargarAlmacen() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select distinct almacen from tb_productos order by almacen");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet cargarUnidadesMed() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select distinct unimedida from tb_productos order by unimedida");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet cargarProductosSinStock() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from db_inventario.tb_productos where cantidad > 0 order by producto");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet buscarProductoBarras(String codbarra) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_productos where codbarra like '" + codbarra + "'  and length(codbarra)>2 and estado = 1");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al buscar codbarra: " + e);
		}
		return rs;
	}
	
	public ResultSet buscarProductoBarrasyCod(String codbarra, int id) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_productos where codbarra like '" + codbarra + "'  and length(codbarra)>2 and estado = 1 and codproducto != " + id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al buscar codbarra: " + e);
		}
		return rs;
	}
	
	public ResultSet buscarDistribuidor(int iddistrib) {
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from tb_distribuidores where iddistrib = " + iddistrib);
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet buscarProductoDetalle(String prod, String det) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from db_inventario.tb_productos where producto like '" + prod
					+ "' and detalles like '" + det + "';");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet buscarProductoID(int idprod) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select "
								+ "codproducto,"
								+ "codbarra,"
								+ "producto,"
								+ "detalles,"
								+ "marca,"
								+ "color,"
								+ "lote,"
								+ "laboratorio,"
								+ "unimedida,"
								+ "fechaVenc,"
								+ "categoria,"
								+ "almacen,"
								+ "iddistrib,"
								+ "cantidad,"
								+ "cantmin,"
								+ "precioCo,"
								+ "precioVe,"
								+ "ptjganancia,"
								+ "estado,"
								+ "promo1,"
								+ "cantp1,"
								+ "prep1,"
								+ "promo2,"
								+ "cantp2,"
								+ "prep2"
								+ " from tb_productos where codproducto = '" + idprod + "' and estado = 1 ");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet buscarProductosPorVencer() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM db_inventario.tb_productos WHERE estado = 1 and fechaVenc != '' ORDER BY fechaVenc");
		} catch (Exception e) {
		}
		return rs;
	}

	public int ingresarProducto(String codbarra, String nombreprod, String descripcion, String umedida, String categoria, String almacen, int iddistrib,
			String marca, String color, double stockini, double stockmin, double preco, double ptjgana, double preve, java.sql.Date fec_venc, String laboratiorio,
			String lote, String nombrePromo1, double cantPromo1, double prePromo1, String nombrePromo2, double cantPromo2, double prePromo2, int primeravez) {
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
			return 1; // 1= encontró producto con mismo codigo
		}
	}
	
	public ResultSet modificarProducto(String codbarra, String nombreprod, String descripcion, String umedida, String categoria, String almacen, int iddistrib,
			String marca, String color, double stockini, double stockmin, double preco, double ptjgana, double preve, java.sql.Date fec_venc, String laboratiorio,
			String lote, String nombrePromo1, double cantPromo1, double prePromo1, String nombrePromo2, double cantPromo2, double prePromo2,int cod) {
		try {
			st = con.createStatement();
			//String sql = "update tb_productos set codproducto = ?, producto=?, detalles=?, categoria=?, laboratorio = ?,fechaVenc=?, nrolote=?, unimedida=?, cantidad=?, precioCo=?, precioVe=?, promo1=?, cantp1=?, prep1=?, promo2=?, cantp2=?, prep2=?,marca=?,color=? where codproducto=?";
			String sql = "update tb_productos set codbarra =?, producto=?, detalles=?, marca=?, color=?, lote=?, laboratorio=?, unimedida=?, fechaVenc=?, categoria=?, almacen=?, iddistrib=?, cantidad=?, cantmin=?, precioCo=?, precioVe=?, ptjganancia=?, promo1=?, cantp1=?, prep1=?, promo2=?, cantp2=?, prep2=? where codproducto=?";

			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, codbarra);
			prepareStmt.setString(2, nombreprod);
			prepareStmt.setString(3, descripcion);
			prepareStmt.setString(4, marca);
			prepareStmt.setString(5, color);
			prepareStmt.setString(6, lote);
			prepareStmt.setString(7, laboratiorio);
			prepareStmt.setString(8, umedida);
			prepareStmt.setDate(9, 	 fec_venc);
			prepareStmt.setString(10, categoria);
			prepareStmt.setString(11, almacen);
			prepareStmt.setInt(12, 	  iddistrib);
			prepareStmt.setDouble(13, stockini);
			prepareStmt.setDouble(14, stockmin);
			prepareStmt.setDouble(15, preco);
			prepareStmt.setDouble(16, preve);
			prepareStmt.setDouble(17, ptjgana);
			prepareStmt.setString(18, nombrePromo1);
			prepareStmt.setDouble(19, cantPromo1);
			prepareStmt.setDouble(20, prePromo1);
			prepareStmt.setString(21, nombrePromo2);
			prepareStmt.setDouble(22, cantPromo2);
			prepareStmt.setDouble(23, prePromo2);
			prepareStmt.setInt(24, cod);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, " PRODUCTO MODIFICADO CORRECTAMENTE ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}

	public ResultSet modificarPC_PV(int idProducto, float prec, java.sql.Date fec_venc, float prev) {
		try {
			st = con.createStatement();
			String sql = "update tb_productos set precioCo=?, precioVe=?, fechaVenc=? where codproducto=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setFloat(1, prec);
			prepareStmt.setFloat(2, prev);
			prepareStmt.setDate(3, fec_venc);
			prepareStmt.setInt(4, idProducto);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar precio compra venta: " + e);
		}
		return rs;
	}
	
	public int registrarFechaIngreso(int cod, float cant, float precioCoOld, float precioVeOld, float precioCoNew, float precioVeNew, Object date2, String usuario){
		Connection con = MySQLConexion.getConection();
		java.sql.Statement st;
		ResultSet rs = null;
		
		java.util.Date date = new Date();
		//Object date2 = new java.sql.Timestamp(date.getTime());
		
		try {
			st = con.createStatement();
			String sql = "insert into tb_ingreso_productos (codproducto, cantidad, precioCoOld, precioVeOld, precioCoNew, precioVeNew, nombreusu, fechaingreso)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, cod);
			prepareStmt.setFloat(2, cant);
			prepareStmt.setFloat(3, precioCoOld);
			prepareStmt.setFloat(4, precioVeOld);
			prepareStmt.setFloat(5, precioCoNew);
			prepareStmt.setFloat(6, precioVeNew);
			prepareStmt.setString(7, usuario);
			prepareStmt.setObject(8, date2);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al añadir stock: " + e);
		}
		return 0;
	}
	
	public void FusionarConteoKardex(int codproducto, float conteo){
		try {
			st = con.createStatement();
			String sql = "update tb_productos set cantidad=? where codproducto=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setFloat(1, conteo);
			prepareStmt.setInt(2, codproducto);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al fusionar: " + e);
		}
	}

	/*public ResultSet ingresarStock(String cod, float cant) {
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
	}*/
	
	public ResultSet ingresarStock(int cod, float cant) {
		
		try {
			st = con.createStatement();
			String sql = "update tb_productos set cantidad = ? where codproducto=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setFloat(1, cant);
			prepareStmt.setInt(2, cod);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, " AGREGADO CORRECTAMENTE ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al ingresar stock: " + e);
		}
		return rs;
	}

	public int registrarIngreso(int id, double stockini, double precioCoOld, double precioVeOld, double precioCoNew, double precioVeNew, String nombreusu, Object fActual) {		
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
	
	public ResultSet cargarCompras(Object fechai, Object fechaf) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select cp.idcompra, cp.serie, cp.nroSerie, d.nombre, cp.nota, cp.fechaEmision, cp.fechaVencimiento, cp.tot, cp.saldo from  tb_compras cp inner join tb_distribuidores d on cp.idDistrib = d.iddistrib where  cp.fechaEmision between '" + fechai + "' and '" + fechaf + "' order by cp.idcompra desc");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en consulta, al cargar compras: " + e);
		}
		return rs;
	}
	
	public ResultSet buscarCompraDetalle(int nroCompra) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select cd.cantidad, p.producto, p.detalles, p.marca, p.color, cd.preUni, cd.preSubT from tb_compras_detalles  cd inner join tb_productos p on p.codproducto = cd.idprod where idcompra = " + nroCompra);
		} catch (Exception e) {
		}
		return rs;
	}

	public int registrarCompra(int tipComprobante, String serie, String nroSerie, int idDistrib, String moneda, String tc, String nota, String metPago, Object fechaEmision, Object fechaVencimiento, int idusuario,
			double total, double pagado, double saldo) {		
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
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_kardex order by idkardex desc");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet cargarUsuarios() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_usuarios where estado=1 order by nombre");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet crearUsuario(String usu, String pass, String nom, int tipo) {
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
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_distribuidores where estado=1 order by nombre");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet cargarDistribuidoresId(int id) {
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from tb_distribuidores where iddistrib="+id);
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet crearDistribuidor(String tipodoc, String nrodoc, String nombre, String direccion, String telefono, String contacto, String correo) {
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
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_clientes where idCLIENTE = '" + id + "'");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet cargarClienteId(int id) {
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from tb_clientes where idcliente="+id);
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet cargarClientes() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_clientes where estado = 1 order by nombre");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet cargarUltimoCliente() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_clientes order by idcliente desc limit 1");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet cargarUltimoDistribuidor() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_distribuidores order by iddistrib desc limit 1");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet crearCliente(String nombre, String documento, String nroDocumento, String direccion, String correo, String telefono) {
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

	public ResultSet modificarCliente(int id, String nombre, String documento, String nroDocumento, String direccion, String correo, String telefono) {
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
		try {
			st = con.createStatement();
			String sql = "insert into tb_kardex (idkardex, fecha, nota)" + " values (?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setString(1, null);
			prepareStmt.setObject(2, date2);
			prepareStmt.setString(3, nota);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "KARDEX REGISTRADO CORRECTAMENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: KARDEX EXISTENTE " + e);
		}
	}

	public ResultSet ObtenerUltimoNroKardex() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select idkardex, nota from tb_kardex order by idkardex desc limit 1");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet cargarUltimoKardexYDetalle(int ultnrokardex) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select k.idkardex, k.fecha, k.nota, kd.codproducto, kd.registros from tb_kardex k inner join tb_kardex_detalles kd where k.idkardex = " + ultnrokardex + "  and kd.idkardex = " + ultnrokardex);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cosular ultimo kardex "  + e);
		}
		return rs;
	}


	public ResultSet ObtenerNombreProducto(String cod) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select producto from tb_productos where codproducto like '" + cod + "%' limit 1");
		} catch (Exception e) {
		}
		return rs;
	}

	public void registrarDetallesKardex(int idkardex, String codigoProducto, float registros) {
		try {
			st = con.createStatement();
			String sql = "insert into tb_kardex_detalles (idkardex, codproducto, registros)" + " values (?, ?, ?)";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, idkardex);
			prepareStmt.setString(2, codigoProducto);
			prepareStmt.setDouble(3, registros);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "DETALLE DE KARDEX REGISTRADO CORRECTAMENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR EN DETALLE DE  KARDEX " + e);
		}
	}
	
	public ResultSet Vender(int idcliente, int idusuario, double pretotC, double preTotalVentaFinal, double gananciaFinal, double descTotal, String nota, int metpago1, double monto1, int metpago2, double monto2) {
		Connection con = MySQLConexion.getConection();
		
		java.util.Date d = new java.util.Date();
		// java.sql.Date date2 = new java.sql.Date(d.getTime());
		java.util.Date date = new Date();
		Object date2 = new java.sql.Timestamp(date.getTime());
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
		try {
			st = con.createStatement();
			rs = st.executeQuery("select codventa from tb_ventas order by codventa desc limit 1");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet consultarEstadoVenta(int nroVenta) {
		try {
			st = con.createStatement();
			String sql = "select estado from tb_ventas where codventa=?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nroVenta);
			prepareStmt.execute();
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet ObtenerUltimoCodigoCompra() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select idcompra from tb_compras order by idcompra desc limit 1");
		} catch (Exception e) {
		}
		return rs;
	}

	public ResultSet RegistarDetalleVenta(int codventa, int codproducto, double cantidad, double preVeSDInd,
			double preVeSDTot, double descIndiv, double descTotal, double subTotal, double ganancia, String uMedidaUsada) {
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
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_ventas where codventa = '" + numVenta + "'");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	public ResultSet verificarVentaSinStock() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select ventasinstock from tb_configuraciones");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	public ResultSet modificarInformacion(String info, int id){
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
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_ventas_detalle where codventa = '" + numVenta + "'");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}

	public void resetearCeroVentaDetalle(int nroVentaEliminar) {
		try {
			st = con.createStatement();
			String sql = "update tb_ventas_detalle set preVeSDInd=0, preVeSDTot=0, descIndiv=0, descTotal=0, subTotal=0, ganancia=0 where codventa = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nroVentaEliminar);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}
	
	public void eliminarVentaDetalle(int nroVentaEliminar) {
		try {
			st = con.createStatement();
			String sql = "delete from tb_ventas_detalle where codventa = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nroVentaEliminar);
			prepareStmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}

	/*public void modificarVenta(int nroVenta) {
		try {
			st = con.createStatement();
			String sql = "update tb_ventas set totcompra=0, totventa=0, ganancia=0, descuento=0, saldo=0, nota='', metpago1=0, montpago1=0, metpago2=0, montpago2=0, estado=2  where codventa = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nroVenta);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "MODIFICADO CORRECTAMENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}*/
	public void modificarVenta(int nroVenta, int idcliente, int idusuario, double pretotC, double preTotalVentaFinal, double gananciaFinal, double descTotal, String nota, int metpago1, double monto1, int metpago2, double monto2) {
		try {
			java.util.Date d = new java.util.Date();
			// java.sql.Date date2 = new java.sql.Date(d.getTime());
			java.util.Date date = new Date();
			Object date2 = new java.sql.Timestamp(date.getTime());
			
			st = con.createStatement();
			String sql = "update tb_ventas set idcliente=?, fecha=?, idusuario=?, totcompra=?, totventa=?, ganancia=?, descuento=?, saldo=?, nota=?, metpago1=?, montpago1=?, metpago2=?, montpago2=?, estado=?  where codventa = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, idcliente);
			prepareStmt.setObject(2, date2);
			prepareStmt.setInt(3, idusuario);
			prepareStmt.setDouble(4, pretotC);
			prepareStmt.setDouble(5, preTotalVentaFinal);
			prepareStmt.setDouble(6, gananciaFinal);
			prepareStmt.setDouble(7, descTotal);
			prepareStmt.setDouble(8, 0);
			prepareStmt.setString(9, nota);
			prepareStmt.setInt(10, metpago1);
			prepareStmt.setDouble(11, monto1);
			prepareStmt.setInt(12, metpago2);
			prepareStmt.setDouble(13, monto2);
			prepareStmt.setInt(14, 2);
			prepareStmt.setInt(15, nroVenta);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "MODIFICADO CORRECTAMENTE rs");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}
	
	public void modificarVenta2(int nroVenta, int idcliente, int idusuario, double pretotC, double preTotalVentaFinal, double gananciaFinal, double descTotal, String nota, int metpago1, double monto1, int metpago2, double monto2, Object fechaElegida) {
		try {
			st = con.createStatement();
			String sql = "update tb_ventas set idcliente=?, fecha=?, totcompra=?, totventa=?, ganancia=?, descuento=?, saldo=?, nota=?, metpago1=?, montpago1=?, metpago2=?, montpago2=?, estado=?  where codventa = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, idcliente);
			prepareStmt.setObject(2, fechaElegida);
			prepareStmt.setInt(3, idusuario);
			prepareStmt.setDouble(4, pretotC);
			prepareStmt.setDouble(5, preTotalVentaFinal);
			prepareStmt.setDouble(6, gananciaFinal);
			prepareStmt.setDouble(7, descTotal);
			prepareStmt.setString(8, nota);
			prepareStmt.setInt(9, metpago1);
			prepareStmt.setDouble(10, monto1);
			prepareStmt.setInt(11, metpago2);
			prepareStmt.setDouble(12, monto2);
			prepareStmt.setInt(13, 2);
			prepareStmt.setInt(14, nroVenta);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "MODIFICADO CORRECTAMENTE rs");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}
	public void modificarVenta3(int nroVenta) {
		try {
			st = con.createStatement();
			String sql = "update tb_ventas set estado=?  where codventa = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, 3);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "MODIFICADO CORRECTAMENTE rs");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}
	
	public void eliminarVenta(int nroVenta) {
		try {
			st = con.createStatement();
			String sql = "update tb_ventas set totcompra=0, totventa=0, ganancia=0, descuento=0, saldo=0, nota='', metpago1=0, montpago1=0, metpago2=0, montpago2=0, estado=3  where codventa = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nroVenta);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "MODIFICADO CORRECTAMENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}
	public void ModificarDetalleVenta(int nroVenta) {
		try {
			st = con.createStatement();
			String sql = "update tb_ventas_detalle set cantidad=0, preVeSDInd=0, preVeSDTot=0, descIndiv=0, descTotal=0, subTotal=0, ganancia=0 where codventa = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, nroVenta);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "MODIFICADO CORRECTAMENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}

	public void reingresarProductos(String codProducto, float cantidad) {
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
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_productos");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error model");
		}
		return rs;
	}
	
	public ResultSet cargarConfiguraciones() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tb_configuraciones");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public void modificarAtributosProductos(String atributos) {
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
		try {
			st = con.createStatement();
			String sql = "update tb_configuraciones set ventasinstock = ? where idconfig = 1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, eleccion);
			prepareStmt.execute();
			if(eleccion == 1)
				JOptionPane.showMessageDialog(null, "CAMBIO REALIZADO: Ahora puede vender sin restricción del stock");
			if(eleccion == 0)
				JOptionPane.showMessageDialog(null, "CAMBIO REALIZADO: Ahora no puede vender stock menor a 0");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar ventasinstock: " + e);
		}
	}
	
	public void modificarReducirAlVender(int eleccion) {
		try {
			st = con.createStatement();
			String sql = "update tb_configuraciones set reducirstock = ? where idconfig = 1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, eleccion);
			prepareStmt.execute();
			if(eleccion == 1)
				JOptionPane.showMessageDialog(null, "CAMBIO REALIZADO: Ahora, cuando venda, si se descontará de su stock");
			if(eleccion == 0)
				JOptionPane.showMessageDialog(null, "CAMBIO REALIZADO: Ahora puede vender sin que su stock se vea afectado");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar reducirstock: " + e);
		}
	}
	
	public void actualizarStock(double cantTotal, int codproducto) {
		try {
			st = con.createStatement();
			String sql = "update tb_productos set cantidad = ? where codproducto = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setDouble(1, cantTotal);
			prepareStmt.setInt(2, codproducto);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "Stock actualizado");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar atributos: " + e);
		}
	}
	
	public void reIngresarStock(double cantVendida, int codproducto) {
		try {
			st = con.createStatement();
			String sql = "update tb_productos set cantidad = (cantidad+" + cantVendida + ") where codproducto = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, codproducto);
			prepareStmt.execute();
			//JOptionPane.showMessageDialog(null, "Stock actualizado");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar atributos: " + e);
		}
	}
	
	public void modificarFechaAlVender(int eleccion) {
		try {
			st = con.createStatement();
			String sql = "update tb_configuraciones set fechaVauto = ? where idconfig = 1";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, eleccion);
			prepareStmt.execute();
			if(eleccion == 1)
				JOptionPane.showMessageDialog(null, "CAMBIO REALIZADO: Ahora, cuando venda, podrá escoger con que fecha registrarla");
			if(eleccion == 0)
				JOptionPane.showMessageDialog(null, "CAMBIO REALIZADO: Ahora, cuando venda, se registrará con la fecha actual");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar reducirstock: " + e);
		}
	}
	
	public ResultSet cargarID() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select codproducto from tb_productos order by codproducto desc limit 1");
		} catch (Exception e) {
		}
		return rs;
	}
	public ResultSet cargarProdId(String Prod) {
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from tb_productos where codproducto ="+Prod);
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet deshabilitarProducto(int cod) {
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
	
	public ResultSet duplicarProducto(int idProducto) {
		try {
			st = con.createStatement();
			String sql = "INSERT INTO tb_productos (producto, detalles, marca, color, lote, laboratorio, unimedida, fechaVenc, categoria, almacen, iddistrib, cantidad, cantmin, precioCo, precioVe, ptjganancia, estado, promo1, cantp1, prep1, promo2, cantp2, prep2) "
					+ "SELECT producto, detalles, marca, color, lote, laboratorio, unimedida, fechaVenc, categoria, almacen, iddistrib, cantidad, cantmin, precioCo, precioVe, ptjganancia, estado, promo1, cantp1, prep1, promo2, cantp2, prep2 FROM tb_productos "
					+ "WHERE codproducto = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setInt(1, idProducto);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, " PRODUCTO DUPLICADO");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		return rs;
	}
	
	public ResultSet habilitarProducto(int cod) {
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
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from tb_usuarios where idusuario ='"+idusu+"'");
		} catch (Exception e) {
		}
		return rs;
	}
	
	public ResultSet deshabilitarUsuario(int usu) {
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
	
	public void aniadirPagodeCompra(int nroCompra, double nuevoMontoPagado, double nuevoSaldo) {
		try {
			st = con.createStatement();
			String sql = "update tb_compras set pagado = ?, saldo = ? where idcompra = ?";
			PreparedStatement prepareStmt = con.prepareStatement(sql);
			prepareStmt.setDouble(1, nuevoMontoPagado);
			prepareStmt.setDouble(2, nuevoSaldo);
			prepareStmt.setInt(3, nroCompra);
			prepareStmt.execute();
			JOptionPane.showMessageDialog(null, "Pago añadido correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al modificar reducirstock: " + e);
		}
	}
	
	public ResultSet ValorInventarioVenta() {
	    try {
	      this.st = this.con.createStatement();
	      this.rs = this.st.executeQuery("select sum( cantidad * precioVe) as venta FROM db_inventario.tb_productos where estado = 1");
	    } catch (Exception e) {
	      JOptionPane.showMessageDialog(null, "Error en consulta, al cargar inventario compras: " + e);
	    }
	    return this.rs;
	  }
	
	 public ResultSet ValorInventarioCompra() {
		    try {
		      this.st = this.con.createStatement();
		      this.rs = this.st.executeQuery("select sum( cantidad * precioCo) as compra FROM db_inventario.tb_productos where estado = 1");
		    } catch (Exception e) {
		      JOptionPane.showMessageDialog(null, "Error en consulta, al cargar inventario compras: " + e);
		    }
		    return this.rs;
		  }
}
