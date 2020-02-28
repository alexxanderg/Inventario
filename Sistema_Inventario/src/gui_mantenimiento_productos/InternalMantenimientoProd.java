package gui_mantenimiento_productos;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.mxrck.autocompleter.TextAutoCompleter;

import gui_configuracion.Configuraciones;
import gui_principal.VentanaPrincipal;
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

public class InternalMantenimientoProd extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnCrearProducto;
	private JMenu mnModificarProducto;
	private JMenu mnNewMenu_2;
	private JMenu mnIngresarStockA;
	private JButton btnX;
	private JLabel lblCdigo;
	private JTextField txtCodigo;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	private JTable tbProductos;
	
	public VentanaPrincipal vp;
	
	JTable tb;
	ResultSet rs;
	consultas model = new consultas();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalMantenimientoProd frame = new InternalMantenimientoProd(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InternalMantenimientoProd(VentanaPrincipal vp) {
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
		this.lblCdigo.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblCdigo.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		this.lblCdigo.setBounds(10, 41, 138, 38);
		getContentPane().add(this.lblCdigo);
		
		this.txtCodigo = new JTextField();
		this.txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtCodigo.setFont(new Font("Swis721 LtEx BT", Font.BOLD | Font.ITALIC, 20));
		this.txtCodigo.setColumns(10);
		this.txtCodigo.setBackground(SystemColor.controlHighlight);
		this.txtCodigo.setBounds(139, 45, 954, 34);
		getContentPane().add(this.txtCodigo);
		
		this.scrollPane = new JScrollPane();
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
		mnCrearProducto.setForeground(new Color(65, 105, 225));
		mnCrearProducto.setBackground(SystemColor.control);
		mnCrearProducto.setFont(new Font("Arial", Font.BOLD, 22));
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
		mnModificarProducto.setFont(new Font("Arial", Font.BOLD, 22));
		menuBar.add(mnModificarProducto);
		
		mnNewMenu_2 = new JMenu("|Eliminar producto| ");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnNewMenu_2(e);
			}
		});
		mnNewMenu_2.setForeground(new Color(220, 20, 60));
		mnNewMenu_2.setBackground(SystemColor.control);
		mnNewMenu_2.setFont(new Font("Arial", Font.BOLD, 22));
		menuBar.add(mnNewMenu_2);
		
		mnIngresarStockA = new JMenu("|Agregar stock| ");
		mnIngresarStockA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnIngresarStockA(e);
			}
		});
		mnIngresarStockA.setForeground(new Color(75, 0, 130));
		mnIngresarStockA.setBackground(SystemColor.control);
		mnIngresarStockA.setFont(new Font("Arial", Font.BOLD, 22));
		menuBar.add(mnIngresarStockA);
		
		mnOtrasOpciones = new JMenu("|Otras opciones|");
		mnOtrasOpciones.setForeground(new Color(255, 69, 0));
		mnOtrasOpciones.setFont(new Font("Arial", Font.BOLD, 22));
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
		tb.setRowHeight(25);
		tb.setModel(dtm);
		
		// CARGAR ATRIBUTOS EN TABLA
		String atribTodos = "";
		try {
			rs = model.cargarAtributosProd();
			rs.next();
			atribTodos = rs.getString("atributosprod");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar atributos: " + e);
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
		list.add("STOCK");
		list.add("PREC CO");
		list.add("% GAN");
		list.add("PREC VE");
		String[] columnas = list.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
		/*dtm.setColumnIdentifiers(new Object[] { "Codigo", "Producto", "Detalle","Categoría", "Marca", "Color",
				"F. Vencimiento", "Uni. Medida", "Cantidad", "PrecioCompra", "PrecioVenta" });*/
		dtm.setColumnIdentifiers(columnas);
		
		consultas model = new consultas();
		rs = model.cargarProductos();

		try {
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
		        listProds.add(rs.getString("cantidad"));
		        listProds.add(rs.getString("precioCo"));
		        listProds.add(rs.getString("ptjganancia"));
		        listProds.add(rs.getString("precioVe"));
		        
		        String[] columnasProds = listProds.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
				dtm.addRow(columnasProds); // AGREGAMOS EL PRODUCTO A LA LISTA
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS2: " + e);
		}
		ajustarAnchoColumnas();
	}
	
	
	public void cargarBuscador() {
		ac = new TextAutoCompleter(txtCodigo);
		consultas model = new consultas();
		ResultSet rs = model.cargarProductos();
		ac.setMode(0);
		try {
			while (rs.next()) {
				// ac.addItem(rs.getString("codproducto"));
				ac.addItem(rs.getString("producto") + "_" + rs.getString("detalles"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
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
	
	NuevoProducto2 np = new NuevoProducto2(this);
	private JMenu mnOtrasOpciones;
	private JMenuItem mntmRealizarKardex;
	private JMenuItem mntmVerHistorial;
	protected void mouseClickedMnCrearProducto(MouseEvent arg0) {
		try {
			if (np.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				np.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				np.setVisible(true); 
			} else {
				np = new NuevoProducto2(this);
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
		try {		
			ModificarProducto2 mp = new ModificarProducto2(codigoProducto,this);;
			try { 
				if (mp.isShowing()) {
					//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
					mp.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
					mp.setVisible(true); 
				} else {
					mp.setLocationRelativeTo(null);
					mp.setVisible(true);
				}
			} catch (Exception f) {
				JOptionPane.showMessageDialog(null, "Error: " + f);
			}
		} catch(Exception e1){
			JOptionPane.showMessageDialog(null, "Seleccione el producto a modificar " + e);
		}		
	}
	protected void mouseClickedMnNewMenu_2(MouseEvent e) {
		DefaultTableModel tm = (DefaultTableModel) tbProductos.getModel();
		String codigoProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 0));
		
		JOptionPane.showMessageDialog(null, "No elimina, solo cambia de estado al producto a 1 y vuelve a cargar la tabla");
		int opc = JOptionPane.showConfirmDialog(null, "¿Seguro de querer DESHABILITAR ESTE PRODUCTO?", "Confirmación", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opc == 0) {
			model.deshabilitarProducto(Integer.parseInt(codigoProducto));
			cargar();
		}else{
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
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
}
