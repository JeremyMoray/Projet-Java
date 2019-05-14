package viewPackage;

import javax.swing.*;
import java.awt.*;

public class FenetreFermeture extends JFrame {

    private JLabel label;

    public FenetreFermeture(){
        super("Fermeture");
        setBounds((int)(FenetreMenu.getWindowWidth() * 0.40), (int)(FenetreMenu.getWindowHeight() * 0.40),
                (int)(FenetreMenu.getWindowWidth() * 0.20), (int)(FenetreMenu.getWindowHeight() * 0.20));

        setLayout(new GridBagLayout());

        label = new JLabel();
        this.add(label);

        setVisible(true);
    }

    public void setSeconde(int seconde){
        label.setText("Fermeture dans " + seconde);
    }
}
