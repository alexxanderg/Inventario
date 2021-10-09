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

public class Kardex extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnEmpezarCero;
	private JLabel lblCdigo;
	private JTextField txtCodigo;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	private JTable tbProductos;
	private JCheckBox chckbxFiltrar;
	
	JTable tb;
	ResultSet rs;
	consultas consulta = new consultas();
	ModificarProducto mp = null;
	DefaultTableModel dtm = new DefaultTableModel();
	consultas model = new consultas();
	String usuario;
	public VentanaPrincipal vp;
	private JTextField txtCodigo2;
	private JButton btnMas1;
	private JButton btnMenos1;
	private JLabel lblNewLabel;
	private JTextField txtNota;
	private JLabel lblDejarNotaAdicional;
	private JMenu mncargarltimoRegistro;
	private JMenu mnfusionar;
	private JLabel lblKardex;
	private JMenu mnValorMonetario;
	ValorMonetarioKardex vValorMonetario;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kardex frame = new Kardex(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Kardex(VentanaPrincipal vp) {
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
		this.lblCdigo.setBounds(10, 179, 113, 34);
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
		this.txtCodigo.setBounds(123, 179, 428, 34);
		getContentPane().add(this.txtCodigo);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 224, 1083, 385);
		getContentPane().add(this.scrollPane);
		
		tbProductos = new JTable();
		tbProductos.setAutoCreateRowSorter(true);
		tbProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbProductos.setFont(new Font("Arial", Font.ITALIC, 14));
		tbProductos.setBackground(Color.WHITE);
		tbProductos.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbProductos);
		
		chckbxFiltrar = new JCheckBox("\u00BFFiltrar al escribir?");
		chckbxFiltrar.setVisible(false);
		chckbxFiltrar.setForeground(new Color(30, 144, 255));
		chckbxFiltrar.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxFiltrar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				itemStateChangedChckbxFiltrar(arg0);
			}
		});
		chckbxFiltrar.setBackground(Color.WHITE);
		chckbxFiltrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxFiltrar.setBounds(342, 188, 197, 20);
		getContentPane().add(chckbxFiltrar);
		
		txtCodigo2 = new JTextField();
		txtCodigo2.setVisible(false);
		txtCodigo2.setEnabled(false);
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
		txtCodigo2.setBounds(123, 179, 428, 34);
		getContentPane().add(txtCodigo2);
		
		btnMas1 = new JButton("+1");
		btnMas1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnMas1(arg0);
			}
		});
		btnMas1.setForeground(Color.WHITE);
		btnMas1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnMas1.setBackground(new Color(50, 205, 50));
		btnMas1.setBounds(1015, 179, 78, 33);
		getContentPane().add(btnMas1);
		
		btnMenos1 = new JButton("-1");
		btnMenos1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnMenos1(arg0);
			}
		});
		btnMenos1.setForeground(Color.WHITE);
		btnMenos1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnMenos1.setBackground(new Color(220, 20, 60));
		btnMenos1.setBounds(913, 180, 78, 33);
		getContentPane().add(btnMenos1);
		
		lblNewLabel = new JLabel("<html><center>En esta ventana puede hacer una verificaci\u00F3n de su inventario. Podr\u00E1 comparar su Stock que indica el sistema con el que va revisando de manera fisica (columna CONTEO).<br> Cuando culmine, puede fusionar los cambios con sus productos reales.</center></html>");
		lblNewLabel.setForeground(new Color(105, 105, 105));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(573, 74, 520, 78);
		getContentPane().add(lblNewLabel);
		
		txtNota = new JTextField();
		txtNota.setHorizontalAlignment(SwingConstants.LEFT);
		txtNota.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		txtNota.setColumns(10);
		txtNota.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		txtNota.setBackground(new Color(245, 245, 245));
		txtNota.setBounds(10, 118, 541, 34);
		getContentPane().add(txtNota);
		
		lblDejarNotaAdicional = new JLabel("ANOTACI\u00D3N:");
		lblDejarNotaAdicional.setForeground(Color.DARK_GRAY);
		lblDejarNotaAdicional.setFont(new Font("Candara", Font.BOLD, 25));
		lblDejarNotaAdicional.setBounds(10, 77, 249, 34);
		getContentPane().add(lblDejarNotaAdicional);
		
		lblKardex = new JLabel("KARDEX");
		lblKardex.setForeground(Color.BLACK);
		lblKardex.setFont(new Font("Candara", Font.BOLD, 30));
		lblKardex.setBounds(500, 11, 113, 52);
		getContentPane().add(lblKardex);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbProductos.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCodigo, chckbxFiltrar, txtCodigo2}));
		
		mnEmpezarCero = new JMenu("|Reiniciar conteo a cero| ");
		mnEmpezarCero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedMnEmpezarCero(arg0);
			}
		});
		
		JMenu mnaadirStock = new JMenu("|Guardar conteo actual| ");
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
		
		mncargarltimoRegistro = new JMenu("|Cargar \u00FAltimo conteo| ");
		mncargarltimoRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedMncargarltimoRegistro(arg0);
			}
		});
		mncargarltimoRegistro.setForeground(new Color(255, 215, 0));
		mncargarltimoRegistro.setFont(new Font("Tahoma", Font.BOLD, 20));
		mncargarltimoRegistro.setBackground(SystemColor.menu);
		menuBar.add(mncargarltimoRegistro);
		mnEmpezarCero.setForeground(new Color(240, 128, 128));
		mnEmpezarCero.setBackground(SystemColor.control);
		mnEmpezarCero.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnEmpezarCero);
		
		mnfusionar = new JMenu("|FUSIONAR| ");
		mnfusionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedMnfusionar(arg0);
			}
		});
		mnfusionar.setForeground(new Color(30, 144, 255));
		mnfusionar.setFont(new Font("Tahoma", Font.BOLD, 20));
		mnfusionar.setBackground(SystemColor.menu);
		menuBar.add(mnfusionar);
		
		mnValorMonetario = new JMenu("Valor Monetario");
		mnValorMonetario.setFont(new Font("Tahoma", Font.BOLD, 20));
		mnValorMonetario.setForeground(new Color(47, 79, 79));
		menuBar.add(mnValorMonetario);
		mnValorMonetario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedMnValorMonetario(arg0);
			}
		});

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		cargar();
		cargarBuscador();
		cargarKardexCero();
	}
	
	public void cargar() {
		tb = this.tbProductos;
		tb.setRowHeight(40);
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
			//if(parts[x].equals("lote"))
			//	list.add("LOTE");
			if(parts[x].equals("laboratorio"))
				list.add("LAB");
			//if(parts[x].equals("fvencimiento"))
			//	list.add("FECHA VENC.");
		}
		list.add("UNI MED");
		list.add("CATEGORIA");
		//list.add("ALMACÉN");
		//list.add("DISTRIBUIDOR");
		//list.add("PREC CO");
		//list.add("% GAN");
		//list.add("PREC VE");
		list.add("STOCK");
		list.add("CONTEO");
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
						//if(parts[x].equals("lote"))
						//	listProds.add(rs.getString("lote"));
						if(parts[x].equals("laboratorio"))
							listProds.add(rs.getString("laboratorio"));
						/*if(parts[x].equals("fvencimiento")){
							try {
								// En esta linea de código estamos indicando el nuevo formato que queremos para nuestra fecha.
								SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
								// Aqui usamos la instancia formatter para darle el formato a la fecha. Es importante ver que el resultado es un string.
								String fechaOrdenada = formatter.format(rs.getDate("fechaVenc"));
								listProds.add(fechaOrdenada);
							} catch (Exception e) {
								listProds.add("");
							}
						}*/
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
			        
			        //listProds.add(rs.getString("precioCo"));
			        //listProds.add(rs.getString("ptjganancia"));
			        //listProds.add(rs.getString("precioVe"));
			        listProds.add(rs.getString("cantidad"));
			        //System.out.println(listProds);
			        
			        String[] columnasProds = listProds.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
			        //System.out.println(columnasProds);
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
		
		try {
			ac = new TextAutoCompleter(txtCodigo);
			consulta.iniciar();
			ResultSet rs = consulta.cargarProductos();
			ac.setMode(0);
			
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
	
	private void cargarKardexCero(){
		int cantProductos = tbProductos.getRowCount();
		for (int j = 0; j < cantProductos; j++) {			
			for(int i=0; i<tbProductos.getColumnCount(); i++){
				if(tbProductos.getColumnName(i).equals("CONTEO")){
						tbProductos.setValueAt(0, j, i);
					}
			}
		}
	}
	
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbProductos.getColumnModel(); // 
		tcm.getColumn(0).setPreferredWidth(anchoColumna(1)); // ID
		tcm.getColumn(1).setPreferredWidth(anchoColumna(4)); // Código
		tcm.getColumn(2).setPreferredWidth(anchoColumna(20)); // Producto
		tcm.getColumn(3).setPreferredWidth(anchoColumna(25)); // Detalle
		
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
	private void abrirModificarProducto(String idProd){
	}
	
	private void elminarProducto(String codigoProducto){
	}
	protected void keyTypedTxtCodigo(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == (char) KeyEvent.VK_ENTER){
			if(txtCodigo.getText().length()==0)
				JOptionPane.showMessageDialog(null, "Escriba el producto que desee buscar");
			else{
				String producto = txtCodigo.getText();
				txtCodigo.setText("");
				
					try {
						int idProdBuscar = Integer.parseInt( producto.substring(producto.indexOf("(")+1, producto.indexOf(")")));
						//JOptionPane.showMessageDialog(null, "IDBUSCAR: " + idProdBuscar);
						
						DefaultTableModel tm = (DefaultTableModel) tbProductos.getModel();
						
						for (int j = 0; j < tbProductos.getRowCount(); j++) {
							int idProdFila  = Integer.parseInt( String.valueOf(tm.getValueAt(j, 0)) );
							
							if(idProdBuscar == idProdFila){
								tbProductos.setRowSelectionInterval(j, j);
								
								for(int i=0; i<tbProductos.getColumnCount(); i++){
									
									if(tbProductos.getColumnName(i).equals("CONTEO")){
										if(tbProductos.getValueAt(j, i) == null || tbProductos.getValueAt(j, i).toString().equals("0")){
											tbProductos.setValueAt(1, j, i);
											JOptionPane.showMessageDialog(null, producto + "\n\nConteo = 1");
										}
										else{
											int conteoold = Integer.parseInt(tbProductos.getValueAt(j, i).toString());
											tbProductos.setValueAt(conteoold+1, j, i);
											JOptionPane.showMessageDialog(null, producto + "\n\nConteo = " + (conteoold+1));
										}
									}
									
								}	
							}
						}
						
					} catch (Exception e2) {// AQUI ES SI LO QUE SE INGRESA ES UN CÓDIGO DE BARRAS
						try {
							model.iniciar();
							rs = model.buscarProductoBarras(producto);
							rs.next();
							int idProdBuscar = rs.getInt("codproducto");
							DefaultTableModel tm = (DefaultTableModel) tbProductos.getModel();
							
							for (int j = 0; j < tbProductos.getRowCount(); j++) {
								int idProdFila = Integer.parseInt( String.valueOf(tm.getValueAt(j, 0)) );
								
								if(idProdBuscar == idProdFila){
									tbProductos.setRowSelectionInterval(j, j);
									for(int i=0; i<tbProductos.getColumnCount(); i++){
										if(tbProductos.getColumnName(i).equals("CONTEO")){
											if(tbProductos.getValueAt(j, i) == null || tbProductos.getValueAt(j, i).toString().equals("0")){
												tbProductos.setValueAt(1, j, i);
												JOptionPane.showMessageDialog(null, producto + "\n\nConteo = 1");
											}
											else{
												int conteoold = Integer.parseInt(tbProductos.getValueAt(j, i).toString());
												tbProductos.setValueAt(conteoold+1, j, i);
												JOptionPane.showMessageDialog(null, producto + "\n\nConteo = " + (conteoold+1));
											}

										}
									}	
								}
							}						
							
							
						} catch (Exception e3) {
							// TODO: handle exception
						}	finally {
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
			}
		}
		
	}
	
	
	protected void keyReleasedTxtCodigo2(KeyEvent arg0) {
		/*if(txtCodigo2.getText().length()==0){
			limpiarTabla();
		}
		else
			cargarTabla(txtCodigo2.getText());*/
	}
	protected void itemStateChangedChckbxFiltrar(ItemEvent arg0) {
		/*if(txtCodigo.isVisible()){
			txtCodigo.setVisible(false);
			txtCodigo2.setVisible(true);
		}
		else{
			cargarTabla("todos");
			txtCodigo.setVisible(true);
			txtCodigo2.setVisible(false);			
		}*/
	}
	
	private void limpiarTabla(){
		for (int i = 0; i < tbProductos.getRowCount(); i++) {
			dtm.removeRow(i);
			i -= 1;
		}
	}
	protected void mouseClickedMnaadirStock(MouseEvent arg0) {
		String nota = "";
		try {
			nota = txtNota.getText();
		} catch (Exception e) {}
		
		java.util.Date date = new Date();
		Object date2 = new java.sql.Timestamp(date.getTime());
		//Date date2 = new java.sql.Date(date.getTime());
		model.iniciar();
		model.registrarKardex(date2, nota);
		
		rs = model.ObtenerUltimoNroKardex();
		int idkardex = 0;
		try {
			rs.next();
			idkardex = rs.getInt("idkardex");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR al obtener ultimo kardex: " + ex);
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
		DefaultTableModel tm = (DefaultTableModel) tbProductos.getModel();
		try {
			int cantProductos = tbProductos.getRowCount();
			for (int i = 0; i < cantProductos; i++) {
				String idProducto = String.valueOf(tm.getValueAt(i, 0));
				for(int j=0; j<tbProductos.getColumnCount(); j++){
					if(tbProductos.getColumnName(j).equals("CONTEO")){
						float conteo = Float.parseFloat(String.valueOf(tm.getValueAt(i, j)));

						model.iniciar();
						try {
							model.registrarDetallesKardex(idkardex, idProducto, conteo);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "error al guardar detalles de kardes " + e);
						}finally {
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
				}
			}		

			this.dispose();
			JOptionPane.showMessageDialog(null, "CONTEO SALVADO CORRECTAMENTE");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage());
		}
		
		
	}
	protected void actionPerformedBtnMenos1(ActionEvent arg0) {
		for(int i=0; i<tbProductos.getColumnCount(); i++){
			if(tbProductos.getColumnName(i).equals("CONTEO")){
				if(tbProductos.getValueAt(tbProductos.getSelectedRow(), i) == null || tbProductos.getValueAt(tbProductos.getSelectedRow(), i).toString().equals("0")){
					tbProductos.setValueAt(0, tbProductos.getSelectedRow(), i);
					//JOptionPane.showMessageDialog(null, "No puede tener numeros negativos");
				}
				else{
					int conteoold = Integer.parseInt(tbProductos.getValueAt(tbProductos.getSelectedRow(), i).toString());
					tbProductos.setValueAt(conteoold-1, tbProductos.getSelectedRow(), i);
					//JOptionPane.showMessageDialog(null, "-1 \n\nConteo: " + (conteoold-1));
				}
			}
		}		
	}
	protected void actionPerformedBtnMas1(ActionEvent arg0) {
		for(int i=0; i<tbProductos.getColumnCount(); i++){
			if(tbProductos.getColumnName(i).equals("CONTEO")){
				if(tbProductos.getValueAt(tbProductos.getSelectedRow(), i) == null || tbProductos.getValueAt(tbProductos.getSelectedRow(), i).toString().equals("0")){
					tbProductos.setValueAt(1, tbProductos.getSelectedRow(), i);
					//JOptionPane.showMessageDialog(null, "+1 \n\nConteo: 1");
				}
				else{
					int conteoold = Integer.parseInt(tbProductos.getValueAt(tbProductos.getSelectedRow(), i).toString());
					tbProductos.setValueAt(conteoold+1, tbProductos.getSelectedRow(), i);
					//JOptionPane.showMessageDialog(null, "+1 \n\nConteo: " + (conteoold+1));
				}

			}
		}		
	}
	protected void mouseClickedMnEmpezarCero(MouseEvent arg0) {
		
		int opc = JOptionPane.showConfirmDialog(null, "Con esta opción su conteo realizado hasta ahora se podrá en cero.\n¿Continuar?", "Verificación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == 0) {
			int cantProductos = tbProductos.getRowCount();
			for (int j = 0; j < cantProductos; j++) {	
				for(int i=0; i<tbProductos.getColumnCount(); i++){
					if(tbProductos.getColumnName(i).equals("CONTEO")){
							tbProductos.setValueAt(0, j, i);
						}
				}
			}
		}
		else{
			
		}
	}
	protected void mouseClickedMncargarltimoRegistro(MouseEvent arg0) {
		int opc = JOptionPane.showConfirmDialog(null, "Con esta opción, cargará su último conteo guardado, para que pueda revisarlo o continuar modificandolo.\n¿Continuar?", "Verificación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (opc == 0) {
			
			consulta.iniciar();
			try {
				rs = consulta.ObtenerUltimoNroKardex();
				int ultnrokardex = 0;
				String nota = "";
				
				try {
					rs.next();
					ultnrokardex = rs.getInt("idkardex");
					nota = rs.getString("nota");
					txtNota.setText(nota);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "error al cargar ult nro de kardex " + e);
				}
				
				
				rs = consulta.cargarUltimoKardexYDetalle(ultnrokardex);
				int idprod = 0;
				String conteo = "";
				int cantProductos = tbProductos.getRowCount();
				while (rs.next()){
					idprod = rs.getInt("codproducto");
					conteo = rs.getString("registros");

					for (int j = 0; j < cantProductos; j++) {
						if(Integer.parseInt(tbProductos.getValueAt(j, 0).toString()) == idprod){
							
							for(int i=0; i<tbProductos.getColumnCount(); i++)
								if(tbProductos.getColumnName(i).equals("CONTEO"))
									tbProductos.setValueAt(conteo, j, i);
							
						}
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al recorrer productos de kardex " + e);
			}
			JOptionPane.showMessageDialog(null, "Cargado correctamente");
		}
		else{
			
		}
	}
	protected void mouseClickedMnfusionar(MouseEvent arg0) {
		int opc = JOptionPane.showConfirmDialog(null, "ALERTA: Con esta opción, el conteo realizado a cada producto, sobreescribirá el stock de este.\nNo hay manera de deshacer esta operación\n\n¿Continuar?", "Verificación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (opc == 0) {

			int cantProductos = tbProductos.getRowCount();
			for (int j = 0; j < cantProductos; j++) {

				float conteo = 0;
				int codproducto = 0;
				for(int i=0; i<tbProductos.getColumnCount(); i++){
					if(tbProductos.getColumnName(i).equals("CONTEO")){
						
						conteo = Float.parseFloat(tbProductos.getValueAt(j, i).toString());
						codproducto = Integer.parseInt(tbProductos.getValueAt(j, 0).toString());
						
						model.iniciar();
						try {
							model.FusionarConteoKardex(codproducto, conteo);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error al fusionar " + e);
						}finally {
							try {
								if (rs != null)
									rs.close();
								if (model != null)
									model.reset();
				            } catch (Exception ex) {
				            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
				            }
						}
						
						this.dispose();
					}
				}
			}
				JOptionPane.showMessageDialog(null, "Operación exitosa.");
			
		}
		else{
			
		}
	}
	
	protected void mouseClickedMnValorMonetario(MouseEvent arg0) {
		//System.out.println(tbProductos);
		vValorMonetario = new ValorMonetarioKardex(tbProductos);
		try {
			if (!vValorMonetario.isShowing()) {
				vValorMonetario.setLocationRelativeTo(null);
				vValorMonetario.setVisible(true);
			} else {
				//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				vValorMonetario.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				vValorMonetario.setVisible(true); 
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
}




