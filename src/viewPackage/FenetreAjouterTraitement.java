package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Medicament;
import modelPackage.Soignant;
import modelPackage.Traitement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FenetreAjouterTraitement extends JFrame {

    private Soignant utilisateur;
    private Integer patient_id;
    private JComboBox medicamentCbx;
    private ArrayList<Medicament> listeObjetMedicaments;
    private GregorianCalendar dateDeDebut, dateDeFin;
    private Traitement traitement;
    private JSpinner jourDebutSpinner, moisDebutSpinner, anneeDebutSpinner, jourFinSpinner, moisFinSpinner, anneeFinSpinner;
    private JTextField frequenceField;
    private ApplicationController controller;
    private JButton enregistrerBouton, annulerBouton;
    private GridBagConstraints gbc = new GridBagConstraints();

    public FenetreAjouterTraitement(Soignant utilisateur, Integer patient_id){
        super("Ajouter un traitement");
        setBounds((int)(FenetreMenu.getWindowWidth() * 0.30), (int)(FenetreMenu.getWindowHeight() * 0.30),
                (int)(FenetreMenu.getWindowWidth() * 0.40), (int)(FenetreMenu.getWindowHeight() * 0.40));
        setController(new ApplicationController());

        this.utilisateur = utilisateur;
        this.patient_id = patient_id;
        this.setLayout(new GridBagLayout());
        dateDeDebut = new GregorianCalendar();
        dateDeFin = new GregorianCalendar();

        int jourActuel = GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH);
        int moisActuel = GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1;
        int anneeActuelle = GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);

        SpinnerModel modelDebutJour = new SpinnerNumberModel(jourActuel, 1, 31, 1);
        SpinnerModel modelDebutMois = new SpinnerNumberModel(moisActuel, 1, 12, 1);
        SpinnerModel modelDebutAnnee = new SpinnerNumberModel(anneeActuelle, anneeActuelle, anneeActuelle + 100, 1);

        SpinnerModel modelFinJour = new SpinnerNumberModel(jourActuel, 1, 31, 1);
        SpinnerModel modelFinMois = new SpinnerNumberModel(moisActuel, 1, 12, 1);
        SpinnerModel modelFinAnnee = new SpinnerNumberModel(anneeActuelle, anneeActuelle, anneeActuelle + 100, 1);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 15;
        gbc.ipady = 10;
        gbc.insets = new Insets(10,5,10,5);
        this.add(new JLabel("Date de début : "), gbc);

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

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(new JLabel("Date de fin : "), gbc);

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

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 0;
        this.add(new JLabel("Fréquence :"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 300;
        gbc.gridwidth = 5;
        frequenceField = new JTextField();
        this.add(frequenceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipadx = 0;
        gbc.gridwidth = 1;
        this.add(new JLabel("Médicament :"), gbc);

        try{
            listeObjetMedicaments = controller.getAllMedicaments();
            String[] values = new String[listeObjetMedicaments.size()];
            for(int i = 0; i < values.length; i++) {
                values[i] = listeObjetMedicaments.get(i).getNom();
            }
            medicamentCbx = new JComboBox(values);
            gbc.gridx = 1;
            this.add(medicamentCbx, gbc);
        }
        catch (AccesDBException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (ChampsVideException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (CodeInvalideException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (CaracteresLimiteException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (FormatNombreException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        enregistrerBouton = new JButton("Enregistrer");
        ButtonListener enregistrerButtonListener = new ButtonListener();
        enregistrerBouton.addActionListener(enregistrerButtonListener);
        gbc.gridy = 4;
        gbc.gridx = 0;
        this.add(enregistrerBouton, gbc);

        annulerBouton = new JButton("Annuler");
        ButtonListener annulerButtonListener = new ButtonListener();
        annulerBouton.addActionListener(annulerButtonListener);
        gbc.gridx = 1;
        this.add(annulerBouton, gbc);

        setVisible(true);
    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if(event.getSource() == enregistrerBouton){
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

                        if(jourDebut < 0 || jourDebut > 31 || jourFin < 0 || jourFin > 31){
                            throw new FormatDateException();
                        }
                        if(moisDebut < 0 || moisDebut > 12 || moisFin < 0 || moisFin > 12){
                            throw new FormatDateException();
                        }
                        if(anneeDebut < dateActuelle.get(Calendar.YEAR) || anneeDebut > dateActuelle.get(Calendar.YEAR) + 100){
                            throw new FormatDateException();
                        }
                        if(anneeFin < dateActuelle.get(Calendar.YEAR) || anneeFin > dateActuelle.get(Calendar.YEAR) + 100){
                            throw new FormatDateException();
                        }
                        dateDeDebut.set(GregorianCalendar.DAY_OF_MONTH, jourDebut-1);
                        dateDeDebut.set(GregorianCalendar.MONTH, moisDebut);
                        dateDeDebut.set(GregorianCalendar.YEAR, anneeDebut);

                        dateDeFin.set(GregorianCalendar.DAY_OF_MONTH, jourFin-1);
                        dateDeFin.set(GregorianCalendar.MONTH, moisFin);
                        dateDeFin.set(GregorianCalendar.YEAR, anneeFin);

                        if(dateDeDebut.get(GregorianCalendar.MONTH) != moisDebut){
                            throw new FormatDateException();
                        }
                        if(dateDeFin.get(GregorianCalendar.MONTH) != moisFin){
                            throw new FormatDateException();
                        }

                        if(dateDeDebut.compareTo(dateActuelle) < 0){
                            throw new FormatDateException();
                        }
                        if(dateDeFin.compareTo(dateActuelle) < 0){
                            throw new FormatDateException();
                        }
                        if(dateDeDebut.compareTo(dateDeFin) > 0){
                            throw new FormatDateException();
                        }
                    }
                    catch(NumberFormatException exception){
                        throw new FormatDateException();
                    }

                    if(frequenceField.getText().length() > 100){
                        throw new CaracteresLimiteException("Frequence");
                    }

                    if(listeObjetMedicaments.size() != 0){
                        traitement = new Traitement(dateDeDebut, dateDeFin, (frequenceField.getText().isEmpty())?null:frequenceField.getText(), patient_id, utilisateur.getId(), listeObjetMedicaments.get(medicamentCbx.getSelectedIndex()).getId());
                    }
                    else{
                        throw new ChampsVideException("Médicament");
                    }
                    controller.addTraitement(traitement);
                    JOptionPane.showMessageDialog(null, "Le traitement a été ajouté", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
                catch (AccesDBException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (ChampsVideException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (AllergieAMedicamentException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (ObjetExistantException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (FormatDateException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (CaracteresLimiteException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (CodeInvalideException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (FormatNombreException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(event.getSource() == annulerBouton){
                dispose();
            }
        }
    }
}
