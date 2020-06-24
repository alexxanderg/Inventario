package gui_usuarios;

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

public class MantenimientoUsuarios extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnCrearProducto;
	private JMenu mnModificarProducto;
	private JMenu mnNewMenu_2;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	private JTable tbUsuarios;
	
	public VentanaPrincipal vp;
	
	JTable tb;
	ResultSet rs;
	consultas consulta = new consultas();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MantenimientoUsuarios frame = new MantenimientoUsuarios(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MantenimientoUsuarios(VentanaPrincipal vp) {
		this.vp = vp;
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("USUARIOS");
		setBounds(100, 100, 1134, 679);
		getContentPane().setLayout(null);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 41, 1083, 568);
		getContentPane().add(this.scrollPane);
		
		tbUsuarios = new JTable();
		tbUsuarios.setAutoCreateRowSorter(true);
		tbUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbUsuarios.setFont(new Font("Arial", Font.ITALIC, 14));
		tbUsuarios.setBackground(Color.WHITE);
		tbUsuarios.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbUsuarios);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbUsuarios.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(Color.DARK_GRAY);
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
		mnModificarProducto.setForeground(new Color(50, 205, 50));
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
		mnNewMenu_2.setForeground(new Color(240, 128, 128));
		mnNewMenu_2.setBackground(SystemColor.control);
		mnNewMenu_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnNewMenu_2);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
	}
	
	public void cargar() {
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbUsuarios;
		tb.setRowHeight(30);
		tb.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[]{"ID", "NOMBRE", "USUARIO", "CONTRASEÑA", "TIPO"});
		try {
			consulta.iniciar();
			rs = consulta.cargarUsuarios();
			while(rs.next()){
				String u = rs.getString("usuario");
				if(!u.equals("bxb")){
					int t = rs.getInt("tipo");
					if(t == 0)
						dtm.addRow(new Object[]{rs.getString("idusuario"), rs.getString("nombre"), rs.getString("usuario"), "************", "Administrador"});
					if(t == 1)
						dtm.addRow(new Object[]{rs.getString("idusuario"), rs.getString("nombre"), rs.getString("usuario"), "************", "Vendedor"});
				}
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
		TableColumnModel tcm = tbUsuarios.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(5));  // ID
		tcm.getColumn(1).setPreferredWidth(anchoColumna(40));  // Nombre
		tcm.getColumn(2).setPreferredWidth(anchoColumna(20));  // Usuario
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15));  // Contraseña
		tcm.getColumn(4).setPreferredWidth(anchoColumna(20));  // Tipo
		
		/*DefaultTableCellRenderer tcr0 = new DefaultTableCellRenderer();
		tcr0.setHorizontalAlignment(SwingConstants.CENTER);
		tbUsuarios.getColumnModel().getColumn(3).setCellRenderer(tcr0);*/
	}
	
	public void selecionarUsuario(String id) {
		int cantProductos = tbUsuarios.getRowCount();
		for (int i = 0; i < cantProductos; i++) {
			if (id.equals(tbUsuarios.getValueAt(i, 0).toString())) {
				tbUsuarios.setRowSelectionInterval(i, i);
				break;
			}
		}
	}
	
	NuevoUsuario nu = new NuevoUsuario(this);
	protected void mouseClickedMnCrearProducto(MouseEvent arg0) {
		try {
			if (nu.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				// nu.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				nu.setVisible(true); 
			} else {
				nu = new NuevoUsuario(this);
				nu.setLocationRelativeTo(null);
				nu.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
	
	// De manera global
	
	protected void mouseClickedMnModificarProducto(MouseEvent e) {
		try {		
			DefaultTableModel tm = (DefaultTableModel) tbUsuarios.getModel();
			int idusuario = Integer.parseInt(String.valueOf(tm.getValueAt(tbUsuarios.getSelectedRow(), 0)));
			ModificarUsuario mu = new ModificarUsuario(idusuario,this);;
			try { 
				if (mu.isShowing()) {
					//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
					//mu.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
					mu.setVisible(true);
				} else {
					mu.setLocationRelativeTo(null);
					mu.setVisible(true);
				}
			} catch (Exception f) {
				JOptionPane.showMessageDialog(null, "Error: " + f);
			}
		} catch(Exception e1){
			JOptionPane.showMessageDialog(null, "Seleccione el producto a modificar");
		}
	}
	
	protected void mouseClickedMnNewMenu_2(MouseEvent e) {
		DefaultTableModel tm = (DefaultTableModel) tbUsuarios.getModel();
		int idusuario = Integer.parseInt(String.valueOf(tm.getValueAt(tbUsuarios.getSelectedRow(), 0)));
		int opc = JOptionPane.showConfirmDialog(null, "¿Seguro de querer eliminar este usuario?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == 0) {
			consulta.iniciar();
			consulta.deshabilitarUsuario(idusuario);
			cargar();
			consulta.reset();
		}else{
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
	}
}
