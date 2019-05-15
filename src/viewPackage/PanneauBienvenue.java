package viewPackage;

import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;

public class PanneauBienvenue extends JPanel{

    private JLabel bienvenue;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PanneauBienvenue(){
        setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));
        setLayout(new GridBagLayout());

        bienvenue = new JLabel("<html><div style='text-align: center;'>Bienvenue " + MenuUtilisateur.getUtilisateurActuel().getPrenom() + " !<br />Sélectionnez votre choix dans le menu ci-dessus</div></html>");
        bienvenue.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20,20,20,20);
        this.add(bienvenue, gbc);
    }
}
