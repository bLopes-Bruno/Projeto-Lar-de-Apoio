package br.com.lardeApoio.janelas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author beatr
 */
import java.sql.*;
import br.com.lardeApoio.camadadeacessoadados.ModulodeConexao;
import javax.swing.JOptionPane;
//RECURSOS ANGELSANDDEMONS
import net.proteanit.sql.DbUtils;

public class TelaPais extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pcc = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaPais
     */
    public TelaPais() {

        initComponents();
        conexao = ModulodeConexao.conexao();

    }

    private void inserir() {
        String sql = "insert into pais(nomePais,dataNascPais, rgPais, cpfPais, enderecoPais, loginPais, senhaPais) "
                + "values(?, ?, ?, ?, ?, ?, ?)";
        try {
            pcc = conexao.prepareStatement(sql);
            pcc.setString(1, txtNome.getText());
            pcc.setString(2, txtData.getText());
            pcc.setString(3, txtRg.getText());
            pcc.setString(4, txtCpf.getText());
            pcc.setString(5, txtEnd.getText());
            pcc.setString(6, txtLogin.getText());
            pcc.setString(7, txtSenha.getText());

            //INSERÇÃO 
            // || OPERADOR LOGICO OU 
            if (txtNome.getText().isEmpty() || txtData.getText().isEmpty()
                    || txtRg.getText().isEmpty() || txtCpf.getText().isEmpty()
                    || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, " preencha os campos obrigatorios ");
            } else {
                int adc = pcc.executeUpdate();
                if (adc > 0) {
                    JOptionPane.showMessageDialog(null, " cadastrado ");

                    // MOSTRA 1 - ADICIONADO COM SUCESSO 
                    System.out.println(adc);

                    txtNome.setText(null);
                    txtData.setText(null);
                    txtRg.setText(null);
                    txtCpf.setText(null);
                    txtEnd.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                }
            }
            //pcc.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void consulta_avancada() {
        
        // % NAO ENTRA NA SENTENÇA , EM SET STRING + CONCATENA
        String sql = "select nomePais as Nome, dataNascPais as Data, rgPais as Rg, "
                + "cpfPais as Cpf, enderecoPais as Endereço, loginPais as login, "
                + "senhaPais as senha from pais where nomePais like ?";
        try {
            pcc =  conexao.prepareStatement(sql);
            
            pcc.setString(1,txtPesquisa.getText() + "%");
            rs = pcc.executeQuery();
            
            tblPais.setModel(DbUtils.resultSetToTableModel(rs));
            
            

        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }

    }
    
    public void campos (){
        int setar = tblPais.getSelectedRow();
        txtNome.setText(tblPais.getModel().getValueAt(setar, 0).toString());
        txtData.setText(tblPais.getModel().getValueAt(setar, 1).toString());
        txtRg.setText(tblPais.getModel().getValueAt(setar, 2).toString());
        txtCpf.setText(tblPais.getModel().getValueAt(setar, 3).toString());
        if((tblPais.getModel().getValueAt(setar, 4)) != null){
            txtEnd.setText(tblPais.getModel().getValueAt(setar, 4).toString());
        }
        txtLogin.setText(tblPais.getModel().getValueAt(setar, 5).toString());
        txtSenha.setText(tblPais.getModel().getValueAt(setar, 6).toString());
        inserir.setEnabled(false);
        
       
    
    }

     private void editar() {
        String sql = "update pais set nomePais=?, dataNascPais=?, rgPais=?, cpfPais=?, enderecoPais=?, loginPais=?, senhaPais=? where nomePais=?";

        try {
            pcc = conexao.prepareStatement(sql);
            pcc.setString(1, txtNome.getText());
            pcc.setString(2, txtData.getText());
            pcc.setString(3, txtRg.getText());
            pcc.setString(4, txtCpf.getText());
            pcc.setString(5, txtEnd.getText());
            pcc.setString(6, txtLogin.getText());
            pcc.setString(7, txtSenha.getText());
            pcc.setString(8, txtNome.getText());
            if (txtNome.getText().isEmpty() || txtData.getText().isEmpty()
                    || txtRg.getText().isEmpty() || txtCpf.getText().isEmpty()
                    || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, " preencha os campos obrigatorios ");
            } else {

                //ALTERAR ADICIONAR EXECUTE UPDATE 
                int adc = pcc.executeUpdate();
                if (adc > 0) {
                    JOptionPane.showMessageDialog(null, " dado alterado ");

                    // MOSTRA 1 - ADICIONADO COM SUCESSO 
                    System.out.println(adc);
                    txtNome.setText(null);
                    txtData.setText(null);
                    txtRg.setText(null);
                    txtCpf.setText(null);
                    txtEnd.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                    inserir.setEnabled(true);
                    
                    // txtPerfil.setSelectedItem(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
      private void excluir() {

        int confirma = JOptionPane.showConfirmDialog(null, " remover ?", "atenção", JOptionPane.YES_NO_CANCEL_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            String sql = "delete from pais where nomePais=?";

            try {
                pcc = conexao.prepareStatement(sql);
                pcc.setString(1, txtNome.getText());
                int excluido = pcc.executeUpdate();
                if (excluido > 0) {
                    JOptionPane.showMessageDialog(null, " excluido ");
                    txtNome.setText(null);
                    txtData.setText(null);
                    txtRg.setText(null);
                    txtCpf.setText(null);
                    txtEnd.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                    inserir.setEnabled(true);
                    // DECREMENTAR IDPAIS (AUTO)

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

        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        inserir = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        excluir = new javax.swing.JButton();
        txtPesquisa = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPais = new javax.swing.JTable();
        txtNome = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        txtRg = new javax.swing.JTextField();
        txtCpf = new javax.swing.JTextField();
        txtEnd = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        nome = new javax.swing.JLabel();
        data = new javax.swing.JLabel();
        rg = new javax.swing.JLabel();
        cpf = new javax.swing.JLabel();
        end = new javax.swing.JLabel();
        login = new javax.swing.JLabel();
        senha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(470, 568));

        inserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/lardeApoio/icones/new.png"))); // NOI18N
        inserir.setToolTipText("Novo");
        inserir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirActionPerformed(evt);
            }
        });

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/lardeApoio/icones/edit.png"))); // NOI18N
        editar.setToolTipText("Editar");
        editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/lardeApoio/icones/delete.png"))); // NOI18N
        excluir.setToolTipText("Excluir");
        excluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirActionPerformed(evt);
            }
        });

        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        tblPais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Endereço", "Email", "Telefone"
            }
        ));
        tblPais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPaisMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPais);

        txtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfActionPerformed(evt);
            }
        });

        nome.setText("Nome");

        data.setText("Data de Nasc.");

        rg.setText("RG");

        cpf.setText("CPF");

        end.setText("Endereço");

        login.setText("Login");

        senha.setText("Senha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rg)
                            .addComponent(data)
                            .addComponent(nome)
                            .addComponent(cpf)
                            .addComponent(end)
                            .addComponent(login)
                            .addComponent(senha))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(inserir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtLogin, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEnd, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNome)
                                .addComponent(txtData)
                                .addComponent(txtCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                .addComponent(txtRg)))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(data))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cpf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(end))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(login))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(senha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inserir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirActionPerformed
        // TODO add your handling code here:
        inserir();
    }//GEN-LAST:event_inserirActionPerformed

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        // TODO add your handling code here:
        //EVENTO DE DIGITAÇÃO 
        consulta_avancada();
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void tblPaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPaisMouseClicked
        // TODO add your handling code here:
        campos();
        
    }//GEN-LAST:event_tblPaisMouseClicked

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_editarActionPerformed

    private void excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirActionPerformed
        // TODO add your handling code here:
        excluir();
    }//GEN-LAST:event_excluirActionPerformed

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cpf;
    private javax.swing.JLabel data;
    private javax.swing.JButton editar;
    private javax.swing.JLabel end;
    private javax.swing.JButton excluir;
    private javax.swing.JButton inserir;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel login;
    private javax.swing.JLabel nome;
    private javax.swing.JLabel rg;
    private javax.swing.JLabel senha;
    private javax.swing.JTable tblPais;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtEnd;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtRg;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
