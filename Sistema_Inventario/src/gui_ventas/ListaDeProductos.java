package gui_ventas;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import gui_principal.Login;
import gui_ventas.Ventas;
import mysql.consultas;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ListaDeProductos extends JDialog implements ActionListener, WindowListener, KeyListener {
	private JTable tbProductos;
	private JButton btnAgregar;
	private JTextField txtCantidad;
	private JScrollPane scrollPane;
	
	JTable tb;
	ResultSet rs;
	Login log = new Login();
	Ventas v = null;
	private JTextField txtListaCompletaDe;
	
	public static void main(String[] args) {
		try {
			ListaDeProductos dialog = new ListaDeProductos(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListaDeProductos(Ventas temp2){
		setResizable(false);
		setAlwaysOnTop(true);
		addWindowListener(this);
		v = temp2;
		setBounds(100, 100, 970, 645);
		getContentPane().setLayout(null);
		
		btnAgregar = new JButton("A\u00D1ADIR A LA LISTA");
		btnAgregar.addActionListener(this);
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setFont(new Font("EngraversGothic BT", Font.BOLD, 28));
		btnAgregar.setBackground(new Color(30, 144, 255));
		btnAgregar.setBounds(632, 540, 312, 55);
		getContentPane().add(btnAgregar);
		
		txtCantidad = new JTextField();
		txtCantidad.setText("1");
		txtCantidad.addKeyListener(this);
		txtCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCantidad.setFont(new Font("Tw Cen MT", Font.BOLD, 35));
		txtCantidad.setBounds(492, 540, 130, 55);
		getContentPane().add(txtCantidad);
		txtCantidad.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 934, 448);
		getContentPane().add(scrollPane);
		
		tbProductos = new JTable();
		tbProductos.setFont(new Font("Tw Cen MT", Font.BOLD | Font.ITALIC, 17));
		scrollPane.setViewportView(tbProductos);
		
		txtListaCompletaDe = new JTextField();
		txtListaCompletaDe.setText("LISTA COMPLETA DE PRODUCTOS");
		txtListaCompletaDe.setRequestFocusEnabled(false);
		txtListaCompletaDe.setIgnoreRepaint(true);
		txtListaCompletaDe.setHorizontalAlignment(SwingConstants.CENTER);
		txtListaCompletaDe.setForeground(Color.WHITE);
		txtListaCompletaDe.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));
		txtListaCompletaDe.setFocusable(false);
		txtListaCompletaDe.setFocusTraversalKeysEnabled(false);
		txtListaCompletaDe.setEditable(false);
		txtListaCompletaDe.setColumns(10);
		txtListaCompletaDe.setBackground(Color.DARK_GRAY);
		txtListaCompletaDe.setBounds(0, 0, 964, 58);
		getContentPane().add(txtListaCompletaDe);
		tbProductos.getTableHeader().setResizingAllowed(false);
		tbProductos.getTableHeader().setReorderingAllowed(false) ;
		cargarDatos();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(arg0);
		}
	}
	protected void actionPerformedBtnAgregar(ActionEvent arg0) {
		try {
			if(txtCantidad.getText().equals("")){
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Ingrese una cantidad");
				this.setAlwaysOnTop(true);
			}
			else{
				String cantidad = txtCantidad.getText();
				String codigo = tbProductos.getValueAt(tbProductos.getSelectedRow(), 0).toString();
				int rpt = v.AnadirProductosdeListaCompleta(codigo, cantidad);
				if(rpt == 1){
					v.dtm.addRow(new Object[]{cantidad, tbProductos.getValueAt(tbProductos.getSelectedRow(), 1), tbProductos.getValueAt(tbProductos.getSelectedRow(), 2), tbProductos.getValueAt(tbProductos.getSelectedRow(), 4), tbProductos.getValueAt(tbProductos.getSelectedRow(), 5), "", tbProductos.getValueAt(tbProductos.getSelectedRow(), 0),tbProductos.getValueAt(tbProductos.getSelectedRow(), 6)});
					v.seleccionarRow();
				}
				v.sumarSubTotales();
				v.sumarTotal();
			}
		} catch (Exception e) {
			this.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(null, "Seleccione un producto");
			this.setAlwaysOnTop(true);
		}
	}
	private void cargarDatos(){
		this.setLocationRelativeTo(null);
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbProductos;
		tb.setModel(dtm);
		tbProductos.setRowHeight(30);
		dtm.setColumnIdentifiers(new Object[]{"Codigo", "Producto", "Detalle", "Uni. Medida", "Cantidad","PrecioVenta","PrecioComp"});
		consultas model = new consultas();
		rs = model.cargarProductos();
		try {
			while(rs.next())
				dtm.addRow(new Object[]{rs.getString("codproducto"), rs.getString("producto"), rs.getString("detalles"), rs.getString("unimedida"), rs.getFloat("cantidad"), rs.getFloat("precioVe"), rs.getFloat("precioCo")});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		ajustarAnchoColumnas();
	}
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbProductos.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(10));  // Codigo
		tcm.getColumn(1).setPreferredWidth(anchoColumna(30));  // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(25));  // Detalle
		tcm.getColumn(3).setPreferredWidth(anchoColumna(12));  // Uni. Medida
		tcm.getColumn(4).setPreferredWidth(anchoColumna(11));  // Stock
		tcm.getColumn(5).setPreferredWidth(anchoColumna(12));  // PrecioC
		tcm.getColumn(6).setPreferredWidth(anchoColumna(0));  // PrecioV
		
		/*DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(0).setCellRenderer(tcr);*/
		
		DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(3).setCellRenderer(tcr2);
		
		DefaultTableCellRenderer tcr3 = new DefaultTableCellRenderer();
		tcr3.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(4).setCellRenderer(tcr3);
		
		DefaultTableCellRenderer tcr4 = new DefaultTableCellRenderer();
		tcr4.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(6).setCellRenderer(tcr4);
	}
	
	public void windowActivated(WindowEvent arg0) {
	}
	public void windowClosed(WindowEvent arg0) {
		if (arg0.getSource() == this) {
			windowClosedThis(arg0);
		}
	}
	public void windowClosing(WindowEvent arg0) {
		v.setEnabled(true);
		v.setVisible(true);	
	}
	public void windowDeactivated(WindowEvent arg0) {
	}
	public void windowDeiconified(WindowEvent arg0) {
	}
	public void windowIconified(WindowEvent arg0) {
	}
	public void windowOpened(WindowEvent arg0) {
	}
	protected void windowClosedThis(WindowEvent arg0) {
			
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtCantidad) {
			txtCantidadKeyTyped(arg0);
		}
	}
	protected void txtCantidadKeyTyped(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if ((c<'0' || c>'9') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER) && (c!= '.')){
			arg0.consume();
		}
		if (txtCantidad.getText().length() == 10){
			arg0.consume();
		}
		if (c == '.' && txtCantidad.getText().contains(".")) {
			arg0.consume();
		}
		if(c==(char)KeyEvent.VK_ENTER)
			actionPerformedBtnAgregar(null);
	}
}
