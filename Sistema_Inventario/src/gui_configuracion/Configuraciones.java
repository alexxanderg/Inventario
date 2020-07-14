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
	private JLabel lblCantidadDeImpresiones;
	private JButton button_9;
	private JTextField textField_9;
	private JButton btnActualizarBD;


	
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
		btnModifFecha.setBounds(256, 465, 180, 27);
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
		cbModifFecha.setBounds(36, 465, 191, 26);
		getContentPane().add(cbModifFecha);
		cbModifFecha.setFont(new Font("Candara", Font.PLAIN, 18));
		cbModifFecha.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		
		lblpermitirModificarFecha = new JLabel("\u00BFPermitir modificar fecha al vender?");
		lblpermitirModificarFecha.setBounds(36, 431, 400, 26);
		getContentPane().add(lblpermitirModificarFecha);
		lblpermitirModificarFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblpermitirModificarFecha.setFont(new Font("Candara", Font.BOLD, 20));
		
		btnVenderSinStock = new JButton("Guardar");
		btnVenderSinStock.setBounds(256, 379, 180, 27);
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
		cbVenderSinStock.setBounds(36, 379, 191, 26);
		getContentPane().add(cbVenderSinStock);
		cbVenderSinStock.setFont(new Font("Candara", Font.PLAIN, 18));
		cbVenderSinStock.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		
		lblPermitirSeguirVendiendo = new JLabel("\u00BFPermitir seguir vendiendo cuando no haya stock?");
		lblPermitirSeguirVendiendo.setBounds(36, 346, 451, 26);
		getContentPane().add(lblPermitirSeguirVendiendo);
		lblPermitirSeguirVendiendo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPermitirSeguirVendiendo.setFont(new Font("Candara", Font.BOLD, 20));
		
		lblNewLabel = new JLabel("Seleccione los atributos que desee manejar");
		lblNewLabel.setBounds(26, 11, 400, 26);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 20));
		
		chckbxMarca = new JCheckBox("Marca");
		chckbxMarca.setBounds(26, 44, 135, 23);
		getContentPane().add(chckbxMarca);
		chckbxMarca.setForeground(Color.DARK_GRAY);
		chckbxMarca.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxMarca.setBackground(Color.WHITE);
		
		chckbxColor = new JCheckBox("Color");
		chckbxColor.setBounds(26, 70, 97, 23);
		getContentPane().add(chckbxColor);
		chckbxColor.setForeground(Color.DARK_GRAY);
		chckbxColor.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxColor.setBackground(Color.WHITE);
		
		chckbxFechaVencimiento = new JCheckBox("Fecha Vencimiento");
		chckbxFechaVencimiento.setBounds(26, 96, 171, 23);
		getContentPane().add(chckbxFechaVencimiento);
		chckbxFechaVencimiento.setForeground(Color.DARK_GRAY);
		chckbxFechaVencimiento.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxFechaVencimiento.setBackground(Color.WHITE);
		
		chckbxLote = new JCheckBox("Lote");
		chckbxLote.setBounds(26, 122, 97, 23);
		getContentPane().add(chckbxLote);
		chckbxLote.setForeground(Color.DARK_GRAY);
		chckbxLote.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxLote.setBackground(Color.WHITE);
		
		chckbxLaboratorio = new JCheckBox("Laboratorio");
		chckbxLaboratorio.setBounds(26, 148, 135, 23);
		getContentPane().add(chckbxLaboratorio);
		chckbxLaboratorio.setForeground(Color.DARK_GRAY);
		chckbxLaboratorio.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxLaboratorio.setBackground(Color.WHITE);
		
		chckbxPromocion1 = new JCheckBox("Promoci\u00F3n 1");
		chckbxPromocion1.setBounds(26, 174, 135, 23);
		getContentPane().add(chckbxPromocion1);
		chckbxPromocion1.setForeground(Color.DARK_GRAY);
		chckbxPromocion1.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxPromocion1.setBackground(Color.WHITE);
		
		chckbxPromocion2 = new JCheckBox("Promoci\u00F3n 2");
		chckbxPromocion2.setBounds(26, 200, 171, 23);
		getContentPane().add(chckbxPromocion2);
		chckbxPromocion2.setForeground(Color.DARK_GRAY);
		chckbxPromocion2.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxPromocion2.setBackground(Color.WHITE);
		
		btnGuardarAtributos = new JButton("Guardar");
		btnGuardarAtributos.setBounds(256, 200, 180, 25);
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
		lblreducirStockAl.setBounds(36, 267, 220, 26);
		getContentPane().add(lblreducirStockAl);
		lblreducirStockAl.setHorizontalAlignment(SwingConstants.LEFT);
		lblreducirStockAl.setFont(new Font("Candara", Font.BOLD, 20));
		
		cbReducirAlVender = new JComboBox();
		cbReducirAlVender.setFont(new Font("Candara", Font.PLAIN, 18));
		cbReducirAlVender.setBounds(36, 298, 191, 26);
		getContentPane().add(cbReducirAlVender);
		cbReducirAlVender.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		
		btnReducirAlVender = new JButton("Guardar");
		btnReducirAlVender.setBounds(256, 298, 180, 27);
		getContentPane().add(btnReducirAlVender);
		btnReducirAlVender.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReducirAlVender.setForeground(Color.WHITE);
		btnReducirAlVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnReducirAlVender(e);
			}
		});
		btnReducirAlVender.setBackground(new Color(30, 144, 255));
		
		lblCantidadDeImpresiones = new JLabel("Cantidad de impresiones al vender:");
		lblCantidadDeImpresiones.setEnabled(false);
		lblCantidadDeImpresiones.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadDeImpresiones.setFont(new Font("Candara", Font.BOLD, 20));
		lblCantidadDeImpresiones.setBounds(36, 504, 400, 26);
		getContentPane().add(lblCantidadDeImpresiones);
		
		button_9 = new JButton("Guardar");
		button_9.setForeground(Color.WHITE);
		button_9.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_9.setBackground(new Color(30, 144, 255));
		button_9.setBounds(256, 538, 180, 27);
		getContentPane().add(button_9);
		
		textField_9 = new JTextField();
		textField_9.setText("0");
		textField_9.setHorizontalAlignment(SwingConstants.LEFT);
		textField_9.setForeground(SystemColor.windowBorder);
		textField_9.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_9.setColumns(10);
		textField_9.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		textField_9.setBackground(new Color(245, 245, 245));
		textField_9.setBounds(36, 542, 191, 23);
		getContentPane().add(textField_9);
		
		btnActualizarBD = new JButton("Verificar y actualizar base de datos");
		btnActualizarBD.setEnabled(false);
		btnActualizarBD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnActualizarBD(arg0);
			}
		});
		btnActualizarBD.setForeground(Color.WHITE);
		btnActualizarBD.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnActualizarBD.setBackground(new Color(0, 206, 209));
		btnActualizarBD.setBounds(611, 593, 400, 25);
		getContentPane().add(btnActualizarBD);

		
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE T�TULO
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
		cbReducirAlVender.setSelectedIndex(1);
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
	
	protected void actionPerformedBtnActualizarBD(ActionEvent arg0) {
		
	}
}
