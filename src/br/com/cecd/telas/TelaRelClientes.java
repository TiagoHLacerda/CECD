/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.cecd.telas;

import br.com.cecd.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author tiago
 */
public class TelaRelClientes extends javax.swing.JInternalFrame {
   Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form TelaRelClientes
     */
    public TelaRelClientes() {
        initComponents();
          conexao = ModuloConexao.conector();
            pesquisarCli();
    }

    
     

     private void pesquisarCli() {
        String sql = "select  idcli as Matrícula, nome as Nome , endereco as Endereço, fone as Telefone, email as Email from tbclientes";
        // String sql = "select *from tbclientes  where nome like ?";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            tblRelCli.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

  
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCli = new javax.swing.JTable();
        tabelaCli = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return(false);}

        }

        ;
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaCli = new javax.swing.JTable();
        tblRelCli = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return(false);}

        }

        ;

        tabelaCli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Matrícula", "Nome", "Endereço", "Telefone", "Email"
            }
        ));
        tabelaCli.setFocusable(false);
        tabelaCli.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaCli);

        setClosable(true);

        tblRelCli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Matrícula", "Nome", "Endereço", "Telefone", "Email"
            }
        ));
        tblRelCli.setFocusable(false);
        tblRelCli.getTableHeader().setReorderingAllowed(false);
        tblRelCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRelCliMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblRelCli);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblRelCliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRelCliMouseClicked

      
    }//GEN-LAST:event_tblRelCliMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaCli;
    private javax.swing.JTable tblRelCli;
    // End of variables declaration//GEN-END:variables
}
