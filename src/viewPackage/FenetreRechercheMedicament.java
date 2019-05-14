package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Medicament;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FenetreRechercheMedicament extends JFrame{

    private ArrayList<Medicament> listeMedicaments;
    private AllMedicamentsDatesModel model;
    private JTable table;
    private ApplicationController controller;
    private JButton quitterBouton;
    private GridBagConstraints gbc = new GridBagConstraints();

    public FenetreRechercheMedicament(Integer soignant_id, GregorianCalendar dateMin, GregorianCalendar dateMax){
        super("Liste des médicaments attribué aux patients consultés entre 2 dates");
        setBounds((int)(FenetreMenu.getWindowWidth() * 0.20), (int)(FenetreMenu.getWindowHeight() * 0.20),
                (int)(FenetreMenu.getWindowWidth() * 0.40), (int)(FenetreMenu.getWindowHeight() * 0.60));
        setController(new ApplicationController());

        try{
            listeMedicaments = controller.getAllMedicamentsDates(soignant_id, dateMin, dateMax);
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

        model = new AllMedicamentsDatesModel(listeMedicaments);

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
        table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        gbc.ipadx = 600;
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
