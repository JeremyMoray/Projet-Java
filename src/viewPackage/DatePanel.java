package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatePanel extends JPanel{

    private JSpinner jourSpinner, moisSpinner, anneeSpinner;

    private GridBagConstraints gbc = new GridBagConstraints();

    public DatePanel(){
        this.setLayout(new GridBagLayout());

        int anneeActuelle = GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
        SpinnerModel modelJour = new SpinnerNumberModel(1, 1, 31, 1);
        SpinnerModel modelMois = new SpinnerNumberModel(1, 1, 12, 1);
        SpinnerModel modelAnnee = new SpinnerNumberModel(anneeActuelle, anneeActuelle - 150, anneeActuelle, 1);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = -5;
        gbc.ipady = 10;
        gbc.insets = new Insets(0,10,0,10);
        jourSpinner = new JSpinner(modelJour);
        this.add(jourSpinner, gbc);

        gbc.gridx = 1;
        this.add(new JLabel("/"));

        gbc.gridx = 2;
        moisSpinner = new JSpinner(modelMois);
        this.add(moisSpinner, gbc);

        gbc.gridx = 3;
        this.add(new JLabel("/"));

        gbc.gridx = 4;
        anneeSpinner = new JSpinner(modelAnnee);
        this.add(anneeSpinner, gbc);
    }

    public String getJourField() {
        return jourSpinner.getValue().toString();
    }

    public String getMoisField() {
        return moisSpinner.getValue().toString();
    }

    public String getAnneeField() {
        return anneeSpinner.getValue().toString();
    }

    public void setJourField(String jour) {
        this.jourSpinner.setValue(Integer.parseInt(jour));
    }

    public void setMoisField(String mois) {
        this.moisSpinner.setValue(Integer.parseInt(mois));
    }

    public void setAnneeField(String annee) {
        this.anneeSpinner.setValue(Integer.parseInt(annee));
    }

    public void setJourEditable(Boolean isEditable) {
        this.jourSpinner.setEnabled(isEditable);
    }

    public void setMoisEditable(Boolean isEditable) {
        this.moisSpinner.setEnabled(isEditable);
    }

    public void setAnneeEditable(Boolean isEditable) {
        this.anneeSpinner.setEnabled(isEditable);
    }
}
