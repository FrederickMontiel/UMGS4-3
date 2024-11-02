package com.fmontiel.eventos.views;

import java.util.ArrayList;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;
import com.fmontiel.eventos.models.PersonaModel;
import com.fmontiel.eventos.services.PersonasService;
import com.fmontiel.eventos.views.personasview.ButtonEditor;
import com.fmontiel.eventos.views.personasview.ButtonRenderer;
import com.fmontiel.eventos.views.personasview.Formulario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author PC
 */
public class PersonasView extends javax.swing.JFrame {
        private Long idEvento;
    private PersonasService personasService;
    private ArrayList<PersonaModel> listaPersonas;

    @Override
    public void dispose() {
        super.dispose();
        IndexView gv = new IndexView();
        gv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gv.setLocationRelativeTo(null);
        gv.setVisible(true);
    }

    /**
     * Constructor que recibe el ID del evento y obtiene las personas del evento.
     */
    public PersonasView(Long idEvento) {
        this.idEvento = idEvento;
        this.personasService = new PersonasService();

        initComponents();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            obtenerPersonas();
            rellenarTabla();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para obtener las personas asociadas al evento.
     */
    public void obtenerPersonas()  {
            try {
                listaPersonas = new ArrayList<>(personasService.getPersonasByEvento(idEvento));
            } catch (IOException ex) {
                Logger.getLogger(PersonasView.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    /**
     * Método para llenar la tabla con la lista de personas del evento.
     */
    public void rellenarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][] {},
                new String[] { "CUI", "Nombre", "Email", "Teléfono", "Acciones" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Solo la columna de "Acciones" es editable para permitir los botones
            }
        };

        modelo.setRowCount(0);

        for (PersonaModel persona : listaPersonas) {
            Object[] fila = {
                    persona.getId(), // CUI
                    persona.getNombre(),
                    persona.getEmail(),
                    persona.getTelefono(),
                    null // Espacio para los botones de acciones (Ver / Editar / Eliminar)
            };
            modelo.addRow(fila);
        }

        jTable1.setModel(modelo);
        //jTable1.setDefaultRenderer(Object.class, new PaddingCellRenderer());

        jTable1.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        jTable1.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(
                jTable1,
                // Editar
                (JSONObject jo) -> {
                    PersonaModel persona = buscarPersonaPorId(jo.getLong("id"));
                    Formulario f = new Formulario(
                            Formulario.Modos.EDITAR,
                            idEvento,
                            persona,
                            (JSONObject jo1) -> {
                                obtenerPersonas();
                                rellenarTabla();
                            });
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    f.setLocationRelativeTo(null);
                    f.setVisible(true);
                },
                // Eliminar
                (JSONObject jo) -> {
            try {
                if (personasService.removePersonaFromEvento(idEvento, jo.getLong("id"))) {
                    JOptionPane.showMessageDialog(null, "Persona eliminada correctamente del evento");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar la persona del evento");
                }
                obtenerPersonas();
                rellenarTabla();
            } catch (IOException ex) {
                Logger.getLogger(PersonasView.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
        ));

        jTable1.setRowHeight(40);
    }

    /**
     * Método para buscar una persona por su ID en la lista de personas.
     */
    private PersonaModel buscarPersonaPorId(Long id) {
        for (PersonaModel persona : listaPersonas) {
            if (persona.getId().equals(id)) {
                return persona;
            }
        }
        return null;
    }

    private void regresarAGrados(java.awt.event.ActionEvent evt) {
        IndexView gv = new IndexView();
        gv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gv.setLocationRelativeTo(null);
        gv.setVisible(true);
                        gv.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.dispose();
    }

    private void openAgregarView(java.awt.event.ActionEvent evt) {
        Formulario f = new Formulario(
                Formulario.Modos.AGREGAR,
                idEvento,
                new PersonaModel(),
                (JSONObject jo1) -> {
                    obtenerPersonas();
                    rellenarTabla();
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openAgregarView(evt);
            }
        });

        jLabel1.setText("Personas que asistirán al evento");

        jLabel2.setText("Filtrar por: ");

        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarAGrados(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


        /**
         * @param args the command line arguments
         */
        /*
         * public static void main(String args[]) {
         * 
         * //<editor-fold defaultstate="collapsed"
         * desc=" Look and feel setting code (optional) ">
         * 
         * try {
         * for (javax.swing.UIManager.LookAndFeelInfo info :
         * javax.swing.UIManager.getInstalledLookAndFeels()) {
         * if ("Nimbus".equals(info.getName())) {
         * javax.swing.UIManager.setLookAndFeel(info.getClassName());
         * break;
         * }
         * }
         * } catch (ClassNotFoundException ex) {
         * java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.
         * logging.Level.SEVERE, null, ex);
         * } catch (InstantiationException ex) {
         * java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.
         * logging.Level.SEVERE, null, ex);
         * } catch (IllegalAccessException ex) {
         * java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.
         * logging.Level.SEVERE, null, ex);
         * } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         * java.util.logging.Logger.getLogger(GradosView.class.getName()).log(java.util.
         * logging.Level.SEVERE, null, ex);
         * }
         * //</editor-fold>
         * 
         * 
         * java.awt.EventQueue.invokeLater(new Runnable() {
         * public void run() {
         * new GradosView().setVisible(true);
         * }
         * });
         * }
         */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
