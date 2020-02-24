package gui_ventas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.Picture;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;

public class Prueba extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton button2;
	private JPanel panel;
	private JDesktopPane desktopPane;
	private JPanel panel_1;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba frame = new Prueba();
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
	public Prueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 52, 205, 677);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(11, 5, 184, 23);
		panel.add(btnNewButton);
		
		button2 = new JButton("New button");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedButton2(arg0);
			}
		});
		button2.setBounds(10, 160, 185, 23);
		panel.add(button2);
		
		panel_1 = new JPanel();
		panel_1.setVisible(false);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(11, 27, 184, 134);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setBounds(29, 11, 155, 23);
		panel_1.add(btnNewButton_1);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(277, 84, 963, 608);
		contentPane.add(desktopPane);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnNewButton(arg0);
			}
		});
	}
	Pruebainterna pi;
	protected void actionPerformedBtnNewButton(ActionEvent arg0) {

		try{
			 pi = new Pruebainterna();		
			desktopPane.add(pi);
			pi.show();
			pi.setMaximum(true);
			
			if(panel_1.isVisible()){
				panel_1.setVisible(false);
				button2.setBounds(11, 27, 155, 23);
			}
			else{
				panel_1.setVisible(true);
				button2.setBounds(10, 160, 185, 23);
				
			}
		}catch(Exception f){
			JOptionPane.showMessageDialog(null, "dsad: " + f );
		}	
	}
	protected void actionPerformedButton2(ActionEvent arg0) {
		pi.dispose();
	}
}
