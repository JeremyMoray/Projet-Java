package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanneauRechercheConsultations extends JPanel {

    private JLabel rechercherLabel, soignantsLabel;
    private JComboBox soignantCbx;
    private JButton rechercherBouton;
    private ApplicationController controller;
    private ArrayList<Soignant> listeObjetSoignants;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PanneauRechercheConsultations(){
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

        soignantsLabel = new JLabel("Mutualité :");
        soignantsLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.insets = new Insets(30,10,30,10);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        this.add(soignantsLabel, gbc);

        try{
            listeObjetSoignants = controller.getAllSoignants();
            String[] values = new String[listeObjetSoignants.size()];
            for(int i = 0; i < values.length; i++) {
                values[i] = listeObjetSoignants.get(i).getPrenom() + " " + listeObjetSoignants.get(i).getNom() + " (" + listeObjetSoignants.get(i).getId() + ")";
            }
            soignantCbx = new JComboBox(values);
            soignantCbx.setMaximumRowCount(10);
            gbc.gridx = 1;
            this.add(soignantCbx, gbc);
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
                    if(listeObjetSoignants.size() == 0){
                        throw new AucuneSelectionException();
                    }
                    new FenetreRechercheConsultation(listeObjetSoignants.get(soignantCbx.getSelectedIndex()).getId());
                }
                catch (AucuneSelectionException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

