package com.fmontiel.eventos.views.personasview;

import com.fmontiel.eventos.interfaces.CallbackInterface;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.json.JSONObject;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private Long personaId;
    private String nombre;
    private JPanel panel;

    public ButtonEditor(JTable table, CallbackInterface editCallback, CallbackInterface deleteCallback) {
        panel = new JPanel();
        panel.setBackground(java.awt.Color.white);

        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");

        btnEditar.addActionListener((ActionEvent e) -> {
            if (editCallback != null) {
                JSONObject jo = new JSONObject();
                jo.put("id", personaId);
                jo.put("nombre", nombre);
                editCallback.call(jo);
            }
        });

        btnEliminar.addActionListener((ActionEvent e) -> {
            if (deleteCallback != null) {
                JSONObject jo = new JSONObject();
                jo.put("id", personaId);
                jo.put("nombre", nombre);
                deleteCallback.call(jo);
            }
        });

        panel.add(btnEditar);
        panel.add(btnEliminar);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        personaId = Long.valueOf(String.valueOf(table.getValueAt(row, 0))); // Suponiendo que el ID (CUI) está en la columna 0
        nombre = String.valueOf(table.getValueAt(row, 1)); // Suponiendo que el nombre está en la columna 1
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}
