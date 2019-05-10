package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Patient;
import modelPackage.Soignant;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PanneauListePatient extends JPanel{

    private Container frameContainer;
    private Soignant utilisateur;
    private ApplicationController controller ;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ListSelectionModel listSelect;
    private JButton modifierButton, supprimerButton;

    private JLabel numeroNationalLabel, nomLabel, prenomLabel, nbEnfantsLabel, dateNaissanceLabel, numTelFixeLabel,
            numTelMobileLabel, remarqueLabel, aSurveillerLabel, conseilsLabel, donnerEtatLabel, besoinAvalLabel,
            acharnementTherapeuthiqueLabel, causeDecesPereLabel, causeDecesMereLabel, primeAnuelleLabel, mutualiteLabel;

    private JTextField numeroNationalField, nomField, prenomField, nbEnfantsField, numTelFixeField, numTelMobileField,
            remarqueField, aSurveillerField, conseilsField, causeDecesPereField, causeDecesMereField, primeAnuelleField;

    private DatePanel datePanel;
    private JCheckBox donnerEtatBox, besoinAvalBox, acharnementTherapeuthiqueBox;
    private JComboBox mutualites;
    private AllPatientsModel model;
    private JTable table;
    private ArrayList<Patient> patients;
    private Patient patient;

    public PanneauListePatient(Container frameContainer, Soignant utilisateur){
        this.frameContainer = frameContainer;
        this.utilisateur = utilisateur;
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        setController(new ApplicationController());
        setLayout(new GridBagLayout());
        try{
            patients = controller.getAllPatients(utilisateur.getId());
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

        model = new AllPatientsModel(patients);

        table = new JTable(model);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        listSelect = table.getSelectionModel();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                activerModifications();

                int indiceLigneSelectionnee = listSelect.getMinSelectionIndex();

                numeroNationalField.setText(model.getValueAt(indiceLigneSelectionnee, 1).toString());
                nomField.setText(model.getValueAt(indiceLigneSelectionnee, 2).toString());
                prenomField.setText(model.getValueAt(indiceLigneSelectionnee, 3).toString());
                nbEnfantsField.setText(model.getValueAt(indiceLigneSelectionnee, 4).toString());
                datePanel.setJourField(model.getValueAt(indiceLigneSelectionnee, 5).toString());
                datePanel.setMoisField(model.getValueAt(indiceLigneSelectionnee, 5).toString());
                datePanel.setAnneeField(model.getValueAt(indiceLigneSelectionnee, 5).toString());
                numTelFixeField.setText(model.getValueAt(indiceLigneSelectionnee, 6).toString());
                numTelMobileField.setText(model.getValueAt(indiceLigneSelectionnee, 7).toString());
                remarqueField.setText(model.getValueAt(indiceLigneSelectionnee, 8).toString());
                aSurveillerField.setText(model.getValueAt(indiceLigneSelectionnee, 9).toString());
                conseilsField.setText(model.getValueAt(indiceLigneSelectionnee, 10).toString());
                donnerEtatBox.setEnabled(model.getValueAt(indiceLigneSelectionnee, 11).equals("1"));
                besoinAvalBox.setEnabled(model.getValueAt(indiceLigneSelectionnee, 12).toString().equals("1"));
                acharnementTherapeuthiqueBox.setEnabled(model.getValueAt(indiceLigneSelectionnee, 13).toString().equals("1"));
                causeDecesPereField.setText(model.getValueAt(indiceLigneSelectionnee, 14).toString());
                causeDecesMereField.setText(model.getValueAt(indiceLigneSelectionnee, 15).toString());
                primeAnuelleField.setText(model.getValueAt(indiceLigneSelectionnee, 16).toString());
                mutualites.setSelectedItem(model.getValueAt(indiceLigneSelectionnee, 17).toString());
            }
        });

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(10).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(11).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(12).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(13).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(14).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(15).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(16).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(17).setCellRenderer(rightRenderer);

        gbc.ipadx = 650;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 18;
        this.add(new JScrollPane(table), gbc);
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void activerModifications(){
        numeroNationalField.setEditable(true);
        nomField.setEditable(true);
        prenomField.setEditable(true);
        nbEnfantsField.setEditable(true);
        datePanel.setJourEditable(true);
        datePanel.setMoisEditable(true);
        datePanel.setAnneeEditable(true);
        numTelFixeField.setEditable(true);
        numTelMobileField.setEditable(true);
        remarqueField.setEditable(true);
        aSurveillerField.setEditable(true);
        conseilsField.setEditable(true);
        donnerEtatBox.setEnabled(true);
        besoinAvalBox.setEnabled(true);
        acharnementTherapeuthiqueBox.setEnabled(true);
        causeDecesPereField.setEditable(true);
        causeDecesMereField.setEditable(true);
        primeAnuelleField.setEditable(true);
        mutualites.setEditable(true);
    }

    public void desactiverModifications(){
        numeroNationalField.setEditable(false);
        nomField.setEditable(false);
        prenomField.setEditable(false);
        nbEnfantsField.setEditable(false);
        datePanel.setJourEditable(false);
        datePanel.setMoisEditable(false);
        datePanel.setAnneeEditable(false);
        numTelFixeField.setEditable(false);
        numTelMobileField.setEditable(false);
        remarqueField.setEditable(false);
        aSurveillerField.setEditable(false);
        conseilsField.setEditable(false);
        donnerEtatBox.setEnabled(false);
        besoinAvalBox.setEnabled(false);
        acharnementTherapeuthiqueBox.setEnabled(false);
        causeDecesPereField.setEditable(false);
        causeDecesMereField.setEditable(false);
        primeAnuelleField.setEditable(false);
        mutualites.setEditable(false);
    }
}