package gui_productos;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import com.mxrck.autocompleter.TextAutoCompleter;
import gui_principal.VentanaPrincipal;
import gui_ventas.Ventas;
import mysql.consultas;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MantenimientoProd extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnCrearProducto;
	private JMenu mnModificarProducto;
	private JMenu mnEliminarProducto;
	private JMenu mnIngresarStockA;
	private JButton btnX;
	private JLabel lblCdigo;
	private JTextField txtCodigo;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	private JTable tbProductos;
	private JMenu mnOtrasOpciones;
	private JMenuItem mntmRealizarKardex;
	private JMenuItem mntmVerHistorial;
	
	NuevoProducto np = new NuevoProducto(this, null);
	JTable tb;
	ResultSet rs;
	consultas consulta = new consultas();
	ModificarProducto mp = null;
	public VentanaPrincipal vp;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MantenimientoProd frame = new MantenimientoProd(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MantenimientoProd(VentanaPrincipal vp) {
		this.vp = vp;
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("ALMAC\u00C9N");
		setBounds(100, 100, 1134, 679);
		getContentPane().setLayout(null);
		
		btnX = new JButton("X");
		this.btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnX(arg0);
			}
		});
		btnX.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnX.setForeground(new Color(255, 255, 255));
		btnX.setBackground(new Color(220, 20, 60));
		btnX.setBounds(1030, 0, 63, 30);
		getContentPane().add(btnX);
		
		this.lblCdigo = new JLabel("Buscar:");
		lblCdigo.setForeground(Color.DARK_GRAY);
		this.lblCdigo.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblCdigo.setFont(new Font("Candara", Font.BOLD, 30));
		this.lblCdigo.setBounds(10, 45, 138, 34);
		getContentPane().add(this.lblCdigo);
		
		this.txtCodigo = new JTextField();
		txtCodigo.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtCodigo(e);
			}
		});
		this.txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtCodigo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		this.txtCodigo.setColumns(10);
		this.txtCodigo.setBackground(new Color(245, 245, 245));
		this.txtCodigo.setBounds(139, 45, 954, 34);
		getContentPane().add(this.txtCodigo);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 90, 1083, 519);
		getContentPane().add(this.scrollPane);
		
		tbProductos = new JTable();
		tbProductos.setAutoCreateRowSorter(true);
		tbProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbProductos.setFont(new Font("Arial", Font.ITALIC, 14));
		tbProductos.setBackground(Color.WHITE);
		tbProductos.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbProductos);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbProductos.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);
		
		mnCrearProducto = new JMenu("|Crear nuevo producto| ");
		mnCrearProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedMnCrearProducto(arg0);
			}
		});
		mnCrearProducto.setForeground(new Color(30, 144, 255));
		mnCrearProducto.setBackground(SystemColor.control);
		mnCrearProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnCrearProducto);
		
		mnModificarProducto = new JMenu("|Modificar producto| ");
		mnModificarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnModificarProducto(e);
			}
		});
		mnModificarProducto.setForeground(new Color(60, 179, 113));
		mnModificarProducto.setBackground(SystemColor.control);
		mnModificarProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnModificarProducto);
		
		mnEliminarProducto = new JMenu("|Eliminar producto| ");
		mnEliminarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnNewMenu_2(e);
			}
		});
		mnEliminarProducto.setForeground(new Color(220, 20, 60));
		mnEliminarProducto.setBackground(SystemColor.control);
		mnEliminarProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnEliminarProducto);
		
		mnIngresarStockA = new JMenu("|Agregar stock| ");
		mnIngresarStockA.setVisible(false);
		mnIngresarStockA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnIngresarStockA(e);
			}
		});
		mnIngresarStockA.setForeground(new Color(75, 0, 130));
		mnIngresarStockA.setBackground(SystemColor.control);
		mnIngresarStockA.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnIngresarStockA);
		
		mnOtrasOpciones = new JMenu("|Otras opciones|");
		mnOtrasOpciones.setVisible(false);
		mnOtrasOpciones.setForeground(new Color(255, 69, 0));
		mnOtrasOpciones.setFont(new Font("Tahoma", Font.BOLD, 20));
		mnOtrasOpciones.setBackground(SystemColor.menu);
		menuBar.add(mnOtrasOpciones);
		
		mntmRealizarKardex = new JMenuItem("Realizar Kardex");
		mntmRealizarKardex.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMntmRealizarKardex(e);
			}
		});
		mntmRealizarKardex.setForeground(new Color(255, 69, 0));
		mntmRealizarKardex.setFont(new Font("Arial", Font.PLAIN, 20));
		mnOtrasOpciones.add(mntmRealizarKardex);
		
		mntmVerHistorial = new JMenuItem("Ver Historial de Kardex");
		mntmVerHistorial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMntmVerHistorial(e);
			}
		});
		mntmVerHistorial.setForeground(new Color(255, 69, 0));
		mntmVerHistorial.setFont(new Font("Arial", Font.PLAIN, 20));
		mnOtrasOpciones.add(mntmVerHistorial);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
		cargarBuscador();
	}
	
	public void cargar() {
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbProductos;
		tb.setRowHeight(30);
		tb.setModel(dtm);
		
		// CARGAR ATRIBUTOS EN TABLA
		String atribTodos = "";
		try {
			consulta.iniciar();
			rs = consulta.cargarConfiguraciones();
			rs.next();
			atribTodos = rs.getString("atributosprod");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar atributos: " + e);
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (consulta != null)
					consulta.reset();
            } catch (Exception ex) {
            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
            }
		}

        List<String> list = new ArrayList<String>();
        list.add("ID");
        list.add("CÓDIGO");
        list.add("NOMBRE");
        list.add("DESCRIPCIÓN");
		String[] parts = atribTodos.split(",");
		for (int x=0; x<parts.length; x++){
			if(parts[x].equals("marca"))
				list.add("MARCA");
			if(parts[x].equals("color"))
				list.add("COLOR");
			if(parts[x].equals("lote"))
				list.add("LOTE");
			if(parts[x].equals("laboratorio"))
				list.add("LAB");
			if(parts[x].equals("fvencimiento"))
				list.add("FECHA VENC.");
		}
		list.add("UNI MED");
		list.add("CATEGORIA");
		list.add("ALMACÉN");
		list.add("DISTRIBUIDOR");
		list.add("STOCK");
		list.add("PREC CO");
		list.add("% GAN");
		list.add("PREC VE");
		String[] columnas = list.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
		/*dtm.setColumnIdentifiers(new Object[] { "Codigo", "Producto", "Detalle","Categoría", "Marca", "Color",
				"F. Vencimiento", "Uni. Medida", "Cantidad", "PrecioCompra", "PrecioVenta" });*/
		dtm.setColumnIdentifiers(columnas);
		
		
		try {
			consulta.iniciar();
			rs = consulta.cargarProductos();

			while (rs.next()){
				List<String> listProds = new ArrayList<String>();
		        listProds.add(rs.getString("codproducto"));
		        listProds.add(rs.getString("codbarra"));
		        listProds.add(rs.getString("producto"));
		        listProds.add(rs.getString("detalles"));
		        for (int x=0; x<parts.length; x++){
					if(parts[x].equals("marca"))
						listProds.add(rs.getString("marca"));
					if(parts[x].equals("color"))
						listProds.add(rs.getString("color"));
					if(parts[x].equals("lote"))
						listProds.add(rs.getString("lote"));
					if(parts[x].equals("laboratorio"))
						listProds.add(rs.getString("laboratorio"));
					if(parts[x].equals("fvencimiento")){
						try {
							// En esta linea de código estamos indicando el nuevo formato que queremos para nuestra fecha.
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
							// Aqui usamos la instancia formatter para darle el formato a la fecha. Es importante ver que el resultado es un string.
							String fechaOrdenada = formatter.format(rs.getDate("fechaVenc"));
							listProds.add(fechaOrdenada);
						} catch (Exception e) {
							listProds.add("");
						}
					}
				}
		        listProds.add(rs.getString("unimedida"));
		        listProds.add(rs.getString("categoria"));
		        listProds.add(rs.getString("almacen"));
		        
		        int iddistrib = rs.getInt("iddistrib");
		        try {
		        	 ResultSet rs2 = consulta.buscarDistribuidor(iddistrib);
		        	 rs2.next();
				     listProds.add(rs2.getString("nombre"));		        	 
				} catch (Exception e) {}
		        
		        listProds.add(rs.getString("cantidad"));
		        listProds.add(rs.getString("precioCo"));
		        listProds.add(rs.getString("ptjganancia"));
		        listProds.add(rs.getString("precioVe"));
		        
		        String[] columnasProds = listProds.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
				dtm.addRow(columnasProds); // AGREGAMOS EL PRODUCTO A LA LISTA
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS2: " + e);
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (consulta != null)
					consulta.reset();
            } catch (Exception ex) {
            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
            }
		}
		ajustarAnchoColumnas();
	}
	
	public void cargarBuscador() {
		ac = new TextAutoCompleter(txtCodigo);
		consulta.iniciar();
		ResultSet rs = consulta.cargarProductos();
		ac.setMode(0);
		try {
			while (rs.next()) {
				ac.addItem(rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " " + rs.getString("lote") + " * " + rs.getString("unimedida") + 
					"  -  (" + rs.getString("codproducto") + ")");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar buscador: " + e);
		}finally {
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
	
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbProductos.getColumnModel(); // 
		tcm.getColumn(0).setPreferredWidth(anchoColumna(2)); // ID
		tcm.getColumn(1).setPreferredWidth(anchoColumna(5)); // Código
		tcm.getColumn(2).setPreferredWidth(anchoColumna(10)); // Producto
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15)); // Detalle
		
		for(int i=0; i<tbProductos.getColumnCount(); i++)
			if(tbProductos.getColumnName(i).equals("FECHA VENC."))
				tcm.getColumn(i).setPreferredWidth(anchoColumna(10)); // FECHA DE VENCIMIENTO
		
		/*DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(5).setCellRenderer(tcr2);*/
	}

	protected void actionPerformedBtnX(ActionEvent arg0) {
		try {
			this.setClosed(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	public void selecionarProducto(String id) {
		int cantProductos = tbProductos.getRowCount();
		for (int i = 0; i < cantProductos; i++) {
			if (id.equals(tbProductos.getValueAt(i, 0))) {
				tbProductos.setRowSelectionInterval(i, i);
				break;
			}
		}
	}
	
	protected void mouseClickedMnCrearProducto(MouseEvent arg0) {
		try {
			if (np.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				np.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				np.setVisible(true); 
			} else {
				np = new NuevoProducto(this, null);
				np.setLocationRelativeTo(null);
				np.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
	
	// De manera global
	
	protected void mouseClickedMnModificarProducto(MouseEvent e) {
		DefaultTableModel tm = (DefaultTableModel) tbProductos.getModel();
		String codigoProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 0));

		abrirModificarProducto(codigoProducto);
	}
	private void abrirModificarProducto(String idProd){
		/*try {
			cerrarVentanas();
			mp = new mp(this);
			desktopPane.add(ventas);
			ventas.show();
			ventas.setMaximum(true);
			pintarBotones();
			btnVentas.setBackground(colorSelec);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(null, "Error al crear ventana Ventas: " + e);
		}*/
		
		try { 
			if (mp.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				mp.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				mp.setVisible(true); 
			} else {
				mp = new ModificarProducto(idProd, this);
				mp.setLocationRelativeTo(null);
				mp.setVisible(true);
			}
		} catch (Exception f) {
			mp = new ModificarProducto(""+idProd,this);;
			mp.setLocationRelativeTo(null);
			mp.setVisible(true);
		}
	}
	
	protected void mouseClickedMnNewMenu_2(MouseEvent e) {
		DefaultTableModel tm = (DefaultTableModel) tbProductos.getModel();
		String codigoProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 0));
		
		int opc = JOptionPane.showConfirmDialog(null, "¿Seguro de querer ELIMINAR ESTE PRODUCTO?", "Confirmación", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opc == 0) {
			elminarProducto(codigoProducto);
		}else{
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
	}
	
	private void elminarProducto(String codigoProducto){
		consulta.iniciar();
		consulta.deshabilitarProducto(Integer.parseInt(codigoProducto));
		cargar();
		consulta.reset();
	}
	
	protected void mouseClickedMnIngresarStockA(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "Ingresa stock con nuevos atributos creados en la DB");
	}
	protected void mouseClickedMntmRealizarKardex(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "Realizar kardex");
	}
	protected void mouseClickedMntmVerHistorial(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "Historial kardex");
	}
	protected void keyTypedTxtCodigo(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == (char) KeyEvent.VK_ENTER){
			String producto = txtCodigo.getText();

			
			String[] opciones = { "MODIFICAR", "ELIMINAR", "CANCELAR" };
			int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Seleccione una opcion",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

			if (seleccion == 0) {// MODIFICAR
				try {
					consulta.iniciar();
					String idProd = producto.substring(producto.indexOf("(")+1, producto.indexOf(")"));
					rs = consulta.buscarProductoID(Integer.parseInt(idProd));
					abrirModificarProducto(idProd);
				} catch (Exception e2) {// AQUI ES SI LO QUE SE INGRESA ES UN CÓDIGO DE BARRAS
					try {
						rs = consulta.buscarProductoBarras(producto);
						rs.next();
						int idProd = rs.getInt("codproducto");
						abrirModificarProducto(""+idProd);
					} catch (Exception e3) {
						// TODO: handle exception
					}				
				}finally {
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
			if (seleccion == 1) {//  ELIMINAR
				try {
					int idProd = Integer.parseInt( producto.substring(producto.indexOf("(")+1, producto.indexOf(")")));
					elminarProducto(""+idProd);
				} catch (Exception e2) {
					try {
						consulta.iniciar();
						rs = consulta.buscarProductoBarras(producto);
						rs.next();
						int idProd = rs.getInt("codproducto");
						elminarProducto(""+idProd);
					} catch (Exception e3) {
						// TODO: handle exception
					}	
					
				}finally {
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
			txtCodigo.setText("");
		}		
	}
	
	public void cerrarVentanas(){
		if(mp != null)
			mp.dispose();
		mp = null;
	}
	
	
}
