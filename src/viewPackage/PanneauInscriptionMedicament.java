package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Medicament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauInscriptionMedicament extends JPanel{

    private JLabel medicamentLabel, codeCNKLabel, nomLabel, firmeLabel, principeActifLabel, codeATCLabel,
            caracteristiqueLabel, tauxRemboursementLabel;
    private JTextField codeCNKField, nomField, firmeField, principeActifField, codeATCField,
            caracteristiqueField, tauxRemboursementField;
    private JButton inscriptionBouton, reinitialiserBouton, retourBouton;
    private Container frameContainer;
    private ApplicationController controller;
    private Medicament medicament;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PanneauInscriptionMedicament(Container frameContainer){
        this.frameContainer = frameContainer;
        setController(new ApplicationController());
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));

        medicamentLabel = new JLabel("Inscrivez un médicament");
        medicamentLabel.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 0;
        gbc.ipady = 10;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(medicamentLabel, gbc);

        codeCNKLabel = new JLabel("Code CNK*");
        codeCNKLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        this.add(codeCNKLabel, gbc);

        nomLabel = new JLabel("Nom*");
        nomLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 2;
        this.add(nomLabel, gbc);

        firmeLabel = new JLabel("Firme*");
        firmeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 3;
        this.add(firmeLabel, gbc);

        principeActifLabel = new JLabel("Principe actif*");
        principeActifLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 4;
        this.add(principeActifLabel, gbc);

        codeATCLabel = new JLabel("Code ATC*");
        codeATCLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 5;
        this.add(codeATCLabel, gbc);

        caracteristiqueLabel = new JLabel("Caractéristique*");
        caracteristiqueLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 6;
        this.add(caracteristiqueLabel, gbc);

        tauxRemboursementLabel = new JLabel("Taux de remboursement (?)*");
        tauxRemboursementLabel.setToolTipText("Exprimé en pourcent au format xx.xx (Exemple : 25.84)");
        tauxRemboursementLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 7;
        this.add(tauxRemboursementLabel, gbc);

        codeCNKField = new JTextField();
        gbc.ipadx = 200;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(codeCNKField, gbc);

        nomField = new JTextField();
        gbc.gridy = 2;
        this.add(nomField, gbc);

        firmeField = new JTextField();
        gbc.gridy = 3;
        this.add(firmeField, gbc);

        principeActifField = new JTextField();
        gbc.gridy = 4;
        this.add(principeActifField, gbc);

        codeATCField = new JTextField();
        gbc.gridy = 5;
        this.add(codeATCField, gbc);

        caracteristiqueField = new JTextField();
        gbc.gridy = 6;
        this.add(caracteristiqueField, gbc);

        tauxRemboursementField = new JTextField();
        gbc.gridy = 7;
        this.add(tauxRemboursementField, gbc);

        inscriptionBouton = new JButton("Valider");
        ButtonListener inscriptionListener = new ButtonListener();
        inscriptionBouton.addActionListener(inscriptionListener);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.ipadx = 80;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(inscriptionBouton, gbc);

        reinitialiserBouton = new JButton("Réinitialiser");
        ButtonListener reinitialiserListener = new ButtonListener();
        reinitialiserBouton.addActionListener(reinitialiserListener);
        gbc.gridx = 1;
        this.add(reinitialiserBouton, gbc);

        retourBouton = new JButton("Retour");
        ButtonListener retourListener = new ButtonListener();
        retourBouton.addActionListener(retourListener);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.ipadx = 250;
        gbc.gridwidth = 2;
        this.add(retourBouton, gbc);
    }

    public void setController(ApplicationController applicationController){
        controller = applicationController;
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {

            if(event.getSource() == inscriptionBouton){
                try{
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
                            null,
                            codeCNKField.getText(),
                            nomField.getText(),
                            firmeField.getText(),
                            principeActifField.getText(),
                            codeATCField.getText(),
                            caracteristiqueField.getText(),
                            Double.parseDouble(tauxRemboursementField.getText())
                    );

                    controller.addMedicament(medicament);

                    frameContainer.removeAll();
                    frameContainer.add(new MenuUtilisateur(frameContainer));
                    frameContainer.revalidate();
                    frameContainer.repaint();
                    JOptionPane.showMessageDialog(null, "Le médicament a été ajoutée", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
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


            if(event.getSource() == reinitialiserBouton){
                codeCNKField.setText("");
                nomField.setText("");
                firmeField.setText("");
                principeActifField.setText("");
                codeATCField.setText("");
                caracteristiqueField.setText("");
                tauxRemboursementField.setText("");
            }

            if(event.getSource() == retourBouton){
                frameContainer.removeAll();
                frameContainer.add(new MenuUtilisateur(frameContainer));
                frameContainer.revalidate();
                frameContainer.repaint();
            }
        }
    }
}

