package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AccesDBException;
import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;
import modelPackage.Allergie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauInscriptionAllergie extends JPanel{

    private JLabel allergieLabel, libelleLabel, symptomeLabel, conditionnementLabel;
    private JTextField libelleField, symptomeField, conditionnementField;
    private JButton inscriptionBouton, reinitialiserBouton, retourBouton;
    private ApplicationController controller;
    private Allergie allergie;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PanneauInscriptionAllergie(){
        setController(new ApplicationController());
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));

        allergieLabel = new JLabel("Inscrivez une allergie");
        allergieLabel.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 0;
        gbc.ipady = 10;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(allergieLabel, gbc);

        libelleLabel = new JLabel("Nom*");
        libelleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        this.add(libelleLabel, gbc);

        symptomeLabel = new JLabel("Symptôme*");
        symptomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 2;
        this.add(symptomeLabel, gbc);

        conditionnementLabel = new JLabel("Conditionnement*");
        conditionnementLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 3;
        this.add(conditionnementLabel, gbc);

        libelleField = new JTextField();
        gbc.ipadx = 200;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(libelleField, gbc);

        symptomeField = new JTextField();
        gbc.gridy = 2;
        this.add(symptomeField, gbc);

        conditionnementField = new JTextField();
        gbc.gridy = 3;
        this.add(conditionnementField, gbc);

        inscriptionBouton = new JButton("Valider");
        ButtonListener inscriptionListener = new ButtonListener();
        inscriptionBouton.addActionListener(inscriptionListener);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.ipadx = 80;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(inscriptionBouton, gbc);

        reinitialiserBouton = new JButton("Réinitialiser");
        ButtonListener reinitialiserListener = new ButtonListener();
        reinitialiserBouton.addActionListener(reinitialiserListener);
        gbc.gridx = 1;
        gbc.gridy = 8;
        this.add(reinitialiserBouton, gbc);

        retourBouton = new JButton("Retour");
        ButtonListener retourListener = new ButtonListener();
        retourBouton.addActionListener(retourListener);
        gbc.gridx = 0;
        gbc.gridy = 9;
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
                    if(libelleField.getText().isEmpty()){
                        throw new ChampsVideException("Nom");
                    }
                    if(libelleField.getText().length() > 50){
                        throw new CaracteresLimiteException("Nom");
                    }

                    if(symptomeField.getText().isEmpty()){
                        throw new ChampsVideException("Symptôme");
                    }
                    if(symptomeField.getText().length() > 250){
                        throw new CaracteresLimiteException("Symptôme");
                    }

                    if(conditionnementField.getText().isEmpty()){
                        throw new ChampsVideException("Conditionnement");
                    }
                    if(conditionnementField.getText().length() > 250){
                        throw new CaracteresLimiteException("Conditionnement");
                    }

                    allergie = new Allergie(
                            null,
                            libelleField.getText(),
                            symptomeField.getText(),
                            conditionnementField.getText()
                    );

                    controller.addAllergie(allergie);

                    MenuUtilisateur.getFrameContainerActuel().removeAll();
                    MenuUtilisateur.getFrameContainerActuel().add(new MenuUtilisateur());
                    MenuUtilisateur.getFrameContainerActuel().revalidate();
                    MenuUtilisateur.getFrameContainerActuel().repaint();
                    JOptionPane.showMessageDialog(null, "L'allergie a été ajoutée", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
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
            }


            if(event.getSource() == reinitialiserBouton){
                libelleField.setText("");
                symptomeField.setText("");
                conditionnementField.setText("");
            }

            if(event.getSource() == retourBouton){
                MenuUtilisateur.getFrameContainerActuel().removeAll();
                MenuUtilisateur.getFrameContainerActuel().add(new MenuUtilisateur());
                MenuUtilisateur.getFrameContainerActuel().revalidate();
                MenuUtilisateur.getFrameContainerActuel().repaint();
            }
        }
    }
}

