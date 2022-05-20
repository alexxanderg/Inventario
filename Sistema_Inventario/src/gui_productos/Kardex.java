package gui_productos;

import com.mxrck.autocompleter.TextAutoCompleter;
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

public class Kardex extends JInternalFrame {
	private JMenuBar menuBar;
	private JTextField txtCodigo;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	private JTable tbProductos;
	JTable tb;
	ResultSet rs;
	consultas consulta = new consultas();
	ModificarProducto mp = null;
	DefaultTableModel dtm = new DefaultTableModel();
	consultas model = new consultas();
	String usuario;
	public VentanaPrincipal vp;
	private JTextField txtCodigo2;
	private JMenu mncargarltimoRegistro;
	private JMenu mnfusionar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kardex frame = new Kardex(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Kardex(VentanaPrincipal vp) {
		this.vp = vp;
		this.usuario = vp.lblUsuario.getText();
		getContentPane().setBackground(Color.WHITE);
		setTitle("ALMACÉN");
		setBounds(100, 100, 1134, 679);
		getContentPane().setLayout(null);

		

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		this.scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 224, 1083, 385);
		getContentPane().add(this.scrollPane);

		this.tbProductos = new JTable();
		this.tbProductos.setAutoCreateRowSorter(true);
		this.tbProductos.setSelectionMode(0);
		this.tbProductos.setFont(new Font("Arial", 2, 14));
		this.tbProductos.setBackground(Color.WHITE);
		this.tbProductos.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		this.scrollPane.setViewportView(this.tbProductos);

		

		this.tbProductos.getTableHeader().setReorderingAllowed(false);

		this.menuBar = new JMenuBar();
		this.menuBar.setCursor(Cursor.getPredefinedCursor(12));
		this.menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(this.menuBar);

		JMenu mnaadirStock = new JMenu("|Guardar conteo actual| ");
		mnaadirStock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Kardex.this.mouseClickedMnaadirStock(e);
			}
		});
		mnaadirStock.setForeground(new Color(50, 205, 50));
		mnaadirStock.setFont(new Font("Tahoma", 1, 20));
		mnaadirStock.setBackground(SystemColor.menu);
		this.menuBar.add(mnaadirStock);

		this.mncargarltimoRegistro = new JMenu("|Cargar último conteo| ");
		this.mncargarltimoRegistro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Kardex.this.mouseClickedMncargarltimoRegistro(arg0);
			}
		});
		this.mncargarltimoRegistro.setForeground(new Color(255, 215, 0));
		this.mncargarltimoRegistro.setFont(new Font("Tahoma", 1, 20));
		this.mncargarltimoRegistro.setBackground(SystemColor.menu);
		this.menuBar.add(this.mncargarltimoRegistro);

		this.mnfusionar = new JMenu("|FUSIONAR| ");
		this.mnfusionar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Kardex.this.mouseClickedMnfusionar(arg0);
			}
		});
		this.mnfusionar.setForeground(new Color(30, 144, 255));
		this.mnfusionar.setFont(new Font("Tahoma", 1, 20));
		this.mnfusionar.setBackground(SystemColor.menu);
		this.menuBar.add(this.mnfusionar);

		((BasicInternalFrameUI) getUI()).setNorthPane(null);
		cargar();
		cargarBuscador();
		cargarKardexCero();
	}

	public void cargar() {
		this.tb = this.tbProductos;
		this.tb.setRowHeight(40);
		this.tb.setModel(this.dtm);

		cargarTabla("todos");
	}

	public void cargarTabla(String prod) {
	
		ajustarAnchoColumnas();
	}

	public void cargarBuscador() {
		
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

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = this.tbProductos.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(1));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(47));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(15));

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

	protected void keyTypedTxtCodigo(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == '\n')
			if (this.txtCodigo.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Escriba el producto que desee buscar");
			} else {
				String producto = this.txtCodigo.getText();
				this.txtCodigo.setText("");
				try {
					int idProdBuscar = Integer
							.parseInt(producto.substring(producto.indexOf("(") + 1, producto.indexOf(")")));

					DefaultTableModel tm = (DefaultTableModel) this.tbProductos.getModel();

					for (int j = 0; j < this.tbProductos.getRowCount(); j++) {
						int idProdFila = Integer.parseInt(String.valueOf(tm.getValueAt(j, 0)));

						if (idProdBuscar == idProdFila) {
							this.tbProductos.setRowSelectionInterval(j, j);

							for (int i = 0; i < this.tbProductos.getColumnCount(); i++) {
								if (this.tbProductos.getColumnName(i).equals("CONTEO")) {
									
									
									double cantidad = Double.parseDouble( JOptionPane.showInputDialog(null, this.tbProductos.getValueAt(j, 1),  "Introduzca el stock real..."));
									this.tbProductos.setValueAt(cantidad, j, i);
									
//									if ((this.tbProductos.getValueAt(j, i) == null)	|| (this.tbProductos.getValueAt(j, i).toString().equals("0"))) {
//										this.tbProductos.setValueAt(Integer.valueOf(1), j, i);
//										JOptionPane.showMessageDialog(null, producto + "\n\nConteo = 1");
//									} else {
//										int conteoold = Integer.parseInt(this.tbProductos.getValueAt(j, i).toString());
//										this.tbProductos.setValueAt(Integer.valueOf(conteoold + 1), j, i);
//										JOptionPane.showMessageDialog(null,
//												producto + "\n\nConteo = " + (conteoold + 1));
//									}
								}
							}
						}
					}
				} catch (Exception e2) {
					try {
						this.model.iniciar();
						this.rs = this.model.buscarProductoBarras(producto);
						this.rs.next();
						int idProdBuscar = this.rs.getInt("codproducto");
						DefaultTableModel tm = (DefaultTableModel) this.tbProductos.getModel();

						for (int j = 0; j < this.tbProductos.getRowCount(); j++) {
							int idProdFila = Integer.parseInt(String.valueOf(tm.getValueAt(j, 0)));

							if (idProdBuscar == idProdFila) {
								this.tbProductos.setRowSelectionInterval(j, j);
								for (int i = 0; i < this.tbProductos.getColumnCount(); i++) {
									if (this.tbProductos.getColumnName(i).equals("CONTEO")) {
										
										double cantidad = Double.parseDouble( JOptionPane.showInputDialog(null, this.tbProductos.getValueAt(j, 1),  "Introduzca el stock real..."));
										this.tbProductos.setValueAt(cantidad, j, i);
										
//										if ((this.tbProductos.getValueAt(j, i) == null)
//												|| (this.tbProductos.getValueAt(j, i).toString().equals("0"))) {
//											this.tbProductos.setValueAt(Integer.valueOf(1), j, i);
//											JOptionPane.showMessageDialog(null, producto + "\n\nConteo = 1");
//										} else {
//											int conteoold = Integer
//													.parseInt(this.tbProductos.getValueAt(j, i).toString());
//											this.tbProductos.setValueAt(Integer.valueOf(conteoold + 1), j, i);
//											JOptionPane.showMessageDialog(null,
//													producto + "\n\nConteo = " + (conteoold + 1));
//										}
									}
								}
							}
						}

					} catch (Exception localException1) {
						try {
							if (this.rs != null)
								this.rs.close();
							if (this.model != null)
								this.model.reset();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
						}
					} finally {
						try {
							if (this.rs != null)
								this.rs.close();
							if (this.model != null)
								this.model.reset();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
						}
					}
				}
			}
	}

	protected void keyReleasedTxtCodigo2(KeyEvent arg0) {
	}

	private void limpiarTabla() {
		for (int i = 0; i < this.tbProductos.getRowCount(); i++) {
			this.dtm.removeRow(i);
			i--;
		}
	}

	protected void mouseClickedMnaadirStock(MouseEvent arg0) {

		JOptionPane.showMessageDialog(null, "Guardando, espere un momento...");
		
		String nota = "";

		Date date = new Date();
		Object date2 = new Timestamp(date.getTime());

		this.model.iniciar();
		this.model.registrarKardex(date2, nota);

		this.rs = this.model.ObtenerUltimoNroKardex();
		int idkardex = 0;
		try {
			this.rs.next();
			idkardex = this.rs.getInt("idkardex");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR al obtener ultimo kardex: " + ex);
			try {
				if (this.rs != null)
					this.rs.close();
				if (this.model != null)
					this.model.reset();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
			}
		} finally {
			try {
				if (this.rs != null)
					this.rs.close();
				if (this.model != null)
					this.model.reset();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
			}
		}
		DefaultTableModel tm = (DefaultTableModel) this.tbProductos.getModel();
		try {
			int cantProductos = this.tbProductos.getRowCount();
			for (int i = 0; i < cantProductos; i++) {
				String idProducto = String.valueOf(tm.getValueAt(i, 0));
				for (int j = 0; j < this.tbProductos.getColumnCount(); j++) {
					if (this.tbProductos.getColumnName(j).equals("CONTEO")) {
						float conteo = Float.parseFloat(String.valueOf(tm.getValueAt(i, j)));

						this.model.iniciar();
						try {
							this.model.registrarDetallesKardex(idkardex, idProducto, conteo);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "error al guardar detalles de kardes " + e);
							try {
								if (this.rs != null)
									this.rs.close();
								if (this.model != null)
									this.model.reset();
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
							}
						} finally {
							try {
								if (this.rs != null)
									this.rs.close();
								if (this.model != null)
									this.model.reset();
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
							}
						}
					}
				}
			}

			dispose();
			JOptionPane.showMessageDialog(null, "CONTEO SALVADO CORRECTAMENTE");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage());
		}
	}

	public double redondearDecimales(double valorInicial, int numeroDecimales) {
		double resultado = valorInicial;
		double parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10.0D, numeroDecimales);
		resultado = Math.round(resultado);
		resultado = resultado / Math.pow(10.0D, numeroDecimales) + parteEntera;
		return resultado;
	}

	protected void mouseClickedMncargarltimoRegistro(MouseEvent arg0) {
		int opc = JOptionPane.showConfirmDialog(null,
				"Con esta opción, cargará su último conteo guardado, para que pueda revisarlo o continuar modificandolo.\n¿Continuar?",
				"Verificación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (opc == 0) {


			JOptionPane.showMessageDialog(null, "Cargando, espere un momento...");
			
			consulta.iniciar();
			try {
				rs = consulta.ObtenerUltimoNroKardex();
				int ultnrokardex = 0;

				try {
					rs.next();
					ultnrokardex = rs.getInt("idkardex");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "error al cargar ult nro de kardex " + e);
				}

				rs = consulta.cargarUltimoKardexYDetalle(ultnrokardex);
				int idprod = 0;
				String conteo = "";
				int cantProductos = tbProductos.getRowCount();
				while (rs.next()) {
					idprod = rs.getInt("codproducto");
					conteo = rs.getString("registros");

					for (int j = 0; j < cantProductos; j++) {
						if (Integer.parseInt(tbProductos.getValueAt(j, 0).toString()) == idprod) {

							for (int i = 0; i < tbProductos.getColumnCount(); i++)
								if (tbProductos.getColumnName(i).equals("CONTEO"))
									tbProductos.setValueAt(conteo, j, i);

						}
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al recorrer productos de kardex " + e);
			}
			JOptionPane.showMessageDialog(null, "Cargado correctamente");
		} else {

		}
	}

	protected void mouseClickedMnfusionar(MouseEvent arg0) {
		int opc = JOptionPane.showConfirmDialog(null,
				"ALERTA: Con esta opción, el conteo real realizado a cada producto, sobreescribirá el stock que indica el sistema.\nNo hay manera de deshacer esta operación\n\n¿Continuar?",
				"Verificación", 0, 3);

		if (opc == 0) {

			JOptionPane.showMessageDialog(null, "Fusionando, espere un momento...");
			
			int cantProductos = this.tbProductos.getRowCount();
			for (int j = 0; j < cantProductos; j++) {
				float conteo = 0.0F;
				int codproducto = 0;
				for (int i = 0; i < this.tbProductos.getColumnCount(); i++) {
					if (this.tbProductos.getColumnName(i).equals("CONTEO")) {
						conteo = Float.parseFloat(this.tbProductos.getValueAt(j, i).toString());
						codproducto = Integer.parseInt(this.tbProductos.getValueAt(j, 0).toString());

						this.model.iniciar();
						try {
							this.model.FusionarConteoKardex(codproducto, conteo);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error al fusionar " + e);
							try {
								if (this.rs != null)
									this.rs.close();
								if (this.model != null)
									this.model.reset();
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
							}
						} finally {
							try {
								if (this.rs != null)
									this.rs.close();
								if (this.model != null)
									this.model.reset();
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
							}
						}

						dispose();
					}
				}
			}
			JOptionPane.showMessageDialog(null, "Operación exitosa.");
		}
	}
}