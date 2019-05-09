package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.Patient;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauInscriptionPatient extends JPanel{

    private JLabel patientLabel, numeroNationalLabel, nomLabel, prenomLabel, nbEnfantsLabel, dateNaissanceLabel, numTelFixeLabel,
            numTelMobileLabel, remarqueLabel, aSurveillerLabel, conseilsLabel, donnerEtatLabel, besoinAvalLabel,
            acharnementTherapeuthiqueLabel, causeDecesPereLabel, causeDecesMereLabel, primeAnuelleLabel, mutualiteLabel;

    private JTextField numeroNationalField, nomField, prenomField, nbEnfantsField, numTelFixeField, numTelMobileField,
            remarqueField, aSurveillerField, conseilsField, causeDecesPereField, causeDecesMereField, primeAnuelleField;

    private JFormattedTextField dateNaissanceField;

    private JCheckBox donnerEtatBox, besoinAvalBox, acharnementTherapeuthiqueBox;

    private JComboBox mutualites;

    private JButton inscriptionBouton, reinitialiserBouton, retourBouton;
    private Container frameContainer;
    private ApplicationController controller;
    private Patient patient;
    private Soignant utilisateur;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PanneauInscriptionPatient(Container frameContainer, Soignant utilisateur) {
        this.utilisateur = utilisateur;
        this.frameContainer = frameContainer;
        setController(new ApplicationController());
        setLayout(new GridBagLayout());

        patientLabel = new JLabel("Inscrivez un patient");
        patientLabel.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 0;
        gbc.ipady = 10;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(patientLabel, gbc);

        numeroNationalLabel = new JLabel("Numéro national*");
        numeroNationalLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        this.add(numeroNationalLabel, gbc);

        nomLabel = new JLabel("Nom*");
        nomLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 2;
        this.add(nomLabel, gbc);

        prenomLabel = new JLabel("Prénom*");
        prenomLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 3;
        this.add(prenomLabel, gbc);

        nbEnfantsLabel = new JLabel("NbEnfants*");
        nbEnfantsLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 4;
        this.add(nbEnfantsLabel, gbc);

        dateNaissanceLabel = new JLabel("Date de naissance*");
        dateNaissanceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 5;
        this.add(dateNaissanceLabel, gbc);

        numTelFixeLabel = new JLabel("(Numéro tel. fixe)");
        numTelFixeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 6;
        this.add(numTelFixeLabel, gbc);

        numTelMobileLabel = new JLabel("(Numéro tel. mobile)");
        numTelMobileLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 7;
        this.add(numTelMobileLabel, gbc);

        remarqueLabel = new JLabel("(Remarque)");
        remarqueLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 8;
        this.add(remarqueLabel, gbc);

        aSurveillerLabel = new JLabel("(A surveiller)");
        aSurveillerLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 9;
        this.add(aSurveillerLabel, gbc);

        conseilsLabel = new JLabel("(Conseils)");
        conseilsLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 10;
        this.add(conseilsLabel, gbc);

        donnerEtatLabel = new JLabel("Donner état*");
        donnerEtatLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 11;
        this.add(donnerEtatLabel, gbc);

        besoinAvalLabel = new JLabel("Besoin aval*");
        besoinAvalLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 12;
        this.add(besoinAvalLabel, gbc);

        acharnementTherapeuthiqueLabel = new JLabel("Archanement thérapeuthique*");
        acharnementTherapeuthiqueLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 13;
        this.add(acharnementTherapeuthiqueLabel, gbc);

        causeDecesPereLabel = new JLabel("(Cause décès du père)");
        causeDecesPereLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 14;
        this.add(causeDecesPereLabel, gbc);

        causeDecesMereLabel = new JLabel("(Cause décès de la mère)");
        causeDecesMereLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 15;
        this.add(causeDecesMereLabel, gbc);

        primeAnuelleLabel = new JLabel("(Prime anuelle)");
        primeAnuelleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 16;
        this.add(primeAnuelleLabel, gbc);

        mutualiteLabel = new JLabel("(Mutualité)");
        mutualiteLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 17;
        this.add(mutualiteLabel, gbc);

        numeroNationalField = new JTextField();
        gbc.ipadx = 200;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(numeroNationalField, gbc);

        nomField = new JTextField();
        gbc.gridy = 2;
        this.add(nomField, gbc);

        prenomField = new JTextField();
        gbc.gridy = 3;
        this.add(prenomField, gbc);

        nbEnfantsField = new JTextField();
        gbc.gridy = 4;
        this.add(nbEnfantsField, gbc);

        dateNaissanceField = new JFormattedTextField();
        gbc.gridy = 5;
        this.add(dateNaissanceField, gbc);

        numTelFixeField = new JFormattedTextField();
        gbc.gridy = 6;
        this.add(numTelFixeField, gbc);

        numTelMobileField = new JTextField();
        gbc.gridy = 7;
        this.add(numTelMobileField, gbc);

        remarqueField = new JTextField();
        gbc.gridy = 8;
        this.add(remarqueField, gbc);

        aSurveillerField = new JTextField();
        gbc.gridy = 9;
        this.add(aSurveillerField, gbc);

        conseilsField = new JTextField();
        gbc.gridy = 10;
        this.add(conseilsField, gbc);

        donnerEtatBox = new JCheckBox();
        gbc.gridy = 11;
        this.add(donnerEtatBox, gbc);

        besoinAvalBox = new JCheckBox();
        gbc.gridy = 12;
        this.add(besoinAvalBox, gbc);

        acharnementTherapeuthiqueBox = new JCheckBox();
        gbc.gridy = 13;
        this.add(acharnementTherapeuthiqueBox, gbc);

        causeDecesPereField = new JTextField();
        gbc.gridy = 14;
        this.add(causeDecesPereField, gbc);

        causeDecesMereField = new JTextField();
        gbc.gridy = 15;
        this.add(causeDecesMereField, gbc);

        primeAnuelleField = new JTextField();
        gbc.gridy = 16;
        this.add(primeAnuelleField, gbc);

        String[ ] values = { "1", "Psychiatre", "Chirurgien", "Autre (Veuillez spécifier)" };

        mutualites = new JComboBox(values);
        gbc.ipadx = 35;
        gbc.ipady = 5;
        gbc.gridy = 17;
        this.add(mutualites, gbc);

        inscriptionBouton = new JButton("Valider");
        ButtonListener inscriptionListener = new ButtonListener();
        inscriptionBouton.addActionListener(inscriptionListener);
        gbc.gridx = 0;
        gbc.gridy = 18;
        gbc.ipadx = 80;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(inscriptionBouton, gbc);

        reinitialiserBouton = new JButton("Réinitialiser");
        ButtonListener reinitialiserListener = new ButtonListener();
        reinitialiserBouton.addActionListener(reinitialiserListener);
        gbc.gridx = 1;
        gbc.gridy = 18;
        this.add(reinitialiserBouton, gbc);

        retourBouton = new JButton("Retour");
        ButtonListener retourListener = new ButtonListener();
        retourBouton.addActionListener(retourListener);
        gbc.gridx = 0;
        gbc.gridy = 19;
        gbc.ipadx = 250;
        gbc.gridwidth = 2;
        this.add(retourBouton, gbc);
    }

    public void setController(ApplicationController applicationController){
        controller = applicationController;
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {

            if(event.getSource() == inscriptionBouton){

            }

            if(event.getSource() == reinitialiserBouton){

            }

            if(event.getSource() == retourBouton){
                frameContainer.removeAll();
                frameContainer.add(new PageConnexion(frameContainer));
                frameContainer.revalidate();
                frameContainer.repaint();
            }
        }
    }
}


