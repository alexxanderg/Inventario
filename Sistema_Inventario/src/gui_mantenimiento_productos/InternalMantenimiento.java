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
		
		
		cargarDatos();
		cargarBuscador();
		ajustarAnchoColumnas();
	}
	protected void actionPerformedBtnX(ActionEvent arg0) {
		try {
			this.setClosed(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void cargarDatos() {
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbProductos;
		tb.setRowHeight(25);
		tb.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[] { "Codigo", "Producto", "Detalle","Categoría", "Marca", "Color",
				"F. Vencimiento", "Uni. Medida", "Cantidad", "PrecioCompra", "PrecioVenta" });
		consultas model = new consultas();
		rs = model.cargarProductos();
		try {
			while (rs.next())
				dtm.addRow(
						new Object[] { rs.getString("codproducto"), rs.getString("producto"), rs.getString("detalles"),rs.getString("categoria"),
								rs.getString("marca"), rs.getString("color"),rs.getString("fechaVenc"),
								rs.getString("unimedida"),
								rs.getFloat("cantidad"), rs.getFloat("precioCo"), rs.getFloat("precioVe") });
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
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
		/*TableColumnModel tcm = tbProductos.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(8)); // Codigo
		tcm.getColumn(1).setPreferredWidth(anchoColumna(12)); // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8)); // Detalle
		tcm.getColumn(3).setPreferredWidth(anchoColumna(8)); // Categoria
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8)); // Marca
		tcm.getColumn(5).setPreferredWidth(anchoColumna(7)); // Color
		tcm.getColumn(6).setPreferredWidth(anchoColumna(7)); // Laboratorio
		tcm.getColumn(7).setPreferredWidth(anchoColumna(7)); // F. Vencimiento
		tcm.getColumn(8).setPreferredWidth(anchoColumna(7)); // Nto Lote
		tcm.getColumn(9).setPreferredWidth(anchoColumna(7)); // Uni. Medida
		tcm.getColumn(10).setPreferredWidth(anchoColumna(7)); // Cantidad
		tcm.getColumn(11).setPreferredWidth(anchoColumna(7)); // PrecioCompra
		tcm.getColumn(12).setPreferredWidth(anchoColumna(7)); // PrecioVenta
*//*
		DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(5).setCellRenderer(tcr2);

		DefaultTableCellRenderer tcr3 = new DefaultTableCellRenderer();
		tcr3.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(6).setCellRenderer(tcr3);

		DefaultTableCellRenderer tcr4 = new DefaultTableCellRenderer();
		tcr3.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(7).setCellRenderer(tcr4);

		DefaultTableCellRenderer tcr5 = new DefaultTableCellRenderer();
		tcr3.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(8).setCellRenderer(tcr5);*/
	}
}
