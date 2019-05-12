package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Allergie;
import modelPackage.Souffrance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FenetreAjouterAllergie extends JFrame {

    private JComboBox allergieCbx;
    private ArrayList<Allergie>listeObjetAllergies;
    private Integer patient_id;
    private JButton enregistrerBouton, annulerBouton;
    private Souffrance souffrance;

    private ApplicationController controller;
    private GridBagConstraints gbc = new GridBagConstraints();

    public FenetreAjouterAllergie(Integer patient_id){
        super("Ajouter une allergie");
        setBounds((int)(FenetreMenu.getWindowWidth() * 0.30), (int)(FenetreMenu.getWindowHeight() * 0.30),
                (int)(FenetreMenu.getWindowWidth() * 0.40), (int)(FenetreMenu.getWindowHeight() * 0.40));
        setController(new ApplicationController());

        this.patient_id = patient_id;
        this.setLayout(new GridBagLayout());

        try{
            listeObjetAllergies = controller.getAllAllergies();
            String[ ] values = new String[listeObjetAllergies.size()];
            for(int i = 0; i < values.length; i++) {
                values[i] = listeObjetAllergies.get(i).getLibelle();
            }
            allergieCbx = new JComboBox(values);
            gbc.ipadx = 100;
            gbc.ipady = 5;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10,10,10,10);
            this.add(allergieCbx, gbc);

            enregistrerBouton = new JButton("Enregistrer");
            ButtonListener enregistrerButtonListener = new ButtonListener();
            enregistrerBouton.addActionListener(enregistrerButtonListener);
            gbc.gridx = 1;
            this.add(enregistrerBouton, gbc);

            annulerBouton = new JButton("Annuler");
            ButtonListener annulerButtonListener = new ButtonListener();
            annulerBouton.addActionListener(annulerButtonListener);
            gbc.gridx = 2;
            this.add(annulerBouton, gbc);
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

        setVisible(true);
    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if(event.getSource() == enregistrerBouton){
                try{
                    if(listeObjetAllergies.size() != 0){
                        souffrance = new Souffrance(patient_id, listeObjetAllergies.get(allergieCbx.getSelectedIndex()).getId());
                    }
                    else{
                        throw new ChampsVideException("Allergie");
                    }
                    controller.addSouffrance(souffrance);
                    JOptionPane.showMessageDialog(null, "L'allergie a été ajoutée", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
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
            }

            dispose();
        }
    }
}
