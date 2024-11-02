package com.fmontiel.eventos.views.personasview;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ButtonRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (column == 4) { // Cambiamos la columna a la de "Acciones"
            JPanel panel = new JPanel();
            panel.setBackground(java.awt.Color.white); // Fondo blanco

            JButton btnEditar = new JButton("Editar");
            JButton btnEliminar = new JButton("Eliminar");

            panel.add(btnEditar);
            panel.add(btnEliminar);
            return panel;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}
