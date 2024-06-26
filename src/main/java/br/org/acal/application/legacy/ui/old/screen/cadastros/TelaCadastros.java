package br.org.acal.application.legacy.ui.old.screen.cadastros;

import br.org.acal.application.legacy.ui.telas.relatorios.tabelas.TelaTabelaEnderecos;
import br.org.acal.domain.datasource.PartnerDataSource;
import br.org.acal.domain.datasource.PersonDataSource;
import br.org.acal.domain.entity.Person;
import br.org.acal.domain.usecase.person.PersonFindByNameUsecase;
import br.org.acal.resources.legacy.dao.DaoCategoriaSocio;
import br.org.acal.resources.legacy.dao.DaoEndereco;
import br.org.acal.resources.legacy.dao.DaoEnderecoPessoa;
import br.org.acal.resources.legacy.dao.DaoEntradas;
import br.org.acal.resources.legacy.dao.DaoMotivoDespesa;
import br.org.acal.resources.legacy.dao.DaoMotivoEntrada;
import br.org.acal.resources.legacy.dao.DaoPessoa;
import br.org.acal.resources.legacy.dao.DaoSaidas;
import br.org.acal.resources.legacy.dao.DaoSocio;
import br.org.acal.resources.legacy.dao.DaoSocioView;
import br.org.acal.resources.legacy.dao.DaoTaxa;
import br.org.acal.resources.legacy.entidades.CategoriaSocio;
import br.org.acal.resources.legacy.entidades.Cheque;
import br.org.acal.resources.legacy.entidades.Endereco;
import br.org.acal.resources.legacy.entidades.EnderecoPessoa;
import br.org.acal.resources.legacy.entidades.Entrada;
import br.org.acal.resources.legacy.entidades.Funcionario;
import br.org.acal.resources.legacy.entidades.Motivodespesa;
import br.org.acal.resources.legacy.entidades.Motivoentrada;
import br.org.acal.resources.legacy.entidades.Pessoa;
import br.org.acal.resources.legacy.entidades.Saida;
import br.org.acal.resources.legacy.entidades.Socio;
import br.org.acal.resources.legacy.entidades.SociosView;
import br.org.acal.resources.legacy.entidades.Sociotabela;
import br.org.acal.resources.legacy.entidades.Taxa;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TelaCadastros extends JFrame {
    private JFrame telaPrincipal;
    private String pesquisarTable = "";
    private boolean flagEditar = false;
    private String socioNumero = "";
    private boolean cadastrar;
    private final PartnerDataSource partnerDataSource;

    private final PersonDataSource personDataSource;

    private final PersonFindByNameUsecase personFindByNameUsecase;

    private final DaoPessoa daoPessoa;
    private final DaoEndereco daoEndereco;
    private final DaoTaxa daoTaxa;
    private final DaoSocio daoSocio;
    private final DaoSaidas daoSaidas;
    private final DaoEntradas daoEntradas;
    private final DaoMotivoDespesa daoMotivoDespesa;
    private final DaoEnderecoPessoa daoEnderecoPessoa;
    private final DaoSocioView socioView;

    public TelaCadastros(
         DaoPessoa daoPessoa,
         DaoEndereco daoEndereco,
         DaoTaxa daoTaxa,
         DaoSocio daoSocio,
         DaoSaidas daoSaidas,
         DaoEntradas daoEntradas,
         DaoMotivoDespesa daoMotivoDespesa,
         DaoEnderecoPessoa daoEnderecoPessoa,
         DaoSocioView socioView,
         PartnerDataSource partnerDataSource,
         PersonDataSource personDataSource,
         PersonFindByNameUsecase personFindByNameUsecase
    ){
        this.daoPessoa = daoPessoa;
        this.daoEndereco = daoEndereco;
        this.daoTaxa =daoTaxa;
        this.daoSocio = daoSocio;
        this.daoSaidas = daoSaidas;
        this.daoEntradas = daoEntradas;
        this.daoMotivoDespesa = daoMotivoDespesa;
        this.daoEnderecoPessoa = daoEnderecoPessoa;
        this.socioView = socioView;
        this.partnerDataSource = partnerDataSource;
        this.personDataSource = personDataSource;
        this.personFindByNameUsecase = personFindByNameUsecase;
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            EventQueue.invokeLater(() -> {
                initComponents();
                startComponents();
                toFront();
                setLocationRelativeTo(null);
                super.setVisible(true);
            });
        } else {
            super.setVisible(false);
        }
    }

    public void startComponents() {
        initComponents();
        editableTextFields(false);
        setEditableComponentesLogradouros(false);
        setEditableComponentesTaxas(false);
        setEditableComponentesSocio(false);
        setEditableComponentesCategoriaSocio(false);
        setEditableComponentesTipoDespesa(false);
        setEditableComponentesDespesa(false);
        setEditableComponentesTipoReceita(false);
        setEditableComponentesReceita(false);
        setEditableComponentesCheques(false);
        jDesktopPane1.setVisible(false);
        jInternalFrame1.setVisible(false);
        setExtendedState(MAXIMIZED_BOTH);
        jFormattedCNPJ.setEditable(false);
        jRadioCPFCNPJ.setEnabled(false);
        jCheckBoxExclusivamenteSocio.setEnabled(false);
        jCheckBoxReceitaAvulsa.setEnabled(cadastrar);
    }

    /*
    public TelaCadastros(JFrame telaPrincipal, ActionEvent evt) {

        this(telaPrincipal);

        switch (evt.getActionCommand()) {
            case "Funcionarios" ->
                jTabbedPane1.setSelectedComponent(jPanelFunc);
            case "Logradouro" ->
                jTabbedPane1.setSelectedComponent(jPanelLog);
            case "Receitas" ->
                jTabbedPane1.setSelectedComponent(jPanelReceitas);
            case "Contas a Pagar" ->
                jTabbedPane1.setSelectedComponent(jPanelReceitas);
            case "Categoria Socio" ->
                jTabbedPane1.setSelectedComponent(jPanelCategoriaSocio);
            case "Socio" ->
                jTabbedPane1.setSelectedComponent(jPanelSocio);
            case "Tipo de Despesa" ->
                jTabbedPane1.setSelectedComponent(jPanelTipoDespesa);
            case "Despesa" ->
                jTabbedPane1.setSelectedComponent(jPanelDespesa);
            case "Tipo de Receita" ->
                jTabbedPane1.setSelectedComponent(jPanelTipoReceita);
            case "Receita" ->
                jTabbedPane1.setSelectedComponent(jPanelReceitas);
            case "Taxas" ->
                jTabbedPane1.setSelectedComponent(jPanelTaxas);
        }
    }
    */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupSocioAprovacao = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelLog = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jButtonLogradouroNovo = new javax.swing.JButton();
        jButtonLogradouroEditar = new javax.swing.JButton();
        jButtonLogradouroApagar = new javax.swing.JButton();
        jButtonLogradouroSalvar = new javax.swing.JButton();
        jButtonLogradouroCancelar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaLogradouroDescricao = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxLogradouroTipo = new javax.swing.JComboBox();
        jTextFieldLogradouroNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        jButtonLogradouroPesquisar = new javax.swing.JButton();
        jTextFieldLogradouroID = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jPanelFunc = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        Logradouro4 = new javax.swing.JLabel();
        Logradouro5 = new javax.swing.JLabel();
        Logradouro6 = new javax.swing.JLabel();
        Logradouro7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaFuncionarioObservacoes = new javax.swing.JTextArea();
        jTextFieldFuncionarioCargo = new javax.swing.JTextField();
        jTextFieldFuncionarioSalario = new javax.swing.JTextField();
        jFormattedTextFieldFuncionarioDataContratacao = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        Logradouro1 = new javax.swing.JLabel();
        Logradouro3 = new javax.swing.JLabel();
        jTextFieldFuncionarioNumeroDaMatricula = new javax.swing.JTextField();
        jComboBoxFuncionarioStatus = new javax.swing.JComboBox();
        jButtonFuncionarioEditar = new javax.swing.JButton();
        jButtonFuncionarioApagar = new javax.swing.JButton();
        jButtonFuncionarioSalvar = new javax.swing.JButton();
        jButtonFuncionarioNovo = new javax.swing.JButton();
        jButtonFuncionarioCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jComboBoxFuncionarioLograduro = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldFuncionarioNumero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldFuncionarioBairro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldFuncionarioCidade = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldFuncionarioCep = new javax.swing.JTextField();
        Logradouro = new javax.swing.JLabel();
        jComboBoxFuncionarioUf = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldFuncionarioNome = new javax.swing.JTextField();
        jTextFieldFuncionarioSobrenome = new javax.swing.JTextField();
        jTextFieldFuncionarioApelido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldFuncionarioID = new javax.swing.JTextField();
        jButtonFuncionarioPesquisar = new javax.swing.JButton();
        jPanel47 = new javax.swing.JPanel();
        jTextFieldFuncionarioRgnumero = new javax.swing.JTextField();
        jFormattedTextFieldFuncionarioCpf = new javax.swing.JFormattedTextField();
        jTextFieldFuncionarioNomedopai = new javax.swing.JTextField();
        jTextFieldFuncionarioNomedame = new javax.swing.JTextField();
        jFormattedTextFieldFuncionarioDataNascimento = new javax.swing.JFormattedTextField();
        jTextFieldFuncionarioTelefone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldFuncionarioEmail = new javax.swing.JTextField();
        jComboBoxFuncionarioSexo = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jFormattedTextFieldFuncionarioDataEmissao = new javax.swing.JFormattedTextField();
        jTextFieldFuncionarioOrgaoExpedidor = new javax.swing.JTextField();
        jPanelCategoriaSocio = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jButtonCategoriaSocioNovo = new javax.swing.JButton();
        jButtonCategoriaSocioEditar = new javax.swing.JButton();
        jButtonCategoriaSocioSalvar = new javax.swing.JButton();
        jButtonCategoriaSocioApagar = new javax.swing.JButton();
        jButtonCategoriaSocioCancelar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jTextFieldCategoriaSocioNome = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaCategoriaSocioDescricao = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        jComboBoxCategoriaSocioTaxa = new javax.swing.JComboBox();
        jPanel42 = new javax.swing.JPanel();
        jButtonCategoriaSocioPesquisar = new javax.swing.JButton();
        jTextFieldCategoriaSocioID = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jPanelTipoDespesa = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextFieldTipoDespesaNome = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaTipoDespesaDescricao = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaTipoDespesaObservacao = new javax.swing.JTextArea();
        jPanel45 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jTextFieldTIpoDespesaID = new javax.swing.JTextField();
        jButtonTipoDespesaPesquisar = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jButtonTipoDespesaNovo = new javax.swing.JButton();
        jButtonTipoDespesaEditar = new javax.swing.JButton();
        jButtonTipoDespesaSalvar = new javax.swing.JButton();
        jButtonTipoDespesaApagar = new javax.swing.JButton();
        jButtonTipoDespesaCancelar = new javax.swing.JButton();
        jPanelDespesa = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButtonDespesaNovo = new javax.swing.JButton();
        jButtonDespesaEditar = new javax.swing.JButton();
        jButtonDespesaSalvar = new javax.swing.JButton();
        jButtonDespesaApagar = new javax.swing.JButton();
        jButtonDespesaCancelar = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextAreaDespesaObservacao = new javax.swing.JTextArea();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jTextFieldDespesaValor = new javax.swing.JTextField();
        jComboBoxDespesaMotivo = new javax.swing.JComboBox();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jTextFieldDespesaFavorecido = new javax.swing.JTextField();
        jFormattedTextFieldDespesaData = new javax.swing.JFormattedTextField();
        jComboBoxDespesaFuncionario = new javax.swing.JComboBox();
        jPanel46 = new javax.swing.JPanel();
        jButtonDespesaPesquisar = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jTextFieldDespesaID = new javax.swing.JTextField();
        jPanelTipoReceita = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jButtonTipoReceitaNovo = new javax.swing.JButton();
        jButtonTipoReceitaEditar = new javax.swing.JButton();
        jButtonTipoReceitaSalvar = new javax.swing.JButton();
        jButtonTipoReceitaApagar = new javax.swing.JButton();
        jButtonTipoReceitaCancelar = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jTextFieldTipoReceitaNome = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextAreaTipoReceitaDescricao = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextAreaTipoReceitaObservacao = new javax.swing.JTextArea();
        jPanel48 = new javax.swing.JPanel();
        jButtonTipoReceitaPesquisar = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jTextFieldTIpoReceitaID = new javax.swing.JTextField();
        jPanelReceitas = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButtonReceitaNovo = new javax.swing.JButton();
        jButtonReceitaEditar = new javax.swing.JButton();
        jButtonReceitaSalvar = new javax.swing.JButton();
        jButtonReceitaApagar = new javax.swing.JButton();
        jButtonReceitaCancelar = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextAreaReceitaObservacao = new javax.swing.JTextArea();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jTextFieldReceitaValor = new javax.swing.JTextField();
        jComboBoxReceitaMotivoEntrada = new javax.swing.JComboBox();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jComboBoxReceitaSocio = new javax.swing.JComboBox();
        jComboBoxReceitaFuncionario = new javax.swing.JComboBox();
        jPanel50 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jTextFieldReceitaID = new javax.swing.JTextField();
        jButtonReceitaPesquisar = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jCheckBoxReceitaAvulsa = new javax.swing.JCheckBox();
        jPanelCheques = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButtonChequeNovo = new javax.swing.JButton();
        jButtonChequeEditar = new javax.swing.JButton();
        jButtonChequeSalvar = new javax.swing.JButton();
        jButtonChequeApagar = new javax.swing.JButton();
        jButtonChequeCancelar = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jTextFieldChequeValor = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jComboBoxChequeMotivoDespesa = new javax.swing.JComboBox();
        jLabel64 = new javax.swing.JLabel();
        jTextFieldChequeNumero = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaChequeObservacoes = new javax.swing.JTextArea();
        jLabel65 = new javax.swing.JLabel();
        jComboBoxChequeFuncionario = new javax.swing.JComboBox();
        jFormattedTextFieldChequeDataVencimento = new javax.swing.JFormattedTextField();
        jFormattedTextFieldChequeDataPagamento = new javax.swing.JFormattedTextField();
        jPanel52 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldChequeID = new javax.swing.JTextField();
        jButtonChequePesquisar = new javax.swing.JButton();
        jPanelTaxas = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel55 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jButtonTaxaNovo = new javax.swing.JButton();
        jButtonTaxaEditar = new javax.swing.JButton();
        jButtonTaxaSalvar = new javax.swing.JButton();
        jButtonTaxaApagar = new javax.swing.JButton();
        jButtonTaxaCancelar = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldTaxasNome = new javax.swing.JTextField();
        jTextFieldTaxasValor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaTaxasDescricao = new javax.swing.JTextArea();
        jPanel54 = new javax.swing.JPanel();
        jButtonTaxasPesquisar = new javax.swing.JButton();
        jTextFieldTaxasID = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanelSocio = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButtonSocioNovo = new javax.swing.JButton();
        jButtonSocioEditar = new javax.swing.JButton();
        jButtonSocioSalvar = new javax.swing.JButton();
        jButtonSocioApagar = new javax.swing.JButton();
        jButtonSocioCancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jComboBoxSocioCategoriaSocio = new javax.swing.JComboBox();
        jTextFieldSocioNumeroSocio = new javax.swing.JTextField();
        jFormattedTextFieldSocioDiaVencimento = new javax.swing.JFormattedTextField();
        jRadioButtonSocioAtivo = new javax.swing.JRadioButton();
        jRadioButtonSocioInativo = new javax.swing.JRadioButton();
        jFormattedTextFieldSocioDataMatricula = new javax.swing.JFormattedTextField();
        jPanel28 = new javax.swing.JPanel();
        jComboBoxSocioLogradouro = new javax.swing.JComboBox();
        jLabel80 = new javax.swing.JLabel();
        jTextFieldSocioNumero = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jTextFieldSocioBairro = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jTextFieldSocioCEP = new javax.swing.JTextField();
        Logradouro2 = new javax.swing.JLabel();
        jComboBoxSocioUF = new javax.swing.JComboBox();
        jButtonSocioAdicionarLogradouro = new javax.swing.JButton();
        jTextFieldSocioCep2 = new javax.swing.JFormattedTextField();
        jPanel23 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel36 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jTextFieldSocioEmail = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jComboBoxSocioSexo = new javax.swing.JComboBox();
        jLabel79 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jTextFieldSocioNomeMae = new javax.swing.JTextField();
        jTextFieldSocioNomePai = new javax.swing.JTextField();
        jPanel37 = new javax.swing.JPanel();
        jTextFieldSocioNome = new javax.swing.JTextField();
        jTextFieldSocioSobrenome = new javax.swing.JTextField();
        jTextFieldSocioApelido = new javax.swing.JTextField();
        jTextFieldSocioTelefone = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jFormattedTextFieldSocioDataNascimento = new javax.swing.JFormattedTextField();
        jPanel38 = new javax.swing.JPanel();
        jButtonSocioPesquisar = new javax.swing.JButton();
        jTextFieldSocioID = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jFormattedTextFieldSocioCPF = new javax.swing.JFormattedTextField();
        jTextFieldSocioRgNumero = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jFormattedTextFieldSocioDataEmissao = new javax.swing.JFormattedTextField();
        jLabel90 = new javax.swing.JLabel();
        jFormattedCNPJ = new javax.swing.JFormattedTextField();
        jRadioCPFCNPJ = new javax.swing.JCheckBox();
        jCheckBoxExclusivamenteSocio = new javax.swing.JCheckBox();
        jLabel77 = new javax.swing.JLabel();
        jTextFieldSocioOrgaoExpedidor = new javax.swing.JTextField();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButtonPesquisa = new javax.swing.JButton();
        jButtonInternalFrameVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setExtendedState(MAXIMIZED_BOTH);
        setIconImage(new ImageIcon(getClass().getResource("/img/ico.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanelLog.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelLog.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelLog.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanelLog.setLayout(new java.awt.GridBagLayout());

        jPanel41.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel40.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonLogradouroNovo.setText("Novo");
        jButtonLogradouroNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogradouroNovoActionPerformed(evt);
            }
        });

        jButtonLogradouroEditar.setText("Editar");
        jButtonLogradouroEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogradouroEditarActionPerformed(evt);
            }
        });

        jButtonLogradouroApagar.setText("Apagar");
        jButtonLogradouroApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogradouroApagarActionPerformed(evt);
            }
        });

        jButtonLogradouroSalvar.setText("Salvar");
        jButtonLogradouroSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogradouroSalvarActionPerformed(evt);
            }
        });

        jButtonLogradouroCancelar.setText("Cancelar");
        jButtonLogradouroCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogradouroCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jButtonLogradouroNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLogradouroEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLogradouroApagar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLogradouroSalvar)
                .addGap(18, 18, 18)
                .addComponent(jButtonLogradouroCancelar)
                .addContainerGap(271, Short.MAX_VALUE))
        );

        jPanel40Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonLogradouroApagar, jButtonLogradouroCancelar, jButtonLogradouroEditar, jButtonLogradouroNovo, jButtonLogradouroSalvar});

        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonLogradouroNovo)
                    .addComponent(jButtonLogradouroEditar)
                    .addComponent(jButtonLogradouroApagar)
                    .addComponent(jButtonLogradouroSalvar)
                    .addComponent(jButtonLogradouroCancelar))
                .addGap(10, 10, 10))
        );

        jPanel40Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonLogradouroApagar, jButtonLogradouroCancelar, jButtonLogradouroEditar, jButtonLogradouroNovo, jButtonLogradouroSalvar});

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Logradouros"));

        jTextAreaLogradouroDescricao.setColumns(20);
        jTextAreaLogradouroDescricao.setRows(5);
        jScrollPane1.setViewportView(jTextAreaLogradouroDescricao);

        jLabel3.setText("Descrição");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Tipo");

        jComboBoxLogradouroTipo.setModel(new javax.swing.DefaultComboBoxModel(
            new String[] {
                "",
                "Aeroporto",
                "Apartamento",
                "Avenida",
                "Beco",
                "Bloco",
                "Caminhi",
                "Escadinha",
                "Estação",
                "Estrada",
                "Fazenda",
                "Ladeira",
                "Largo",
                "Praça",
                "Parque",
                "Quadra",
                "Quilômetro",
                "Quinta",
                "Rodovia",
                "Rua",
                "Travessa"
            }));

            jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel2.setText("Nome");

            jButtonLogradouroPesquisar.setText("Pesquisar");
            jButtonLogradouroPesquisar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonLogradouroPesquisarActionPerformed(evt);
                }
            });

            jTextFieldLogradouroID.setEnabled(false);

            jLabel32.setText("ID");

            javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
            jPanel44.setLayout(jPanel44Layout);
            jPanel44Layout.setHorizontalGroup(
                jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel32)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonLogradouroPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addComponent(jTextFieldLogradouroID))
                    .addGap(19, 19, 19))
            );
            jPanel44Layout.setVerticalGroup(
                jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel44Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldLogradouroID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonLogradouroPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(40, 40, 40))
            );

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                        .addComponent(jComboBoxLogradouroTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldLogradouroNome))
                    .addGap(18, 18, 18)
                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jComboBoxLogradouroTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jTextFieldLogradouroNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))))
                    .addGap(10, 10, 10))
            );

            javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
            jPanel41.setLayout(jPanel41Layout);
            jPanel41Layout.setHorizontalGroup(
                jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel41Layout.createSequentialGroup()
                    .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            jPanel41Layout.setVerticalGroup(
                jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel41Layout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
            jPanel20.setLayout(jPanel20Layout);
            jPanel20Layout.setHorizontalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel20Layout.setVerticalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.ipadx = 151;
            gridBagConstraints.ipady = 807;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(3, 3, 25, 0);
            jPanelLog.add(jPanel20, gridBagConstraints);

            jTabbedPane1.addTab("Logradouros", null, jPanelLog, "Cadastre um logradouro");
            jPanelLog.getAccessibleContext().setAccessibleDescription("");

            jPanelFunc.setLayout(new java.awt.GridBagLayout());

            jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

            jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalhes do Funcionario"));
            jPanel5.setToolTipText("");

            Logradouro4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            Logradouro4.setText("Cargo");

            Logradouro5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            Logradouro5.setText("Salario");

            Logradouro6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            Logradouro6.setText("Data de Contratação");

            Logradouro7.setText("Observações");

            jTextAreaFuncionarioObservacoes.setColumns(20);
            jTextAreaFuncionarioObservacoes.setRows(5);
            jScrollPane2.setViewportView(jTextAreaFuncionarioObservacoes);

            jTextFieldFuncionarioSalario.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextFieldFuncionarioSalarioActionPerformed(evt);
                }
            });

            try {
                jFormattedTextFieldFuncionarioDataContratacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(Logradouro6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFormattedTextFieldFuncionarioDataContratacao, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Logradouro4)
                                .addComponent(Logradouro5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldFuncionarioCargo)
                                .addComponent(jTextFieldFuncionarioSalario))))
                    .addGap(101, 101, 101)
                    .addComponent(Logradouro7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(99, 99, 99))
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Logradouro4)
                                .addComponent(jTextFieldFuncionarioCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Logradouro7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Logradouro5)
                                .addComponent(jTextFieldFuncionarioSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(6, 6, 6)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Logradouro6)
                                .addComponent(jFormattedTextFieldFuncionarioDataContratacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(27, Short.MAX_VALUE))
            );

            jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            Logradouro1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            Logradouro1.setText("Status");

            Logradouro3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            Logradouro3.setText("Numero da Matricula");

            jComboBoxFuncionarioStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"","Ativo", "Inativo" }));

            jButtonFuncionarioEditar.setText("Editar");
            jButtonFuncionarioEditar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonFuncionarioEditarActionPerformed(evt);
                }
            });

            jButtonFuncionarioApagar.setText("Apagar");
            jButtonFuncionarioApagar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonFuncionarioApagarActionPerformed(evt);
                }
            });

            jButtonFuncionarioSalvar.setText("Salvar");
            jButtonFuncionarioSalvar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonFuncionarioSalvarActionPerformed(evt);
                }
            });

            jButtonFuncionarioNovo.setText("Novo");
            jButtonFuncionarioNovo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonFuncionarioNovoActionPerformed(evt);
                }
            });

            jButtonFuncionarioCancelar.setText("Cancelar");
            jButtonFuncionarioCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonFuncionarioCancelarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Logradouro1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBoxFuncionarioStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButtonFuncionarioNovo)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonFuncionarioEditar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonFuncionarioApagar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonFuncionarioSalvar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonFuncionarioCancelar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                    .addComponent(Logradouro3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldFuncionarioNumeroDaMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18))
            );

            jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonFuncionarioApagar, jButtonFuncionarioCancelar, jButtonFuncionarioEditar, jButtonFuncionarioNovo, jButtonFuncionarioSalvar});

            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonFuncionarioEditar)
                            .addComponent(jButtonFuncionarioApagar)
                            .addComponent(jButtonFuncionarioSalvar)
                            .addComponent(jButtonFuncionarioNovo)
                            .addComponent(jButtonFuncionarioCancelar))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Logradouro1)
                            .addComponent(Logradouro3)
                            .addComponent(jTextFieldFuncionarioNumeroDaMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxFuncionarioStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(10, 10, 10))
            );

            jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonFuncionarioApagar, jButtonFuncionarioCancelar, jButtonFuncionarioEditar, jButtonFuncionarioNovo, jButtonFuncionarioSalvar});

            jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

            jLabel8.setText("Número");

            jLabel9.setText("Bairro");

            jLabel10.setText("Cidade");

            jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel11.setText("UF");

            jLabel12.setText("CEP");

            Logradouro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            Logradouro.setText("Logradouro");

            jComboBoxFuncionarioUf.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
                "",
                "AC",
                "AL",
                "AP",
                "AM",
                "BA",
                "CE",
                "DF",
                "ES",
                "GO",
                "MA",
                "MT",
                "MS",
                "MG",
                "PA",
                "PB",
                "PR",
                "PE",
                "PI",
                "RJ",
                "RN",
                "RS",
                "RO",
                "RR",
                "SC",
                "SE",
                "TO"
            }));

            jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Cadastros.png"))); // NOI18N
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(Logradouro)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jTextFieldFuncionarioNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldFuncionarioBairro))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jComboBoxFuncionarioLograduro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jLabel8)
                        .addComponent(jLabel9))
                    .addGap(159, 159, 159)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11)
                        .addComponent(jLabel10)
                        .addComponent(jLabel12))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBoxFuncionarioUf, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldFuncionarioCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                        .addComponent(jTextFieldFuncionarioCep))
                    .addContainerGap(214, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jTextFieldFuncionarioNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jTextFieldFuncionarioCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jTextFieldFuncionarioBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jComboBoxFuncionarioUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Logradouro)
                        .addComponent(jComboBoxFuncionarioLograduro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jTextFieldFuncionarioCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(47, Short.MAX_VALUE))
            );

            jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Pessoais"));

            jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel6.setText("Sobrenome");

            jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel5.setText("Nome");

            jTextFieldFuncionarioNome.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextFieldFuncionarioNomeActionPerformed(evt);
                }
            });

            jLabel7.setText("Apelido");

            jLabel18.setText("ID");

            jTextFieldFuncionarioID.setEditable(false);

            jButtonFuncionarioPesquisar.setText("Pesquisar");
            jButtonFuncionarioPesquisar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonFuncionarioPesquisarActionPerformed(evt);
                }
            });

            jTextFieldFuncionarioRgnumero.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextFieldFuncionarioRgnumeroActionPerformed(evt);
                }
            });

            try {
                jFormattedTextFieldFuncionarioCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            try {
                jFormattedTextFieldFuncionarioDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            jLabel4.setText("Telefone");

            jLabel14.setText("Data Nascimento");

            jLabel16.setText("Nome da Mãe");

            jLabel27.setText("Nome do Pai");

            jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel20.setText("Cpf");

            jLabel21.setText("Rg Numero");

            javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
            jPanel47.setLayout(jPanel47Layout);
            jPanel47Layout.setHorizontalGroup(
                jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel47Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel47Layout.createSequentialGroup()
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20))
                                .addGap(76, 76, 76)
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldFuncionarioRgnumero)
                                    .addGroup(jPanel47Layout.createSequentialGroup()
                                        .addComponent(jFormattedTextFieldFuncionarioCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGroup(jPanel47Layout.createSequentialGroup()
                            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16)
                                .addComponent(jLabel27)
                                .addComponent(jLabel14))
                            .addGap(48, 48, 48)
                            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldFuncionarioNomedopai, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                .addComponent(jTextFieldFuncionarioNomedame)
                                .addComponent(jTextFieldFuncionarioTelefone)
                                .addComponent(jFormattedTextFieldFuncionarioDataNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)))))
            );
            jPanel47Layout.setVerticalGroup(
                jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel47Layout.createSequentialGroup()
                    .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel47Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel4)
                            .addGap(11, 11, 11)
                            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(jFormattedTextFieldFuncionarioDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jTextFieldFuncionarioTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jTextFieldFuncionarioNomedame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(jTextFieldFuncionarioNomedopai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jFormattedTextFieldFuncionarioCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jTextFieldFuncionarioRgnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 11, Short.MAX_VALUE))
            );

            jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel19.setText("Sexo");

            jLabel13.setText("Email");

            jTextFieldFuncionarioEmail.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextFieldFuncionarioEmailActionPerformed(evt);
                }
            });

            jComboBoxFuncionarioSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"","Masculino","Feminino" }));

            jLabel22.setText("Orgão Expedidor");

            jLabel24.setText("Data de Emissao");

            try {
                jFormattedTextFieldFuncionarioDataEmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
            jPanel57.setLayout(jPanel57Layout);
            jPanel57Layout.setHorizontalGroup(
                jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel57Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel57Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFormattedTextFieldFuncionarioDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxFuncionarioSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldFuncionarioOrgaoExpedidor, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldFuncionarioEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel57Layout.setVerticalGroup(
                jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel57Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldFuncionarioEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxFuncionarioSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldFuncionarioOrgaoExpedidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(jFormattedTextFieldFuncionarioDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextFieldFuncionarioNome, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                        .addComponent(jTextFieldFuncionarioSobrenome))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButtonFuncionarioPesquisar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7)
                        .addComponent(jLabel18))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextFieldFuncionarioApelido, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addComponent(jTextFieldFuncionarioID))
                    .addGap(24, 24, 24))
                .addComponent(jSeparator1)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jTextFieldFuncionarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonFuncionarioPesquisar))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jTextFieldFuncionarioSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldFuncionarioID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jTextFieldFuncionarioApelido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(10, 10, 10)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, 0))
            );

            javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
            jPanel21.setLayout(jPanel21Layout);
            jPanel21Layout.setHorizontalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, 0))
            );
            jPanel21Layout.setVerticalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            jPanelFunc.add(jPanel21, new java.awt.GridBagConstraints());

            jTabbedPane1.addTab("Funcionários", jPanelFunc);

            jPanelCategoriaSocio.setLayout(new java.awt.GridBagLayout());

            jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            jButtonCategoriaSocioNovo.setText("Novo");
            jButtonCategoriaSocioNovo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonCategoriaSocioNovoActionPerformed(evt);
                }
            });

            jButtonCategoriaSocioEditar.setText("Editar");
            jButtonCategoriaSocioEditar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonCategoriaSocioEditarActionPerformed(evt);
                }
            });

            jButtonCategoriaSocioSalvar.setText("Salvar");
            jButtonCategoriaSocioSalvar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonCategoriaSocioSalvarActionPerformed(evt);
                }
            });

            jButtonCategoriaSocioApagar.setText("Apagar");

            jButtonCategoriaSocioCancelar.setText("Cancelar");
            jButtonCategoriaSocioCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonCategoriaSocioCancelarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
            jPanel10.setLayout(jPanel10Layout);
            jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(182, 182, 182)
                    .addComponent(jButtonCategoriaSocioNovo)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonCategoriaSocioEditar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonCategoriaSocioApagar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonCategoriaSocioSalvar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonCategoriaSocioCancelar)
                    .addContainerGap(199, Short.MAX_VALUE))
            );

            jPanel10Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonCategoriaSocioApagar, jButtonCategoriaSocioCancelar, jButtonCategoriaSocioEditar, jButtonCategoriaSocioNovo, jButtonCategoriaSocioSalvar});

            jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonCategoriaSocioNovo)
                        .addComponent(jButtonCategoriaSocioEditar)
                        .addComponent(jButtonCategoriaSocioApagar)
                        .addComponent(jButtonCategoriaSocioCancelar)
                        .addComponent(jButtonCategoriaSocioSalvar))
                    .addGap(10, 10, 10))
            );

            jPanel10Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonCategoriaSocioApagar, jButtonCategoriaSocioCancelar, jButtonCategoriaSocioEditar, jButtonCategoriaSocioNovo, jButtonCategoriaSocioSalvar});

            jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Categoria Sócio"));

            jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel34.setText("Nome");

            jLabel35.setText("Descrição");

            jTextAreaCategoriaSocioDescricao.setColumns(20);
            jTextAreaCategoriaSocioDescricao.setRows(5);
            jScrollPane5.setViewportView(jTextAreaCategoriaSocioDescricao);

            jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel36.setText("Taxa");

            jButtonCategoriaSocioPesquisar.setText("Pesquisar");
            jButtonCategoriaSocioPesquisar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonCategoriaSocioPesquisarActionPerformed(evt);
                }
            });

            jTextFieldCategoriaSocioID.setEnabled(false);

            jLabel33.setText("ID");

            javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
            jPanel42.setLayout(jPanel42Layout);
            jPanel42Layout.setHorizontalGroup(
                jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonCategoriaSocioPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel42Layout.createSequentialGroup()
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldCategoriaSocioID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            jPanel42Layout.setVerticalGroup(
                jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel42Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33)
                        .addComponent(jTextFieldCategoriaSocioID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonCategoriaSocioPesquisar)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
            jPanel7.setLayout(jPanel7Layout);
            jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel36))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldCategoriaSocioNome, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxCategoriaSocioTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel34)
                                .addComponent(jTextFieldCategoriaSocioNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel36)
                                .addComponent(jComboBoxCategoriaSocioTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel35)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(10, 10, 10))
            );

            javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
            jPanel43.setLayout(jPanel43Layout);
            jPanel43Layout.setHorizontalGroup(
                jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel43Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, 0))
            );
            jPanel43Layout.setVerticalGroup(
                jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel43Layout.createSequentialGroup()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
            jPanel30.setLayout(jPanel30Layout);
            jPanel30Layout.setHorizontalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel30Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel30Layout.setVerticalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel30Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.ipadx = 243;
            gridBagConstraints.ipady = 752;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            jPanelCategoriaSocio.add(jPanel30, gridBagConstraints);

            jTabbedPane1.addTab("Categoria Sócio", jPanelCategoriaSocio);

            jPanelTipoDespesa.setLayout(new java.awt.GridBagLayout());

            jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipos de despesas"));

            jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel37.setText("Nome");

            jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel38.setText("Descricão");

            jLabel39.setText("Observação");

            jTextAreaTipoDespesaDescricao.setColumns(20);
            jTextAreaTipoDespesaDescricao.setRows(5);
            jScrollPane6.setViewportView(jTextAreaTipoDespesaDescricao);

            jTextAreaTipoDespesaObservacao.setColumns(20);
            jTextAreaTipoDespesaObservacao.setRows(5);
            jScrollPane7.setViewportView(jTextAreaTipoDespesaObservacao);

            jLabel40.setText("ID");

            jTextFieldTIpoDespesaID.setEnabled(false);

            jButtonTipoDespesaPesquisar.setText("Pesquisar");
            jButtonTipoDespesaPesquisar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTipoDespesaPesquisarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
            jPanel45.setLayout(jPanel45Layout);
            jPanel45Layout.setHorizontalGroup(
                jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButtonTipoDespesaPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(jTextFieldTIpoDespesaID))
                    .addGap(45, 45, 45))
            );
            jPanel45Layout.setVerticalGroup(
                jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel45Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel40)
                        .addComponent(jTextFieldTIpoDespesaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonTipoDespesaPesquisar)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
            jPanel24.setLayout(jPanel24Layout);
            jPanel24Layout.setHorizontalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel24Layout.createSequentialGroup()
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel38)
                                .addComponent(jLabel37))
                            .addGap(13, 13, 13)
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldTipoDespesaNome)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)))
                        .addGroup(jPanel24Layout.createSequentialGroup()
                            .addComponent(jLabel39)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel24Layout.setVerticalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel24Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel37)
                                .addComponent(jTextFieldTipoDespesaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel38)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel24Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel39)
                            .addGap(93, 93, 93))
                        .addGroup(jPanel24Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10))))
            );

            jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            jButtonTipoDespesaNovo.setText("Novo");
            jButtonTipoDespesaNovo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTipoDespesaNovoActionPerformed(evt);
                }
            });

            jButtonTipoDespesaEditar.setText("Editar");
            jButtonTipoDespesaEditar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTipoDespesaEditarActionPerformed(evt);
                }
            });

            jButtonTipoDespesaSalvar.setText("Salvar");
            jButtonTipoDespesaSalvar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTipoDespesaSalvarActionPerformed(evt);
                }
            });

            jButtonTipoDespesaApagar.setText("Apagar");
            jButtonTipoDespesaApagar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTipoDespesaApagarActionPerformed(evt);
                }
            });

            jButtonTipoDespesaCancelar.setText("Cancelar");
            jButtonTipoDespesaCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTipoDespesaCancelarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
            jPanel11.setLayout(jPanel11Layout);
            jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(201, 201, 201)
                    .addComponent(jButtonTipoDespesaNovo)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonTipoDespesaEditar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonTipoDespesaApagar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonTipoDespesaSalvar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonTipoDespesaCancelar)
                    .addContainerGap(180, Short.MAX_VALUE))
            );

            jPanel11Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonTipoDespesaApagar, jButtonTipoDespesaCancelar, jButtonTipoDespesaEditar, jButtonTipoDespesaNovo, jButtonTipoDespesaSalvar});

            jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonTipoDespesaNovo)
                        .addComponent(jButtonTipoDespesaEditar)
                        .addComponent(jButtonTipoDespesaApagar)
                        .addComponent(jButtonTipoDespesaCancelar)
                        .addComponent(jButtonTipoDespesaSalvar))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel11Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonTipoDespesaApagar, jButtonTipoDespesaCancelar, jButtonTipoDespesaEditar, jButtonTipoDespesaNovo, jButtonTipoDespesaSalvar});

            javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
            jPanel31.setLayout(jPanel31Layout);
            jPanel31Layout.setHorizontalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel31Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0))
            );
            jPanel31Layout.setVerticalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel31Layout.createSequentialGroup()
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.ipadx = 110;
            gridBagConstraints.ipady = 835;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            jPanelTipoDespesa.add(jPanel31, gridBagConstraints);

            jTabbedPane1.addTab("Tipo/Despesa", jPanelTipoDespesa);

            jPanelDespesa.setLayout(new java.awt.GridBagLayout());

            jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            jButtonDespesaNovo.setText("Novo");
            jButtonDespesaNovo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonDespesaNovoActionPerformed(evt);
                }
            });

            jButtonDespesaEditar.setText("Editar");
            jButtonDespesaEditar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonDespesaEditarActionPerformed(evt);
                }
            });

            jButtonDespesaSalvar.setText("Salvar");
            jButtonDespesaSalvar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonDespesaSalvarActionPerformed(evt);
                }
            });

            jButtonDespesaApagar.setText("Apagar");
            jButtonDespesaApagar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonDespesaApagarActionPerformed(evt);
                }
            });

            jButtonDespesaCancelar.setText("Cancelar");
            jButtonDespesaCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonDespesaCancelarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
            jPanel12.setLayout(jPanel12Layout);
            jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(192, 192, 192)
                    .addComponent(jButtonDespesaNovo)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonDespesaEditar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonDespesaApagar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonDespesaSalvar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonDespesaCancelar)
                    .addContainerGap(189, Short.MAX_VALUE))
            );

            jPanel12Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonDespesaApagar, jButtonDespesaCancelar, jButtonDespesaEditar, jButtonDespesaNovo, jButtonDespesaSalvar});

            jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonDespesaNovo)
                        .addComponent(jButtonDespesaEditar)
                        .addComponent(jButtonDespesaApagar)
                        .addComponent(jButtonDespesaCancelar)
                        .addComponent(jButtonDespesaSalvar))
                    .addContainerGap(15, Short.MAX_VALUE))
            );

            jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonDespesaApagar, jButtonDespesaCancelar, jButtonDespesaEditar, jButtonDespesaNovo, jButtonDespesaSalvar});

            jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Despesas"));

            jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel42.setText("Data");

            jLabel44.setText("Observação");

            jTextAreaDespesaObservacao.setColumns(20);
            jTextAreaDespesaObservacao.setRows(5);
            jScrollPane9.setViewportView(jTextAreaDespesaObservacao);

            jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel47.setText("Funcionario");

            jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel48.setText("Valor");

            jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel49.setText("Motivo ");

            jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel50.setText("favorecido");

            try {
                jFormattedTextFieldDespesaData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            jButtonDespesaPesquisar.setText("Pesquisar");
            jButtonDespesaPesquisar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonDespesaPesquisarActionPerformed(evt);
                }
            });

            jLabel45.setText("ID");

            jTextFieldDespesaID.setEnabled(false);

            javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
            jPanel46.setLayout(jPanel46Layout);
            jPanel46Layout.setHorizontalGroup(
                jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45)
                    .addGap(10, 10, 10)
                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButtonDespesaPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addComponent(jTextFieldDespesaID))
                    .addGap(10, 10, 10))
            );
            jPanel46Layout.setVerticalGroup(
                jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel46Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(jTextFieldDespesaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonDespesaPesquisar)
                    .addGap(10, 10, 10))
            );

            javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
            jPanel25.setLayout(jPanel25Layout);
            jPanel25Layout.setHorizontalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel47)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel49)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBoxDespesaFuncionario, 0, 236, Short.MAX_VALUE)
                                        .addComponent(jFormattedTextFieldDespesaData, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldDespesaValor)
                                        .addComponent(jTextFieldDespesaFavorecido)
                                        .addComponent(jComboBoxDespesaMotivo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addComponent(jLabel44)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            jPanel25Layout.setVerticalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel47)
                                .addComponent(jComboBoxDespesaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel42)
                                .addComponent(jFormattedTextFieldDespesaData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldDespesaValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel48)))
                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxDespesaMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel49))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(jTextFieldDespesaFavorecido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel44)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
            jPanel53.setLayout(jPanel53Layout);
            jPanel53Layout.setHorizontalGroup(
                jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel53Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            jPanel53Layout.setVerticalGroup(
                jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel53Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
            jPanel32.setLayout(jPanel32Layout);
            jPanel32Layout.setHorizontalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel32Layout.setVerticalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.ipadx = 245;
            gridBagConstraints.ipady = 814;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            jPanelDespesa.add(jPanel32, gridBagConstraints);

            jTabbedPane1.addTab("Despesa", jPanelDespesa);

            jPanelTipoReceita.setLayout(new java.awt.GridBagLayout());

            jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jPanel49.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            jPanel49.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            jButtonTipoReceitaNovo.setText("Novo");
            jButtonTipoReceitaNovo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTipoReceitaNovoActionPerformed(evt);
                }
            });

            jButtonTipoReceitaEditar.setText("Editar");
            jButtonTipoReceitaEditar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTipoReceitaEditarActionPerformed(evt);
                }
            });

            jButtonTipoReceitaSalvar.setText("Salvar");
            jButtonTipoReceitaSalvar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTipoReceitaSalvarActionPerformed(evt);
                }
            });

            jButtonTipoReceitaApagar.setText("Apagar");
            jButtonTipoReceitaApagar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTipoReceitaApagarActionPerformed(evt);
                }
            });

            jButtonTipoReceitaCancelar.setText("Cancelar");
            jButtonTipoReceitaCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTipoReceitaCancelarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
            jPanel14.setLayout(jPanel14Layout);
            jPanel14Layout.setHorizontalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(175, 175, 175)
                    .addComponent(jButtonTipoReceitaNovo)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonTipoReceitaEditar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonTipoReceitaApagar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonTipoReceitaSalvar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonTipoReceitaCancelar)
                    .addContainerGap(206, Short.MAX_VALUE))
            );

            jPanel14Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonTipoReceitaApagar, jButtonTipoReceitaCancelar, jButtonTipoReceitaEditar, jButtonTipoReceitaNovo, jButtonTipoReceitaSalvar});

            jPanel14Layout.setVerticalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonTipoReceitaNovo)
                        .addComponent(jButtonTipoReceitaEditar)
                        .addComponent(jButtonTipoReceitaApagar)
                        .addComponent(jButtonTipoReceitaCancelar)
                        .addComponent(jButtonTipoReceitaSalvar))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel14Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonTipoReceitaApagar, jButtonTipoReceitaCancelar, jButtonTipoReceitaEditar, jButtonTipoReceitaNovo, jButtonTipoReceitaSalvar});

            jPanel49.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 280, -1, -1));

            jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipos de receita"));

            jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel51.setText("Nome");

            jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel53.setText("Descricão");

            jLabel54.setText("Observação");

            jTextAreaTipoReceitaDescricao.setColumns(20);
            jTextAreaTipoReceitaDescricao.setRows(5);
            jScrollPane10.setViewportView(jTextAreaTipoReceitaDescricao);

            jTextAreaTipoReceitaObservacao.setColumns(20);
            jTextAreaTipoReceitaObservacao.setRows(5);
            jScrollPane11.setViewportView(jTextAreaTipoReceitaObservacao);

            jButtonTipoReceitaPesquisar.setText("Pesquisar");
            jButtonTipoReceitaPesquisar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTipoReceitaPesquisarActionPerformed(evt);
                }
            });

            jLabel55.setText("ID");

            jTextFieldTIpoReceitaID.setEnabled(false);

            javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
            jPanel48.setLayout(jPanel48Layout);
            jPanel48Layout.setHorizontalGroup(
                jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldTIpoReceitaID)
                        .addComponent(jButtonTipoReceitaPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel48Layout.setVerticalGroup(
                jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel48Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldTIpoReceitaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel55))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonTipoReceitaPesquisar)
                    .addGap(10, 10, 10))
            );

            javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
            jPanel26.setLayout(jPanel26Layout);
            jPanel26Layout.setHorizontalGroup(
                jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel26Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel26Layout.createSequentialGroup()
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel51)
                                .addComponent(jLabel53))
                            .addGap(13, 13, 13))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                            .addComponent(jLabel54)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldTipoReceitaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                    .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel26Layout.setVerticalGroup(
                jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel26Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel26Layout.createSequentialGroup()
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldTipoReceitaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel51))
                            .addGap(12, 12, 12)
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel53))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel54)
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            );

            jPanel49.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 800, -1));

            jPanel33.add(jPanel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.ipadx = 66;
            gridBagConstraints.ipady = 770;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            jPanelTipoReceita.add(jPanel33, gridBagConstraints);

            jTabbedPane1.addTab("Tipo/Receita", jPanelTipoReceita);

            jPanelReceitas.setLayout(new java.awt.GridBagLayout());

            jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            jButtonReceitaNovo.setText("Novo");
            jButtonReceitaNovo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonReceitaNovoActionPerformed(evt);
                }
            });

            jButtonReceitaEditar.setText("Editar");
            jButtonReceitaEditar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonReceitaEditarActionPerformed(evt);
                }
            });

            jButtonReceitaSalvar.setText("Salvar");
            jButtonReceitaSalvar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonReceitaSalvarActionPerformed(evt);
                }
            });

            jButtonReceitaApagar.setText("Apagar");
            jButtonReceitaApagar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonReceitaApagarActionPerformed(evt);
                }
            });

            jButtonReceitaCancelar.setText("Cancelar");
            jButtonReceitaCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonReceitaCancelarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
            jPanel8.setLayout(jPanel8Layout);
            jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(188, 188, 188)
                    .addComponent(jButtonReceitaNovo)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonReceitaEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonReceitaApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonReceitaSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonReceitaCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(193, Short.MAX_VALUE))
            );

            jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonReceitaApagar, jButtonReceitaCancelar, jButtonReceitaEditar, jButtonReceitaNovo, jButtonReceitaSalvar});

            jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonReceitaNovo)
                        .addComponent(jButtonReceitaEditar)
                        .addComponent(jButtonReceitaApagar)
                        .addComponent(jButtonReceitaCancelar)
                        .addComponent(jButtonReceitaSalvar))
                    .addContainerGap())
            );

            jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonReceitaApagar, jButtonReceitaCancelar, jButtonReceitaEditar, jButtonReceitaNovo, jButtonReceitaSalvar});

            javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
            jPanel51.setLayout(jPanel51Layout);
            jPanel51Layout.setHorizontalGroup(
                jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel51Layout.setVerticalGroup(
                jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel51Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Receitas"));

            jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel43.setText("Data");

            jLabel46.setText("Observação");

            jTextAreaReceitaObservacao.setColumns(20);
            jTextAreaReceitaObservacao.setRows(5);
            jScrollPane12.setViewportView(jTextAreaReceitaObservacao);

            jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel58.setText("Funcionario");

            jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel59.setText("Valor");

            jTextFieldReceitaValor.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent evt) {
                    jTextFieldReceitaValorFocusLost(evt);
                }
            });

            jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel60.setText("Motivo ");

            jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel61.setText("Socio");

            jLabel57.setText("ID");
            jLabel57.setEnabled(false);

            jTextFieldReceitaID.setEnabled(false);
            jTextFieldReceitaID.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextFieldReceitaIDActionPerformed(evt);
                }
            });

            jButtonReceitaPesquisar.setText("Pesquisar");
            jButtonReceitaPesquisar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonReceitaPesquisarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
            jPanel50.setLayout(jPanel50Layout);
            jPanel50Layout.setHorizontalGroup(
                jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButtonReceitaPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(jTextFieldReceitaID))
                    .addGap(10, 10, 10))
            );
            jPanel50Layout.setVerticalGroup(
                jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel50Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel57)
                        .addComponent(jTextFieldReceitaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addComponent(jButtonReceitaPesquisar)
                    .addGap(10, 10, 10))
            );

            jCheckBoxReceitaAvulsa.setText("Entrada Avulsa");
            jCheckBoxReceitaAvulsa.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBoxReceitaAvulsaActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
            jPanel27.setLayout(jPanel27Layout);
            jPanel27Layout.setHorizontalGroup(
                jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel27Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel58)
                        .addComponent(jLabel43)
                        .addComponent(jLabel59)
                        .addComponent(jLabel60)
                        .addComponent(jLabel61)
                        .addComponent(jLabel46))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel27Layout.createSequentialGroup()
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel27Layout.createSequentialGroup()
                                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBoxReceitaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel27Layout.createSequentialGroup()
                                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBoxReceitaMotivoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel27Layout.createSequentialGroup()
                                            .addComponent(jComboBoxReceitaSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(40, 40, 40)
                                            .addComponent(jCheckBoxReceitaAvulsa)))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addContainerGap())
                        .addGroup(jPanel27Layout.createSequentialGroup()
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldReceitaValor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 375, Short.MAX_VALUE))))
            );
            jPanel27Layout.setVerticalGroup(
                jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel27Layout.createSequentialGroup()
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel27Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel58)
                                .addComponent(jComboBoxReceitaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(12, 12, 12)
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel43)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel59)
                        .addComponent(jTextFieldReceitaValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel60)
                        .addComponent(jComboBoxReceitaMotivoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(9, 9, 9)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel61)
                        .addComponent(jComboBoxReceitaSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBoxReceitaAvulsa))
                    .addGap(8, 8, 8)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel46)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10))
            );

            javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
            jPanel34.setLayout(jPanel34Layout);
            jPanel34Layout.setHorizontalGroup(
                jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel34Layout.createSequentialGroup()
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0))
            );
            jPanel34Layout.setVerticalGroup(
                jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel34Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.ipadx = 76;
            gridBagConstraints.ipady = 764;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            jPanelReceitas.add(jPanel34, gridBagConstraints);

            jTabbedPane1.addTab("Receitas", jPanelReceitas);

            jPanelCheques.setLayout(new java.awt.GridBagLayout());

            jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            jButtonChequeNovo.setText("Novo");
            jButtonChequeNovo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonChequeNovoActionPerformed(evt);
                }
            });

            jButtonChequeEditar.setText("Editar");
            jButtonChequeEditar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonChequeEditarActionPerformed(evt);
                }
            });

            jButtonChequeSalvar.setText("Salvar");
            jButtonChequeSalvar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonChequeSalvarActionPerformed(evt);
                }
            });

            jButtonChequeApagar.setText("Apagar");
            jButtonChequeApagar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonChequeApagarActionPerformed(evt);
                }
            });

            jButtonChequeCancelar.setText("Cancelar");
            jButtonChequeCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonChequeCancelarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
            jPanel16.setLayout(jPanel16Layout);
            jPanel16Layout.setHorizontalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addGap(196, 196, 196)
                    .addComponent(jButtonChequeNovo)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonChequeEditar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonChequeApagar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonChequeSalvar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonChequeCancelar)
                    .addContainerGap(185, Short.MAX_VALUE))
            );

            jPanel16Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonChequeApagar, jButtonChequeCancelar, jButtonChequeEditar, jButtonChequeNovo, jButtonChequeSalvar});

            jPanel16Layout.setVerticalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonChequeNovo)
                        .addComponent(jButtonChequeEditar)
                        .addComponent(jButtonChequeApagar)
                        .addComponent(jButtonChequeCancelar)
                        .addComponent(jButtonChequeSalvar))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel16Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonChequeApagar, jButtonChequeCancelar, jButtonChequeEditar, jButtonChequeNovo, jButtonChequeSalvar});

            jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Cheques"));

            jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel28.setText("Funcionario");

            jTextFieldChequeValor.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextFieldChequeValorActionPerformed(evt);
                }
            });

            jLabel29.setText("Data de pagamento");

            jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel30.setText("Data de vencimento");

            jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel62.setText("Numero");

            jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel63.setText("Valor");

            jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel64.setText("Motivo da despesa");

            jTextAreaChequeObservacoes.setColumns(20);
            jTextAreaChequeObservacoes.setRows(5);
            jScrollPane8.setViewportView(jTextAreaChequeObservacoes);

            jLabel65.setText("observações");

            try {
                jFormattedTextFieldChequeDataVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            try {
                jFormattedTextFieldChequeDataPagamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            jLabel26.setText("ID");

            jTextFieldChequeID.setEnabled(false);

            jButtonChequePesquisar.setText("Pesquisar");
            jButtonChequePesquisar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonChequePesquisarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
            jPanel52.setLayout(jPanel52Layout);
            jPanel52Layout.setHorizontalGroup(
                jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel52Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel26)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonChequePesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addComponent(jTextFieldChequeID))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel52Layout.setVerticalGroup(
                jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel52Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(jTextFieldChequeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButtonChequePesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10))
            );

            javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
            jPanel15.setLayout(jPanel15Layout);
            jPanel15Layout.setHorizontalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel62)
                                .addComponent(jLabel63)
                                .addComponent(jLabel28))
                            .addGap(59, 59, 59)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBoxChequeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldChequeValor, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(jTextFieldChequeNumero)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel64)
                                        .addComponent(jLabel65))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxChequeMotivoDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel30)
                                        .addComponent(jLabel29))
                                    .addGap(10, 10, 10)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jFormattedTextFieldChequeDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jFormattedTextFieldChequeDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel62)
                                .addComponent(jTextFieldChequeNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel63))
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldChequeValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel28)
                                .addComponent(jComboBoxChequeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(jFormattedTextFieldChequeDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(jFormattedTextFieldChequeDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel64)
                        .addComponent(jComboBoxChequeMotivoDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel65)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10))
            );

            javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
            jPanel35.setLayout(jPanel35Layout);
            jPanel35Layout.setHorizontalGroup(
                jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0))
            );
            jPanel35Layout.setVerticalGroup(
                jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.ipadx = 206;
            gridBagConstraints.ipady = 882;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            jPanelCheques.add(jPanel35, gridBagConstraints);

            jTabbedPane1.addTab("Cheques", jPanelCheques);

            jPanelTaxas.setLayout(new java.awt.GridBagLayout());

            javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
            jPanel22.setLayout(jPanel22Layout);
            jPanel22Layout.setHorizontalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );
            jPanel22Layout.setVerticalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );

            jPanelTaxas.add(jPanel22, new java.awt.GridBagConstraints());

            jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            jButtonTaxaNovo.setText("Novo");
            jButtonTaxaNovo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTaxaNovoActionPerformed(evt);
                }
            });

            jButtonTaxaEditar.setText("Editar");
            jButtonTaxaEditar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTaxaEditarActionPerformed(evt);
                }
            });

            jButtonTaxaSalvar.setText("Salvar");
            jButtonTaxaSalvar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTaxaSalvarActionPerformed(evt);
                }
            });

            jButtonTaxaApagar.setText("Apagar");
            jButtonTaxaApagar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTaxaApagarActionPerformed(evt);
                }
            });

            jButtonTaxaCancelar.setText("Cancelar");
            jButtonTaxaCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTaxaCancelarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
            jPanel18.setLayout(jPanel18Layout);
            jPanel18Layout.setHorizontalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(165, 165, 165)
                    .addComponent(jButtonTaxaNovo)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonTaxaEditar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonTaxaApagar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonTaxaSalvar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonTaxaCancelar)
                    .addContainerGap(216, Short.MAX_VALUE))
            );

            jPanel18Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonTaxaApagar, jButtonTaxaCancelar, jButtonTaxaEditar, jButtonTaxaNovo, jButtonTaxaSalvar});

            jPanel18Layout.setVerticalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonTaxaNovo)
                        .addComponent(jButtonTaxaEditar)
                        .addComponent(jButtonTaxaApagar)
                        .addComponent(jButtonTaxaCancelar)
                        .addComponent(jButtonTaxaSalvar))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel18Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonTaxaApagar, jButtonTaxaCancelar, jButtonTaxaEditar, jButtonTaxaNovo, jButtonTaxaSalvar});

            jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Taxas"));

            jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel17.setText("Nome");

            jLabel23.setText("Descrição");

            jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel25.setText("Valor");

            jTextAreaTaxasDescricao.setColumns(20);
            jTextAreaTaxasDescricao.setRows(5);
            jScrollPane3.setViewportView(jTextAreaTaxasDescricao);

            jButtonTaxasPesquisar.setText("Pesquisar");
            jButtonTaxasPesquisar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonTaxasPesquisarActionPerformed(evt);
                }
            });

            jTextFieldTaxasID.setEnabled(false);

            jLabel15.setText("ID");

            javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
            jPanel54.setLayout(jPanel54Layout);
            jPanel54Layout.setHorizontalGroup(
                jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jLabel15)
                    .addGap(10, 10, 10)
                    .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonTaxasPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addComponent(jTextFieldTaxasID))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel54Layout.setVerticalGroup(
                jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldTaxasID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButtonTaxasPesquisar)
                    .addGap(10, 10, 10))
            );

            javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
            jPanel13.setLayout(jPanel13Layout);
            jPanel13Layout.setHorizontalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel25)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldTaxasValor, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldTaxasNome, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel13Layout.setVerticalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldTaxasNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(jTextFieldTaxasValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel23)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10))
            );

            javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
            jPanel55.setLayout(jPanel55Layout);
            jPanel55Layout.setHorizontalGroup(
                jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel55Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            jPanel55Layout.setVerticalGroup(
                jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel55Layout.createSequentialGroup()
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            jPanelTaxas.add(jPanel55, new java.awt.GridBagConstraints());

            jTabbedPane1.addTab("Taxas", jPanelTaxas);

            jPanelSocio.setLayout(new java.awt.GridBagLayout());

            javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
            jPanel17.setLayout(jPanel17Layout);
            jPanel17Layout.setHorizontalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );
            jPanel17Layout.setVerticalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );

            jPanelSocio.add(jPanel17, new java.awt.GridBagConstraints());

            jPanel56.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

            jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            jButtonSocioNovo.setText("Novo");
            jButtonSocioNovo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonSocioNovoActionPerformed(evt);
                }
            });

            jButtonSocioEditar.setText("Editar");
            jButtonSocioEditar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonSocioEditarActionPerformed(evt);
                }
            });

            jButtonSocioSalvar.setText("Salvar");
            jButtonSocioSalvar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonSocioSalvarActionPerformed(evt);
                }
            });

            jButtonSocioApagar.setText("Apagar");
            jButtonSocioApagar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonSocioApagarActionPerformed(evt);
                }
            });

            jButtonSocioCancelar.setText("Cancelar");
            jButtonSocioCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonSocioCancelarActionPerformed(evt);
                }
            });

            jButton1.setText("Importar Funcionário");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(jButtonSocioNovo)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonSocioEditar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonSocioApagar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonSocioSalvar)
                    .addGap(10, 10, 10)
                    .addComponent(jButtonSocioCancelar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(109, 109, 109))
            );

            jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonSocioApagar, jButtonSocioCancelar, jButtonSocioEditar, jButtonSocioNovo, jButtonSocioSalvar});

            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonSocioNovo)
                        .addComponent(jButtonSocioEditar)
                        .addComponent(jButtonSocioApagar)
                        .addComponent(jButtonSocioCancelar)
                        .addComponent(jButtonSocioSalvar)
                        .addComponent(jButton1))
                    .addContainerGap())
            );

            jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonSocioApagar, jButtonSocioCancelar, jButtonSocioEditar, jButtonSocioNovo, jButtonSocioSalvar});

            jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalhes do socio"));

            jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel85.setText("Dia  de vencimento");

            jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel86.setText("Aprovação:");

            jLabel87.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel87.setText("data de matricula");

            jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel88.setText("Numero de Socio");

            jLabel89.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel89.setText("Categoria de socio");

            jComboBoxSocioCategoriaSocio.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jComboBoxSocioCategoriaSocioActionPerformed(evt);
                }
            });

            try {
                jFormattedTextFieldSocioDiaVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            jRadioButtonSocioAtivo.setBackground(new java.awt.Color(0, 255, 255));
            buttonGroupSocioAprovacao.add(jRadioButtonSocioAtivo);
            jRadioButtonSocioAtivo.setText("Ativo");

            jRadioButtonSocioInativo.setBackground(new java.awt.Color(255, 0, 0));
            buttonGroupSocioAprovacao.add(jRadioButtonSocioInativo);
            jRadioButtonSocioInativo.setText("Inativo");

            try {
                jFormattedTextFieldSocioDataMatricula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
            jPanel29.setLayout(jPanel29Layout);
            jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addComponent(jLabel86)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jRadioButtonSocioInativo, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addComponent(jRadioButtonSocioAtivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel85)
                        .addComponent(jLabel87))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jFormattedTextFieldSocioDataMatricula)
                        .addComponent(jFormattedTextFieldSocioDiaVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel29Layout.createSequentialGroup()
                            .addComponent(jLabel89)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBoxSocioCategoriaSocio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel29Layout.createSequentialGroup()
                            .addComponent(jLabel88)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldSocioNumeroSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel29Layout.createSequentialGroup()
                            .addComponent(jRadioButtonSocioAtivo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jRadioButtonSocioInativo))
                        .addGroup(jPanel29Layout.createSequentialGroup()
                            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel86)
                                .addComponent(jFormattedTextFieldSocioDiaVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel85)
                                .addComponent(jLabel88)
                                .addComponent(jTextFieldSocioNumeroSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel29Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel87)
                                        .addComponent(jFormattedTextFieldSocioDataMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel89)))
                                .addGroup(jPanel29Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jComboBoxSocioCategoriaSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

            jLabel80.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel80.setText("Número");

            jLabel81.setText("Bairro");

            jTextFieldSocioBairro.setText("Centro");

            jLabel82.setText("Cidade");

            jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel83.setText("UF");

            jLabel84.setText("CEP");

            jTextFieldSocioCEP.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextFieldSocioCEPActionPerformed(evt);
                }
            });

            Logradouro2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            Logradouro2.setText("Logradouro");

            jComboBoxSocioUF.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
                "",
                "AC",
                "AL",
                "AP",
                "AM",
                "BA",
                "CE",
                "DF",
                "ES",
                "GO",
                "MA",
                "MT",
                "MS",
                "MG",
                "PA",
                "PB",
                "PR",
                "PE",
                "PI",
                "RJ",
                "RN",
                "RS",
                "RO",
                "RR",
                "SC",
                "SE",
                "TO"
            }));
            jComboBoxSocioUF.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jComboBoxSocioUFActionPerformed(evt);
                }
            });

            jButtonSocioAdicionarLogradouro.setText("Editar");
            jButtonSocioAdicionarLogradouro.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonSocioAdicionarLogradouroActionPerformed(evt);
                }
            });

            try {
                jTextFieldSocioCep2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }
            jTextFieldSocioCep2.setText("44700-000");

            javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
            jPanel28.setLayout(jPanel28Layout);
            jPanel28Layout.setHorizontalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel28Layout.createSequentialGroup()
                            .addComponent(Logradouro2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel28Layout.createSequentialGroup()
                                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel28Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jTextFieldSocioNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jTextFieldSocioBairro))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jPanel28Layout.createSequentialGroup()
                                    .addComponent(jComboBoxSocioLogradouro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jButtonSocioAdicionarLogradouro)
                                    .addGap(18, 18, 18))))
                        .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel28Layout.createSequentialGroup()
                            .addComponent(jLabel80)
                            .addGap(153, 153, 153)
                            .addComponent(jLabel83)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboBoxSocioUF, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel82)
                        .addGroup(jPanel28Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel84)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldSocioCep2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldSocioCEP, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addContainerGap())
            );
            jPanel28Layout.setVerticalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel82)
                            .addComponent(jTextFieldSocioCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel83)
                            .addComponent(jComboBoxSocioUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel80)
                            .addComponent(jTextFieldSocioNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(13, 13, 13)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel81)
                        .addComponent(jTextFieldSocioBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel84)
                        .addComponent(jTextFieldSocioCep2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Logradouro2)
                        .addComponent(jComboBoxSocioLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSocioAdicionarLogradouro))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Pessoais"));
            jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            jPanel23.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 234, 712, -1));

            jLabel70.setText("Email");

            jTextFieldSocioEmail.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextFieldSocioEmailActionPerformed(evt);
                }
            });

            jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel74.setText("Sexo");

            jComboBoxSocioSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"","Masculino","Feminino" }));

            jLabel79.setText("Nome do Pai");

            jLabel72.setText("Nome da Mãe");

            javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
            jPanel36.setLayout(jPanel36Layout);
            jPanel36Layout.setHorizontalGroup(
                jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel36Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel36Layout.createSequentialGroup()
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel72)
                                .addComponent(jLabel79))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldSocioNomePai)
                                .addComponent(jTextFieldSocioNomeMae)))
                        .addGroup(jPanel36Layout.createSequentialGroup()
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel70)
                                .addComponent(jLabel74))
                            .addGap(48, 48, 48)
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel36Layout.createSequentialGroup()
                                    .addComponent(jComboBoxSocioSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 256, Short.MAX_VALUE))
                                .addComponent(jTextFieldSocioEmail))))
                    .addGap(0, 0, 0))
            );
            jPanel36Layout.setVerticalGroup(
                jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel36Layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel70)
                        .addComponent(jTextFieldSocioEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel74)
                        .addComponent(jComboBoxSocioSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel79)
                        .addComponent(jTextFieldSocioNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel72)
                        .addComponent(jTextFieldSocioNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(39, Short.MAX_VALUE))
            );

            jPanel23.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 79, -1, 155));

            jTextFieldSocioApelido.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextFieldSocioApelidoActionPerformed(evt);
                }
            });

            jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel67.setText("Nome");

            jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel66.setText("Sobrenome");

            jLabel68.setText("Apelido");

            jLabel69.setText("Telefone");

            jLabel71.setText("Nascimento");

            try {
                jFormattedTextFieldSocioDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }
            jFormattedTextFieldSocioDataNascimento.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jFormattedTextFieldSocioDataNascimentoActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
            jPanel37.setLayout(jPanel37Layout);
            jPanel37Layout.setHorizontalGroup(
                jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel37Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel66)
                        .addComponent(jLabel67)
                        .addComponent(jLabel68)
                        .addComponent(jLabel69)
                        .addComponent(jLabel71))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextFieldSocioNome, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                        .addComponent(jTextFieldSocioApelido)
                        .addComponent(jTextFieldSocioSobrenome)
                        .addComponent(jTextFieldSocioTelefone)
                        .addComponent(jFormattedTextFieldSocioDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );
            jPanel37Layout.setVerticalGroup(
                jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel37Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel67)
                        .addComponent(jTextFieldSocioNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel66)
                        .addComponent(jTextFieldSocioSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel68)
                        .addComponent(jTextFieldSocioApelido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel69)
                        .addComponent(jTextFieldSocioTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel71)
                        .addComponent(jFormattedTextFieldSocioDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10))
            );

            jPanel23.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 79, -1, -1));

            jButtonSocioPesquisar.setText("Pesquisar");
            jButtonSocioPesquisar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonSocioPesquisarActionPerformed(evt);
                }
            });

            jTextFieldSocioID.setEditable(false);
            jTextFieldSocioID.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextFieldSocioIDActionPerformed(evt);
                }
            });

            jLabel73.setText("ID");

            javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
            jPanel38.setLayout(jPanel38Layout);
            jPanel38Layout.setHorizontalGroup(
                jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                    .addContainerGap(95, Short.MAX_VALUE)
                    .addComponent(jLabel73)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButtonSocioPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldSocioID, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );
            jPanel38Layout.setVerticalGroup(
                jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel38Layout.createSequentialGroup()
                    .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldSocioID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addComponent(jButtonSocioPesquisar)
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jPanel23.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 16, -1, -1));

            try {
                jFormattedTextFieldSocioCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            jLabel76.setText("Rg Numero");

            jLabel75.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel75.setText("CPF");

            jLabel78.setText("Data de Emissao");

            try {
                jFormattedTextFieldSocioDataEmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            jLabel90.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel90.setText("CNPJ");

            try {
                jFormattedCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###/####-##")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            jRadioCPFCNPJ.setText("Alternar");
            jRadioCPFCNPJ.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jRadioCPFCNPJActionPerformed(evt);
                }
            });

            jCheckBoxExclusivamenteSocio.setText("Exclusivamente Socio");

            javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
            jPanel39.setLayout(jPanel39Layout);
            jPanel39Layout.setHorizontalGroup(
                jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel39Layout.createSequentialGroup()
                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel39Layout.createSequentialGroup()
                            .addComponent(jLabel78)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jFormattedTextFieldSocioDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel39Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel76)
                                .addComponent(jLabel75))
                            .addGap(31, 31, 31)
                            .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldSocioRgNumero)
                                .addComponent(jFormattedTextFieldSocioCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))))
                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel39Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel90)
                            .addGap(14, 14, 14)
                            .addComponent(jFormattedCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jRadioCPFCNPJ)
                            .addContainerGap(241, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBoxExclusivamenteSocio)
                            .addContainerGap())))
            );
            jPanel39Layout.setVerticalGroup(
                jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel39Layout.createSequentialGroup()
                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel75)
                        .addComponent(jFormattedTextFieldSocioCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel90)
                        .addComponent(jFormattedCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioCPFCNPJ))
                    .addGap(8, 8, 8)
                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel76)
                        .addComponent(jTextFieldSocioRgNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel39Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel78)
                                .addComponent(jFormattedTextFieldSocioDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jCheckBoxExclusivamenteSocio)
                            .addGap(16, 16, 16))))
            );

            jPanel23.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 276, 712, 86));

            jLabel77.setText("Orgão Expedidor");
            jPanel23.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 249, -1, -1));
            jPanel23.add(jTextFieldSocioOrgaoExpedidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 246, 135, -1));

            javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
            jPanel56.setLayout(jPanel56Layout);
            jPanel56Layout.setHorizontalGroup(
                jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel56Layout.setVerticalGroup(
                jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel56Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanelSocio.add(jPanel56, new java.awt.GridBagConstraints());

            jTabbedPane1.addTab("Sócio", jPanelSocio);

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1045, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, Short.MAX_VALUE))
            );

            jDesktopPane1.setBackground(new java.awt.Color(240, 240, 240));
            jDesktopPane1.setOpaque(false);

            jInternalFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            jInternalFrame1.setFrameIcon(null);
            jInternalFrame1.setPreferredSize(new java.awt.Dimension(1024, 768));

            jScrollPane4.setAutoscrolls(true);
            jScrollPane4.setPreferredSize(new java.awt.Dimension(1024, 768));

            jTable1.setAutoCreateRowSorter(true);
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {

                }
            ));
            jTable1.setToolTipText("");
            jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
            jTable1.setSelectionBackground(new java.awt.Color(51, 255, 255));
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTable1MouseClicked(evt);
                }
            });
            jScrollPane4.setViewportView(jTable1);

            jLabel31.setText("Pesquisar");

            jTextField2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField2ActionPerformed(evt);
                }
            });

            jButtonPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pesquisar.gif"))); // NOI18N
            jButtonPesquisa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            jButtonPesquisa.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonPesquisaActionPerformed(evt);
                }
            });

            jButtonInternalFrameVoltar.setText("Voltar");
            jButtonInternalFrameVoltar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonInternalFrameVoltarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
            jPanel19.setLayout(jPanel19Layout);
            jPanel19Layout.setHorizontalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addComponent(jLabel31)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButtonPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButtonInternalFrameVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 644, Short.MAX_VALUE))
            );
            jPanel19Layout.setVerticalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel31)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonInternalFrameVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
            jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
            jInternalFrame1Layout.setHorizontalGroup(
                jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jInternalFrame1Layout.setVerticalGroup(
                jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jDesktopPane1.add(jInternalFrame1);
            jInternalFrame1.setBounds(0, 0, 790, 700);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(32, Short.MAX_VALUE)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        telaPrincipal.setEnabled(true);
        telaPrincipal.setVisible(true);
        telaPrincipal.toFront();

    }//GEN-LAST:event_formWindowClosed

    private void jButtonFuncionarioNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFuncionarioNovoActionPerformed

        editableTextFields(true);
        if (!this.cadastrar) {
            limparCampos();
        }
        jButtonFuncionarioApagar.setEnabled(false);
        jButtonFuncionarioEditar.setEnabled(false);
        jButtonFuncionarioNovo.setEnabled(false);

        if (jComboBoxFuncionarioLograduro.getItemCount() == 0) {
            jComboBoxFuncionarioLograduro.addItem("");
            List<Endereco> enderecos = daoEndereco.BuscarTodosEnderecos();
            for (Endereco endereco : enderecos) {

                jComboBoxFuncionarioLograduro.addItem(endereco.getTipo() + " " + endereco.getNome());

            }
        }
        jComboBoxFuncionarioLograduro.setSelectedItem("");
        this.cadastrar = false;
    }//GEN-LAST:event_jButtonFuncionarioNovoActionPerformed

    private void jTextFieldFuncionarioNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFuncionarioNomeActionPerformed

        if (jButtonFuncionarioNovo.isEnabled()) {
            String nome = jTextFieldFuncionarioNome.getText();
            if (nome.matches("\\w+\\s\\w+")) {
                Pessoa pessoa = daoPessoa.BuscarNomeCompleto(jTextFieldFuncionarioNome.getText());
                if (pessoa != null) {

                    preencherAbaFuncionarios(pessoa);
                    jButtonFuncionarioApagar.setEnabled(true);
                    jButtonFuncionarioEditar.setEnabled(true);
                    jButtonFuncionarioCancelar.setEnabled(true);

                } else {

                    JOptionPane.showMessageDialog(this, "Funcionário não encontrado");
                    jTextFieldFuncionarioNome.setText("");
                }

            } else {

                JOptionPane.showMessageDialog(this, "Para fazer uma pesquisa por nome é necessário digitar o sobrenome");
            }
        } else {

            JOptionPane.showMessageDialog(this, "Para realizar uma pesquisa é necessário primeiro cancelar a operação ");
        }
    }//GEN-LAST:event_jTextFieldFuncionarioNomeActionPerformed

    private void jButtonFuncionarioPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFuncionarioPesquisarActionPerformed

        if (jButtonFuncionarioNovo.isEnabled()) {

            pesquisarTable = "funcionarios";
            mostrarJtable();

        } else {

            JOptionPane.showMessageDialog(this, "É preciso cancelar a operação de inclusão para realizar pesquisa!");
        }
    }//GEN-LAST:event_jButtonFuncionarioPesquisarActionPerformed

    private void jButtonFuncionarioCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFuncionarioCancelarActionPerformed
        editableTextFields(false);
        jButtonFuncionarioCancelar.setEnabled(false);
        jButtonFuncionarioEditar.setEnabled(false);
        jButtonFuncionarioApagar.setEnabled(false);
        jButtonFuncionarioSalvar.setEnabled(false);
        jButtonFuncionarioNovo.setEnabled(true);
        limparCampos();
    }//GEN-LAST:event_jButtonFuncionarioCancelarActionPerformed

    private void jButtonFuncionarioEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFuncionarioEditarActionPerformed

        // JOptionPane.showMessageDialog(this, "Você está em modo de edição, as informações só serão gravadas depois de clicar em salvar!");
        editableTextFields(true);
        jButtonFuncionarioSalvar.setEnabled(true);
        jButtonFuncionarioCancelar.setEnabled(true);
        jButtonFuncionarioNovo.setEnabled(false);
        jButtonFuncionarioApagar.setEnabled(false);
        jButtonFuncionarioEditar.setEnabled(false);

    }//GEN-LAST:event_jButtonFuncionarioEditarActionPerformed

    private void jButtonFuncionarioApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFuncionarioApagarActionPerformed

        int result = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o registro?", "Apagar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {

            try {
                Pessoa p = daoPessoa.BuscarPessoaId(Integer.parseInt(jTextFieldFuncionarioID.getText()));
                daoPessoa.ApagarPessoa(p);
                JOptionPane.showMessageDialog(this, "Registro excluido com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                jButtonFuncionarioCancelarActionPerformed(evt);
            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, "Erro para excluir o registro");
            }

        }
    }//GEN-LAST:event_jButtonFuncionarioApagarActionPerformed

    private void jButtonFuncionarioSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFuncionarioSalvarActionPerformed

        if (isCamposFuncionariosPreenchidos()) {

            if (jTextFieldFuncionarioID.getText().equals("")) {
                int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja gravar os dados?", "Salvar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    Pattern p1 = Pattern.compile("\\d{2}\\/\\d{2}\\/\\d{4}");;
                    Matcher m;
                    try {
                        Pessoa p = new Pessoa();
                        p.setNome(jTextFieldFuncionarioNome.getText());
                        p.setSobrenome(jTextFieldFuncionarioSobrenome.getText());
                        p.setApelido(jTextFieldFuncionarioApelido.getText());
                        p.setTelefone(jTextFieldFuncionarioTelefone.getText());
                        p.setEmail(jTextFieldFuncionarioEmail.getText());
                        p.setSexo((String) jComboBoxFuncionarioSexo.getSelectedItem());
                        p.setNomeMae(jTextFieldFuncionarioNomedame.getText());
                        p.setNomePai(jTextFieldFuncionarioNomedopai.getText());
                        Pessoa p0 = daoPessoa.BuscarPessoaCpf(jFormattedTextFieldFuncionarioCpf.getText());
                        if (p0 != null) {
                            JOptionPane.showMessageDialog(this, "Esse cpf já existe", "Erro", JOptionPane.ERROR_MESSAGE);
                            jFormattedTextFieldFuncionarioCpf.setText("");

                            throw new Exception();
                        }
                        p.setCpf(jFormattedTextFieldFuncionarioCpf.getText());
                        p.setRgNumero(jTextFieldFuncionarioRgnumero.getText());
                        p.setRgExpedidor(jTextFieldFuncionarioOrgaoExpedidor.getText());
                        p.setNumeroEndereco(jTextFieldFuncionarioNumero.getText());
                        p.setCidade(jTextFieldFuncionarioCidade.getText());
                        p.setBairro(jTextFieldFuncionarioBairro.getText());
                        p.setUf((String) jComboBoxFuncionarioUf.getSelectedItem());
                        p.setCep(jTextFieldFuncionarioCep.getText());

                        m = p1.matcher(jFormattedTextFieldFuncionarioDataNascimento.getText());

                        if (m.find()) {

                            p.setDataNasc(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldFuncionarioDataNascimento.getText()));
                        }

                        m = p1.matcher(jFormattedTextFieldFuncionarioDataEmissao.getText());
                        if (m.find()) {

                            p.setRgEmissao(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldFuncionarioDataEmissao.getText()));
                        }
                        String endereco = (String) jComboBoxFuncionarioLograduro.getSelectedItem();
                        String[] enderecos = endereco.split(" ");

                        List<Endereco> ends = daoEndereco.BuscarEnderecoNomeLike(enderecos[1]);
                        if (!ends.isEmpty()) {
                            p.setIdEndereco(ends.get(0));
                        }

                        String status = (String) jComboBoxFuncionarioStatus.getSelectedItem();
                        if (status.equals("Ativo")) {
                            p.setStatus(true);
                        } else {
                            p.setStatus(false);
                        }

                        Funcionario f = new Funcionario();

                        f.setCargo(jTextFieldFuncionarioCargo.getText());
                        f.setSalario(BigDecimal.valueOf(Double.parseDouble(jTextFieldFuncionarioSalario.getText())));
                        f.setObservacao(jTextAreaFuncionarioObservacoes.getText());
                        f.setMatricula(Integer.parseInt(jTextFieldFuncionarioNumeroDaMatricula.getText()));
                        m = p1.matcher(jFormattedTextFieldFuncionarioDataContratacao.getText());
                        if (m.find()) {

                            f.setDataContratacao(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldFuncionarioDataContratacao.getText()));
                        }

                        f.setIdPessoa(p);

                        p.setFuncionario(f);

                        //new DaoFuncionario().AdicionarFuncionario(f);
                        daoPessoa.AdicionarPessoa(p);

                        JOptionPane.showMessageDialog(this, "Dados gravados com Sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);
                        jButtonFuncionarioCancelarActionPerformed(evt);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Erro para gravar os dados, verifique se todos os campos estão preenchidos corretamente", "ERRO", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }
                }
            } else {

                int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja gravar a edição desse registro?", "Salvar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    Pattern p1 = Pattern.compile("\\d{2}\\/\\d{2}\\/\\d{4}");;
                    Matcher m;
                    try {
                        Pessoa p = daoPessoa.BuscarPessoaId(Integer.parseInt(jTextFieldFuncionarioID.getText()));

                        p.setNome(jTextFieldFuncionarioNome.getText());
                        p.setSobrenome(jTextFieldFuncionarioSobrenome.getText());
                        p.setApelido(jTextFieldFuncionarioApelido.getText());
                        p.setTelefone(jTextFieldFuncionarioTelefone.getText());
                        p.setEmail(jTextFieldFuncionarioEmail.getText());
                        p.setSexo((String) jComboBoxFuncionarioSexo.getSelectedItem());
                        p.setNomeMae(jTextFieldFuncionarioNomedame.getText());
                        p.setNomePai(jTextFieldFuncionarioNomedopai.getText());
                        if (!p.getCpf().equals(jFormattedTextFieldFuncionarioCpf.getText())) {
                            Pessoa p0 = daoPessoa.BuscarPessoaCpf(jFormattedTextFieldFuncionarioCpf.getText());
                            if (p0 != null) {
                                JOptionPane.showMessageDialog(this, "Esse cpf já existe", "Erro", JOptionPane.ERROR_MESSAGE);
                                jFormattedTextFieldFuncionarioCpf.setText("");

                                throw new Exception();

                            }

                        }
                        p.setCpf(jFormattedTextFieldFuncionarioCpf.getText());
                        p.setRgNumero(jTextFieldFuncionarioRgnumero.getText());
                        p.setRgExpedidor(jTextFieldFuncionarioOrgaoExpedidor.getText());
                        p.setNumeroEndereco(jTextFieldFuncionarioNumero.getText());
                        p.setCidade(jTextFieldFuncionarioCidade.getText());
                        p.setBairro(jTextFieldFuncionarioBairro.getText());
                        p.setUf((String) jComboBoxFuncionarioUf.getSelectedItem());
                        p.setCep(jTextFieldFuncionarioCep.getText());

                        m = p1.matcher(jFormattedTextFieldFuncionarioDataNascimento.getText());

                        if (m.find()) {

                            p.setDataNasc(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldFuncionarioDataNascimento.getText()));
                        }

                        m = p1.matcher(jFormattedTextFieldFuncionarioDataEmissao.getText());
                        if (m.find()) {

                            p.setRgEmissao(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldFuncionarioDataEmissao.getText()));
                        }

                        String endereco = (String) jComboBoxFuncionarioLograduro.getSelectedItem();
                        String[] enderecos = endereco.split(" ");

                        List<Endereco> ends = daoEndereco.BuscarEnderecoNomeLike(enderecos[1]);
                        if (!ends.isEmpty()) {
                            p.setIdEndereco(ends.get(0));
                        }

                        String status = (String) jComboBoxFuncionarioStatus.getSelectedItem();
                        if (status.equals("Ativo")) {
                            p.setStatus(true);
                        } else {
                            p.setStatus(false);
                        }

                        p.getFuncionario().setCargo(jTextFieldFuncionarioCargo.getText());
                        p.getFuncionario().setSalario(BigDecimal.valueOf(Double.parseDouble(jTextFieldFuncionarioSalario.getText().replaceAll("R$", ""))));
                        p.getFuncionario().setObservacao(jTextAreaFuncionarioObservacoes.getText());
                        p.getFuncionario().setMatricula(Integer.parseInt(jTextFieldFuncionarioNumeroDaMatricula.getText()));
                        m = p1.matcher(jFormattedTextFieldFuncionarioDataContratacao.getText());
                        if (m.find()) {

                            p.getFuncionario().setDataContratacao(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldFuncionarioDataContratacao.getText()));
                        }

                        daoPessoa.AlterarPessoa(p);

                        JOptionPane.showMessageDialog(this, "Dados gravados com Sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);
                        jButtonFuncionarioCancelarActionPerformed(evt);

                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Não foi possivel alterar os dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonFuncionarioSalvarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() > 1) {

            if (pesquisarTable.equals("funcionarios")) {
                limparCampos();
                preencherAbaFuncionarios(daoPessoa.BuscarPessoaCpf((String) jTable1.getValueAt(jTable1.getSelectedRow(), 2)));
                jButtonFuncionarioApagar.setEnabled(true);
                jButtonFuncionarioEditar.setEnabled(true);
                jButtonFuncionarioCancelar.setEnabled(true);
            } else if (pesquisarTable.equals("importar funcionário")) {

                limparCamposSocio();
                preencherCamposSocioImportados(daoPessoa.BuscarPessoaCpf((String) jTable1.getValueAt(jTable1.getSelectedRow(), 2)));
                // jButtonSocioSalvar.setEnabled(true);
                //jButtonSocioCancelar.setEnabled(true);

            } else if (pesquisarTable.equals("logradouros")) {

                limparCamposLogradouro();
                preencherCamposLogradouro(daoEndereco.BuscarPorId((Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0)));

                jButtonLogradouroApagar.setEnabled(true);
                jButtonLogradouroCancelar.setEnabled(true);
                jButtonLogradouroEditar.setEnabled(true);

            } else if (pesquisarTable.equals("taxas")) {

                limparCamposTaxas();
                preencherCamposTaxas(daoTaxa.TaxaPorId((Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0)));

                jButtonTaxaApagar.setEnabled(true);
                jButtonTaxaCancelar.setEnabled(true);
                jButtonTaxaEditar.setEnabled(true);

            } else if (pesquisarTable.equals("socios")) {

                limparCamposSocio();
                preencherCamposSocio(daoSocio.BuscarSocioId((Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
                jButtonSocioApagar.setEnabled(true);
                jButtonSocioCancelar.setEnabled(true);
                jButtonSocioEditar.setEnabled(true);

            } else if (pesquisarTable.equals("categoria socio")) {

                limparCamposCategoriaSocio();

                preencherCamposCategoriaSocio(new DaoCategoriaSocio().BuscarCategoriaSocioPorId((Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
                jButtonCategoriaSocioApagar.setEnabled(true);
                jButtonCategoriaSocioEditar.setEnabled(true);
                jButtonCategoriaSocioCancelar.setEnabled(true);

            } else if (pesquisarTable.equals("tipo despesa")) {

                limparCamposTipoDespesa();

            } else if (pesquisarTable.equals("despesa")) {

                limparCamposDespesa();

            } else if (pesquisarTable.equals("tipo receita")) {

                limparCamposTipoReceita();

            } else if (pesquisarTable.equals("receita")) {

                limparCamposReceita();

            }

            jPanel1.setVisible(true);
            jDesktopPane1.setVisible(false);
            jInternalFrame1.setVisible(false);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

        if (!jTextField2.getText().isEmpty()) {

            preencherTabelaComLike();

        } else if (jTextField2.getText().equals("")) {

            preencherTabelaComTodos();

        }

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void findPartners(){
        Collection<Person> person = personFindByNameUsecase.execute(jTextField2.getText()) ;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        if (!person.isEmpty()) {
            person.forEach(p -> {
                model.addRow(
                    new Object[]{
                            p.getId(),
                            p.getName(),
                            p.getAddressNumber(),
                            "Endereco",
                    });
            });

        } else {
            JOptionPane.showMessageDialog(this, "Sócio não encontrado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void preencherTabelaComLike() {

        val search = pesquisarTable.toLowerCase();
        val partners = "socios";

        switch (search) {
            case partners:
                findPartners();
            break;
        }

        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

           if (pesquisarTable.equals("logradouros")) {

                ArrayList<Endereco> end = (ArrayList) daoEndereco.BuscarEnderecoNomeLike(jTextField2.getText());
                model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                if (!end.isEmpty()) {

                    for (Endereco e : end) {

                        model.addRow(new Object[]{e.getId(), e.getNome(), e.getTipo(), e.getDescricao()});

                    }

                } else {

                    JOptionPane.showMessageDialog(this, "Endereço não encontrado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (pesquisarTable.equals("taxas")) {

                ArrayList<Taxa> taxas = (ArrayList) daoTaxa.BuscarTaxaNomeLike(jTextField2.getText());
                model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                if (!taxas.isEmpty()) {

                    for (Taxa t : taxas) {

                        model.addRow(new Object[]{t.getId(), t.getNome(), t.getDescricao(), t.getValor()});

                    }

                } else {

                    JOptionPane.showMessageDialog(this, "Taxa não encontrada", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (pesquisarTable.equals("categoria socio")) {

                ArrayList<CategoriaSocio> categorias = (ArrayList) new DaoCategoriaSocio().BuscarCategoriaPorNomeLike(jTextField2.getText());
                model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                if (!categorias.isEmpty()) {

                    for (CategoriaSocio c : categorias) {
                        model.addRow(new Object[]{c.getId(), c.getNome(), c.getDescricao(), c.getTaxasId().getNome()});

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Categoria Sócio não encontrada", "Atenção", JOptionPane.INFORMATION_MESSAGE);

                }

            } else if (pesquisarTable.equals("tipo despesa")) {

                ArrayList<Motivodespesa> tipos = (ArrayList) daoMotivoDespesa.BuscarMotivoDespesaLikeNome(jTextField2.getText());
                model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);

                if (!tipos.isEmpty()) {

                    for (Motivodespesa m : tipos) {
                        model.addRow(new Object[]{m.getId(), m.getNome(), m.getDescricao(), m.getObservacao()});
                    }
                } else {

                    JOptionPane.showMessageDialog(this, "Tipo Despesa não encontrado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (pesquisarTable.equals("despesa")) {

                ArrayList<Saida> despesas = (ArrayList) daoSaidas.BuscarSaidaFavorecidoLikeNome(jTextField2.getText());
                model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);

                if (!despesas.isEmpty()) {

                    for (Saida s : despesas) {

                        model.addRow(new Object[]{s.getId(), SimpleDateFormat.getDateInstance().format(s.getData()), s.getValor(), s.getFavorecido(), s.getIdfuncionario().getIdPessoa().getNome() + " " + s.getIdfuncionario().getIdPessoa().getSobrenome(), s.getIdmotivosaida().getNome(), s.getObservacao()});

                    }

                } else {

                    JOptionPane.showMessageDialog(this, "Despesa não encontrada", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (pesquisarTable.equals("tipo receita")) {

                ArrayList<Motivoentrada> motivoEntrada = (ArrayList) new DaoMotivoEntrada().BuscarMotivoEntradaLikeNome(jTextField2.getText());
                model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);

                if (!motivoEntrada.isEmpty()) {

                    for (Motivoentrada m : motivoEntrada) {

                        model.addRow(new Object[]{m.getId(), m.getNome(), m.getDescricao(), m.getObservacao()});
                    }

                } else {

                    JOptionPane.showMessageDialog(this, "Motivo Entrada não encontrado", "Atenção", JOptionPane.INFORMATION_MESSAGE);

                }

            } else if (pesquisarTable.equals("receita")) {

                ArrayList<Entrada> entradas = (ArrayList) daoEntradas.BuscarEntradaCedenteLikeNome(jTextField2.getText());
                model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);

                if (!entradas.isEmpty()) {

                    for (Entrada e : entradas) {

                        if (e.getData() != null) {

                            model.addRow(new Object[]{e.getId(), SimpleDateFormat.getDateInstance().format(e.getData()), e.getValor(), e.getIdCedente().getIdPessoa().getNome() + " " + e.getIdCedente().getIdPessoa().getSobrenome(), e.getIdFuncionario().getIdPessoa().getNome() + " " + e.getIdFuncionario().getIdPessoa().getSobrenome(), e.getIdMotivoEntrada().getNome(), e.getObservacao()});
                        } else {

                            model.addRow(new Object[]{e.getId(), "nulo", e.getIdCedente().getIdPessoa().getNome() + " " + e.getIdCedente().getIdPessoa().getSobrenome(), e.getIdFuncionario().getIdPessoa().getNome() + " " + e.getIdFuncionario().getIdPessoa().getSobrenome(), e.getIdMotivoEntrada().getNome(), e.getObservacao()});
                        }
                    }

                } else {

                    JOptionPane.showMessageDialog(this, " Entrada não encontrada", "Atenção", JOptionPane.INFORMATION_MESSAGE);

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void preencherTabelaComTodos() {

        DefaultTableModel model;

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("properties/hibernate.properties"));

            if (!prop.get("hibernate.connection.username").equals("root")) {
                throw new Exception("Você não tem privilégios de Administrador");
            }

            switch (pesquisarTable) {
                case "logradouros" -> {
                    ArrayList<Endereco> enderecos = (ArrayList) daoEndereco.BuscarTodosEnderecos();
                    model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);
                    if (!enderecos.isEmpty()) {
                        for (Endereco end : enderecos) {

                            model.addRow(new Object[]{end.getId(), end.getNome(), end.getTipo(), end.getDescricao()});
                        }
                    } else {

                        JOptionPane.showMessageDialog(this, "A tabela de Endereços está vazia", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                case "taxas" -> {
                    ArrayList<Taxa> taxas = (ArrayList) daoTaxa.TaxasTodas();
                    model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);
                    if (!taxas.isEmpty()) {

                        for (Taxa t : taxas) {
                            model.addRow(new Object[]{t.getId(), t.getNome(), t.getDescricao(), t.getValor()});
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "A tabela de Taxas está vazia", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                }
                case "socios" -> {
                    List<Sociotabela> socios = daoSocio.TodosOsSociosView();
                    model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);
                    if (!socios.isEmpty()) {

                        for (Sociotabela s : socios) {

                            //List<Enderecopessoa> ends = s.getIdPessoa().getEnderecopessoaList();
                            //List<Enderecopessoa> ends = daoSocio.TodosOsEnderecosPessoasJDBC(s);
                            // Pessoa p = daoSocio.fromSocioJDBC(s);
                            //Categoriasocio c = daoSocio.fromCategoriaSocioJDBC(s);
                            // for(Enderecopessoa e : ends){
                            // Categoriasocio c = daoSocio.fromCategoriaSocioJDBC(e.getId());
                            //Endereco endereco = daoSocio.fromEnderecoPessoaJDBC(e.getId());
                            model.addRow(new Object[]{s.getIdSocio(), s.getNomeCompleto(), s.getCpf(), s.getNome(), s.getEndereco(), s.getNumero()});

                            //}
                        }

                    } else {

                        JOptionPane.showMessageDialog(this, "A tabela de Sócios está vazia", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                }
                case "categoria socio" -> {
                    List<CategoriaSocio> categorias = new DaoCategoriaSocio().BuscarTodasCategorias();
                    model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);
                    if (!categorias.isEmpty()) {

                        for (CategoriaSocio c : categorias) {

                            model.addRow(new Object[]{c.getId(), c.getNome(), c.getDescricao(), c.getTaxasId().getNome()});
                        }

                    } else {

                        JOptionPane.showMessageDialog(this, "A tabela de Categoria Sócio está vazia", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                }
                case "tipo despesa" -> {
                    List<Motivodespesa> tipos = daoMotivoDespesa.BuscarTodosMotivos();
                    model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);
                    if (!tipos.isEmpty()) {

                        for (Motivodespesa m : tipos) {

                            model.addRow(new Object[]{m.getId(), m.getNome(), m.getDescricao(), m.getObservacao()});
                        }

                    } else {

                        JOptionPane.showMessageDialog(this, "A tabela de Motivo Despesa está vazia", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                }
                case "despesa" -> {
                    List<Saida> saidas = daoSaidas.BuscarTodasSaidas();
                    model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);
                    if (!saidas.isEmpty()) {

                        for (Saida s : saidas) {

                            model.addRow(new Object[]{s.getId(), SimpleDateFormat.getDateInstance().format(s.getData()), s.getValor(), s.getFavorecido(), s.getIdfuncionario().getIdPessoa().getNome() + " " + s.getIdfuncionario().getIdPessoa().getSobrenome(), s.getIdmotivosaida().getNome(), s.getObservacao()});
                        }

                    } else {

                        JOptionPane.showMessageDialog(this, "A tabela de Despesa está vazia", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                }
                case "tipo receita" -> {
                    List<Motivoentrada> motivoEntrada = new DaoMotivoEntrada().BuscarTodosMotivos();
                    model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);
                    if (!motivoEntrada.isEmpty()) {

                        for (Motivoentrada m : motivoEntrada) {

                            model.addRow(new Object[]{m.getId(), m.getNome(), m.getDescricao(), m.getObservacao()});
                        }

                    } else {

                        JOptionPane.showMessageDialog(this, "A tabela de Motivo Entrada está vazia", "Atenção", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
                case "receita" -> {
                    List<Entrada> entradas = daoEntradas.BuscarTodasEntradas();
                    model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);
                    if (!entradas.isEmpty()) {

                        for (Entrada e : entradas) {

                            if (e.getData() != null) {

                                model.addRow(new Object[]{e.getId(), SimpleDateFormat.getDateInstance().format(e.getData()), e.getValor(), e.getIdCedente().getIdPessoa().getNome() + " " + e.getIdCedente().getIdPessoa().getSobrenome(), e.getIdFuncionario().getIdPessoa().getNome() + " " + e.getIdFuncionario().getIdPessoa().getSobrenome(), e.getIdMotivoEntrada().getNome(), e.getObservacao()});
                            } else {

                                model.addRow(new Object[]{e.getId(), "nulo", e.getIdCedente().getIdPessoa().getNome() + " " + e.getIdCedente().getIdPessoa().getSobrenome(), e.getIdFuncionario().getIdPessoa().getNome() + " " + e.getIdFuncionario().getIdPessoa().getSobrenome(), e.getIdMotivoEntrada().getNome(), e.getObservacao()});
                            }
                        }

                    } else {

                        JOptionPane.showMessageDialog(this, "A tabela de entradas está vazia", "Atenção", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
                default -> {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisaActionPerformed

        if (!jTextField2.getText().isEmpty()) {
            preencherTabelaComLike();
        } else if (jTextField2.getText().equals("")) {
            preencherTabelaComTodos();
        }
    }//GEN-LAST:event_jButtonPesquisaActionPerformed

    private void jButtonInternalFrameVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInternalFrameVoltarActionPerformed

        switch (pesquisarTable) {

            case "funcionarios":
                jButtonFuncionarioCancelarActionPerformed(evt);
                break;
            case "logradouros":
                jButtonLogradouroCancelarActionPerformed(evt);
                break;
            case "taxas":
                jButtonTaxaCancelarActionPerformed(evt);
                break;
            case "socios":
                jButtonSocioCancelarActionPerformed(evt);
                break;
            case "categoria socio":
                jButtonCategoriaSocioCancelarActionPerformed(evt);
                break;
            case "tipo despesa":
                jButtonTipoDespesaCancelarActionPerformed(evt);
                break;
            case "despesa":
                jButtonDespesaCancelarActionPerformed(evt);
                break;
            case "tipo receita":
                jButtonTipoReceitaCancelarActionPerformed(evt);
                break;
            case "receita":
                jButtonReceitaCancelarActionPerformed(evt);
                break;
            case "cheque":
                jButtonChequeCancelarActionPerformed(evt);
            case "importar funcionário":
                jButtonSocioCancelarActionPerformed(evt);
                break;
        }
        jTextField2.setText("");
        jDesktopPane1.setVisible(false);
        jInternalFrame1.setVisible(false);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_jButtonInternalFrameVoltarActionPerformed

    private void jButtonLogradouroNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogradouroNovoActionPerformed

        limparCamposLogradouro();
        setEditableComponentesLogradouros(true);
        jButtonLogradouroEditar.setEnabled(false);
        jButtonLogradouroApagar.setEnabled(false);
        jButtonLogradouroNovo.setEnabled(false);


    }//GEN-LAST:event_jButtonLogradouroNovoActionPerformed

    private void jButtonLogradouroCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogradouroCancelarActionPerformed

        limparCamposLogradouro();
        setEditableComponentesLogradouros(false);
        jButtonLogradouroNovo.setEnabled(true);
        if (this.cadastrar) {

            this.cadastrar = false;
            jTabbedPane1.setSelectedComponent(jPanelFunc);

        }
    }//GEN-LAST:event_jButtonLogradouroCancelarActionPerformed

    private void jButtonLogradouroEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogradouroEditarActionPerformed

        setEditableComponentesLogradouros(true);
        jButtonLogradouroNovo.setEnabled(false);
        jButtonLogradouroApagar.setEnabled(false);
        jButtonLogradouroEditar.setEnabled(false);
    }//GEN-LAST:event_jButtonLogradouroEditarActionPerformed

    private void jButtonLogradouroSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogradouroSalvarActionPerformed

        if (!jTextFieldLogradouroNome.getText().equals("") && !jComboBoxLogradouroTipo.getSelectedItem().equals("")) {

            if (jTextFieldLogradouroID.getText().equals("")) {
                int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja gravar os dados?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {
                    try {
                        Endereco end = new Endereco();
                        end.setNome(jTextFieldLogradouroNome.getText());
                        end.setTipo((String) jComboBoxLogradouroTipo.getSelectedItem());
                        end.setDescricao(jTextAreaLogradouroDescricao.getText());

                        daoEndereco.AdicionarEndereco(end);

                        JOptionPane.showMessageDialog(this, "Dados gravados com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
                        jButtonLogradouroCancelarActionPerformed(evt);
                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(this, "Erro na gravação dos dados", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                }
            } else {

                int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja gravar a edição do registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    Endereco end = daoEndereco.BuscarPorId(Integer.parseInt(jTextFieldLogradouroID.getText()));
                    end.setNome(jTextFieldLogradouroNome.getText());
                    end.setTipo((String) jComboBoxLogradouroTipo.getSelectedItem());
                    end.setDescricao(jTextAreaLogradouroDescricao.getText());

                    daoEndereco.AlterarEndereco(end);

                    JOptionPane.showMessageDialog(this, "Dados gravados com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
                    jButtonLogradouroCancelarActionPerformed(evt);

                }

            }

            jTabbedPane1.setSelectedComponent(jPanelFunc);
            jButtonFuncionarioNovoActionPerformed(evt);
        } else {

            JOptionPane.showMessageDialog(this, "Os campos em negrito são obrigatórios", "Atenção", JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_jButtonLogradouroSalvarActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged

        if ((!jButtonFuncionarioNovo.isEnabled() || !jTextFieldFuncionarioID.getText().equals("")) && this.cadastrar == false) {
            if (jTabbedPane1.getSelectedComponent() != jPanelFunc) {
                JOptionPane.showMessageDialog(this, "Cancele a operação ", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
            jTabbedPane1.setSelectedComponent(jPanelFunc);

        } else if ((!jButtonLogradouroNovo.isEnabled() || !jTextFieldLogradouroID.getText().equals("")) || this.cadastrar) {

            if (jTabbedPane1.getSelectedComponent() != jPanelLog) {

                JOptionPane.showMessageDialog(this, "Cancele a operação ", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
            jTabbedPane1.setSelectedComponent(jPanelLog);

        } else if (!jButtonTaxaNovo.isEnabled() || !jTextFieldTaxasID.getText().equals("")) {

            if (jTabbedPane1.getSelectedComponent() != jPanelTaxas) {

                JOptionPane.showMessageDialog(this, "Cancele a operação ", "Atenção", JOptionPane.INFORMATION_MESSAGE);

            }
            jTabbedPane1.setSelectedComponent(jPanelTaxas);

        } else if (!jButtonSocioNovo.isEnabled() || !jTextFieldSocioID.getText().equals("") || (!jFormattedTextFieldSocioCPF.getText().equals("") && !jFormattedTextFieldSocioCPF.getText().equals("   .   .   -  "))) {

            if (jTabbedPane1.getSelectedComponent() != jPanelSocio) {

                JOptionPane.showMessageDialog(this, "Cancele a operação ", "Atenção", JOptionPane.INFORMATION_MESSAGE);

            }
            jTabbedPane1.setSelectedComponent(jPanelSocio);

        } else if (!jButtonCategoriaSocioNovo.isEnabled() || !jTextFieldCategoriaSocioID.getText().equals("")) {

            if (jTabbedPane1.getSelectedComponent() != jPanelCategoriaSocio) {

                JOptionPane.showMessageDialog(this, "Cancele a operação ", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
            jTabbedPane1.setSelectedComponent(jPanelCategoriaSocio);

        } else if (!jButtonTipoDespesaNovo.isEnabled() || !jTextFieldTIpoDespesaID.getText().equals("")) {

            if (jTabbedPane1.getSelectedComponent() != jPanelTipoDespesa) {

                JOptionPane.showMessageDialog(this, "Cancele a operação ", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
            jTabbedPane1.setSelectedComponent(jPanelTipoDespesa);

        } else if (!jButtonDespesaNovo.isEnabled() || !jTextFieldDespesaID.getText().equals("")) {

            if (jTabbedPane1.getSelectedComponent() != jPanelDespesa) {

                JOptionPane.showMessageDialog(this, "Cancele a operação ", "Atenção", JOptionPane.INFORMATION_MESSAGE);

            }
            jTabbedPane1.setSelectedComponent(jPanelDespesa);

        } else if (!jButtonTipoReceitaNovo.isEnabled() || !jTextFieldTIpoReceitaID.getText().equals("")) {

            if (jTabbedPane1.getSelectedComponent() != jPanelTipoReceita) {

                JOptionPane.showMessageDialog(this, "Cancele a operação ", "Atenção", JOptionPane.INFORMATION_MESSAGE);

            }
            jTabbedPane1.setSelectedComponent(jPanelTipoReceita);

        } else if (!jButtonReceitaNovo.isEnabled() || !jTextFieldReceitaID.getText().equals("")) {

            if (jTabbedPane1.getSelectedComponent() != jPanelReceitas) {

                JOptionPane.showMessageDialog(this, "Cancele a operação ", "Atenção", JOptionPane.INFORMATION_MESSAGE);

            }
            jTabbedPane1.setSelectedComponent(jPanelReceitas);

        } else if (!jButtonChequeNovo.isEnabled() || !jTextFieldChequeID.getText().equals("")) {
            if (jTabbedPane1.getSelectedComponent() != jPanelCheques) {

                JOptionPane.showMessageDialog(this, "Cancele a operação ", "Atenção", JOptionPane.INFORMATION_MESSAGE);

            }
            jTabbedPane1.setSelectedComponent(jPanelCheques);

        } else {

            zerarComboBox();
        }


    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButtonLogradouroPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogradouroPesquisarActionPerformed

        if (jButtonLogradouroNovo.isEnabled()) {

            pesquisarTable = "logradouros";
            mostrarJtable();

        } else {

            JOptionPane.showMessageDialog(this, "Cancele a operação para fazer uma pesquisa", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_jButtonLogradouroPesquisarActionPerformed

    private void jButtonLogradouroApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogradouroApagarActionPerformed

        int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o registro?", "Apagar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {
            try {
                Endereco end = daoEndereco.BuscarPorId(Integer.parseInt(jTextFieldLogradouroID.getText()));
                daoEndereco.ApagarEndereco(end);
                JOptionPane.showMessageDialog(this, "Registro excluido com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
                limparCamposLogradouro();
                jButtonLogradouroCancelarActionPerformed(evt);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, "Não foi possível excluir o registro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonLogradouroApagarActionPerformed

    private void jButtonTaxaNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaxaNovoActionPerformed

        limparCamposTaxas();
        setEditableComponentesTaxas(true);
        jButtonTaxaNovo.setEnabled(false);
        jButtonTaxaEditar.setEnabled(false);
        jButtonTaxaApagar.setEnabled(false);


    }//GEN-LAST:event_jButtonTaxaNovoActionPerformed

    private void jButtonTaxaCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaxaCancelarActionPerformed

        setEditableComponentesTaxas(false);
        jButtonTaxaNovo.setEnabled(true);
        jButtonTaxaCancelar.setEnabled(false);
        limparCamposTaxas();

    }//GEN-LAST:event_jButtonTaxaCancelarActionPerformed

    private void jButtonTaxaEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaxaEditarActionPerformed

        setEditableComponentesTaxas(true);
        jButtonTaxaNovo.setEnabled(false);
        jButtonTaxaApagar.setEnabled(false);
        jButtonTaxaEditar.setEnabled(false);

    }//GEN-LAST:event_jButtonTaxaEditarActionPerformed

    private void jButtonTaxaSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaxaSalvarActionPerformed

        try {
            if (!jTextFieldTaxasNome.getText().equals("") && !jTextFieldTaxasValor.getText().equals("")) {

                Pattern p = Pattern.compile("\\d+\\.\\d+");
                Matcher m = p.matcher(jTextFieldTaxasValor.getText());
                if (jTextFieldTaxasID.getText().equals("")) {

                    int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja gravar o registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (op == JOptionPane.YES_OPTION) {
                        if (m.find()) {

                            Taxa taxa = new Taxa();
                            taxa.setNome(jTextFieldTaxasNome.getText());
                            taxa.setValor(BigDecimal.valueOf(Double.parseDouble(jTextFieldTaxasValor.getText())));
                            taxa.setDescricao(jTextAreaTaxasDescricao.getText());

                            daoTaxa.AdicionarTaxa(taxa);
                            JOptionPane.showMessageDialog(this, "Dados gravados com sucesso", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                            jButtonTaxaCancelarActionPerformed(evt);
                        } else {

                            JOptionPane.showMessageDialog(this, "Números monetários devem ser separados por ponto", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {

                    int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja gravar a edição do registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (op == JOptionPane.YES_OPTION) {

                        if (m.find()) {

                            Taxa taxa = daoTaxa.TaxaPorId(Integer.parseInt(jTextFieldTaxasID.getText()));
                            taxa.setNome(jTextFieldTaxasNome.getText());
                            taxa.setValor(BigDecimal.valueOf(Double.parseDouble(jTextFieldTaxasValor.getText())));
                            taxa.setDescricao(jTextAreaTaxasDescricao.getText());

                            daoTaxa.AlterarTaxa(taxa);
                            JOptionPane.showMessageDialog(this, "Dados gravados com sucesso", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                            jButtonTaxaCancelarActionPerformed(evt);
                        } else {

                            JOptionPane.showMessageDialog(this, "Números monetários devem ser separados por ponto", "Erro", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }

            } else {

                JOptionPane.showMessageDialog(this, "Os campos em negrito são obrigatórios", "Atenção", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (HeadlessException | NumberFormatException e) {

            JOptionPane.showMessageDialog(this, "Erro ao gravar os dados", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButtonTaxaSalvarActionPerformed

    private void jButtonTaxasPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaxasPesquisarActionPerformed

        if (jButtonLogradouroNovo.isEnabled()) {

            pesquisarTable = "taxas";
            mostrarJtable();

        } else {

            JOptionPane.showMessageDialog(this, "Cancele a operação para fazer uma pesquisa", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButtonTaxasPesquisarActionPerformed

    private void jButtonTaxaApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaxaApagarActionPerformed

        try {
            int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja apagar o registro?", "Atençao", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (op == JOptionPane.YES_OPTION) {

                Taxa taxa = daoTaxa.TaxaPorId(Integer.parseInt(jTextFieldTaxasID.getText()));
                daoTaxa.ApagarTaxa(taxa);

                JOptionPane.showMessageDialog(this, "Taxa excluida com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);

                jButtonTaxaCancelarActionPerformed(evt);

            }
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro para excluir o registro", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonTaxaApagarActionPerformed

    private void jButtonSocioNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSocioNovoActionPerformed

        limparCamposSocio();
        setEditableComponentesSocio(true);
        jButtonSocioApagar.setEnabled(false);
        jButtonSocioEditar.setEnabled(false);
        jButtonSocioNovo.setEnabled(false);

        jRadioCPFCNPJ.setEnabled(true);
        jButtonSocioAdicionarLogradouro.setEnabled(false);
        if (jComboBoxSocioLogradouro.getItemCount() == 0) {

            jComboBoxSocioLogradouro.addItem("");
            List<Endereco> end = daoEndereco.BuscarTodosEnderecos();
            for (Endereco e : end) {
                jComboBoxSocioLogradouro.addItem(e.getTipo() + " " + e.getNome());
            }
        }
        if (jComboBoxSocioCategoriaSocio.getItemCount() == 0) {

            jComboBoxSocioCategoriaSocio.addItem("");
            List<CategoriaSocio> cs = new DaoCategoriaSocio().BuscarTodasCategorias();
            for (CategoriaSocio c : cs) {

                jComboBoxSocioCategoriaSocio.addItem(c.getNome());

            }

        }
        jComboBoxSocioLogradouro.setSelectedItem("");
        jComboBoxSocioCategoriaSocio.setSelectedItem("");

    }//GEN-LAST:event_jButtonSocioNovoActionPerformed

    private void jButtonSocioCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSocioCancelarActionPerformed

        limparCamposSocio();
        setEditableComponentesSocio(false);
        jButtonSocioNovo.setEnabled(true);


    }//GEN-LAST:event_jButtonSocioCancelarActionPerformed

    private void jButtonSocioEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSocioEditarActionPerformed

        flagEditar = true;
        socioNumero = (String) jTextFieldSocioNumero.getText();
        jRadioCPFCNPJ.setEnabled(true);
        setEditableComponentesSocio(true);
        jButtonSocioEditar.setEnabled(false);
        jButtonSocioApagar.setEnabled(false);
        jButtonSocioNovo.setEnabled(false);

    }//GEN-LAST:event_jButtonSocioEditarActionPerformed

    private void jButtonSocioPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSocioPesquisarActionPerformed

        if (jButtonSocioNovo.isEnabled()) {
            pesquisarTable = "socios";
            mostrarJtable();

        } else {

            JOptionPane.showMessageDialog(this, "Cancele a operação para fazer uma pesquisa", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButtonSocioPesquisarActionPerformed

    private void jButtonSocioSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSocioSalvarActionPerformed

        if (isCamposSocioPreenchidos()) {

            Pattern p1 = Pattern.compile("\\d{2}\\/\\d{2}\\/\\d{4}");;
            Matcher m;
            if (jTextFieldSocioID.getText().equals("")) {
                if (pesquisarTable.equals("importar funcionário")) {

                    int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja gravar esse funcionario como sócio?", "Salvar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (op == JOptionPane.YES_OPTION) {

                        try {
                            Pessoa p = daoPessoa.BuscarPessoaCpf(jFormattedTextFieldSocioCPF.getText());

                            p.setNome(jTextFieldSocioNome.getText());
                            p.setSobrenome(jTextFieldSocioSobrenome.getText());
                            p.setApelido(jTextFieldSocioApelido.getText());
                            p.setTelefone(jTextFieldSocioTelefone.getText());
                            p.setEmail(jTextFieldSocioEmail.getText());
                            p.setNomeMae(jTextFieldSocioNomeMae.getText());
                            p.setNomePai(jTextFieldSocioNomePai.getText());
                            p.setRgNumero(jTextFieldSocioRgNumero.getText());
                            p.setRgExpedidor(jTextFieldSocioOrgaoExpedidor.getText());

                            if (jFormattedCNPJ.isEditable()) {
                            } else {
                            }
                            if (flagEditar == false) {
                                if (daoEnderecoPessoa.EnderecopessoaporNumero(jTextFieldSocioNumero.getText()) != null) {
                                    throw new Exception("Número de Endereço já cadastrado -1");
                                }
                            } else {
                                if (!daoEnderecoPessoa.EnderecopessoaporNumero(jTextFieldSocioNumero.getText()).equals(socioNumero)) {
                                    if (daoEnderecoPessoa.EnderecopessoaporNumero(jTextFieldSocioNumero.getText()) != null) {
                                        throw new Exception("Número de Endereço já cadastrado -2");
                                    }
                                }

                            }

                            p.setNumeroEndereco(jTextFieldSocioNumero.getText());
                            p.setCidade(jTextFieldSocioCep2.getText());
                            p.setBairro(jTextFieldSocioBairro.getText());
                            p.setCep(jTextFieldSocioCEP.getText());

                            if (p1.matcher(jFormattedTextFieldSocioDataNascimento.getText()).find()) {
                                p.setDataNasc(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldSocioDataNascimento.getText()));
                            }

                            p.setCpf(jFormattedTextFieldSocioCPF.getText());
                            if (p1.matcher(jFormattedTextFieldSocioDataEmissao.getText()).find()) {
                                p.setRgEmissao(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldSocioDataEmissao.getText()));
                            }

                            p.setSexo((String) jComboBoxSocioSexo.getSelectedItem());
                            p.setUf((String) jComboBoxSocioUF.getSelectedItem());

                            EnderecoPessoa ep = new EnderecoPessoa();
                            Endereco e = daoEndereco.BuscarEnderecoCompleto((String) jComboBoxSocioLogradouro.getSelectedItem());
                            List<CategoriaSocio> cs = new DaoCategoriaSocio().BuscarCategoriaPorNomeLike((String) jComboBoxSocioCategoriaSocio.getSelectedItem());

                            if (!cs.isEmpty()) {
                                ep.setIdCategoriaSocio(cs.get(0));
                            }

                            if (e != null) {
                                p.setIdEndereco(e);

                                ep.setIdEndereco(e);
                                ep.setIdPessoa(p);
                                ep.setNumero(jTextFieldSocioNumero.getText());
                                List<EnderecoPessoa> enderecos1 = new ArrayList<>();
                                enderecos1.add(ep);
                                p.setEnderecopessoaList(enderecos1);

                            }
                            Socio s = new Socio();
                            if (jRadioButtonSocioAtivo.isSelected()) {
                                p.setStatus(true);
                                s.setDataAprovacao(new Date());
                            } else {
                                p.setStatus(false);
                            }

                            s.setIdCategoriaSocio(cs.get(0));
                            s.setDataVence(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldSocioDiaVencimento.getText()));
                            s.setDataMatricula(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldSocioDataMatricula.getText()));
                            s.setNumeroSocio((Integer.parseInt(jTextFieldSocioNumeroSocio.getText())));

                            //O socio usa um Carne de Agua ou de Socio??
                            s.setSocioExclusivo(jCheckBoxExclusivamenteSocio.isSelected());//carne de Socio

                            s.setIdPessoa(p);
                            p.setSocio(s);

                            daoPessoa.AlterarPessoa(p);

                            jButtonSocioCancelarActionPerformed(evt);
                            JOptionPane.showMessageDialog(this, "Dados gravados com Sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception e) {

                            JOptionPane.showMessageDialog(this, "Erro:" + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();

                        }

                    }

                } else {
                    int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja gravar os dados?", "Salvar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (op == JOptionPane.YES_OPTION) {

                        try {
                            Pessoa p = new Pessoa();

                            p.setNome(jTextFieldSocioNome.getText());
                            p.setSobrenome(jTextFieldSocioSobrenome.getText());
                            p.setApelido(jTextFieldSocioApelido.getText());
                            p.setTelefone(jTextFieldSocioTelefone.getText());
                            p.setEmail(jTextFieldSocioEmail.getText());
                            p.setNomeMae(jTextFieldSocioNomeMae.getText());
                            p.setNomePai(jTextFieldSocioNomePai.getText());
                            p.setRgNumero(jTextFieldSocioRgNumero.getText());
                            p.setRgExpedidor(jTextFieldSocioOrgaoExpedidor.getText());

                            if (jFormattedCNPJ.isEditable()) {
                                //aqui estamos editando
                                if (flagEditar == false) {
                                    if (daoEnderecoPessoa.BuscaCnpj(jFormattedCNPJ.getText())) {
                                        throw new Exception("Número de Endereço já cadastrado: erro 1");
                                    }
                                } //aqui estamos salvando
                                else {
                                    if (!daoEnderecoPessoa.EnderecopessoaporNumero(jTextFieldSocioNumero.getText()).equals(socioNumero)) {
                                        if (daoEnderecoPessoa.EnderecopessoaporNumero(jTextFieldSocioNumero.getText()) != null) {
                                            throw new Exception("Número de Endereço já cadastrado: erro 2");
                                        }
                                    }
                                }
                            } else {
                                if (flagEditar == false) {
                                    if (daoEnderecoPessoa.EnderecopessoaporNumero(jTextFieldSocioNumero.getText()) != null) {
                                        throw new Exception("Número de Endereço já cadastrado: ERRO 3");
                                    }
                                } else {
                                    if (!daoEnderecoPessoa.EnderecopessoaporNumero(jTextFieldSocioNumero.getText()).equals(socioNumero)) {
                                        if (daoEnderecoPessoa.EnderecopessoaporNumero(jTextFieldSocioNumero.getText()) != null) {
                                            throw new Exception("Número de Endereço já cadastrado 2");
                                        }
                                    }
                                }
                            }

                            p.setNumeroEndereco(jTextFieldSocioNumero.getText());
                            p.setCidade(jTextFieldSocioCep2.getText());
                            p.setBairro(jTextFieldSocioBairro.getText());
                            p.setCep(jTextFieldSocioCEP.getText());

                            if (p1.matcher(jFormattedTextFieldSocioDataNascimento.getText()).find()) {

                                p.setDataNasc(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldSocioDataNascimento.getText()));
                            }

                            Pessoa p0 = daoPessoa.BuscarPessoaCpf(jFormattedTextFieldSocioCPF.getText());
                            if (p0 != null) {
                                JOptionPane.showMessageDialog(this, "O cpf informado já existe", "Erro", JOptionPane.ERROR_MESSAGE);
                                jFormattedTextFieldSocioCPF.setText("");
                                throw new Exception();
                            }
                            p.setCpf(jFormattedTextFieldSocioCPF.getText());
                            if (p1.matcher(jFormattedTextFieldSocioDataEmissao.getText()).find()) {

                                p.setRgEmissao(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldSocioDataEmissao.getText()));
                            }
                            p.setSexo((String) jComboBoxSocioSexo.getSelectedItem());
                            p.setUf((String) jComboBoxSocioUF.getSelectedItem());

                            EnderecoPessoa ep = new EnderecoPessoa();
                            Endereco e = daoEndereco.BuscarEnderecoCompleto((String) jComboBoxSocioLogradouro.getSelectedItem());
                            List<CategoriaSocio> cs = new DaoCategoriaSocio().BuscarCategoriaPorNomeLike((String) jComboBoxSocioCategoriaSocio.getSelectedItem());
                            if (!cs.isEmpty()) {

                                ep.setIdCategoriaSocio(cs.get(0));
                            }
                            if (e != null) {
                                p.setIdEndereco(e);

                                ep.setIdEndereco(e);
                                ep.setIdPessoa(p);
                                ep.setNumero(jTextFieldSocioNumero.getText());
                                List<EnderecoPessoa> enderecos1 = new ArrayList<>();
                                enderecos1.add(ep);
                                p.setEnderecopessoaList(enderecos1);

                            }
                            Socio s = new Socio();
                            if (jRadioButtonSocioAtivo.isSelected()) {

                                p.setStatus(true);
                                s.setDataAprovacao(new Date());
                            } else {

                                p.setStatus(false);
                            }

                            s.setDataVence(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldSocioDiaVencimento.getText()));
                            s.setDataMatricula(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldSocioDataMatricula.getText()));
                            s.setNumeroSocio(Integer.parseInt(jTextFieldSocioNumeroSocio.getText()));
                            s.setIdCategoriaSocio(cs.get(0));
                            s.setIdPessoa(p);
                            //O socio usa um Carne de Agua ou de Socio??
                            if (jCheckBoxExclusivamenteSocio.isSelected()) {
                                s.setSocioExclusivo(true);//carne de Socio
                            } else {
                                s.setSocioExclusivo(false);//Carne de Agua
                            }

                            p.setSocio(s);

                            daoPessoa.AdicionarPessoa(p);

                            jButtonSocioCancelarActionPerformed(evt);
                            JOptionPane.showMessageDialog(this, "Dados gravados com Sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception e) {

                            JOptionPane.showMessageDialog(this, "Erro:" + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();

                        }
                    }
                }
            } else {
                int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja gravar a edição desse registro?", "Salvar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    try {

                        Socio s = daoSocio.BuscarSocioId(Integer.parseInt(jTextFieldSocioID.getText()));
                        Pessoa p = s.getIdPessoa();

                        p.setNome(jTextFieldSocioNome.getText());
                        p.setSobrenome(jTextFieldSocioSobrenome.getText());
                        p.setApelido(jTextFieldSocioApelido.getText());
                        p.setTelefone(jTextFieldSocioTelefone.getText());
                        p.setEmail(jTextFieldSocioEmail.getText());
                        p.setNomeMae(jTextFieldSocioNomeMae.getText());
                        p.setNomePai(jTextFieldSocioNomePai.getText());
                        p.setRgNumero(jTextFieldSocioRgNumero.getText());

                        p.setRgExpedidor(jTextFieldSocioOrgaoExpedidor.getText());

                        if ((flagEditar == true) && (socioNumero.equals(jTextFieldSocioNumero.getText()))) {
                        } else {

                            if (daoEnderecoPessoa.EnderecopessoaporNumero(jTextFieldSocioNumero.getText()) != null) {
                                JOptionPane.showMessageDialog(null, "ja existe");
                                throw new Exception("Número de Endereço já cadastrado: Erro 3");
                            }

                        }

                        p.setNumeroEndereco(jTextFieldSocioNumero.getText());
                        p.setCidade(jTextFieldSocioCep2.getText());
                        p.setBairro(jTextFieldSocioBairro.getText());
                        p.setCep(jTextFieldSocioCEP.getText());

                        if (p1.matcher(jFormattedTextFieldSocioDataNascimento.getText()).find()) {

                            p.setDataNasc(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldSocioDataNascimento.getText()));
                        }

                        if (!p.getCpf().equals(jFormattedTextFieldSocioCPF.getText())) {

                            Pessoa p0 = daoPessoa.BuscarPessoaCpf(jFormattedTextFieldSocioCPF.getText());
                            if (p0 != null) {
                                JOptionPane.showMessageDialog(this, "Esse cpf já existe", "Erro", JOptionPane.ERROR_MESSAGE);
                                jFormattedTextFieldSocioCPF.setText("");

                                throw new Exception();

                            }

                        }
                        p.setCpf(jFormattedTextFieldSocioCPF.getText());
                        if (p1.matcher(jFormattedTextFieldSocioDataEmissao.getText()).find()) {

                            p.setRgEmissao(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldSocioDataEmissao.getText()));
                        }
                        p.setSexo((String) jComboBoxSocioSexo.getSelectedItem());
                        p.setUf((String) jComboBoxSocioUF.getSelectedItem());

                        //Enderecopessoa ep = new Enderecopessoa();
                        Endereco e = daoEndereco.BuscarEnderecoCompleto((String) jComboBoxSocioLogradouro.getSelectedItem());
                        List<CategoriaSocio> cs = new DaoCategoriaSocio().BuscarCategoriaPorNomeLike((String) jComboBoxSocioCategoriaSocio.getSelectedItem());
                        p.getEnderecopessoaList().get(0).setIdCategoriaSocio(cs.get(0));
                        if (e != null) {
                            if (p.getIdEndereco() == e) {
                                p.setIdEndereco(e);
                            } else {

                                p.setIdEndereco(e);
                                p.getEnderecopessoaList().get(0).setIdEndereco(e);
                                p.getEnderecopessoaList().get(0).setNumero(p.getNumeroEndereco());

                            }

                        }

                        if (jRadioButtonSocioAtivo.isSelected()) {

                            p.setStatus(true);
                            if (p.getSocio().getDataAprovacao() == null) {

                                p.getSocio().setDataAprovacao(new Date());
                            }
                        } else {
                            p.getSocio().setDataAprovacao(null);
                            p.setStatus(false);
                        }
                        p.getSocio().setDataVence(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldSocioDiaVencimento.getText()));
                        p.getSocio().setDataMatricula(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldSocioDataMatricula.getText()));
                        p.getSocio().setNumeroSocio(Integer.parseInt((jTextFieldSocioNumeroSocio.getText())));
                        p.getSocio().setIdCategoriaSocio(cs.get(0));

                        //O socio usa um Carne de Agua ou de Socio??
                        if (jCheckBoxExclusivamenteSocio.isSelected()) {
                            p.getSocio().setSocioExclusivo(true);//carne de Socio
                        } else {
                            p.getSocio().setSocioExclusivo(false);//Carne de Agua
                        }
                        daoPessoa.AlterarPessoa(p);

                        jButtonSocioCancelarActionPerformed(evt);
                        JOptionPane.showMessageDialog(this, "Dados alterados com Sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(this, "Erro :" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                }

            }

        }

    }//GEN-LAST:event_jButtonSocioSalvarActionPerformed

    private void jButtonSocioApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSocioApagarActionPerformed

        int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o registro?", "Apagar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {

            try {

                Socio s = daoSocio.BuscarSocioId(Integer.parseInt(jTextFieldSocioID.getText()));
                Pessoa p = s.getIdPessoa();

                daoPessoa.ApagarPessoa(p);
                JOptionPane.showMessageDialog(this, "Registro excluido com sucesso", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                jButtonSocioCancelarActionPerformed(evt);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, "Erro ao excluir o registro", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButtonSocioApagarActionPerformed

    private void jButtonCategoriaSocioNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCategoriaSocioNovoActionPerformed

        setEditableComponentesCategoriaSocio(true);
        jButtonCategoriaSocioNovo.setEnabled(false);
        jButtonCategoriaSocioApagar.setEnabled(false);
        jButtonCategoriaSocioEditar.setEnabled(false);
        limparCamposCategoriaSocio();

        if (jComboBoxCategoriaSocioTaxa.getItemCount() == 0) {

            jComboBoxCategoriaSocioTaxa.addItem("");
            List<Taxa> taxas = daoTaxa.TaxasTodas();
            for (Taxa t : taxas) {

                jComboBoxCategoriaSocioTaxa.addItem(t.getNome());
            }

        }
        jComboBoxCategoriaSocioTaxa.setSelectedItem("");

    }//GEN-LAST:event_jButtonCategoriaSocioNovoActionPerformed

    private void jButtonCategoriaSocioCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCategoriaSocioCancelarActionPerformed

        setEditableComponentesCategoriaSocio(false);
        limparCamposCategoriaSocio();
        jButtonCategoriaSocioNovo.setEnabled(true);


    }//GEN-LAST:event_jButtonCategoriaSocioCancelarActionPerformed

    private void jButtonCategoriaSocioPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCategoriaSocioPesquisarActionPerformed
        if (jButtonCategoriaSocioNovo.isEnabled()) {
            pesquisarTable = "categoria socio";
            mostrarJtable();

        } else {

            JOptionPane.showMessageDialog(this, "Cancele a operação para fazer uma pesquisa", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCategoriaSocioPesquisarActionPerformed

    private void jButtonCategoriaSocioEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCategoriaSocioEditarActionPerformed

        setEditableComponentesCategoriaSocio(true);
        jButtonCategoriaSocioApagar.setEnabled(false);
        jButtonCategoriaSocioEditar.setEnabled(false);

    }//GEN-LAST:event_jButtonCategoriaSocioEditarActionPerformed

    private void jButtonCategoriaSocioSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCategoriaSocioSalvarActionPerformed

        if (isCamposCategoriaSocioPreenchidos()) {

            if (jTextFieldCategoriaSocioID.getText().equals("")) {
                int op = JOptionPane.showConfirmDialog(this, "Deseja gravar esses dados?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {
                    try {
                        CategoriaSocio c = new CategoriaSocio();
                        c.setNome(jTextFieldCategoriaSocioNome.getText());
                        List<Taxa> taxas = daoTaxa.BuscarTaxaNomeLike((String) jComboBoxCategoriaSocioTaxa.getSelectedItem());
                        if (!taxas.isEmpty()) {

                            c.setTaxasId(taxas.get(0));
                        }

                        c.setDescricao(jTextAreaCategoriaSocioDescricao.getText());

                        new DaoCategoriaSocio().AdicionarCategoria(c);
                        JOptionPane.showMessageDialog(this, "Dados gravados com sucesso!");
                        jButtonCategoriaSocioCancelarActionPerformed(evt);
                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(this, "Erro ao tentar gravar os dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } else {
                int op = JOptionPane.showConfirmDialog(this, "Deseja gravar a edição desse registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    try {

                        CategoriaSocio c = new DaoCategoriaSocio().BuscarCategoriaSocioPorId(Integer.parseInt(jTextFieldCategoriaSocioID.getText()));
                        c.setNome(jTextFieldCategoriaSocioNome.getText());
                        List<Taxa> taxas = daoTaxa.BuscarTaxaNomeLike((String) jComboBoxCategoriaSocioTaxa.getSelectedItem());
                        if (!taxas.isEmpty()) {

                            c.setTaxasId(taxas.get(0));
                        }

                        c.setDescricao(jTextAreaCategoriaSocioDescricao.getText());

                        new DaoCategoriaSocio().AtualizarCategoria(c);
                        JOptionPane.showMessageDialog(this, "Dados alterados com sucesso!");
                        jButtonCategoriaSocioCancelarActionPerformed(evt);

                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(this, "Erro ao tentar gravar os dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }
        }
    }//GEN-LAST:event_jButtonCategoriaSocioSalvarActionPerformed

    private void jButtonTipoDespesaNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoDespesaNovoActionPerformed

        limparCamposTipoDespesa();
        setEditableComponentesTipoDespesa(true);
        jButtonTipoDespesaNovo.setEnabled(false);
        jButtonTipoDespesaApagar.setEnabled(false);
        jButtonTipoDespesaEditar.setEnabled(false);


    }//GEN-LAST:event_jButtonTipoDespesaNovoActionPerformed

    private void jButtonTipoDespesaCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoDespesaCancelarActionPerformed

        limparCamposTipoDespesa();
        setEditableComponentesTipoDespesa(false);
        jButtonTipoDespesaNovo.setEnabled(true);

    }//GEN-LAST:event_jButtonTipoDespesaCancelarActionPerformed

    private void jButtonTipoDespesaEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoDespesaEditarActionPerformed

        setEditableComponentesTipoDespesa(true);
        jButtonTipoDespesaEditar.setEnabled(false);
        jButtonTipoDespesaApagar.setEnabled(false);

    }//GEN-LAST:event_jButtonTipoDespesaEditarActionPerformed

    private void jButtonTipoDespesaPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoDespesaPesquisarActionPerformed

        if (jButtonTipoDespesaNovo.isEnabled()) {

            pesquisarTable = "tipo despesa";
            mostrarJtable();

        } else {

            JOptionPane.showMessageDialog(this, "Cancele a operação para fazer uma pesquisa", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonTipoDespesaPesquisarActionPerformed

    private void jButtonTipoDespesaSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoDespesaSalvarActionPerformed

        if (isCamposTipoDespesaPreenchidos()) {

            if (jTextFieldTIpoDespesaID.getText().equals("")) {

                int op = JOptionPane.showConfirmDialog(this, "Confirma a gravação dos dados?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    try {
                        Motivodespesa md = new Motivodespesa();

                        md.setNome(jTextFieldTipoDespesaNome.getText());
                        md.setDescricao(jTextAreaTipoDespesaDescricao.getText());
                        md.setObservacao(jTextAreaTipoDespesaObservacao.getText());

                        daoMotivoDespesa.AdicionarMotivoDespesa(md);

                        jButtonTipoDespesaCancelarActionPerformed(evt);
                        JOptionPane.showMessageDialog(this, "Registro gravado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(this, "Erro para gravar os dados", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                }

            } else {

                int op = JOptionPane.showConfirmDialog(this, "Confirma a gravação da edição desse registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    try {

                        Motivodespesa md = daoMotivoDespesa.BuscarMotivoDespesaId(Integer.parseInt(jTextFieldTIpoDespesaID.getText()));

                        md.setNome(jTextFieldTipoDespesaNome.getText());
                        md.setDescricao(jTextAreaTipoDespesaDescricao.getText());
                        md.setObservacao(jTextAreaTipoDespesaObservacao.getText());

                        daoMotivoDespesa.AlterarMotivoDespesa(md);

                        jButtonTipoDespesaCancelarActionPerformed(evt);
                        JOptionPane.showMessageDialog(this, "Registro gravado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(this, "Erro para gravar os dados", "Erro", JOptionPane.ERROR_MESSAGE);

                    }

                }
            }

        }
    }//GEN-LAST:event_jButtonTipoDespesaSalvarActionPerformed

    private void jButtonTipoDespesaApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoDespesaApagarActionPerformed

        int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esse registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {

            try {

                Motivodespesa md = daoMotivoDespesa.BuscarMotivoDespesaId(Integer.parseInt(jTextFieldTIpoDespesaID.getText()));

                daoMotivoDespesa.ApagarMotivoDespesa(md);

                JOptionPane.showMessageDialog(this, "Registro excluido com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                jButtonTipoDespesaCancelarActionPerformed(evt);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, "Erro ao tentar excluir o registro", "Erro", JOptionPane.ERROR_MESSAGE);

            }

        }

    }//GEN-LAST:event_jButtonTipoDespesaApagarActionPerformed

    private void jButtonDespesaNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDespesaNovoActionPerformed

        limparCamposDespesa();

        setEditableComponentesDespesa(true);
        jButtonDespesaNovo.setEnabled(false);
        jButtonDespesaApagar.setEnabled(false);
        jButtonDespesaEditar.setEnabled(false);
        jComboBoxDespesaFuncionario.setSelectedIndex(0);

        if (jComboBoxDespesaMotivo.getItemCount() == 0) {
            jComboBoxDespesaMotivo.addItem("");
            List<Motivodespesa> motivoDespesa = daoMotivoDespesa.BuscarTodosMotivos();

            for (Motivodespesa m : motivoDespesa) {

                jComboBoxDespesaMotivo.addItem(m.getNome());
            }
        }
        jComboBoxDespesaMotivo.setSelectedIndex(0);

    }//GEN-LAST:event_jButtonDespesaNovoActionPerformed

    private void jButtonDespesaCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDespesaCancelarActionPerformed

        limparCamposDespesa();
        setEditableComponentesDespesa(false);
        jButtonDespesaNovo.setEnabled(true);

    }//GEN-LAST:event_jButtonDespesaCancelarActionPerformed

    private void jButtonDespesaEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDespesaEditarActionPerformed

        setEditableComponentesDespesa(true);
        jButtonDespesaEditar.setEnabled(false);
        jButtonDespesaApagar.setEnabled(false);


    }//GEN-LAST:event_jButtonDespesaEditarActionPerformed

    private void jButtonDespesaPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDespesaPesquisarActionPerformed

        if (jButtonDespesaNovo.isEnabled()) {

            pesquisarTable = "despesa";
            mostrarJtable();

        } else {

            JOptionPane.showMessageDialog(this, "Cancele a operação antes de realizar uma pesquisa", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButtonDespesaPesquisarActionPerformed

    private void jButtonDespesaSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDespesaSalvarActionPerformed

        if (isCamposDespesaPreenchidos()) {

            if (jTextFieldDespesaID.getText().equals("")) {

                int op = JOptionPane.showConfirmDialog(this, "Confirma gravação do registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    try {

                        Saida s = new Saida();
                        s.setFavorecido(jTextFieldDespesaFavorecido.getText());
                        s.setValor(BigDecimal.valueOf(Double.parseDouble(jTextFieldDespesaValor.getText())));

                        s.setData(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldDespesaData.getText()));

                        Pessoa p1 = daoPessoa.BuscarNomeCompleto((String) jComboBoxDespesaFuncionario.getSelectedItem());
                        Funcionario f = p1.getFuncionario();
                        s.setIdfuncionario(f);

                        List<Motivodespesa> motivos = daoMotivoDespesa.BuscarMotivoDespesaLikeNome((String) jComboBoxDespesaMotivo.getSelectedItem());
                        s.setIdmotivosaida(motivos.get(0));
                        s.setObservacao(jTextAreaDespesaObservacao.getText());

                        daoSaidas.AdicionarSaida(s);

                        JOptionPane.showMessageDialog(this, "Dados gravados com sucesso", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        jButtonDespesaCancelarActionPerformed(evt);

                    } catch (Exception e) {
                        //04
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Erro ao gravar o registro 2", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                }

            } else {
                int op = JOptionPane.showConfirmDialog(this, "Confirma a gravação da edição desse registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    try {

                        Saida s = daoSaidas.BuscarSaidaId(Integer.parseInt(jTextFieldDespesaID.getText()));
                        s.setFavorecido(jTextFieldDespesaFavorecido.getText());
                        s.setValor(BigDecimal.valueOf(Double.parseDouble(jTextFieldDespesaValor.getText())));

                        s.setData(SimpleDateFormat.getDateInstance().parse(jFormattedTextFieldDespesaData.getText()));

                        Pessoa p1 = daoPessoa.BuscarNomeCompleto((String) jComboBoxDespesaFuncionario.getSelectedItem());
                        Funcionario f = p1.getFuncionario();
                        s.setIdfuncionario(f);

                        List<Motivodespesa> motivos = daoMotivoDespesa.BuscarMotivoDespesaLikeNome((String) jComboBoxDespesaMotivo.getSelectedItem());
                        s.setIdmotivosaida(motivos.get(0));
                        s.setObservacao(jTextAreaDespesaObservacao.getText());

                        daoSaidas.AlterarSaida(s);

                        JOptionPane.showMessageDialog(this, "Dados alterados com sucesso", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        jButtonDespesaCancelarActionPerformed(evt);

                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(this, "Erro ao alterar o registro", "Erro", JOptionPane.ERROR_MESSAGE);

                    }

                }

            }

        }


    }//GEN-LAST:event_jButtonDespesaSalvarActionPerformed

    private void jButtonDespesaApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDespesaApagarActionPerformed

        int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esse registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {

            try {
                Saida s = daoSaidas.BuscarSaidaId(Integer.parseInt(jTextFieldDespesaID.getText()));

                daoSaidas.ApagarSaida(s);
                JOptionPane.showMessageDialog(this, "Registro excluido com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                jButtonDespesaCancelarActionPerformed(evt);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, "Erro ao tentar excluir o registro", "Erro", JOptionPane.ERROR_MESSAGE);

            }
        }


    }//GEN-LAST:event_jButtonDespesaApagarActionPerformed

    private void jButtonTipoReceitaNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoReceitaNovoActionPerformed

        limparCamposTipoReceita();

        setEditableComponentesTipoReceita(true);
        jButtonTipoReceitaNovo.setEnabled(false);
        jButtonTipoReceitaApagar.setEnabled(false);
        jButtonTipoReceitaEditar.setEnabled(false);

    }//GEN-LAST:event_jButtonTipoReceitaNovoActionPerformed

    private void jButtonTipoReceitaCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoReceitaCancelarActionPerformed

        limparCamposTipoReceita();
        setEditableComponentesTipoReceita(false);
        jButtonTipoReceitaNovo.setEnabled(true);

    }//GEN-LAST:event_jButtonTipoReceitaCancelarActionPerformed

    private void jButtonTipoReceitaEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoReceitaEditarActionPerformed

        setEditableComponentesTipoReceita(true);
        jButtonTipoReceitaApagar.setEnabled(false);
        jButtonTipoReceitaEditar.setEnabled(false);


    }//GEN-LAST:event_jButtonTipoReceitaEditarActionPerformed

    private void jButtonTipoReceitaPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoReceitaPesquisarActionPerformed

        if (jButtonTipoReceitaNovo.isEnabled()) {

            pesquisarTable = "tipo receita";
            mostrarJtable();

        } else {

            JOptionPane.showMessageDialog(this, "Cancele a operação antes de realizar uma pesquisa", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButtonTipoReceitaPesquisarActionPerformed

    private void jButtonTipoReceitaSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoReceitaSalvarActionPerformed

        if (isCamposTipoReceitaPreenchidos()) {

            if (jTextFieldTIpoReceitaID.getText().equals("")) {
                int op = JOptionPane.showConfirmDialog(this, "Confirma a gravação desse registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {
                    try {
                        Motivoentrada motivo = new Motivoentrada();

                        motivo.setNome(jTextFieldTipoReceitaNome.getText());
                        motivo.setDescricao(jTextAreaTipoReceitaDescricao.getText());
                        motivo.setObservacao(jTextAreaTipoReceitaObservacao.getText());

                        new DaoMotivoEntrada().AdicionarMotivoEntrada(motivo);
                        JOptionPane.showMessageDialog(this, "Registro gravado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        jButtonTipoReceitaCancelarActionPerformed(evt);

                    } catch (Exception e) {
                        //01
                        JOptionPane.showMessageDialog(this, "Erro ao gravar o registro 4", "Erro", JOptionPane.ERROR_MESSAGE);

                    }

                }

            } else {

                int op = JOptionPane.showConfirmDialog(this, "Confirma a alteração desse registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    try {
                        Motivoentrada motivo = new DaoMotivoEntrada().BuscarMotivoEntradaId(Integer.parseInt(jTextFieldTIpoReceitaID.getText()));

                        motivo.setNome(jTextFieldTipoReceitaNome.getText());
                        motivo.setDescricao(jTextAreaTipoReceitaDescricao.getText());
                        motivo.setObservacao(jTextAreaTipoReceitaObservacao.getText());

                        new DaoMotivoEntrada().AlterarMotivoEntrada(motivo);
                        JOptionPane.showMessageDialog(this, "Registro alterado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        jButtonTipoReceitaCancelarActionPerformed(evt);

                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(this, "Erro ao alterar o registro", "Erro", JOptionPane.ERROR_MESSAGE);

                    }

                }

            }

        }

    }//GEN-LAST:event_jButtonTipoReceitaSalvarActionPerformed

    private void jButtonTipoReceitaApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoReceitaApagarActionPerformed

        int op = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esse registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {

            try {
                Motivoentrada m = new DaoMotivoEntrada().BuscarMotivoEntradaId(Integer.parseInt(jTextFieldTIpoReceitaID.getText()));

                new DaoMotivoEntrada().ApagarMotivoEntrada(m);
                JOptionPane.showMessageDialog(this, "Registro excluido com sucesso", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                jButtonTipoReceitaCancelarActionPerformed(evt);

            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir o registro", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }


    }//GEN-LAST:event_jButtonTipoReceitaApagarActionPerformed

    private void jButtonReceitaNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReceitaNovoActionPerformed

        limparCamposReceita();

        jCheckBoxReceitaAvulsa.setEnabled(true);
        setEditableComponentesReceita(true);
        jButtonReceitaNovo.setEnabled(false);
        jButtonReceitaApagar.setEnabled(false);
        jButtonReceitaEditar.setEnabled(false);

        if (jComboBoxReceitaMotivoEntrada.getItemCount() == 0) {

            jComboBoxReceitaMotivoEntrada.addItem("");

            List<Motivoentrada> motivos = new DaoMotivoEntrada().BuscarTodosMotivos();
            for (Motivoentrada m : motivos) {

                jComboBoxReceitaMotivoEntrada.addItem(m.getNome());

            }

        }

        if (jComboBoxReceitaSocio.getItemCount() == 0) {

            jComboBoxReceitaSocio.addItem("");

            List<SociosView> socios = socioView.BuscarTodosSociosView();

            for (SociosView s : socios) {

                jComboBoxReceitaSocio.addItem(s.getNome());

            }

        }


    }//GEN-LAST:event_jButtonReceitaNovoActionPerformed

    private void jButtonReceitaCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReceitaCancelarActionPerformed

        limparCamposReceita();
        setEditableComponentesReceita(false);
        jButtonReceitaNovo.setEnabled(true);
    }//GEN-LAST:event_jButtonReceitaCancelarActionPerformed

    private void jButtonReceitaEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReceitaEditarActionPerformed

        setEditableComponentesReceita(true);
        jButtonReceitaApagar.setEnabled(false);
        jButtonReceitaEditar.setEnabled(false);
    }//GEN-LAST:event_jButtonReceitaEditarActionPerformed

    private void jButtonSocioAdicionarLogradouroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSocioAdicionarLogradouroActionPerformed

        Socio s = daoSocio.BuscarSocioId(Integer.parseInt(jTextFieldSocioID.getText()));
        Pessoa p = s.getIdPessoa();
        List<EnderecoPessoa> enderecos = p.getEnderecopessoaList();

        TelaTabelaEnderecos l = new TelaTabelaEnderecos(enderecos);
        // Logradouros l = new Logradouros(this, true, enderecos, s);
        l.setLocationRelativeTo(null);

        l.setVisible(true);


    }//GEN-LAST:event_jButtonSocioAdicionarLogradouroActionPerformed

    private void jButtonReceitaPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReceitaPesquisarActionPerformed

        if (jButtonReceitaNovo.isEnabled()) {

            pesquisarTable = "receita";
            mostrarJtable();

        } else {

            JOptionPane.showMessageDialog(this, "Cancele a operação antes de realizar uma pesquisa", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButtonReceitaPesquisarActionPerformed

    private void jButtonReceitaSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReceitaSalvarActionPerformed

        if (isCamposReceitaPreenchidos()) {

            if (jTextFieldReceitaID.getText().equals("")) {
                int op = JOptionPane.showConfirmDialog(this, "Confirma a gravação desse registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    try {
                        Entrada e = new Entrada();

                        e.setData(jDateChooser1.getDate());
                        e.setValor(BigDecimal.valueOf(Double.parseDouble(jTextFieldReceitaValor.getText())));
                        e.setObservacao(jTextAreaReceitaObservacao.getText());

                        Pessoa p = daoPessoa.BuscarNomeCompleto((String) jComboBoxReceitaFuncionario.getSelectedItem());
                        e.setIdFuncionario(p.getFuncionario());

                        p = daoPessoa.BuscarNomeCompleto((String) jComboBoxReceitaSocio.getSelectedItem());
                        e.setIdCedente(p.getSocio());

                        List<Motivoentrada> entrada = new DaoMotivoEntrada().BuscarMotivoEntradaLikeNome((String) jComboBoxReceitaMotivoEntrada.getSelectedItem());
                        e.setIdMotivoEntrada(entrada.get(0));

                        daoEntradas.AdicionarEntrada(e);

                        JOptionPane.showMessageDialog(this, "Registro gravado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        jButtonReceitaCancelarActionPerformed(evt);
                    } catch (NumberFormatException | HeadlessException e) {
                        JOptionPane.showMessageDialog(this, "Erro ao gravar o registro 3", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } else {
                int op = JOptionPane.showConfirmDialog(this, "Confirma a alteração desse registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {

                    try {

                        Entrada e = daoEntradas.EntradaPorId(Integer.parseInt(jTextFieldReceitaID.getText()));

                        e.setData(jDateChooser1.getDate());
                        e.setValor(BigDecimal.valueOf(Double.parseDouble(jTextFieldReceitaValor.getText())));
                        e.setObservacao(jTextAreaReceitaObservacao.getText());

                        Pessoa p = daoPessoa.BuscarNomeCompleto((String) jComboBoxReceitaFuncionario.getSelectedItem());
                        e.setIdFuncionario(p.getFuncionario());

                        p = daoPessoa.BuscarNomeCompleto((String) jComboBoxReceitaSocio.getSelectedItem());
                        if (p.getSocio() != null) {
                            e.setIdCedente(p.getSocio());
                        }

                        List<Motivoentrada> entrada = new DaoMotivoEntrada().BuscarMotivoEntradaLikeNome((String) jComboBoxReceitaMotivoEntrada.getSelectedItem());
                        e.setIdMotivoEntrada(entrada.get(0));

                        daoEntradas.AlterarEntrada(e);
                        JOptionPane.showMessageDialog(this, "Registro alterado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        jButtonReceitaCancelarActionPerformed(evt);
                    } catch (HeadlessException | NumberFormatException e) {

                        JOptionPane.showMessageDialog(this, "Erro ao alterar o registro", "Erro", JOptionPane.ERROR_MESSAGE);

                    }

                }

            }

        }

    }//GEN-LAST:event_jButtonReceitaSalvarActionPerformed

    private void jButtonReceitaApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReceitaApagarActionPerformed

        int op = JOptionPane.showConfirmDialog(this, "1 Tem certeza que deseja excluir esse registro?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {

            try {

                Entrada e = daoEntradas.EntradaPorId(Integer.parseInt(jTextFieldReceitaID.getText()));

                daoEntradas.ApagarEntrada(e);

                JOptionPane.showMessageDialog(this, "Registro excluido com sucesso", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                jButtonReceitaCancelarActionPerformed(evt);
            } catch (NumberFormatException | HeadlessException e) {

                JOptionPane.showMessageDialog(this, "Erro ao excluir o registro", "Erro", JOptionPane.ERROR_MESSAGE);

            }

        }

    }//GEN-LAST:event_jButtonReceitaApagarActionPerformed

    private void jButtonChequeNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChequeNovoActionPerformed

        limparCamposCheques();

        setEditableComponentesCheques(true);
        jButtonChequeNovo.setEnabled(false);
        jButtonChequeApagar.setEnabled(false);
        jButtonChequeEditar.setEnabled(false);

        if (jComboBoxChequeMotivoDespesa.getItemCount() == 0) {

            jComboBoxChequeMotivoDespesa.addItem("");
            List<Motivodespesa> motivos = daoMotivoDespesa.BuscarTodosMotivos();

            if (!motivos.isEmpty()) {

                for (Motivodespesa m : motivos) {

                    jComboBoxChequeMotivoDespesa.addItem(m.getNome());
                }

            }

        }

    }//GEN-LAST:event_jButtonChequeNovoActionPerformed

    private void jButtonChequeEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChequeEditarActionPerformed

        setEditableComponentesCheques(true);
        jButtonChequeEditar.setEnabled(false);
        jButtonChequeApagar.setEnabled(false);

    }//GEN-LAST:event_jButtonChequeEditarActionPerformed

    private void jButtonChequeCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChequeCancelarActionPerformed

        limparCamposCheques();

        setEditableComponentesCheques(false);
        jButtonChequeNovo.setEnabled(true);

    }//GEN-LAST:event_jButtonChequeCancelarActionPerformed

    private void jButtonChequePesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChequePesquisarActionPerformed

        if (jButtonChequeNovo.isEnabled()) {

            pesquisarTable = "cheque";
            mostrarJtable();

        } else {

            JOptionPane.showMessageDialog(this, "Cancele a operação antes de realizar uma pesquisa", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_jButtonChequePesquisarActionPerformed

    private void jButtonChequeSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChequeSalvarActionPerformed


    }//GEN-LAST:event_jButtonChequeSalvarActionPerformed

    private void jButtonChequeApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChequeApagarActionPerformed

    }//GEN-LAST:event_jButtonChequeApagarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (jButtonSocioNovo.isEnabled()) {
            pesquisarTable = "importar funcionário";
            mostrarJtable();
            if (jComboBoxSocioLogradouro.getItemCount() == 0) {

                jComboBoxSocioLogradouro.addItem("");
                List<Endereco> end = daoEndereco.BuscarTodosEnderecos();
                for (Endereco e : end) {

                    jComboBoxSocioLogradouro.addItem(e.getTipo() + " " + e.getNome());

                }

            }
            if (jComboBoxSocioCategoriaSocio.getItemCount() == 0) {

                jComboBoxSocioCategoriaSocio.addItem("");
                List<CategoriaSocio> cs = new DaoCategoriaSocio().BuscarTodasCategorias();
                for (CategoriaSocio c : cs) {
                    jComboBoxSocioCategoriaSocio.addItem(c.getNome());

                }

            }

        } else {

            JOptionPane.showMessageDialog(this, "Cancele a operação para importar um funcionário", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxSocioUFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSocioUFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSocioUFActionPerformed

    private void jTextFieldSocioApelidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSocioApelidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSocioApelidoActionPerformed

    private void jFormattedTextFieldSocioDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldSocioDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldSocioDataNascimentoActionPerformed

    private void jTextFieldSocioIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSocioIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSocioIDActionPerformed

    private void jTextFieldSocioEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSocioEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSocioEmailActionPerformed

    private void jTextFieldFuncionarioSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFuncionarioSalarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFuncionarioSalarioActionPerformed

    private void jTextFieldFuncionarioRgnumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFuncionarioRgnumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFuncionarioRgnumeroActionPerformed

    private void jTextFieldReceitaIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldReceitaIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldReceitaIDActionPerformed

    private void jTextFieldChequeValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldChequeValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldChequeValorActionPerformed

    private void jTextFieldFuncionarioEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFuncionarioEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFuncionarioEmailActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.cadastrar = true;
        jButtonLogradouroCancelar.setEnabled(true);
        jTabbedPane1.setSelectedComponent(jPanelLog);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioCPFCNPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioCPFCNPJActionPerformed
        if (jRadioCPFCNPJ.isSelected()) {
            jFormattedCNPJ.setEditable(true);
            jFormattedTextFieldSocioCPF.setEditable(false);
            jFormattedTextFieldSocioCPF.setText(null);
        } else {
            jFormattedCNPJ.setEditable(false);
            jFormattedTextFieldSocioCPF.setEditable(true);
            jFormattedCNPJ.setText(null);
        }
    }//GEN-LAST:event_jRadioCPFCNPJActionPerformed

    private void jCheckBoxReceitaAvulsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxReceitaAvulsaActionPerformed
        if (jCheckBoxReceitaAvulsa.isSelected()) {
            jComboBoxReceitaSocio.setSelectedIndex(0);
            jComboBoxReceitaSocio.setEnabled(false);
        } else {
            jComboBoxReceitaSocio.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBoxReceitaAvulsaActionPerformed

    private void jTextFieldReceitaValorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldReceitaValorFocusLost

    }//GEN-LAST:event_jTextFieldReceitaValorFocusLost

    private void jTextFieldSocioCEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSocioCEPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSocioCEPActionPerformed

    private void jComboBoxSocioCategoriaSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSocioCategoriaSocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSocioCategoriaSocioActionPerformed

    private void editableTextFields(boolean editable) {

        jTextFieldFuncionarioApelido.setEditable(editable);
        jTextFieldFuncionarioBairro.setEditable(editable);
        jTextFieldFuncionarioCargo.setEditable(editable);
        jButton2.setEnabled(editable);
        jTextFieldFuncionarioCep.setEditable(editable);
        jTextFieldFuncionarioCidade.setEditable(editable);
        jFormattedTextFieldFuncionarioCpf.setEditable(editable);
        jFormattedTextFieldFuncionarioDataContratacao.setEditable(editable);
        jFormattedTextFieldFuncionarioDataNascimento.setEditable(editable);
        jFormattedTextFieldFuncionarioDataEmissao.setEditable(editable);
        jTextFieldFuncionarioEmail.setEditable(editable);
        jTextFieldFuncionarioNomedame.setEditable(editable);
        jTextFieldFuncionarioNomedopai.setEditable(editable);
        jTextFieldFuncionarioNumero.setEditable(editable);
        jTextFieldFuncionarioNumeroDaMatricula.setEditable(editable);
        jTextFieldFuncionarioOrgaoExpedidor.setEditable(editable);
        jTextFieldFuncionarioRgnumero.setEditable(editable);
        jTextFieldFuncionarioSalario.setEditable(editable);
        jTextFieldFuncionarioSobrenome.setEditable(editable);
        jTextFieldFuncionarioTelefone.setEditable(editable);
        jComboBoxFuncionarioLograduro.setEnabled(editable);
        jComboBoxFuncionarioSexo.setEnabled(editable);
        jComboBoxFuncionarioStatus.setEnabled(editable);
        jComboBoxFuncionarioUf.setEnabled(editable);
        jButtonFuncionarioApagar.setEnabled(editable);
        jButtonFuncionarioCancelar.setEnabled(editable);
        jButtonFuncionarioEditar.setEnabled(editable);
        jButtonFuncionarioSalvar.setEnabled(editable);

    }

    private void limparCampos() {

        jTextFieldFuncionarioID.setText("");
        jTextFieldFuncionarioNome.setText("");
        jTextFieldFuncionarioApelido.setText("");
        jTextFieldFuncionarioBairro.setText("");
        jTextFieldFuncionarioCargo.setText("");

        jTextFieldFuncionarioCep.setText("");
        jTextFieldFuncionarioCidade.setText("");
        jFormattedTextFieldFuncionarioCpf.setText("");
        jFormattedTextFieldFuncionarioDataContratacao.setText("");
        jFormattedTextFieldFuncionarioDataNascimento.setText("");
        jFormattedTextFieldFuncionarioDataEmissao.setText("");
        jTextFieldFuncionarioEmail.setText("");
        jTextFieldFuncionarioNomedame.setText("");
        jTextFieldFuncionarioNomedopai.setText("");
        jTextFieldFuncionarioNumero.setText("");
        jTextFieldFuncionarioNumeroDaMatricula.setText("");
        jTextFieldFuncionarioOrgaoExpedidor.setText("");
        jTextFieldFuncionarioRgnumero.setText("");
        jTextFieldFuncionarioSalario.setText("");
        jTextFieldFuncionarioSobrenome.setText("");
        jTextFieldFuncionarioTelefone.setText("");
        jTextAreaFuncionarioObservacoes.setText("");
        jComboBoxFuncionarioSexo.setSelectedIndex(0);
        jComboBoxFuncionarioStatus.setSelectedIndex(0);
        jComboBoxFuncionarioUf.setSelectedIndex(0);
        if (jComboBoxFuncionarioLograduro.getItemCount() != 0) {
            jComboBoxFuncionarioLograduro.setSelectedIndex(0);
        }

    }

    protected void preencherAbaFuncionarios(Pessoa pessoa) {

        jTextFieldFuncionarioNome.setText(pessoa.getNome());
        jTextFieldFuncionarioID.setText(String.valueOf(pessoa.getId()));
        jTextFieldFuncionarioSobrenome.setText(pessoa.getSobrenome());
        jTextFieldFuncionarioApelido.setText(pessoa.getApelido());
        jTextFieldFuncionarioTelefone.setText(pessoa.getTelefone());
        jTextFieldFuncionarioEmail.setText(pessoa.getEmail());
        if (pessoa.getDataNasc() != null) {
            jFormattedTextFieldFuncionarioDataNascimento.setText(SimpleDateFormat.getDateInstance().format(pessoa.getDataNasc()));
        }
        jComboBoxFuncionarioSexo.setSelectedItem(pessoa.getSexo());
        jTextFieldFuncionarioNomedame.setText(pessoa.getNomeMae());
        jTextFieldFuncionarioNomedopai.setText(pessoa.getNomePai());
        jFormattedTextFieldFuncionarioCpf.setText(pessoa.getCpf());
        jTextFieldFuncionarioRgnumero.setText(pessoa.getRgNumero());
        jTextFieldFuncionarioOrgaoExpedidor.setText(pessoa.getRgExpedidor());
        if (pessoa.getRgEmissao() != null) {
            jFormattedTextFieldFuncionarioDataEmissao.setText(SimpleDateFormat.getDateInstance().format(pessoa.getRgEmissao()));
        }
        if (jComboBoxFuncionarioLograduro.getItemCount() == 0) {
            jComboBoxFuncionarioLograduro.addItem("");
            List<Endereco> enderecos = daoEndereco.BuscarTodosEnderecos();
            for (Endereco endereco : enderecos) {

                jComboBoxFuncionarioLograduro.addItem(endereco.getTipo() + " " + endereco.getNome());

            }
        }
        jComboBoxFuncionarioLograduro.setSelectedItem(pessoa.getIdEndereco().getTipo() + " " + pessoa.getIdEndereco().getNome());
        jTextFieldFuncionarioNumero.setText(pessoa.getNumeroEndereco());
        jTextFieldFuncionarioCidade.setText(pessoa.getCidade());
        jTextFieldFuncionarioBairro.setText(pessoa.getBairro());
        jComboBoxFuncionarioUf.setSelectedItem(pessoa.getUf());
        jTextFieldFuncionarioCep.setText(pessoa.getCep());

        Funcionario f = pessoa.getFuncionario();

        jTextFieldFuncionarioCargo.setText(f.getCargo());
        // jTextFieldFuncionarioSalario.setText(NumberFormat.getCurrencyInstance().format(func.getSalario()));
        jTextFieldFuncionarioSalario.setText(String.valueOf(f.getSalario()));
        if (f.getDataContratacao() != null) {

            jFormattedTextFieldFuncionarioDataContratacao.setText(SimpleDateFormat.getDateInstance().format(f.getDataContratacao()));
        } else {
            jFormattedTextFieldFuncionarioDataContratacao.setText("nulo");
        }
        jTextAreaFuncionarioObservacoes.setText(f.getObservacao());
        jTextFieldFuncionarioNumeroDaMatricula.setText(String.valueOf(f.getMatricula()));

        if (pessoa.getStatus()) {
            jComboBoxFuncionarioStatus.setSelectedItem("Ativo");
        } else {
            jComboBoxFuncionarioStatus.setSelectedItem("Desativo");
        }

        // jTextFieldFuncionarioNumeroDaMatricula.setText(String.valueOf(pessoa.getNumeroMatricula()));
    }

    private boolean isCamposFuncionariosPreenchidos() {

        Pattern p = Pattern.compile("\\d{2}\\/\\d{2}\\/\\d{4}");
        Matcher m = p.matcher(jFormattedTextFieldFuncionarioDataContratacao.getText());

        if (!jTextFieldFuncionarioNome.getText().equals("") && !jTextFieldFuncionarioSobrenome.getText().equals("") && !jTextFieldFuncionarioCargo.getText().equals("") && !jTextFieldFuncionarioSalario.getText().equals("") && m.find() && !jComboBoxFuncionarioStatus.getSelectedItem().equals("") && !jTextFieldFuncionarioNumeroDaMatricula.getText().equals("") && !jComboBoxFuncionarioLograduro.getSelectedItem().equals("") && !jComboBoxFuncionarioSexo.getSelectedItem().equals("") && !jComboBoxFuncionarioUf.getSelectedItem().equals("")) {

            p = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
            m = p.matcher(jFormattedTextFieldFuncionarioCpf.getText());
            if (m.find()) {

                p = Pattern.compile("\\d+\\.?\\d+");
                m = p.matcher(jTextFieldFuncionarioSalario.getText());
                if (m.find()) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Números monetários devem ser separados por ponto", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por Favor, preencha todos os campos em negrito", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

        } else {

            JOptionPane.showMessageDialog(this, "Por Favor, preencha todos os campos em negrito", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

    }

    private boolean isCamposSocioPreenchidos() {

        Pattern p = Pattern.compile("\\d{2}\\/\\d{2}\\/\\d{4}");
        Matcher m;

        if (!jTextFieldSocioNome.getText().equals("")
                && !jTextFieldSocioSobrenome.getText().equals("")
                && !jComboBoxSocioSexo.getSelectedItem().equals("")
                && !jComboBoxSocioLogradouro.getSelectedItem().equals("")
                && !jComboBoxSocioUF.getSelectedItem().equals("")
                && !jTextFieldSocioNumeroSocio.getText().equals("")
                && !jComboBoxSocioCategoriaSocio.getSelectedItem().equals("")
                && !jTextFieldSocioNumero.getText().equals("")
                && (jRadioButtonSocioAtivo.isSelected() || jRadioButtonSocioInativo.isSelected())) {

            m = p.matcher(jFormattedTextFieldSocioDiaVencimento.getText());
            if (m.find()) {
                m = p.matcher(jFormattedTextFieldSocioDataMatricula.getText());
                if (m.find()) {
                    p = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
                    m = p.matcher(jFormattedTextFieldSocioCPF.getText());

                    if (m.find() || jRadioCPFCNPJ.isSelected()) {
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Por Favor, preencha todos os campos em negrito 1", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        return false;
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Por Favor, preencha todos os campos em negrito 2", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }

            } else {
                JOptionPane.showMessageDialog(this, "Por Favor, preencha todos os campos em negrito 3", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

        } else {
            JOptionPane.showMessageDialog(this, "Por Favor, preencha todos os campos em negrito 4", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    private boolean isCamposCategoriaSocioPreenchidos() {

        if (!jTextFieldCategoriaSocioNome.getText().equals("") && !jComboBoxCategoriaSocioTaxa.getSelectedItem().equals("")) {

            return true;

        } else {

            JOptionPane.showMessageDialog(this, "Os campos em negrito são obrigatórios", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

    }

    private boolean isCamposTipoDespesaPreenchidos() {

        if (!jTextFieldTipoDespesaNome.getText().equals("") && !jTextAreaTipoDespesaDescricao.getText().equals("")) {

            return true;

        } else {

            JOptionPane.showMessageDialog(this, "Os campos em negrito são obrigatórios", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

    }

    private boolean isCamposDespesaPreenchidos() {

        Pattern p = Pattern.compile("\\d{2}\\/\\d{2}\\/\\d{4}");
        Matcher m;
        if (!jComboBoxDespesaFuncionario.getSelectedItem().equals("") && !jComboBoxDespesaMotivo.getSelectedItem().equals("") && !jTextFieldDespesaFavorecido.getText().equals("") && !jTextFieldDespesaValor.getText().equals("")) {

            m = p.matcher(jFormattedTextFieldDespesaData.getText());
            if (m.find()) {

                p = Pattern.compile("\\d+\\.\\d+");
                m = p.matcher(jTextFieldDespesaValor.getText());
                if (m.find()) {

                    return true;

                } else {
                    JOptionPane.showMessageDialog(this, "Campos monetários devem ser separados com ponto", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }

            } else {

                JOptionPane.showMessageDialog(this, "Os campos em negrito são obrigatórios", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

        } else {

            JOptionPane.showMessageDialog(this, "Os campos em negrito são obrigatórios", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;

        }

    }

    private boolean isCamposTipoReceitaPreenchidos() {

        if (!jTextFieldTipoReceitaNome.getText().equals("") && !jTextAreaTipoReceitaDescricao.getText().equals("")) {

            return true;

        } else {

            JOptionPane.showMessageDialog(this, "Os campos em negrito são obrigatórios", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

    }

    private boolean isCamposReceitaPreenchidos() {

        Pattern p = Pattern.compile("\\d{2}\\/\\d{2}\\/\\d{4}");
        Matcher m;

        if ((jCheckBoxReceitaAvulsa.isSelected()
                || !jComboBoxReceitaSocio.getSelectedItem().equals(""))
                && !jComboBoxReceitaMotivoEntrada.getSelectedItem().equals("")
                && !jComboBoxReceitaFuncionario.getSelectedItem().equals("")) {

            //alexandre was here
            if (null != jDateChooser1.getDate()) {

                p = Pattern.compile("\\d+\\.\\d+");
                m = p.matcher(jTextFieldReceitaValor.getText());

                if (m.find()) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Campos monetários devem ser separados com ponto", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }

            } else {
                JOptionPane.showMessageDialog(this, "Os campos em negrito são obrigatórios", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

        } else {

            JOptionPane.showMessageDialog(this, "Os campos em negrito são obrigatórios", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;

        }

    }

    private boolean isCamposChequePreenchidos() {

        Pattern p = Pattern.compile("\\d{2}\\/\\d{2}\\/\\d{4}");
        Matcher m;

        if (!jTextFieldChequeNumero.getText().equals("") && !jComboBoxChequeFuncionario.getSelectedItem().equals("") && !jComboBoxChequeMotivoDespesa.getSelectedItem().equals("")) {

            m = p.matcher(jFormattedTextFieldChequeDataVencimento.getText());
            if (m.find()) {
                p = Pattern.compile("\\d+\\.\\d+");
                m = p.matcher(jTextFieldChequeValor.getText());
                if (m.find()) {
                    return true;

                } else {

                    JOptionPane.showMessageDialog(this, "Campos monetários devem ser separados com ponto", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }

            } else {

                JOptionPane.showMessageDialog(this, "Os campos em negrito são obrigatórios", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

        } else {

            JOptionPane.showMessageDialog(this, "Os campos em negrito são obrigatórios", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

    }

    private void setEditableComponentesLogradouros(boolean edite) {

        jTextFieldLogradouroNome.setEnabled(edite);
        jTextAreaLogradouroDescricao.setEnabled(edite);
        jComboBoxLogradouroTipo.setEnabled(edite);
        jButtonLogradouroApagar.setEnabled(edite);
        jButtonLogradouroCancelar.setEnabled(edite);
        jButtonLogradouroEditar.setEnabled(edite);
        jButtonLogradouroSalvar.setEnabled(edite);

    }

    private void setEditableComponentesTaxas(boolean edite) {

        jTextFieldTaxasNome.setEditable(edite);
        jTextFieldTaxasValor.setEditable(edite);
        jTextAreaTaxasDescricao.setEditable(edite);
        jButtonTaxaApagar.setEnabled(edite);
        jButtonTaxaCancelar.setEnabled(edite);
        jButtonTaxaEditar.setEnabled(edite);
        jButtonTaxaSalvar.setEnabled(edite);

    }

    private void setEditableComponentesSocio(boolean edit) {

        if (pesquisarTable.equals("importar funcionário")) {

            jTextFieldSocioNome.setEditable(edit);
            jTextFieldSocioSobrenome.setEditable(edit);
            jTextFieldSocioApelido.setEditable(edit);
            jTextFieldSocioTelefone.setEditable(edit);
            jTextFieldSocioEmail.setEditable(edit);
            jFormattedTextFieldSocioDataNascimento.setEditable(edit);
            jComboBoxSocioSexo.setEnabled(edit);
            jComboBoxSocioLogradouro.setEnabled(edit);
            jTextFieldSocioNomeMae.setEditable(edit);
            jTextFieldSocioNomePai.setEditable(edit);
            jFormattedTextFieldSocioCPF.setEditable(false);
            jTextFieldSocioRgNumero.setEditable(edit);
            jTextFieldSocioOrgaoExpedidor.setEditable(edit);
            jFormattedTextFieldSocioDataEmissao.setEditable(edit);
            jTextFieldSocioNumero.setEditable(edit);
            jTextFieldSocioBairro.setEditable(edit);
            jTextFieldSocioCep2.setEditable(edit);
            jComboBoxSocioUF.setEnabled(edit);
            jTextFieldSocioCEP.setEditable(edit);
            buttonGroupSocioAprovacao.clearSelection();
            jRadioButtonSocioAtivo.setEnabled(edit);
            jRadioButtonSocioInativo.setEnabled(edit);
            jFormattedTextFieldSocioDiaVencimento.setEditable(edit);
            jFormattedTextFieldSocioDataMatricula.setEditable(edit);
            jTextFieldSocioNumeroSocio.setEditable(edit);
            jComboBoxSocioCategoriaSocio.setEnabled(edit);
            jButtonSocioApagar.setEnabled(false);
            jButtonSocioCancelar.setEnabled(edit);
            jButtonSocioEditar.setEnabled(false);
            jButtonSocioSalvar.setEnabled(edit);
            jButtonSocioAdicionarLogradouro.setEnabled(edit);
        } else {
            jTextFieldSocioNome.setEditable(edit);
            jTextFieldSocioSobrenome.setEditable(edit);
            jTextFieldSocioApelido.setEditable(edit);
            jTextFieldSocioTelefone.setEditable(edit);
            jTextFieldSocioEmail.setEditable(edit);
            jFormattedTextFieldSocioDataNascimento.setEditable(edit);
            jComboBoxSocioSexo.setEnabled(edit);
            jComboBoxSocioLogradouro.setEnabled(edit);
            jTextFieldSocioNomeMae.setEditable(edit);
            jTextFieldSocioNomePai.setEditable(edit);
            jFormattedTextFieldSocioCPF.setEditable(edit);
            jTextFieldSocioRgNumero.setEditable(edit);
            jTextFieldSocioOrgaoExpedidor.setEditable(edit);
            jFormattedTextFieldSocioDataEmissao.setEditable(edit);
            jTextFieldSocioNumero.setEditable(edit);
            jTextFieldSocioBairro.setEditable(edit);
            jTextFieldSocioCep2.setEditable(edit);
            jComboBoxSocioUF.setEnabled(edit);
            jTextFieldSocioCEP.setEditable(edit);
            buttonGroupSocioAprovacao.clearSelection();
            jRadioButtonSocioAtivo.setEnabled(edit);
            jRadioButtonSocioInativo.setEnabled(edit);
            jFormattedTextFieldSocioDiaVencimento.setEditable(edit);
            jFormattedTextFieldSocioDataMatricula.setEditable(edit);
            jTextFieldSocioNumeroSocio.setEditable(edit);
            jComboBoxSocioCategoriaSocio.setEnabled(edit);
            jButtonSocioApagar.setEnabled(edit);
            jButtonSocioCancelar.setEnabled(edit);
            jButtonSocioEditar.setEnabled(edit);
            jButtonSocioSalvar.setEnabled(edit);
            jButtonSocioAdicionarLogradouro.setEnabled(edit);
            jCheckBoxExclusivamenteSocio.setEnabled(edit);
        }

    }

    private void setEditableComponentesCategoriaSocio(boolean edit) {

        jTextFieldCategoriaSocioNome.setEditable(edit);
        jTextAreaCategoriaSocioDescricao.setEditable(edit);
        jComboBoxCategoriaSocioTaxa.setEnabled(edit);
        jButtonCategoriaSocioApagar.setEnabled(edit);
        jButtonCategoriaSocioCancelar.setEnabled(edit);
        jButtonCategoriaSocioEditar.setEnabled(edit);
        jButtonCategoriaSocioSalvar.setEnabled(edit);

    }

    private void setEditableComponentesTipoDespesa(boolean edit) {

        jTextFieldTipoDespesaNome.setEditable(edit);
        jTextAreaTipoDespesaDescricao.setEditable(edit);
        jTextAreaTipoDespesaObservacao.setEditable(edit);
        jButtonTipoDespesaApagar.setEnabled(edit);
        jButtonTipoDespesaCancelar.setEnabled(edit);
        jButtonTipoDespesaEditar.setEnabled(edit);
        jButtonTipoDespesaSalvar.setEnabled(edit);

    }

    private void setEditableComponentesDespesa(boolean edit) {

        jComboBoxDespesaFuncionario.setEnabled(edit);
        jFormattedTextFieldDespesaData.setEditable(edit);
        jTextFieldDespesaValor.setEditable(edit);
        jComboBoxDespesaMotivo.setEnabled(edit);
        jTextFieldDespesaFavorecido.setEditable(edit);
        jTextAreaDespesaObservacao.setEditable(edit);
        jButtonDespesaApagar.setEnabled(edit);
        jButtonDespesaCancelar.setEnabled(edit);
        jButtonDespesaEditar.setEnabled(edit);
        jButtonDespesaSalvar.setEnabled(edit);

    }

    private void setEditableComponentesTipoReceita(boolean edit) {

        jTextFieldTipoReceitaNome.setEditable(edit);
        jTextAreaTipoReceitaDescricao.setEditable(edit);
        jTextAreaTipoReceitaObservacao.setEditable(edit);
        jButtonTipoReceitaApagar.setEnabled(edit);
        jButtonTipoReceitaCancelar.setEnabled(edit);
        jButtonTipoReceitaEditar.setEnabled(edit);
        jButtonTipoReceitaSalvar.setEnabled(edit);

    }

    private void setEditableComponentesReceita(boolean edit) {

        jComboBoxReceitaFuncionario.setEnabled(edit);
        jComboBoxReceitaMotivoEntrada.setEnabled(edit);
        jComboBoxReceitaSocio.setEnabled(edit);
        jTextFieldReceitaValor.setEditable(edit);
        jTextAreaReceitaObservacao.setEditable(edit);
        jDateChooser1.setEnabled(edit);
        jButtonReceitaApagar.setEnabled(edit);
        jButtonReceitaCancelar.setEnabled(edit);
        jButtonReceitaEditar.setEnabled(edit);
        jButtonReceitaSalvar.setEnabled(edit);
        jCheckBoxReceitaAvulsa.setEnabled(edit);

    }

    private void setEditableComponentesCheques(boolean edit) {

        jTextFieldChequeNumero.setEditable(edit);
        jTextFieldChequeValor.setEditable(edit);
        jComboBoxChequeFuncionario.setEnabled(edit);
        jComboBoxChequeMotivoDespesa.setEnabled(edit);
        jFormattedTextFieldChequeDataPagamento.setEditable(edit);
        jFormattedTextFieldChequeDataVencimento.setEditable(edit);
        jButtonChequeApagar.setEnabled(edit);
        jButtonChequeCancelar.setEnabled(edit);
        jButtonChequeEditar.setEnabled(edit);
        jButtonChequeSalvar.setEnabled(edit);

    }

    private void limparCamposTaxas() {

        jTextFieldTaxasID.setText("");
        jTextFieldTaxasNome.setText("");
        jTextFieldTaxasValor.setText("");
        jTextAreaTaxasDescricao.setText("");
    }

    private void limparCamposLogradouro() {

        jTextFieldLogradouroNome.setText("");
        jTextAreaLogradouroDescricao.setText("");
        if (jComboBoxLogradouroTipo.getItemCount() != 0) {
            jComboBoxLogradouroTipo.setSelectedIndex(0);
        }
        jTextFieldLogradouroID.setText("");

    }

    private void limparCamposSocio() {
        jCheckBoxExclusivamenteSocio.setSelected(false);
        jTextFieldSocioID.setText("");
        jTextFieldSocioNome.setText("");
        jTextFieldSocioSobrenome.setText("");
        jTextFieldSocioApelido.setText("");
        jTextFieldSocioTelefone.setText("");
        jTextFieldSocioEmail.setText("");
        jFormattedTextFieldSocioDataNascimento.setText("");
        jComboBoxSocioSexo.setSelectedItem("");
        jTextFieldSocioNomePai.setText("");
        jTextFieldSocioNomeMae.setText("");
        jFormattedTextFieldSocioCPF.setText("");
        jTextFieldSocioRgNumero.setText("");
        jTextFieldSocioOrgaoExpedidor.setText("");
        jFormattedTextFieldSocioDataEmissao.setText("");
        jTextFieldSocioNumero.setText("");
        jTextFieldSocioBairro.setText("");
        jTextFieldSocioCep2.setText("");
        jComboBoxSocioUF.setSelectedItem("");
        jTextFieldSocioCEP.setText("");
        if (jComboBoxSocioLogradouro.getItemCount() != 0) {

            jComboBoxSocioLogradouro.setSelectedIndex(0);
        }
        buttonGroupSocioAprovacao.clearSelection();
        jFormattedTextFieldSocioDataMatricula.setText("");
        jFormattedTextFieldSocioDiaVencimento.setText("");
        jTextFieldSocioNumeroSocio.setText("");
        if (jComboBoxSocioCategoriaSocio.getItemCount() != 0) {

            jComboBoxSocioCategoriaSocio.setSelectedIndex(0);

        }

        jCheckBoxExclusivamenteSocio.setEnabled(true);
    }

    private void limparCamposCategoriaSocio() {

        jTextFieldCategoriaSocioNome.setText("");
        jTextFieldCategoriaSocioID.setText("");
        jTextAreaCategoriaSocioDescricao.setText("");
        if (jComboBoxCategoriaSocioTaxa.getItemCount() != 0) {

            jComboBoxCategoriaSocioTaxa.setSelectedIndex(0);
        }

    }

    private void limparCamposTipoDespesa() {

        jTextFieldTIpoDespesaID.setText("");
        jTextFieldTipoDespesaNome.setText("");
        jTextAreaTipoDespesaDescricao.setText("");
        jTextAreaTipoDespesaObservacao.setText("");

    }

    private void limparCamposDespesa() {

        if (jComboBoxDespesaFuncionario.getItemCount() != 0) {
            jComboBoxDespesaFuncionario.setSelectedIndex(0);

        }

        jTextFieldDespesaID.setText("");
        jFormattedTextFieldDespesaData.setText("");
        jTextFieldDespesaValor.setText("");
        if (jComboBoxDespesaMotivo.getItemCount() != 0) {

            jComboBoxDespesaMotivo.setSelectedIndex(0);
        }
        jTextFieldDespesaFavorecido.setText("");
        jTextAreaDespesaObservacao.setText("");

    }

    private void limparCamposTipoReceita() {

        jTextFieldTIpoReceitaID.setText("");
        jTextFieldTipoReceitaNome.setText("");
        jTextAreaTipoReceitaDescricao.setText("");
        jTextAreaTipoReceitaObservacao.setText("");

    }

    private void limparCamposReceita() {

        jTextFieldReceitaID.setText("");
        jTextFieldReceitaValor.setText("");
        jDateChooser1.setDate(null);

        if (jComboBoxReceitaFuncionario.getItemCount() != 0) {

            jComboBoxReceitaFuncionario.setSelectedIndex(0);
        }
        if (jComboBoxReceitaMotivoEntrada.getItemCount() != 0) {

            jComboBoxReceitaMotivoEntrada.setSelectedIndex(0);
        }
        if (jComboBoxReceitaSocio.getItemCount() != 0) {

            jComboBoxReceitaSocio.setSelectedIndex(0);
        }
        jTextAreaReceitaObservacao.setText("");

    }

    private void limparCamposCheques() {

        jTextFieldChequeID.setText("");
        jTextFieldChequeNumero.setText("");
        jTextFieldChequeValor.setText("");
        jTextAreaChequeObservacoes.setText("");
        jFormattedTextFieldChequeDataPagamento.setText("");
        jFormattedTextFieldChequeDataVencimento.setText("");

        if (jComboBoxChequeFuncionario.getItemCount() != 0) {

            jComboBoxChequeFuncionario.setSelectedIndex(0);
        }

        if (jComboBoxChequeMotivoDespesa.getItemCount() != 0) {

            jComboBoxChequeMotivoDespesa.setSelectedIndex(0);
        }

    }

    private void preencherCamposLogradouro(Endereco end) {

        jTextFieldLogradouroID.setText(String.valueOf(end.getId()));
        jTextFieldLogradouroNome.setText(end.getNome());
        jTextAreaLogradouroDescricao.setText(end.getDescricao());

        jComboBoxLogradouroTipo.setSelectedItem(end.getTipo());

    }

    private void preencherCamposTaxas(Taxa taxa) {

        jTextFieldTaxasID.setText(String.valueOf(taxa.getId()));
        jTextFieldTaxasNome.setText(taxa.getNome());
        jTextFieldTaxasValor.setText(String.valueOf(taxa.getValor()));
        jTextAreaTaxasDescricao.setText(taxa.getDescricao());

    }

    private void preencherCamposSocio(Socio partnerModel) {

        jTextFieldSocioNome.setText(partnerModel.getIdPessoa().getNome());
        jTextFieldSocioSobrenome.setText(partnerModel.getIdPessoa().getSobrenome());
        jTextFieldSocioApelido.setText(partnerModel.getIdPessoa().getApelido());
        jTextFieldSocioID.setText(String.valueOf(partnerModel.getId()));
        jTextFieldSocioTelefone.setText(partnerModel.getIdPessoa().getTelefone());
        jTextFieldSocioEmail.setText(partnerModel.getIdPessoa().getEmail());
        jTextFieldSocioNomePai.setText(partnerModel.getIdPessoa().getNomePai());
        jTextFieldSocioNomeMae.setText(partnerModel.getIdPessoa().getNomeMae());
        jTextFieldSocioRgNumero.setText(partnerModel.getIdPessoa().getRgNumero());
        jTextFieldSocioOrgaoExpedidor.setText(partnerModel.getIdPessoa().getRgExpedidor());
        jTextFieldSocioNumero.setText(partnerModel.getIdPessoa().getEnderecopessoaList().get(0).getNumero());
        jTextFieldSocioCep2.setText(partnerModel.getIdPessoa().getCidade());
        jTextFieldSocioBairro.setText(partnerModel.getIdPessoa().getBairro());
        jTextFieldSocioCEP.setText(partnerModel.getIdPessoa().getCep());
        jTextFieldSocioNumeroSocio.setText(String.valueOf(partnerModel.getNumeroSocio()));

        jComboBoxSocioSexo.setSelectedItem(partnerModel.getIdPessoa().getSexo());
        jComboBoxSocioUF.setSelectedItem(partnerModel.getIdPessoa().getUf());
        if (jComboBoxSocioLogradouro.getItemCount() == 0) {

            jComboBoxSocioLogradouro.addItem("");
            List<Endereco> ends = daoEndereco.BuscarTodosEnderecos();
            for (Endereco e : ends) {

                jComboBoxSocioLogradouro.addItem(e.getTipo() + " " + e.getNome());

            }

        }
        jComboBoxSocioLogradouro.setSelectedItem(partnerModel.getIdPessoa().getIdEndereco().getTipo() + " " + partnerModel.getIdPessoa().getIdEndereco().getNome());
        if (jComboBoxSocioCategoriaSocio.getItemCount() == 0) {

            jComboBoxSocioCategoriaSocio.addItem("");
            List<CategoriaSocio> categorias = new DaoCategoriaSocio().BuscarTodasCategorias();
            for (CategoriaSocio categoria : categorias) {

                jComboBoxSocioCategoriaSocio.addItem(categoria.getNome());

            }

        }
        jComboBoxSocioCategoriaSocio.setSelectedItem(partnerModel.getIdCategoriaSocio().getNome());

        if (partnerModel.getIdPessoa().getDataNasc() != null) {
            jFormattedTextFieldSocioDataNascimento.setText(SimpleDateFormat.getDateInstance().format(partnerModel.getIdPessoa().getDataNasc()));
        }
        jFormattedTextFieldSocioCPF.setText(partnerModel.getIdPessoa().getCpf());
        if (partnerModel.getIdPessoa().getRgEmissao() != null) {

            jFormattedTextFieldSocioDataEmissao.setText(SimpleDateFormat.getDateInstance().format(partnerModel.getIdPessoa().getRgEmissao()));
        }
        jFormattedTextFieldSocioDiaVencimento.setText(SimpleDateFormat.getDateInstance().format(partnerModel.getDataVence()));
        jFormattedTextFieldSocioDataMatricula.setText(SimpleDateFormat.getDateInstance().format(partnerModel.getDataMatricula()));

        if (partnerModel.getIdPessoa().getStatus()) {

            jRadioButtonSocioAtivo.setSelected(true);
        } else {

            jRadioButtonSocioInativo.setSelected(false);

        }
        jCheckBoxExclusivamenteSocio.setSelected(partnerModel.getSocioExclusivo());
    }

    private void preencherCamposCategoriaSocio(CategoriaSocio c) {

        jTextFieldCategoriaSocioID.setText(String.valueOf(c.getId()));
        jTextFieldCategoriaSocioNome.setText(c.getNome());
        jTextAreaCategoriaSocioDescricao.setText(c.getDescricao());

        if (jComboBoxCategoriaSocioTaxa.getItemCount() == 0) {

            jComboBoxCategoriaSocioTaxa.addItem("");
            List<Taxa> taxas = daoTaxa.TaxasTodas();
            for (Taxa t : taxas) {

                jComboBoxCategoriaSocioTaxa.addItem(t.getNome());
            }

        }
        jComboBoxCategoriaSocioTaxa.setSelectedItem(c.getTaxasId().getNome());

    }

    private void preencherCamposSocioImportados(Pessoa pessoa) {

        Socio s = pessoa.getSocio();
        if (s != null) {

            try {
                throw new Exception();
            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, "Esse funcionario ja foi importado", "Erro", JOptionPane.ERROR_MESSAGE);

            }

        } else {
            jTextFieldSocioNome.setText(pessoa.getNome());

            jTextFieldSocioSobrenome.setText(pessoa.getSobrenome());
            jTextFieldSocioApelido.setText(pessoa.getApelido());
            jTextFieldSocioTelefone.setText(pessoa.getTelefone());
            jTextFieldSocioEmail.setText(pessoa.getEmail());
            if (pessoa.getDataNasc() != null) {
                jFormattedTextFieldSocioDataNascimento.setText(SimpleDateFormat.getDateInstance().format(pessoa.getDataNasc()));
            }
            jComboBoxSocioSexo.setSelectedItem(pessoa.getSexo());
            jTextFieldSocioNomeMae.setText(pessoa.getNomeMae());
            jTextFieldSocioNomePai.setText(pessoa.getNomePai());
            jFormattedTextFieldSocioCPF.setText(pessoa.getCpf());

            jTextFieldSocioRgNumero.setText(pessoa.getRgNumero());
            jTextFieldSocioOrgaoExpedidor.setText(pessoa.getRgExpedidor());
            if (pessoa.getRgEmissao() != null) {
                jFormattedTextFieldSocioDataEmissao.setText(SimpleDateFormat.getDateInstance().format(pessoa.getRgEmissao()));
            }
            if (jComboBoxSocioLogradouro.getItemCount() == 0) {
                jComboBoxSocioLogradouro.addItem("");
                List<Endereco> enderecos = daoEndereco.BuscarTodosEnderecos();
                for (Endereco endereco : enderecos) {

                    jComboBoxSocioLogradouro.addItem(endereco.getTipo() + " " + endereco.getNome());

                }
            }
            jComboBoxSocioLogradouro.setSelectedItem(pessoa.getIdEndereco().getTipo() + " " + pessoa.getIdEndereco().getNome());
            jTextFieldSocioNumero.setText(pessoa.getNumeroEndereco());
            jTextFieldSocioCep2.setText(pessoa.getCidade());
            jTextFieldSocioBairro.setText(pessoa.getBairro());
            jComboBoxSocioUF.setSelectedItem(pessoa.getUf());
            jTextFieldSocioCEP.setText(pessoa.getCep());

            if (pessoa.getStatus()) {
                jRadioButtonSocioAtivo.setSelected(true);
            } else {
                jRadioButtonSocioInativo.setSelected(false);
            }
            setEditableComponentesSocio(true);
        }
    }

    private void preencherCamposTipoDespesa(Motivodespesa m) {

        jTextFieldTIpoDespesaID.setText(String.valueOf(m.getId()));
        jTextFieldTipoDespesaNome.setText(m.getNome());
        jTextAreaTipoDespesaDescricao.setText(m.getDescricao());
        jTextAreaTipoDespesaObservacao.setText(m.getObservacao());

    }

    private void preencherCamposDespesa(Saida s) {

        jTextFieldDespesaID.setText(String.valueOf(s.getId()));
        jComboBoxDespesaFuncionario.setSelectedItem(s.getIdfuncionario().getIdPessoa().getNome() + " " + s.getIdfuncionario().getIdPessoa().getSobrenome());
        jFormattedTextFieldDespesaData.setText(SimpleDateFormat.getDateInstance().format(s.getData()));
        jTextFieldDespesaValor.setText(String.valueOf(s.getValor()));

        if (jComboBoxDespesaMotivo.getItemCount() == 0) {
            jComboBoxDespesaMotivo.addItem("");
            List<Motivodespesa> motivos = daoMotivoDespesa.BuscarTodosMotivos();

            for (Motivodespesa m : motivos) {

                jComboBoxDespesaMotivo.addItem(m.getNome());
            }

        }
        jComboBoxDespesaMotivo.setSelectedItem(s.getIdmotivosaida().getNome());

        jTextFieldDespesaFavorecido.setText(s.getFavorecido());
        jTextAreaDespesaObservacao.setText(s.getObservacao());

    }

    private void preencherCamposTipoReceita(Motivoentrada m) {

        jTextFieldTIpoReceitaID.setText(String.valueOf(m.getId()));
        jTextFieldTipoReceitaNome.setText(m.getNome());
        jTextAreaTipoReceitaDescricao.setText(m.getDescricao());
        jTextAreaTipoReceitaObservacao.setText(m.getObservacao());

    }

    private void preencherCamposReceita(Entrada e) {

        jTextFieldReceitaID.setText(String.valueOf(e.getId()));
        jTextAreaReceitaObservacao.setText(e.getObservacao());
        jTextFieldReceitaValor.setText(String.valueOf(e.getValor()));
        jDateChooser1.setDate(e.getData());
        jComboBoxReceitaFuncionario.setSelectedItem(e.getIdFuncionario().getIdPessoa().getNome() + " " + e.getIdFuncionario().getIdPessoa().getSobrenome());

        if (jComboBoxReceitaMotivoEntrada.getItemCount() == 0) {

            jComboBoxReceitaMotivoEntrada.addItem("");

            List<Motivoentrada> motivos = new DaoMotivoEntrada().BuscarTodosMotivos();
            for (Motivoentrada m : motivos) {

                jComboBoxReceitaMotivoEntrada.addItem(m.getNome());

            }

        }
        jComboBoxReceitaMotivoEntrada.setSelectedItem(e.getIdMotivoEntrada().getNome());

        if (jComboBoxReceitaSocio.getItemCount() == 0) {

            jComboBoxReceitaSocio.addItem("");

            List<Socio> partnerModels = daoSocio.TodosOsSocios();

            for (Socio s : partnerModels) {

                jComboBoxReceitaSocio.addItem(s.getIdPessoa().getNome() + " " + s.getIdPessoa().getSobrenome());

            }

        }
        jComboBoxReceitaSocio.setSelectedItem(e.getIdCedente().getIdPessoa().getNome() + " " + e.getIdCedente().getIdPessoa().getSobrenome());

    }

    private void preencherCamposCheque(Cheque c) {

        jTextFieldChequeID.setText(String.valueOf(c.getId()));
        jTextFieldChequeNumero.setText(String.valueOf(c.getNumero()));
        jTextFieldChequeValor.setText(String.valueOf(c.getValor()));
        jTextAreaChequeObservacoes.setText(c.getObservacoes());

        jFormattedTextFieldChequeDataPagamento.setText(SimpleDateFormat.getDateInstance().format(c.getDataPagamento()));
        jFormattedTextFieldChequeDataVencimento.setText(SimpleDateFormat.getDateInstance().format(c.getDataVencimento()));
        jComboBoxChequeFuncionario.setSelectedItem(c.getIdFuncionario().getIdPessoa().getNome() + " " + c.getIdFuncionario().getIdPessoa().getSobrenome());

        if (jComboBoxChequeMotivoDespesa.getItemCount() == 0) {

            jComboBoxChequeMotivoDespesa.addItem("");
            List<Motivodespesa> motivos = daoMotivoDespesa.BuscarTodosMotivos();

            if (!motivos.isEmpty()) {

                for (Motivodespesa m : motivos) {

                    jComboBoxChequeMotivoDespesa.addItem(m.getNome());
                }

            }

        }
        jComboBoxChequeMotivoDespesa.setSelectedItem(c.getIdMotivoDespesa().getNome());
    }

    private void zerarComboBox() {

        jComboBoxCategoriaSocioTaxa.removeAllItems();
        jComboBoxDespesaFuncionario.removeAllItems();
        jComboBoxDespesaMotivo.removeAllItems();
        jComboBoxFuncionarioLograduro.removeAllItems();

        jComboBoxReceitaFuncionario.removeAllItems();
        jComboBoxReceitaMotivoEntrada.removeAllItems();
        jComboBoxReceitaSocio.removeAllItems();
        jComboBoxSocioCategoriaSocio.removeAllItems();
        jComboBoxSocioLogradouro.removeAllItems();

    }

    private void mostrarJtable() {

        DefaultTableCellRenderer centralizar = new DefaultTableCellRenderer();
        centralizar.setHorizontalAlignment(SwingConstants.CENTER);
        switch (pesquisarTable) {
            case "funcionarios":
            case "importar funcionário":
                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        new Object[][]{},
                        new String[]{"Nome", "Sobrenome", "CPF", "Cidade", "Data Nascimento", "Email", "Status"}) {
                    Class[] types = new Class[]{String.class, String.class, String.class, String.class, String.class,
                        String.class, Boolean.class};

                    @Override
                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false};

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {

                        return canEdit[columnIndex];
                    }
                });
                jTable1.getColumn("Nome").setCellRenderer(centralizar);
                jTable1.getColumn("Sobrenome").setCellRenderer(centralizar);
                jTable1.getColumn("CPF").setCellRenderer(centralizar);
                jTable1.getColumn("Cidade").setCellRenderer(centralizar);
                jTable1.getColumn("Data Nascimento").setCellRenderer(centralizar);
                jTable1.getColumn("Email").setCellRenderer(centralizar);
                break;
            case "logradouros":
                jTable1.setModel(new DefaultTableModel(
                        new Object[][]{}, new String[]{"ID", "Nome", "Tipo", "Descrição"}) {
                    Class[] types = new Class[]{Integer.class, String.class, String.class, String.class};

                    @Override
                    public Class getColumnClass(int columnIndex) {

                        return types[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{false, false, false, false};

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {

                        return canEdit[columnIndex];

                    }
                });
                jTable1.getColumn("ID").setCellRenderer(centralizar);
                jTable1.getColumn("Nome").setCellRenderer(centralizar);
                jTable1.getColumn("Tipo").setCellRenderer(centralizar);
                jTable1.getColumn("Descrição").setCellRenderer(centralizar);
                break;
            case "taxas":
                jTable1.setModel(new DefaultTableModel(
                        new Object[][]{}, new String[]{"ID", "Nome", "Descrição", "Valor"}) {
                    Class[] types = new Class[]{Integer.class, String.class, String.class, BigDecimal.class};

                    @Override
                    public Class getColumnClass(int columnIndex) {

                        return types[columnIndex];
                    }
                    final boolean[] canEdit = new boolean[]{false, false, false, false};

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {

                        return canEdit[columnIndex];

                    }
                });
                jTable1.getColumn("ID").setCellRenderer(centralizar);
                jTable1.getColumn("Nome").setCellRenderer(centralizar);
                jTable1.getColumn("Descrição").setCellRenderer(centralizar);
                jTable1.getColumn("Valor").setCellRenderer(centralizar);
                break;
            case "socios":
                jTable1.setModel(new DefaultTableModel(
                        new Object[][]{}, new String[]{"ID", "Nome", "CPF", "Categoria Socio", "Logradouro", "Numero"}) {
                    final Class[] types = new Class[]{Integer.class, String.class, String.class, String.class, String.class, String.class};

                    @Override
                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];

                    }
                    boolean[] canEdit = new boolean[]{false, false, false, false, false, false};

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                });
                jTable1.getColumn("ID").setCellRenderer(centralizar);
                jTable1.getColumn("Nome").setCellRenderer(centralizar);
                jTable1.getColumn("CPF").setCellRenderer(centralizar);
                jTable1.getColumn("Categoria Socio").setCellRenderer(centralizar);
                jTable1.getColumn("Logradouro").setCellRenderer(centralizar);
                jTable1.getColumn("Numero").setCellRenderer(centralizar);
                break;

            case "categoria socio":
                jTable1.setModel(new DefaultTableModel(
                        new Object[][]{}, new String[]{"ID", "Nome", "Descrição", "Taxa"}) {
                    Class[] type = new Class[]{Integer.class, String.class, String.class, String.class};

                    @Override
                    public Class getColumnClass(int columnIndex) {

                        return type[columnIndex];

                    }
                    boolean[] canEdit = new boolean[]{false, false, false, false};

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {

                        return canEdit[columnIndex];
                    }
                });
                jTable1.getColumn("ID").setCellRenderer(centralizar);
                jTable1.getColumn("Nome").setCellRenderer(centralizar);
                jTable1.getColumn("Descrição").setCellRenderer(centralizar);
                jTable1.getColumn("Taxa").setCellRenderer(centralizar);
                break;
            case "tipo despesa":
                jTable1.setModel(new DefaultTableModel(
                        new Object[][]{}, new String[]{"ID", "Nome", "Descrição", "Observação"}) {
                    Class[] type = new Class[]{Integer.class, String.class, String.class, String.class};

                    @Override
                    public Class getColumnClass(int columnIndex) {

                        return type[columnIndex];

                    }
                    boolean[] canEdit = new boolean[]{false, false, false, false};

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {

                        return canEdit[columnIndex];
                    }
                });
                jTable1.getColumn("ID").setCellRenderer(centralizar);
                jTable1.getColumn("Nome").setCellRenderer(centralizar);
                jTable1.getColumn("Descrição").setCellRenderer(centralizar);
                jTable1.getColumn("Observação").setCellRenderer(centralizar);
                break;
            case "despesa":
                jTable1.setModel(new DefaultTableModel(
                        new Object[][]{}, new String[]{"ID", "Data", "Valor", "Favorecido", "Funcionario", "Motivo de Saida", "Observação"}) {
                    Class[] type = new Class[]{Integer.class, String.class, BigDecimal.class, String.class, String.class, String.class, String.class};

                    @Override
                    public Class getColumnClass(int columnIndex) {

                        return type[columnIndex];
                    }
                    boolean canEdit[] = new boolean[]{false, false, false, false, false, false, false};

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {

                        return canEdit[columnIndex];

                    }
                });
                jTable1.getColumn("ID").setCellRenderer(centralizar);
                jTable1.getColumn("Data").setCellRenderer(centralizar);
                jTable1.getColumn("Valor").setCellRenderer(centralizar);
                jTable1.getColumn("Favorecido").setCellRenderer(centralizar);
                jTable1.getColumn("Funcionario").setCellRenderer(centralizar);
                jTable1.getColumn("Motivo de Saida").setCellRenderer(centralizar);
                jTable1.getColumn("Observação").setCellRenderer(centralizar);
                break;
            case "tipo receita":
                jTable1.setModel(new DefaultTableModel(
                        new Object[][]{}, new String[]{"ID", "Nome", "Descrição", "Observação"}) {
                    Class[] type = new Class[]{Integer.class, String.class, String.class, String.class};

                    @Override
                    public Class getColumnClass(int columnIndex) {

                        return type[columnIndex];
                    }
                    boolean[] canEdit = new boolean[]{false, false, false, false};

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {

                        return canEdit[columnIndex];

                    }
                });
                jTable1.getColumn("ID").setCellRenderer(centralizar);
                jTable1.getColumn("Nome").setCellRenderer(centralizar);
                jTable1.getColumn("Descrição").setCellRenderer(centralizar);
                jTable1.getColumn("Observação").setCellRenderer(centralizar);
                break;
            case "receita":
                jTable1.setModel(new DefaultTableModel(
                        new Object[][]{}, new String[]{"ID", "Data", "Valor", "Cedente", "Funcionario", "Motivo", "Observações"}) {

                    Class[] type = new Class[]{String.class, String.class, BigDecimal.class, String.class, String.class, String.class, String.class};

                    @Override
                    public Class getColumnClass(int columnIndex) {

                        return type[columnIndex];

                    }

                    boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false};

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {

                        return canEdit[columnIndex];

                    }

                });
                jTable1.getColumn("ID").setCellRenderer(centralizar);
                jTable1.getColumn("Data").setCellRenderer(centralizar);
                jTable1.getColumn("Valor").setCellRenderer(centralizar);
                jTable1.getColumn("Cedente").setCellRenderer(centralizar);
                jTable1.getColumn("Funcionario").setCellRenderer(centralizar);
                jTable1.getColumn("Motivo").setCellRenderer(centralizar);
                jTable1.getColumn("Observações").setCellRenderer(centralizar);
                break;
            case "cheque":
                jTable1.setModel(new DefaultTableModel(
                        new Object[][]{}, new String[]{"ID", "Numero", "Valor", "Funcionario", "Motivo Despesa", "Data Pagamento", "Data Vencimento"}) {

                    Class[] type = new Class[]{String.class, String.class, BigDecimal.class, String.class, String.class, String.class, String.class};

                    @Override
                    public Class getColumnClass(int columnIndex) {

                        return type[columnIndex];
                    }

                    boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false};

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {

                        return canEdit[columnIndex];

                    }
                });
                jTable1.getColumn("ID").setCellRenderer(centralizar);
                jTable1.getColumn("Numero").setCellRenderer(centralizar);
                jTable1.getColumn("Valor").setCellRenderer(centralizar);
                jTable1.getColumn("Funcionario").setCellRenderer(centralizar);
                jTable1.getColumn("Motivo Despesa").setCellRenderer(centralizar);
                jTable1.getColumn("Data Pagamento").setCellRenderer(centralizar);
                jTable1.getColumn("Data Vencimento").setCellRenderer(centralizar);
                break;
        }

        jDesktopPane1.setVisible(true);
        jInternalFrame1.setVisible(true);
        jInternalFrame1.setSize(1200, 768);
        try {
            jInternalFrame1.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaCadastros.class.getName()).log(Level.SEVERE, null, ex);
        }

        jPanel1.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logradouro;
    private javax.swing.JLabel Logradouro1;
    private javax.swing.JLabel Logradouro2;
    private javax.swing.JLabel Logradouro3;
    private javax.swing.JLabel Logradouro4;
    private javax.swing.JLabel Logradouro5;
    private javax.swing.JLabel Logradouro6;
    private javax.swing.JLabel Logradouro7;
    private javax.swing.ButtonGroup buttonGroupSocioAprovacao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCategoriaSocioApagar;
    private javax.swing.JButton jButtonCategoriaSocioCancelar;
    private javax.swing.JButton jButtonCategoriaSocioEditar;
    private javax.swing.JButton jButtonCategoriaSocioNovo;
    private javax.swing.JButton jButtonCategoriaSocioPesquisar;
    private javax.swing.JButton jButtonCategoriaSocioSalvar;
    private javax.swing.JButton jButtonChequeApagar;
    private javax.swing.JButton jButtonChequeCancelar;
    private javax.swing.JButton jButtonChequeEditar;
    private javax.swing.JButton jButtonChequeNovo;
    private javax.swing.JButton jButtonChequePesquisar;
    private javax.swing.JButton jButtonChequeSalvar;
    private javax.swing.JButton jButtonDespesaApagar;
    private javax.swing.JButton jButtonDespesaCancelar;
    private javax.swing.JButton jButtonDespesaEditar;
    private javax.swing.JButton jButtonDespesaNovo;
    private javax.swing.JButton jButtonDespesaPesquisar;
    private javax.swing.JButton jButtonDespesaSalvar;
    protected javax.swing.JButton jButtonFuncionarioApagar;
    private javax.swing.JButton jButtonFuncionarioCancelar;
    protected javax.swing.JButton jButtonFuncionarioEditar;
    private javax.swing.JButton jButtonFuncionarioNovo;
    private javax.swing.JButton jButtonFuncionarioPesquisar;
    private javax.swing.JButton jButtonFuncionarioSalvar;
    private javax.swing.JButton jButtonInternalFrameVoltar;
    private javax.swing.JButton jButtonLogradouroApagar;
    private javax.swing.JButton jButtonLogradouroCancelar;
    private javax.swing.JButton jButtonLogradouroEditar;
    private javax.swing.JButton jButtonLogradouroNovo;
    private javax.swing.JButton jButtonLogradouroPesquisar;
    private javax.swing.JButton jButtonLogradouroSalvar;
    private javax.swing.JButton jButtonPesquisa;
    private javax.swing.JButton jButtonReceitaApagar;
    private javax.swing.JButton jButtonReceitaCancelar;
    private javax.swing.JButton jButtonReceitaEditar;
    private javax.swing.JButton jButtonReceitaNovo;
    private javax.swing.JButton jButtonReceitaPesquisar;
    private javax.swing.JButton jButtonReceitaSalvar;
    private javax.swing.JButton jButtonSocioAdicionarLogradouro;
    private javax.swing.JButton jButtonSocioApagar;
    private javax.swing.JButton jButtonSocioCancelar;
    private javax.swing.JButton jButtonSocioEditar;
    private javax.swing.JButton jButtonSocioNovo;
    private javax.swing.JButton jButtonSocioPesquisar;
    private javax.swing.JButton jButtonSocioSalvar;
    private javax.swing.JButton jButtonTaxaApagar;
    private javax.swing.JButton jButtonTaxaCancelar;
    private javax.swing.JButton jButtonTaxaEditar;
    private javax.swing.JButton jButtonTaxaNovo;
    private javax.swing.JButton jButtonTaxaSalvar;
    private javax.swing.JButton jButtonTaxasPesquisar;
    private javax.swing.JButton jButtonTipoDespesaApagar;
    private javax.swing.JButton jButtonTipoDespesaCancelar;
    private javax.swing.JButton jButtonTipoDespesaEditar;
    private javax.swing.JButton jButtonTipoDespesaNovo;
    private javax.swing.JButton jButtonTipoDespesaPesquisar;
    private javax.swing.JButton jButtonTipoDespesaSalvar;
    private javax.swing.JButton jButtonTipoReceitaApagar;
    private javax.swing.JButton jButtonTipoReceitaCancelar;
    private javax.swing.JButton jButtonTipoReceitaEditar;
    private javax.swing.JButton jButtonTipoReceitaNovo;
    private javax.swing.JButton jButtonTipoReceitaPesquisar;
    private javax.swing.JButton jButtonTipoReceitaSalvar;
    private javax.swing.JCheckBox jCheckBoxExclusivamenteSocio;
    private javax.swing.JCheckBox jCheckBoxReceitaAvulsa;
    private javax.swing.JComboBox jComboBoxCategoriaSocioTaxa;
    private javax.swing.JComboBox jComboBoxChequeFuncionario;
    private javax.swing.JComboBox jComboBoxChequeMotivoDespesa;
    private javax.swing.JComboBox jComboBoxDespesaFuncionario;
    private javax.swing.JComboBox jComboBoxDespesaMotivo;
    private javax.swing.JComboBox jComboBoxFuncionarioLograduro;
    private javax.swing.JComboBox jComboBoxFuncionarioSexo;
    private javax.swing.JComboBox jComboBoxFuncionarioStatus;
    private javax.swing.JComboBox jComboBoxFuncionarioUf;
    private javax.swing.JComboBox jComboBoxLogradouroTipo;
    private javax.swing.JComboBox jComboBoxReceitaFuncionario;
    private javax.swing.JComboBox jComboBoxReceitaMotivoEntrada;
    private javax.swing.JComboBox jComboBoxReceitaSocio;
    private javax.swing.JComboBox jComboBoxSocioCategoriaSocio;
    private javax.swing.JComboBox jComboBoxSocioLogradouro;
    private javax.swing.JComboBox jComboBoxSocioSexo;
    private javax.swing.JComboBox jComboBoxSocioUF;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFormattedTextField jFormattedCNPJ;
    private javax.swing.JFormattedTextField jFormattedTextFieldChequeDataPagamento;
    private javax.swing.JFormattedTextField jFormattedTextFieldChequeDataVencimento;
    private javax.swing.JFormattedTextField jFormattedTextFieldDespesaData;
    private javax.swing.JFormattedTextField jFormattedTextFieldFuncionarioCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldFuncionarioDataContratacao;
    private javax.swing.JFormattedTextField jFormattedTextFieldFuncionarioDataEmissao;
    private javax.swing.JFormattedTextField jFormattedTextFieldFuncionarioDataNascimento;
    private javax.swing.JFormattedTextField jFormattedTextFieldSocioCPF;
    private javax.swing.JFormattedTextField jFormattedTextFieldSocioDataEmissao;
    private javax.swing.JFormattedTextField jFormattedTextFieldSocioDataMatricula;
    private javax.swing.JFormattedTextField jFormattedTextFieldSocioDataNascimento;
    private javax.swing.JFormattedTextField jFormattedTextFieldSocioDiaVencimento;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
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
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelCategoriaSocio;
    private javax.swing.JPanel jPanelCheques;
    private javax.swing.JPanel jPanelDespesa;
    private javax.swing.JPanel jPanelFunc;
    private javax.swing.JPanel jPanelLog;
    private javax.swing.JPanel jPanelReceitas;
    private javax.swing.JPanel jPanelSocio;
    private javax.swing.JPanel jPanelTaxas;
    private javax.swing.JPanel jPanelTipoDespesa;
    private javax.swing.JPanel jPanelTipoReceita;
    private javax.swing.JRadioButton jRadioButtonSocioAtivo;
    private javax.swing.JRadioButton jRadioButtonSocioInativo;
    private javax.swing.JCheckBox jRadioCPFCNPJ;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextAreaCategoriaSocioDescricao;
    private javax.swing.JTextArea jTextAreaChequeObservacoes;
    private javax.swing.JTextArea jTextAreaDespesaObservacao;
    private javax.swing.JTextArea jTextAreaFuncionarioObservacoes;
    private javax.swing.JTextArea jTextAreaLogradouroDescricao;
    private javax.swing.JTextArea jTextAreaReceitaObservacao;
    private javax.swing.JTextArea jTextAreaTaxasDescricao;
    private javax.swing.JTextArea jTextAreaTipoDespesaDescricao;
    private javax.swing.JTextArea jTextAreaTipoDespesaObservacao;
    private javax.swing.JTextArea jTextAreaTipoReceitaDescricao;
    private javax.swing.JTextArea jTextAreaTipoReceitaObservacao;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextFieldCategoriaSocioID;
    private javax.swing.JTextField jTextFieldCategoriaSocioNome;
    private javax.swing.JTextField jTextFieldChequeID;
    private javax.swing.JTextField jTextFieldChequeNumero;
    private javax.swing.JTextField jTextFieldChequeValor;
    private javax.swing.JTextField jTextFieldDespesaFavorecido;
    private javax.swing.JTextField jTextFieldDespesaID;
    private javax.swing.JTextField jTextFieldDespesaValor;
    private javax.swing.JTextField jTextFieldFuncionarioApelido;
    private javax.swing.JTextField jTextFieldFuncionarioBairro;
    private javax.swing.JTextField jTextFieldFuncionarioCargo;
    private javax.swing.JTextField jTextFieldFuncionarioCep;
    private javax.swing.JTextField jTextFieldFuncionarioCidade;
    private javax.swing.JTextField jTextFieldFuncionarioEmail;
    private javax.swing.JTextField jTextFieldFuncionarioID;
    private javax.swing.JTextField jTextFieldFuncionarioNome;
    private javax.swing.JTextField jTextFieldFuncionarioNomedame;
    private javax.swing.JTextField jTextFieldFuncionarioNomedopai;
    private javax.swing.JTextField jTextFieldFuncionarioNumero;
    private javax.swing.JTextField jTextFieldFuncionarioNumeroDaMatricula;
    private javax.swing.JTextField jTextFieldFuncionarioOrgaoExpedidor;
    private javax.swing.JTextField jTextFieldFuncionarioRgnumero;
    private javax.swing.JTextField jTextFieldFuncionarioSalario;
    private javax.swing.JTextField jTextFieldFuncionarioSobrenome;
    private javax.swing.JTextField jTextFieldFuncionarioTelefone;
    private javax.swing.JTextField jTextFieldLogradouroID;
    private javax.swing.JTextField jTextFieldLogradouroNome;
    private javax.swing.JTextField jTextFieldReceitaID;
    private javax.swing.JTextField jTextFieldReceitaValor;
    private javax.swing.JTextField jTextFieldSocioApelido;
    private javax.swing.JTextField jTextFieldSocioBairro;
    private javax.swing.JTextField jTextFieldSocioCEP;
    private javax.swing.JFormattedTextField jTextFieldSocioCep2;
    private javax.swing.JTextField jTextFieldSocioEmail;
    private javax.swing.JTextField jTextFieldSocioID;
    private javax.swing.JTextField jTextFieldSocioNome;
    private javax.swing.JTextField jTextFieldSocioNomeMae;
    private javax.swing.JTextField jTextFieldSocioNomePai;
    private javax.swing.JTextField jTextFieldSocioNumero;
    private javax.swing.JTextField jTextFieldSocioNumeroSocio;
    private javax.swing.JTextField jTextFieldSocioOrgaoExpedidor;
    private javax.swing.JTextField jTextFieldSocioRgNumero;
    private javax.swing.JTextField jTextFieldSocioSobrenome;
    private javax.swing.JTextField jTextFieldSocioTelefone;
    private javax.swing.JTextField jTextFieldTIpoDespesaID;
    private javax.swing.JTextField jTextFieldTIpoReceitaID;
    private javax.swing.JTextField jTextFieldTaxasID;
    private javax.swing.JTextField jTextFieldTaxasNome;
    private javax.swing.JTextField jTextFieldTaxasValor;
    private javax.swing.JTextField jTextFieldTipoDespesaNome;
    private javax.swing.JTextField jTextFieldTipoReceitaNome;
    // End of variables declaration//GEN-END:variables
}
