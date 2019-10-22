package gui_mantenimiento_usuarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import gui_mantenimiento_usuarios.ModificarUsuario;
import gui_mantenimiento_usuarios.NuevoUsuario;
import gui_principal.EleccionVentanas;
import mysql.consultas;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
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
import javax.swing.JTextField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class MantenimientoUsuarios extends JFrame implements ActionListener, WindowListener {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tbUsuarios;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTextField txtUsuarios;
	private JButton btnVolver;
	
	JTable tb;
	ResultSet rs;
	consultas model = new consultas();
	String usuario;
	
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

	public MantenimientoUsuarios(String temp2) {
		usuario = temp2;
				
		setTitle("Mantenimiento de Usuarios");
		setResizable(false);
		addWindowListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 694, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 658, 263);
		contentPane.add(scrollPane);
		
		tbUsuarios = new JTable();
		scrollPane.setViewportView(tbUsuarios);
		//tbUsuarios.getTableHeader().setResizingAllowed(false);
		tbUsuarios.getTableHeader().setReorderingAllowed(false) ;
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setFont(new Font("EngraversGothic BT", Font.BOLD, 28));
		btnAgregar.setBackground(new Color(30, 144, 255));
		btnAgregar.setBounds(10, 69, 205, 55);
		contentPane.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setFont(new Font("EngraversGothic BT", Font.BOLD, 28));
		btnModificar.setBackground(new Color(30, 144, 255));
		btnModificar.setBounds(232, 69, 219, 55);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFont(new Font("EngraversGothic BT", Font.BOLD, 28));
		btnEliminar.setBackground(new Color(30, 144, 255));
		btnEliminar.setBounds(461, 69, 205, 55);
		contentPane.add(btnEliminar);
		
		btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(this);
		btnVolver.setForeground(new Color(0, 255, 0));
		btnVolver.setFont(new Font("EngraversGothic BT", Font.BOLD | Font.ITALIC, 15));
		btnVolver.setBackground(Color.DARK_GRAY);
		btnVolver.setBounds(0, 0, 131, 58);
		contentPane.add(btnVolver);
		
		txtUsuarios = new JTextField();
		txtUsuarios.setForeground(new Color(255, 255, 255));
		txtUsuarios.setText("USUARIOS");
		txtUsuarios.setRequestFocusEnabled(false);
		txtUsuarios.setIgnoreRepaint(true);
		txtUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuarios.setFont(new Font("EngraversGothic BT", Font.BOLD, 32));
		txtUsuarios.setFocusable(false);
		txtUsuarios.setFocusTraversalKeysEnabled(false);
		txtUsuarios.setEditable(false);
		txtUsuarios.setColumns(10);
		txtUsuarios.setBackground(Color.DARK_GRAY);
		txtUsuarios.setBounds(0, 0, 688, 58);
		contentPane.add(txtUsuarios);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnAgregar, btnModificar, btnEliminar, scrollPane, tbUsuarios, btnVolver}));
		cargarUsuarios();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnVolver) {
			actionPerformedBtnVolver(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(arg0);
		}
	}
	public void cargarUsuarios(){
		this.setLocationRelativeTo(null);
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbUsuarios;
		tb.setModel(dtm);
		tb.setRowHeight(25);
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
	
	protected void actionPerformedBtnAgregar(ActionEvent arg0) {
		NuevoUsuario nu = new NuevoUsuario(this);
		nu.setVisible(true);
		nu.setLocationRelativeTo(null);
		nu.setAlwaysOnTop(true);
		this.setEnabled(false);
	}
	
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		try {
			DefaultTableModel tm = (DefaultTableModel) tbUsuarios.getModel();
			String usuario = String.valueOf(tm.getValueAt(tbUsuarios.getSelectedRow(),1));
			if( usuario.equals("admin"))
				JOptionPane.showMessageDialog(null, "Este usuario no se puede modificar por seguridad.");
			else{	 
				
					String usu = String.valueOf(tm.getValueAt(tbUsuarios.getSelectedRow(),1));
					String pass = String.valueOf(tm.getValueAt(tbUsuarios.getSelectedRow(),2));
					String nom = String.valueOf(tm.getValueAt(tbUsuarios.getSelectedRow(),0));
					String tip = String.valueOf(tm.getValueAt(tbUsuarios.getSelectedRow(),3));					
					ModificarUsuario mu = new ModificarUsuario(usu, pass, nom, tip, this);
					mu.setVisible(true);
					mu.setLocationRelativeTo(null);
					mu.setAlwaysOnTop(true);		
					this.setEnabled(false);
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione el usuario a modificar");
		}
		
		
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		int opc = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == 0){
			DefaultTableModel tm = (DefaultTableModel) tbUsuarios.getModel();
			String usuario = String.valueOf(tm.getValueAt(tbUsuarios.getSelectedRow(),1));
			if( usuario.equals("admin"))
				JOptionPane.showMessageDialog(null, "Este usuario no se puede borrar por seguridad.");
			else{
				try {
					model.eliminarUsuario(usuario);
					cargarUsuarios();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR: "+ e);
				}
			}
		}
	}
	
	public void seleccionarUsuario(String usu){
		int cantUsuarios = tbUsuarios.getRowCount();
		for(int i = 0; i<cantUsuarios; i++){
			if(usu.equals(tbUsuarios.getValueAt(i, 0))){
				tbUsuarios.setRowSelectionInterval(i,i);
				break;
			}
		}
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
		int opc = JOptionPane.showConfirmDialog(null, "¿Cerrar Sistema?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
	protected void actionPerformedBtnVolver(ActionEvent arg0) {
		EleccionVentanas el = new EleccionVentanas(usuario);
		el.setVisible(true);
		dispose();
	}
}
