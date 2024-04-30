package br.org.acal.application.legacy.ui.old.screen.login;

import br.org.acal.application.legacy.ui.old.screen.principal.TelaPrincipal;
import br.org.acal.domain.datasource.UserDataSource;
import br.org.acal.domain.entity.User;
import br.org.acal.resources.datasource.UserDataSourceImpl;
import org.springframework.stereotype.Component;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.EventQueue;

@Component
public class TelaLogin extends JFrame {
    private final UserDataSource userDataSource;
    private final TelaPrincipal telaPrincipal;
    private JPasswordField jPasswordFieldTelaPrincipalSenha;
    private JTextField jTextFieldTelaLoginNome;

    public TelaLogin(

            UserDataSourceImpl userRepository,
            TelaPrincipal telaPrincipal
    ) {
        this.userDataSource = userRepository;
        this.telaPrincipal = telaPrincipal;
    }
    
    private void start(){
        jTextFieldTelaLoginNome.setText("edvaldo@acal.com");
        jPasswordFieldTelaPrincipalSenha.setText("123");

        User userByName = userDataSource.findUserByName(jTextFieldTelaLoginNome.getText());

        if(userByName == null){

            telaPrincipal.setVisible(true);
            this.dispose();

        } else {
            jTextFieldTelaLoginNome.setText(null);
            jPasswordFieldTelaPrincipalSenha.setText(null);
        }
    }
    
    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            EventQueue.invokeLater(() -> {
                initComponents();
                setLocationRelativeTo(null);
                super.setVisible(true);
            });
        } else {
            super.setVisible(false);
        }
    }


    private void initComponents() {

        login_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldTelaLoginNome = new javax.swing.JTextField();
        jButtonTelaLoginLogar = new javax.swing.JButton();
        jPasswordFieldTelaPrincipalSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        login_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Seja bem vindo"));

        jLabel1.setText("Nome");

        jLabel2.setText("Senha");

        jTextFieldTelaLoginNome.setText("edvaldo@acal.com");
        jTextFieldTelaLoginNome.addActionListener(this::jTextFieldTelaLoginNomeActionPerformed);

        jButtonTelaLoginLogar.setText("Logar");
        jButtonTelaLoginLogar.addActionListener(this::jButtonTelaLoginLogarActionPerformed);

        jPasswordFieldTelaPrincipalSenha.setText("123");
        jPasswordFieldTelaPrincipalSenha.addActionListener(this::jPasswordFieldTelaPrincipalSenhaActionPerformed);

        javax.swing.GroupLayout login_panelLayout = new javax.swing.GroupLayout(login_panel);
        login_panel.setLayout(login_panelLayout);
        login_panelLayout.setHorizontalGroup(
            login_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(login_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonTelaLoginLogar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldTelaLoginNome, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jPasswordFieldTelaPrincipalSenha))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        login_panelLayout.setVerticalGroup(
            login_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTelaLoginNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPasswordFieldTelaPrincipalSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButtonTelaLoginLogar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jButtonTelaLoginLogarActionPerformed(java.awt.event.ActionEvent evt) {
        start();
    }

    private void jPasswordFieldTelaPrincipalSenhaActionPerformed(java.awt.event.ActionEvent evt) {
        start();
    }

    private void jTextFieldTelaLoginNomeActionPerformed(java.awt.event.ActionEvent evt) {
        start();
    }
    
    private javax.swing.JButton jButtonTelaLoginLogar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;

    private javax.swing.JPanel login_panel;
}
