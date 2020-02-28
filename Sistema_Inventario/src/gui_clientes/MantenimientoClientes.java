package gui_clientes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import mysql.consultas;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import com.mxrck.autocompleter.TextAutoCompleter;
import gui_mantenimiento_productos.AgregarStock;
import gui_principal.EleccionVentanas;

import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.Icon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MantenimientoClientes extends JFrame implements ActionListener, WindowListener, KeyListener {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JLabel lblCdigo;
	private JTable tbClientes;
	private TextAutoCompleter ac;
	private JScrollPane scrollPane;
	private JTextField txtMantenimientoDeProductos;
	private JButton btnVolver;

	JTable tb;
	ResultSet rs;
	String usuario;
	consultas model = new consultas();
	private JButton btnCrear;
	private JButton btnModificar;
	private JButton btnEliminar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MantenimientoClientes frame = new MantenimientoClientes(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MantenimientoClientes(String temp2) {
		usuario = temp2;
		addWindowListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 979, 627);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setFont(new Font("Swis721 LtEx BT", Font.BOLD | Font.ITALIC, 20));
		txtNombre.setColumns(10);
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setBounds(322, 175, 632, 34);
		contentPane.add(txtNombre);

		lblCdigo = new JLabel("Buscar por nombre:");
		lblCdigo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCdigo.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		lblCdigo.setBounds(10, 171, 314, 38);
		contentPane.add(lblCdigo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 220, 944, 360);
		contentPane.add(scrollPane);

		tbClientes = new JTable();
		tbClientes.setFont(new Font("Tw Cen MT", Font.ITALIC, 17));
		tbClientes.setBackground(Color.WHITE);
		tbClientes.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tbClientes);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbClientes.getTableHeader().setReorderingAllowed(false);

		btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(this);
		btnVolver.setForeground(new Color(0, 255, 0));
		btnVolver.setFont(new Font("EngraversGothic BT", Font.BOLD | Font.ITALIC, 20));
		btnVolver.setBackground(Color.DARK_GRAY);
		btnVolver.setBounds(0, 0, 132, 75);
		contentPane.add(btnVolver);

		txtMantenimientoDeProductos = new JTextField();
		txtMantenimientoDeProductos.setForeground(new Color(255, 255, 255));
		txtMantenimientoDeProductos.setText("CLIENTES");
		txtMantenimientoDeProductos.setRequestFocusEnabled(false);
		txtMantenimientoDeProductos.setIgnoreRepaint(true);
		txtMantenimientoDeProductos.setHorizontalAlignment(SwingConstants.CENTER);
		txtMantenimientoDeProductos.setFont(new Font("EngraversGothic BT", Font.BOLD, 40));
		txtMantenimientoDeProductos.setFocusable(false);
		txtMantenimientoDeProductos.setFocusTraversalKeysEnabled(false);
		txtMantenimientoDeProductos.setEditable(false);
		txtMantenimientoDeProductos.setColumns(10);
		txtMantenimientoDeProductos.setBackground(Color.DARK_GRAY);
		txtMantenimientoDeProductos.setBounds(0, 0, 963, 75);
		contentPane.add(txtMantenimientoDeProductos);
		
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(this);
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("Dialog", Font.BOLD, 28));
		btnCrear.setBackground(new Color(30, 144, 255));
		btnCrear.setBounds(145, 96, 205, 55);
		contentPane.add(btnCrear);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Dialog", Font.BOLD, 28));
		btnModificar.setBackground(new Color(30, 144, 255));
		btnModificar.setBounds(367, 96, 219, 55);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(this);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Dialog", Font.BOLD, 28));
		btnEliminar.setBackground(new Color(30, 144, 255));
		btnEliminar.setBounds(596, 96, 205, 55);
		contentPane.add(btnEliminar);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtNombre, contentPane, lblCdigo, scrollPane, tbClientes}));

		cargarDatos();
		cargarBuscador();
		ajustarAnchoColumnas();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnCrear) {
			actionPerformedBtnCrear(arg0);
		}
		if (arg0.getSource() == btnVolver) {
			actionPerformedBtnVolver(arg0);
		}
	}

	public void cargarDatos() {
		this.setLocationRelativeTo(null);
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbClientes;
		tb.setRowHeight(25);
		tb.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[] { "ID", "Nombre", "Doc.", "Nro Documento", "Dirección", "Correo", "Telefono"});
		consultas model = new consultas();
		rs = model.cargarClientes();
		try {
			while (rs.next())
				dtm.addRow(new Object[] { rs.getInt("id"), rs.getString("nombre"), rs.getString("tipodoc"),
						rs.getString("nrodoc"), rs.getString("direccion"), rs.getString("correo"), rs.getString("telefono")});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}

	public void cargarBuscador() {
		ac = new TextAutoCompleter(txtNombre);
		consultas model = new consultas();
		ResultSet rs = model.cargarClientes();
		ac.setMode(0);
		try {
			while (rs.next()) {
				// ac.addItem(rs.getString("codproducto"));
				ac.addItem(rs.getString("id") + "_" + rs.getString("nombre"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbClientes.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(5)); // ID
		tcm.getColumn(1).setPreferredWidth(anchoColumna(25)); // Nombre
		tcm.getColumn(2).setPreferredWidth(anchoColumna(5)); // Documento
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15)); // Nro Documento
		tcm.getColumn(4).setPreferredWidth(anchoColumna(20)); // Dirección
		tcm.getColumn(5).setPreferredWidth(anchoColumna(20)); // Correo 
		tcm.getColumn(6).setPreferredWidth(anchoColumna(10)); // Telefono
/*
		DefaultTableCellRenderer tcr0 = new DefaultTableCellRenderer();
		tcr0.setHorizontalAlignment(SwingConstants.CENTER);
		tbClientes.getColumnModel().getColumn(3).setCellRenderer(tcr0);

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		tbClientes.getColumnModel().getColumn(4).setCellRenderer(tcr);

		DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbClientes.getColumnModel().getColumn(5).setCellRenderer(tcr2);

		DefaultTableCellRenderer tcr3 = new DefaultTableCellRenderer();
		tcr3.setHorizontalAlignment(SwingConstants.CENTER);
		tbClientes.getColumnModel().getColumn(6).setCellRenderer(tcr3);*/
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
		if (arg0.getSource() == this) {
			windowClosingThis(arg0);
		}
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}

	protected void windowClosingThis(WindowEvent arg0) {
		int opc = JOptionPane.showConfirmDialog(null, "¿Cerrar Sistema?", "Confirmación", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opc == 0){
			try {
				DateFormat df = new SimpleDateFormat("dd.MM.yyyy  HH.mm.ss");
				Date today = Calendar.getInstance().getTime();       
				String reportDate = df.format(today);
				File directorio=new File("D:\\ INFORMACION_DEL_SISTEMA\\BACKUP_SISTEMA"); 
				directorio.mkdirs(); 
				Process p;
				p = Runtime.getRuntime().exec("mysqldump -u root -pAa123 db_inventario");
				InputStream is = p.getInputStream();
				FileOutputStream fos = new FileOutputStream("D:\\ INFORMACION_DEL_SISTEMA\\BACKUP_SISTEMA\\backup_inventario  "+reportDate+".sql");
				byte[] buffer = new byte[1000];
				int leido = is.read(buffer);
				while(leido>0){
					fos.write(buffer, 0, leido);
					leido = is.read(buffer);
				}
				//JOptionPane.showMessageDialog(null, "Copia de segudidad creada en: \n D:/ INFORMACION DEL SISTEMA / BACKUP_SISTEMA / ");
				//JOptionPane.showMessageDialog(null, "Copia de segudidad realizada correctamente");
				fos.close();
			} catch (IOException e1) {
				//JOptionPane.showMessageDialog(null, e1);
			}
			System.exit(0);
		}
		else
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}

	public void keyPressed(KeyEvent arg0) {
		if (arg0.getSource() == txtNombre) {
			keyPressedTxtCodigo(arg0);
		}
	}

	public void keyReleased(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		int posi = -1;
		if ((c == (char) KeyEvent.VK_ENTER)) {
			try {
				String pcompleto = txtNombre.getText();
				String[] parts = pcompleto.split("_");
				String id = parts[0]; 
				String nombre = parts[1]; 
				rs = model.buscarCliente(id);
				rs.next();
				if (rs.equals(null)) {
					JOptionPane.showMessageDialog(null, "Cliente no registrado");
					limpiar();
				} else {
					String documento = rs.getString("tipodoc");
					String nrodocumento = rs.getString("nrodoc");
					String direccion = rs.getString("direccion");
					String correo = rs.getString("correo");
					String telefono = rs.getString("telefono");
					
					ModificarCliente mc = new ModificarCliente(id, nombre, documento, nrodocumento, direccion, correo, telefono, this);
					mc.setVisible(true);
					this.setEnabled(false);
					txtNombre.setText("");
				}
			} catch (Exception e2) {
					limpiar();
			}
		}
	}

	public void keyTyped(KeyEvent arg0) {
	}

	protected void keyPressedTxtCodigo(KeyEvent arg0) {

	}

	public void limpiar() {
		txtNombre.setText(null);
	}

	public void selecionarCliente(String nrodoc) {
		int cantClientes = tbClientes.getRowCount();
		for (int i = 0; i < cantClientes; i++) {
			if (nrodoc.equals(tbClientes.getValueAt(i, 3))) {
				tbClientes.setRowSelectionInterval(i, i);
				break;
			}
		}
	}

	protected void actionPerformedBtnVolver(ActionEvent arg0) {
		EleccionVentanas el = new EleccionVentanas(usuario);
		el.setVisible(true);
		dispose();
	}
	protected void actionPerformedBtnCrear(ActionEvent arg0) {
		NuevoCliente nc = new NuevoCliente(this, null, usuario);
		nc.setVisible(true);
		nc.setLocationRelativeTo(null);
		this.setEnabled(false);		
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		DefaultTableModel tm = (DefaultTableModel) tbClientes.getModel();
		try {
			String id = String.valueOf(tm.getValueAt(tbClientes.getSelectedRow(), 0));
			String nombre = String.valueOf(tm.getValueAt(tbClientes.getSelectedRow(), 1));
			String documento = String.valueOf(tm.getValueAt(tbClientes.getSelectedRow(), 2));
			String nrodocumento = String.valueOf(tm.getValueAt(tbClientes.getSelectedRow(), 3));
			String direccion = String.valueOf(tm.getValueAt(tbClientes.getSelectedRow(), 4));
			String correo = String.valueOf(tm.getValueAt(tbClientes.getSelectedRow(), 5));
			String telefono = String.valueOf(tm.getValueAt(tbClientes.getSelectedRow(), 6));
			ModificarCliente mp = new ModificarCliente(id, nombre, documento, nrodocumento, direccion, correo, telefono, this);
			mp.setVisible(true);
			this.setEnabled(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione el producto a modificar");
		}
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "Por el momento el apartado está deshabilitado");
	}
}
