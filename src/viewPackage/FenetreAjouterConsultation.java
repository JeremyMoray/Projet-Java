package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Consultation;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FenetreAjouterConsultation extends JFrame {

    private Soignant utilisateur;
    private Integer patient_id;
    private ApplicationController controller;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JSpinner jourSpinner, moisSpinner, anneeSpinner, heureSpinner, minuteSpinner;
    private JTextField observationsField;
    private JLabel observationLabel;
    private JButton enregistrerBouton, annulerBouton;
    private GregorianCalendar dateConsultation;
    private Consultation consultation;

    public FenetreAjouterConsultation(Soignant utilisateur, Integer patient_id){
        super("Ajouter une consultation");
        setBounds((int)(FenetreMenu.getWindowWidth() * 0.30), (int)(FenetreMenu.getWindowHeight() * 0.30),
                (int)(FenetreMenu.getWindowWidth() * 0.40), (int)(FenetreMenu.getWindowHeight() * 0.40));
        setController(new ApplicationController());
        this.utilisateur = utilisateur;
        this.patient_id = patient_id;
        this.setLayout(new GridBagLayout());
        dateConsultation = new GregorianCalendar();

        int jourActuel = GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH);
        int moisActuel = GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1;
        int anneeActuelle = GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);

        SpinnerModel modelJour = new SpinnerNumberModel(jourActuel, 1, 31, 1);
        SpinnerModel modelMois = new SpinnerNumberModel(moisActuel, 1, 12, 1);
        SpinnerModel modelAnnee = new SpinnerNumberModel(anneeActuelle, anneeActuelle, anneeActuelle + 10, 1);
        SpinnerModel modelHeure = new SpinnerNumberModel(12, 0, 23, 1);
        SpinnerModel modelMinute = new SpinnerNumberModel(0, 0, 59, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 15;
        gbc.ipady = 10;
        gbc.insets = new Insets(10,5,10,5);
        this.add(new JLabel("Date de consultation : "), gbc);

        gbc.gridx = 1;
        jourSpinner = new JSpinner(modelJour);
        this.add(jourSpinner, gbc);

        gbc.gridx = 2;
        this.add(new JLabel("/"), gbc);

        gbc.gridx = 3;
        moisSpinner = new JSpinner(modelMois);
        this.add(moisSpinner, gbc);

        gbc.gridx = 4;
        this.add(new JLabel("/"), gbc);

        gbc.gridx = 5;
        anneeSpinner = new JSpinner(modelAnnee);
        this.add(anneeSpinner, gbc);

        gbc.gridx = 6;
        gbc.insets = new Insets(10,30,10,5);
        heureSpinner = new JSpinner(modelHeure);
        this.add(heureSpinner, gbc);

        gbc.gridx = 7;
        gbc.insets = new Insets(10,5,10,5);
        this.add(new JLabel("H -"), gbc);

        gbc.gridx = 8;
        minuteSpinner = new JSpinner(modelMinute);
        this.add(minuteSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 0;
        gbc.insets = new Insets(10,5,10,5);
        observationLabel = new JLabel("Observations :");
        this.add(observationLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 500;
        gbc.gridwidth = 8;
        observationsField = new JTextField();
        this.add(observationsField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 50;
        gbc.gridwidth = 3;
        enregistrerBouton = new JButton("Enregistrer");
        ButtonListener enregistrerButtonListener = new ButtonListener();
        enregistrerBouton.addActionListener(enregistrerButtonListener);
        this.add(enregistrerBouton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        annulerBouton = new JButton("Annuler");
        ButtonListener annulerButtonListener = new ButtonListener();
        annulerBouton.addActionListener(annulerButtonListener);
        this.add(annulerBouton, gbc);

        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if(event.getSource() == enregistrerBouton){
                try{
                    try{
                        Integer jour = Integer.parseInt(jourSpinner.getValue().toString());
                        Integer mois = Integer.parseInt(moisSpinner.getValue().toString())-1;
                        Integer annee = Integer.parseInt(anneeSpinner.getValue().toString());
                        Integer heure = Integer.parseInt(heureSpinner.getValue().toString());
                        Integer minute = Integer.parseInt(minuteSpinner.getValue().toString());

                        if(jour == null){
                            throw new ChampsVideException("Date de consultation (Jour)");
                        }
                        if(mois == null){
                            throw new ChampsVideException("Date de consultation (Mois)");
                        }
                        if(annee == null){
                            throw new ChampsVideException("Date de consultation (Annee)");
                        }
                        if(heure == null){
                            throw new ChampsVideException("Date de consultation (Heure)");
                        }
                        if(minute == null){
                            throw new ChampsVideException("Date de consultation (Annee)");
                        }

                        Calendar dateActuelle = Calendar.getInstance();

                        if(jour < 0 || jour > 31){
                            throw new FormatDateException();
                        }
                        if(mois < 0 || mois > 12){
                            throw new FormatDateException();
                        }
                        if(annee < dateActuelle.get(Calendar.YEAR) || annee > dateActuelle.get(Calendar.YEAR) + 10){
                            throw new FormatDateException();
                        }
                        dateConsultation.set(GregorianCalendar.DAY_OF_MONTH, jour);
                        dateConsultation.set(GregorianCalendar.MONTH, mois);
                        dateConsultation.set(GregorianCalendar.YEAR, annee);
                        dateConsultation.set(GregorianCalendar.HOUR, heure);
                        dateConsultation.set(GregorianCalendar.MINUTE, minute);
                        dateConsultation.set(GregorianCalendar.SECOND, 0);
                        dateConsultation.set(GregorianCalendar.MILLISECOND, 0);

                        if(dateConsultation.get(GregorianCalendar.MONTH) != mois){
                            throw new FormatDateException();
                        }

                        if(dateConsultation.compareTo(dateActuelle) < 0){
                            throw new FormatDateException();
                        }
                    }
                    catch(NumberFormatException exception){
                        throw new FormatDateException();
                    }

                    if(observationsField.getText().length() > 250){
                        throw new CaracteresLimiteException("Observations");
                    }

                    consultation = new Consultation(utilisateur.getId(), patient_id, (observationsField.getText().isEmpty())?null:observationsField.getText(), dateConsultation);
                    controller.addConsultation(consultation);
                    JOptionPane.showMessageDialog(null, "La consultation a été ajoutée", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (AccesDBException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (ChampsVideException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (ObjetExistantException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (CaracteresLimiteException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (FormatDateException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            dispose();
        }
    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }
}

