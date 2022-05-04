package gui_productos;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.mxrck.autocompleter.TextAutoCompleter;

import clases.AbstractJasperReports;
import gui_principal.VentanaPrincipal;
import gui_ventas.Ventas;
import mysql.MySQLConexion;
import mysql.consultas;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.EmptyBorder;

public class MantenimientoProd extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnCrearProducto;
	private JMenu mnModificarProducto;
	private JMenu mnEliminarProducto;
	private JLabel lblCdigo;
	private JTextField txtCodigo;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	private JTable tbProductos;
	
	NuevoProducto np = new NuevoProducto(this, null, null);
	JTable tb;
	ResultSet rs;
	consultas consulta = new consultas();
	ModificarProducto mp = null;
	DefaultTableModel dtm = new DefaultTableModel();
	consultas model = new consultas();
	String usuario;
	public VentanaPrincipal vp;
	private JTextField txtCodigo2;
	private JButton btnExportar;
	private JMenu mnduplicarProducto;
	private JButton btnInventarioPreCo;
	private JButton btnInventarioPreVe;

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
		usuario = vp.lblUsuario.getText();
		getContentPane().setBackground(Color.WHITE);
		setTitle("ALMAC\u00C9N");
		setBounds(100, 100, 1134, 679);
		getContentPane().setLayout(null);
		
		this.lblCdigo = new JLabel("Buscar:");
		lblCdigo.setVerticalAlignment(SwingConstants.TOP);
		lblCdigo.setForeground(Color.DARK_GRAY);
		this.lblCdigo.setFont(new Font("Candara", Font.BOLD, 30));
		this.lblCdigo.setBounds(10, 45, 113, 34);
		getContentPane().add(this.lblCdigo);
		
		txtCodigo2 = new JTextField();
		txtCodigo2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				keyReleasedTxtCodigo2(arg0);
			}
		});
		txtCodigo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodigo2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		txtCodigo2.setColumns(10);
		txtCodigo2.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		txtCodigo2.setBackground(new Color(245, 245, 245));
		txtCodigo2.setBounds(123, 45, 428, 34);
		getContentPane().add(txtCodigo2);
		
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
		
		btnExportar = new JButton("<html><center>EXPORTAR INVENTARIO</center></html>");
		btnExportar.setVisible(false);
		btnExportar.setVerticalAlignment(SwingConstants.TOP);
		btnExportar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExportar.setBorder(new LineBorder(new Color(138, 43, 226), 3, true));
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnExportar(arg0);
			}
		});
		btnExportar.setForeground(new Color(138, 43, 226));
		btnExportar.setFont(new Font("Tahoma", Font.BOLD, 5));
		btnExportar.setBackground(new Color(255, 255, 255));
		btnExportar.setBounds(1014, 11, 79, 19);
		getContentPane().add(btnExportar);
		
		btnInventarioPreCo = new JButton("<html><center>Ver valor de inventario a Precio Compra</center></html>");
		btnInventarioPreCo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnInventarioPreCo(e);
			}
		});
		btnInventarioPreCo.setVerticalAlignment(SwingConstants.TOP);
		btnInventarioPreCo.setForeground(new Color(138, 43, 226));
		btnInventarioPreCo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInventarioPreCo.setBorder(new LineBorder(new Color(138, 43, 226), 3, true));
		btnInventarioPreCo.setBackground(Color.WHITE);
		btnInventarioPreCo.setBounds(680, 32, 198, 47);
		getContentPane().add(btnInventarioPreCo);
		
		btnInventarioPreVe = new JButton("<html><center>Ver valor de inventario a Precio Venta</center></html>");
		btnInventarioPreVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnInventarioPreVe(e);
			}
		});
		btnInventarioPreVe.setVerticalAlignment(SwingConstants.TOP);
		btnInventarioPreVe.setForeground(new Color(138, 43, 226));
		btnInventarioPreVe.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInventarioPreVe.setBorder(new LineBorder(new Color(138, 43, 226), 3, true));
		btnInventarioPreVe.setBackground(Color.WHITE);
		btnInventarioPreVe.setBounds(914, 32, 179, 47);
		getContentPane().add(btnInventarioPreVe);
		
		this.txtCodigo = new JTextField();
		this.txtCodigo.setVisible(false);
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
		this.txtCodigo.setBounds(123, 45, 428, 34);
		getContentPane().add(this.txtCodigo);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{this.txtCodigo, this.txtCodigo2}));
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbProductos.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(Color.DARK_GRAY);
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
		mnModificarProducto.setForeground(new Color(218, 112, 214));
		mnModificarProducto.setBackground(SystemColor.control);
		mnModificarProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnModificarProducto);
		
		mnduplicarProducto = new JMenu("|Duplicar| ");
		mnduplicarProducto.setVisible(false);
		mnduplicarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedMnduplicarProducto(arg0);
			}
		});
		
		mnEliminarProducto = new JMenu("|Eliminar producto| ");
		mnEliminarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnNewMenu_2(e);
			}
		});
		mnEliminarProducto.setForeground(new Color(240, 128, 128));
		mnEliminarProducto.setBackground(SystemColor.control);
		mnEliminarProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnEliminarProducto);
		
		JMenu mnaadirStock = new JMenu("|A\u00F1adir stock| ");
		mnaadirStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnaadirStock(e);
			}
		});
		mnaadirStock.setForeground(new Color(50, 205, 50));
		mnaadirStock.setFont(new Font("Tahoma", Font.BOLD, 20));
		mnaadirStock.setBackground(SystemColor.menu);
		menuBar.add(mnaadirStock);
		mnduplicarProducto.setForeground(new Color(135, 206, 250));
		mnduplicarProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		mnduplicarProducto.setBackground(SystemColor.menu);
		menuBar.add(mnduplicarProducto);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		cargar();
		cargarBuscador();
	}
	
	public void cargar() {
		tb = this.tbProductos;
		tb.setRowHeight(30);
		tb.setModel(dtm);
		
		// CARGAR ATRIBUTOS EN TABLA
		cargarTabla("todos");
	}
	
	public void cargarTabla(String prod){
		limpiarTabla();
		
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
        list.add("C BARRA");
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
		//list.add("ALMACÉN");
		//list.add("DISTRIBUIDOR");
		list.add("STOCK");
		list.add("PREC CO");
		//list.add("% GAN");
		list.add("PREC VE");
		String[] columnas = list.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
		/*dtm.setColumnIdentifiers(new Object[] { "Codigo", "Producto", "Detalle","Categoría", "Marca", "Color",
				"F. Vencimiento", "Uni. Medida", "Cantidad", "PrecioCompra", "PrecioVenta" });*/
		dtm.setColumnIdentifiers(columnas);
		
		
		try {
			consulta.iniciar();
			if(prod.equals("todos"))
				rs = consulta.cargarProductos();
			else{
				rs = consulta.cargarProductoParticular(prod);
			}

			while (rs.next()){
				
				if(rs.getInt("estado") == 1){
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
			        //listProds.add(rs.getString("almacen"));
			        
			        /*int iddistrib = rs.getInt("iddistrib");
			        try {
			        	 ResultSet rs2 = consulta.buscarDistribuidor(iddistrib);
			        	 rs2.next();
					     listProds.add(rs2.getString("nombre"));		        	 
					} catch (Exception e) {}*/
			        
			        listProds.add(rs.getString("cantidad"));
			        listProds.add(rs.getString("precioCo"));
			        //listProds.add(rs.getString("ptjganancia"));
			        listProds.add(rs.getString("precioVe"));
			        
			        String[] columnasProds = listProds.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
					dtm.addRow(columnasProds); // AGREGAMOS EL PRODUCTO A LA LISTA
				}				
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
		tcm.getColumn(2).setPreferredWidth(anchoColumna(15)); // Producto
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10)); // Detalle
		
		for(int i=0; i<tbProductos.getColumnCount(); i++)
			if(tbProductos.getColumnName(i).equals("FECHA VENC."))
				tcm.getColumn(i).setPreferredWidth(anchoColumna(10)); // FECHA DE VENCIMIENTO
		
		/*DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(5).setCellRenderer(tcr2);*/
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
				np = new NuevoProducto(this, null, usuario);
				np.setLocationRelativeTo(null);
				np.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
		
		
		
		
	}
	
	// De manera global
	
	protected void mouseClickedMnModificarProducto(MouseEvent e) {
		String codigoProducto = tbProductos.getValueAt(tb.getSelectedRow(), 0).toString();
		abrirModificarProducto(codigoProducto);
	}
	private void abrirModificarProducto(String idProd){
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
			mp = new ModificarProducto(""+idProd, this);
			mp.setLocationRelativeTo(null);
			mp.setVisible(true);
			mp.setExtendedState(0);
		}
	}
	
	protected void mouseClickedMnNewMenu_2(MouseEvent e) {
		String codigoProducto = tbProductos.getValueAt(tb.getSelectedRow(), 0).toString();
		String producto = tbProductos.getValueAt(tb.getSelectedRow(), 2).toString();
		String descripcion = tbProductos.getValueAt(tb.getSelectedRow(), 3).toString();
		String marca = tbProductos.getValueAt(tb.getSelectedRow(), 4).toString();
		String color = tbProductos.getValueAt(tb.getSelectedRow(), 5).toString();
		
		int opc = JOptionPane.showConfirmDialog(null, "¿Seguro de querer ELIMINAR el producto:\n" + producto + " " + descripcion + " " + marca + " " + color + " ?", "Confirmación", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opc == 0) {
			elminarProducto(codigoProducto);
			cargarBuscador();
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
	protected void keyTypedTxtCodigo(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == (char) KeyEvent.VK_ENTER){
			if(txtCodigo.getText().length()==0)
				JOptionPane.showMessageDialog(null, "Escriba el producto que desee buscar");
			else{
				String productoBuscado = txtCodigo.getText();
				int codproducto = -1;
				String productoName = "";
				String productoDetail = "";
				float cantActual = -1;
				float precioCo = -1;
				float precioVe = -1;
				Date fv = null;
				
				try {
					codproducto = Integer.parseInt( productoBuscado.substring(productoBuscado.indexOf("(")+1, productoBuscado.indexOf(")")));
					try {
						model.iniciar();
						rs = model.buscarProductoID(codproducto);
						rs.next();
						
						codproducto = rs.getInt("codproducto");
						productoName = rs.getString("producto");
						productoDetail = rs.getString("detalles");
						cantActual = rs.getFloat("cantidad");
						precioCo = rs.getFloat("precioCo");
						precioVe = rs.getFloat("precioVe");
					} catch (Exception e3) {
					} finally {
						try {
							if (rs != null)
								rs.close();
							if (model != null)
								model.reset();
			            } catch (Exception ex) {
			            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
			            }
					}
				} catch (Exception e2) {
					try {
						model.iniciar();
						rs = model.buscarProductoBarras(productoBuscado);
						rs.next();
						
						codproducto = rs.getInt("codproducto");
						productoName = rs.getString("producto");
						productoDetail = rs.getString("detalles");
						cantActual = rs.getFloat("cantidad");
						precioCo = rs.getFloat("precioCo");
						precioVe = rs.getFloat("precioVe");

						fv = rs.getDate("fechaVenc");
					} catch (Exception e3) {
					} finally {
						try {
							if (rs != null)
								rs.close();
							if (model != null)
								model.reset();
			            } catch (Exception ex) {
			            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
			            }
					}		
				}
				
				txtCodigo.setText("");
				
				String[] opciones = { "AÑADIR STOCK", "MODIFICAR", "DUPLICAR", "ELIMINAR", "CANCELAR" };
				int seleccion = JOptionPane.showOptionDialog(null, productoName + " " + productoDetail, "Seleccione una opcion",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

				if (seleccion == 0) {// añadir stock
					try {
						int idProd = Integer.parseInt( productoBuscado.substring(productoBuscado.indexOf("(")+1, productoBuscado.indexOf(")")));
						model.iniciar();
						AgregarStock as = new AgregarStock(idProd, cantActual, precioCo, precioVe, fv, usuario, this);
						as.setVisible(true);
						model.reset();
						
					} catch (Exception e2) {// AQUI ES SI LO QUE SE INGRESA ES UN CÓDIGO DE BARRAS
						AgregarStock as = new AgregarStock(codproducto, cantActual, precioCo, precioVe, fv, usuario, this);
						as.setVisible(true);				
					}
				}
				
				if (seleccion == 1) {// MODIFICAR
					try {
						int idProd = Integer.parseInt( productoBuscado.substring(productoBuscado.indexOf("(")+1, productoBuscado.indexOf(")")));
						abrirModificarProducto(""+idProd);
						
					} catch (Exception e2) {// AQUI ES SI LO QUE SE INGRESA ES UN CÓDIGO DE BARRAS
						try {
							abrirModificarProducto(""+codproducto);
						} catch (Exception e3) {
						}
					}
				}
				
				if (seleccion == 2) {// DUPLICAR
					try {
						int idProd = Integer.parseInt( productoBuscado.substring(productoBuscado.indexOf("(")+1, productoBuscado.indexOf(")")));
						try {
							int opc = JOptionPane.showConfirmDialog(null, "¿Crear una copia de este producto? \nEl código de barras no se copiará.", "Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
							if (opc == 0) {
								model.iniciar();
								model.duplicarProducto(idProd);
								cargar();
								model.reset();
							}
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Error: Seleccione un producto");
						}	
					} catch (Exception e2) {// AQUI ES SI LO QUE SE INGRESA ES UN CÓDIGO DE BARRAS
						try {
							int opc = JOptionPane.showConfirmDialog(null, "¿Crear una copia de este producto? \nEl código de barras no se copiará.", "Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
							if (opc == 0) {
								model.iniciar();
								model.duplicarProducto(codproducto);
								cargar();
								model.reset();
							}
						} catch (Exception e3) {
							JOptionPane.showMessageDialog(null, "Error: Seleccione un producto");
						}		
					}
				}				
				
				if (seleccion == 3) {//  ELIMINAR
					try {
						int idProd = Integer.parseInt( productoBuscado.substring(productoBuscado.indexOf("(")+1, productoBuscado.indexOf(")")));
						elminarProducto(""+idProd);
						cargarBuscador();
					} catch (Exception e2) {
						try {
							elminarProducto(""+codproducto);
							model.reset();
							cargarBuscador();
						} catch (Exception e3) {
							// TODO: handle exception
						}
					}
				}
			}
		}
		
	}
	
	public void cerrarVentanas(){
		if(mp != null)
			mp.dispose();
		mp = null;
	}
	protected void keyReleasedTxtCodigo2(KeyEvent arg0) {
		if(txtCodigo2.getText().length()==0){
			limpiarTabla();
			cargarTabla("todos");
		}
		else
			cargarTabla(txtCodigo2.getText());
	}
	
	private void limpiarTabla(){
		for (int i = 0; i < tbProductos.getRowCount(); i++) {
			dtm.removeRow(i);
			i -= 1;
		}
	}
	
	protected void actionPerformedBtnExportar(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			String prod = "";
			Map parameters = new HashMap();
			
//			if (chckbxFiltrar.isSelected()){
			if(txtCodigo2.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "Mostrando inventario total" + prod);
				new AbstractJasperReports().createReport(con, "rInventarioTodo.jasper", parameters);
			}
			else{
				prod = txtCodigo2.getText();
				JOptionPane.showMessageDialog(null, "Mostrando inventario de productos con referecia de: " + prod);
				parameters.put("prod", prod);
				new AbstractJasperReports().createReport(con, "rInventarioFiltrado.jasper", parameters);					
			}
//			}
//			else{
//				if(txtCodigo.getText().length() == 0){
//					JOptionPane.showMessageDialog(null, "Mostrando inventario total" + prod);
//					new AbstractJasperReports().createReport(con, "rInventarioTodo.jasper", parameters);
//				}
//				else{
//					prod = txtCodigo.getText();
//					JOptionPane.showMessageDialog(null, "Mostrando inventario de productos con referecia de: " + prod);
//					parameters.put("prod", prod);
//					new AbstractJasperReports().createReport(con, "rInventarioFiltrado.jasper", parameters);					
//				}
//			} 

			AbstractJasperReports.showViewer();
			
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al cargar reporte: " + ex);
		}	
	}
	protected void mouseClickedMnaadirStock(MouseEvent e) {
		try {
			int idProducto = Integer.parseInt(tbProductos.getValueAt(tb.getSelectedRow(), 0).toString());
			try {
				
				model.iniciar();
				rs = model.buscarProductoID(idProducto);
				rs.next();
				
				String productoName = rs.getString("producto");
				String productoDetail = rs.getString("detalles");
				float cantActual = rs.getFloat("cantidad");
				float precioCo = rs.getFloat("precioCo");
				float precioVe = rs.getFloat("precioVe");
				Date fv = rs.getDate("fechaVenc");
				
				
				AgregarStock as = new AgregarStock(idProducto, cantActual, precioCo, precioVe, fv, usuario, this);
				as.setVisible(true);
				
				/*float stockanadir = Float.parseFloat(JOptionPane.showInputDialog("Ingrese stock a añadir al producto:\n" + productoName + " " + productoDetail + "\n\nStock actual: " + cantidadActual+ "\n"));
				
				float cantidadFinal = cantActual + stockanadir;
				
				//JOptionPane.showMessageDialog(null, "Ahora: " + cantidadFinal);
				model.ingresarStock(idProducto, cantidadFinal);
				cargar();
				selecionarProducto(""+idProducto);*/
				
			} catch (Exception e2) {
				
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (model != null)
						model.reset();
	            } catch (Exception ex) {
	            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
	            }
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	 
	protected void mouseClickedMnduplicarProducto(MouseEvent arg0) {
		try {
			int opc = JOptionPane.showConfirmDialog(null, "¿Crear una copia de este producto? \nEl código de barras no se copiará.", "Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if (opc == 0) {
				int idProducto = Integer.parseInt(tbProductos.getValueAt(tb.getSelectedRow(), 0).toString());
				
				consulta.iniciar();
				consulta.duplicarProducto(idProducto);
				cargar();
				consulta.reset();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: Seleccione un producto");
		}
	}
	protected void actionPerformedBtnInventarioPreCo(ActionEvent e) {
		consultas consulta = new consultas();

	    float compra = 0.0F;

	    consulta.iniciar();
	    ResultSet rs = consulta.ValorInventarioCompra();
	    try {
	      rs.next();
	      compra = rs.getFloat("compra");
	    }
	    catch (Exception ex) {
	      JOptionPane.showMessageDialog(null, "ERROR al cargar Compras: " + ex);
	    }
	    consulta.reset();

	    JOptionPane.showMessageDialog(null, "El valor de su inventario actual a precio de Compra es de: S/ " + compra);
	}
	
	protected void actionPerformedBtnInventarioPreVe(ActionEvent e) {
		consultas consulta = new consultas();

	    float venta = 0.0F;

	    consulta.iniciar();
	    ResultSet rs = consulta.ValorInventarioVenta();
	    try {
	      rs.next();
	      venta = rs.getFloat("venta");
	    }
	    catch (Exception ex) {
	      JOptionPane.showMessageDialog(null, "ERROR al cargar Ventas: " + ex);
	    }
	    consulta.reset();

	    JOptionPane.showMessageDialog(null, "El valor de su inventario actual a precio de Venta es de: S/ " + venta);
	}
}




