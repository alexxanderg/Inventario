package clases;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorearFilas extends DefaultTableCellRenderer{

	String cod;
	
	public ColorearFilas(String cod) {
		this.cod=cod;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col){
		super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
		
		if(table.getValueAt(row,0).toString().equals(cod)){
			setBackground(Color.red);			
		}
		
		
		return this;
	}
}
