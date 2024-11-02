/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.fmontiel.eventos.views;

import com.fmontiel.eventos.views.indexview.ButtonRenderer;
import com.fmontiel.eventos.views.indexview.Formulario;
import com.fmontiel.eventos.models.EventoModel;
import com.fmontiel.eventos.services.EventosService;
import com.fmontiel.eventos.views.indexview.ButtonEditor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

/**
 *
 * @author PC
 */
public class IndexView extends javax.swing.JFrame {

    
    ArrayList<EventoModel> listaEventos;
    EventosService eventosService;

    public IndexView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();

        eventosService = new EventosService();

        try {
            actualizarListaEventos();
            rellenarTabla();
        } catch (IOException ex) {
            Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarListaEventos() throws IOException {
        listaEventos = (ArrayList<EventoModel>) eventosService.getAllEventos();
    }

    public void rellenarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Nombre", "Descripción", "Organizador", "Ubicación", "Día", "Mes", "Año", "Acciones" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8; // Solo la columna de "Acciones" es editable
            }
        };

        modelo.setRowCount(0);

        for (EventoModel evento : listaEventos) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(evento.getFechaEvento());

            Object[] fila = {
                    evento.getId(),
                    evento.getNombre(),
                    evento.getDescripcion(),
                    evento.getOrganizador(),
                    evento.getUbicacion(),
                    cal.get(Calendar.DAY_OF_MONTH),
                    cal.get(Calendar.MONTH) + 1, // Calendar.MONTH es 0-indexed
                    cal.get(Calendar.YEAR),
                    null // Espacio para los botones de acciones (Ver / Editar / Eliminar)
            };
            modelo.addRow(fila);
        }

        jTable1.setModel(modelo);
        jTable1.setRowHeight(40);
        jTable1.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
        jTable1.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(
                jTable1,
                (JSONObject jo) -> {
                    PersonasView indexView = new PersonasView(
                            jo.getLong("id")
                    );
                    indexView.setVisible(true);
                    indexView.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    this.dispose();
                },
                (JSONObject jo) -> {
                    // Acción para "Editar" evento
                    Formulario f = new Formulario(
                            Formulario.Modos.EDITAR,
                            jo.getLong("id"),
                            jo.getString("nombre"),
                            jo.getString("descripcion"),
                            jo.getString("organizador"),
                            jo.getString("ubicacion"),
                            jo.getInt("dia"),
                            jo.getInt("mes"),
                            jo.getInt("anio"),
                            (JSONObject jo1) -> {
                                try {
                                    this.actualizarListaEventos();
                                    this.rellenarTabla();
                                } catch (IOException ex) {
                                    Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    f.setLocationRelativeTo(null);
                    f.setVisible(true);
                },
                (JSONObject jo) -> {
                    try {
                        if (eventosService.deleteEvento(jo.getLong("id"))) {
                            actualizarListaEventos();
                            rellenarTabla();
                            JOptionPane.showMessageDialog(
                                    this,
                                    "Evento eliminado: " + jo.getString("nombre"),
                                    "Éxito",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "Error al eliminar el evento: " + jo.getString("nombre"),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }));
    }

    private void agregarEvento(java.awt.event.ActionEvent evt) {
        Formulario f = new Formulario(
                Formulario.Modos.AGREGAR,
                null,
                "", // nombre del evento
                "", // descripción
                "", // organizador
                "", // ubicación
                1, // día
                1, // mes
                2024, // año
                (JSONObject jo1) -> {
                    try {
                        this.actualizarListaEventos();
                        this.rellenarTabla();
                    } catch (IOException ex) {
                        Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        textFieldFiltroConcidencias = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Eventos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CUI", "Nombres", "Apellidos", "Acciones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        textFieldFiltroConcidencias.setToolTipText("");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarEvento(evt);
            }
        });

        jLabel4.setText("Filtrar por nombre u otro");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldFiltroConcidencias)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 761, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(textFieldFiltroConcidencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*private void agregarAlumno(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_agregarAlumno
        Formulario f = new Formulario(
                Formulario.Modos.AGREGAR,
                null,
                "",
                "",
                (JSONObject jo1) -> {
                    this.actualizarListaAlumnos();
                    try {
                        this.rellenarTabla();
                    } catch (SQLException ex) {
                        Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true); // TODO add your handling code here:
    }// GEN-LAST:event_agregarAlumno

    private void openGradosView(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_openGradosView
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MateriasView gv = new MateriasView();
                gv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gv.setLocationRelativeTo(null);
                // gv.setExtendedState(JFrame.MAXIMIZED_BOTH);
                gv.setVisible(true);

                dispose();
            }
        });
    }// GEN-LAST:event_openGradosView

    private void goToGradosView(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_goToGradosView
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GradosView gv = new GradosView();
                gv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gv.setLocationRelativeTo(null);
                // gv.setExtendedState(JFrame.MAXIMIZED_BOTH);
                gv.setVisible(true);

                dispose();
            }
        });
    }// GEN-LAST:event_goToGradosView
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField textFieldFiltroConcidencias;
    // End of variables declaration//GEN-END:variables
}
