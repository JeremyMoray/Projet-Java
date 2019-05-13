package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Proche;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class FenetreRechercheProche extends JFrame{

    private ArrayList<Proche> listeProches;
    private AllProchesAAppellerModel model;
    private JTable table;
    private ApplicationController controller;
    private GridBagConstraints gbc = new GridBagConstraints();

    public FenetreRechercheProche(Integer patient_id){
        super("Liste des proches à appeller en cas d'urgence pour ce patient");
        setBounds((int)(FenetreMenu.getWindowWidth() * 0.30), (int)(FenetreMenu.getWindowHeight() * 0.20),
                (int)(FenetreMenu.getWindowWidth() * 0.40), (int)(FenetreMenu.getWindowHeight() * 0.60));
        setController(new ApplicationController());

        try{
            listeProches = controller.getAllProchesUrgence(patient_id);
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

        model = new AllProchesAAppellerModel(listeProches);

        table = new JTable(model);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

        gbc.ipadx = 600;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(table), gbc);
        this.add(panel);

        setVisible(true);
    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }
}
