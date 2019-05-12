package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.Medicament;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FenetreAjouterTraitement extends JFrame {

    private Soignant utilisateur;
    private Integer patient_id;
    private JComboBox medicamentCbx;
    private ArrayList<Medicament> listeObjetMedicaments;
    private GregorianCalendar dateDébut, dateFin;
    private JSpinner jourSpinner, moisSpinner, anneeSpinner;
    private JTextField frequenceField;
    private ApplicationController controller;
    private GridBagConstraints gbc = new GridBagConstraints();

    public FenetreAjouterTraitement(Soignant utilisateur, Integer patient_id){
        super("Ajouter un traitement");
        setBounds((int)(FenetreMenu.getWindowWidth() * 0.30), (int)(FenetreMenu.getWindowHeight() * 0.30),
                (int)(FenetreMenu.getWindowWidth() * 0.40), (int)(FenetreMenu.getWindowHeight() * 0.40));
        setController(new ApplicationController());

        this.utilisateur = utilisateur;
        this.patient_id = patient_id;
        this.setLayout(new GridBagLayout());
        dateDébut = new GregorianCalendar();
        dateFin = new GregorianCalendar();

        int jourActuel = GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH);
        int moisActuel = GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1;
        int anneeActuelle = GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);

        SpinnerModel modelJour = new SpinnerNumberModel(jourActuel, 1, 31, 1);
        SpinnerModel modelMois = new SpinnerNumberModel(moisActuel, 1, 12, 1);
        SpinnerModel modelAnnee = new SpinnerNumberModel(anneeActuelle, anneeActuelle, anneeActuelle + 100, 1);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 15;
        gbc.ipady = 10;
        gbc.insets = new Insets(10,10,10,10);
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

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 0;
        gbc.insets = new Insets(10,0,10,0);

        this.add(new JLabel("Frequence :"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 300;
        gbc.gridwidth = 4;
        frequenceField = new JTextField();
        this.add(frequenceField, gbc);

        setVisible(true);
    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }
}
