/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.acal.application.legacy.ui.telas.GeradorUsuario;

import br.org.acal.resources.datasource.UserDataSourceImpl;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alexandre
 */
public class CriadorUsuario extends javax.swing.JDialog {

    @Autowired
    private UserDataSourceImpl userDataSourceImpl;

    public CriadorUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        travarComponentes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jCheckBoxEntradasConsultar = new javax.swing.JCheckBox();
        jCheckBoxEntradasSalvar = new javax.swing.JCheckBox();
        jCheckBoxEntradasAlterar = new javax.swing.JCheckBox();
        jCheckBoxEntradasApagar = new javax.swing.JCheckBox();
        jCheckBoxEntradasTodos = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButtonMarcarTodas = new javax.swing.JButton();
        jButtonDesmarcarTodos = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jCheckBoxSaidasConsultar = new javax.swing.JCheckBox();
        jCheckBoxSaidasSalvar = new javax.swing.JCheckBox();
        jCheckBoxSaidasAlterar = new javax.swing.JCheckBox();
        jCheckBoxSaidasApagar = new javax.swing.JCheckBox();
        jCheckBoxSaidasTodos = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jCheckBoxChequesConsultar = new javax.swing.JCheckBox();
        jCheckBoxChequesSalvar = new javax.swing.JCheckBox();
        jCheckBoxChequesAlterar = new javax.swing.JCheckBox();
        jCheckBoxChequesApagar = new javax.swing.JCheckBox();
        jCheckBoxChequesTodos = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jCheckBoxContasConsultar = new javax.swing.JCheckBox();
        jCheckBoxContasSalvar = new javax.swing.JCheckBox();
        jCheckBoxContasAlterar = new javax.swing.JCheckBox();
        jCheckBoxContasApagar = new javax.swing.JCheckBox();
        jCheckBoxContasTodos = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jCheckBoxSociosConsultar = new javax.swing.JCheckBox();
        jCheckBoxSociosSalvar = new javax.swing.JCheckBox();
        jCheckBoxSociosAlterar = new javax.swing.JCheckBox();
        jCheckBoxSociosApagar = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jCheckBoxSociosTodos = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        jCheckBoxTaxasConsultar = new javax.swing.JCheckBox();
        jCheckBoxTaxasSalvar = new javax.swing.JCheckBox();
        jCheckBoxTaxasAlterar = new javax.swing.JCheckBox();
        jCheckBoxTaxasApagar = new javax.swing.JCheckBox();
        jCheckBoxTaxasTodos = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jCheckBoxEntradasLogConsultar = new javax.swing.JCheckBox();
        jCheckBoxEntradasLogSalvar = new javax.swing.JCheckBox();
        jCheckBoxEntradasLogAlterar = new javax.swing.JCheckBox();
        jCheckBoxEntradasLogApagar = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jCheckBoxSaidasLogConsultar = new javax.swing.JCheckBox();
        jCheckBoxSaidasLogSalvar = new javax.swing.JCheckBox();
        jCheckBoxSaidasLogAlterar = new javax.swing.JCheckBox();
        jCheckBoxSaidasLogApagar = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jCheckBoxChequesLogConsultar = new javax.swing.JCheckBox();
        jCheckBoxChequesLogSalvar = new javax.swing.JCheckBox();
        jCheckBoxChequesLogAlterar = new javax.swing.JCheckBox();
        jCheckBoxChequesLogApagar = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jCheckBoxContasLogConsultar = new javax.swing.JCheckBox();
        jCheckBoxContasLogSalvar = new javax.swing.JCheckBox();
        jCheckBoxContasLogAlterar = new javax.swing.JCheckBox();
        jCheckBoxContasLogApagar = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBoxEntradasConsultar.setText("Consultar");

        jCheckBoxEntradasSalvar.setText("Salvar");

        jCheckBoxEntradasAlterar.setText("Alterar");

        jCheckBoxEntradasApagar.setText("Apagar");

        jCheckBoxEntradasTodos.setText("Todos");
        jCheckBoxEntradasTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEntradasTodosActionPerformed(evt);
            }
        });

        jLabel1.setText("Entradas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBoxEntradasConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxEntradasSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxEntradasAlterar))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxEntradasTodos)
                    .addComponent(jCheckBoxEntradasApagar))
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCheckBoxEntradasTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEntradasConsultar)
                    .addComponent(jCheckBoxEntradasSalvar)
                    .addComponent(jCheckBoxEntradasAlterar)
                    .addComponent(jCheckBoxEntradasApagar))
                .addGap(5, 5, 5))
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Usuario");

        jLabel3.setText("Senha");

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(65, 65, 65))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonMarcarTodas.setText("Marcar Todos");
        jButtonMarcarTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMarcarTodasActionPerformed(evt);
            }
        });

        jButtonDesmarcarTodos.setText("Desmarcar Todos");
        jButtonDesmarcarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDesmarcarTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jButtonMarcarTodas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDesmarcarTodos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDesmarcarTodos)
                    .addComponent(jButtonMarcarTodas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBoxSaidasConsultar.setText("Consultar");

        jCheckBoxSaidasSalvar.setText("Salvar");

        jCheckBoxSaidasAlterar.setText("Alterar");

        jCheckBoxSaidasApagar.setText("Apagar");

        jCheckBoxSaidasTodos.setText("Todos");
        jCheckBoxSaidasTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSaidasTodosActionPerformed(evt);
            }
        });

        jLabel4.setText("Saidas");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jCheckBoxSaidasConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxSaidasSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxSaidasAlterar))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxSaidasTodos)
                    .addComponent(jCheckBoxSaidasApagar))
                .addGap(5, 5, 5))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jCheckBoxSaidasTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxSaidasConsultar)
                    .addComponent(jCheckBoxSaidasSalvar)
                    .addComponent(jCheckBoxSaidasAlterar)
                    .addComponent(jCheckBoxSaidasApagar))
                .addGap(5, 5, 5))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBoxChequesConsultar.setText("Consultar");

        jCheckBoxChequesSalvar.setText("Salvar");

        jCheckBoxChequesAlterar.setText("Alterar");

        jCheckBoxChequesApagar.setText("Apagar");

        jCheckBoxChequesTodos.setText("Todos");
        jCheckBoxChequesTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxChequesTodosActionPerformed(evt);
            }
        });

        jLabel5.setText("Cheques");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBoxChequesConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxChequesSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxChequesAlterar))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxChequesTodos)
                    .addComponent(jCheckBoxChequesApagar))
                .addGap(5, 5, 5))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jCheckBoxChequesTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxChequesConsultar)
                    .addComponent(jCheckBoxChequesSalvar)
                    .addComponent(jCheckBoxChequesAlterar)
                    .addComponent(jCheckBoxChequesApagar))
                .addGap(5, 5, 5))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBoxContasConsultar.setText("Consultar");

        jCheckBoxContasSalvar.setText("Salvar");

        jCheckBoxContasAlterar.setText("Alterar");

        jCheckBoxContasApagar.setText("Apagar");

        jCheckBoxContasTodos.setText("Todos");
        jCheckBoxContasTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxContasTodosActionPerformed(evt);
            }
        });

        jLabel6.setText("Contas");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jCheckBoxContasConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxContasSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxContasAlterar))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxContasTodos)
                    .addComponent(jCheckBoxContasApagar))
                .addGap(5, 5, 5))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jCheckBoxContasTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxContasConsultar)
                    .addComponent(jCheckBoxContasSalvar)
                    .addComponent(jCheckBoxContasAlterar)
                    .addComponent(jCheckBoxContasApagar))
                .addGap(5, 5, 5))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBoxSociosConsultar.setText("Consultar");

        jCheckBoxSociosSalvar.setText("Salvar");

        jCheckBoxSociosAlterar.setText("Alterar");

        jCheckBoxSociosApagar.setText("Apagar");

        jLabel7.setText("Socios");

        jCheckBoxSociosTodos.setText("Todos");
        jCheckBoxSociosTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSociosTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jCheckBoxSociosConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxSociosSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxSociosAlterar))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxSociosApagar)
                    .addComponent(jCheckBoxSociosTodos))
                .addGap(5, 5, 5))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jCheckBoxSociosTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxSociosConsultar)
                    .addComponent(jCheckBoxSociosSalvar)
                    .addComponent(jCheckBoxSociosAlterar)
                    .addComponent(jCheckBoxSociosApagar))
                .addGap(5, 5, 5))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBoxTaxasConsultar.setText("Consultar");

        jCheckBoxTaxasSalvar.setText("Salvar");

        jCheckBoxTaxasAlterar.setText("Alterar");

        jCheckBoxTaxasApagar.setText("Apagar");

        jCheckBoxTaxasTodos.setText("Todos");
        jCheckBoxTaxasTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTaxasTodosActionPerformed(evt);
            }
        });

        jLabel8.setText("Taxas");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jCheckBoxTaxasConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxTaxasSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxTaxasAlterar))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxTaxasTodos)
                    .addComponent(jCheckBoxTaxasApagar))
                .addGap(5, 5, 5))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jCheckBoxTaxasTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxTaxasConsultar)
                    .addComponent(jCheckBoxTaxasSalvar)
                    .addComponent(jCheckBoxTaxasAlterar)
                    .addComponent(jCheckBoxTaxasApagar))
                .addGap(5, 5, 5))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBoxEntradasLogConsultar.setText("Consultar");

        jCheckBoxEntradasLogSalvar.setText("Salvar");

        jCheckBoxEntradasLogAlterar.setText("Alterar");

        jCheckBoxEntradasLogApagar.setText("Apagar");

        jLabel9.setText("Entradas Log");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jCheckBoxEntradasLogConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxEntradasLogSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxEntradasLogAlterar))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxEntradasLogApagar)
                .addGap(5, 5, 5))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEntradasLogConsultar)
                    .addComponent(jCheckBoxEntradasLogSalvar)
                    .addComponent(jCheckBoxEntradasLogAlterar)
                    .addComponent(jCheckBoxEntradasLogApagar))
                .addGap(5, 5, 5))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBoxSaidasLogConsultar.setText("Consultar");

        jCheckBoxSaidasLogSalvar.setText("Salvar");

        jCheckBoxSaidasLogAlterar.setText("Alterar");

        jCheckBoxSaidasLogApagar.setText("Apagar");

        jLabel10.setText("Saidas Log");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jCheckBoxSaidasLogConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxSaidasLogSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxSaidasLogAlterar))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxSaidasLogApagar)
                .addGap(5, 5, 5))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxSaidasLogConsultar)
                    .addComponent(jCheckBoxSaidasLogSalvar)
                    .addComponent(jCheckBoxSaidasLogAlterar)
                    .addComponent(jCheckBoxSaidasLogApagar))
                .addGap(5, 5, 5))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBoxChequesLogConsultar.setText("Consultar");

        jCheckBoxChequesLogSalvar.setText("Salvar");

        jCheckBoxChequesLogAlterar.setText("Alterar");

        jCheckBoxChequesLogApagar.setText("Apagar");

        jLabel11.setText("Cheques Log");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jCheckBoxChequesLogConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxChequesLogSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxChequesLogAlterar))
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxChequesLogApagar)
                .addGap(5, 5, 5))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxChequesLogConsultar)
                    .addComponent(jCheckBoxChequesLogSalvar)
                    .addComponent(jCheckBoxChequesLogAlterar)
                    .addComponent(jCheckBoxChequesLogApagar))
                .addGap(5, 5, 5))
        );

        jButton3.setText("Salvar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBoxContasLogConsultar.setText("Consultar");

        jCheckBoxContasLogSalvar.setText("Salvar");

        jCheckBoxContasLogAlterar.setText("Alterar");

        jCheckBoxContasLogApagar.setText("Apagar");

        jLabel12.setText("Contas Log");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jCheckBoxContasLogConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxContasLogSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxContasLogAlterar))
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxContasLogApagar)
                .addGap(5, 5, 5))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxContasLogConsultar)
                    .addComponent(jCheckBoxContasLogSalvar)
                    .addComponent(jCheckBoxContasLogAlterar)
                    .addComponent(jCheckBoxContasLogApagar))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxEntradasTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEntradasTodosActionPerformed
        if (jCheckBoxEntradasTodos.isSelected()) {
            jCheckBoxEntradasConsultar.setSelected(true);
            jCheckBoxEntradasSalvar.setSelected(true);
            jCheckBoxEntradasAlterar.setSelected(true);
            jCheckBoxEntradasApagar.setSelected(true);
        } else {
            jCheckBoxEntradasConsultar.setSelected(false);
            jCheckBoxEntradasSalvar.setSelected(false);
            jCheckBoxEntradasAlterar.setSelected(false);
            jCheckBoxEntradasApagar.setSelected(false);
        }

    }//GEN-LAST:event_jCheckBoxEntradasTodosActionPerformed

    private void jCheckBoxSaidasTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSaidasTodosActionPerformed

        if (jCheckBoxSaidasTodos.isSelected()) {
            jCheckBoxSaidasConsultar.setSelected(true);
            jCheckBoxSaidasSalvar.setSelected(true);
            jCheckBoxSaidasAlterar.setSelected(true);
            jCheckBoxSaidasApagar.setSelected(true);
        } else {
            jCheckBoxSaidasConsultar.setSelected(false);
            jCheckBoxSaidasSalvar.setSelected(false);
            jCheckBoxSaidasAlterar.setSelected(false);
            jCheckBoxSaidasApagar.setSelected(false);
        }


    }//GEN-LAST:event_jCheckBoxSaidasTodosActionPerformed

    private void jCheckBoxContasTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxContasTodosActionPerformed
        if (jCheckBoxContasTodos.isSelected()) {
            jCheckBoxContasConsultar.setSelected(true);
            jCheckBoxContasSalvar.setSelected(true);
            jCheckBoxContasAlterar.setSelected(true);
            jCheckBoxContasApagar.setSelected(true);
        } else {
            jCheckBoxContasConsultar.setSelected(false);
            jCheckBoxContasSalvar.setSelected(false);
            jCheckBoxContasAlterar.setSelected(false);
            jCheckBoxContasApagar.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxContasTodosActionPerformed

    private void jCheckBoxTaxasTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTaxasTodosActionPerformed
        if (jCheckBoxTaxasTodos.isSelected()) {
            jCheckBoxTaxasConsultar.setSelected(true);
            jCheckBoxTaxasSalvar.setSelected(true);
            jCheckBoxTaxasAlterar.setSelected(true);
            jCheckBoxTaxasApagar.setSelected(true);
        } else {
            jCheckBoxTaxasConsultar.setSelected(false);
            jCheckBoxTaxasSalvar.setSelected(false);
            jCheckBoxTaxasAlterar.setSelected(false);
            jCheckBoxTaxasApagar.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxTaxasTodosActionPerformed

    private void jButtonMarcarTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMarcarTodasActionPerformed

        jCheckBoxEntradasTodos.setSelected(true);
        jCheckBoxEntradasConsultar.setSelected(true);
        jCheckBoxEntradasSalvar.setSelected(true);
        jCheckBoxEntradasAlterar.setSelected(true);
        jCheckBoxEntradasApagar.setSelected(true);

        jCheckBoxSaidasTodos.setSelected(true);
        jCheckBoxSaidasConsultar.setSelected(true);
        jCheckBoxSaidasSalvar.setSelected(true);
        jCheckBoxSaidasAlterar.setSelected(true);
        jCheckBoxSaidasApagar.setSelected(true);

        jCheckBoxChequesTodos.setSelected(true);
        jCheckBoxChequesConsultar.setSelected(true);
        jCheckBoxChequesSalvar.setSelected(true);
        jCheckBoxChequesAlterar.setSelected(true);
        jCheckBoxChequesApagar.setSelected(true);

        jCheckBoxContasTodos.setSelected(true);
        jCheckBoxContasConsultar.setSelected(true);
        jCheckBoxContasSalvar.setSelected(true);
        jCheckBoxContasAlterar.setSelected(true);
        jCheckBoxContasApagar.setSelected(true);

        jCheckBoxTaxasTodos.setSelected(true);
        jCheckBoxTaxasConsultar.setSelected(true);
        jCheckBoxTaxasSalvar.setSelected(true);
        jCheckBoxTaxasAlterar.setSelected(true);
        jCheckBoxTaxasApagar.setSelected(true);

        jCheckBoxSociosTodos.setSelected(true);
        jCheckBoxSociosConsultar.setSelected(true);
        jCheckBoxSociosSalvar.setSelected(true);
        jCheckBoxSociosAlterar.setSelected(true);
        jCheckBoxSociosApagar.setSelected(true);
        jCheckBoxEntradasLogConsultar.setSelected(true);
        jCheckBoxSaidasLogConsultar.setSelected(true);
        jCheckBoxChequesLogConsultar.setSelected(true);
        jCheckBoxContasLogConsultar.setSelected(true);

    }//GEN-LAST:event_jButtonMarcarTodasActionPerformed

    private void jCheckBoxChequesTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxChequesTodosActionPerformed
        if (jCheckBoxChequesTodos.isSelected()) {
            jCheckBoxChequesConsultar.setSelected(true);
            jCheckBoxChequesSalvar.setSelected(true);
            jCheckBoxChequesAlterar.setSelected(true);
            jCheckBoxChequesApagar.setSelected(true);
        } else {
            jCheckBoxChequesConsultar.setSelected(false);
            jCheckBoxChequesSalvar.setSelected(false);
            jCheckBoxChequesAlterar.setSelected(false);
            jCheckBoxChequesApagar.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxChequesTodosActionPerformed

    private void jCheckBoxSociosTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSociosTodosActionPerformed
        if (jCheckBoxSociosTodos.isSelected()) {
            jCheckBoxSociosConsultar.setSelected(true);
            jCheckBoxSociosSalvar.setSelected(true);
            jCheckBoxSociosAlterar.setSelected(true);
            jCheckBoxSociosApagar.setSelected(true);
        } else {
            jCheckBoxSociosConsultar.setSelected(false);
            jCheckBoxSociosSalvar.setSelected(false);
            jCheckBoxSociosAlterar.setSelected(false);
            jCheckBoxSociosApagar.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxSociosTodosActionPerformed

    private void jButtonDesmarcarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDesmarcarTodosActionPerformed
        jCheckBoxEntradasTodos.setSelected(false);
        jCheckBoxEntradasConsultar.setSelected(false);
        jCheckBoxEntradasSalvar.setSelected(false);
        jCheckBoxEntradasAlterar.setSelected(false);
        jCheckBoxEntradasApagar.setSelected(false);

        jCheckBoxSaidasTodos.setSelected(false);
        jCheckBoxSaidasConsultar.setSelected(false);
        jCheckBoxSaidasSalvar.setSelected(false);
        jCheckBoxSaidasAlterar.setSelected(false);
        jCheckBoxSaidasApagar.setSelected(false);

        jCheckBoxChequesTodos.setSelected(false);
        jCheckBoxChequesConsultar.setSelected(false);
        jCheckBoxChequesSalvar.setSelected(false);
        jCheckBoxChequesAlterar.setSelected(false);
        jCheckBoxChequesApagar.setSelected(false);

        jCheckBoxContasConsultar.setSelected(false);
        jCheckBoxContasTodos.setSelected(false);
        jCheckBoxContasSalvar.setSelected(false);
        jCheckBoxContasAlterar.setSelected(false);
        jCheckBoxContasApagar.setSelected(false);

        jCheckBoxTaxasTodos.setSelected(false);
        jCheckBoxTaxasConsultar.setSelected(false);
        jCheckBoxTaxasSalvar.setSelected(false);
        jCheckBoxTaxasAlterar.setSelected(false);
        jCheckBoxTaxasApagar.setSelected(false);

        jCheckBoxSociosTodos.setSelected(false);
        jCheckBoxSociosConsultar.setSelected(false);
        jCheckBoxSociosSalvar.setSelected(false);
        jCheckBoxSociosAlterar.setSelected(false);
        jCheckBoxSociosApagar.setSelected(false);
        //log's
        jCheckBoxEntradasLogConsultar.setSelected(false);
        jCheckBoxSaidasLogConsultar.setSelected(false);
        jCheckBoxChequesLogConsultar.setSelected(false);
        jCheckBoxContasLogConsultar.setSelected(false);

    }//GEN-LAST:event_jButtonDesmarcarTodosActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //usuario e senha
        String usuario = null;
        String senha = "";
        String privilegios1 = "";
        String privilegios2 = "";
        String privilegios3 = "";
        String privilegios4 = "";
        String privilegios5 = "";
        String privilegios6 = "";
        char[] input;
        int cont = 0;

        if (jTextFieldUsuario.getText().equals("") || (jPasswordFieldSenha.getPassword().length == 0)) {
            JOptionPane.showMessageDialog(null, "Os campos Usuario e senha São Obrigatorios");
        } else if ((jPasswordFieldSenha.getPassword().length < 4) || (jPasswordFieldSenha.getPassword().length > 12)) {
            JOptionPane.showMessageDialog(null, "Os campo de senha precisa ter 4 ou mais digitos e 12 ou menos");
        } else {
            input = jPasswordFieldSenha.getPassword();

            for (int i = 0; i < input.length; i++) {
                senha += input[i];
            }

            usuario
                    = "CREATE USER '" + jTextFieldUsuario.getText().trim()
                    + "' IDENTIFIED BY '" + senha + "'";

            if (!entradas(jTextFieldUsuario.getText().trim()).equals("0")) {
                privilegios1 += entradas(jTextFieldUsuario.getText().trim());
                cont++;
            }
            if (!saidas(jTextFieldUsuario.getText().trim()).equals("0")) {
                privilegios2 += saidas(jTextFieldUsuario.getText().trim());
                cont++;
            }
            if (!cheques(jTextFieldUsuario.getText().trim()).equals("0")) {
                privilegios3 += cheques(jTextFieldUsuario.getText().trim());
                cont++;
            }
            if (!contas(jTextFieldUsuario.getText().trim()).equals("0")) {
                privilegios4 += contas(jTextFieldUsuario.getText().trim());
                cont++;
            }
            if (!taxas(jTextFieldUsuario.getText().trim()).equals("0")) {
                privilegios5 += taxas(jTextFieldUsuario.getText().trim());
                cont++;
            }
            if (!socios(jTextFieldUsuario.getText().trim()).equals("0")) {
                privilegios6 += socios(jTextFieldUsuario.getText().trim());
                cont++;
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        PesquisarUsuario pu = new PesquisarUsuario(null, true);
        pu.setLocationRelativeTo(null);
        pu.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed
    private void travarComponentes() {
        jCheckBoxEntradasLogSalvar.setEnabled(false);
        jCheckBoxEntradasLogAlterar.setEnabled(false);
        jCheckBoxEntradasLogApagar.setEnabled(false);

        jCheckBoxSaidasLogSalvar.setEnabled(false);
        jCheckBoxSaidasLogAlterar.setEnabled(false);
        jCheckBoxSaidasLogApagar.setEnabled(false);

        jCheckBoxChequesLogSalvar.setEnabled(false);
        jCheckBoxChequesLogAlterar.setEnabled(false);
        jCheckBoxChequesLogApagar.setEnabled(false);

        jCheckBoxContasLogSalvar.setEnabled(false);
        jCheckBoxContasLogAlterar.setEnabled(false);
        jCheckBoxContasLogApagar.setEnabled(false);
    }

    public String entradas(String usuario) {
        String instrucao = "";
        String privilegios = "";
        int cont = 0;// insere a senha se for preciso
        int quant = 0;

        //quantos campos foram marcados?
        if (jCheckBoxEntradasConsultar.isSelected()) {
            quant++;
        }
        if (jCheckBoxEntradasSalvar.isSelected()) {
            quant++;
        }
        if (jCheckBoxEntradasAlterar.isSelected()) {
            quant++;
        }
        if (jCheckBoxEntradasApagar.isSelected()) {
            quant++;
        }

        if (jCheckBoxEntradasConsultar.isSelected()) {

            privilegios += "SELECT";
            cont++;

            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }

        }
        if (jCheckBoxEntradasSalvar.isSelected()) {
            privilegios += "INSERT";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        if (jCheckBoxEntradasAlterar.isSelected()) {
            privilegios += "UPDATE";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        if (jCheckBoxEntradasApagar.isSelected()) {
            privilegios += "DELETE";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        instrucao = "GRANT " + privilegios + " ON acal.entrada TO " + usuario;

        if (quant == 0) {
            instrucao = "0";
        }
        return instrucao;
    }

    public String saidas(String usuario) {
        String instrucao = "";
        String privilegios = "";
        int cont = 0;// insere a senha se for preciso
        int quant = 0;

        //quantos campos foram marcados?
        if (jCheckBoxSaidasConsultar.isSelected()) {
            quant++;
        }
        if (jCheckBoxSaidasSalvar.isSelected()) {
            quant++;
        }
        if (jCheckBoxSaidasAlterar.isSelected()) {
            quant++;
        }
        if (jCheckBoxSaidasApagar.isSelected()) {
            quant++;
        }

        if (jCheckBoxSaidasConsultar.isSelected()) {

            privilegios += "SELECT";
            cont++;

            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }

        }
        if (jCheckBoxSaidasSalvar.isSelected()) {
            privilegios += "INSERT";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        if (jCheckBoxSaidasAlterar.isSelected()) {
            privilegios += "UPDATE";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        if (jCheckBoxSaidasApagar.isSelected()) {
            privilegios += "DELETE";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        instrucao = "GRANT " + privilegios + " ON acal.saida to " + usuario;

        if (quant == 0) {
            instrucao = "0";
        }
        return instrucao;
    }

    public String cheques(String usuario) {
        String instrucao = "";
        String privilegios = "";
        int cont = 0;// insere a senha se for preciso
        int quant = 0;

        //quantos campos foram marcados?
        if (jCheckBoxSaidasConsultar.isSelected()) {
            quant++;
        }
        if (jCheckBoxSaidasSalvar.isSelected()) {
            quant++;
        }
        if (jCheckBoxSaidasAlterar.isSelected()) {
            quant++;
        }
        if (jCheckBoxSaidasApagar.isSelected()) {
            quant++;
        }

        if (jCheckBoxSaidasConsultar.isSelected()) {

            privilegios += "SELECT";
            cont++;

            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }

        }
        if (jCheckBoxSaidasSalvar.isSelected()) {
            privilegios += "INSERT";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        if (jCheckBoxSaidasAlterar.isSelected()) {
            privilegios += "UPDATE";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        if (jCheckBoxSaidasApagar.isSelected()) {
            privilegios += "DELETE";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        instrucao = "GRANT " + privilegios + " ON acal.cheque TO " + usuario;

        if (quant == 0) {
            instrucao = "0";
        }
        return instrucao;
    }

    public String contas(String usuario) {
        String instrucao = "";
        String privilegios = "";
        int cont = 0;// insere a senha se for preciso
        int quant = 0;

        //quantos campos foram marcados?
        if (jCheckBoxContasConsultar.isSelected()) {
            quant++;
        }
        if (jCheckBoxContasSalvar.isSelected()) {
            quant++;
        }
        if (jCheckBoxContasAlterar.isSelected()) {
            quant++;
        }
        if (jCheckBoxContasApagar.isSelected()) {
            quant++;
        }

        if (jCheckBoxContasConsultar.isSelected()) {

            privilegios += "SELECT";
            cont++;

            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }

        }
        if (jCheckBoxContasSalvar.isSelected()) {
            privilegios += "INSERT";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        if (jCheckBoxContasAlterar.isSelected()) {
            privilegios += "UPDATE";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        if (jCheckBoxContasApagar.isSelected()) {
            privilegios += "DELETE";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        instrucao = "GRANT " + privilegios + " ON acal.conta TO " + usuario;

        if (quant == 0) {
            instrucao = "0";
        }
        return instrucao;
    }

    public String taxas(String usuario) {
        String instrucao = "";
        String privilegios = "";
        int cont = 0;// insere a senha se for preciso
        int quant = 0;

        //quantos campos foram marcados?
        if (jCheckBoxTaxasConsultar.isSelected()) {
            quant++;
        }
        if (jCheckBoxTaxasSalvar.isSelected()) {
            quant++;
        }
        if (jCheckBoxTaxasAlterar.isSelected()) {
            quant++;
        }
        if (jCheckBoxTaxasApagar.isSelected()) {
            quant++;
        }

        if (jCheckBoxTaxasConsultar.isSelected()) {

            privilegios += "SELECT";
            cont++;

            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }

        }
        if (jCheckBoxTaxasSalvar.isSelected()) {
            privilegios += "INSERT";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        if (jCheckBoxTaxasAlterar.isSelected()) {
            privilegios += "UPDATE";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        if (jCheckBoxTaxasApagar.isSelected()) {
            privilegios += "DELETE";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        instrucao = "GRANT " + privilegios + " ON acal.taxa TO " + usuario;

        if (quant == 0) {
            instrucao = "0";
        }
        return instrucao;
    }

    public String socios(String usuario) {
        String instrucao = "";
        String privilegios = "";
        int cont = 0;// insere a senha se for preciso
        int quant = 0;

        //quantos campos foram marcados?
        if (jCheckBoxSociosConsultar.isSelected()) {
            quant++;
        }
        if (jCheckBoxSociosSalvar.isSelected()) {
            quant++;
        }
        if (jCheckBoxSociosAlterar.isSelected()) {
            quant++;
        }
        if (jCheckBoxSociosApagar.isSelected()) {
            quant++;
        }

        if (jCheckBoxSociosConsultar.isSelected()) {

            privilegios += "SELECT";
            cont++;

            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }

        }
        if (jCheckBoxSociosSalvar.isSelected()) {
            privilegios += "INSERT";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        if (jCheckBoxSociosAlterar.isSelected()) {
            privilegios += "UPDATE";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        if (jCheckBoxSociosApagar.isSelected()) {
            privilegios += "DELETE";
            cont++;
            if ((cont > 0) && (cont < quant)) {
                privilegios += ",";
            }
        }
        instrucao = "GRANT " + privilegios + " ON acal.socio TO " + usuario;

        if (quant == 0) {
            instrucao = "0";
        }
        return instrucao;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CriadorUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CriadorUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CriadorUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CriadorUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CriadorUsuario dialog = new CriadorUsuario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonDesmarcarTodos;
    private javax.swing.JButton jButtonMarcarTodas;
    private javax.swing.JCheckBox jCheckBoxChequesAlterar;
    private javax.swing.JCheckBox jCheckBoxChequesApagar;
    private javax.swing.JCheckBox jCheckBoxChequesConsultar;
    private javax.swing.JCheckBox jCheckBoxChequesLogAlterar;
    private javax.swing.JCheckBox jCheckBoxChequesLogApagar;
    private javax.swing.JCheckBox jCheckBoxChequesLogConsultar;
    private javax.swing.JCheckBox jCheckBoxChequesLogSalvar;
    private javax.swing.JCheckBox jCheckBoxChequesSalvar;
    private javax.swing.JCheckBox jCheckBoxChequesTodos;
    private javax.swing.JCheckBox jCheckBoxContasAlterar;
    private javax.swing.JCheckBox jCheckBoxContasApagar;
    private javax.swing.JCheckBox jCheckBoxContasConsultar;
    private javax.swing.JCheckBox jCheckBoxContasLogAlterar;
    private javax.swing.JCheckBox jCheckBoxContasLogApagar;
    private javax.swing.JCheckBox jCheckBoxContasLogConsultar;
    private javax.swing.JCheckBox jCheckBoxContasLogSalvar;
    private javax.swing.JCheckBox jCheckBoxContasSalvar;
    private javax.swing.JCheckBox jCheckBoxContasTodos;
    private javax.swing.JCheckBox jCheckBoxEntradasAlterar;
    private javax.swing.JCheckBox jCheckBoxEntradasApagar;
    private javax.swing.JCheckBox jCheckBoxEntradasConsultar;
    private javax.swing.JCheckBox jCheckBoxEntradasLogAlterar;
    private javax.swing.JCheckBox jCheckBoxEntradasLogApagar;
    private javax.swing.JCheckBox jCheckBoxEntradasLogConsultar;
    private javax.swing.JCheckBox jCheckBoxEntradasLogSalvar;
    private javax.swing.JCheckBox jCheckBoxEntradasSalvar;
    private javax.swing.JCheckBox jCheckBoxEntradasTodos;
    private javax.swing.JCheckBox jCheckBoxSaidasAlterar;
    private javax.swing.JCheckBox jCheckBoxSaidasApagar;
    private javax.swing.JCheckBox jCheckBoxSaidasConsultar;
    private javax.swing.JCheckBox jCheckBoxSaidasLogAlterar;
    private javax.swing.JCheckBox jCheckBoxSaidasLogApagar;
    private javax.swing.JCheckBox jCheckBoxSaidasLogConsultar;
    private javax.swing.JCheckBox jCheckBoxSaidasLogSalvar;
    private javax.swing.JCheckBox jCheckBoxSaidasSalvar;
    private javax.swing.JCheckBox jCheckBoxSaidasTodos;
    private javax.swing.JCheckBox jCheckBoxSociosAlterar;
    private javax.swing.JCheckBox jCheckBoxSociosApagar;
    private javax.swing.JCheckBox jCheckBoxSociosConsultar;
    private javax.swing.JCheckBox jCheckBoxSociosSalvar;
    private javax.swing.JCheckBox jCheckBoxSociosTodos;
    private javax.swing.JCheckBox jCheckBoxTaxasAlterar;
    private javax.swing.JCheckBox jCheckBoxTaxasApagar;
    private javax.swing.JCheckBox jCheckBoxTaxasConsultar;
    private javax.swing.JCheckBox jCheckBoxTaxasSalvar;
    private javax.swing.JCheckBox jCheckBoxTaxasTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}