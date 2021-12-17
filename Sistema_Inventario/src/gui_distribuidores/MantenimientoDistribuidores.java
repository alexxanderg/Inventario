package gui_distribuidores;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import com.mxrck.autocompleter.TextAutoCompleter;
import gui_principal.VentanaPrincipal;
import mysql.consultas;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.ListSelectionModel;

public class MantenimientoDistribuidores extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnCrearProducto;
	private JMenu mnModificarProducto;
	private JMenu mnNewMenu_2;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	private JTable tbDistribuidores;
	
	public VentanaPrincipal vp;
	
	JTable tb;
	ResultSet rs;
	consultas consulta = new consultas();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MantenimientoDistribuidores frame = new MantenimientoDistribuidores(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MantenimientoDistribuidores(VentanaPrincipal vp) {
		this.vp = vp;
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("DISTRIBUIDORES");
		setBounds(100, 100, 780, 679);
		getContentPane().setLayout(null);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 41, 744, 568);
		getContentPane().add(this.scrollPane);
		
		tbDistribuidores = new JTable();
		tbDistribuidores.setAutoCreateRowSorter(true);
		tbDistribuidores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbDistribuidores.setFont(new Font("Arial", Font.ITALIC, 14));
		tbDistribuidores.setBackground(Color.WHITE);
		tbDistribuidores.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbDistribuidores);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbDistribuidores.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);
		
		mnCrearProducto = new JMenu("|Crear nuevo distribuidor| ");
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
		
		mnModificarProducto = new JMenu("|Modificar distribuidor| ");
		mnModificarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnModificarProducto(e);
			}
		});
		mnModificarProducto.setForeground(new Color(50, 205, 50));
		mnModificarProducto.setBackground(SystemColor.control);
		mnModificarProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnModificarProducto);
		
		mnNewMenu_2 = new JMenu("|Eliminar| ");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnNewMenu_2(e);
			}
		});
		mnNewMenu_2.setForeground(new Color(240, 128, 128));
		mnNewMenu_2.setBackground(SystemColor.control);
		mnNewMenu_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnNewMenu_2);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
	}
	
	public void cargar() {
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbDistribuidores;
		tb.setRowHeight(30);
		tb.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[]{"ID", "NOMBRE", "TIPO DOC", "NRO DOCUMENTO", "DIRECCIÓN", "PERSONA CONTACTO", "TELEFONO", "CORREO"});
		
		try {
			consulta.iniciar();
			rs = consulta.cargarDistribuidores();
			while(rs.next()){
				dtm.addRow(new Object[]{rs.getInt("iddistrib"), rs.getString("nombre"), rs.getString("tipodoc"), rs.getString("nrodoc"), rs.getString("direccion"), rs.getString("perscontact"), rs.getString("telefono"), rs.getString("correo")});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar usuarios: " + e.getMessage());
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
		
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbDistribuidores.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(2));  // 
		tcm.getColumn(1).setPreferredWidth(anchoColumna(10));  // 
		tcm.getColumn(4).setPreferredWidth(anchoColumna(10));  // 
		tcm.getColumn(5).setPreferredWidth(anchoColumna(10));  // 
		
	}
	
	public void selecionarDistribuidor(String id) {
		int cantDist = tbDistribuidores.getRowCount();
		for (int i = 0; i < cantDist; i++) {
			if (id.equals(tbDistribuidores.getValueAt(i, 0).toString())) {
				tbDistribuidores.setRowSelectionInterval(i, i);
				break;
			}
		}
	}
	
	NuevoDistribuidor nd = new NuevoDistribuidor(this, null, null, null);
	protected void mouseClickedMnCrearProducto(MouseEvent arg0) {
		try {
			if (nd.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				nd.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				nd.setVisible(true); 
			} else {
				nd = new NuevoDistribuidor(this, null, null, null);
				nd.setLocationRelativeTo(null);
				nd.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
	
	protected void mouseClickedMnModificarProducto(MouseEvent e) {
		try {		
			DefaultTableModel tm = (DefaultTableModel) tbDistribuidores.getModel();
			int idDist = Integer.parseInt(String.valueOf(tm.getValueAt(tbDistribuidores.getSelectedRow(), 0)));
			ModificarDistribuidor md = new ModificarDistribuidor(idDist,this);;
			try { 
				if (md.isShowing()) {
					//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
					//mu.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
					md.setVisible(true);
				} else {
					md.setLocationRelativeTo(null);
					md.setVisible(true);
				}
			} catch (Exception f) {
				JOptionPane.showMessageDialog(null, "Error: " + f);
			}
		} catch(Exception e1){
			JOptionPane.showMessageDialog(null, "Seleccione el distribuidor a modificar");
		}
	}
	
	protected void mouseClickedMnNewMenu_2(MouseEvent e) {
		DefaultTableModel tm = (DefaultTableModel) tbDistribuidores.getModel();
		int iddistrib = Integer.parseInt(String.valueOf(tm.getValueAt(tbDistribuidores.getSelectedRow(), 0)));
		int opc = JOptionPane.showConfirmDialog(null, "¿Seguro de querer eliminar este distribuidor?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == 0) {
			consulta.iniciar();
			consulta.deshabilitarDistrib(iddistrib);
			cargar();
			consulta.reset();
		}else{
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
	}
}
