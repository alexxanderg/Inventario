package clases;

import clases.PintarTablaRealizarVenta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import mysql.consultas;

public class PintarTablaRealizarVenta extends DefaultTableCellRenderer {
	
	int columna;
	
	public PintarTablaRealizarVenta (int columna) {
		this.columna = columna;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		
		
		super.getTableCellRendererComponent(table, value, isSelected, false, row, column);
		
		   if (column == 5 ) {
		     setBackground(new Color(138, 230, 78));
		     setHorizontalAlignment(SwingConstants.CENTER);
		   }
		   
		   setBorder(null);
		   setForeground(Color.black);
		   setFont(new Font("Candara", Font.BOLD | Font.ITALIC, 18));
		   
		   if(column >=7 || column <=10)
			   setFont(new Font("Candara", Font.BOLD | Font.ITALIC, 20));
		

		   return this;
		
	}
}