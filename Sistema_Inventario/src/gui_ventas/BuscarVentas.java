package gui_ventas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
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
import java.util.Date;
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
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import clases.Cliente;
import clases.Usuarios;

public class BuscarVentas extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnCrearProducto;
	private JMenu mnModificarProducto;
	private JMenu mnNewMenu_2;
	private JButton btnX;
	private JScrollPane scrollPane;
	private JTable tbVentas;
	private JButton btnVerVentas;
	private JComboBox <Usuarios>cbUsuarios;
	private JLabel lblVendedor;
	private JLabel lblDesde;
	private JDateChooser dchDesde;
	private JLabel lblHasta;
	private JDateChooser dchHasta;
	
	public VentanaPrincipal vp;	
	JTable tb;
	ResultSet rs;
	consultas model = new consultas();
	DefaultTableModel dtm = new DefaultTableModel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarVentas frame = new BuscarVentas(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BuscarVentas(VentanaPrincipal vp) {
		this.vp = vp;
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("HISTORIAL DE VENTAS");
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
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 72, 1083, 537);
		getContentPane().add(this.scrollPane);
		
		tbVentas = new JTable();
		tbVentas.setAutoCreateRowSorter(true);
		tbVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbVentas.setFont(new Font("Arial", Font.ITALIC, 14));
		tbVentas.setBackground(Color.WHITE);
		tbVentas.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbVentas);
		
		btnVerVentas = new JButton("Filtrar");
		btnVerVentas.setBackground(new Color(30, 144, 255));
		btnVerVentas.setForeground(Color.WHITE);
		btnVerVentas.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVerVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnVerVentas(arg0);
			}
		});
		btnVerVentas.setBounds(814, 31, 110, 30);
		getContentPane().add(btnVerVentas);
		
		cbUsuarios = new JComboBox();
		cbUsuarios.setFont(new Font("Arial", Font.ITALIC, 18));
		cbUsuarios.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbUsuarios.setBackground(new Color(245, 245, 245));
		cbUsuarios.setBounds(110, 31, 227, 30);
		getContentPane().add(cbUsuarios);
		
		lblVendedor = new JLabel("Vendedor:");
		lblVendedor.setForeground(Color.DARK_GRAY);
		lblVendedor.setFont(new Font("Candara", Font.BOLD, 20));
		lblVendedor.setBounds(10, 31, 110, 30);
		getContentPane().add(lblVendedor);
		
		lblDesde = new JLabel("Desde:");
		lblDesde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesde.setForeground(new Color(102, 205, 170));
		lblDesde.setFont(new Font("Candara", Font.BOLD, 20));
		lblDesde.setBackground(new Color(50, 205, 50));
		lblDesde.setBounds(351, 31, 71, 30);
		getContentPane().add(lblDesde);
		
		dchDesde = new JDateChooser();
		dchDesde.setBounds(432, 31, 130, 30);
		getContentPane().add(dchDesde);
		
		lblHasta = new JLabel("Hasta:");
		lblHasta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHasta.setForeground(new Color(102, 205, 170));
		lblHasta.setFont(new Font("Candara", Font.BOLD, 20));
		lblHasta.setBackground(new Color(50, 205, 50));
		lblHasta.setBounds(572, 31, 71, 30);
		getContentPane().add(lblHasta);
		
		dchHasta = new JDateChooser();
		dchHasta.setBounds(648, 31, 130, 30);
		getContentPane().add(dchHasta);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbVentas.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);
		
		mnCrearProducto = new JMenu("|Crear nuevo usuario| ");
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
		
		mnModificarProducto = new JMenu("|Modificar usuario| ");
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
		
		mnNewMenu_2 = new JMenu("|Eliminar usuario| ");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnNewMenu_2(e);
			}
		});
		mnNewMenu_2.setForeground(new Color(220, 20, 60));
		mnNewMenu_2.setBackground(SystemColor.control);
		mnNewMenu_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnNewMenu_2);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
	}
	
	public void cargar() {
		tb = this.tbVentas;
		tb.setRowHeight(30);
		tb.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[]{"NRO", "CLIENTE", "VENDEDOR", "NOTA", "FECHA", "DESCUENTO", "SALDO", "TOTAL"});
		
		Usuarios vendedores = new Usuarios();
		vendedores.cargarUsuarios(cbUsuarios);

		//JOptionPane.showMessageDialog(null, vp.lblIdusuario.getText());
		int idUsuario = Integer.parseInt(vp.lblIdusuario.getText());
		
		for(int i = 0; i<cbUsuarios.getItemCount(); i++)
			if(cbUsuarios.getItemAt(i).getIdusuario() == idUsuario)
				cbUsuarios.setSelectedIndex(i);
		
		java.util.Date date = new Date();
		date.getTime();
		dchDesde.setDate(date);
		dchHasta.setDate(date);
		
	}
		
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbVentas.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(7));  // 
		tcm.getColumn(1).setPreferredWidth(anchoColumna(20));  // 
		tcm.getColumn(2).setPreferredWidth(anchoColumna(20));  // 
		tcm.getColumn(3).setPreferredWidth(anchoColumna(17));  // 
		tcm.getColumn(4).setPreferredWidth(anchoColumna(18));  // 
		tcm.getColumn(5).setPreferredWidth(anchoColumna(6));
		tcm.getColumn(6).setPreferredWidth(anchoColumna(6));
		tcm.getColumn(7).setPreferredWidth(anchoColumna(6));
		
		DefaultTableCellRenderer tcr0 = new DefaultTableCellRenderer();
		tcr0.setHorizontalAlignment(SwingConstants.CENTER);
		tbVentas.getColumnModel().getColumn(0).setCellRenderer(tcr0);
		tbVentas.getColumnModel().getColumn(4).setCellRenderer(tcr0);
		tbVentas.getColumnModel().getColumn(5).setCellRenderer(tcr0);
		tbVentas.getColumnModel().getColumn(6).setCellRenderer(tcr0);
		tbVentas.getColumnModel().getColumn(7).setCellRenderer(tcr0);
	}

	protected void actionPerformedBtnX(ActionEvent arg0) {
		try {
			this.setClosed(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	public void selecionarUsuario(String id) {
	}
	
	protected void mouseClickedMnCrearProducto(MouseEvent arg0) {
		
	}
	protected void mouseClickedMnModificarProducto(MouseEvent e) {
		
	}
	
	protected void mouseClickedMnNewMenu_2(MouseEvent e) {
		
	}
	protected void actionPerformedBtnVerVentas(ActionEvent arg0) {
		consultas model = new consultas();
		rs = model.cargarVentasUsuario();
		try {
			while(rs.next()){
				dtm.addRow(new Object[]{rs.getInt("codventa"), rs.getString("ncliente"), rs.getString("nusuario"), rs.getString("nota"), rs.getString("fecha"), rs.getFloat("descuento"), rs.getFloat("saldo"), rs.getFloat("totventa")});	
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar ventas: " + e.getMessage());
		}
		ajustarAnchoColumnas();
	}
}
