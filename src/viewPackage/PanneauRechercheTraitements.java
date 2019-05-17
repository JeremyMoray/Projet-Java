package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Mutualite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanneauRechercheTraitements extends JPanel {

    private JLabel rechercherLabel, mutualitesLabel;
    private JComboBox mutualiteCbx;
    private JButton rechercherBouton;
    private ApplicationController controller;
    private ArrayList<Mutualite> listeObjetMutualites;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PanneauRechercheTraitements(){
        setController(new ApplicationController());
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));

        rechercherLabel = new JLabel("Recherchez tous les traitements prescrit sous une mutualité");
        rechercherLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 0;
        gbc.ipady = 5;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(rechercherLabel, gbc);

        mutualitesLabel = new JLabel("Mutualité :");
        mutualitesLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.insets = new Insets(30,10,30,10);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        this.add(mutualitesLabel, gbc);

        try{
            listeObjetMutualites = controller.getAllMutualites();
            String[] values = new String[listeObjetMutualites.size()];
            for(int i = 0; i < values.length; i++) {
                values[i] = listeObjetMutualites.get(i).getLibelle() + " (" + listeObjetMutualites.get(i).getId() + ")";
            }
            mutualiteCbx = new JComboBox(values);
            mutualiteCbx.setMaximumRowCount(10);
            gbc.gridx = 1;
            this.add(mutualiteCbx, gbc);
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

        rechercherBouton = new JButton("Rechercher");
        ButtonListener rechercherListener = new ButtonListener();
        rechercherBouton.addActionListener(rechercherListener);
        gbc.gridx = 2;
        gbc.ipadx = 80;
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
                    if(listeObjetMutualites.size() == 0){
                        throw new AucuneSelectionException();
                    }
                    new FenetreRechercheTraitement(listeObjetMutualites.get(mutualiteCbx.getSelectedIndex()).getId());
                }
                catch (AucuneSelectionException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

