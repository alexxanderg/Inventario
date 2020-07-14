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

public class notificaciones extends JInternalFrame {
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

		getContentPane().setBackground(new Color(220, 20, 60));
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
		
		lblproductosPorVencer = new JLabel("<html>15 PRODUCTOS M\u00C1S PR\u00D3XIMOS A VENCER</html>");
		lblproductosPorVencer.setHorizontalAlignment(SwingConstants.CENTER);
		lblproductosPorVencer.setForeground(new Color(255, 255, 255));
		lblproductosPorVencer.setFont(new Font("Candara", Font.BOLD, 25));
		lblproductosPorVencer.setBounds(20, 24, 1073, 45);
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
        list.add("C BARRA");
        list.add("NOMBRE");
        list.add("DESCRIPCIÓN");
		String[] parts = atribTodos.split(",");
		for (int x=0; x<parts.length; x++){
			if(parts[x].equals("marca"))
				list.add("MARCA");
			if(parts[x].equals("color"))
				list.add("COLOR");
			if(parts[x].equals("lote"))
				list.add("LOTE");
			if(parts[x].equals("laboratorio"))
				list.add("LAB");
			if(parts[x].equals("fvencimiento"))
				list.add("FECHA VENC.");
		}
		list.add("UNI MED");
		list.add("CATEGORIA");
		list.add("ALMACÉN");
		list.add("DISTRIBUIDOR");
		list.add("STOCK");
		String[] columnas = list.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
		/*dtm.setColumnIdentifiers(new Object[] { "Codigo", "Producto", "Detalle","Categoría", "Marca", "Color",
				"F. Vencimiento", "Uni. Medida", "Cantidad", "PrecioCompra", "PrecioVenta" });*/
		dtm.setColumnIdentifiers(columnas);
		
		int count = 0;
		
		try {
			consulta.iniciar();
			rs = consulta.buscarProductosPorVencer();

			while (rs.next()){
				if(rs.getInt("estado") == 1){
					List<String> listProds = new ArrayList<String>();
			        listProds.add(rs.getString("codbarra"));
			        listProds.add(rs.getString("producto"));
			        listProds.add(rs.getString("detalles"));
			        for (int x=0; x<parts.length; x++){
						if(parts[x].equals("marca"))
							listProds.add(rs.getString("marca"));
						if(parts[x].equals("color"))
							listProds.add(rs.getString("color"));
						if(parts[x].equals("lote"))
							listProds.add(rs.getString("lote"));
						if(parts[x].equals("laboratorio"))
							listProds.add(rs.getString("laboratorio"));
						if(parts[x].equals("fvencimiento")){
							try {
								// En esta linea de código estamos indicando el nuevo formato que queremos para nuestra fecha.
								SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
								// Aqui usamos la instancia formatter para darle el formato a la fecha. Es importante ver que el resultado es un string.
								String fechaOrdenada = formatter.format(rs.getDate("fechaVenc"));
								listProds.add(fechaOrdenada);
							} catch (Exception e) {
								listProds.add("");
							}
						}
					}
			        listProds.add(rs.getString("unimedida"));
			        listProds.add(rs.getString("categoria"));
			        listProds.add(rs.getString("almacen"));
			        
			        int iddistrib = rs.getInt("iddistrib");
			        try {
			        	 ResultSet rs2 = consulta.buscarDistribuidor(iddistrib);
			        	 rs2.next();
					     listProds.add(rs2.getString("nombre"));		        	 
					} catch (Exception e) {}
			        
			        listProds.add(rs.getString("cantidad"));
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
        list.add("C BARRA");
        list.add("NOMBRE");
        list.add("DESCRIPCIÓN");
		String[] parts = atribTodos.split(",");
		for (int x=0; x<parts.length; x++){
			if(parts[x].equals("marca"))
				list.add("MARCA");
			if(parts[x].equals("color"))
				list.add("COLOR");
			if(parts[x].equals("lote"))
				list.add("LOTE");
			if(parts[x].equals("laboratorio"))
				list.add("LAB");
			if(parts[x].equals("fvencimiento"))
				list.add("FECHA VENC.");
		}
		list.add("UNI MED");
		list.add("CATEGORIA");
		list.add("ALMACÉN");
		list.add("DISTRIBUIDOR");
		list.add("STOCK");
		list.add("STOCK MIN");
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
			        listProds.add(rs.getString("codbarra"));
			        listProds.add(rs.getString("producto"));
			        listProds.add(rs.getString("detalles"));
			        for (int x=0; x<parts.length; x++){
						if(parts[x].equals("marca"))
							listProds.add(rs.getString("marca"));
						if(parts[x].equals("color"))
							listProds.add(rs.getString("color"));
						if(parts[x].equals("lote"))
							listProds.add(rs.getString("lote"));
						if(parts[x].equals("laboratorio"))
							listProds.add(rs.getString("laboratorio"));
						if(parts[x].equals("fvencimiento")){
							try {
								// En esta linea de código estamos indicando el nuevo formato que queremos para nuestra fecha.
								SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
								// Aqui usamos la instancia formatter para darle el formato a la fecha. Es importante ver que el resultado es un string.
								String fechaOrdenada = formatter.format(rs.getDate("fechaVenc"));
								listProds.add(fechaOrdenada);
							} catch (Exception e) {
								listProds.add("");
							}
						}
					}
			        listProds.add(rs.getString("unimedida"));
			        listProds.add(rs.getString("categoria"));
			        listProds.add(rs.getString("almacen"));
			        
			        int iddistrib = rs.getInt("iddistrib");
			        try {
			        	 ResultSet rs2 = consulta.buscarDistribuidor(iddistrib);
			        	 rs2.next();
					     listProds.add(rs2.getString("nombre"));		        	 
					} catch (Exception e) {}
			        
			        listProds.add(rs.getString("cantidad"));
			        listProds.add(rs.getString("cantmin"));
			        
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
		tcm.getColumn(0).setPreferredWidth(anchoColumna(5)); // Código
		tcm.getColumn(1).setPreferredWidth(anchoColumna(15)); // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(15)); // Detalle
		
		for(int i=0; i<tbXVencer.getColumnCount(); i++)
			if(tbXVencer.getColumnName(i).equals("FECHA VENC."))
				tcm.getColumn(i).setPreferredWidth(anchoColumna(10)); // FECHA DE VENCIMIENTO
		
		/*DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(5).setCellRenderer(tcr2);*/
	}
	
	public void ajustarAnchoColumnasPA() {
		TableColumnModel tcm = tbPorAgotar.getColumnModel(); // 
		tcm.getColumn(0).setPreferredWidth(anchoColumna(5)); // Código
		tcm.getColumn(1).setPreferredWidth(anchoColumna(15)); // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(15)); // Detalle
		
		for(int i=0; i<tbPorAgotar.getColumnCount(); i++)
			if(tbPorAgotar.getColumnName(i).equals("FECHA VENC."))
				tcm.getColumn(i).setPreferredWidth(anchoColumna(10)); // FECHA DE VENCIMIENTO
		
		/*DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(5).setCellRenderer(tcr2);*/
	}
}
