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
public class TelaClientes extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCliente
     */
    public TelaClientes() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void limparCampos() {
        txtClientesProcurar.setText(null);
        txtCliMatricula.setText(null);
        txtCliNome.setText(null);
        txtCliEndereco.setText(null);
        txtCliTelefone.setText(null);
        txtCliEmail.setText(null);
        ((DefaultTableModel) tabelaCli.getModel()).setRowCount(0);

    }

    private void adc() {

        String sql = """                         
                     insert into tbclientes (nome,endereco, fone,email)
                     values(?,?,?,?)""";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliTelefone.getText());
            pst.setString(4, txtCliEmail.getText());

            if ((txtCliNome.getText().isEmpty())
                    || (txtCliNome.getText().isEmpty())
                    || txtCliTelefone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
                //   return;
            } else {
                int adcionar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cadastrar um novo Cliente?? ", "Atenção", JOptionPane.YES_NO_OPTION);
                //  System.out.println(adcionar);

                if (adcionar == 0) {
                    int adc = pst.executeUpdate();

                    limparCampos();

                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso! ");
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não cadastrado ! ");
                    //   limparCampos();
                    //     return;
                }

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void pesquisarCli() {
        String sql = "select idcli as Matrícula, nome as Nome , endereco as Endereço, fone as Telefone, email as Email from tbclientes  where nome like ?";
        // String sql = "select *from tbclientes  where nome like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtClientesProcurar.getText() + "%");
            rs = pst.executeQuery();
            tabelaCli.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void setarCampos() {

        int setar = tabelaCli.getSelectedRow();
        txtCliMatricula.setText(tabelaCli.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(tabelaCli.getModel().getValueAt(setar, 1).toString());
        txtCliEndereco.setText(tabelaCli.getModel().getValueAt(setar, 2).toString());
        txtCliTelefone.setText(tabelaCli.getModel().getValueAt(setar, 3).toString());
        txtCliEmail.setText(tabelaCli.getModel().getValueAt(setar, 4).toString());

        btnCliAdicionar.setEnabled(false);

    }

    private void updt() {
        String sql = "update tbclientes set nome=?,endereco=?,fone=?,email=? where idcli=?";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEmail.getText());
            pst.setString(3, txtCliEndereco.getText());
            pst.setString(4, txtCliTelefone.getText());
            pst.setString(5, txtCliMatricula.getText());

            if ((txtCliNome.getText().isEmpty())
                    || (txtCliTelefone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
                //  return;

            } else {
                int adcionar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja atualizar os dados desse cliente?? ", "Atenção", JOptionPane.YES_NO_OPTION);
                //  System.out.println(adcionar);

                if (adcionar == 0) {
                    int adc = pst.executeUpdate();

                    limparCampos();
                    btnCliAdicionar.setEnabled(true);

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

        String sql = "delete  from tbclientes where idcli=? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliMatricula.getText());
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

        txtClientesProcurar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCli = new javax.swing.JTable();
        tabelaCli = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return(false);}

        }

        ;
        txtCliNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCliEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCliEndereco = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCliTelefone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnCliAtualizar = new javax.swing.JButton();
        btnCliAdicionar = new javax.swing.JButton();
        btnCliDeletar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCliMatricula = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Clientes");
        setToolTipText("");

        txtClientesProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClientesProcurarActionPerformed(evt);
            }
        });
        txtClientesProcurar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClientesProcurarKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cecd/icones/pesquisar.png"))); // NOI18N

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
        tabelaCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCli);
        if (tabelaCli.getColumnModel().getColumnCount() > 0) {
            tabelaCli.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            tabelaCli.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            tabelaCli.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            tabelaCli.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }
        tabelaCli.getAccessibleContext().setAccessibleName("");
        tabelaCli.getAccessibleContext().setAccessibleDescription("");

        txtCliNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliNomeActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Nome");

        txtCliEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliEmailActionPerformed(evt);
            }
        });

        jLabel4.setText("email");

        txtCliEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliEnderecoActionPerformed(evt);
            }
        });

        jLabel3.setText("Endereço");

        txtCliTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliTelefoneActionPerformed(evt);
            }
        });

        jLabel6.setText("Telefone");

        btnCliAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cecd/icones/editar.png"))); // NOI18N
        btnCliAtualizar.setToolTipText("Editar");
        btnCliAtualizar.setBorder(null);
        btnCliAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAtualizarActionPerformed(evt);
            }
        });

        btnCliAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cecd/icones/add.png"))); // NOI18N
        btnCliAdicionar.setToolTipText("Adicionar ");
        btnCliAdicionar.setBorder(null);
        btnCliAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAdicionarActionPerformed(evt);
            }
        });

        btnCliDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cecd/icones/remover.png"))); // NOI18N
        btnCliDeletar.setToolTipText("Deletar");
        btnCliDeletar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCliDeletar.setBorderPainted(false);
        btnCliDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliDeletar.setDoubleBuffered(true);
        btnCliDeletar.setFocusPainted(false);
        btnCliDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliDeletarActionPerformed(evt);
            }
        });

        jLabel5.setText("Matrícula");

        txtCliMatricula.setEnabled(false);
        txtCliMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliMatriculaActionPerformed(evt);
            }
        });

        jButton1.setText("Limpar campos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(btnCliAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCliAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129)
                .addComponent(btnCliDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtClientesProcurar)
                .addGap(72, 72, 72))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCliEmail)
                            .addComponent(txtCliEndereco)
                            .addComponent(txtCliNome)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(37, 37, 37)))))
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
                        .addComponent(txtClientesProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCliMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCliAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliNomeActionPerformed

    private void txtCliEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliEnderecoActionPerformed

    private void txtCliTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliTelefoneActionPerformed

    private void btnCliAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAtualizarActionPerformed
        // TODO add your handling code here:

        updt();


    }//GEN-LAST:event_btnCliAtualizarActionPerformed

    private void btnCliAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAdicionarActionPerformed

        adc();
        limparCampos();


    }//GEN-LAST:event_btnCliAdicionarActionPerformed


    private void btnCliDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliDeletarActionPerformed
        int deletar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar esse cliente?? ", "Atenção", JOptionPane.YES_NO_OPTION);
        System.out.println(JOptionPane.YES_OPTION);
        if (deletar == 0) {
            delete();
            limparCampos();
            btnCliAdicionar.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso! ");
        } else {
            limparCampos();
            return;

        }

    }//GEN-LAST:event_btnCliDeletarActionPerformed


    private void txtCliEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliEmailActionPerformed

    private void txtClientesProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClientesProcurarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClientesProcurarActionPerformed

    private void txtClientesProcurarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientesProcurarKeyReleased
        // TODO add your handling code here:

        pesquisarCli();


    }//GEN-LAST:event_txtClientesProcurarKeyReleased

    private void tabelaCliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCliMouseClicked

        setarCampos();


    }//GEN-LAST:event_tabelaCliMouseClicked

    private void txtCliMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliMatriculaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limparCampos();
        btnCliAdicionar.setEnabled(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliAdicionar;
    private javax.swing.JButton btnCliAtualizar;
    private javax.swing.JButton btnCliDeletar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaCli;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliMatricula;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliTelefone;
    private javax.swing.JTextField txtClientesProcurar;
    // End of variables declaration//GEN-END:variables
}
