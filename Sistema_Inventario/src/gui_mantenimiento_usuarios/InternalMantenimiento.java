package gui_mantenimiento_usuarios;

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

public class InternalMantenimiento extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnCrearProducto;
	private JMenu mnModificarProducto;
	private JMenu mnNewMenu_2;
	private JButton btnX;
	private JLabel lblCdigo;
	private JTextField txtCodigo;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	private JTable tbUsuarios;
	
	public VentanaPrincipal vp;
	
	JTable tb;
	ResultSet rs;
	consultas model = new consultas();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalMantenimiento frame = new InternalMantenimiento(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InternalMantenimiento(VentanaPrincipal vp) {
		this.vp = vp;
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("ALMAC\u00C9N");
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
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 90, 1083, 519);
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
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);
		
		mnCrearProducto = new JMenu("|Crear nuevo usuario| ");
		mnCrearProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedMnCrearProducto(arg0);
			}
		});
		mnCrearProducto.setForeground(new Color(65, 105, 225));
		mnCrearProducto.setBackground(SystemColor.control);
		mnCrearProducto.setFont(new Font("Arial", Font.BOLD, 22));
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
		mnModificarProducto.setFont(new Font("Arial", Font.BOLD, 22));
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
		mnNewMenu_2.setFont(new Font("Arial", Font.BOLD, 22));
		menuBar.add(mnNewMenu_2);
		
		mnOtrasOpciones = new JMenu("|Otras opciones|");
		mnOtrasOpciones.setForeground(new Color(255, 69, 0));
		mnOtrasOpciones.setFont(new Font("Arial", Font.BOLD, 22));
		mnOtrasOpciones.setBackground(SystemColor.menu);
		menuBar.add(mnOtrasOpciones);
		
		mntmRealizarKardex = new JMenuItem("Realizar Kardex");
		mntmRealizarKardex.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMntmRealizarKardex(e);
			}
		});
		mntmRealizarKardex.setForeground(new Color(255, 69, 0));
		mntmRealizarKardex.setFont(new Font("Arial", Font.PLAIN, 20));
		mnOtrasOpciones.add(mntmRealizarKardex);
		
		mntmVerHistorial = new JMenuItem("Ver Historial de Kardex");
		mntmVerHistorial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMntmVerHistorial(e);
			}
		});
		mntmVerHistorial.setForeground(new Color(255, 69, 0));
		mntmVerHistorial.setFont(new Font("Arial", Font.PLAIN, 20));
		mnOtrasOpciones.add(mntmVerHistorial);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
		cargarBuscador();
	}
	
	public void cargar() {
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbUsuarios;
		tb.setRowHeight(25);
		tb.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[]{"NOMBRE", "USUARIO", "CONTRASEÑA", "TIPO"});
		consultas model = new consultas();
		rs = model.cargarUsuarios();
		try {
			while(rs.next()){
				String u = rs.getString("usuario");
				if(!u.equals("admin")){
					int t = rs.getInt("tipo");
					if(t == 0)
						dtm.addRow(new Object[]{rs.getString("nombre"), rs.getString("usuario"), "************", "Administrador"});
					if(t == 1)
						dtm.addRow(new Object[]{rs.getString("nombre"), rs.getString("usuario"), "************", "Vendedor"});
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar usuarios: " + e.getMessage());
		}
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
		TableColumnModel tcm = tbUsuarios.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(40));  // Nombre
		tcm.getColumn(1).setPreferredWidth(anchoColumna(20));  // Usuario
		tcm.getColumn(2).setPreferredWidth(anchoColumna(20));  // Contraseña
		tcm.getColumn(3).setPreferredWidth(anchoColumna(20));  // Tipo
		
		DefaultTableCellRenderer tcr0 = new DefaultTableCellRenderer();
		tcr0.setHorizontalAlignment(SwingConstants.CENTER);
		tbUsuarios.getColumnModel().getColumn(3).setCellRenderer(tcr0);
	}

	protected void actionPerformedBtnX(ActionEvent arg0) {
		try {
			this.setClosed(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	public void selecionarProducto(String id) {
		int cantProductos = tbUsuarios.getRowCount();
		for (int i = 0; i < cantProductos; i++) {
			if (id.equals(tbUsuarios.getValueAt(i, 0))) {
				tbUsuarios.setRowSelectionInterval(i, i);
				break;
			}
		}
	}
	
	NuevoUsuario2 nu = new NuevoUsuario2(this);
	private JMenu mnOtrasOpciones;
	private JMenuItem mntmRealizarKardex;
	private JMenuItem mntmVerHistorial;
	protected void mouseClickedMnCrearProducto(MouseEvent arg0) {
		try {
			if (nu.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				// nu.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				nu.setVisible(true); 
			} else {
				nu = new NuevoUsuario2(this);
				nu.setLocationRelativeTo(null);
				nu.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
	
	// De manera global
	
	protected void mouseClickedMnModificarProducto(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "Cargar el id del usuario seleccionado. Con ello cargar los datos directamente de la base de datos para ser modificados");
		DefaultTableModel tm = (DefaultTableModel) tbUsuarios.getModel();
		String codigoUsuario = String.valueOf(tm.getValueAt(tbUsuarios.getSelectedRow(), 1));
		try {		
			ModificarUsuario2 mu = new ModificarUsuario2(codigoUsuario,this);;
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
			JOptionPane.showMessageDialog(null, "Seleccione el producto a modificar " + e);
		}
	}
	protected void mouseClickedMnNewMenu_2(MouseEvent e) {
		DefaultTableModel tm = (DefaultTableModel) tbUsuarios.getModel();
		String codigoProducto = String.valueOf(tm.getValueAt(tbUsuarios.getSelectedRow(), 1));
		JOptionPane.showMessageDialog(null, "No elimina, solo cambia de estado al producto a 1 y vuelve a cargar la tabla");
		int opc = JOptionPane.showConfirmDialog(null, "¿Seguro de querer DESHABILITAR ESTE USUARIO?", "Confirmación", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opc == 0) {
			model.deshabilitarUsuario(codigoProducto);
			cargar();
		}else{
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
	}
	protected void mouseClickedMntmRealizarKardex(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "Realizar kardex");
	}
	protected void mouseClickedMntmVerHistorial(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "Historial kardex");
	}
}
