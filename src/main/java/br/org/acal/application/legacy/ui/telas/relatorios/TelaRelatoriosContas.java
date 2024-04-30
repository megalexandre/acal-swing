package br.org.acal.application.legacy.ui.telas.relatorios;

import br.org.acal.resources.legacy.dao.DaoCategoriaSocio;
import br.org.acal.resources.legacy.dao.DaoEndereco;
import br.org.acal.resources.legacy.dao.DaoSocioView;
import br.org.acal.resources.legacy.entidades.CategoriaSocio;
import br.org.acal.resources.legacy.entidades.Endereco;
import br.org.acal.resources.legacy.entidades.SociosView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;

import org.springframework.beans.factory.annotation.Autowired;
import br.org.acal.application.legacy.ui.old.screen.principal.TelaPrincipal;

public final class TelaRelatoriosContas extends javax.swing.JFrame {

    @Autowired
    private DaoEndereco daoEndereco;
    
    @Autowired
    private DaoSocioView socioView;

    public TelaRelatoriosContas() {
        initComponents();
        travarComponentes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel15 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jCheckBoxId = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldIdMaior = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldIdMenor = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jCheckBoxStatus = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jComboBoxStatus = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jCheckBoxData = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jFormattedTextFieldDataMenor = new com.toedter.calendar.JDateChooser();
        jFormattedTextFieldDataMaior = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jCheckBoxCategoria = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        jComboBoxCategoria = new javax.swing.JComboBox();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jCheckBoxSocio = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        jComboBoxSocio = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jCheckRegristro = new javax.swing.JCheckBox();
        jCheckData = new javax.swing.JCheckBox();
        jCheckCategoria = new javax.swing.JCheckBox();
        jCheckSocio = new javax.swing.JCheckBox();
        jCheckNumero = new javax.swing.JCheckBox();
        jPanel17 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jCheckBoxLogradouro = new javax.swing.JCheckBox();
        jPanel20 = new javax.swing.JPanel();
        jComboBoxLogradouro = new javax.swing.JComboBox();
        jPanel21 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jCheckBoxDataVecimento = new javax.swing.JCheckBox();
        jPanel23 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jDateVencimentoMenor = new com.toedter.calendar.JDateChooser();
        jDateVecimentoMaior = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Conta");

        jCheckBoxId.setText("Filtrar por Regristro");
        jCheckBoxId.setPreferredSize(new java.awt.Dimension(100, 23));
        jCheckBoxId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxIdActionPerformed(evt);
            }
        });

        jTextFieldIdMaior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdMaiorActionPerformed(evt);
            }
        });

        jLabel7.setText("E");

        jLabel8.setText("Entre");

        jTextFieldIdMenor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdMenorActionPerformed(evt);
            }
        });
        jTextFieldIdMenor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIdMenorFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(97, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(11, 11, 11)
                .addComponent(jTextFieldIdMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIdMaior, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldIdMaior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldIdMenor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxId, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(54, 54, 54))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxId, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Status");

        jCheckBoxStatus.setText("Filtrar por Status");
        jCheckBoxStatus.setMaximumSize(new java.awt.Dimension(100, 23));
        jCheckBoxStatus.setMinimumSize(new java.awt.Dimension(100, 23));
        jCheckBoxStatus.setPreferredSize(new java.awt.Dimension(100, 23));
        jCheckBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jCheckBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jCheckBoxStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Aberta", "Fechada", "Todas" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(162, Short.MAX_VALUE)
                .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(54, 54, 54))
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Data de Geração");

        jCheckBoxData.setText("Filtrar por Geracão");
        jCheckBoxData.setMaximumSize(new java.awt.Dimension(100, 23));
        jCheckBoxData.setMinimumSize(new java.awt.Dimension(100, 23));
        jCheckBoxData.setPreferredSize(new java.awt.Dimension(100, 23));
        jCheckBoxData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jCheckBoxData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jCheckBoxData, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jLabel11.setText("E");

        jLabel12.setText("Entre");

        jFormattedTextFieldDataMenor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldDataMenorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldDataMenorFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldDataMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldDataMaior, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFormattedTextFieldDataMaior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldDataMenor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(54, 54, 54))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Categoria");

        jCheckBoxCategoria.setText("Filtrar por Categoria");
        jCheckBoxCategoria.setMaximumSize(new java.awt.Dimension(100, 23));
        jCheckBoxCategoria.setMinimumSize(new java.awt.Dimension(100, 23));
        jCheckBoxCategoria.setPreferredSize(new java.awt.Dimension(100, 23));
        jCheckBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jCheckBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jCheckBoxCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(54, 54, 54))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Socio");

        jCheckBoxSocio.setText("Filtrar por Socio");
        jCheckBoxSocio.setMaximumSize(new java.awt.Dimension(100, 23));
        jCheckBoxSocio.setMinimumSize(new java.awt.Dimension(100, 23));
        jCheckBoxSocio.setPreferredSize(new java.awt.Dimension(100, 23));
        jCheckBoxSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSocioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jCheckBoxSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jCheckBoxSocio, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jComboBoxSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(54, 54, 54))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setText("Gerar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel16.setPreferredSize(new java.awt.Dimension(486, 50));

        jLabel6.setText("Ordernar");

        jCheckRegristro.setText("Regristro");
        jCheckRegristro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckRegristroActionPerformed(evt);
            }
        });

        jCheckData.setText("Data");
        jCheckData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckDataActionPerformed(evt);
            }
        });

        jCheckCategoria.setText("Categoria");
        jCheckCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckCategoriaActionPerformed(evt);
            }
        });

        jCheckSocio.setText("Socio");
        jCheckSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckSocioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckRegristro, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jCheckData, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jCheckCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jCheckSocio, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addGap(103, 103, 103))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckRegristro)
                    .addComponent(jCheckData)
                    .addComponent(jCheckCategoria)
                    .addComponent(jCheckSocio))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jCheckNumero.setText("Numero");
        jCheckNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckNumeroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jCheckNumero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jCheckNumero))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Logradouro");

        jCheckBoxLogradouro.setText("Filtrar por Logradouro");
        jCheckBoxLogradouro.setMaximumSize(new java.awt.Dimension(100, 23));
        jCheckBoxLogradouro.setMinimumSize(new java.awt.Dimension(100, 23));
        jCheckBoxLogradouro.setPreferredSize(new java.awt.Dimension(100, 23));
        jCheckBoxLogradouro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxLogradouroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jCheckBoxLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jCheckBoxLogradouro, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jComboBoxLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(54, 54, 54))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Data de Vencimento");

        jCheckBoxDataVecimento.setText("Filtrar por Vencimento");
        jCheckBoxDataVecimento.setMaximumSize(new java.awt.Dimension(100, 23));
        jCheckBoxDataVecimento.setMinimumSize(new java.awt.Dimension(100, 23));
        jCheckBoxDataVecimento.setPreferredSize(new java.awt.Dimension(100, 23));
        jCheckBoxDataVecimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxDataVecimentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jCheckBoxDataVecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jCheckBoxDataVecimento, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jLabel13.setText("E");

        jLabel14.setText("Entre");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateVencimentoMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateVecimentoMaior, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jDateVecimentoMaior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateVencimentoMenor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxIdActionPerformed

        if (jCheckBoxId.isSelected()) {
            jTextFieldIdMaior.setEditable(true);
            jTextFieldIdMenor.setEditable(true);
        } else {
            jTextFieldIdMaior.setText("");
            jTextFieldIdMenor.setText("");
            jTextFieldIdMaior.setEditable(false);
            jTextFieldIdMenor.setEditable(false);
        }
    }//GEN-LAST:event_jCheckBoxIdActionPerformed

    private void jTextFieldIdMaiorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdMaiorActionPerformed

    }//GEN-LAST:event_jTextFieldIdMaiorActionPerformed

    private void jTextFieldIdMenorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdMenorActionPerformed

    }//GEN-LAST:event_jTextFieldIdMenorActionPerformed

    private void jCheckBoxDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxDataActionPerformed

        if (jCheckBoxData.isSelected()) {
            jFormattedTextFieldDataMaior.setEnabled(true);
            jFormattedTextFieldDataMenor.setEnabled(true);
        } else {
            jFormattedTextFieldDataMaior.setDate(new Date());
            jFormattedTextFieldDataMenor.setDate(null);
            jFormattedTextFieldDataMaior.setEnabled(false);
            jFormattedTextFieldDataMenor.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxDataActionPerformed

    private void jCheckBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCategoriaActionPerformed

        if (jCheckBoxCategoria.isSelected()) {
            jComboBoxCategoria.setEnabled(true);

            if (jComboBoxCategoria.getItemCount() == 0) {
                jComboBoxCategoria.addItem("");
                List<CategoriaSocio> f = new DaoCategoriaSocio().BuscarTodasCategorias();
                for (CategoriaSocio funcs : f) {
                    jComboBoxCategoria.addItem(funcs.getNome());
                }
            }
        } else {
            jComboBoxCategoria.setEnabled(false);
            jComboBoxCategoria.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jCheckBoxCategoriaActionPerformed

    private void jCheckBoxSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSocioActionPerformed
        if (jCheckBoxSocio.isSelected()) {
            jComboBoxSocio.setEnabled(true);

            if (jComboBoxSocio.getItemCount() == 0) {
                jComboBoxSocio.addItem("");
                List<SociosView> s = socioView.BuscarTodosSociosView();
                for (SociosView scs : s) {
                    jComboBoxSocio.addItem(scs.getNome());
                }
            }
        } else {
            jComboBoxSocio.setEnabled(false);
            jComboBoxSocio.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jCheckBoxSocioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (preencidoCheckBox(jCheckBoxId, jTextFieldIdMenor.getText(), jTextFieldIdMaior.getText())
                && preencidoCheckBoxDate(jCheckBoxData, jFormattedTextFieldDataMenor.getDate(), jFormattedTextFieldDataMaior.getDate())
                && preencidoCombobox(jCheckBoxStatus, (String) jComboBoxStatus.getSelectedItem())
                && preencidoCombobox(jCheckBoxSocio, (String) jComboBoxSocio.getSelectedItem())
                && preencidoCombobox(jCheckBoxCategoria, (String) jComboBoxCategoria.getSelectedItem())
                && preencidoCheckBoxDate(jCheckBoxDataVecimento, jDateVencimentoMenor.getDate(), jDateVecimentoMaior.getDate())) {

            new Thread() {
                @Override
                @SuppressWarnings("empty-statement")

                public void run() {

                    int cont = 0;
                    int and = 0;

                    String id = null;
                    String status = null;
                    String datMenor = null;
                    String datMaior = null;
                    String venMenor = null;
                    String venMaior = null;
                    String categoria = null;
                    String socio = null;
                    String end = null;

                    String sql = " WHERE s.SocioExclusivo = 0 ";
                    String sql2 = " WHERE s.SocioExclusivo = 1 ";
                    String comp = "";

                    String preConsulsata
                            = "select\n"
                            + "\n"
                            + "c.id         as numeroconta,\n"
                            + "c.dataGerada as data,\n"
                            + "c.dataPag    as pagamento,\n"
                            + "c.dataVence  as vencimento,\n"
                            + "\n"
                            + "(CASE\n"
                            + "WHEN (Month(c.dataVence) = 1 ) THEN \"Dezembro\"\n"
                            + "WHEN (Month(c.dataVence) = 2 ) THEN \"Janeiro\"\n"
                            + "WHEN (Month(c.dataVence) = 3 ) THEN \"Fevereiro\"\n"
                            + "WHEN (Month(c.dataVence) = 4 ) THEN \"Março\"\n"
                            + "WHEN (Month(c.dataVence) = 5 ) THEN \"Abril\"\n"
                            + "WHEN (Month(c.dataVence) = 6 ) THEN \"Maio\"\n"
                            + "WHEN (Month(c.dataVence) = 7 ) THEN \"Junho\"\n"
                            + "WHEN (Month(c.dataVence) = 8 ) THEN \"Julho\"\n"
                            + "WHEN (Month(c.dataVence) = 9 ) THEN \"Agosto\"\n"
                            + "WHEN (Month(c.dataVence) = 10 ) THEN \"Setembro\"\n"
                            + "WHEN (Month(c.dataVence) = 11 ) THEN \"Outubro\"\n"
                            + "WHEN (Month(c.dataVence) = 12 ) THEN \"Novembro\"\n"
                            + "\n"
                            + "END )  as MesReferente,\n"
                            + "\n"
                            + "(CASE\n"
                            + " WHEN (Month(c.dataVence) = 1 )THEN (Year(c.dataVence)-1)\n"
                            + " ELSE Year(c.dataVence)\n"
                            + "end )\n"
                            + "AnoReferente,\n"
                            + "\n"
                            + "\n"
                            + "-- dados de pessoa\n"
                            + "concat(p.nome,\" \",p.sobrenome) as socio,\n"
                            + "\n"
                            + "-- dados do endere\n"
                            + "concat(e.tipo,\" \",e.nome) as endereco,\n"
                            + "ep.Numero as numero,\n"
                            + "\n"
                            + "-- dados de socio\n"
                            + "s.numeroSocio,\n"
                            + "cs.nome as categoriaSocio,\n"
                            + "\n"
                            + "-- taxa de socio\n"
                            + "c.ValorTaxa as taxaSocio,\n"
                            + "\n"
                            + "-- dados do hidrometro\n"
                            + "-- consumo em litros\n"
                            + "COALESCE(h.Consumo,0) AS consumo,\n"
                            + "\n"
                            + "-- excesso em litros\n"
                            + "COALESCE(\n"
                            + "(CASE\n"
                            + "WHEN ((h.Consumo - (15000) ) < 0 ) THEN 0\n"
                            + "ELSE (h.Consumo -  (15000))\n"
                            + "END ) ,0)as excessoLTd,\n"
                            + "\n"
                            + "-- valor de excesso em reais\n"
                            + "COALESCE(\n"
                            + "(CASE\n"
                            + "WHEN ((h.Consumo - (15000) ) < 0 ) THEN 0\n"
                            + "ELSE ((h.Consumo - (15000))* (select valor FROM taxa where id = 20))/10\n"
                            + "END ) ,0)as excessoValor,\n"
                            + "\n"
                            + "( 0) as taxas\n"
                            + "\n"
                            + ",\n"
                            + "(c.ValorTaxa +\n"
                            + "COALESCE(\n"
                            + "(CASE\n"
                            + "WHEN ((h.Consumo - (15000) ) < 0 ) THEN 0\n"
                            + "ELSE ((h.Consumo - (15000))* (select valor FROM taxa where id = 20))/10\n"
                            + "END ) ,0))\n"
                            + "AS totalconta\n"
                            + "\n"
                            + "from conta c\n"
                            + "\n"
                            + "inner join enderecopessoa ep on ep.id = c.idEnderecoPessoa\n"
                            + "inner join pessoa          p on  p.id = ep.idPessoa\n"
                            + "inner join endereco        e on  e.id = ep.idEndereco\n"
                            + "inner join socio           s on  p.id = s.idPessoa\n"
                            + "inner join categoriasocio cs on cs.id = ep.idCategoriaSocio\n"
                            + "inner join taxa            t on  t.id = cs.taxasId\n"
                            + "left  join hidrometro      h on  c.id = h.idconta\n"
                            + "left  join taxasconta     tc on  c.id = tc.contaid\n"
                            + "left  join taxa           t2 on  t2.id = tc.taxaid";

                    if (jCheckBoxId.isSelected()) {
                        id = " and ( c.id between " + jTextFieldIdMenor.getText() + " and  " + jTextFieldIdMaior.getText() + " ) ";
                        comp += id;
                    }

                    if (jCheckBoxStatus.isSelected()) {
                        if (jComboBoxStatus.getSelectedIndex() == 1) {
                            status = " and ( c.datapag is null )";
                        } else if (jComboBoxStatus.getSelectedIndex() == 2) {
                            status = "( c.datapag is not null )";
                        }
                        comp += status;
                    }

                    if (jCheckBoxData.isSelected()) {

                        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

                        datMenor = " and ( c.dataGerada between '" + s.format(jFormattedTextFieldDataMenor.getDate()) + "'";
                        datMaior = " and  '" + s.format(jFormattedTextFieldDataMaior.getDate()) + "' )";

                        comp += datMenor + datMaior;
                    }

                    if (jCheckBoxDataVecimento.isSelected()) {
                        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                        venMenor = " and ( c.dataVence between '" + s.format(jDateVencimentoMenor.getDate()) + "'";
                        venMaior = " and  '" + s.format(jDateVencimentoMenor.getDate()) + "' )";
                        comp += venMenor + venMaior;
                    }

                    if (jCheckBoxCategoria.isSelected()) {
                        categoria = " and ( cs.nome like trim('" + jComboBoxCategoria.getSelectedItem() + "') )";
                        comp += categoria;
                    }

                    if (jCheckBoxSocio.isSelected()) {
                        socio = " and lower(trim(concat(p.nome,' ', p.sobrenome))) = lower(trim(concat('" + jComboBoxSocio.getSelectedItem() + "')))";
                        comp += socio;
                    }
                    if (jCheckBoxLogradouro.isSelected()) {
                        end = " and lower(concat(e.tipo,\" \",e.nome)) = lower(\"" + jComboBoxLogradouro.getSelectedItem() + "\")";
                        comp += end;
                    }

                    sql += comp;
                    sql2 += comp;

                    sql += " group by c.id ";
                    sql2 += " group by c.id ";

                    System.out.println(sql);
                    System.out.println(sql2);

                    if (jCheckRegristro.isSelected()) {
                        sql += " order by c.id ";
                        cont++;
                    } else if (jCheckData.isSelected()) {
                        sql += " order by c.dataGerada ";
                        cont++;
                    } else if (jCheckRegristro.isSelected()) {
                        sql += " order by cs.nome ";
                        cont++;
                    } else if (jCheckCategoria.isSelected()) {
                        sql += " order by p.sobrenome ";
                        cont++;
                    } else if (jCheckNumero.isSelected()) {
                        sql += " order by ep.numero ";
                        cont++;
                    }

                    try {
                        /*
                        Connection conn = HibernateUtil.getConnection();
                        Map<String, Object> p = new HashMap<>();
                        Map<String, Object> p2 = new HashMap<>();

                        p.put("complementos", sql);
                        p2.put("complementos", sql2);

                        JasperPrint jasper = JasperFillManager.fillReport(getClass().getResourceAsStream("/relatorios/2016/conta-2016.jasper"), p, conn);
                        JasperViewer.viewReport(jasper, false);

                        JasperPrint jasper2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/relatorios/rc_exclusivo.jasper"), p2, conn);
                        JasperViewer.viewReport(jasper2, false);
                        */    
                    } catch (Exception ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckRegristroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckRegristroActionPerformed

        jCheckData.setSelected(false);
        jCheckSocio.setSelected(false);
        jCheckCategoria.setSelected(false);
        jCheckNumero.setSelected(false);

    }//GEN-LAST:event_jCheckRegristroActionPerformed

    private void jCheckDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckDataActionPerformed

        jCheckRegristro.setSelected(false);
        jCheckSocio.setSelected(false);
        jCheckCategoria.setSelected(false);
        jCheckNumero.setSelected(false);

    }//GEN-LAST:event_jCheckDataActionPerformed

    private void jCheckCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckCategoriaActionPerformed

        jCheckRegristro.setSelected(false);
        jCheckData.setSelected(false);
        jCheckSocio.setSelected(false);
        jCheckNumero.setSelected(false);

    }//GEN-LAST:event_jCheckCategoriaActionPerformed

    private void jCheckSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckSocioActionPerformed

        jCheckRegristro.setSelected(false);
        jCheckData.setSelected(false);
        jCheckCategoria.setSelected(false);
        jCheckNumero.setSelected(false);

    }//GEN-LAST:event_jCheckSocioActionPerformed

    private void jCheckBoxLogradouroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxLogradouroActionPerformed
        if (jCheckBoxLogradouro.isSelected()) {
            jComboBoxLogradouro.setEnabled(true);

            if (jComboBoxLogradouro.getItemCount() == 0) {
                jComboBoxLogradouro.addItem("");
                List<Endereco> s = daoEndereco.BuscarTodosEnderecos();
                for (Endereco scs : s) {
                    jComboBoxLogradouro.addItem(scs.getTipo() + " " + scs.getNome());
                }
            }
        } else {
            jComboBoxLogradouro.setEnabled(false);
            jComboBoxLogradouro.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jCheckBoxLogradouroActionPerformed

    private void jCheckBoxDataVecimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxDataVecimentoActionPerformed
        if (jCheckBoxDataVecimento.isSelected()) {
            jDateVencimentoMenor.setEnabled(true);
            jDateVecimentoMaior.setEnabled(true);
        } else {
            jDateVencimentoMenor.setDate(null);
            jDateVecimentoMaior.setDate(null);
            jDateVencimentoMenor.setEnabled(false);
            jDateVecimentoMaior.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxDataVecimentoActionPerformed

    private void jCheckBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxStatusActionPerformed
        if (jCheckBoxStatus.isSelected()) {
            jComboBoxStatus.setEnabled(true);
        } else {
            jComboBoxStatus.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxStatusActionPerformed

    private void jTextFieldIdMenorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIdMenorFocusLost
        jTextFieldIdMaior.setText(jTextFieldIdMenor.getText());
    }//GEN-LAST:event_jTextFieldIdMenorFocusLost

    private void jFormattedTextFieldDataMenorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDataMenorFocusGained
        jDateVencimentoMenor.setDate(new Date());
    }//GEN-LAST:event_jFormattedTextFieldDataMenorFocusGained

    private void jFormattedTextFieldDataMenorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDataMenorFocusLost
        jDateVecimentoMaior.setDate(jDateVencimentoMenor.getDate());
    }//GEN-LAST:event_jFormattedTextFieldDataMenorFocusLost

    private void jCheckNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckNumeroActionPerformed

        jCheckRegristro.setSelected(false);
        jCheckData.setSelected(false);
        jCheckSocio.setSelected(false);
        jCheckCategoria.setSelected(false);
    }//GEN-LAST:event_jCheckNumeroActionPerformed

    public boolean preencidoCombobox(JCheckBox check, String string) {
        boolean test;
        if (check.isSelected()) {
            test = !string.equals("");
        } else {
            test = true;
        }
        return test;
    }

    public boolean preencidoCheckBox(JCheckBox check, String a, String b) {

        boolean test;

        if (check.isSelected()) {
            test = !a.equals("") && !b.equals("");
        } else {
            test = true;
        }
        return test;
    }

    public boolean preencidoCheckBoxDate(JCheckBox check, Date a, Date b) {

        boolean test;

        if (check.isSelected()) {
            test = a != null && b != null;
        } else {
            test = true;
        }
        return test;
    }

    public boolean preencidoTipoCarne(boolean a, boolean b) {
        return a == true || b == true;
    }

    public void travarComponentes() {
        jComboBoxLogradouro.setEnabled(false);
        jComboBoxStatus.setEnabled(false);
        jTextFieldIdMenor.setEditable(false);
        jTextFieldIdMaior.setEditable(false);
        jDateVencimentoMenor.setEnabled(false);
        jDateVecimentoMaior.setEnabled(false);
        jFormattedTextFieldDataMenor.setEnabled(false);
        jFormattedTextFieldDataMaior.setEnabled(false);
        jComboBoxCategoria.setEnabled(false);
        jComboBoxSocio.setEnabled(false);
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRelatoriosContas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaRelatoriosContas().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBoxCategoria;
    private javax.swing.JCheckBox jCheckBoxData;
    private javax.swing.JCheckBox jCheckBoxDataVecimento;
    private javax.swing.JCheckBox jCheckBoxId;
    private javax.swing.JCheckBox jCheckBoxLogradouro;
    private javax.swing.JCheckBox jCheckBoxSocio;
    private javax.swing.JCheckBox jCheckBoxStatus;
    private javax.swing.JCheckBox jCheckCategoria;
    private javax.swing.JCheckBox jCheckData;
    private javax.swing.JCheckBox jCheckNumero;
    private javax.swing.JCheckBox jCheckRegristro;
    private javax.swing.JCheckBox jCheckSocio;
    private javax.swing.JComboBox jComboBoxCategoria;
    private javax.swing.JComboBox jComboBoxLogradouro;
    private javax.swing.JComboBox jComboBoxSocio;
    private javax.swing.JComboBox jComboBoxStatus;
    private com.toedter.calendar.JDateChooser jDateVecimentoMaior;
    private com.toedter.calendar.JDateChooser jDateVencimentoMenor;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldDataMaior;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldDataMenor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextFieldIdMaior;
    private javax.swing.JTextField jTextFieldIdMenor;
    // End of variables declaration//GEN-END:variables
}
