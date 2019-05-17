package viewPackage;

import exceptionPackage.ChampsVideException;
import exceptionPackage.FormatDateException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PanneauRechercheConsultations extends JPanel{

    private GregorianCalendar dateConsultation;
    private JLabel rechercherLabel, dateMinLabel, dateConsultationLabel;
    private JCheckBox contrainteDateBox;
    private JButton rechercherBouton;
    private JSpinner jourSpinner, moisSpinner, anneeSpinner;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PanneauRechercheConsultations(){
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));

        rechercherLabel = new JLabel("Recherchez la prime anuelle par patient");
        rechercherLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 0;
        gbc.ipady = 10;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridwidth = 6;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(rechercherLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        dateMinLabel = new JLabel("Date minimum de consultation ? ");
        dateMinLabel.setFont(new Font("Arial", Font.BOLD, 15));
        this.add(dateMinLabel, gbc);

        gbc.gridy = 2;
        dateConsultationLabel = new JLabel("Date de consultation :");
        dateConsultationLabel.setFont(new Font("Arial", Font.BOLD, 15));
        this.add(dateConsultationLabel, gbc);

        int jourActuel = GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH);
        int moisActuel = GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1;
        int anneeActuelle = GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);

        SpinnerModel modelJour = new SpinnerNumberModel(jourActuel, 1, 31, 1);
        SpinnerModel modelMois = new SpinnerNumberModel(moisActuel, 1, 12, 1);
        SpinnerModel modelAnnee = new SpinnerNumberModel(anneeActuelle, anneeActuelle - 100, anneeActuelle + 100, 1);

        gbc.gridy = 1;
        gbc.gridx = 1;
        contrainteDateBox = new JCheckBox();
        contrainteDateBox.setSelected(true);
        CheckBoxListener dateListener = new CheckBoxListener();
        contrainteDateBox.addItemListener(dateListener);
        this.add(contrainteDateBox, gbc);

        gbc.gridy = 2;
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

        rechercherBouton = new JButton("Rechercher");
        ButtonListener rechercherListener = new ButtonListener();
        rechercherBouton.addActionListener(rechercherListener);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 6;
        gbc.ipadx = 0;
        this.add(rechercherBouton, gbc);
    }

    private class CheckBoxListener implements ItemListener
    {
        public void itemStateChanged(ItemEvent event) {
            if(event.getStateChange() == ItemEvent.SELECTED){
                jourSpinner.setEnabled(true);
                moisSpinner.setEnabled(true);
                anneeSpinner.setEnabled(true);
            }
            else{
                jourSpinner.setValue(1);
                moisSpinner.setValue(1);
                anneeSpinner.setValue(1900);
                jourSpinner.setEnabled(false);
                moisSpinner.setEnabled(false);
                anneeSpinner.setEnabled(false);
            }
        }
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == rechercherBouton){
                try{
                    try{
                        Integer jour = Integer.parseInt(jourSpinner.getValue().toString());
                        Integer mois = Integer.parseInt(moisSpinner.getValue().toString())-1;
                        Integer annee = Integer.parseInt(anneeSpinner.getValue().toString());

                        if(jour == null){
                            throw new ChampsVideException("Date de consultation (Jour)");
                        }
                        if(mois == null){
                            throw new ChampsVideException("Date de consultation (Mois)");
                        }
                        if(annee == null){
                            throw new ChampsVideException("Date de consultation (Annee)");
                        }

                        Calendar dateActuelle = Calendar.getInstance();

                        if(jour < 0 || jour > 31){
                            throw new FormatDateException();
                        }
                        if(mois < 0 || mois > 31){
                            throw new FormatDateException();
                        }
                        if(annee < 1900 || annee > dateActuelle.get(Calendar.YEAR) + 100){
                            throw new FormatDateException();
                        }

                        dateConsultation = new GregorianCalendar();
                        dateConsultation.set(GregorianCalendar.DAY_OF_MONTH, jour);
                        dateConsultation.set(GregorianCalendar.MONTH, mois);
                        dateConsultation.set(GregorianCalendar.YEAR, annee);

                        if(dateConsultation.get(GregorianCalendar.MONTH) != mois){
                            throw new FormatDateException();
                        }
                    }
                    catch(NumberFormatException exception){
                        throw new FormatDateException();
                    }

                    new FenetreRechercheConsultation(MenuUtilisateur.getUtilisateurActuel().getId(), dateConsultation);
                }
                catch (FormatDateException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (ChampsVideException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
