/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.cecd.telas;

import java.sql.*;
import br.com.cecd.dal.ModuloConexao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author tiago
 */
public class TelaEntrada extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaEntrada
     */
    public TelaEntrada() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void limparCampos() {
        txtProdProcurar.setText(null);
        txtProdCod.setText(null);
        txtProdNome.setText(null);
        txtProdQtd.setText(null);
        txtProdValorUni.setText(null);
        txtQtdAdc.setText(null);
        ((DefaultTableModel) tabelaProd.getModel()).setRowCount(0);

    }

    public void setarCampos() {

        int setar = tabelaProd.getSelectedRow();
        txtProdCod.setText(tabelaProd.getModel().getValueAt(setar, 0).toString());
        txtProdNome.setText(tabelaProd.getModel().getValueAt(setar, 1).toString());
        txtProdQtd.setText(tabelaProd.getModel().getValueAt(setar, 2).toString());
        txtProdValorUni.setText(tabelaProd.getModel().getValueAt(setar, 3).toString());
        // txtCliEmail.setText(tabelaCli.getModel().getValueAt(setar, 4).toString());

    }

    private void pesquisarCli() {
        String sql = "select idproduto as Código, nomeproduto as Descrição , qtdproduto as Quantidade, valoruniprod as Valor from tbProdutos where nomeproduto like ?";
        // String sql = "select *from tbclientes  where nome like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtProdProcurar.getText() + "%");
            rs = pst.executeQuery();
            tabelaProd.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void updt() {
        String sql = "update tbProdutos set qtdproduto=?,valoruniprod=? where idproduto=?";

        try {
            //   int qtdEstoq = txtProdQtd();
            pst = conexao.prepareStatement(sql);
            pst = conexao.prepareStatement(sql);

            int qtdEst = Integer.parseInt(txtProdQtd.getText());
            int qtdEstAdc = Integer.parseInt(txtQtdAdc.getText());
            int somaEst = qtdEst + qtdEstAdc;

            pst.setInt(1, somaEst);
            pst.setString(2, txtProdValorUni.getText());
            pst.setString(3, txtProdCod.getText());

            if ((txtQtdAdc.getText().isEmpty()) || (txtProdValorUni.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
                //  return;

            } else {
                int adcionar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja atualizar os dados desse produto?? ", "Atenção", JOptionPane.YES_NO_OPTION);
                //  System.out.println(adcionar);

                if (adcionar == 0) {
                    pst.executeUpdate();

                    limparCampos();

                    JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso! ");
                } else {
                    JOptionPane.showMessageDialog(null, "Dados não atualizados ! ");
                    //  limparCampos();
                    return;
                }

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLcampos = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtProdValorUni = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtProdProcurar = new javax.swing.JTextField();
        txtProdQtd = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnProdAtualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProd = new javax.swing.JTable();
        tabelaProd = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return(false);}

        }

        ;
        txtProdNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtProdCod = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtQtdAdc = new javax.swing.JTextField();

        btnLcampos.setText("Limpar campos");
        btnLcampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLcamposActionPerformed(evt);
            }
        });

        jLabel4.setText("Qtd");

        txtProdValorUni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProdValorUniActionPerformed(evt);
            }
        });

        jLabel3.setText("Valor Unitário       R$");

        txtProdProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProdProcurarActionPerformed(evt);
            }
        });
        txtProdProcurar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProdProcurarKeyReleased(evt);
            }
        });

        txtProdQtd.setEnabled(false);
        txtProdQtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProdQtdActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cecd/icones/pesquisar.png"))); // NOI18N

        btnProdAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cecd/icones/editar.png"))); // NOI18N
        btnProdAtualizar.setToolTipText("Editar");
        btnProdAtualizar.setBorder(null);
        btnProdAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProdAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdAtualizarActionPerformed(evt);
            }
        });

        tabelaProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Produto", "Quantidade", "Valor unitário"
            }
        ));
        tabelaProd.setFocusable(false);
        tabelaProd.getTableHeader().setReorderingAllowed(false);
        tabelaProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaProd);

        txtProdNome.setEnabled(false);
        txtProdNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProdNomeActionPerformed(evt);
            }
        });

        jLabel5.setText("Código");

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Produto");

        txtProdCod.setEnabled(false);
        txtProdCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProdCodActionPerformed(evt);
            }
        });

        jLabel7.setText("Quantidade de entrada");

        txtQtdAdc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtdAdcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtProdProcurar)
                .addGap(72, 72, 72))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProdQtd)
                            .addComponent(txtProdNome)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtProdCod, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLcampos)
                                .addGap(37, 37, 37))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProdValorUni, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQtdAdc, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(btnProdAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txtProdProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtProdCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLcampos, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProdNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProdQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProdValorUni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQtdAdc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(btnProdAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLcamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLcamposActionPerformed
        limparCampos();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnLcamposActionPerformed

    private void txtProdValorUniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdValorUniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProdValorUniActionPerformed

    private void txtProdProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdProcurarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProdProcurarActionPerformed

    private void txtProdProcurarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdProcurarKeyReleased
        // TODO add your handling code here:

        pesquisarCli();
    }//GEN-LAST:event_txtProdProcurarKeyReleased

    private void txtProdQtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdQtdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProdQtdActionPerformed

    private void btnProdAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdAtualizarActionPerformed
        // TODO add your handling code here:

        updt();
    }//GEN-LAST:event_btnProdAtualizarActionPerformed

    private void tabelaProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdMouseClicked

        setarCampos();
    }//GEN-LAST:event_tabelaProdMouseClicked

    private void txtProdNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProdNomeActionPerformed

    private void txtProdCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProdCodActionPerformed

    private void txtQtdAdcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtdAdcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtdAdcActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLcampos;
    private javax.swing.JButton btnProdAtualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaProd;
    private javax.swing.JTextField txtProdCod;
    private javax.swing.JTextField txtProdNome;
    private javax.swing.JTextField txtProdProcurar;
    private javax.swing.JTextField txtProdQtd;
    private javax.swing.JTextField txtProdValorUni;
    private javax.swing.JTextField txtQtdAdc;
    // End of variables declaration//GEN-END:variables
}
