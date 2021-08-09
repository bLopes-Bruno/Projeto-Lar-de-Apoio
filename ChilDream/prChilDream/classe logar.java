
    public void logar() {
        String sql = "select * from usuario where login=? and senha=?";
        try {
            pcc = conexao.prepareStatement(sql);
            pcc.setString(1, txtUsuario.getText());
            pcc.setString(2, txtSenha.getText());
            rs = pcc.executeQuery();
            //VALIDAÇÃO SENHA E USUARIO
            if (rs.next()) {
                //VALIDAÇÃO DO PERFIL   
                String perfil = rs.getString(6);
                System.out.println(perfil);

                if (perfil.equals("admin")) {

                    Inicio tela = new Inicio();
                    tela.setVisible(true);
                    
                    //HABILITA CAMPOS RESTRITOS PUBLIC STATIC 
                    Inicio.mRelatorio.setEnabled(true);
                    Inicio.mFuncionario.setEnabled(true);
                    
                    // RS PUXA DADOS DO SQL NO CAMPO 2
                    
                    
                   // Inicio.lblUsuario.setText(rs.getString(2));
                    //Inicio.lblUsuario.setForeground(Color.green);
                    this.dispose();
                    //A CHAVE 
                }else{
                 Inicio tela = new Inicio();
                  tela.setVisible(true);
                  //Inicio.lblUsuario.setText(rs.getString(2));
                  this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "usuario ou senha invalido");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }