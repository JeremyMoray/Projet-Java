package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PageConnexion extends JPanel {

    private GridBagConstraints gbc = new GridBagConstraints();
    private JLabel bienvenue;

    public PageConnexion(Container frameContainer){
        frameContainer.setLayout(new GridBagLayout());
        bienvenue = new JLabel("Bienvenue ! Connectez-vous à votre compte :");
        bienvenue.setFont(new Font("Arial", Font.BOLD, 27));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,100,0);
        frameContainer.add(bienvenue, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.insets = new Insets(0,0,200,0);
        frameContainer.add(new PanneauConnexion(frameContainer), gbc);
    }
}

