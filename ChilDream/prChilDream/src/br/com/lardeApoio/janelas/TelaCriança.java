/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lardeApoio.janelas;

/**
 *
 * @author beatr
 */
import java.sql.*;
import br.com.lardeApoio.camadadeacessoadados.ModulodeConexao;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;


public class TelaCriança extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCriança
     */
    
    Connection conexao = null;
    PreparedStatement pcc = null;
    ResultSet rs = null; 
    
    public TelaCriança() {
        initComponents();
          conexao = ModulodeConexao.conexao();
        
        
    }

     private void exibir() {
        String sql = "select * from crianca where idCrianca=?";
        try {
            pcc = conexao.prepareStatement(sql);
            //SUBSTITUI O  ?
            pcc.setString(1, txtId.getText());
            rs = pcc.executeQuery();
            if (rs.next()) {
                txtNome.setText(rs.getString(2));
                txtRg.setText(rs.getString(3));
                txtCpf.setText(rs.getString(4));
                txtData.setText(rs.getString(5));
                if(rs.getString(6).equals("M"))
                    rdM.setSelected(true);
                else 
                    rdF.setSelected(true);
                //COMBOBOX
                txtEtnia.setSelectedItem(rs.getString(7));

            } else {
                JOptionPane.showMessageDialog(null, " não cadastrado");
                //LIMPAR CAMPOS
                txtNome.setText(null);
                txtRg.setText(null);
                txtCpf.setText(null);
                txtData.setText(null);
                btSexo.clearSelection();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void inserir() {
        String sql = "insert into crianca(nomeCrianca, rgCrianca, cpfCrianca, dataNascCrianca, sexoCrianca, etniaCrianca) "
                + "values(?,?,?,?,?,?)";
        try {
            pcc = conexao.prepareStatement(sql);
            pcc.setString(1, txtNome.getText());
            pcc.setString(2, txtRg.getText());
            pcc.setString(3, txtId.getText());
            pcc.setString(4, txtData.getText());
            if(rdM.isSelected())
                pcc.setString(5, "M");
            else
                pcc.setString(5, "F");
            
            pcc.setString(6, txtEtnia.getSelectedItem().toString());
            //INSERÇÃO 
            // || OPERADOR LOGICO OU 
            if (txtNome.getText().isEmpty() || txtData.getText().isEmpty()
                    || (!rdM.isSelected() && !rdF.isSelected())) {
                JOptionPane.showMessageDialog(null, " preencha os campos obrigatorios ");
            } else {

                int adc = pcc.executeUpdate();
                if (adc > 0) {
                    JOptionPane.showMessageDialog(null, " Cadastrado ");

                    // MOSTRA 1 - ADICIONADO COM SUCESSO 
                    System.out.println(adc);

                    txtId.setText(null);
                    txtNome.setText(null);
                    txtRg.setText(null);
                    txtCpf.setText(null);
                    txtData.setText(null);
                    btSexo.clearSelection();
                    // txtetinia.setSelectedItem(null);
                }else{
                    JOptionPane.showMessageDialog(null,"erro ao cadastrar");
                }
            }
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void editar() {
        String sql = "update crianca set nomeCrianca=?, rgCrianca=?, cpfCrianca=?, dataNascCrianca=?, "
                + "sexoCrianca=?, etniaCrianca=? where idCrianca=? ";

        try {
            pcc = conexao.prepareStatement(sql);
            pcc.setString(1, txtNome.getText());
            pcc.setString(2, txtRg.getText());
            pcc.setString(3, txtCpf.getText());
            pcc.setString(4, txtData.getText());
            if(rdM.isSelected())
                pcc.setString(5, "M");
            else
                pcc.setString(5, "F");
            pcc.setString(6, txtEtnia.getSelectedItem().toString());
            pcc.setString(7, txtId.getText());
            if (txtNome.getText().isEmpty() || txtData.getText().isEmpty()
                    || (!rdM.isSelected() && !rdF.isSelected())) {
                JOptionPane.showMessageDialog(null, " preencha os campos obrigatorios ");
            } else {

                //ALTERAR ADICIONAR EXECUTE UPDATE 
                int adc = pcc.executeUpdate();
                if (adc > 0) {
                    JOptionPane.showMessageDialog(null, " dado alterado ");

                    // MOSTRA 1 - ADICIONADO COM SUCESSO 
                    System.out.println(adc);

                    txtNome.setText(null);
                    txtRg.setText(null);
                    txtCpf.setText(null);
                    txtData.setText(null);
                    btSexo.clearSelection();
                   
                } else{
                    JOptionPane.showMessageDialog(null, "Erro ao alterar dado(s)");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void excluir() {

        int confirma = JOptionPane.showConfirmDialog(null, " Tem certeza que deseja remover ?", "atenção", JOptionPane.YES_NO_CANCEL_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            String sql = "delete from crianca where idCrianca=?";

            try {
                pcc = conexao.prepareStatement(sql);
                pcc.setString(1, txtId.getText());
                int excluido = pcc.executeUpdate();
                if (excluido > 0) {
                    JOptionPane.showMessageDialog(null, " excluido ");
                    txtId.setText(null);
                    txtNome.setText(null);
                    txtRg.setText(null);
                    txtCpf.setText(null);
                    txtData.setText(null);
                    btSexo.clearSelection();
                }else{
                    JOptionPane.showMessageDialog(null, "erro ao excluir");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
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

        btSexo = new javax.swing.ButtonGroup();
        id = new javax.swing.JLabel();
        txtEtnia = new javax.swing.JComboBox<>();
        nome = new javax.swing.JLabel();
        editar = new javax.swing.JButton();
        data = new javax.swing.JLabel();
        novo = new javax.swing.JButton();
        rg = new javax.swing.JLabel();
        exibir = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        etnia = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        txtRg = new javax.swing.JTextField();
        txtCpf = new javax.swing.JTextField();
        cpf = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rdM = new javax.swing.JRadioButton();
        rdF = new javax.swing.JRadioButton();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(470, 568));

        id.setText("ID");

        txtEtnia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "preta", "amarela", "parda", "branca", "indígena" }));

        nome.setText("Nome");

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/lardeApoio/icones/edit.png"))); // NOI18N
        editar.setToolTipText("Editar");
        editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        data.setText("Data Nascimento");

        novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/lardeApoio/icones/new.png"))); // NOI18N
        novo.setToolTipText("Novo");
        novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoActionPerformed(evt);
            }
        });

        rg.setText("RG");

        exibir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/lardeApoio/icones/view.png"))); // NOI18N
        exibir.setToolTipText("Exibir");
        exibir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exibirActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/lardeApoio/icones/delete.png"))); // NOI18N
        jButton4.setToolTipText("Excluir");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        etnia.setText("Etnia");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        txtData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataActionPerformed(evt);
            }
        });

        txtRg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRgActionPerformed(evt);
            }
        });

        cpf.setText("CPF");

        jLabel2.setText("Sexo");

        btSexo.add(rdM);
        rdM.setText("Masculino");
        rdM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMActionPerformed(evt);
            }
        });

        btSexo.add(rdF);
        rdF.setText("Feminino");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(novo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exibir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(nome)
                            .addComponent(id)
                            .addComponent(rg)
                            .addComponent(cpf)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etnia)
                                .addGap(4, 4, 4))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(data)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRg, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtData)
                            .addComponent(txtNome)
                            .addComponent(txtId)
                            .addComponent(txtCpf))
                        .addGap(105, 105, 105))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtEtnia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdM)
                        .addGap(58, 58, 58)
                        .addComponent(rdF)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rg))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cpf))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(data))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdM)
                        .addComponent(rdF)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEtnia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etnia))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(exibir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(novo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_editarActionPerformed

    private void novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoActionPerformed
        // TODO add your handling code here:
        inserir();
    }//GEN-LAST:event_novoActionPerformed

    private void exibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exibirActionPerformed
        // TODO add your handling code here:
        exibir();
    }//GEN-LAST:event_exibirActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        excluir();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtRgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRgActionPerformed

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataActionPerformed

    private void rdMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btSexo;
    private javax.swing.JLabel cpf;
    private javax.swing.JLabel data;
    private javax.swing.JButton editar;
    private javax.swing.JLabel etnia;
    private javax.swing.JButton exibir;
    private javax.swing.JLabel id;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel nome;
    private javax.swing.JButton novo;
    private javax.swing.JRadioButton rdF;
    private javax.swing.JRadioButton rdM;
    private javax.swing.JLabel rg;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtData;
    private javax.swing.JComboBox<String> txtEtnia;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRg;
    // End of variables declaration//GEN-END:variables
}
