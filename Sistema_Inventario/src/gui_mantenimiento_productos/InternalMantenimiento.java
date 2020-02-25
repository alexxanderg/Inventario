package gui_mantenimiento_productos;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.mxrck.autocompleter.TextAutoCompleter;

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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class InternalMantenimiento extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu_2;
	private JMenu mnIngresarStockA;
	private JButton btnX;
	private JLabel lblCdigo;
	private JTextField txtCodigo;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	private JTable tbProductos;
	
	
	JTable tb;
	ResultSet rs;
	String usuario;
	consultas model = new consultas();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalMantenimiento frame = new InternalMantenimiento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InternalMantenimiento() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("ALMAC\u00C9N");
		setBounds(100, 100, 1119, 679);
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
		btnX.setBounds(1040, 0, 63, 30);
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
		this.scrollPane.setBounds(10, 90, 1083, 519);
		getContentPane().add(this.scrollPane);
		
		tbProductos = new JTable();
		tbProductos.setFont(new Font("Tw Cen MT", Font.ITALIC, 17));
		tbProductos.setBackground(Color.WHITE);
		tbProductos.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tbProductos);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbProductos.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Crear nuevo producto");
		mnNewMenu.setForeground(new Color(30, 144, 255));
		mnNewMenu.setBackground(SystemColor.control);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNewMenu);
		
		mnNewMenu_1 = new JMenu("Modificar producto");
		mnNewMenu_1.setForeground(new Color(60, 179, 113));
		mnNewMenu_1.setBackground(SystemColor.control);
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNewMenu_1);
		
		mnNewMenu_2 = new JMenu("Eliminar producto");
		mnNewMenu_2.setForeground(new Color(220, 20, 60));
		mnNewMenu_2.setBackground(SystemColor.control);
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNewMenu_2);
		
		mnIngresarStockA = new JMenu("Ingresar stock a producto");
		mnIngresarStockA.setForeground(new Color(255, 140, 0));
		mnIngresarStockA.setBackground(SystemColor.control);
		mnIngresarStockA.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnIngresarStockA);

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
				list.add("FE. VENC");
		}
		list.add("CATEGORIA");
		list.add("ALMACÉN");
		list.add("STOCK");
		list.add("PRE CO");
		list.add("PRE VE");
		String[] columnas = list.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
		/*dtm.setColumnIdentifiers(new Object[] { "Codigo", "Producto", "Detalle","Categoría", "Marca", "Color",
				"F. Vencimiento", "Uni. Medida", "Cantidad", "PrecioCompra", "PrecioVenta" });*/
		dtm.setColumnIdentifiers(columnas);
		
		consultas model = new consultas();
		rs = model.cargarProductos();

		List<String> listProds = new ArrayList<String>();
		try {
			while (rs.next()){
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
					if(parts[x].equals("fvencimiento"))
						listProds.add(rs.getString("fechaVenc"));
				}
		        listProds.add(rs.getString("categoria"));
		        listProds.add(rs.getString("almacen"));
		        listProds.add(rs.getString("cantidad"));
		        listProds.add(rs.getString("precioCo"));
		        listProds.add(rs.getString("precioVe"));
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS2: " + e);
		}

		String[] columnasProds = listProds.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
		dtm.addRow(columnasProds);
		
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
		
		/*DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(5).setCellRenderer(tcr2);*/
	}

	protected void actionPerformedBtnX(ActionEvent arg0) {
		try {
			this.setClosed(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
