package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AccesDBException;
import exceptionPackage.AucuneSelectionException;
import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;
import modelPackage.Mutualite;
import modelPackage.Soignant;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PanneauListeMutualite extends JPanel{

    private Container frameContainer;
    private Soignant utilisateur;
    private ApplicationController controller ;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ListSelectionModel listSelect;
    private JButton modifierButton, supprimerButton;
    private JLabel nomLabel, affiliationPolitiqueLabel, diminutifLabel;
    private JTextField nomField, affiliationPolitiqueField, diminutifField;
    private AllMutualitesModel model;
    private JTable table;
    private Mutualite mutualite;

    public PanneauListeMutualite(Container frameContainer, Soignant utilisateur){
        try{
            this.frameContainer = frameContainer;
            this.utilisateur = utilisateur;
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
            setController(new ApplicationController());
            setLayout(new GridBagLayout());

            ArrayList<Mutualite> mutualites = controller.getAllMutualites();
            model = new AllMutualitesModel(mutualites);

            table = new JTable(model);

            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setAutoCreateRowSorter(true);
            listSelect = table.getSelectionModel();

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    activerModifications();
                    int indiceLigneSelectionnee = listSelect.getMinSelectionIndex();
                    nomField.setText(model.getValueAt(indiceLigneSelectionnee, 1).toString());
                    affiliationPolitiqueField.setText(model.getValueAt(indiceLigneSelectionnee, 2).toString());
                    diminutifField.setText(model.getValueAt(indiceLigneSelectionnee, 3).toString());
                }
            });

            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

            table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
            table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
            table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

            gbc.ipadx = 600;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 8;
            this.add(new JScrollPane(table), gbc);

            gbc.insets = new Insets(20,10,20,10);
            gbc.ipadx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            nomLabel = new JLabel("Nom :");
            this.add(nomLabel, gbc);

            gbc.gridx = 1;
            gbc.ipadx = 100;
            nomField = new JTextField();
            this.add(nomField, gbc);

            gbc.gridx = 2;
            gbc.ipadx = 0;
            affiliationPolitiqueLabel = new JLabel("Affiliation politique :");
            this.add(affiliationPolitiqueLabel, gbc);

            gbc.gridx = 3;
            gbc.ipadx = 100;
            affiliationPolitiqueField = new JTextField();
            this.add(affiliationPolitiqueField, gbc);

            gbc.gridx = 4;
            gbc.ipadx = 0;
            diminutifLabel = new JLabel("Diminutif :");
            this.add(diminutifLabel, gbc);

            gbc.gridx = 5;
            gbc.ipadx = 100;
            diminutifField = new JTextField();
            this.add(diminutifField, gbc);

            gbc.gridx = 6;
            modifierButton = new JButton("Modifier");
            ButtonListener modifierListener = new ButtonListener();
            modifierButton.addActionListener(modifierListener);
            this.add(modifierButton, gbc);

            gbc.gridx = 7;
            supprimerButton = new JButton("Supprimer");
            ButtonListener supprimerListener = new ButtonListener();
            supprimerButton.addActionListener(supprimerListener);
            this.add(supprimerButton, gbc);

            desactiverModifications();

            table.getColumnModel().getColumn(0).setPreferredWidth(15);
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

    public void setController(ApplicationController applicationController){
        controller = applicationController;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == modifierButton) {
                try{
                    int indiceLigneSelectionnee = listSelect.getMinSelectionIndex();

                    if(indiceLigneSelectionnee == -1){
                        throw new AucuneSelectionException();
                    }
                    if(nomField.getText().isEmpty()){
                        throw new ChampsVideException("Nom");
                    }
                    if(nomField.getText().length() > 50){
                        throw new CaracteresLimiteException("Nom");
                    }

                    if(affiliationPolitiqueField.getText().isEmpty()){
                        throw new ChampsVideException("Affiliation politique");
                    }
                    if(affiliationPolitiqueField.getText().length() > 50){
                        throw new CaracteresLimiteException("Affiliation politique");
                    }

                    if(diminutifField.getText().isEmpty()){
                        throw new ChampsVideException("Diminutif");
                    }
                    if(diminutifField.getText().length() > 10){
                        throw new CaracteresLimiteException("Diminutif");
                    }

                    mutualite = new Mutualite(
                            Integer.parseInt(model.getValueAt(indiceLigneSelectionnee, 0).toString()),
                            nomField.getText(),
                            affiliationPolitiqueField.getText(),
                            diminutifField.getText()
                    );

                    controller.updateMutualite(mutualite);

                    model.setValueAt(mutualite.getLibelle(), indiceLigneSelectionnee, 1);
                    model.setValueAt(mutualite.getAffiliationPolitique(), indiceLigneSelectionnee, 2);
                    model.setValueAt(mutualite.getDiminutif(), indiceLigneSelectionnee, 3);

                    desactiverModifications();
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
                catch (AucuneSelectionException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (event.getSource() == supprimerButton) {
                try{
                    int indiceLigneSelectionnee = listSelect.getMinSelectionIndex();

                    if(indiceLigneSelectionnee == -1){
                        throw new AucuneSelectionException();
                    }

                    int reponse = JOptionPane.showConfirmDialog(PanneauListeMutualite.this, "Confirmer la suppression ?", "Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if(reponse == JOptionPane.YES_OPTION){
                        controller.deleteMutualite(Integer.parseInt(model.getValueAt(indiceLigneSelectionnee, 0).toString()));

                        model.removeRow(indiceLigneSelectionnee);

                        nomField.setText("");
                        affiliationPolitiqueField.setText("");
                        diminutifField.setText("");

                        desactiverModifications();
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

    public void activerModifications(){
        nomField.setEditable(true);
        affiliationPolitiqueField.setEditable(true);
        diminutifField.setEditable(true);
    }

    public void desactiverModifications(){
        nomField.setEditable(false);
        affiliationPolitiqueField.setEditable(false);
        diminutifField.setEditable(false);
    }
}
