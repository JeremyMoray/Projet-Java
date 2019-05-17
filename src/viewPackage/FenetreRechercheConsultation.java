package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FenetreRechercheConsultation extends JFrame {

    private ArrayList<Patient> listePatients;
    private ApplicationController controller;
    private AllConsultationsModel model;
    private JButton quitterBouton;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JTable table;

    public FenetreRechercheConsultation(Integer soignant_id, GregorianCalendar dateConsultation){
        super("Liste des patients consultés après la date saisie et leurs prime annuelle");
        setBounds((int)(FenetreMenu.getWindowWidth() * 0.30), (int)(FenetreMenu.getWindowHeight() * 0.20),
                (int)(FenetreMenu.getWindowWidth() * 0.40), (int)(FenetreMenu.getWindowHeight() * 0.60));
        setController(new ApplicationController());

        try{
            listePatients = controller.getAllPrimesPatient(soignant_id, dateConsultation);
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
        catch (CodeInvalideException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (FormatNombreException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        model = new AllConsultationsModel(listePatients);

        table = new JTable(model);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        gbc.ipadx = 500;
        gbc.ipady = 300;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JScrollPane(table), gbc);

        gbc.gridy = 1;
        gbc.ipadx = 50;
        gbc.ipady = 0;
        gbc.insets = new Insets(30,0,30,30);
        quitterBouton = new JButton("Quitter");
        ButtonListener rechercherListener = new ButtonListener();
        quitterBouton.addActionListener(rechercherListener);

        panel.add(quitterBouton, gbc);
        getContentPane().add(panel);

        setVisible(true);
    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {

            if(event.getSource() == quitterBouton){
                dispose();
            }
        }
    }
}
