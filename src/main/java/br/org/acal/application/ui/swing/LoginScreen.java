package br.org.acal.application.ui.swing;

import org.springframework.stereotype.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

@Component
public class LoginScreen extends JFrame {
    private JPanel outher;

    public LoginScreen(){
        setTitle("ACAL, Login.");
        setSize(new Dimension(1024, 768));
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        setBackground(Color.RED);  // Optional: Set background for JFrame

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        rootPanel.setPreferredSize(new Dimension(200, 200));  // Square panel
        rootPanel.setBackground(Color.WHITE);

        rootPanel.setMinimumSize(rootPanel.getPreferredSize());
        JPanel spacerPanel = new JPanel();
        spacerPanel.setBackground(new Color(0, 0, 0, 0));  // Transparent

        add(rootPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            EventQueue.invokeLater(() ->  super.setVisible(true));
        } else {
            super.setVisible(false);
        }
    }

    /*
    private final LoginUseCase loginUseCase;
    private final AppMessage appMessage;
    private final MainScreen mainScreen;

    public LoginScreen(
            AppMessage appMessage,
            MainScreen mainScreen,
            LoginUseCase loginUseCase
    ) {
        this.mainScreen = mainScreen;
        this.appMessage = appMessage;
        this.loginUseCase = loginUseCase;
    }

    private void login(){
        User user = User.builder().username(getUsername()).password(getPassword()).build();
        if(loginUseCase.execute(user)){
            mainScreen.setVisible(true);
            this.dispose();
        } else {
            errorOnLogin();
        }
    }


    private void errorOnLogin(){
        JOptionPane.showMessageDialog(null, appMessage.message("app.login.error"), "Error", ERROR_MESSAGE);
        //setUsername(null);
        //setPassword(null);
    }



    private void init(){
        var welcome = appMessage.message("app.login.welcome");
        var username = appMessage.message("app.login.username");
        var password = appMessage.message("app.login.password");

        welcomeLabel.setText(welcome);
        usernameLabel.setText(username);
        passwordLabel.setText(password);
        setUsername("alexandre");
        setPassword("123");

    }
    private void createRootPanel() {

        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
        setTitle(appMessage.message("app.login.title"));

        setSize(dimension);
        setLocationRelativeTo(null);

        getContentPane().add(rootPanel);
        setupListeners();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        /*

    }

        Dimension halfScreen = new Dimension(width, height);
        Dimension halfAndAHalfScreen = new Dimension(width/2, height/2);

        setTitle("panel vermelho");
        setBackground(Color.RED);
        setSize(halfScreen);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JLabel("Hi Mom", SwingConstants.CENTER),  BorderLayout.NORTH);
        centerPanel.add(new JButton("Click Me"), BorderLayout.CENTER);
        centerPanel.setBackground(Color.BLUE);
        centerPanel.setPreferredSize(halfAndAHalfScreen);

        add(centerPanel);


    private void setupListeners(){
       // confirmButton.addActionListener(e -> login());
       // usernameField.addActionListener(e -> login());
       // passwordField.addActionListener(e -> login());
    }

    private String getUsername(){
        throw new RuntimeException("");
        //return usernameField.getText();
    }
    private void setUsername(String text){
        throw new RuntimeException("");
       // usernameField.setText(text);
    }
    private String getPassword(){
        throw new RuntimeException("");
        //return new String(passwordField.getPassword());
    }
    private void setPassword(String password){
        //passwordField.setText(password);
    }


    private JPasswordField passwordField;
    private JButton confirmButton;
    private JTextField usernameField;
    private JLabel welcomeLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    */
}
