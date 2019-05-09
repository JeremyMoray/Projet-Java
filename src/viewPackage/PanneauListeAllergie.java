package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AccesDBException;
import exceptionPackage.AucuneSelectionException;
import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;
import modelPackage.Allergie;
import modelPackage.Soignant;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PanneauListeAllergie extends JPanel{

    private Container frameContainer;
    private Soignant utilisateur;
    private ApplicationController controller ;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ListSelectionModel listSelect;
    private JButton modifierButton, supprimerButton;
    private JLabel nomLabel, symptomeLabel, conditionnementLabel;
    private JTextField nomField, symptomeField, conditionnementField;
    private AllAllergiesModel model;
    private JTable table;
    private Allergie allergie;

    public PanneauListeAllergie(Container frameContainer, Soignant utilisateur){
        try{
            this.frameContainer = frameContainer;
            this.utilisateur = utilisateur;
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
            setController(new ApplicationController());
            setLayout(new GridBagLayout());

            ArrayList<Allergie> allergies = controller.getAllAllergies();
            model = new AllAllergiesModel(allergies);

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
                    symptomeField.setText(model.getValueAt(indiceLigneSelectionnee, 2).toString());
                    conditionnementField.setText(model.getValueAt(indiceLigneSelectionnee, 3).toString());
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
            symptomeLabel = new JLabel("Symptôme :");
            this.add(symptomeLabel, gbc);

            gbc.gridx = 3;
            gbc.ipadx = 100;
            symptomeField = new JTextField();
            this.add(symptomeField, gbc);

            gbc.gridx = 4;
            gbc.ipadx = 0;
            conditionnementLabel = new JLabel("Conditionnement :");
            this.add(conditionnementLabel, gbc);

            gbc.gridx = 5;
            gbc.ipadx = 100;
            conditionnementField = new JTextField();
            this.add(conditionnementField, gbc);

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
                    if(nomField.getText() == null){
                        throw new ChampsVideException("Nom");
                    }
                    if(nomField.getText().length() > 50){
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
                            Integer.parseInt(model.getValueAt(indiceLigneSelectionnee, 0).toString()),
                            nomField.getText(),
                            symptomeField.getText(),
                            conditionnementField.getText()
                    );

                    controller.updateAllergie(allergie);

                    model.setValueAt(allergie.getLibelle(), indiceLigneSelectionnee, 1);
                    model.setValueAt(allergie.getSymptome(), indiceLigneSelectionnee, 2);
                    model.setValueAt(allergie.getConditionnement(), indiceLigneSelectionnee, 3);

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

                    controller.deleteAllergie(Integer.parseInt(model.getValueAt(indiceLigneSelectionnee, 0).toString()));

                    model.removeRow(indiceLigneSelectionnee);

                    nomField.setText("");
                    symptomeField.setText("");
                    conditionnementField.setText("");

                    desactiverModifications();
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
        symptomeField.setEditable(true);
        conditionnementField.setEditable(true);
    }

    public void desactiverModifications(){
        nomField.setEditable(false);
        symptomeField.setEditable(false);
        conditionnementField.setEditable(false);
    }
}
