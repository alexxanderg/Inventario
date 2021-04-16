package gui_ventas;

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
import mysql.MySQLConexion;
import mysql.consultas;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import clases.AbstractJasperReports;
import clases.Cliente;
import clases.PintarTablaVentasBuscar;
import clases.Usuarios;
import javax.swing.border.EmptyBorder;

public class BuscarVentas extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnModificarProducto;
	private JMenu mnEliminarVenta;
	private JScrollPane scrollPane;
	private JTable tbVentas;
	private JButton btnVerVentas;
	private JComboBox <Usuarios>cbUsuarios;
	
	private JLabel lblVendedor;
	private JLabel lblDesde;
	private JDateChooser dchDesde;
	private JLabel lblHasta;
	private JDateChooser dchHasta;
	private JLabel lblTV;
	private JLabel lblTotVentas;
	private JLabel lblTotDescuentos;
	private JLabel lblTD;
	
	public VentanaPrincipal vp;
	ResultSet rs;
	consultas consulta = new consultas();
	DefaultTableModel dtm = new DefaultTableModel();
	DefaultTableModel dtmVD = new DefaultTableModel();
	private JButton btnGenerarReporte;
	private JLabel lblBuscarVentas;
	private JScrollPane scrollPane_1;
	private JLabel lblDetallesDeVenta;
	private JTable tbDetalleVenta;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel lblVentasModificadas;
	private JLabel lblVentasEliminadas;
	private JMenu mnactualizarNotaDe;
	private JMenu mnimprimirCopiaDe;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarVentas frame = new BuscarVentas(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BuscarVentas(VentanaPrincipal vp) {
		this.vp = vp;
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("HISTORIAL DE VENTAS");
		setBounds(100, 100, 1134, 679);
		getContentPane().setLayout(null);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 78, 1083, 210);
		getContentPane().add(this.scrollPane);
		
		tbVentas = new JTable();
		tbVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedTbVentas(arg0);
			}
		});
		tbVentas.setAutoCreateRowSorter(true);
		tbVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbVentas.setFont(new Font("Arial", Font.ITALIC, 14));
		tbVentas.setBackground(Color.WHITE);
		tbVentas.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbVentas);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbVentas.getTableHeader().setReorderingAllowed(false);
		
		btnVerVentas = new JButton("Buscar");
		btnVerVentas.setBackground(new Color(30, 144, 255));
		btnVerVentas.setForeground(Color.WHITE);
		btnVerVentas.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVerVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnVerVentas(arg0);
			}
		});
		btnVerVentas.setBounds(829, 38, 123, 30);
		getContentPane().add(btnVerVentas);
		
		cbUsuarios = new JComboBox();
		cbUsuarios.setFont(new Font("Arial", Font.ITALIC, 18));
		cbUsuarios.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbUsuarios.setBackground(new Color(245, 245, 245));
		cbUsuarios.setBounds(312, 38, 227, 30);
		getContentPane().add(cbUsuarios);
		
		lblVendedor = new JLabel("Vendido por:");
		lblVendedor.setForeground(Color.DARK_GRAY);
		lblVendedor.setFont(new Font("Candara", Font.BOLD, 20));
		lblVendedor.setBounds(312, 13, 123, 30);
		getContentPane().add(lblVendedor);
		
		lblDesde = new JLabel("Desde:");
		lblDesde.setHorizontalAlignment(SwingConstants.LEFT);
		lblDesde.setForeground(Color.DARK_GRAY);
		lblDesde.setFont(new Font("Candara", Font.BOLD, 20));
		lblDesde.setBackground(new Color(50, 205, 50));
		lblDesde.setBounds(549, 11, 71, 30);
		getContentPane().add(lblDesde);
		
		dchDesde = new JDateChooser();
		dchDesde.setBounds(549, 38, 130, 30);
		getContentPane().add(dchDesde);
		
		lblHasta = new JLabel("Hasta:");
		lblHasta.setHorizontalAlignment(SwingConstants.LEFT);
		lblHasta.setForeground(Color.DARK_GRAY);
		lblHasta.setFont(new Font("Candara", Font.BOLD, 20));
		lblHasta.setBackground(new Color(50, 205, 50));
		lblHasta.setBounds(689, 11, 71, 30);
		getContentPane().add(lblHasta);
		
		dchHasta = new JDateChooser();
		dchHasta.setBounds(689, 38, 130, 30);
		getContentPane().add(dchHasta);
		
		lblTV = new JLabel("TOTAL VENTAS S/ ");
		lblTV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTV.setForeground(new Color(30, 144, 255));
		lblTV.setFont(new Font("Candara", Font.BOLD, 25));
		lblTV.setBackground(new Color(50, 205, 50));
		lblTV.setBounds(754, 291, 221, 36);
		getContentPane().add(lblTV);
		
		lblTotVentas = new JLabel("0");
		lblTotVentas.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotVentas.setForeground(new Color(30, 144, 255));
		lblTotVentas.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTotVentas.setBackground(new Color(50, 205, 50));
		lblTotVentas.setBounds(985, 290, 108, 36);
		getContentPane().add(lblTotVentas);
		
		lblTotDescuentos = new JLabel("0");
		lblTotDescuentos.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotDescuentos.setForeground(new Color(205, 92, 92));
		lblTotDescuentos.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTotDescuentos.setBackground(new Color(50, 205, 50));
		lblTotDescuentos.setBounds(662, 291, 95, 36);
		getContentPane().add(lblTotDescuentos);
		
		lblTD = new JLabel("TOTAL DE DESCUENTOS S/ ");
		lblTD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTD.setForeground(new Color(205, 92, 92));
		lblTD.setFont(new Font("Candara", Font.BOLD, 25));
		lblTD.setBackground(new Color(50, 205, 50));
		lblTD.setBounds(355, 291, 296, 36);
		getContentPane().add(lblTD);
		
		btnGenerarReporte = new JButton("<html><center>EXPORTAR<br>VENTAS</center></html>");
		btnGenerarReporte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGenerarReporte.setBorder(new LineBorder(new Color(138, 43, 226), 3, true));
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnGenerarReporte(arg0);
			}
		});
		btnGenerarReporte.setForeground(new Color(138, 43, 226));
		btnGenerarReporte.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGenerarReporte.setBackground(new Color(255, 255, 255));
		btnGenerarReporte.setBounds(962, 10, 131, 57);
		getContentPane().add(btnGenerarReporte);
		
		lblBuscarVentas = new JLabel("<html>Historial<br>de ventas:</html>");
		lblBuscarVentas.setForeground(Color.DARK_GRAY);
		lblBuscarVentas.setFont(new Font("Candara", Font.BOLD, 25));
		lblBuscarVentas.setBounds(10, 11, 123, 56);
		getContentPane().add(lblBuscarVentas);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane_1.setAutoscrolls(true);
		scrollPane_1.setBounds(10, 401, 1083, 210);
		getContentPane().add(scrollPane_1);
		
		tbDetalleVenta = new JTable();
		tbDetalleVenta.setAutoCreateRowSorter(true);
		tbDetalleVenta.setFont(new Font("Arial", Font.ITALIC, 14));
		scrollPane_1.setViewportView(tbDetalleVenta);
		
		lblDetallesDeVenta = new JLabel("Detalles de venta:");
		lblDetallesDeVenta.setVerticalAlignment(SwingConstants.TOP);
		lblDetallesDeVenta.setForeground(Color.DARK_GRAY);
		lblDetallesDeVenta.setFont(new Font("Candara", Font.BOLD, 30));
		lblDetallesDeVenta.setBounds(10, 356, 396, 34);
		getContentPane().add(lblDetallesDeVenta);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(152, 19, 13, 13);
		textField.setBackground(new Color(138, 230, 78)); //VERDE
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(152, 36, 13, 13);
		textField_1.setBackground(new Color(236, 236, 69)); //AMARILLO
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(152, 54, 13, 13);
		textField_2.setBackground(new Color(251, 105, 120)); //ROJO
		getContentPane().add(textField_2);
		
		lblNewLabel = new JLabel("Ventas correctas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(175, 19, 95, 14);
		getContentPane().add(lblNewLabel);
		
		lblVentasModificadas = new JLabel("Ventas modificadas");
		lblVentasModificadas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblVentasModificadas.setBounds(175, 36, 108, 14);
		getContentPane().add(lblVentasModificadas);
		
		lblVentasEliminadas = new JLabel("Ventas eliminadas");
		lblVentasEliminadas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblVentasEliminadas.setBounds(175, 53, 95, 14);
		getContentPane().add(lblVentasEliminadas);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);
		
		mnModificarProducto = new JMenu("|Modificar Venta| ");
		mnModificarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnModificarProducto(e);
			}
		});
		
		mnactualizarNotaDe = new JMenu("|Actualizar Nota de la venta| ");
		mnactualizarNotaDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedMnactualizarNotaDe(arg0);
			}
		});
		mnactualizarNotaDe.setForeground(new Color(255, 215, 0));
		mnactualizarNotaDe.setFont(new Font("Tahoma", Font.BOLD, 20));
		mnactualizarNotaDe.setBackground(SystemColor.menu);
		menuBar.add(mnactualizarNotaDe);
		mnModificarProducto.setForeground(new Color(50, 205, 50));
		mnModificarProducto.setBackground(SystemColor.control);
		mnModificarProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnModificarProducto);
		
		mnEliminarVenta = new JMenu("|Eliminar venta| ");
		mnEliminarVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnNewMenu_2(e);
			}
		});
		mnEliminarVenta.setForeground(new Color(240, 128, 128));
		mnEliminarVenta.setBackground(SystemColor.control);
		mnEliminarVenta.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnEliminarVenta);
		
		mnimprimirCopiaDe = new JMenu("|Imprimir copia de ticket| ");
		mnimprimirCopiaDe.setVisible(false);
		mnimprimirCopiaDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnimprimirCopiaDe(e);
			}
		});
		mnimprimirCopiaDe.setForeground(new Color(218, 112, 214));
		mnimprimirCopiaDe.setFont(new Font("Tahoma", Font.BOLD, 20));
		mnimprimirCopiaDe.setBackground(SystemColor.menu);
		menuBar.add(mnimprimirCopiaDe);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
		//ajustarAnchoColumnas();
	}
	
	public void cargar() {
		
		dtm.setColumnIdentifiers(new Object[]{"NRO", "CLIENTE", "VENDEDOR", "NOTA", "FECHA", "DESCUENTO TOT", "TOTAL"});
		tbVentas.setRowHeight(30);
		tbVentas.setModel(dtm);
		
		tbDetalleVenta.setRowHeight(30);
		tbDetalleVenta.setModel(dtmVD);
		dtmVD.setColumnIdentifiers(new Object[]{"CANTIDAD", "PRODUCTO", "PRE INDIV C/DESC", "DESCUENTO TOT", "SUB TOTAL"});
		
		
		Usuarios todos = new Usuarios(0, "TODOS", "TODOS", "TODOS", 0);
		cbUsuarios.addItem(todos);
		
		Usuarios vendedores = new Usuarios();
		vendedores.cargarUsuarios(cbUsuarios);

		int idUsuario = Integer.parseInt(vp.lblIdusuario.getText());
		
		for(int i = 0; i<cbUsuarios.getItemCount(); i++)
			if(cbUsuarios.getItemAt(i).getIdusuario() == idUsuario)
				cbUsuarios.setSelectedIndex(i);
		
		java.util.Date date = new Date();
		date.getTime();
		dchDesde.setDate(date);
		dchHasta.setDate(date);
		
		ajustarAnchoColumnas();
	}
	
	private void recargar(){
		actionPerformedBtnVerVentas(null);
		
	}
		
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbVentas.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(8));   // 
		tcm.getColumn(1).setPreferredWidth(anchoColumna(21));  // 
		tcm.getColumn(2).setPreferredWidth(anchoColumna(21));  // 
		tcm.getColumn(3).setPreferredWidth(anchoColumna(18));  // 
		tcm.getColumn(4).setPreferredWidth(anchoColumna(19));  // 
		tcm.getColumn(5).setPreferredWidth(anchoColumna(7));
		tcm.getColumn(6).setPreferredWidth(anchoColumna(6));
		
		DefaultTableCellRenderer tcr0 = new DefaultTableCellRenderer();
		tcr0.setHorizontalAlignment(SwingConstants.CENTER);
		tbVentas.getColumnModel().getColumn(0).setCellRenderer(tcr0);
		tbVentas.getColumnModel().getColumn(4).setCellRenderer(tcr0);
		tbVentas.getColumnModel().getColumn(5).setCellRenderer(tcr0);
		tbVentas.getColumnModel().getColumn(6).setCellRenderer(tcr0);
		
		TableColumnModel tcmVD = tbDetalleVenta.getColumnModel();
		tcmVD.getColumn(0).setPreferredWidth(anchoColumna(15));  // 
		tcmVD.getColumn(1).setPreferredWidth(anchoColumna(40));  // 
		tcmVD.getColumn(2).setPreferredWidth(anchoColumna(15));  //
		tcmVD.getColumn(3).setPreferredWidth(anchoColumna(15));  //
		tcmVD.getColumn(4).setPreferredWidth(anchoColumna(15));  //
		
		tbDetalleVenta.getColumnModel().getColumn(0).setCellRenderer(tcr0);
		tbDetalleVenta.getColumnModel().getColumn(2).setCellRenderer(tcr0);
		tbDetalleVenta.getColumnModel().getColumn(3).setCellRenderer(tcr0);
		tbDetalleVenta.getColumnModel().getColumn(4).setCellRenderer(tcr0);
	}
	
	public void selecionarUsuario(String id) {
	}
	protected void mouseClickedMnModificarProducto(MouseEvent e) {
		try {
			int nroVenta = Integer.parseInt( tbVentas.getValueAt(tbVentas.getSelectedRow(), 0).toString() );
			float subTotal = Float.parseFloat( tbVentas.getValueAt(tbVentas.getSelectedRow(), 6).toString() );
			
			if(subTotal == 0){
				JOptionPane.showMessageDialog(null, "No puede modificar ventas eliminadas");
			}
			else{
				int seleccion = JOptionPane.showConfirmDialog(null, "MODIFICAR VENTA\n\nALERTA! Al dar en SI, toda la información de la venta será borrada y a la vez cargada en la ventana de Ventas, \npara que pueda realizar las modificaciones que necesite.\nNO salga de la ventana de Ventas sin antes realizar las modificaciones requeridas. Caso contrario, la venta quedará en Cero.\n\n¿Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if(seleccion == 0){
					//JOptionPane.showMessageDialog(null, "Se acaba de cargar toda la venta realizada. \nModifique lo que necesita y luego de clic en VENDER.\n\nNO se efectuará ningun cambio, hasta que lo haga.");	
					try {
						int nroVentaModificar = Integer.parseInt( tbVentas.getValueAt(tbVentas.getSelectedRow(), 0).toString() );
						
						vp.cargarVentas(nroVentaModificar);  // ACÁ SE ABRE LA VENTANA PARA QUE MODIFIQUE
						
						consulta.iniciar();
						rs = consulta.cargarVentaDetalles(nroVentaModificar);
						try {
							while(rs.next()){
								
								int codproducto = rs.getInt("codproducto");
								float cantVendida = rs.getFloat("cantidad"); // NRO VENDIDO EN UNIDADES CAJAS LITROS ETC
								String uMedidaUsada = rs.getString("uMedidaUsada");
								float cantTotalVendidaUnidades = 0;
								
								consultas consulta2 = new consultas();
								consulta2.iniciar();
								ResultSet rs2 = consulta2.buscarProductoID(codproducto);
								try {
									rs2.next();
									
									String unimedida = rs2.getString("unimedida");
									String promo1 = rs2.getString("promo1");
									float cantp1 = rs2.getFloat("cantp1");
									float prep1 = rs2.getFloat("prep1");
									String promo2 = rs2.getString("promo2");
									float cantp2 = rs2.getFloat("cantp2");
									float prep2 = rs2.getFloat("prep2");
									
									if( uMedidaUsada.equals(unimedida) ){
										cantTotalVendidaUnidades = cantVendida;
										
									} else if( uMedidaUsada.equals(promo1) ){
										cantTotalVendidaUnidades = cantVendida * cantp1;
										
									} else if ( uMedidaUsada.equals(promo2)){
										cantTotalVendidaUnidades = cantVendida * cantp2;
										
									}
									
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, "Error al buscar producto para reingresar stock: " + e2);
								}finally {
									try {
										if (rs2 != null)
											rs2.close();
										if (consulta2 != null)
											consulta2.reset();
						            } catch (Exception ex) {
						            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
						            }
								}
																
								consulta.reIngresarStock(cantTotalVendidaUnidades, codproducto);

								consulta.eliminarVentaDetalle(nroVentaModificar);
							}
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Error: al reingresar Stock " + e);
						}
						
						consulta.eliminarVenta(nroVentaModificar);
						
						consulta.resetearCeroVentaDetalle(nroVentaModificar);
						
						//actionPerformedBtnVerVentas(null);
						//actionPerformedBtnVerVentas(null);
						//JOptionPane.showMessageDialog(null, "Venta Eliminada");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Error al eliminar venta " + e2);
					}
				}
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Seleccione una venta");
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
	
	protected void mouseClickedMnNewMenu_2(MouseEvent e) {

		String[] options = {"Eliminar", "Cancelar"};
		int seleccion = JOptionPane.showOptionDialog(null, "¿Seguro de eliminar la Venta?\nEsta opción no se puede deshacer", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,  options, options[0]);
		
		if(seleccion == 0){
			try {
				int nroVentaEliminar = Integer.parseInt( tbVentas.getValueAt(tbVentas.getSelectedRow(), 0).toString() );
				consulta.iniciar();
				rs = consulta.cargarVentaDetalles(nroVentaEliminar);
				
				while(rs.next()){
					
					int codproducto = rs.getInt("codproducto");
					float cantVendida = rs.getFloat("cantidad"); // NRO VENDIDO EN UNIDADES CAJAS LITROS ETC
					String uMedidaUsada = rs.getString("uMedidaUsada");
					float cantTotalVendidaUnidades = 0;
					
					consultas consulta2 = new consultas();
					consulta2.iniciar();
					ResultSet rs2 = consulta2.buscarProductoID(codproducto);
					try {
						rs2.next();
						
						String unimedida = rs2.getString("unimedida");
						String promo1 = rs2.getString("promo1");
						float cantp1 = rs2.getFloat("cantp1");
						float prep1 = rs2.getFloat("prep1");
						String promo2 = rs2.getString("promo2");
						float cantp2 = rs2.getFloat("cantp2");
						float prep2 = rs2.getFloat("prep2");
						
						if( uMedidaUsada.equals(unimedida) ){
							cantTotalVendidaUnidades = cantVendida;
							
						} else if( uMedidaUsada.equals(promo1) ){
							cantTotalVendidaUnidades = cantVendida * cantp1;
							
						} else if ( uMedidaUsada.equals(promo2)){
							cantTotalVendidaUnidades = cantVendida * cantp2;
							
						}
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Error al buscar producto para reingresar stock: " + e2);
					}finally {
						try {
							if (rs2 != null)
								rs2.close();
							if (consulta2 != null)
								consulta2.reset();
			            } catch (Exception ex) {
			            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
			            }
					}
					
					consulta.reIngresarStock(cantTotalVendidaUnidades, codproducto);
					//consulta.eliminarVentaDetalle(nroVentaEliminar);
					//consulta.modificarVenta3(nroVentaEliminar); // CAMBIAR ESTADO

					consulta.eliminarVenta(nroVentaEliminar);
					consulta.resetearCeroVentaDetalle(nroVentaEliminar);
				}
				
				JOptionPane.showMessageDialog(null, "Venta Eliminada Correctamente");
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Error al eliminar ventaaa " + e2);
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
			
			actionPerformedBtnVerVentas(null);
			
		}
	}
	
	protected void actionPerformedBtnVerVentas(ActionEvent arg0) {
		try {
			for (int i = 0; i < tbVentas.getRowCount(); i++) {
				dtm.removeRow(i);
				i -= 1;
			}			
			dtm.setColumnIdentifiers(new Object[]{"NRO", "CLIENTE", "VENDEDOR", "NOTA", "FECHA", "DESCUENTO", "SALDO", "TOTAL"});
			tbVentas.setModel(dtm);
			
			int idusuario = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getIdusuario();
						
			int añoi = dchDesde.getCalendar().get(Calendar.YEAR);
			int mesi = dchDesde.getCalendar().get(Calendar.MARCH) + 1;
			int diai = dchDesde.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaInicial = añoi + "-" + mesi + "-" + diai + " " + "00:00:00";

			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = (Date) formatter.parse(fechaInicial);
			Object fechai = new java.sql.Timestamp(date.getTime());
			
			int añof = dchHasta.getCalendar().get(Calendar.YEAR);
			int mesf = dchHasta.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = dchHasta.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaFinal = añof + "-" + mesf + "-" + diaf + " " + "23:59:59";

			DateFormat formatter2;
			formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date2 = (Date) formatter.parse(fechaFinal);
			Object fechaf = new java.sql.Timestamp(date2.getTime());
		
			try {
				consulta.iniciar();
				if(cbUsuarios.getSelectedIndex() == 0)
					rs = consulta.cargarVentasUsuarioTodos(fechai, fechaf);
				else
					rs = consulta.cargarVentasUsuario(idusuario, fechai, fechaf);
				
				while(rs.next()){
					dtm.addRow(new Object[]{
							rs.getInt("codventa"), 
							rs.getString("ncliente"), 
							rs.getString("nusuario"), 
							rs.getString("nota"), 
							rs.getString("fecha"), 
							rs.getFloat("descuento"), 
							rs.getFloat("saldo"), 
							rs.getFloat("totventa")});	
				}
				
				this.tbVentas.setDefaultRenderer(Object.class, new PintarTablaVentasBuscar());

			} catch (Exception e) {
				// TODO: handle exception
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
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar ventas: " + e.getMessage());
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
		
		// CALCULAR TOTALES
		
		double sumTotal = 0;
		double sumDescuentos = 0;
		
		for (int i = 0; i < tbVentas.getRowCount(); i++) {
			sumTotal = sumTotal + Double.parseDouble(tbVentas.getValueAt(i, 7).toString());
			sumDescuentos = sumDescuentos + Double.parseDouble(tbVentas.getValueAt(i, 5).toString());
		}
		sumTotal = redondearDecimales(sumTotal, 2);
		sumDescuentos = redondearDecimales(sumDescuentos, 2);
		
		lblTotVentas.setText(""+sumTotal);
		lblTotDescuentos.setText(""+sumDescuentos);
		//ajustarAnchoColumnas();
	}
	
	public double redondearDecimales(double valorInicial, int numeroDecimales) {
		double parteEntera, resultado;
		resultado = valorInicial;
		parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
		resultado = Math.round(resultado);
		resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
		return resultado;
	}
	
	protected void actionPerformedBtnGenerarReporte(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			String usu = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getUsuario();
			String totalVenta = lblTotVentas.getText();
			int metpago = -1;
			
			int añoi = dchDesde.getCalendar().get(Calendar.YEAR);
			int mesi = dchDesde.getCalendar().get(Calendar.MARCH) + 1;
			int diai = dchDesde.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = dchHasta.getCalendar().get(Calendar.YEAR);
			int mesf = dchHasta.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = dchHasta.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = añof + "-" + mesf + "-" + diaf + " 23:59:59";

			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = (Date) formatter.parse(fechai);
			java.sql.Timestamp timeStampDateI = new Timestamp(date.getTime());
			DateFormat formatter2;
			formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date2 = (Date) formatter2.parse(fechaf);
			java.sql.Timestamp timeStampDateF = new Timestamp(date2.getTime());

			Map parameters = new HashMap();
			parameters.put("prtFechaI", timeStampDateI);
			parameters.put("prtFechaF", timeStampDateF);
			parameters.put("metpago", metpago);
			parameters.put("totalVenta", totalVenta);

			if (usu.equals("TODOS")) {
				if (metpago == - 1) {
					new AbstractJasperReports().createReport(con, "rVentasDetalladasTodos.jasper", parameters);
					AbstractJasperReports.showViewer();
				}
			} else {
				parameters.put("prmtVendedor", usu);
				if (metpago == - 1) {
					new AbstractJasperReports().createReport(con, "rVentasDetalladasVendedor.jasper", parameters);
					AbstractJasperReports.showViewer();
				} 
			}
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
		}
	}
	
	protected void mouseClickedTbVentas(MouseEvent arg0) {
		int nroVenta = -1;
		nroVenta = Integer.parseInt( tbVentas.getValueAt(tbVentas.getSelectedRow(), 0).toString());
		
		for (int i = 0; i < tbDetalleVenta.getRowCount(); i++) {
			dtmVD.removeRow(i);
			i -= 1;
		}
		
		try {
			consulta.iniciar();
			rs = consulta.cargarVentaDetallesProducto(nroVenta);
			while (rs.next()){
				dtmVD.addRow(new Object[]{rs.getFloat("cantidad"), rs.getString("producto")+" "+rs.getString("detalles")+" "+rs.getString("marca")+" "+ rs.getString("color") + " (" + rs.getString("uMedidaUsada")+")", rs.getFloat("preVeSDInd"), rs.getFloat("descTotal"), rs.getFloat("subTotal")});
			}
			consulta.reset();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar compra detalle " + e);
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
	protected void mouseClickedMnactualizarNotaDe(MouseEvent arg0) {
		try {
			String nuevaNota = JOptionPane.showInputDialog(null, "Deje la nota que desee", "Modificación de nota de venta", JOptionPane.INFORMATION_MESSAGE);

			if(nuevaNota != null){
				int nroVenta = Integer.parseInt( tbVentas.getValueAt(tbVentas.getSelectedRow(), 0).toString() );
				consulta.iniciar();
				consulta.modificarInformacion(nuevaNota, nroVenta);
				recargar();
			}
			else{
				
			}			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al actualizar nota: " + e);
		}
	}
	
	protected void mouseClickedMnimprimirCopiaDe(MouseEvent e) {
		try {
			int nroVenta = Integer.parseInt( tbVentas.getValueAt(tbVentas.getSelectedRow(), 0).toString() );			
			
			try {
				Map<String, Object> parameters = new HashMap();
				parameters.put("prtNVenta", nroVenta);
				try {
					Connection con = null;
		            con = MySQLConexion.getConection();
					JasperPrint impressao = JasperFillManager.fillReport(
							getClass().getClassLoader().getResourceAsStream("rComprobante.jasper"),
							parameters, con);

					// AbstractJasperReports.showViewer();
					JasperPrintManager.printReport(impressao, true);
				} catch (JRException ex) {
					 JOptionPane.showMessageDialog(null,
					 "ERROR " + ex.getMessage());
					 System.err.println("Error iReport: " +
					 ex.getMessage());
				}
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "ERROR " + ex);
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
}
