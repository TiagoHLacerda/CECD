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
public class TelaProdutos extends javax.swing.JInternalFrame {

    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaProdutos
     */
    public TelaProdutos() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void limparCampos() {
        txtProdProcurar.setText(null);
        txtProdCod.setText(null);
        txtProdNome.setText(null);
        txtProdQtd.setText(null);
        txtProdValorUni.setText(null);

        ((DefaultTableModel) tabelaProd.getModel()).setRowCount(0);

    }

    public boolean prodDuplicado() {
        String sqlVerifica = "select *from tbProdutos where nomeproduto=?";

        try {
            pst = conexao.prepareStatement(sqlVerifica);
            pst.setString(1, txtProdNome.getText());
            rs = pst.executeQuery();
            Boolean verificado = null;
            if (rs.next()) {

                return verificado = true;

            } else {

                return verificado = false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    public void setarCampos() {

        int setar = tabelaProd.getSelectedRow();
        txtProdCod.setText(tabelaProd.getModel().getValueAt(setar, 0).toString());
        txtProdNome.setText(tabelaProd.getModel().getValueAt(setar, 1).toString());
        txtProdQtd.setText(tabelaProd.getModel().getValueAt(setar, 2).toString());
        txtProdValorUni.setText(tabelaProd.getModel().getValueAt(setar, 3).toString());
        // txtCliEmail.setText(tabelaCli.getModel().getValueAt(setar, 4).toString());

        btnProdAdicionar.setEnabled(false);
    }

    private void adc() {

        String sql = """                         
                     insert into tbProdutos (nomeproduto,qtdproduto, valoruniprod)
                     values(?,?,?)""";

        try {
            if (prodDuplicado() == false) {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtProdNome.getText());
                pst.setString(2, txtProdQtd.getText());
                pst.setString(3, txtProdValorUni.getText());

                if ((txtProdNome.getText().isEmpty())
                        || (txtProdQtd.getText().isEmpty())
                        || txtProdValorUni.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");

                } else {

                    int adcionar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cadastrar um novo Produto?? ", "Atenção", JOptionPane.YES_NO_OPTION);
                    //  System.out.println(adcionar);

                    if (adcionar == 0) {
                        pst.executeUpdate();

                        limparCampos();

                        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso! ");
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto não cadastrado ! ");
                        //   limparCampos();
                        //     return;
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Produto ja  possui cadastro");

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

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
        String sql = "update tbProdutos set nomeproduto=?,qtdproduto=?,valoruniprod=? where idproduto=?";

        try {
            pst = conexao.prepareStatement(sql);

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtProdNome.getText());
            pst.setString(2, txtProdQtd.getText());
            pst.setString(3, txtProdValorUni.getText());
            pst.setString(4, txtProdCod.getText());

            if ((txtProdNome.getText().isEmpty()) || (txtProdQtd.getText().isEmpty())
                    || (txtProdValorUni.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
                //  return;

            } else {
                int adcionar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja atualizar os dados desse produto?? ", "Atenção", JOptionPane.YES_NO_OPTION);
                //  System.out.println(adcionar);

                if (adcionar == 0) {
                    pst.executeUpdate();

                    limparCampos();
                    btnProdAdicionar.setEnabled(true);

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

    private void delete() {

        String sql = "delete  from tbProdutos where idproduto=? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtProdCod.getText());
            pst.executeUpdate();

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
        jLabel1 = new javax.swing.JLabel();
        btnProdAtualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProd = new javax.swing.JTable();
        tabelaProd = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return(false);}

        }

        ;
        btnProdAdicionar = new javax.swing.JButton();
        btnProdDeletar = new javax.swing.JButton();
        txtProdNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtProdCod = new javax.swing.JTextField();
        txtProdQtd = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de produtos");

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

        jLabel3.setText("Valor Unitário  R$");

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

        btnProdAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cecd/icones/add.png"))); // NOI18N
        btnProdAdicionar.setToolTipText("Adicionar ");
        btnProdAdicionar.setBorder(null);
        btnProdAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProdAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdAdicionarActionPerformed(evt);
            }
        });

        btnProdDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cecd/icones/remover.png"))); // NOI18N
        btnProdDeletar.setToolTipText("Deletar");
        btnProdDeletar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnProdDeletar.setBorderPainted(false);
        btnProdDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProdDeletar.setDoubleBuffered(true);
        btnProdDeletar.setFocusPainted(false);
        btnProdDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdDeletarActionPerformed(evt);
            }
        });

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

        txtProdQtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProdQtdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(btnProdAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnProdAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129)
                .addComponent(btnProdDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtProdProcurar)
                .addGap(72, 72, 72))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addGap(3, 3, 3)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProdQtd)
                            .addComponent(txtProdNome)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtProdCod, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLcampos)
                                .addGap(37, 37, 37))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtProdValorUni, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(24, 24, 24))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProdAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProdAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProdDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLcamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLcamposActionPerformed
        limparCampos();
        btnProdAdicionar.setEnabled(true);

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

    private void btnProdAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdAtualizarActionPerformed
        // TODO add your handling code here:

        updt();

    }//GEN-LAST:event_btnProdAtualizarActionPerformed

    private void tabelaProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdMouseClicked

        setarCampos();

    }//GEN-LAST:event_tabelaProdMouseClicked

    private void btnProdAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdAdicionarActionPerformed

        adc();
        limparCampos();

    }//GEN-LAST:event_btnProdAdicionarActionPerformed

    private void btnProdDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdDeletarActionPerformed
        int deletar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar esse produto?? ", "Atenção", JOptionPane.YES_NO_OPTION);
        System.out.println(JOptionPane.YES_OPTION);
        if (deletar == 0) {
            delete();
            limparCampos();
            btnProdAdicionar.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso! ");
        } else {
            limparCampos();
            return;

        }
    }//GEN-LAST:event_btnProdDeletarActionPerformed

    private void txtProdNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProdNomeActionPerformed

    private void txtProdCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProdCodActionPerformed

    private void txtProdQtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdQtdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProdQtdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLcampos;
    private javax.swing.JButton btnProdAdicionar;
    private javax.swing.JButton btnProdAtualizar;
    private javax.swing.JButton btnProdDeletar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaProd;
    private javax.swing.JTextField txtProdCod;
    private javax.swing.JTextField txtProdNome;
    private javax.swing.JTextField txtProdProcurar;
    private javax.swing.JTextField txtProdQtd;
    private javax.swing.JTextField txtProdValorUni;
    // End of variables declaration//GEN-END:variables
}
