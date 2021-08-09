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
import java.time.Clock;
import javax.swing.JOptionPane;

public class TelaFuncionario extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaPais
     */
    // PACOTE JAVA SQL 
    Connection conexao = null;
    PreparedStatement pcc = null;
    ResultSet rs = null;

    //METODO CONSTRUTOR 
    public TelaFuncionario() {

        initComponents();

        //INSTANCIA 
        conexao = ModulodeConexao.conexao();
    }

    private void exibir() {
        String sql = "select * from funcionarios where idFunc=?";
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
                txtLogin.setText(rs.getString(7));
                txtSenha.setText(rs.getString(8));
                //COMBOBOX
                if(rs.getString(6).equals("1"))
                    txtPerfil.setSelectedItem("Gerente");
                else
                    txtPerfil.setSelectedItem("Funcionario");

            } else {
                JOptionPane.showMessageDialog(null, "usuario não cadastrado");
                //LIMPAR CAMPOS
                txtNome.setText(null);
                txtRg.setText(null);
                txtCpf.setText(null);
                txtData.setText(null);
                txtLogin.setText(null);
                txtSenha.setText(null);
                // txtPerfil.setSelectedItem(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }

    }

    private void inserir() {
        String perfil;
        String sql = "insert into funcionarios(nomeFunc, rgFunc, cpfFunc, dataNascFunc, GerenteFunc, loginFunc, senhaFunc) "
                + "values(?,?,?,?,?,?,?)";
        try {
            pcc = conexao.prepareStatement(sql);
            pcc.setString(1, txtNome.getText());
            pcc.setString(2, txtRg.getText());
            pcc.setString(3, txtCpf.getText());
            pcc.setString(4, txtData.getText());
            pcc.setString(6, txtLogin.getText());
            pcc.setString(7, txtSenha.getText());
            // NAO SABE Q É TXT, CONVERTER
            if(txtPerfil.getSelectedItem().toString().equals("Gerente"))
                perfil = "1";
            else
                perfil = "0";
            pcc.setString(5, perfil);
            //INSERÇÃO 
            // || OPERADOR LOGICO OU 
            if (txtNome.getText().isEmpty() || txtRg.getText().isEmpty()
                    || txtCpf.getText().isEmpty() || txtData.getText().isEmpty()
                    || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, " preencha os campos obrigatorios");
            } else {

                int adc = pcc.executeUpdate();
                if (adc > 0) {
                    JOptionPane.showMessageDialog(null, " Funcionário inserido ");

                    // MOSTRA 1 - ADICIONADO COM SUCESSO 
                    System.out.println(adc);

                    txtId.setText(null);
                    txtNome.setText(null);
                    txtRg.setText(null);
                    txtCpf.setText(null);
                    txtData.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                    // txtPerfil.setSelectedItem(null);
                }else{
                   JOptionPane.showMessageDialog(null,"erro ao cadastrar"); 
                }
            }

        } catch (Exception e) {
            System.out.println(e);
            
        }

    }

    private void editar() {
        String perfil;
        String sql = "update funcionarios set nomeFunc=?, rgFunc=?, cpfFunc=?, dataNascFunc=?, "
                + "GerenteFunc=?, loginFunc=?, senhaFunc=? where idFunc=? ";

        try {
            pcc = conexao.prepareStatement(sql);
            pcc.setString(1, txtNome.getText());
            pcc.setString(2, txtRg.getText());
            pcc.setString(3, txtCpf.getText());
            pcc.setString(4, txtData.getText());
            pcc.setString(6, txtLogin.getText());
            pcc.setString(7, txtSenha.getText());
            pcc.setString(8, txtId.getText());
            if(txtPerfil.getSelectedItem().toString().equals("Gerente"))
                perfil = "1";
            else
                perfil = "0";
            pcc.setString(5, perfil);
            
            if (txtNome.getText().isEmpty() || txtRg.getText().isEmpty()
                    || txtCpf.getText().isEmpty() || txtData.getText().isEmpty()
                    || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, " preencha os campos obrigatorios");
            } else {

                //ALTERAR ADICIONAR EXECUTE UPDATE 
                int adc = pcc.executeUpdate();
                if (adc > 0) {
                    JOptionPane.showMessageDialog(null, " Dado(s) alterado(s) ");

                    // MOSTRA 1 - ADICIONADO COM SUCESSO 
                    System.out.println(adc);

                    txtId.setText(null);
                    txtNome.setText(null);
                    txtRg.setText(null);
                    txtCpf.setText(null);
                    txtData.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                    // txtPerfil.setSelectedItem(null);
                } else{
                    JOptionPane.showMessageDialog(null, "Erro ao alterar dado(s)");
                }
            }

        } catch (Exception e) {
             System.out.println(e);
        }
    }

    private void excluir() {

        int confirma = JOptionPane.showConfirmDialog(null, " Tem certeza que deseja remover ?", "atenção", JOptionPane.YES_NO_CANCEL_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            String sql = "delete from funcionarios where idFunc=?";

            try {
                pcc = conexao.prepareStatement(sql);
                pcc.setString(1, txtId.getText());
                int excluido = pcc.executeUpdate();
                if (excluido > 0) {
                    JOptionPane.showMessageDialog(null, " Funcionário excluido! ");
                    txtId.setText(null);
                    txtNome.setText(null);
                    txtRg.setText(null);
                    txtCpf.setText(null);
                    txtData.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);

                }else{
                    JOptionPane.showMessageDialog(null, "erro ao excluir");
                }

            } catch (Exception e) {
                System.out.println(e);
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

        id = new javax.swing.JLabel();
        nome = new javax.swing.JLabel();
        data = new javax.swing.JLabel();
        login = new javax.swing.JLabel();
        senha = new javax.swing.JLabel();
        perfil = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        txtPerfil = new javax.swing.JComboBox<>();
        editar = new javax.swing.JButton();
        novo = new javax.swing.JButton();
        exibir = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        rg = new javax.swing.JLabel();
        txtRg = new javax.swing.JTextField();
        cpf = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();

        setClosable(true);
        setTitle("Funcionarios");
        setPreferredSize(new java.awt.Dimension(470, 568));

        id.setText("ID");

        nome.setText("Nome");

        data.setText("Data Nascimento");

        login.setText("Login");

        senha.setText("Senha");

        perfil.setText("Perfil");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });

        txtPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerente", "Funcionario"}));

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/lardeApoio/icones/edit.png"))); // NOI18N
        editar.setToolTipText("Editar");
        editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/lardeApoio/icones/new.png"))); // NOI18N
        novo.setToolTipText("Novo");
        novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoActionPerformed(evt);
            }
        });

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

        rg.setText("RG");

        cpf.setText("CPF");

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
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cpf)
                            .addComponent(rg)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(login, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(nome))
                                .addComponent(senha, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(perfil)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(data))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLogin, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtData)
                    .addComponent(txtNome)
                    .addComponent(txtId)
                    .addComponent(txtRg)
                    .addComponent(txtCpf)
                    .addComponent(txtSenha))
                .addGap(102, 102, 102))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rg)
                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpf)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(data))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(senha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(perfil)
                    .addComponent(txtPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(exibir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(novo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        setBounds(0, 0, 470, 568);
    }// </editor-fold>//GEN-END:initComponents

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

    private void novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoActionPerformed
        // TODO add your handling code here:
        inserir();
    }//GEN-LAST:event_novoActionPerformed

    private void exibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exibirActionPerformed
        // TODO add your handling code here:
        exibir();
    }//GEN-LAST:event_exibirActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_editarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        excluir();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cpf;
    private javax.swing.JLabel data;
    private javax.swing.JButton editar;
    private javax.swing.JButton exibir;
    private javax.swing.JLabel id;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel login;
    private javax.swing.JLabel nome;
    private javax.swing.JButton novo;
    private javax.swing.JLabel perfil;
    private javax.swing.JLabel rg;
    private javax.swing.JLabel senha;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JComboBox<String> txtPerfil;
    private javax.swing.JTextField txtRg;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
