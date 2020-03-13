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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	private JMenu mnhistorialDeAcciones;
	private JLabel lblTV;
	private JLabel lblTotVentas;
	private JLabel lblTotDescuentos;
	private JLabel lblTD;
	
	public VentanaPrincipal vp;	
	JTable tb;
	ResultSet rs;
	consultas consulta = new consultas();
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
		this.scrollPane.setBounds(10, 72, 1083, 461);
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
		
		lblTV = new JLabel("TOTAL VENTAS S/ ");
		lblTV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTV.setForeground(new Color(30, 144, 255));
		lblTV.setFont(new Font("Candara", Font.BOLD, 30));
		lblTV.setBackground(new Color(50, 205, 50));
		lblTV.setBounds(687, 560, 255, 36);
		getContentPane().add(lblTV);
		
		lblTotVentas = new JLabel("0");
		lblTotVentas.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotVentas.setForeground(new Color(30, 144, 255));
		lblTotVentas.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTotVentas.setBackground(new Color(50, 205, 50));
		lblTotVentas.setBounds(952, 559, 141, 36);
		getContentPane().add(lblTotVentas);
		
		lblTotDescuentos = new JLabel("0");
		lblTotDescuentos.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotDescuentos.setForeground(new Color(205, 92, 92));
		lblTotDescuentos.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTotDescuentos.setBackground(new Color(50, 205, 50));
		lblTotDescuentos.setBounds(524, 559, 141, 36);
		getContentPane().add(lblTotDescuentos);
		
		lblTD = new JLabel("TOTAL DESCUENTOS S/ ");
		lblTD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTD.setForeground(new Color(205, 92, 92));
		lblTD.setFont(new Font("Candara", Font.BOLD, 30));
		lblTD.setBackground(new Color(50, 205, 50));
		lblTD.setBounds(170, 560, 344, 36);
		getContentPane().add(lblTD);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbVentas.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);
		
		mnCrearProducto = new JMenu("|Ver detalles de venta| ");
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
		
		mnModificarProducto = new JMenu("|Modificar Venta| ");
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
		
		mnNewMenu_2 = new JMenu("|Eliminar venta| ");
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
		
		mnhistorialDeAcciones = new JMenu("|Historial de acciones realizadas| ");
		mnhistorialDeAcciones.setForeground(new Color(186, 85, 211));
		mnhistorialDeAcciones.setFont(new Font("Tahoma", Font.BOLD, 20));
		mnhistorialDeAcciones.setBackground(SystemColor.menu);
		menuBar.add(mnhistorialDeAcciones);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
	}
	
	public void cargar() {
		tb = this.tbVentas;
		tb.setRowHeight(30);
		tb.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[]{"NRO", "CLIENTE", "VENDEDOR", "NOTA", "FECHA", "DESCUENTO", "SALDO", "TOTAL"});
		
		Usuarios todos = new Usuarios(0, "TODOS", "TODOS", "TODOS", 0);
		cbUsuarios.addItem(todos);
		
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
		try {
			
			for (int i = 0; i < tbVentas.getRowCount(); i++) {
				dtm.removeRow(i);
				i -= 1;
			}
			
			
			int idusuario = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getIdusuario();
						
			int añoi = dchDesde.getCalendar().get(Calendar.YEAR);
			int mesi = dchDesde.getCalendar().get(Calendar.MARCH) + 1;
			int diai = dchDesde.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaInicial = añoi + "-" + mesi + "-" + diai + " " + "00:00:00";

			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = (Date) formatter.parse(fechaInicial);
			Object fechai = new java.sql.Timestamp(date.getTime());
			
			int añof = dchHasta.getCalendar().get(Calendar.YEAR);
			int mesf = dchHasta.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = dchHasta.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaFinal = añof + "-" + mesf + "-" + diaf + " " + "23:59:59";

			DateFormat formatter2;
			formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date2 = (Date) formatter.parse(fechaFinal);
			Object fechaf = new java.sql.Timestamp(date2.getTime());
			
			consulta.iniciar();
			if(cbUsuarios.getSelectedIndex() == 0)
				rs = consulta.cargarVentasUsuarioTodos(fechai, fechaf);
			else
				rs = consulta.cargarVentasUsuario(idusuario, fechai, fechaf);
			
			while(rs.next()){
				dtm.addRow(new Object[]{rs.getInt("codventa"), rs.getString("ncliente"), rs.getString("nusuario"), rs.getString("nota"), rs.getString("fecha"), rs.getFloat("descuento"), rs.getFloat("saldo"), rs.getFloat("totventa")});	
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar ventas: " + e.getMessage());
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
		
		// CALCULAR TOTALES
		
		double sumTotal = 0;
		double sumDescuentos = 0;
		
		for (int i = 0; i < tbVentas.getRowCount(); i++) {
			sumTotal = sumTotal + Double.parseDouble(tbVentas.getValueAt(i, 7).toString());
			sumDescuentos = sumDescuentos + Double.parseDouble(tbVentas.getValueAt(i, 5).toString());
		}
		sumTotal = redondearDecimales(sumTotal, 2);
		sumDescuentos = redondearDecimales(sumDescuentos, 2);
		
		lblTotVentas.setText(""+sumTotal);
		lblTotDescuentos.setText(""+sumDescuentos);
	}
	
	public double redondearDecimales(double valorInicial, int numeroDecimales) {
		double parteEntera, resultado;
		resultado = valorInicial;
		parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
		resultado = Math.round(resultado);
		resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
		return resultado;
	}
	
}
