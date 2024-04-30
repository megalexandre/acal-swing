package br.org.acal.application.legacy.ui.telas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.UnsupportedLookAndFeelException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class Splash extends JWindow{
         
    ImageIcon image;
    JLabel label;
    JProgressBar progress;
     
    public Splash(){
        
        label = new JLabel();
        image = new ImageIcon(getClass().getResource("/images/acal.png"));
        label.setIcon(image);
        progress = new JProgressBar();
        progress.setPreferredSize(new Dimension(640,20));
        progress.setBackground(Color.WHITE);
        progress.setString("Carregando");
        progress.setStringPainted(true);
        progress.setForeground(Color.ORANGE);
     
        new Thread(){
       
            @Override
            public void run(){
                
                int i = 0;
                while(i < 101){
                    
                    progress.setValue(i);
                    i++;
                }
               // new TelaLogin().setVisible(true);
                dispose();
            }
           }.start();
     
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/4 ,   
        Toolkit.getDefaultToolkit().getScreenSize().height/5 );  
        pack();
        setVisible(true);
    }
    
    
    private void testarConexao1() {
        new Thread(() -> {
          Session session = null;
          try {
              
            progress.setString("Testando Conexão com o Banco de Dados");
            
              Configuration configuration = new Configuration();
              configuration.configure("hibernate.cfg.xml");
              session = configuration.buildSessionFactory().openSession();
            
          } catch (HibernateException e) {
                JOptionPane.showMessageDialog(null, "Erro de conexão ao banco de dados: " + e.getMessage());
                System.exit(1);
          } finally {
            if (session != null) {
              session.close();
            }
          }
          progress.setString("Conexão bem sucedida...");
        }).start();
    }
    
    public static void main(String[] args) {
        
        try {
            Properties p = new Properties();
            p.put("logoString", "ACAL2000");
            
            
            com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme(p);
           
            javax.swing.UIManager.setLookAndFeel(new com.jtattoo.plaf.luna.LunaLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            System.exit(1);
        }  
        
        Splash splash = new Splash();
        splash.testarConexao1();   
    }
}
