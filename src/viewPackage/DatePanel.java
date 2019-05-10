package viewPackage;

import javax.swing.*;
import java.awt.*;

public class DatePanel extends JPanel{

    private JTextField jourField, moisField, anneeField;

    private GridBagConstraints gbc = new GridBagConstraints();

    public DatePanel(){
        this.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 40;
        gbc.ipady = 10;
        gbc.insets = new Insets(0,10,0,10);
        jourField = new JTextField();
        this.add(jourField, gbc);

        gbc.gridx = 1;
        this.add(new JLabel("/"));

        gbc.gridx = 2;
        moisField = new JTextField();
        this.add(moisField, gbc);

        gbc.gridx = 3;
        this.add(new JLabel("/"));

        gbc.gridx = 4;
        anneeField = new JTextField();
        this.add(anneeField, gbc);
    }

    public String getJourField() {
        return jourField.getText();
    }

    public String getMoisField() {
        return moisField.getText();
    }

    public String getAnneeField() {
        return anneeField.getText();
    }

    public void setJourField(String jour) {
        this.jourField.setText(jour);
    }

    public void setMoisField(String mois) {
        this.moisField.setText(mois);
    }

    public void setAnneeField(String annee) {
        this.anneeField.setText(annee);
    }
}
