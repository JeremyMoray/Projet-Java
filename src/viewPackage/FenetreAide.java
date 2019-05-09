package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreAide extends JFrame {

    private JButton button;
    private GridBagConstraints gbc = new GridBagConstraints();

    public FenetreAide(){
        super("Aide");
        setBounds((int)(FenetreMenu.getWindowWidth() * 0.30), (int)(FenetreMenu.getWindowHeight() * 0.30),
                (int)(FenetreMenu.getWindowWidth() * 0.40), (int)(FenetreMenu.getWindowHeight() * 0.40));

        JLabel label = new JLabel("Ajouter vous même une mutualité, un patient, un médicament, " +
                "une allergie, etc..., dans les menus correspondant");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        button = new JButton("Quitter");
        ButtonListener listener = new ButtonListener();
        button.addActionListener(listener);

        this.setLayout(new GridBagLayout());
        gbc.insets = new Insets(20,0,20,0);
        gbc.ipadx = 20;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(label, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(button, gbc);

        setVisible(true);
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event) {
            dispose();
        }
    }
}
