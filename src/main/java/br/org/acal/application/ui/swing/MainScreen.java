package br.org.acal.application.ui.swing;


import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.Locale;

@Component
public class MainScreen extends JFrame {

    private JPanel rootPanel;
    private JMenuBar mainMenu;
    private JPanel body;
    private final MessageSource messageSource;

    private final SearchScreen searchScreen;

    public MainScreen(
        MessageSource messageSource,
        SearchScreen searchScreen
    ){
        this.messageSource = messageSource;
        this.searchScreen = searchScreen;
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            EventQueue.invokeLater(() -> {
                init();
                super.setVisible(true);
            });
        } else {
            super.setVisible(false);
        }
    }

    private void init(){
        setTitle(messageSource.getMessage("app.login.title", null, Locale.getDefault()));
        createMenuBar();
        setContentPane(rootPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void createMenuBar(){
        JMenu menu = new JMenu(
           "Menu"
        );

        JMenu registrations = new JMenu("Cadastros");
        Arrays.stream(registrationMenuItem()).toList().forEach(item ->{
            JMenuItem jMenuItem = new JMenuItem(item);
            jMenuItem.addActionListener(e -> selectScreen(e.getActionCommand()));
            registrations.add(jMenuItem);
            }
        );

        JMenu invoices = new JMenu("Contas");
        Arrays.stream(invoiceMenuItem()).toList().forEach(item ->
            invoices.add(new JMenuItem(item))
        );

        menu.add(registrations);
        menu.add(invoices);

        mainMenu.add(menu);
    }

    private void selectScreen(String screen){
        searchScreen.setVisible(true);
    }

    private String[] invoiceMenuItem(){
        return new String[]{
            "Contas [Simplificado]",
            "Contas [Avançado]",
            "Editor",
            "Cobrança"
        };
    }

    private String[] registrationMenuItem() {
        return new String[]{
            "Sócio",
            "Logradouro",
            "Funcionario",
            "Receita",
            "Categoria Sócio",
            "Tipo de Despesa",
            "Tipo de Receita",
            "Receita",
            "Contrato",
            "Taxa"
        };
    }
}
