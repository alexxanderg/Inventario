package gui_reportes;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import gui_principal.VentanaPrincipal;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;

public class Reportes2 extends JInternalFrame {
	private JComboBox cbUsuarios;
	private JComboBox cbMetodoPago;
	private JLabel label_1;
	private JDateChooser dateChooser;
	private JLabel label_2;
	private JDateChooser dateChooser_1;
	private JButton btnverventasyDetalles;
	private JButton btnverSoloVentas;
	private JLabel lblIngreseNroDe;
	private JTextField textField;
	private JButton btnBuscarVenta;
	private JLabel label_4;
	private JLabel label_5;
	private JDateChooser dateChooser_2;
	private JDateChooser dateChooser_3;
	private JButton btnVerIngresos;
	private JLabel label_7;
	private JComboBox comboBox_2;
	private JLabel label_8;
	private JDateChooser dateChooser_4;
	private JLabel label_9;
	private JDateChooser dateChooser_5;
	private JButton button_4;
	private JLabel lblVerStockDe;
	private JLabel lblCategora;
	private JComboBox comboBox_3;
	private JTextField textField_3;
	private JLabel lblMenoresA;
	private JLabel lblMayoresA;
	private JTextField textField_4;
	private JButton button_5;
	private JButton button_6;
	private JLabel label_14;
	private JComboBox comboBox_4;
	private JButton btnVerCompras;
	private JLabel label_15;
	private JLabel lblDel;
	private JDateChooser dateChooser_6;
	private JButton btnVerProductosPor;
	private JLabel label_17;
	private JDateChooser dateChooser_7;
	private JTextField textField_8;

	VentanaPrincipal vp;
	private JPanel panel;
	private JLabel lblVendedor;
	private JLabel lblMtodoDePago;
	private JPanel panel_1;
	private JLabel label_19;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblVerDetalleDe;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel label;
	private JLabel label_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reportes2 frame = new Reportes2(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Reportes2(VentanaPrincipal vp) {
		getContentPane().setBackground(Color.WHITE);
		this.vp = vp;
		
		setBounds(100, 100, 1134, 669);
		getContentPane().setLayout(null);
		
		this.textField_8 = new JTextField();
		this.textField_8.setText("REPORTES");
		this.textField_8.setRequestFocusEnabled(false);
		this.textField_8.setIgnoreRepaint(true);
		this.textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		this.textField_8.setForeground(Color.WHITE);
		this.textField_8.setFont(new Font("Tahoma", Font.BOLD, 25));
		this.textField_8.setFocusable(false);
		this.textField_8.setFocusTraversalKeysEnabled(false);
		this.textField_8.setEditable(false);
		this.textField_8.setColumns(10);
		this.textField_8.setBackground(Color.DARK_GRAY);
		this.textField_8.setBounds(0, 0, 1118, 50);
		getContentPane().add(this.textField_8);
		
		this.panel = new JPanel();
		this.panel.setBackground(new Color(60, 179, 113));
		this.panel.setBounds(0, 50, 557, 215);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);
		
		this.lblVendedor = new JLabel("Vendedor:");
		this.lblVendedor.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblVendedor.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblVendedor.setBounds(20, 48, 117, 23);
		this.panel.add(this.lblVendedor);
		
		this.cbUsuarios = new JComboBox();
		this.cbUsuarios.setBounds(197, 49, 219, 23);
		this.panel.add(this.cbUsuarios);
		this.cbUsuarios.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.cbMetodoPago = new JComboBox();
		this.cbMetodoPago.setBounds(197, 82, 219, 23);
		this.panel.add(this.cbMetodoPago);
		this.cbMetodoPago.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.lblMtodoDePago = new JLabel("M\u00E9todo de pago:");
		this.lblMtodoDePago.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblMtodoDePago.setBounds(20, 82, 157, 23);
		this.panel.add(this.lblMtodoDePago);
		
		this.label_1 = new JLabel("del:");
		this.label_1.setBounds(20, 116, 46, 23);
		this.panel.add(this.label_1);
		this.label_1.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_1.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.dateChooser = new JDateChooser();
		this.dateChooser.setBounds(74, 116, 125, 23);
		this.panel.add(this.dateChooser);
		
		this.label_2 = new JLabel("al:");
		this.label_2.setBounds(243, 116, 55, 23);
		this.panel.add(this.label_2);
		this.label_2.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_2.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.dateChooser_1 = new JDateChooser();
		this.dateChooser_1.setBounds(291, 116, 125, 23);
		this.panel.add(this.dateChooser_1);
		
		this.btnverSoloVentas = new JButton("<html>Ver solo ventas</html>");
		this.btnverSoloVentas.setBounds(20, 160, 260, 40);
		this.panel.add(this.btnverSoloVentas);
		this.btnverSoloVentas.setForeground(Color.WHITE);
		this.btnverSoloVentas.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnverSoloVentas.setBackground(new Color(30, 144, 255));
		
		this.btnverventasyDetalles = new JButton("<html>Ver ventas y detalles</html>");
		this.btnverventasyDetalles.setBounds(291, 160, 260, 40);
		this.panel.add(this.btnverventasyDetalles);
		this.btnverventasyDetalles.setForeground(Color.WHITE);
		this.btnverventasyDetalles.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnverventasyDetalles.setBackground(new Color(30, 144, 255));
		
		this.label_19 = new JLabel("VENTAS REALIZADAS");
		this.label_19.setBounds(16, 5, 535, 32);
		this.panel.add(this.label_19);
		this.label_19.setHorizontalAlignment(SwingConstants.CENTER);
		this.label_19.setFont(new Font("Candara", Font.BOLD, 23));
		
		this.panel_1 = new JPanel();
		this.panel_1.setBackground(new Color(240, 128, 128));
		this.panel_1.setBounds(561, 50, 557, 215);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(null);
		
		this.lblCategora = new JLabel("Categor\u00EDa:");
		this.lblCategora.setBounds(20, 48, 143, 31);
		this.panel_1.add(this.lblCategora);
		this.lblCategora.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.lblMenoresA = new JLabel("Menores a:");
		this.lblMenoresA.setBounds(20, 101, 143, 23);
		this.panel_1.add(this.lblMenoresA);
		this.lblMenoresA.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.lblMayoresA = new JLabel("Mayores a: ");
		this.lblMayoresA.setBounds(20, 155, 143, 23);
		this.panel_1.add(this.lblMayoresA);
		this.lblMayoresA.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.textField_4 = new JTextField();
		this.textField_4.setBounds(170, 160, 145, 23);
		this.panel_1.add(this.textField_4);
		this.textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
		this.textField_4.setFont(new Font("Arial", Font.PLAIN, 16));
		this.textField_4.setColumns(10);
		this.textField_4.setBackground(SystemColor.controlHighlight);
		
		this.textField_3 = new JTextField();
		this.textField_3.setBounds(170, 99, 145, 23);
		this.panel_1.add(this.textField_3);
		this.textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		this.textField_3.setFont(new Font("Arial", Font.PLAIN, 16));
		this.textField_3.setColumns(10);
		this.textField_3.setBackground(SystemColor.controlHighlight);
		
		this.button_6 = new JButton("Crear");
		this.button_6.setBounds(325, 98, 161, 31);
		this.panel_1.add(this.button_6);
		this.button_6.setForeground(Color.WHITE);
		this.button_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.button_6.setBackground(new Color(30, 144, 255));
		
		this.button_5 = new JButton("Crear");
		this.button_5.setBounds(325, 160, 161, 31);
		this.panel_1.add(this.button_5);
		this.button_5.setForeground(Color.WHITE);
		this.button_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.button_5.setBackground(new Color(30, 144, 255));
		
		this.comboBox_3 = new JComboBox();
		this.comboBox_3.setFont(new Font("Arial", Font.PLAIN, 16));
		this.comboBox_3.setBounds(170, 52, 316, 23);
		this.panel_1.add(this.comboBox_3);
		
		this.lblVerStockDe = new JLabel("VER STOCK DE PRODUCTOS");
		this.lblVerStockDe.setBounds(10, 5, 537, 32);
		this.panel_1.add(this.lblVerStockDe);
		this.lblVerStockDe.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblVerStockDe.setFont(new Font("Candara", Font.BOLD, 23));
		
		this.panel_2 = new JPanel();
		this.panel_2.setBackground(new Color(102, 205, 170));
		this.panel_2.setBounds(0, 265, 557, 85);
		getContentPane().add(this.panel_2);
		this.panel_2.setLayout(null);
		
		this.lblIngreseNroDe = new JLabel("Ingrese Nro de Venta:");
		this.lblIngreseNroDe.setBounds(20, 50, 191, 23);
		this.panel_2.add(this.lblIngreseNroDe);
		this.lblIngreseNroDe.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.textField = new JTextField();
		this.textField.setBounds(213, 48, 103, 23);
		this.panel_2.add(this.textField);
		this.textField.setHorizontalAlignment(SwingConstants.RIGHT);
		this.textField.setFont(new Font("Arial", Font.PLAIN, 16));
		this.textField.setColumns(10);
		this.textField.setBackground(SystemColor.controlHighlight);
		
		this.btnBuscarVenta = new JButton("Buscar venta");
		this.btnBuscarVenta.setBounds(326, 44, 222, 30);
		this.panel_2.add(this.btnBuscarVenta);
		this.btnBuscarVenta.setForeground(Color.WHITE);
		this.btnBuscarVenta.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnBuscarVenta.setBackground(new Color(30, 144, 255));
		
		this.lblVerDetalleDe = new JLabel("VER  DETALLE DE VENTA ESPEC\u00CDFICA");
		this.lblVerDetalleDe.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblVerDetalleDe.setFont(new Font("Candara", Font.BOLD, 23));
		this.lblVerDetalleDe.setBounds(12, 11, 535, 32);
		this.panel_2.add(this.lblVerDetalleDe);
		
		this.panel_3 = new JPanel();
		this.panel_3.setBackground(new Color(119, 136, 153));
		this.panel_3.setBounds(561, 265, 568, 141);
		getContentPane().add(this.panel_3);
		this.panel_3.setLayout(null);
		
		this.label_4 = new JLabel("INGRESO DE PRODUCTOS");
		this.label_4.setBounds(12, 11, 548, 32);
		this.panel_3.add(this.label_4);
		this.label_4.setHorizontalAlignment(SwingConstants.CENTER);
		this.label_4.setFont(new Font("Candara", Font.BOLD, 23));
		
		this.dateChooser_2 = new JDateChooser();
		this.dateChooser_2.setBounds(83, 54, 141, 23);
		this.panel_3.add(this.dateChooser_2);
		
		this.dateChooser_3 = new JDateChooser();
		this.dateChooser_3.setBounds(347, 54, 141, 23);
		this.panel_3.add(this.dateChooser_3);
		
		this.btnVerIngresos = new JButton("Ver ingresos");
		this.btnVerIngresos.setBounds(171, 92, 234, 40);
		this.panel_3.add(this.btnVerIngresos);
		this.btnVerIngresos.setForeground(Color.WHITE);
		this.btnVerIngresos.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerIngresos.setBackground(new Color(30, 144, 255));
		
		this.label = new JLabel("del:");
		this.label.setHorizontalAlignment(SwingConstants.LEFT);
		this.label.setFont(new Font("Candara", Font.BOLD, 20));
		this.label.setBounds(22, 54, 55, 23);
		this.panel_3.add(this.label);
		
		this.label_3 = new JLabel("al:");
		this.label_3.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_3.setFont(new Font("Candara", Font.BOLD, 20));
		this.label_3.setBounds(300, 54, 55, 23);
		this.panel_3.add(this.label_3);
		
		this.panel_4 = new JPanel();
		this.panel_4.setBackground(new Color(100, 149, 237));
		this.panel_4.setLayout(null);
		this.panel_4.setBounds(0, 349, 557, 194);
		getContentPane().add(this.panel_4);
		
		this.label_7 = new JLabel("RANKING DE PRODUCTOS");
		this.label_7.setBounds(10, 11, 537, 38);
		this.panel_4.add(this.label_7);
		this.label_7.setHorizontalAlignment(SwingConstants.CENTER);
		this.label_7.setFont(new Font("Candara", Font.BOLD, 23));
		
		this.comboBox_2 = new JComboBox();
		this.comboBox_2.setFont(new Font("Candara", Font.BOLD, 20));
		this.comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"M\u00E1s vendidos", "Menos vendidos"}));
		this.comboBox_2.setBounds(162, 54, 231, 33);
		this.panel_4.add(this.comboBox_2);
		this.comboBox_2.setToolTipText("");
		
		this.label_8 = new JLabel("del:");
		this.label_8.setBounds(20, 98, 55, 23);
		this.panel_4.add(this.label_8);
		this.label_8.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_8.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.dateChooser_4 = new JDateChooser();
		this.dateChooser_4.setBounds(74, 98, 125, 23);
		this.panel_4.add(this.dateChooser_4);
		
		this.button_4 = new JButton("Ver ranking");
		this.button_4.setBounds(162, 141, 231, 40);
		this.panel_4.add(this.button_4);
		this.button_4.setForeground(Color.WHITE);
		this.button_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.button_4.setBackground(new Color(30, 144, 255));
		
		this.label_9 = new JLabel("al:");
		this.label_9.setBounds(243, 98, 55, 23);
		this.panel_4.add(this.label_9);
		this.label_9.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_9.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.dateChooser_5 = new JDateChooser();
		this.dateChooser_5.setBounds(292, 98, 125, 23);
		this.panel_4.add(this.dateChooser_5);
		
		this.label_5 = new JLabel("del:");
		this.label_5.setBounds(393, 448, 55, 38);
		getContentPane().add(this.label_5);
		this.label_5.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_5.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.panel_5 = new JPanel();
		this.panel_5.setBackground(new Color(173, 216, 230));
		this.panel_5.setLayout(null);
		this.panel_5.setBounds(0, 543, 557, 96);
		getContentPane().add(this.panel_5);
		
		this.label_14 = new JLabel("COMPRAS DE CLIENTE");
		this.label_14.setBounds(12, 11, 537, 32);
		this.panel_5.add(this.label_14);
		this.label_14.setHorizontalAlignment(SwingConstants.CENTER);
		this.label_14.setFont(new Font("Candara", Font.BOLD, 23));
		
		this.comboBox_4 = new JComboBox();
		this.comboBox_4.setFont(new Font("Arial", Font.PLAIN, 16));
		this.comboBox_4.setBounds(20, 51, 296, 23);
		this.panel_5.add(this.comboBox_4);
		
		this.btnVerCompras = new JButton("Ver compras");
		this.btnVerCompras.setBounds(326, 44, 165, 31);
		this.panel_5.add(this.btnVerCompras);
		this.btnVerCompras.setForeground(Color.WHITE);
		this.btnVerCompras.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerCompras.setBackground(new Color(30, 144, 255));
		
		this.panel_6 = new JPanel();
		this.panel_6.setBackground(new Color(95, 158, 160));
		this.panel_6.setLayout(null);
		this.panel_6.setBounds(561, 406, 557, 233);
		getContentPane().add(this.panel_6);
		
		this.label_15 = new JLabel("PRODUCTOS POR VENCER");
		this.label_15.setBounds(10, 30, 537, 32);
		this.panel_6.add(this.label_15);
		this.label_15.setHorizontalAlignment(SwingConstants.CENTER);
		this.label_15.setFont(new Font("Candara", Font.BOLD, 23));
		
		this.lblDel = new JLabel("desde:");
		this.lblDel.setBounds(33, 81, 109, 23);
		this.panel_6.add(this.lblDel);
		this.lblDel.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDel.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.btnVerProductosPor = new JButton("Ver productos");
		this.btnVerProductosPor.setBounds(174, 129, 215, 38);
		this.panel_6.add(this.btnVerProductosPor);
		this.btnVerProductosPor.setForeground(Color.WHITE);
		this.btnVerProductosPor.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerProductosPor.setBackground(new Color(30, 144, 255));
		
		this.dateChooser_6 = new JDateChooser();
		this.dateChooser_6.setBounds(101, 81, 141, 23);
		this.panel_6.add(this.dateChooser_6);
		
		this.label_17 = new JLabel("hasta:");
		this.label_17.setBounds(281, 81, 109, 23);
		this.panel_6.add(this.label_17);
		this.label_17.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_17.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.dateChooser_7 = new JDateChooser();
		this.dateChooser_7.setBounds(351, 81, 141, 23);
		this.panel_6.add(this.dateChooser_7);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO

	}
}
