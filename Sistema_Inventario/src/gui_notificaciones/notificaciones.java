package gui_notificaciones;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class notificaciones extends JInternalFrame implements ActionListener {
	private JMenuBar menuBar;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	private JTable tbXVencer;

	public VentanaPrincipal vp;
	JTable tbPV;
	JTable tbPA;
	ResultSet rs;
	consultas consulta = new consultas();
	DefaultTableModel dtm = new DefaultTableModel();
	private JLabel lblproductosPorVencer;
	private JLabel lblproductosPorAgotarse;
	private JScrollPane scrollPane_1;
	private JTable tbPorAgotar;
	private JButton btnMarcarVendido;
	private JLabel lblordenadosDelMs;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					notificaciones frame = new notificaciones(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public notificaciones(VentanaPrincipal vp) {
		this.vp = vp;

		getContentPane().setBackground(new Color(205, 92, 92));
		setTitle("NOTIFICACIONES");
		setBounds(100, 100, 1134, 679);
		getContentPane().setLayout(null);

		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(20, 69, 1073, 240);
		getContentPane().add(this.scrollPane);

		tbXVencer = new JTable();
		tbXVencer.setAutoCreateRowSorter(true);
		tbXVencer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbXVencer.setFont(new Font("Arial", Font.ITALIC, 14));
		tbXVencer.setBackground(Color.WHITE);
		tbXVencer.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbXVencer);
		
		lblproductosPorVencer = new JLabel("<html>PRODUCTOS VENCIDOS O POR VENCER</html>");
		lblproductosPorVencer.setHorizontalAlignment(SwingConstants.CENTER);
		lblproductosPorVencer.setForeground(new Color(255, 255, 255));
		lblproductosPorVencer.setFont(new Font("Candara", Font.BOLD, 25));
		lblproductosPorVencer.setBounds(210, 11, 684, 32);
		getContentPane().add(lblproductosPorVencer);
		
		lblproductosPorAgotarse = new JLabel("<html>PRODUCTOS POR AGOTARSE</html>");
		lblproductosPorAgotarse.setHorizontalAlignment(SwingConstants.CENTER);
		lblproductosPorAgotarse.setForeground(new Color(255, 255, 255));
		lblproductosPorAgotarse.setFont(new Font("Candara", Font.BOLD, 25));
		lblproductosPorAgotarse.setBounds(20, 332, 1073, 45);
		getContentPane().add(lblproductosPorAgotarse);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane_1.setAutoscrolls(true);
		scrollPane_1.setBounds(20, 377, 1073, 240);
		getContentPane().add(scrollPane_1);
		
		tbPorAgotar = new JTable();
		tbPorAgotar.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		tbPorAgotar.setAutoCreateRowSorter(true);
		tbPorAgotar.setFont(new Font("Arial", Font.ITALIC, 14));
		tbPorAgotar.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(tbPorAgotar);
		
		btnMarcarVendido = new JButton("Registrar como vendido");
		btnMarcarVendido.addActionListener(this);
		btnMarcarVendido.setForeground(Color.LIGHT_GRAY);
		btnMarcarVendido.setBackground(Color.DARK_GRAY);
		btnMarcarVendido.setFont(new Font("Candara", Font.BOLD | Font.ITALIC, 15));
		btnMarcarVendido.setBounds(876, 46, 217, 23);
		getContentPane().add(btnMarcarVendido);
		
		lblordenadosDelMs = new JLabel("<html>Ordenados del m\u00E1s pr\u00F3ximo al m\u00E1s lejano. Si ya vendi\u00F3 el producto, m\u00E1rquelo como vendido  ---></html>");
		lblordenadosDelMs.setHorizontalAlignment(SwingConstants.CENTER);
		lblordenadosDelMs.setForeground(Color.WHITE);
		lblordenadosDelMs.setFont(new Font("Candara", Font.ITALIC, 15));
		lblordenadosDelMs.setBounds(220, 46, 684, 23);
		getContentPane().add(lblordenadosDelMs);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbXVencer.getTableHeader().setReorderingAllowed(false);

		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);

		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null); // QUITA
																							// LA
																							// BARRA
																							// DE
																							// TÍTULO

		cargarPorVencer();
		cargarPorAgotar();
	}

	public void cargarPorVencer() {		
		tbPV = this.tbXVencer;
		tbPV.setRowHeight(30);
		tbPV.setModel(dtm);
		
		String atribTodos = "";
		try {
			consulta.iniciar();
			rs = consulta.cargarConfiguraciones();
			rs.next();
			atribTodos = rs.getString("atributosprod");
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

        List<String> list = new ArrayList<String>();
        
		String[] parts = atribTodos.split(",");
		//list.add("C BARRA");
		list.add("ID");
        list.add("PRODUCTO Y DETALLES");
       
		
		list.add("CANTIDAD");
		list.add("F. VENCIMIENTO");
		list.add("COMPROBANTE");
		String[] columnas = list.toArray(new String[list.size()]); 
		dtm.setColumnIdentifiers(columnas);
		
		int count = 0;
		
		try {
			consulta.iniciar();
			rs = consulta.buscarProductosPorVencer();

			while (rs.next()){
				if(rs.getInt("estado") == 1){
					List<String> listProds = new ArrayList<String>();
			        //listProds.add(rs.getString("codbarra"));
					listProds.add(rs.getString("codproducto") + "("+ rs.getString("idcompra")+")");
			        listProds.add(rs.getString("producto") + " - " + rs.getString("detalles") + " - " + rs.getString("marca") + " - " + rs.getString("color") + " - " + rs.getString("laboratorio") + " * " + rs.getString("unimedida"));
			      			        
			        listProds.add(rs.getString("cantidad"));
			        
			        try {
						// En esta linea de código estamos indicando el nuevo formato que queremos para nuestra fecha.
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						// Aqui usamos la instancia formatter para darle el formato a la fecha. Es importante ver que el resultado es un string.
						String fechaOrdenada = formatter.format(rs.getDate("fechaVenc"));
						listProds.add(fechaOrdenada);
					} catch (Exception e) {
						listProds.add("");
					}
			        
			        listProds.add(rs.getString("serie") + "-" + rs.getString("nroSerie"));
			        
			        String[] columnasProds = listProds.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
					dtm.addRow(columnasProds); // AGREGAMOS EL PRODUCTO A LA LISTA
					
					count++;
					//lblproductosPorVencer.setText("PRODUCTOS POR VENCER = " + count);
				}				
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS2: " + e);
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

		ajustarAnchoColumnasPV();
		
		
	}
	
	private void cargarPorAgotar(){
		DefaultTableModel dtm = new DefaultTableModel();
		tbPA = this.tbPorAgotar;
		tbPA.setRowHeight(30);
		tbPA.setModel(dtm);
		
		String atribTodos = "";
		try {
			consulta.iniciar();
			rs = consulta.cargarConfiguraciones();
			rs.next();
			atribTodos = rs.getString("atributosprod");
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

        List<String> list = new ArrayList<String>();
        //list.add("C BARRA");
        
        list.add("PRODUCTO Y DETALLES");
		list.add("STOCK ACTUAL");
		list.add("STOCK MINIMO");
		list.add("STOCK MÁXIMO");
        
		String[] columnas = list.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
		/*dtm.setColumnIdentifiers(new Object[] { "Codigo", "Producto", "Detalle","Categoría", "Marca", "Color",
				"F. Vencimiento", "Uni. Medida", "Cantidad", "PrecioCompra", "PrecioVenta" });*/
		dtm.setColumnIdentifiers(columnas);
		
		
		int count = 0;
		try {
			consulta.iniciar();
			rs = consulta.cargarProductos();

			while (rs.next()){
				Float cantmin = rs.getFloat("cantmin");
				Float cant = rs.getFloat("cantidad");
				if(rs.getInt("estado") == 1 && cant <= cantmin){
					List<String> listProds = new ArrayList<String>();
			        //listProds.add(rs.getString("codbarra"));
					listProds.add(rs.getString("producto") + " - " + rs.getString("detalles") + " - " + rs.getString("marca") + " - " + rs.getString("color") + " - " + rs.getString("laboratorio") + " * " + rs.getString("unimedida"));
   			        
				    listProds.add(rs.getString("cantidad"));
				        
			        listProds.add(rs.getString("cantmin"));
			        
			        listProds.add(rs.getString("cantmax"));
			        
			        String[] columnasProds = listProds.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
					dtm.addRow(columnasProds); // AGREGAMOS EL PRODUCTO A LA LISTA

					count++;
					lblproductosPorAgotarse.setText(count + " PRODUCTOS POR AGOTARSE");
				}				
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS2: " + e);
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
		

		ajustarAnchoColumnasPA();
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnasPV() {
		TableColumnModel tcm = tbXVencer.getColumnModel(); // 

		tcm.getColumn(0).setPreferredWidth(anchoColumna(10)); // Código
		tcm.getColumn(1).setPreferredWidth(anchoColumna(60)); // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(10)); // Cantidad
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10)); // Fecha
		tcm.getColumn(4).setPreferredWidth(anchoColumna(10)); // Comprobante
		
		for(int i=0; i<tbXVencer.getColumnCount(); i++)
			if(tbXVencer.getColumnName(i).equals("FECHA VENC."))
				tcm.getColumn(i).setPreferredWidth(anchoColumna(10)); // FECHA DE VENCIMIENTO
		
		/*DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(5).setCellRenderer(tcr2);*/
	}
	
	public void ajustarAnchoColumnasPA() {
		TableColumnModel tcm = tbPorAgotar.getColumnModel(); // 
		tcm.getColumn(0).setPreferredWidth(anchoColumna(55)); // Código
		tcm.getColumn(1).setPreferredWidth(anchoColumna(15)); // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(15)); // Detalle
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15));
		
		for(int i=0; i<tbPorAgotar.getColumnCount(); i++)
			if(tbPorAgotar.getColumnName(i).equals("UNI MED"))
				tcm.getColumn(i).setPreferredWidth(anchoColumna(10));
		
		/*DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(5).setCellRenderer(tcr2);*/
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMarcarVendido) {
			actionPerformedBtnMarcarVendido(e);
		}
	}
	
	protected void actionPerformedBtnMarcarVendido(ActionEvent e) {
		
		int opc = 0;
		opc = JOptionPane.showConfirmDialog(null, "Esta acción quitará al producto de esta lista. ¿Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
		
		if(opc == 0) {
			String prodycompra = tbXVencer.getValueAt(tbPV.getSelectedRow(), 0).toString();
			String codProducto = prodycompra.substring(0, prodycompra.indexOf("("));
			String idcompra = prodycompra.substring(prodycompra.indexOf("(") + 1, prodycompra.indexOf(")"));
			
			
			consulta.iniciar();
			consulta.marcarVendidoProducto(codProducto, idcompra);

			consulta.reset();
			
			int filaseleccionada = tbXVencer.getSelectedRow();
			dtm.removeRow(tbXVencer.getSelectedRow());
			tbXVencer.setRowSelectionInterval(filaseleccionada, filaseleccionada);
		}
		
	}
	
}

