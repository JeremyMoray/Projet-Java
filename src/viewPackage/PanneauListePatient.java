package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Mutualite;
import modelPackage.Patient;
import modelPackage.Soignant;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanneauListePatient extends JPanel {

    private Container frameContainer;
    private Soignant utilisateur;
    private ApplicationController controller;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ListSelectionModel listSelect;
    private JButton modifierButton, supprimerButton;

    private JLabel numeroNationalLabel, nomLabel, prenomLabel, nbEnfantsLabel, dateNaissanceLabel, numTelFixeLabel,
            numTelMobileLabel, remarqueLabel, aSurveillerLabel, conseilsLabel, donnerEtatLabel, besoinAvalLabel,
            acharnementTherapeutiqueLabel, causeDecesPereLabel, causeDecesMereLabel, primeAnuelleLabel, mutualiteLabel;

    private JTextField numeroNationalField, nomField, prenomField, nbEnfantsField, numTelFixeField, numTelMobileField,
            remarqueField, aSurveillerField, conseilsField, causeDecesPereField, causeDecesMereField, primeAnuelleField;

    private DatePanel datePanel;
    private JCheckBox donnerEtatBox, besoinAvalBox, acharnementTherapeutiqueBox;
    private JComboBox mutualites;
    private AllPatientsModel model;
    private JTable table;
    private ArrayList<Patient> patients;
    private Patient patient;
    private ArrayList<Mutualite> listeObjetMutualites;
    private Integer mutualite_id;

    public PanneauListePatient(Container frameContainer, Soignant utilisateur) {
        this.frameContainer = frameContainer;
        this.utilisateur = utilisateur;
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        setController(new ApplicationController());
        setLayout(new GridBagLayout());
        try {
            patients = controller.getAllPatients(utilisateur.getId());
        } catch (AccesDBException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (ChampsVideException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (CaracteresLimiteException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (FormatNombreException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (CodeInvalideException exception) {
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
                acharnementTherapeutiqueBox.setEnabled(model.getValueAt(indiceLigneSelectionnee, 13).toString().equals("1"));
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

        gbc.ipadx = 900;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 8;
        this.add(new JScrollPane(table), gbc);

        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.ipadx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        numeroNationalLabel = new JLabel("Numero national :");
        this.add(numeroNationalLabel, gbc);

        gbc.gridx = 1;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        numeroNationalField = new JTextField();
        this.add(numeroNationalField, gbc);

        gbc.gridx = 2;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        nomLabel = new JLabel("Nom :");
        this.add(nomLabel, gbc);

        gbc.gridx = 3;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        nomField = new JTextField();
        this.add(nomField, gbc);

        gbc.gridx = 4;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        prenomLabel = new JLabel("Prenom :");
        this.add(prenomLabel, gbc);

        gbc.gridx = 5;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        prenomField = new JTextField();
        this.add(prenomField, gbc);

        gbc.gridx = 6;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        nbEnfantsLabel = new JLabel("Nb. d'enfants :");
        this.add(nbEnfantsLabel, gbc);

        gbc.gridx = 7;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        nbEnfantsField = new JTextField();
        this.add(nbEnfantsField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        dateNaissanceLabel = new JLabel("Date naissance :");
        this.add(dateNaissanceLabel, gbc);

        gbc.gridx = 1;
        gbc.ipadx = -10;
        gbc.anchor = GridBagConstraints.WEST;
        datePanel = new DatePanel();
        this.add(datePanel, gbc);

        gbc.gridx = 2;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        numTelFixeLabel = new JLabel("Num. tel. fixe :");
        this.add(numTelFixeLabel, gbc);

        gbc.gridx = 3;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        numTelFixeField = new JTextField();
        this.add(numTelFixeField, gbc);

        gbc.gridx = 4;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        numTelMobileLabel = new JLabel("Num. tel mobile :");
        this.add(numTelMobileLabel, gbc);

        gbc.gridx = 5;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        numTelMobileField = new JTextField();
        this.add(numTelMobileField, gbc);

        gbc.gridx = 6;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        remarqueLabel = new JLabel("Remarque :");
        this.add(remarqueLabel, gbc);

        gbc.gridx = 7;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        remarqueField = new JTextField();
        this.add(remarqueField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        aSurveillerLabel = new JLabel("A surveiller :");
        this.add(aSurveillerLabel, gbc);

        gbc.gridx = 1;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        aSurveillerField = new JTextField();
        this.add(aSurveillerField, gbc);

        gbc.gridx = 2;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        conseilsLabel = new JLabel("Conseils :");
        this.add(conseilsLabel, gbc);

        gbc.gridx = 3;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        conseilsField = new JTextField();
        this.add(conseilsField, gbc);

        gbc.gridx = 4;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        donnerEtatLabel = new JLabel("Donner état :");
        this.add(donnerEtatLabel, gbc);

        gbc.gridx = 5;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        donnerEtatBox = new JCheckBox();
        this.add(donnerEtatBox, gbc);

        gbc.gridx = 6;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        besoinAvalLabel = new JLabel("Besoin aval :");
        this.add(besoinAvalLabel, gbc);

        gbc.gridx = 7;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        besoinAvalBox = new JCheckBox();
        this.add(besoinAvalBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        acharnementTherapeutiqueLabel = new JLabel("Acharnement thérapeutique :");
        this.add(acharnementTherapeutiqueLabel, gbc);

        gbc.gridx = 1;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        acharnementTherapeutiqueBox = new JCheckBox();
        this.add(acharnementTherapeutiqueBox, gbc);

        gbc.gridx = 2;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        causeDecesPereLabel = new JLabel("Cause décès père :");
        this.add(causeDecesPereLabel, gbc);

        gbc.gridx = 3;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        causeDecesPereField = new JTextField();
        this.add(causeDecesPereField, gbc);

        gbc.gridx = 4;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        causeDecesMereLabel = new JLabel("Cause décès mère :");
        this.add(causeDecesMereLabel, gbc);

        gbc.gridx = 5;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        causeDecesMereField = new JTextField();
        this.add(causeDecesMereField, gbc);

        gbc.gridx = 6;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        primeAnuelleLabel = new JLabel("Prime anuelle :");
        this.add(primeAnuelleLabel, gbc);

        gbc.gridx = 7;
        gbc.ipadx = 100;
        gbc.anchor = GridBagConstraints.WEST;
        primeAnuelleField = new JTextField();
        this.add(primeAnuelleField, gbc);

        gbc.insets = new Insets(10, 10, 20, 10);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        mutualiteLabel = new JLabel("Mutualité :");
        this.add(mutualiteLabel, gbc);

        try {
            listeObjetMutualites = controller.getAllMutualites();
            String[] listeMutualites = new String[listeObjetMutualites.size()];
            for (int i = 0; i < listeMutualites.length; i++) {
                listeMutualites[i] = controller.getAllMutualites().get(i).getLibelle() + " (" + controller.getAllMutualites().get(i).getDiminutif() + ")";
            }
            mutualites = new JComboBox(listeMutualites);
            if (listeMutualites.length != 0) {
                mutualite_id = listeObjetMutualites.get(mutualites.getSelectedIndex()).getId();
                ComboBoxListener mutualiteListener = new ComboBoxListener();
                mutualites.addItemListener(mutualiteListener);
            }
            gbc.ipadx = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.ipady = 5;
            gbc.gridx = 1;
            this.add(mutualites, gbc);
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

        //Boutons

        gbc.ipadx = 50;
        gbc.gridx = 2;
        modifierButton = new JButton("Modifier");
        ButtonListener modifierListener = new ButtonListener();
        modifierButton.addActionListener(modifierListener);
        this.add(modifierButton, gbc);

        gbc.gridx = 3;
        supprimerButton = new JButton("Supprimer");
        ButtonListener supprimerListener = new ButtonListener();
        supprimerButton.addActionListener(supprimerListener);
        this.add(supprimerButton, gbc);

        desactiverModifications();
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

        }
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void activerModifications() {
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
        acharnementTherapeutiqueBox.setEnabled(true);
        causeDecesPereField.setEditable(true);
        causeDecesMereField.setEditable(true);
        primeAnuelleField.setEditable(true);
        mutualites.setEnabled(true);
    }

    public void desactiverModifications() {
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
        acharnementTherapeutiqueBox.setEnabled(false);
        causeDecesPereField.setEditable(false);
        causeDecesMereField.setEditable(false);
        primeAnuelleField.setEditable(false);
        mutualites.setEnabled(false);
    }

    private class ComboBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent event) {
            mutualite_id = listeObjetMutualites.get(mutualites.getSelectedIndex()).getId();
        }
    }
}