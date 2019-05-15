package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanneauRechercheProches extends JPanel {

    private Container frameContainer;
    private JLabel rechercherLabel, patientLabel;
    private JComboBox patientCbx;
    private JButton rechercherBouton;
    private ApplicationController controller;
    private ArrayList<Patient> listeObjetPatients;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PanneauRechercheProches(Container frameContainer){
        this.frameContainer = frameContainer;
        setController(new ApplicationController());
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));

        rechercherLabel = new JLabel("Recherchez les proches d'un patient parmis vos consultations");
        rechercherLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 0;
        gbc.ipady = 5;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(rechercherLabel, gbc);

        patientLabel = new JLabel("Patient :");
        patientLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.insets = new Insets(30,10,30,10);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        this.add(patientLabel, gbc);

        try{
            listeObjetPatients = controller.getAllPatientsConsultes(MenuUtilisateur.getUtilisateurActuel().getId());
            String[] values = new String[listeObjetPatients.size()];
            for(int i = 0; i < values.length; i++) {
                values[i] = listeObjetPatients.get(i).getPrenom() + " " + listeObjetPatients.get(i).getNom();
            }
            patientCbx = new JComboBox(values);
            patientCbx.setMaximumRowCount(10);
            gbc.gridx = 1;
            this.add(patientCbx, gbc);
        }
        catch (AccesDBException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (CodeInvalideException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (FormatNombreException exception){
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
                    if(listeObjetPatients.size() == 0){
                        throw new AucuneSelectionException();
                    }
                    new FenetreRechercheProche(listeObjetPatients.get(patientCbx.getSelectedIndex()).getPatient_id());
                }
                catch (AucuneSelectionException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

