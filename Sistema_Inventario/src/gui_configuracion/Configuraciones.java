package gui_configuracion;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.mxrck.autocompleter.TextAutoCompleter;
import mysql.consultas;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class Configuraciones extends JInternalFrame {
	private JMenuBar menuBar;
	private JLabel lblNewLabel;
	private JCheckBox chckbxMarca;
	private JCheckBox chckbxColor;
	private JCheckBox chckbxLote;
	private JCheckBox chckbxLaboratorio;
	private JCheckBox chckbxFechaVencimiento;
	private JCheckBox chckbxPromocion1;
	private JCheckBox chckbxPromocion2;
	private JButton btnGuardarAtributos;
	private JLabel lblPermitirSeguirVendiendo;
	private JButton btnVenderSinStock;
	private JComboBox cbVenderSinStock;
	private JLabel lblreducirStockAl;
	private JButton btnReducirAlVender;
	private JComboBox cbReducirAlVender;
	private JLabel lblpermitirModificarFecha;
	private JButton btnModifFecha;
	private JComboBox cbModifFecha;

	
	private TextAutoCompleter ac;
	JTable tb;
	ResultSet rs;
	String usuario;
	consultas consulta = new consultas();
	private JLabel lblNombreDelNegocio;
	private JTextField textField;
	private JButton button;
	private JLabel lblImagenDelLogin;
	private JTextField textField_1;
	private JButton button_1;
	private JButton button_2;
	private JLabel lblImagenDelLogin_1;
	private JTextField textField_2;
	private JButton button_3;
	private JButton button_4;
	private JLabel lblImagenDelReporte;
	private JTextField textField_3;
	private JButton button_5;
	private JButton button_6;
	private JLabel lblLogopxpx;
	private JTextField textField_4;
	private JButton button_7;
	private JButton button_8;
	private JLabel lblDatosParaEl;
	private JLabel lblDireccin;
	private JTextField textField_5;
	private JButton button_10;
	private JLabel lblTelefono;
	private JTextField textField_6;
	private JButton button_12;
	private JLabel lblRuc;
	private JTextField textField_7;
	private JButton button_14;
	private JLabel lblPresentacin;
	private JTextField textField_8;
	private JButton button_15;


	
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


	public Configuraciones() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("CONFIGURACION");
		setBounds(100, 100, 1119, 679);
		getContentPane().setLayout(null);
		
		btnModifFecha = new JButton("Guardar");
		btnModifFecha.setBounds(730, 465, 180, 27);
		getContentPane().add(btnModifFecha);
		btnModifFecha.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnModifFecha.setForeground(Color.WHITE);
		btnModifFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnModifFecha(e);
			}
		});
		btnModifFecha.setBackground(new Color(30, 144, 255));
		
		cbModifFecha = new JComboBox();
		cbModifFecha.setBounds(510, 465, 191, 26);
		getContentPane().add(cbModifFecha);
		cbModifFecha.setFont(new Font("Candara", Font.PLAIN, 18));
		cbModifFecha.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		
		lblpermitirModificarFecha = new JLabel("\u00BFPermitir modificar fecha al vender?");
		lblpermitirModificarFecha.setBounds(510, 431, 400, 26);
		getContentPane().add(lblpermitirModificarFecha);
		lblpermitirModificarFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblpermitirModificarFecha.setFont(new Font("Candara", Font.BOLD, 20));
		
		btnVenderSinStock = new JButton("Guardar");
		btnVenderSinStock.setBounds(730, 379, 180, 27);
		getContentPane().add(btnVenderSinStock);
		btnVenderSinStock.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVenderSinStock.setForeground(Color.WHITE);
		btnVenderSinStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnVenderSinStock(arg0);
			}
		});
		btnVenderSinStock.setBackground(new Color(30, 144, 255));
		
		cbVenderSinStock = new JComboBox();
		cbVenderSinStock.setBounds(510, 379, 191, 26);
		getContentPane().add(cbVenderSinStock);
		cbVenderSinStock.setFont(new Font("Candara", Font.PLAIN, 18));
		cbVenderSinStock.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		
		lblPermitirSeguirVendiendo = new JLabel("Permitir seguir vendiendo cuando no haya stock?");
		lblPermitirSeguirVendiendo.setBounds(510, 346, 451, 26);
		getContentPane().add(lblPermitirSeguirVendiendo);
		lblPermitirSeguirVendiendo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPermitirSeguirVendiendo.setFont(new Font("Candara", Font.BOLD, 20));
		
		lblNewLabel = new JLabel("Seleccione los atributos que desee manejar");
		lblNewLabel.setBounds(500, 11, 400, 26);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 20));
		
		chckbxMarca = new JCheckBox("Marca");
		chckbxMarca.setBounds(500, 44, 135, 23);
		getContentPane().add(chckbxMarca);
		chckbxMarca.setForeground(Color.DARK_GRAY);
		chckbxMarca.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxMarca.setBackground(Color.WHITE);
		
		chckbxColor = new JCheckBox("Color");
		chckbxColor.setBounds(500, 70, 97, 23);
		getContentPane().add(chckbxColor);
		chckbxColor.setForeground(Color.DARK_GRAY);
		chckbxColor.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxColor.setBackground(Color.WHITE);
		
		chckbxFechaVencimiento = new JCheckBox("Fecha Vencimiento");
		chckbxFechaVencimiento.setBounds(500, 96, 171, 23);
		getContentPane().add(chckbxFechaVencimiento);
		chckbxFechaVencimiento.setForeground(Color.DARK_GRAY);
		chckbxFechaVencimiento.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxFechaVencimiento.setBackground(Color.WHITE);
		
		chckbxLote = new JCheckBox("Lote");
		chckbxLote.setBounds(500, 122, 97, 23);
		getContentPane().add(chckbxLote);
		chckbxLote.setForeground(Color.DARK_GRAY);
		chckbxLote.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxLote.setBackground(Color.WHITE);
		
		chckbxLaboratorio = new JCheckBox("Laboratorio");
		chckbxLaboratorio.setBounds(500, 148, 135, 23);
		getContentPane().add(chckbxLaboratorio);
		chckbxLaboratorio.setForeground(Color.DARK_GRAY);
		chckbxLaboratorio.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxLaboratorio.setBackground(Color.WHITE);
		
		chckbxPromocion1 = new JCheckBox("Promoci\u00F3n 1");
		chckbxPromocion1.setBounds(500, 174, 135, 23);
		getContentPane().add(chckbxPromocion1);
		chckbxPromocion1.setForeground(Color.DARK_GRAY);
		chckbxPromocion1.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxPromocion1.setBackground(Color.WHITE);
		
		chckbxPromocion2 = new JCheckBox("Promoci\u00F3n 2");
		chckbxPromocion2.setBounds(500, 200, 171, 23);
		getContentPane().add(chckbxPromocion2);
		chckbxPromocion2.setForeground(Color.DARK_GRAY);
		chckbxPromocion2.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxPromocion2.setBackground(Color.WHITE);
		
		btnGuardarAtributos = new JButton("Guardar");
		btnGuardarAtributos.setBounds(730, 200, 180, 25);
		getContentPane().add(btnGuardarAtributos);
		btnGuardarAtributos.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnGuardarAtributos.setForeground(Color.WHITE);
		btnGuardarAtributos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGuardarAtributos(e);
			}
		});
		btnGuardarAtributos.setBackground(new Color(30, 144, 255));
		
		lblreducirStockAl = new JLabel("\u00BFReducir stock al vender?");
		lblreducirStockAl.setBounds(500, 267, 230, 26);
		getContentPane().add(lblreducirStockAl);
		lblreducirStockAl.setHorizontalAlignment(SwingConstants.LEFT);
		lblreducirStockAl.setFont(new Font("Candara", Font.BOLD, 20));
		
		cbReducirAlVender = new JComboBox();
		cbReducirAlVender.setFont(new Font("Candara", Font.PLAIN, 18));
		cbReducirAlVender.setBounds(510, 298, 191, 26);
		getContentPane().add(cbReducirAlVender);
		cbReducirAlVender.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		
		btnReducirAlVender = new JButton("Guardar");
		btnReducirAlVender.setBounds(730, 298, 180, 27);
		getContentPane().add(btnReducirAlVender);
		btnReducirAlVender.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReducirAlVender.setForeground(Color.WHITE);
		btnReducirAlVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnReducirAlVender(e);
			}
		});
		btnReducirAlVender.setBackground(new Color(30, 144, 255));
		
		lblNombreDelNegocio = new JLabel("Nombre del Negocio");
		lblNombreDelNegocio.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreDelNegocio.setFont(new Font("Candara", Font.BOLD, 20));
		lblNombreDelNegocio.setBounds(10, 11, 279, 26);
		getContentPane().add(lblNombreDelNegocio);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setForeground(SystemColor.windowBorder);
		textField.setFont(new Font("Arial", Font.ITALIC, 18));
		textField.setColumns(10);
		textField.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		textField.setBackground(new Color(245, 245, 245));
		textField.setBounds(10, 44, 404, 23);
		getContentPane().add(textField);
		
		button = new JButton("Guardar");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 20));
		button.setBackground(new Color(30, 144, 255));
		button.setBounds(234, 70, 180, 25);
		getContentPane().add(button);
		
		lblImagenDelLogin = new JLabel("Logo: (400px*300px)");
		lblImagenDelLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblImagenDelLogin.setFont(new Font("Candara", Font.BOLD, 20));
		lblImagenDelLogin.setBounds(10, 96, 336, 26);
		getContentPane().add(lblImagenDelLogin);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setForeground(SystemColor.windowBorder);
		textField_1.setFont(new Font("Arial", Font.ITALIC, 18));
		textField_1.setColumns(10);
		textField_1.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		textField_1.setBackground(new Color(245, 245, 245));
		textField_1.setBounds(10, 119, 180, 23);
		getContentPane().add(textField_1);
		
		button_1 = new JButton("Guardar");
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_1.setBackground(new Color(30, 144, 255));
		button_1.setBounds(234, 117, 180, 25);
		getContentPane().add(button_1);
		
		button_2 = new JButton("...");
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_2.setBackground(new Color(30, 144, 255));
		button_2.setBounds(198, 123, 26, 19);
		getContentPane().add(button_2);
		
		lblImagenDelLogin_1 = new JLabel("Imagen del login: (700px*300px)");
		lblImagenDelLogin_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblImagenDelLogin_1.setFont(new Font("Candara", Font.BOLD, 20));
		lblImagenDelLogin_1.setBounds(10, 146, 336, 26);
		getContentPane().add(lblImagenDelLogin_1);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.LEFT);
		textField_2.setForeground(SystemColor.windowBorder);
		textField_2.setFont(new Font("Arial", Font.ITALIC, 18));
		textField_2.setColumns(10);
		textField_2.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		textField_2.setBackground(new Color(245, 245, 245));
		textField_2.setBounds(10, 172, 180, 23);
		getContentPane().add(textField_2);
		
		button_3 = new JButton("...");
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_3.setBackground(new Color(30, 144, 255));
		button_3.setBounds(198, 176, 26, 19);
		getContentPane().add(button_3);
		
		button_4 = new JButton("Guardar");
		button_4.setForeground(Color.WHITE);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_4.setBackground(new Color(30, 144, 255));
		button_4.setBounds(234, 170, 180, 25);
		getContentPane().add(button_4);
		
		lblImagenDelReporte = new JLabel("Imagen del reporte: (300px*500px)");
		lblImagenDelReporte.setHorizontalAlignment(SwingConstants.LEFT);
		lblImagenDelReporte.setFont(new Font("Candara", Font.BOLD, 20));
		lblImagenDelReporte.setBounds(10, 198, 336, 26);
		getContentPane().add(lblImagenDelReporte);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.LEFT);
		textField_3.setForeground(SystemColor.windowBorder);
		textField_3.setFont(new Font("Arial", Font.ITALIC, 18));
		textField_3.setColumns(10);
		textField_3.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		textField_3.setBackground(new Color(245, 245, 245));
		textField_3.setBounds(10, 223, 180, 23);
		getContentPane().add(textField_3);
		
		button_5 = new JButton("...");
		button_5.setForeground(Color.WHITE);
		button_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_5.setBackground(new Color(30, 144, 255));
		button_5.setBounds(198, 227, 26, 19);
		getContentPane().add(button_5);
		
		button_6 = new JButton("Guardar");
		button_6.setForeground(Color.WHITE);
		button_6.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_6.setBackground(new Color(30, 144, 255));
		button_6.setBounds(234, 221, 180, 25);
		getContentPane().add(button_6);
		
		lblLogopxpx = new JLabel("Logo: (300px*500px)");
		lblLogopxpx.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogopxpx.setFont(new Font("Candara", Font.BOLD, 20));
		lblLogopxpx.setBounds(10, 295, 336, 26);
		getContentPane().add(lblLogopxpx);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.LEFT);
		textField_4.setForeground(SystemColor.windowBorder);
		textField_4.setFont(new Font("Arial", Font.ITALIC, 18));
		textField_4.setColumns(10);
		textField_4.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		textField_4.setBackground(new Color(245, 245, 245));
		textField_4.setBounds(10, 319, 180, 23);
		getContentPane().add(textField_4);
		
		button_7 = new JButton("...");
		button_7.setForeground(Color.WHITE);
		button_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_7.setBackground(new Color(30, 144, 255));
		button_7.setBounds(198, 323, 26, 19);
		getContentPane().add(button_7);
		
		button_8 = new JButton("Guardar");
		button_8.setForeground(Color.WHITE);
		button_8.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_8.setBackground(new Color(30, 144, 255));
		button_8.setBounds(234, 317, 180, 25);
		getContentPane().add(button_8);
		
		lblDatosParaEl = new JLabel("DATOS PARA EL COMPROBANTE:");
		lblDatosParaEl.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosParaEl.setFont(new Font("Candara", Font.BOLD, 20));
		lblDatosParaEl.setBounds(10, 268, 336, 26);
		getContentPane().add(lblDatosParaEl);
		
		lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccin.setFont(new Font("Candara", Font.BOLD, 20));
		lblDireccin.setBounds(10, 426, 336, 26);
		getContentPane().add(lblDireccin);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.LEFT);
		textField_5.setForeground(SystemColor.windowBorder);
		textField_5.setFont(new Font("Arial", Font.ITALIC, 18));
		textField_5.setColumns(10);
		textField_5.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		textField_5.setBackground(new Color(245, 245, 245));
		textField_5.setBounds(10, 446, 214, 23);
		getContentPane().add(textField_5);
		
		button_10 = new JButton("Guardar");
		button_10.setForeground(Color.WHITE);
		button_10.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_10.setBackground(new Color(30, 144, 255));
		button_10.setBounds(234, 400, 180, 25);
		getContentPane().add(button_10);
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefono.setFont(new Font("Candara", Font.BOLD, 20));
		lblTelefono.setBounds(10, 473, 336, 26);
		getContentPane().add(lblTelefono);
		
		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.LEFT);
		textField_6.setForeground(SystemColor.windowBorder);
		textField_6.setFont(new Font("Arial", Font.ITALIC, 18));
		textField_6.setColumns(10);
		textField_6.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		textField_6.setBackground(new Color(245, 245, 245));
		textField_6.setBounds(10, 494, 214, 23);
		getContentPane().add(textField_6);
		
		button_12 = new JButton("Guardar");
		button_12.setForeground(Color.WHITE);
		button_12.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_12.setBackground(new Color(30, 144, 255));
		button_12.setBounds(234, 492, 180, 25);
		getContentPane().add(button_12);
		
		lblRuc = new JLabel("Ruc:");
		lblRuc.setHorizontalAlignment(SwingConstants.LEFT);
		lblRuc.setFont(new Font("Candara", Font.BOLD, 20));
		lblRuc.setBounds(10, 520, 336, 26);
		getContentPane().add(lblRuc);
		
		textField_7 = new JTextField();
		textField_7.setHorizontalAlignment(SwingConstants.LEFT);
		textField_7.setForeground(SystemColor.windowBorder);
		textField_7.setFont(new Font("Arial", Font.ITALIC, 18));
		textField_7.setColumns(10);
		textField_7.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		textField_7.setBackground(new Color(245, 245, 245));
		textField_7.setBounds(10, 542, 214, 23);
		getContentPane().add(textField_7);
		
		button_14 = new JButton("Guardar");
		button_14.setForeground(Color.WHITE);
		button_14.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_14.setBackground(new Color(30, 144, 255));
		button_14.setBounds(234, 540, 180, 25);
		getContentPane().add(button_14);
		
		lblPresentacin = new JLabel("Presentaci\u00F3n:");
		lblPresentacin.setHorizontalAlignment(SwingConstants.LEFT);
		lblPresentacin.setFont(new Font("Candara", Font.BOLD, 20));
		lblPresentacin.setBounds(10, 346, 336, 26);
		getContentPane().add(lblPresentacin);
		
		textField_8 = new JTextField();
		textField_8.setHorizontalAlignment(SwingConstants.LEFT);
		textField_8.setForeground(SystemColor.windowBorder);
		textField_8.setFont(new Font("Arial", Font.ITALIC, 18));
		textField_8.setColumns(10);
		textField_8.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		textField_8.setBackground(new Color(245, 245, 245));
		textField_8.setBounds(10, 366, 404, 23);
		getContentPane().add(textField_8);
		
		button_15 = new JButton("Guardar");
		button_15.setForeground(Color.WHITE);
		button_15.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_15.setBackground(new Color(30, 144, 255));
		button_15.setBounds(234, 443, 180, 25);
		getContentPane().add(button_15);

		
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		cargar();
	}
	
	private void cargar(){
		String atribTodos = "";
		int ventasinstock = 0;
		int reducirstock = 0;
		int fechaVauto = 0;
		try {
			consulta.iniciar();
			rs = consulta.cargarConfiguraciones();
			rs.next();
			atribTodos = rs.getString("atributosprod");
			ventasinstock = rs.getInt("ventasinstock");
			reducirstock = rs.getInt("reducirstock");
			fechaVauto = rs.getInt("fechaVauto");
			
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
			consulta.iniciar();
			consulta.modificarAtributosProductos(atributos);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al modificar atributos de productos: " + e2);
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
	protected void actionPerformedBtnVenderSinStock(ActionEvent arg0) {
		int eleccion = cbVenderSinStock.getSelectedIndex();
		try {
			consulta.iniciar();
			consulta.modificarVentaSinStock(eleccion);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al modificar venta sin stock: " + e2);
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
	protected void actionPerformedBtnReducirAlVender(ActionEvent e) {
		int eleccion = cbReducirAlVender.getSelectedIndex();
		try {
			consulta.iniciar();
			consulta.modificarReducirAlVender(eleccion);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al modificar Reduccion al vender: " + e2);
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
	protected void actionPerformedBtnModifFecha(ActionEvent e) {
		int eleccion = cbModifFecha.getSelectedIndex();
		try {
			consulta.iniciar();
			consulta.modificarFechaAlVender(eleccion);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al modificar Modificar fecha al vender: " + e2);
		}	finally {
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
}
