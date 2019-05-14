package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Patient;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PanneauRechercheMedicament extends JPanel{

    private Soignant utilisateur;
    private Container frameContainer;
    private JLabel rechercherLabel, dateMinLabel, dateMaxLabel;
    private GregorianCalendar dateMin, dateMax;
    private JSpinner jourDebutSpinner, moisDebutSpinner, anneeDebutSpinner, jourFinSpinner, moisFinSpinner, anneeFinSpinner;
    private JButton rechercherBouton;
    private ApplicationController controller;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PanneauRechercheMedicament(Container frameContainer, Soignant utilisateur){
        this.utilisateur = utilisateur;
        this.frameContainer = frameContainer;
        setController(new ApplicationController());
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));

        rechercherLabel = new JLabel("Recherchez les infos de médicaments donnés entre 2 dates aux patients consultés");
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
        dateMinLabel = new JLabel("Entre");
        dateMinLabel.setFont(new Font("Arial", Font.BOLD, 15));
        this.add(dateMinLabel, gbc);

        gbc.gridy = 2;
        dateMaxLabel = new JLabel("et");
        dateMaxLabel.setFont(new Font("Arial", Font.BOLD, 15));
        this.add(dateMaxLabel, gbc);

        int jourActuel = GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH);
        int moisActuel = GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1;
        int anneeActuelle = GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);

        SpinnerModel modelDebutJour = new SpinnerNumberModel(jourActuel, 1, 31, 1);
        SpinnerModel modelDebutMois = new SpinnerNumberModel(moisActuel, 1, 12, 1);
        SpinnerModel modelDebutAnnee = new SpinnerNumberModel(anneeActuelle, anneeActuelle - 100, anneeActuelle + 100, 1);

        SpinnerModel modelFinJour = new SpinnerNumberModel(jourActuel, 1, 31, 1);
        SpinnerModel modelFinMois = new SpinnerNumberModel(moisActuel, 1, 12, 1);
        SpinnerModel modelFinAnnee = new SpinnerNumberModel(anneeActuelle, anneeActuelle - 100, anneeActuelle + 100, 1);

        gbc.gridy = 1;
        gbc.gridx = 1;
        jourDebutSpinner = new JSpinner(modelDebutJour);
        this.add(jourDebutSpinner, gbc);

        gbc.gridx = 2;
        this.add(new JLabel("/"), gbc);

        gbc.gridx = 3;
        moisDebutSpinner = new JSpinner(modelDebutMois);
        this.add(moisDebutSpinner, gbc);

        gbc.gridx = 4;
        this.add(new JLabel("/"), gbc);

        gbc.gridx = 5;
        anneeDebutSpinner = new JSpinner(modelDebutAnnee);
        this.add(anneeDebutSpinner, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        jourFinSpinner = new JSpinner(modelFinJour);
        this.add(jourFinSpinner, gbc);

        gbc.gridx = 2;
        this.add(new JLabel("/"), gbc);

        gbc.gridx = 3;
        moisFinSpinner = new JSpinner(modelFinMois);
        this.add(moisFinSpinner, gbc);

        gbc.gridx = 4;
        this.add(new JLabel("/"), gbc);

        gbc.gridx = 5;
        anneeFinSpinner = new JSpinner(modelFinAnnee);
        this.add(anneeFinSpinner, gbc);

        rechercherBouton = new JButton("Rechercher");
        ButtonListener rechercherListener = new ButtonListener();
        rechercherBouton.addActionListener(rechercherListener);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(rechercherBouton, gbc);
    }

    public void setController(ApplicationController applicationController){
        controller = applicationController;
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {

            if(event.getSource() == rechercherBouton){
                try{
                    try{
                        Integer jourDebut = Integer.parseInt(jourDebutSpinner.getValue().toString());
                        Integer moisDebut = Integer.parseInt(moisDebutSpinner.getValue().toString())-1;
                        Integer anneeDebut = Integer.parseInt(anneeDebutSpinner.getValue().toString());

                        Integer jourFin = Integer.parseInt(jourFinSpinner.getValue().toString());
                        Integer moisFin = Integer.parseInt(moisFinSpinner.getValue().toString())-1;
                        Integer anneeFin = Integer.parseInt(anneeFinSpinner.getValue().toString());

                        if(jourDebut == null){
                            throw new ChampsVideException("Date de début (Jour)");
                        }
                        if(moisDebut == null){
                            throw new ChampsVideException("Date de début (Mois)");
                        }
                        if(anneeDebut == null){
                            throw new ChampsVideException("Date de début (Annee)");
                        }

                        if(jourFin == null){
                            throw new ChampsVideException("Date de fin (Jour)");
                        }
                        if(moisFin == null){
                            throw new ChampsVideException("Date de fin (Mois)");
                        }
                        if(anneeFin == null){
                            throw new ChampsVideException("Date de fin (Annee)");
                        }

                        Calendar dateActuelle = Calendar.getInstance();

                        if(jourDebut < 0 || jourDebut > 31){
                            throw new FormatDateException();
                        }
                        if(moisDebut < 0 || moisDebut > 31){
                            throw new FormatDateException();
                        }
                        if(anneeDebut < 1900 || anneeDebut > dateActuelle.get(Calendar.YEAR) + 100){
                            throw new FormatDateException();
                        }

                        dateMin = new GregorianCalendar();
                        dateMin.set(GregorianCalendar.DAY_OF_MONTH, jourDebut);
                        dateMin.set(GregorianCalendar.MONTH, moisDebut);
                        dateMin.set(GregorianCalendar.YEAR, anneeDebut);

                        dateMax = new GregorianCalendar();
                        dateMax.set(GregorianCalendar.DAY_OF_MONTH, jourFin);
                        dateMax.set(GregorianCalendar.MONTH, moisFin);
                        dateMax.set(GregorianCalendar.YEAR, anneeFin);

                        //Si le jour entré est supérieur au jour maximum du mois (ex: 31 novembre), GregorianCalendar
                        //va le reporter au mois suivant, (1er décembre), il suffit donc de comparer si les 2 mois correspondent

                        if(dateMin.get(GregorianCalendar.MONTH) != moisDebut){
                            throw new FormatDateException();
                        }

                        if(dateMax.get(GregorianCalendar.MONTH) != moisFin){
                            throw new FormatDateException();
                        }

                        if(dateMax.compareTo(dateMin) < 0){
                            throw new FormatDateException();
                        }
                    }
                    catch(NumberFormatException exception){
                        throw new FormatDateException();
                    }

                    new FenetreRechercheMedicament(utilisateur.getId(), dateMin, dateMax);
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
