package br.org.acal.application.ui.swing;

import br.org.acal.infra.AppMessage;
import org.springframework.stereotype.Component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import static java.awt.Color.WHITE;
@Component
public class SearchScreen extends JFrame {

    private final AppMessage message;
    private JPanel rootPanel;
    private JPanel panel80;
    private JPanel panel20;

    public SearchScreen(AppMessage message){
        this.message = message;
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
        createRootPanel();
        createPanels();
        setLocationRelativeTo(null);
    }

    private void createRootPanel(){
        setTitle(message.message("app.search.person.title"));
        setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.8);
        int height = (int) (screenSize.height * 0.8);
        setSize(width, height);

        setContentPane(rootPanel);
    }

    public void createPanels(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int rootHeight = (int) (screenSize.height * 0.8);
        panel80.setBackground(WHITE);
        panel20.setBackground(Color.LIGHT_GRAY);
    }

}

