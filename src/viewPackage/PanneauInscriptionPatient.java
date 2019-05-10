package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Mutualite;
import modelPackage.Patient;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.zip.DataFormatException;

public class PanneauInscriptionPatient extends JPanel{

    private JLabel patientLabel, numeroNationalLabel, nomLabel, prenomLabel, nbEnfantsLabel, dateNaissanceLabel, numTelFixeLabel,
            numTelMobileLabel, remarqueLabel, aSurveillerLabel, conseilsLabel, donnerEtatLabel, besoinAvalLabel,
            acharnementTherapeuthiqueLabel, causeDecesPereLabel, causeDecesMereLabel, primeAnuelleLabel, mutualiteLabel;

    private JTextField numeroNationalField, nomField, prenomField, nbEnfantsField, numTelFixeField, numTelMobileField,
            remarqueField, aSurveillerField, conseilsField, causeDecesPereField, causeDecesMereField, primeAnuelleField;

    private DatePanel datePanel;
    private JCheckBox donnerEtatBox, besoinAvalBox, acharnementTherapeuthiqueBox;
    private JComboBox mutualites;
    private JButton inscriptionBouton, reinitialiserBouton, retourBouton;
    private ArrayList<Mutualite> listeObjetMutualites;
    private Integer mutualite_id;
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

        nbEnfantsLabel = new JLabel("Nb. d'enfants*");
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
        gbc.weightx = 200;
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

        datePanel = new DatePanel();
        gbc.ipadx = 0;
        gbc.gridy = 5;
        this.add(datePanel, gbc);

        numTelFixeField = new JTextField();
        gbc.ipadx = 200;
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

        try{
            listeObjetMutualites = controller.getAllMutualites();
            String[] listeMutualites = new String[listeObjetMutualites.size()];
            for(int i = 0; i < listeMutualites.length; i++) {
                listeMutualites[i] = controller.getAllMutualites().get(i).getLibelle() + " (" + controller.getAllMutualites().get(i).getDiminutif() + ")";
            }
            mutualites = new JComboBox(listeMutualites);
            if(listeMutualites.length != 0){
                mutualite_id = listeObjetMutualites.get(mutualites.getSelectedIndex()).getId();
                ComboBoxListener mutualiteListener = new ComboBoxListener();
                mutualites.addItemListener(mutualiteListener);
            }
            gbc.ipadx = 35;
            gbc.ipady = 5;
            gbc.gridy = 17;
            this.add(mutualites, gbc);
        }
        catch (AccesDBException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (ChampsVideException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (CaracteresLimiteException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

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
                try{
                    if(numeroNationalField.getText().isEmpty()){
                        throw new ChampsVideException("Numéro national");
                    }
                    if(!numeroNationalField.getText().matches("[0-9]*")){
                        throw new FormatNombreException("Numéro national");
                    }
                    if(numeroNationalField.getText().length() != 11){
                        throw new CodeInvalideException("Numéro national", "Veuillez entrer un numéro national composé de 11 chiffres uniquement");
                    }

                    if(nomField.getText().isEmpty()){
                        throw new ChampsVideException("Nom");
                    }
                    if(nomField.getText().length() > 30){
                        throw new CaracteresLimiteException("Nom");
                    }

                    if(prenomField.getText().isEmpty()){
                        throw new ChampsVideException("Prénom");
                    }
                    if(prenomField.getText().length() > 30){
                        throw new CaracteresLimiteException("Prénom");
                    }

                    if(nbEnfantsField.getText().isEmpty()){
                        throw new ChampsVideException("Nb. d'enfants");
                    }
                    try{
                        if(Integer.parseInt(nbEnfantsField.getText()) < 0){
                            throw new FormatNombreException("Nb. d'enfants");
                        }
                    }
                    catch(NumberFormatException exception){
                        throw new FormatNombreException("Nb. d'enfants");
                    }

                    try{
                        Integer jour = Integer.parseInt(datePanel.getJourField());
                        Integer mois = Integer.parseInt(datePanel.getMoisField())-1;
                        Integer annee = Integer.parseInt(datePanel.getAnneeField());

                        if(jour == null){
                            throw new ChampsVideException("Date de naissance (Jour)");
                        }
                        if(mois == null){
                            throw new ChampsVideException("Date de naissance (Mois)");
                        }
                        if(annee == null){
                            throw new ChampsVideException("Date de naissance (Annee)");
                        }

                        Calendar dateActuelle = Calendar.getInstance();

                        if(jour < 0 || jour > 31){
                            throw new FormatDateException();
                        }
                        if(mois < 0 || mois > 31){
                            throw new FormatDateException();
                        }
                        if(annee < 1900 || annee > dateActuelle.get(Calendar.YEAR)){
                            throw new FormatDateException();
                        }

                        GregorianCalendar dateNaissance = new GregorianCalendar();
                        dateNaissance.set(GregorianCalendar.DAY_OF_MONTH, jour);
                        dateNaissance.set(GregorianCalendar.MONTH, mois);
                        dateNaissance.set(GregorianCalendar.YEAR, annee);

                        if(dateNaissance.compareTo(dateActuelle) > 0){
                            throw new FormatDateException();
                        }
                    }
                    catch(NumberFormatException exception){
                        throw new FormatDateException();
                    }

                    if(numTelFixeField.getText().length() > 20){
                        throw new CaracteresLimiteException("Numéro tel. fixe");
                    }

                    if(numTelMobileField.getText().length() > 20){
                        throw new CaracteresLimiteException("Numéro tel. mobile");
                    }

                    if(remarqueField.getText().length() > 250){
                        throw new CaracteresLimiteException("Remarque");
                    }

                    if(aSurveillerField.getText().length() > 250){
                        throw new CaracteresLimiteException("A surveiller");
                    }

                    if(conseilsField.getText().length() > 250){
                        throw new CaracteresLimiteException("Conseils");
                    }

                    if(causeDecesPereField.getText().length() > 250){
                        throw new CaracteresLimiteException("Cause décès père");
                    }

                    if(causeDecesMereField.getText().length() > 250){
                        throw new CaracteresLimiteException("Cause décès mère");
                    }

                    try{
                        if(!primeAnuelleField.getText().isEmpty()){
                            if(Double.parseDouble(primeAnuelleField.getText()) < 0){
                                throw new FormatNombreException("Prime anuelle");
                            }
                        }
                    }
                    catch(NumberFormatException exception){
                        throw new FormatNombreException("Prime anuelle");
                    }

                    GregorianCalendar dateNaissance = new GregorianCalendar();
                    dateNaissance.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(datePanel.getJourField()));
                    dateNaissance.set(GregorianCalendar.MONTH, Integer.parseInt(datePanel.getMoisField()));
                    dateNaissance.set(GregorianCalendar.YEAR, Integer.parseInt(datePanel.getAnneeField()));

                    patient = new Patient(
                            null,
                            numeroNationalField.getText(),
                            nomField.getText(),
                            prenomField.getText(),
                            Integer.parseInt(nbEnfantsField.getText()),
                            dateNaissance,
                            numTelFixeField.getText(),
                            numTelMobileField.getText(),
                            (remarqueField.getText().isEmpty()?null:remarqueField.getText()),
                            (aSurveillerField.getText().isEmpty()?null:aSurveillerField.getText()),
                            (conseilsField.getText().isEmpty()?null:conseilsField.getText()),
                            donnerEtatBox.isSelected(),
                            besoinAvalBox.isSelected(),
                            acharnementTherapeuthiqueBox.isSelected(),
                            (causeDecesPereField.getText().isEmpty()?null:causeDecesPereField.getText()),
                            (causeDecesMereField.getText().isEmpty()?null:causeDecesMereField.getText()),
                            (primeAnuelleField.getText().isEmpty()?0:Double.parseDouble(primeAnuelleField.getText())),
                            mutualite_id
                    );

                    controller.addPatient(patient);

                    frameContainer.removeAll();
                    frameContainer.add(new MenuUtilisateur(frameContainer, utilisateur));
                    frameContainer.revalidate();
                    frameContainer.repaint();
                    JOptionPane.showMessageDialog(null, "Le patient a été ajouté", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (AccesDBException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (ChampsVideException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (CaracteresLimiteException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (FormatNombreException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (CodeInvalideException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (FormatDateException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
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

    private class ComboBoxListener implements ItemListener {
        public void itemStateChanged( ItemEvent event){
            mutualite_id = listeObjetMutualites.get(mutualites.getSelectedIndex()).getId();
        }
    }
}


