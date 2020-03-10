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

public class Configuraciones extends JInternalFrame {
	private JMenuBar menuBar;
	private JButton btnX;
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

	
	private TextAutoCompleter ac;
	JTable tb;
	ResultSet rs;
	String usuario;
	consultas consulta = new consultas();


	
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
		panelAtributos.setBackground(new Color(72, 209, 204));
		panelAtributos.setBounds(0, 11, 551, 283);
		getContentPane().add(panelAtributos);
		panelAtributos.setLayout(null);
		
		lblNewLabel = new JLabel("Seleccione los atributos que desee manejar");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 519, 26);
		panelAtributos.add(lblNewLabel);
		
		chckbxMarca = new JCheckBox("Marca");
		chckbxMarca.setForeground(new Color(255, 255, 255));
		chckbxMarca.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxMarca.setBackground(new Color(0, 206, 209));
		chckbxMarca.setBounds(67, 68, 135, 23);
		panelAtributos.add(chckbxMarca);
		
		chckbxColor = new JCheckBox("Color");
		chckbxColor.setForeground(new Color(255, 255, 255));
		chckbxColor.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxColor.setBackground(new Color(0, 206, 209));
		chckbxColor.setBounds(213, 68, 97, 23);
		panelAtributos.add(chckbxColor);
		
		chckbxLote = new JCheckBox("Lote");
		chckbxLote.setForeground(new Color(255, 255, 255));
		chckbxLote.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxLote.setBackground(new Color(0, 206, 209));
		chckbxLote.setBounds(385, 68, 97, 23);
		panelAtributos.add(chckbxLote);
		
		chckbxLaboratorio = new JCheckBox("Laboratorio");
		chckbxLaboratorio.setForeground(new Color(255, 255, 255));
		chckbxLaboratorio.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxLaboratorio.setBackground(new Color(0, 206, 209));
		chckbxLaboratorio.setBounds(67, 106, 135, 23);
		panelAtributos.add(chckbxLaboratorio);
		
		chckbxFechaVencimiento = new JCheckBox("Fecha Vencimiento");
		chckbxFechaVencimiento.setForeground(new Color(255, 255, 255));
		chckbxFechaVencimiento.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxFechaVencimiento.setBackground(new Color(0, 206, 209));
		chckbxFechaVencimiento.setBounds(213, 106, 171, 23);
		panelAtributos.add(chckbxFechaVencimiento);
		
		chckbxPromocion1 = new JCheckBox("Promoci\u00F3n 1");
		chckbxPromocion1.setForeground(new Color(255, 255, 255));
		chckbxPromocion1.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxPromocion1.setBackground(new Color(0, 206, 209));
		chckbxPromocion1.setBounds(67, 150, 135, 23);
		panelAtributos.add(chckbxPromocion1);
		
		chckbxPromocion2 = new JCheckBox("Promoci\u00F3n 2");
		chckbxPromocion2.setForeground(new Color(255, 255, 255));
		chckbxPromocion2.setFont(new Font("Candara", Font.BOLD, 18));
		chckbxPromocion2.setBackground(new Color(0, 206, 209));
		chckbxPromocion2.setBounds(213, 150, 171, 23);
		panelAtributos.add(chckbxPromocion2);
		
		btnGuardarAtributos = new JButton("Guardar");
		btnGuardarAtributos.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnGuardarAtributos.setForeground(Color.WHITE);
		btnGuardarAtributos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGuardarAtributos(e);
			}
		});
		btnGuardarAtributos.setBackground(new Color(220, 20, 60));
		btnGuardarAtributos.setBounds(127, 204, 256, 36);
		panelAtributos.add(btnGuardarAtributos);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(563, 11, 540, 283);
		getContentPane().add(panel);
		
		lblPermitirSeguirVendiendo = new JLabel("Permitir seguir vendiendo cuando no haya stock?");
		lblPermitirSeguirVendiendo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPermitirSeguirVendiendo.setFont(new Font("Candara", Font.BOLD, 20));
		lblPermitirSeguirVendiendo.setBounds(10, 50, 519, 26);
		panel.add(lblPermitirSeguirVendiendo);
		
		btnVenderSinStock = new JButton("Guardar");
		btnVenderSinStock.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVenderSinStock.setForeground(Color.WHITE);
		btnVenderSinStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnVenderSinStock(arg0);
			}
		});
		btnVenderSinStock.setBackground(new Color(220, 20, 60));
		btnVenderSinStock.setBounds(143, 147, 256, 36);
		panel.add(btnVenderSinStock);
		
		cbVenderSinStock = new JComboBox();
		cbVenderSinStock.setFont(new Font("Candara", Font.PLAIN, 18));
		cbVenderSinStock.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		cbVenderSinStock.setBounds(143, 98, 256, 26);
		panel.add(cbVenderSinStock);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(100, 149, 237));
		panel_1.setBounds(0, 306, 551, 283);
		getContentPane().add(panel_1);
		
		lblreducirStockAl = new JLabel("\u00BFReducir stock al vender?");
		lblreducirStockAl.setHorizontalAlignment(SwingConstants.CENTER);
		lblreducirStockAl.setFont(new Font("Candara", Font.BOLD, 20));
		lblreducirStockAl.setBounds(87, 27, 359, 26);
		panel_1.add(lblreducirStockAl);
		
		btnReducirAlVender = new JButton("Guardar");
		btnReducirAlVender.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReducirAlVender.setForeground(Color.WHITE);
		btnReducirAlVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnReducirAlVender(e);
			}
		});
		btnReducirAlVender.setBackground(new Color(220, 20, 60));
		btnReducirAlVender.setBounds(170, 102, 191, 36);
		panel_1.add(btnReducirAlVender);
		
		cbReducirAlVender = new JComboBox();
		cbReducirAlVender.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		cbReducirAlVender.setBounds(170, 64, 191, 26);
		panel_1.add(cbReducirAlVender);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(72, 209, 204));
		panel_2.setBounds(563, 306, 540, 283);
		getContentPane().add(panel_2);
		
		lblpermitirModificarFecha = new JLabel("\u00BFPermitir modificar fecha al vender?");
		lblpermitirModificarFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblpermitirModificarFecha.setFont(new Font("Candara", Font.BOLD, 20));
		lblpermitirModificarFecha.setBounds(46, 39, 454, 26);
		panel_2.add(lblpermitirModificarFecha);
		
		btnModifFecha = new JButton("Guardar");
		btnModifFecha.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnModifFecha.setForeground(Color.WHITE);
		btnModifFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnModifFecha(e);
			}
		});
		btnModifFecha.setBackground(new Color(220, 20, 60));
		btnModifFecha.setBounds(147, 138, 256, 36);
		panel_2.add(btnModifFecha);
		
		cbModifFecha = new JComboBox();
		cbModifFecha.setFont(new Font("Candara", Font.PLAIN, 18));
		cbModifFecha.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		cbModifFecha.setBounds(147, 84, 256, 26);
		panel_2.add(cbModifFecha);

		
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
