package gui_compras;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
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
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.ListSelectionModel;

public class MantenimientoCompras extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnCrearCompra;
	private JMenu mnNewMenu_2;
	private JButton btnX;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	public JTable tbCompras;
	public VentanaPrincipal vp;
	

	NuevaCompra nc = new NuevaCompra(0, null);
	JTable tb;
	ResultSet rs;
	consultas consulta = new consultas();
	int idusuario = 0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MantenimientoCompras frame = new MantenimientoCompras(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MantenimientoCompras(VentanaPrincipal vp) {
		this.vp = vp;
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("USUARIOS");
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
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 41, 1083, 568);
		getContentPane().add(this.scrollPane);
		
		tbCompras = new JTable();
		tbCompras.setAutoCreateRowSorter(true);
		tbCompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCompras.setFont(new Font("Arial", Font.ITALIC, 14));
		tbCompras.setBackground(Color.WHITE);
		tbCompras.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbCompras);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbCompras.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);
		
		mnCrearCompra = new JMenu("|Registrar nueva compra| ");
		mnCrearCompra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnCrearCompra(e);
			}
		});
		mnCrearCompra.setForeground(new Color(30, 144, 255));
		mnCrearCompra.setBackground(SystemColor.control);
		mnCrearCompra.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnCrearCompra);
		
		JMenu mnModificarProducto = new JMenu("|Ver detalles de compra| ");
		mnModificarProducto.setForeground(new Color(60, 179, 113));
		mnModificarProducto.setBackground(SystemColor.control);
		mnModificarProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnModificarProducto);
		
		mnNewMenu_2 = new JMenu("|Agregar pago| ");
		mnNewMenu_2.setForeground(new Color(220, 20, 60));
		mnNewMenu_2.setBackground(SystemColor.control);
		mnNewMenu_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnNewMenu_2);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
	}
	
	public void cargar() {
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbCompras;
		tb.setRowHeight(30);
		tb.setModel(dtm);

		dtm.setColumnIdentifiers(new Object[]{"NRO", "SERIE", "DISTRIBUIDOR", "NOTA", "F EMISIÓN", "F VENCIMIENTO", "TOTAL", "SALDO"});
		
		try {
			consulta.iniciar();
			rs = consulta.cargarCompras();
			while(rs.next()){
				dtm.addRow(new Object[]{rs.getInt("idcompra"), rs.getString("serie")+" - " + rs.getString("nroSerie"), rs.getString("nombre"), rs.getString("nota"), rs.getString("fechaEmision"), rs.getString("fechaVencimiento"), rs.getFloat("tot"), rs.getFloat("saldo")});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar compras: " + e.getMessage());
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
		
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbCompras.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(5));  // 
		tcm.getColumn(1).setPreferredWidth(anchoColumna(3));  // 
		tcm.getColumn(2).setPreferredWidth(anchoColumna(70));  // 
		tcm.getColumn(3).setPreferredWidth(anchoColumna(11));  // 
		tcm.getColumn(4).setPreferredWidth(anchoColumna(11));  // 
		
		/*DefaultTableCellRenderer tcr0 = new DefaultTableCellRenderer();
		tcr0.setHorizontalAlignment(SwingConstants.CENTER);
		tbUsuarios.getColumnModel().getColumn(3).setCellRenderer(tcr0);*/
	}

	protected void actionPerformedBtnX(ActionEvent arg0) {
		this.dispose();
	}
	protected void mouseClickedMnCrearCompra(MouseEvent e) {
		try {
			idusuario = Integer.parseInt(vp.lblIdusuario.getText());
			if (nc.isShowing()) { //JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				nc.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				nc.setVisible(true); 
			} else {
				nc = new NuevaCompra(idusuario, this);
				nc.setLocationRelativeTo(null);
				nc.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
}
