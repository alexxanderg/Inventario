package clases;

import clases.PintarTablaVentasBuscar;
import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import mysql.consultas;

public class PintarTablaVentasBuscar extends DefaultTableCellRenderer {
	
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    int nroVenta = Integer.parseInt(table.getValueAt(row, 0).toString());
    int estado = 0;
    consultas consulta = new consultas();
    ResultSet rs = null;
    try {
      consulta.iniciar();
      rs = consulta.cargarVenta(nroVenta);
      rs.next();
      estado = rs.getInt("estado");
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "No se pudo encontrar la venta " + e);
    } finally {
      try {
        if (rs != null)
          rs.close(); 
        if (consulta != null)
          consulta.reset(); 
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
      } 
    } 
    if (estado == 1) {
      setBackground(new Color(138, 230, 78));
      setForeground(Color.BLACK);
    } else if (estado == 2) {
      setBackground(new Color(236, 236, 69)); 
      setForeground(Color.BLACK);
    } else if (estado == 3) {
      setBackground(new Color(251, 105, 120)); 
      setForeground(Color.BLACK);
    } 
    
    
    
    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
  }
}