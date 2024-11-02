package com.fmontiel.eventos.views.indexview;

import com.fmontiel.eventos.interfaces.CallbackInterface;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.json.JSONObject;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton btnVer;
    private JButton btnEditar;
    private JButton btnEliminar;

    // Variables para almacenar la información del evento
    private Long currentId;
    private String nombre;
    private String descripcion;
    private String organizador;
    private String ubicacion;
    private int dia;
    private int mes;
    private int anio;

    public ButtonEditor(JTable table, CallbackInterface verCallback, CallbackInterface editarCallback,
                        CallbackInterface eliminarCallback) {
        panel = new JPanel();
        panel.setBackground(java.awt.Color.white);

        btnVer = new JButton("Ver");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        // Acción para el botón "Ver"
        btnVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verCallback != null) {
                    JSONObject jo = new JSONObject();
                    jo.put("id", currentId);
                    jo.put("nombre", nombre);
                    jo.put("descripcion", descripcion);
                    jo.put("organizador", organizador);
                    jo.put("ubicacion", ubicacion);
                    jo.put("dia", dia);
                    jo.put("mes", mes);
                    jo.put("anio", anio);
                    verCallback.call(jo);
                }
            }
        });

        // Acción para el botón "Editar"
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editarCallback != null) {
                    JSONObject jo = new JSONObject();
                    jo.put("id", currentId);
                    jo.put("nombre", nombre);
                    jo.put("descripcion", descripcion);
                    jo.put("organizador", organizador);
                    jo.put("ubicacion", ubicacion);
                    jo.put("dia", dia);
                    jo.put("mes", mes);
                    jo.put("anio", anio);
                    editarCallback.call(jo);
                }
            }
        });

        // Acción para el botón "Eliminar"
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (eliminarCallback != null) {
                    JSONObject jo = new JSONObject();
                    jo.put("id", currentId);
                    jo.put("nombre", nombre);
                    eliminarCallback.call(jo);
                }
            }
        });

        panel.add(btnVer);
        panel.add(btnEditar);
        panel.add(btnEliminar);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Obtener los valores de la fila actual
        currentId = (Long) table.getValueAt(row, 0);
        nombre = (String) table.getValueAt(row, 1);
        descripcion = (String) table.getValueAt(row, 2);
        organizador = (String) table.getValueAt(row, 3);
        ubicacion = (String) table.getValueAt(row, 4);
        dia = (int) table.getValueAt(row, 5);
        mes = (int) table.getValueAt(row, 6);
        anio = (int) table.getValueAt(row, 7);

        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null; // No es necesario devolver un valor editable aquí
    }
}
