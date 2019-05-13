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
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PanneauListePatient extends JPanel {

    private Container frameContainer;
    private Soignant utilisateur;
    private ApplicationController controller;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ListSelectionModel listSelect;
    private JButton modifierButton, supprimerButton, ajouterConsultationButton, ajouterAllergieButton, ajouterTraitementButton, ajouterProcheButton;

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
            patients = controller.getAllPatients();
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
        catch (FormatNombreException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (CodeInvalideException exception) {
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
                datePanel.setJourField(model.getValueAt(indiceLigneSelectionnee, 5).toString().substring(0,2));
                datePanel.setMoisField(model.getValueAt(indiceLigneSelectionnee, 5).toString().substring(3,5));
                datePanel.setAnneeField(model.getValueAt(indiceLigneSelectionnee, 5).toString().substring(6,10));
                numTelFixeField.setText(model.getValueAt(indiceLigneSelectionnee, 6).toString());
                numTelMobileField.setText(model.getValueAt(indiceLigneSelectionnee, 7).toString());
                remarqueField.setText(model.getValueAt(indiceLigneSelectionnee, 8).toString());
                aSurveillerField.setText(model.getValueAt(indiceLigneSelectionnee, 9).toString());
                conseilsField.setText(model.getValueAt(indiceLigneSelectionnee, 10).toString());
                donnerEtatBox.setSelected(model.getValueAt(indiceLigneSelectionnee, 11).toString().equals("Oui"));
                besoinAvalBox.setSelected(model.getValueAt(indiceLigneSelectionnee, 12).toString().equals("Oui"));
                acharnementTherapeutiqueBox.setSelected(model.getValueAt(indiceLigneSelectionnee, 13).toString().equals("Oui"));
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

        gbc.ipadx = 1000;
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
            listeObjetMutualites = new ArrayList<Mutualite>();
            listeObjetMutualites.add(null);
            listeObjetMutualites.addAll(controller.getAllMutualites());
            String[] listeMutualites = new String[listeObjetMutualites.size()];
            listeMutualites[0] = "Aucune";
            for (int i = 1; i < listeMutualites.length; i++) {
                listeMutualites[i] = listeObjetMutualites.get(i).getLibelle() + " (" + listeObjetMutualites.get(i).getDiminutif() + ")";
            }
            mutualites = new JComboBox(listeMutualites);

            mutualite_id = null;
            ComboBoxListener mutualiteListener = new ComboBoxListener();
            mutualites.addItemListener(mutualiteListener);

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

        gbc.ipadx = 10;
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

        gbc.gridx = 4;
        ajouterConsultationButton = new JButton("Ajouter une consultation");
        ButtonListener ajouterConsultationListener = new ButtonListener();
        ajouterConsultationButton.addActionListener(ajouterConsultationListener);
        this.add(ajouterConsultationButton, gbc);

        gbc.gridx = 5;
        ajouterAllergieButton = new JButton("Ajouter une allergie");
        ButtonListener ajouterAllergieListener = new ButtonListener();
        ajouterAllergieButton.addActionListener(ajouterAllergieListener);
        this.add(ajouterAllergieButton, gbc);

        gbc.gridx = 6;
        ajouterTraitementButton = new JButton("Ajouter un traitement");
        ButtonListener ajouterTraitementListener = new ButtonListener();
        ajouterTraitementButton.addActionListener(ajouterTraitementListener);
        this.add(ajouterTraitementButton, gbc);

        gbc.gridx = 7;
        ajouterProcheButton = new JButton("Ajouter un proche");
        ButtonListener ajouterProcheListener = new ButtonListener();
        ajouterProcheButton.addActionListener(ajouterProcheListener);
        this.add(ajouterProcheButton, gbc);

        desactiverModifications();
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
            if(listeObjetMutualites.get(mutualites.getSelectedIndex()) == null){
                mutualite_id = null;
            }
            else{
                mutualite_id = listeObjetMutualites.get(mutualites.getSelectedIndex()).getId();
            }
        }
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == modifierButton){
                try{
                    int indiceLigneSelectionnee = listSelect.getMinSelectionIndex();

                    if(indiceLigneSelectionnee == -1){
                        throw new AucuneSelectionException();
                    }
                    if(numeroNationalField.getText().isEmpty()){
                        throw new ChampsVideException("Numéro national");
                    }
                    if(!numeroNationalField.getText().matches("[0-9]*")){
                        throw new FormatNombreException("Numéro national");
                    }
                    if(numeroNationalField.getText().length() != 11){
                        throw new CodeInvalideException("Numéro national", "Veuillez entrer un numéro national composé de 11 chiffres uniquement");
                    }

                    if(nomField.getText().isEmpty()){
                        throw new ChampsVideException("Nom");
                    }
                    if(nomField.getText().length() > 30){
                        throw new CaracteresLimiteException("Nom");
                    }

                    if(prenomField.getText().isEmpty()){
                        throw new ChampsVideException("Prénom");
                    }
                    if(prenomField.getText().length() > 30){
                        throw new CaracteresLimiteException("Prénom");
                    }

                    if(nbEnfantsField.getText().isEmpty()){
                        throw new ChampsVideException("Nb. d'enfants");
                    }
                    try{
                        if(Integer.parseInt(nbEnfantsField.getText()) < 0){
                            throw new FormatNombreException("Nb. d'enfants");
                        }
                    }
                    catch(NumberFormatException exception){
                        throw new FormatNombreException("Nb. d'enfants");
                    }

                    try{
                        Integer jour = Integer.parseInt(datePanel.getJourField());
                        Integer mois = Integer.parseInt(datePanel.getMoisField())-1;
                        Integer annee = Integer.parseInt(datePanel.getAnneeField());

                        if(jour == null){
                            throw new ChampsVideException("Date de naissance (Jour)");
                        }
                        if(mois == null){
                            throw new ChampsVideException("Date de naissance (Mois)");
                        }
                        if(annee == null){
                            throw new ChampsVideException("Date de naissance (Annee)");
                        }

                        Calendar dateActuelle = Calendar.getInstance();

                        if(jour < 0 || jour > 31){
                            throw new FormatDateException();
                        }
                        if(mois < 0 || mois > 12){
                            throw new FormatDateException();
                        }
                        if(annee < 1900 || annee > dateActuelle.get(Calendar.YEAR)){
                            throw new FormatDateException();
                        }

                        GregorianCalendar dateNaissance = new GregorianCalendar();
                        dateNaissance.set(GregorianCalendar.DAY_OF_MONTH, jour);
                        dateNaissance.set(GregorianCalendar.MONTH, mois);
                        dateNaissance.set(GregorianCalendar.YEAR, annee);

                        //Si le jour entré est supérieur au jour maximum du mois (ex: 31 novembre), GregorianCalendar
                        //va le reporter au mois suivant, (1er décembre), il suffit donc de comparer si les 2 mois correspondent

                        if(dateNaissance.get(GregorianCalendar.MONTH) != mois){
                            throw new FormatDateException();
                        }

                        if(dateNaissance.compareTo(dateActuelle) > 0){
                            throw new FormatDateException();
                        }
                    }
                    catch(NumberFormatException exception){
                        throw new FormatDateException();
                    }

                    if(numTelFixeField.getText().length() > 20){
                        throw new CaracteresLimiteException("Numéro tel. fixe");
                    }

                    if(numTelMobileField.getText().length() > 20){
                        throw new CaracteresLimiteException("Numéro tel. mobile");
                    }

                    if(remarqueField.getText().length() > 250){
                        throw new CaracteresLimiteException("Remarque");
                    }

                    if(aSurveillerField.getText().length() > 250){
                        throw new CaracteresLimiteException("A surveiller");
                    }

                    if(conseilsField.getText().length() > 250){
                        throw new CaracteresLimiteException("Conseils");
                    }

                    if(causeDecesPereField.getText().length() > 250){
                        throw new CaracteresLimiteException("Cause décès père");
                    }

                    if(causeDecesMereField.getText().length() > 250){
                        throw new CaracteresLimiteException("Cause décès mère");
                    }

                    try{
                        if(!primeAnuelleField.getText().isEmpty()){
                            if(Double.parseDouble(primeAnuelleField.getText()) < 0){
                                throw new FormatNombreException("Prime anuelle");
                            }
                        }
                    }
                    catch(NumberFormatException exception){
                        throw new FormatNombreException("Prime anuelle");
                    }

                    GregorianCalendar dateNaissance = new GregorianCalendar();
                    dateNaissance.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(datePanel.getJourField()));
                    dateNaissance.set(GregorianCalendar.MONTH, Integer.parseInt(datePanel.getMoisField())-1);
                    dateNaissance.set(GregorianCalendar.YEAR, Integer.parseInt(datePanel.getAnneeField()));

                    patient = new Patient(
                            Integer.parseInt(model.getValueAt(indiceLigneSelectionnee, 0).toString()),
                            numeroNationalField.getText(),
                            nomField.getText(),
                            prenomField.getText(),
                            Integer.parseInt(nbEnfantsField.getText()),
                            dateNaissance,
                            (numTelFixeField.getText().isEmpty()?null:numTelFixeField.getText()),
                            (numTelMobileField.getText().isEmpty()?null:numTelMobileField.getText()),
                            (remarqueField.getText().isEmpty()?null:remarqueField.getText()),
                            (aSurveillerField.getText().isEmpty()?null:aSurveillerField.getText()),
                            (conseilsField.getText().isEmpty()?null:conseilsField.getText()),
                            donnerEtatBox.isSelected(),
                            besoinAvalBox.isSelected(),
                            acharnementTherapeutiqueBox.isSelected(),
                            (causeDecesPereField.getText().isEmpty()?null:causeDecesPereField.getText()),
                            (causeDecesMereField.getText().isEmpty()?null:causeDecesMereField.getText()),
                            (primeAnuelleField.getText().isEmpty()?0:Double.parseDouble(primeAnuelleField.getText())),
                            mutualite_id
                    );

                    controller.updatePatient(patient);

                    model.setValueAt(patient.getNumeroNational(), indiceLigneSelectionnee, 1);
                    model.setValueAt(patient.getNom(), indiceLigneSelectionnee, 2);
                    model.setValueAt(patient.getPrenom(), indiceLigneSelectionnee, 3);
                    model.setValueAt(patient.getNbEnfants(), indiceLigneSelectionnee, 4);
                    model.setValueAt(patient.getDateNaissance(), indiceLigneSelectionnee, 5);
                    model.setValueAt(patient.getNumTelFixe(), indiceLigneSelectionnee, 6);
                    model.setValueAt(patient.getNumTelMobile(), indiceLigneSelectionnee, 7);
                    model.setValueAt(patient.getRemarque(), indiceLigneSelectionnee, 8);
                    model.setValueAt(patient.getASurveiller(), indiceLigneSelectionnee, 9);
                    model.setValueAt(patient.getConseils(), indiceLigneSelectionnee, 10);
                    model.setValueAt(patient.isDonnerEtat(), indiceLigneSelectionnee, 11);
                    model.setValueAt(patient.isBesoinAval(), indiceLigneSelectionnee, 12);
                    model.setValueAt(patient.isAcharnementTherapeutique(), indiceLigneSelectionnee, 13);
                    model.setValueAt(patient.getCauseDecesPere(), indiceLigneSelectionnee, 14);
                    model.setValueAt(patient.getCauseDecesMere(), indiceLigneSelectionnee, 15);
                    model.setValueAt(patient.getPrimeAnuelle(), indiceLigneSelectionnee, 16);
                    model.setValueAt(patient.getMutualite_id(), indiceLigneSelectionnee, 17);

                    desactiverModifications();
                }
                catch (AccesDBException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (AucuneSelectionException exception){
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
                catch (FormatDateException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(event.getSource() == supprimerButton){
                try{
                    int indiceLigneSelectionnee = listSelect.getMinSelectionIndex();

                    if(indiceLigneSelectionnee == -1){
                        throw new AucuneSelectionException();
                    }

                    int reponse = JOptionPane.showConfirmDialog(PanneauListePatient.this, "Confirmer la suppression ?", "Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if(reponse == JOptionPane.YES_OPTION){
                        controller.deletePatient(Integer.parseInt(model.getValueAt(indiceLigneSelectionnee, 0).toString()));

                        model.removeRow(indiceLigneSelectionnee);

                        numeroNationalField.setText("");
                        nomField.setText("");
                        prenomField.setText("");
                        nbEnfantsField.setText("");
                        datePanel.setJourField("1");
                        datePanel.setMoisField("1");
                        datePanel.setAnneeField("1900");
                        numTelFixeField.setText("");
                        numTelMobileField.setText("");
                        remarqueField.setText("");
                        aSurveillerField.setText("");
                        conseilsField.setText("");
                        donnerEtatBox.setSelected(false);
                        besoinAvalBox.setSelected(false);
                        acharnementTherapeutiqueBox.setSelected(false);
                        causeDecesPereField.setText("");
                        causeDecesMereField.setText("");
                        primeAnuelleField.setText("");
                        mutualites.setSelectedItem("Aucune");

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

            if(event.getSource() == ajouterConsultationButton){
                try{
                    int indiceLigneSelectionnee = listSelect.getMinSelectionIndex();

                    if(indiceLigneSelectionnee == -1){
                        throw new AucuneSelectionException();
                    }

                    new FenetreAjouterConsultation(utilisateur, Integer.parseInt(model.getValueAt(indiceLigneSelectionnee, 0).toString()));
                }
                catch (AucuneSelectionException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(event.getSource() == ajouterAllergieButton){
                try{
                    int indiceLigneSelectionnee = listSelect.getMinSelectionIndex();

                    if(indiceLigneSelectionnee == -1){
                        throw new AucuneSelectionException();
                    }

                    new FenetreAjouterAllergie(Integer.parseInt(model.getValueAt(indiceLigneSelectionnee, 0).toString()));
                }
                catch (AucuneSelectionException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(event.getSource() == ajouterTraitementButton){
                try{
                    int indiceLigneSelectionnee = listSelect.getMinSelectionIndex();

                    if(indiceLigneSelectionnee == -1){
                        throw new AucuneSelectionException();
                    }

                    new FenetreAjouterTraitement(utilisateur, Integer.parseInt(model.getValueAt(indiceLigneSelectionnee, 0).toString()));
                }
                catch (AucuneSelectionException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(event.getSource() == ajouterProcheButton){
                try{
                    int indiceLigneSelectionnee = listSelect.getMinSelectionIndex();

                    if(indiceLigneSelectionnee == -1){
                        throw new AucuneSelectionException();
                    }

                    new FenetreAjouterProche(Integer.parseInt(model.getValueAt(indiceLigneSelectionnee, 0).toString()));
                }
                catch (AucuneSelectionException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}