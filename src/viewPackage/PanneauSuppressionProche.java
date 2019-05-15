package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanneauSuppressionProche extends JPanel {

    private Container frameContainer;
    private ApplicationController controller ;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ListSelectionModel listSelect;
    private JButton supprimerButton;
    private AllProchesModel model;
    private JTable table;
    private ArrayList<Proche> proches;

    public PanneauSuppressionProche(Container frameContainer){
        this.frameContainer = frameContainer;
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        setController(new ApplicationController());
        setLayout(new GridBagLayout());

        try {
            proches = controller.getAllProches();
        }
        catch (AccesDBException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (ChampsVideException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (CaracteresLimiteException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        model = new AllProchesModel(proches);

        table = new JTable(model);

        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setAutoCreateRowSorter(true);
        listSelect = table.getSelectionModel();

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        gbc.ipadx = 500;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        this.add(new JScrollPane(table), gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.ipadx = 100;
        gbc.insets = new Insets(10,0,10,10);
        supprimerButton = new JButton("Supprimer");
        ButtonListener supprimerListener = new ButtonListener();
        supprimerButton.addActionListener(supprimerListener);
        this.add(supprimerButton, gbc);
    }

    public void setController(ApplicationController applicationController){
        controller = applicationController;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try{
                int row = table.getSelectedRow();
                System.out.println(table.getSelectedRow());
                if(row == -1){
                    throw new AucuneSelectionException();
                }

                int reponse = JOptionPane.showConfirmDialog(PanneauSuppressionProche.this, "Confirmer la suppression ?", "Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if(reponse == JOptionPane.YES_OPTION){
                    while (row != -1)
                    {
                        System.out.println(table.getSelectedRow());
                        controller.deleteProche(Integer.parseInt(model.getValueAt(row, 0).toString()));
                        int modelRow = table.convertRowIndexToModel( row );
                        model.removeRow( modelRow );
                        row = table.getSelectedRow();
                    }
                }
            }
            catch (AccesDBException exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            catch (AucuneSelectionException exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
