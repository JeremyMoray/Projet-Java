package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Medicament;
import modelPackage.Soignant;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PanneauListeMedicament extends JPanel{

    private Container frameContainer;
    private Soignant utilisateur;
    private ApplicationController controller ;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ListSelectionModel listSelect;
    private JButton modifierButton, supprimerButton;
    private JLabel codeCNKLabel, nomLabel, firmeLabel, principeActifLabel, codeATCLabel,
            caracteristiqueLabel, tauxRemboursementLabel;
    private JTextField codeCNKField, nomField, firmeField, principeActifField, codeATCField,
            caracteristiqueField, tauxRemboursementField;
    private AllMedicamentsModel model;
    private JTable table;
    private Medicament medicament;

    public PanneauListeMedicament(Container frameContainer, Soignant utilisateur){
        try{
            this.frameContainer = frameContainer;
            this.utilisateur = utilisateur;
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
            setController(new ApplicationController());
            setLayout(new GridBagLayout());

            ArrayList<Medicament> medicaments = controller.getAllMedicaments();
            model = new AllMedicamentsModel(medicaments);

            table = new JTable(model);

            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setAutoCreateRowSorter(true);
            listSelect = table.getSelectionModel();

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    activerModifications();

                    int indiceLigneSelectionnee = listSelect.getMinSelectionIndex();

                    codeCNKField.setText(model.getValueAt(indiceLigneSelectionnee, 1).toString());
                    nomField.setText(model.getValueAt(indiceLigneSelectionnee, 2).toString());
                    firmeField.setText(model.getValueAt(indiceLigneSelectionnee, 3).toString());
                    principeActifField.setText(model.getValueAt(indiceLigneSelectionnee, 4).toString());
                    codeATCField.setText(model.getValueAt(indiceLigneSelectionnee, 5).toString());
                    caracteristiqueField.setText(model.getValueAt(indiceLigneSelectionnee, 6).toString());
                    tauxRemboursementField.setText(model.getValueAt(indiceLigneSelectionnee, 7).toString());
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

            gbc.ipadx = 650;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 8;
            this.add(new JScrollPane(table), gbc);

            gbc.insets = new Insets(20,10,10,10);
            gbc.ipadx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.EAST;
            codeCNKLabel = new JLabel("Code CNK :");
            this.add(codeCNKLabel, gbc);

            gbc.gridx = 1;
            gbc.ipadx = 100;
            gbc.anchor = GridBagConstraints.CENTER;
            codeCNKField = new JTextField();
            this.add(codeCNKField, gbc);

            gbc.gridx = 2;
            gbc.ipadx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            nomLabel = new JLabel("Nom :");
            this.add(nomLabel, gbc);

            gbc.gridx = 3;
            gbc.ipadx = 100;
            gbc.anchor = GridBagConstraints.CENTER;
            nomField = new JTextField();
            this.add(nomField, gbc);

            gbc.gridx = 4;
            gbc.ipadx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            firmeLabel = new JLabel("Firme :");
            this.add(firmeLabel, gbc);

            gbc.gridx = 5;
            gbc.ipadx = 100;
            gbc.anchor = GridBagConstraints.CENTER;
            firmeField = new JTextField();
            this.add(firmeField, gbc);

            gbc.gridx = 6;
            gbc.ipadx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            principeActifLabel = new JLabel("Principe actif :");
            this.add(principeActifLabel, gbc);

            gbc.gridx = 7;
            gbc.ipadx = 100;
            gbc.anchor = GridBagConstraints.CENTER;
            principeActifField = new JTextField();
            this.add(principeActifField, gbc);

            gbc.insets = new Insets(10,10,20,10);
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.ipadx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            codeATCLabel = new JLabel("Code ATC :");
            this.add(codeATCLabel, gbc);

            gbc.gridx = 1;
            gbc.ipadx = 100;
            gbc.anchor = GridBagConstraints.CENTER;
            codeATCField = new JTextField();
            this.add(codeATCField, gbc);

            gbc.gridx = 2;
            gbc.ipadx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            caracteristiqueLabel = new JLabel("Caractéristique :");
            this.add(caracteristiqueLabel, gbc);

            gbc.gridx = 3;
            gbc.ipadx = 100;
            gbc.anchor = GridBagConstraints.CENTER;
            caracteristiqueField = new JTextField();
            this.add(caracteristiqueField, gbc);

            gbc.gridx = 4;
            gbc.ipadx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            tauxRemboursementLabel = new JLabel("Taux de remboursement (?) :");
            tauxRemboursementLabel.setToolTipText("Exprimé en pourcent au format xx.xx (Exemple : 25.84)");
            this.add(tauxRemboursementLabel, gbc);

            gbc.gridx = 5;
            gbc.ipadx = 100;
            gbc.anchor = GridBagConstraints.CENTER;
            tauxRemboursementField = new JTextField();
            this.add(tauxRemboursementField, gbc);

            //Boutons

            gbc.ipadx = 50;
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
        catch (FormatNombreException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (CodeInvalideException exception){
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

                    if(codeCNKField.getText().isEmpty()){
                        throw new ChampsVideException("Code CNK");
                    }
                    if(codeCNKField.getText().length() != 7){
                        throw new CodeInvalideException("Code CNK", "Veuillez entrer un code CNK composé de 7 chiffres uniquement");
                    }
                    if(!codeCNKField.getText().matches("[0-9]*")){
                        throw new FormatNombreException("Code CNK");
                    }

                    if(nomField.getText().isEmpty()){
                        throw new ChampsVideException("Nom");
                    }
                    if(nomField.getText().length() > 50){
                        throw new CaracteresLimiteException("Nom");
                    }

                    if(firmeField.getText().isEmpty()){
                        throw new ChampsVideException("Firme");
                    }
                    if(firmeField.getText().length() > 50){
                        throw new CaracteresLimiteException("Firme");
                    }

                    if(principeActifField.getText().isEmpty()){
                        throw new ChampsVideException("Principe actif");
                    }
                    if(principeActifField.getText().length() > 50){
                        throw new CaracteresLimiteException("Principe actif");
                    }

                    if(codeATCField.getText().isEmpty()){
                        throw new ChampsVideException("Code ATC");
                    }
                    if(codeATCField.getText().length() != 8){
                        throw new CodeInvalideException("Code ATC", "Veuillez entrer un code ATC composé de 8 caractères uniquement");
                    }

                    if(caracteristiqueField.getText().isEmpty()){
                        throw new ChampsVideException("Caractéristique");
                    }
                    if(caracteristiqueField.getText().length() > 250){
                        throw new CaracteresLimiteException("Caractéristique");
                    }

                    if(tauxRemboursementField.getText().isEmpty()){
                        throw new ChampsVideException("Taux de remboursement");
                    }
                    try{
                        if(Double.parseDouble(tauxRemboursementField.getText()) < 0
                                ||Double.parseDouble(tauxRemboursementField.getText()) > 100){
                            throw new FormatNombreException("Taux de remboursement");
                        }
                    }
                    catch(NumberFormatException exception){
                        throw new FormatNombreException("Taux de remboursement");
                    }

                    medicament = new Medicament(
                            Integer.parseInt(model.getValueAt(indiceLigneSelectionnee, 0).toString()),
                            codeCNKField.getText(),
                            nomField.getText(),
                            firmeField.getText(),
                            principeActifField.getText(),
                            codeATCField.getText(),
                            caracteristiqueField.getText(),
                            Double.parseDouble(tauxRemboursementField.getText())
                    );

                    controller.updateMedicament(medicament);

                    model.setValueAt(medicament.getCodeCNK(), indiceLigneSelectionnee, 1);
                    model.setValueAt(medicament.getNom(), indiceLigneSelectionnee, 2);
                    model.setValueAt(medicament.getFirme(), indiceLigneSelectionnee, 3);
                    model.setValueAt(medicament.getPrincipeActif(), indiceLigneSelectionnee, 4);
                    model.setValueAt(medicament.getCodeATC(), indiceLigneSelectionnee, 5);
                    model.setValueAt(medicament.getCaracteristique(), indiceLigneSelectionnee, 6);
                    model.setValueAt(medicament.getTauxRemboursement(), indiceLigneSelectionnee, 7);

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
            }

            if (event.getSource() == supprimerButton) {
                try{
                    int indiceLigneSelectionnee = listSelect.getMinSelectionIndex();

                    if(indiceLigneSelectionnee == -1){
                        throw new AucuneSelectionException();
                    }

                    controller.deleteMedicament(Integer.parseInt(model.getValueAt(indiceLigneSelectionnee, 0).toString()));

                    model.removeRow(indiceLigneSelectionnee);

                    codeCNKField.setText("");
                    nomField.setText("");
                    firmeField.setText("");
                    principeActifField.setText("");
                    codeATCField.setText("");
                    caracteristiqueField.setText("");
                    tauxRemboursementField.setText("");

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
        codeCNKField.setEditable(true);
        nomField.setEditable(true);
        firmeField.setEditable(true);
        principeActifField.setEditable(true);
        codeATCField.setEditable(true);
        caracteristiqueField.setEditable(true);
        tauxRemboursementField.setEditable(true);
    }

    public void desactiverModifications(){
        codeCNKField.setEditable(false);
        nomField.setEditable(false);
        firmeField.setEditable(false);
        principeActifField.setEditable(false);
        codeATCField.setEditable(false);
        caracteristiqueField.setEditable(false);
        tauxRemboursementField.setEditable(false);
    }
}

