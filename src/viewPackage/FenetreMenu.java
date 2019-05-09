package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AccesDBException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FenetreMenu extends JFrame{

    private static final int WINDOWWIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int WINDOWHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final Color BORDER_THEME = new Color(0xFF3F46);
    private ApplicationController controller;

    public FenetreMenu(){
        super("Gestion des carnets patient");
        setBounds((int)(WINDOWWIDTH*0.10), (int)(WINDOWHEIGHT * 0.10), (int)(WINDOWWIDTH * 0.80), (int)(WINDOWHEIGHT * 0.80));
        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                try{
                    controller.closeConnection();
                }
                catch (AccesDBException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    System.exit(0);
                }
            }
        } );

        new PageConnexion(this.getContentPane());

        setVisible(true);
    }

    public static final int getWindowWidth(){
        return WINDOWWIDTH;
    }

    public static final int getWindowHeight(){
        return WINDOWHEIGHT;
    }

    public static final Color getBorderTheme(){
        return BORDER_THEME;
    }
}
