package br.org.acal.application.ui.jform;

import org.springframework.stereotype.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;


@Component
public class LoginV2 extends JFrame {
    public LoginV2() {
        initComponents();
        setSizes();
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            EventQueue.invokeLater(() -> {
                super.setVisible(true);
            });
        } else {
            super.setVisible(false);
        }
    }

    private void setSizes() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.5);
        int height = (int) (screenSize.height * 0.5);
        setSize(width, height);

        Dimension minimal = new Dimension(300, 200);
        Dimension maximum = new Dimension(1680, 1024);
        this.setMinimumSize(minimal);
        this.setMaximumSize(maximum);
        Dimension dimension = new Dimension((int) (300), (int) (300));
        totalPanel.setSize(dimension);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        totalPanel = new JPanel();

        //======== this ========
        setTitle("Acal");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== totalPanel ========
        {
            totalPanel.setBackground(new Color(0xbbbbbb));
            totalPanel.setForeground(new Color(0x3c3f41));
            totalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            totalPanel.setLayout(new GridLayout());
        }
        contentPane.add(totalPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel totalPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}


/*
 private void initComponents() {
        totalPanel = new JPanel();
        setTitle("Acal");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Centralizando totalPanel usando GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(totalPanel, new GridBagConstraints());
        contentPane.add(panel, BorderLayout.CENTER);

        totalPanel.setBackground(new Color(0xbbbbbb));
        totalPanel.setForeground(new Color(0x3c3f41));
        totalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        pack();
    }

* */