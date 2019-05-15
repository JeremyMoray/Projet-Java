package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AccesDBException;
import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;
import exceptionPackage.ObjetExistantException;
import modelPackage.Proche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreAjouterProche extends JFrame{

    private Integer patient_id;
    private ApplicationController controller;
    private JTextField nomField, prenomField, numTelField, remarquesField;
    private JCheckBox aAccesInfosMedicalesBox, aAppellerSiUrgenceBox;
    private JButton enregistrerBouton, annulerBouton;
    private GridBagConstraints gbc = new GridBagConstraints();

    public FenetreAjouterProche(Integer patient_id){

        super("Ajouter un proche");
        setBounds((int)(FenetreMenu.getWindowWidth() * 0.30), (int)(FenetreMenu.getWindowHeight() * 0.30),
                (int)(FenetreMenu.getWindowWidth() * 0.40), (int)(FenetreMenu.getWindowHeight() * 0.40));
        setController(new ApplicationController());
        this.setLayout(new GridBagLayout());

        this.patient_id = patient_id;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,5,10,5);
        this.add(new JLabel("Nom : "), gbc);

        gbc.gridy = 1;
        this.add(new JLabel("Prénom : "), gbc);

        gbc.gridy = 2;
        this.add(new JLabel("Num. téléphone : "), gbc);

        gbc.gridy = 3;
        this.add(new JLabel("Remarques : "), gbc);

        gbc.gridy = 4;
        this.add(new JLabel("A accès aux infos médicales du patient : "), gbc);

        gbc.gridy = 5;
        this.add(new JLabel("A appeller en cas d'urgence : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 200;
        nomField = new JTextField();
        this.add(nomField, gbc);

        gbc.gridy = 1;
        prenomField = new JTextField();
        this.add(prenomField, gbc);

        gbc.gridy = 2;
        numTelField = new JTextField();
        this.add(numTelField, gbc);

        gbc.gridy = 3;
        remarquesField = new JTextField();
        this.add(remarquesField, gbc);

        gbc.gridy = 4;
        gbc.ipadx = 0;
        aAccesInfosMedicalesBox = new JCheckBox();
        this.add(aAccesInfosMedicalesBox, gbc);

        gbc.gridy = 5;
        aAppellerSiUrgenceBox = new JCheckBox();
        this.add(aAppellerSiUrgenceBox, gbc);

        enregistrerBouton = new JButton("Enregistrer");
        ButtonListener enregistrerButtonListener = new ButtonListener();
        enregistrerBouton.addActionListener(enregistrerButtonListener);
        gbc.gridx = 0;
        gbc.gridy = 6;
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
                    if(nomField.getText().isEmpty()){
                        throw new ChampsVideException("Nom");
                    }
                    if(nomField.getText().length() > 50){
                        throw new CaracteresLimiteException("Nom");
                    }

                    if(prenomField.getText().isEmpty()){
                        throw new ChampsVideException("Prénom");
                    }
                    if(prenomField.getText().length() > 50){
                        throw new CaracteresLimiteException("Prénom");
                    }

                    if(numTelField.getText().isEmpty()){
                        throw new ChampsVideException("Num. téléphone");
                    }
                    if(numTelField.getText().length() > 50){
                        throw new CaracteresLimiteException("Num. téléphone");
                    }

                    if(remarquesField.getText().length() > 250){
                        throw new CaracteresLimiteException("Remarques");
                    }

                    Proche proche = new Proche(
                            null,
                            nomField.getText(),
                            prenomField.getText(),
                            numTelField.getText(),
                            (remarquesField.getText().isEmpty()?null:remarquesField.getText()),
                            aAccesInfosMedicalesBox.isSelected(),
                            aAppellerSiUrgenceBox.isSelected(),
                            patient_id
                    );

                    controller.addProche(proche);

                    JOptionPane.showMessageDialog(null, "Le proche a été ajouté", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
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
            }

            if(event.getSource() == annulerBouton){
                dispose();
            }
        }
    }
}
