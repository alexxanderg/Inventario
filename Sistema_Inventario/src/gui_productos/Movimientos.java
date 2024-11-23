package gui_productos;

import com.mxrck.autocompleter.TextAutoCompleter;

import clases.PintarTablaVentasBuscar;
import clases.Usuarios;
import gui_principal.VentanaPrincipal;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import mysql.consultas;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.SwingConstants;

public class Movimientos extends JInternalFrame {
	private JMenuBar menuBar;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	private JTable tbProductos;
	JTable tb;
	ResultSet rs;
	consultas consulta = new consultas();
	ModificarProducto mp = null;
	Modelaso dtm = new Modelaso();
	consultas model = new consultas();
	String usuario;
	public VentanaPrincipal vp;
	private JLabel lblKardex;
	private JLabel lblNewLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Movimientos frame = new Movimientos(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Movimientos(VentanaPrincipal vp) {
		this.vp = vp;
		this.usuario = vp.lblUsuario.getText();
		getContentPane().setBackground(Color.WHITE);
		setTitle("ALMAC�N");
		setBounds(100, 100, 1134, 679);
		getContentPane().setLayout(null);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		this.scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 74, 1083, 535);
		getContentPane().add(this.scrollPane);

		this.tbProductos = new JTable();
		this.tbProductos.setAutoCreateRowSorter(true);
		this.tbProductos.setSelectionMode(0);
		this.tbProductos.setFont(new Font("Arial", 2, 12));
		this.tbProductos.setBackground(Color.WHITE);
		this.tbProductos.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		this.scrollPane.setViewportView(this.tbProductos);

		this.lblKardex = new JLabel("MOVIMIENTOS DE PRODUCTOS");
		lblKardex.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblKardex.setForeground(Color.BLACK);
		this.lblKardex.setFont(new Font("Candara", 1, 30));
		this.lblKardex.setBounds(265, 14, 456, 52);
		getContentPane().add(this.lblKardex);
		
		lblNewLabel = new JLabel("<html>Nota: Solo se solo puede eliminar tranferencias.<br>\r\nLas compras y ventas debe realizarlo desde sus ventanas específicas.</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(219, 112, 147));
		lblNewLabel.setBounds(752, 14, 341, 66);
		getContentPane().add(lblNewLabel);

		this.tbProductos.getTableHeader().setReorderingAllowed(false);

		this.menuBar = new JMenuBar();
		this.menuBar.setCursor(Cursor.getPredefinedCursor(12));
		this.menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(this.menuBar);

		JMenu mnaadirStock = new JMenu("|Eliminar movimiento| ");
		mnaadirStock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Movimientos.this.mouseClickedMnaadirStock(e);
			}
		});
		mnaadirStock.setForeground(new Color(220, 20, 60));
		mnaadirStock.setFont(new Font("Tahoma", 1, 20));
		mnaadirStock.setBackground(SystemColor.menu);
		this.menuBar.add(mnaadirStock);

		((BasicInternalFrameUI) getUI()).setNorthPane(null);
		
		cargar();
		cargarDatos();
	}

	public void cargar() {
		this.tb = this.tbProductos;
		this.tb.setRowHeight(40);
		this.tb.setModel(this.dtm);

		dtm.setColumnIdentifiers(new Object[]{"ID", "FECHA", "PRODUCTO", "REFEENCIA", "INGRESO", "SALIDA", "TIENDA", "T1","T2","T3","T4"});
		tbProductos.setRowHeight(30);
		tbProductos.setModel(dtm);
		
		
		
		/*java.util.Date date = new Date();
		date.getTime();
		dchDesde.setDate(date);
		dchHasta.setDate(date);*/
		
		ajustarAnchoColumnas();
		
	}
	
	public void cargarDatos() {

		try {
			consulta.iniciar();
			rs = consulta.cargarMovimientos();
			
			tbProductos.setModel(dtm);
			while(rs.next()){
				dtm.addRow(new Object[]{
						rs.getInt("idmovimiento"), 
						rs.getDate("fecha"), 
						rs.getString("producto"), 
						rs.getString("documento"), 
						rs.getString("ingreso"),
						rs.getString("salida"),
						rs.getString("tienda"),
						rs.getFloat("stock1"),
						rs.getFloat("stock2"),
						rs.getFloat("stock3"),
						rs.getFloat("stock4")});	
			}
			
			//this.tbProductos.setDefaultRenderer(Object.class, new PintarTablaVentasBuscar());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar movimientos: " + e.getMessage());
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

	public class Modelaso extends DefaultTableModel {

		 public boolean isCellEditable (int row, int column)
		    {
			 // Aqu� devolvemos true o false seg�n queramos que una celda
		        // identificada por fila,columna (row,column), sea o no editable
		        if (column == 0 || column == 1 ||column == 2 ||column == 3 ||column == 5)
		           return false;
		        return true;
		    }
	}
	public void cargarBuscador() {
		try {
			this.consulta.iniciar();
			ResultSet rs = this.consulta.cargarProductos();
			this.ac.setMode(0);

			while (rs.next())
				this.ac.addItem(rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca")
						+ " " + rs.getString("color") + " " + rs.getString("laboratorio") + " " + rs.getString("lote")
						+ " * " + rs.getString("unimedida") + "  -  (" + rs.getString("codproducto") + ")");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar buscador: " + e);
			try {
				if (this.rs != null)
					this.rs.close();
				if (this.consulta != null)
					this.consulta.reset();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
			}
		} finally {
			try {
				if (this.rs != null)
					this.rs.close();
				if (this.consulta != null)
					this.consulta.reset();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
			}
		}
	}

	private void cargarKardexCero() {
		int cantProductos = this.tbProductos.getRowCount();
		for (int j = 0; j < cantProductos; j++)
			for (int i = 0; i < this.tbProductos.getColumnCount(); i++)
				if (this.tbProductos.getColumnName(i).equals("CONTEO"))
					this.tbProductos.setValueAt(Integer.valueOf(0), j, i);
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * this.scrollPane.getWidth() / 100;
	}

	//"ID", "FECHA", "PRODUCTO", "REFEENCIA", "INGRESO", "SALIDA", "TIENDA", "T1","T2","T3","T4"

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = this.tbProductos.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(5));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(35));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(5));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(5));
		tcm.getColumn(6).setPreferredWidth(anchoColumna(5));
		tcm.getColumn(7).setPreferredWidth(anchoColumna(5));
		tcm.getColumn(8).setPreferredWidth(anchoColumna(5));
		tcm.getColumn(9).setPreferredWidth(anchoColumna(5));
		tcm.getColumn(10).setPreferredWidth(anchoColumna(5));

		for (int i = 0; i < this.tbProductos.getColumnCount(); i++)
			if (this.tbProductos.getColumnName(i).equals("FECHA VENC."))
				tcm.getColumn(i).setPreferredWidth(anchoColumna(10));
	}

	public void selecionarProducto(String id) {
		int cantProductos = this.tbProductos.getRowCount();
		for (int i = 0; i < cantProductos; i++)
			if (id.equals(this.tbProductos.getValueAt(i, 0))) {
				this.tbProductos.setRowSelectionInterval(i, i);
				break;
			}
	}

	private void abrirModificarProducto(String idProd) {
	}

	private void elminarProducto(String codigoProducto) {
	}

	private void limpiarTabla() {
		for (int i = 0; i < this.tbProductos.getRowCount(); i++) {
			this.dtm.removeRow(i);
			i--;
		}
	}

	protected void mouseClickedMnaadirStock(MouseEvent arg0) {

		
		
	}

	public double redondearDecimales(double valorInicial, int numeroDecimales) {
		double resultado = valorInicial;
		double parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10.0D, numeroDecimales);
		resultado = Math.round(resultado);
		resultado = resultado / Math.pow(10.0D, numeroDecimales) + parteEntera;
		return resultado;
	}
}