package com.fmontiel.eventos.views.indexview;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ButtonRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Verificamos si es la columna de acciones
        if (column == 8) { // Ajusta el índice según la posición de la columna de "Acciones"
            JPanel panel = new JPanel();
            panel.setBackground(java.awt.Color.white); // Fondo blanco

            // Crear los botones sin agregar ActionListener aquí
            JButton btnVer = new JButton("Ver");
            JButton btnEditar = new JButton("Editar");
            JButton btnEliminar = new JButton("Eliminar");

            // Añadir los botones al panel
            panel.add(btnVer);
            panel.add(btnEditar);
            panel.add(btnEliminar);

            return panel;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}
