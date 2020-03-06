package gui_configuracion;

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
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Configuraciones extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu_2;
	private JMenu mnIngresarStockA;
	private JButton btnX;
	private TextAutoCompleter ac;
	
	
	JTable tb;
	ResultSet rs;
	String usuario;
	consultas model = new consultas();
	private JLabel lblNewLabel;
	private JPanel panelAtributos;
	private JCheckBox chckbxMarca;
	private JCheckBox chckbxColor;
	private JCheckBox chckbxLote;
	private JCheckBox chckbxLaboratorio;
	private JCheckBox chckbxFechaVencimiento;
	private JCheckBox chckbxPromocion1;
	private JCheckBox chckbxPromocion2;
	private JButton btnGuardarAtributos;
	private JPanel panel;
	private JLabel lblPermitirSeguirVendiendo;
	private JButton btnVenderSinStock;
	private JComboBox cbVenderSinStock;
	private JPanel panel_1;
	private JLabel lblreducirStockAl;
	private JButton btnReducirAlVender;
	private JComboBox cbReducirAlVender;
	private JPanel panel_2;
	private JLabel lblpermitirModificarFecha;
	private JButton btnModifFecha;
	private JComboBox cbModifFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuraciones frame = new Configuraciones();
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
	public Configuraciones() {
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
		
		panelAtributos = new JPanel();
		panelAtributos.setBackground(Color.LIGHT_GRAY);
		panelAtributos.setBounds(0, 11, 551, 283);
		getContentPane().add(panelAtributos);
		panelAtributos.setLayout(null);
		
		lblNewLabel = new JLabel("Seleccione los atributos que desee controlar de sus productos:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 519, 26);
		panelAtributos.add(lblNewLabel);
		
		chckbxMarca = new JCheckBox("Marca");
		chckbxMarca.setBackground(Color.LIGHT_GRAY);
		chckbxMarca.setBounds(10, 44, 97, 23);
		panelAtributos.add(chckbxMarca);
		
		chckbxColor = new JCheckBox("Color");
		chckbxColor.setBackground(Color.LIGHT_GRAY);
		chckbxColor.setBounds(136, 44, 97, 23);
		panelAtributos.add(chckbxColor);
		
		chckbxLote = new JCheckBox("Lote");
		chckbxLote.setBackground(Color.LIGHT_GRAY);
		chckbxLote.setBounds(308, 44, 97, 23);
		panelAtributos.add(chckbxLote);
		
		chckbxLaboratorio = new JCheckBox("Laboratorio");
		chckbxLaboratorio.setBackground(Color.LIGHT_GRAY);
		chckbxLaboratorio.setBounds(10, 82, 97, 23);
		panelAtributos.add(chckbxLaboratorio);
		
		chckbxFechaVencimiento = new JCheckBox("Fecha Vencimiento");
		chckbxFechaVencimiento.setBackground(Color.LIGHT_GRAY);
		chckbxFechaVencimiento.setBounds(136, 82, 171, 23);
		panelAtributos.add(chckbxFechaVencimiento);
		
		chckbxPromocion1 = new JCheckBox("Promoci\u00F3n 1");
		chckbxPromocion1.setBackground(Color.LIGHT_GRAY);
		chckbxPromocion1.setBounds(10, 126, 97, 23);
		panelAtributos.add(chckbxPromocion1);
		
		chckbxPromocion2 = new JCheckBox("Promoci\u00F3n 2");
		chckbxPromocion2.setBackground(Color.LIGHT_GRAY);
		chckbxPromocion2.setBounds(136, 126, 97, 23);
		panelAtributos.add(chckbxPromocion2);
		
		btnGuardarAtributos = new JButton("Guardar");
		btnGuardarAtributos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGuardarAtributos(e);
			}
		});
		btnGuardarAtributos.setBackground(new Color(220, 20, 60));
		btnGuardarAtributos.setBounds(94, 200, 256, 36);
		panelAtributos.add(btnGuardarAtributos);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(563, 11, 540, 283);
		getContentPane().add(panel);
		
		lblPermitirSeguirVendiendo = new JLabel("Permitir seguir vendiendo cuando no haya stock?");
		lblPermitirSeguirVendiendo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPermitirSeguirVendiendo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPermitirSeguirVendiendo.setBounds(-32, 47, 519, 26);
		panel.add(lblPermitirSeguirVendiendo);
		
		btnVenderSinStock = new JButton("Guardar");
		btnVenderSinStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnVenderSinStock(arg0);
			}
		});
		btnVenderSinStock.setBackground(new Color(220, 20, 60));
		btnVenderSinStock.setBounds(94, 200, 256, 36);
		panel.add(btnVenderSinStock);
		
		cbVenderSinStock = new JComboBox();
		cbVenderSinStock.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		cbVenderSinStock.setBounds(94, 107, 256, 26);
		panel.add(cbVenderSinStock);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(0, 306, 551, 283);
		getContentPane().add(panel_1);
		
		lblreducirStockAl = new JLabel("\u00BFReducir stock al vender?");
		lblreducirStockAl.setHorizontalAlignment(SwingConstants.CENTER);
		lblreducirStockAl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblreducirStockAl.setBounds(-32, 47, 519, 26);
		panel_1.add(lblreducirStockAl);
		
		btnReducirAlVender = new JButton("Guardar");
		btnReducirAlVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnReducirAlVender(e);
			}
		});
		btnReducirAlVender.setBackground(new Color(220, 20, 60));
		btnReducirAlVender.setBounds(94, 200, 256, 36);
		panel_1.add(btnReducirAlVender);
		
		cbReducirAlVender = new JComboBox();
		cbReducirAlVender.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		cbReducirAlVender.setBounds(94, 107, 256, 26);
		panel_1.add(cbReducirAlVender);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(563, 306, 540, 283);
		getContentPane().add(panel_2);
		
		lblpermitirModificarFecha = new JLabel("\u00BFPermitir modificar fecha al vender?");
		lblpermitirModificarFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblpermitirModificarFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpermitirModificarFecha.setBounds(-32, 47, 519, 26);
		panel_2.add(lblpermitirModificarFecha);
		
		btnModifFecha = new JButton("Guardar");
		btnModifFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnModifFecha(e);
			}
		});
		btnModifFecha.setBackground(new Color(220, 20, 60));
		btnModifFecha.setBounds(94, 200, 256, 36);
		panel_2.add(btnModifFecha);
		
		cbModifFecha = new JComboBox();
		cbModifFecha.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		cbModifFecha.setBounds(94, 107, 256, 26);
		panel_2.add(cbModifFecha);

		
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
	}
	
	private void cargar(){
		String atribTodos = "";
		int ventasinstock = 0;
		int reducirstock = 0;
		int fechaVauto = 0;
		try {
			rs = model.cargarConfiguraciones();
			rs.next();
			atribTodos = rs.getString("atributosprod");
			ventasinstock = rs.getInt("ventasinstock");
			reducirstock = rs.getInt("reducirstock");
			fechaVauto = rs.getInt("fechaVauto");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar atributos: " + e);
		}

		String[] parts = atribTodos.split(",");
		for (int x=0; x<parts.length; x++){
			if(parts[x].equals("marca"))
				chckbxMarca.setSelected(true);
			if(parts[x].equals("color"))
				chckbxColor.setSelected(true);
			if(parts[x].equals("lote"))
				chckbxLote.setSelected(true);
			if(parts[x].equals("laboratorio"))
				chckbxLaboratorio.setSelected(true);
			if(parts[x].equals("fvencimiento"))
				chckbxFechaVencimiento.setSelected(true);
			if(parts[x].equals("promo1"))
				chckbxPromocion1.setSelected(true);
			if(parts[x].equals("promo2"))
				chckbxPromocion2.setSelected(true);
		}
		cbVenderSinStock.setSelectedIndex(ventasinstock);
		cbReducirAlVender.setSelectedIndex(reducirstock);
		cbModifFecha.setSelectedIndex(fechaVauto);
	}
	
	protected void actionPerformedBtnX(ActionEvent arg0) {
		try {
			this.setClosed(true);
		} catch (PropertyVetoException e) {
		}
	}
	
	protected void actionPerformedBtnGuardarAtributos(ActionEvent e) {
		String atributos = "";
		
		if(chckbxMarca.isSelected())
			atributos = atributos + "marca,";
		if(chckbxColor.isSelected())
			atributos = atributos + "color,";
		if(chckbxLote.isSelected())
			atributos = atributos + "lote,";
		if(chckbxLaboratorio.isSelected())
			atributos = atributos + "laboratorio,";
		if(chckbxFechaVencimiento.isSelected())
			atributos = atributos + "fvencimiento,";
		if(chckbxPromocion1.isSelected())
			atributos = atributos + "promo1,";
		if(chckbxPromocion2.isSelected())
			atributos = atributos + "promo2,";
		
		try {
			model.modificarAtributosProductos(atributos);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al modificar atributos de productos: " + e2);
		}
	}
	protected void actionPerformedBtnVenderSinStock(ActionEvent arg0) {
		int eleccion = cbVenderSinStock.getSelectedIndex();
		try {
			model.modificarVentaSinStock(eleccion);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al modificar venta sin stock: " + e2);
		}	
	}
	protected void actionPerformedBtnReducirAlVender(ActionEvent e) {
		int eleccion = cbReducirAlVender.getSelectedIndex();
		try {
			model.modificarReducirAlVender(eleccion);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al modificar Reduccion al vender: " + e2);
		}	
	}
	protected void actionPerformedBtnModifFecha(ActionEvent e) {
		int eleccion = cbModifFecha.getSelectedIndex();
		try {
			model.modificarFechaAlVender(eleccion);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al modificar Modificar fecha al vender: " + e2);
		}	
	}
}
