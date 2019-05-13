package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauProfil extends JPanel{

    private JLabel profilLabel, numeroNationalLabel, motDePasseLabel, nomLabel, prenomLabel, numTelLabel, numeroINAMILabel, specialiteLabel;
    private JTextField numeroNationalField, nomField, prenomField, numTelField, numeroINAMIField;
    private JPasswordField motDePasseField;
    private JComboBox specialiteCbx;
    private JButton enregistrerBoutton, annulerBouton, supprimerBoutton;
    private Soignant utilisateur;
    private Soignant soignantModifié;
    private ApplicationController controller;
    private Container frameContainer;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PanneauProfil(Container frameContainer, Soignant utilisateur){

        this.frameContainer = frameContainer;
        this.utilisateur = utilisateur;
        setController(new ApplicationController());
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));

        profilLabel = new JLabel("Mon profil");
        profilLabel.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 0;
        gbc.ipady = 10;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(profilLabel, gbc);

        numeroNationalLabel = new JLabel("Numéro national* (?)");
        numeroNationalLabel.setToolTipText("N'entrez que les 11 chiffres");
        numeroNationalLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(numeroNationalLabel, gbc);

        motDePasseLabel = new JLabel("Mot de passe*");
        motDePasseLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(motDePasseLabel, gbc);

        nomLabel = new JLabel("Nom*");
        nomLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(nomLabel, gbc);

        prenomLabel = new JLabel("Prénom*");
        prenomLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(prenomLabel, gbc);

        numTelLabel = new JLabel("Numéro de téléphone*");
        numTelLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(numTelLabel, gbc);

        numeroINAMILabel = new JLabel("(Numéro INAMI)");
        numeroINAMILabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 6;
        this.add(numeroINAMILabel, gbc);

        specialiteLabel = new JLabel("Spécialité*");
        specialiteLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 7;
        this.add(specialiteLabel, gbc);

        numeroNationalField = new JTextField(utilisateur.getNumeroNational(), 1);
        gbc.ipadx = 200;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(numeroNationalField, gbc);

        motDePasseField = new JPasswordField(utilisateur.getMotDePasse(), 1);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(motDePasseField, gbc);

        nomField = new JTextField(utilisateur.getNom(),1);
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(nomField, gbc);

        prenomField = new JTextField(utilisateur.getPrenom(),1);
        gbc.gridx = 1;
        gbc.gridy = 4;
        this.add(prenomField, gbc);

        numTelField = new JTextField(utilisateur.getNumTel(),1);
        gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(numTelField, gbc);

        numeroINAMIField = new JTextField(utilisateur.getNumeroINAMI(),1);
        gbc.gridx = 1;
        gbc.gridy = 6;
        this.add(numeroINAMIField, gbc);

        String[ ] values = { "Docteur", "Psychiatre", "Chirurgien", "Autre (Veuillez spécifier)" };
        specialiteCbx = new JComboBox(values);
        specialiteCbx.setSelectedItem(utilisateur.getSpecialite());
        specialiteCbx.setEditable(true);
        gbc.ipadx = 45;
        gbc.ipady = 5;
        gbc.gridx = 1;
        gbc.gridy = 7;
        this.add(specialiteCbx, gbc);

        enregistrerBoutton = new JButton("Enregistrer");
        ButtonListener enregistrerListener = new ButtonListener();
        enregistrerBoutton.addActionListener(enregistrerListener);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.ipadx = 155;
        gbc.gridx = 0;
        gbc.gridy = 8;
        this.add(enregistrerBoutton, gbc);

        annulerBouton = new JButton("Annuler");
        ButtonListener annulerListener = new ButtonListener();
        annulerBouton.addActionListener(annulerListener);
        gbc.ipadx = 175;
        gbc.gridx = 0;
        gbc.gridy = 9;
        this.add(annulerBouton, gbc);

        supprimerBoutton = new JButton("Supprimer mon profil");
        ButtonListener supprimerListener = new ButtonListener();
        supprimerBoutton.addActionListener(supprimerListener);
        gbc.ipadx = 100;
        gbc.gridx = 0;
        gbc.gridy = 10;
        this.add(supprimerBoutton, gbc);
    }

    public void setController(ApplicationController applicationController){
        controller = applicationController;
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == enregistrerBoutton){
                try{
                    if(numeroNationalField.getText().isEmpty()){
                        throw new ChampsVideException("Numéro national");
                    }
                    if(!numeroNationalField.getText().matches("[0-9]*")){
                        throw new FormatNombreException("Numéro national");
                    }
                    if(numeroNationalField.getText().length() != 11){
                        throw new CodeInvalideException("Numéro national", "Veuillez entrer un numéro national composé de 11 chiffres uniquement");
                    }

                    if(new String(motDePasseField.getPassword()).isEmpty()){
                        throw new ChampsVideException("Mot de passe");
                    }
                    if(new String(motDePasseField.getPassword()).length() > 30){
                        throw new CaracteresLimiteException("Mot de passe");
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

                    if(numTelField.getText().isEmpty()){
                        throw new ChampsVideException("Numéro de téléphone");
                    }
                    if(numTelField.getText().length() > 20){
                        throw new CaracteresLimiteException("Numéro de téléphone");
                    }

                    if(!numeroINAMIField.getText().isEmpty()){
                        if(!numeroINAMIField.getText().matches("[0-9]+")){
                            throw new FormatNombreException("Numéro INAMI");
                        }
                        if(numeroINAMIField.getText().length() != 11){
                            throw new CodeInvalideException("Numéro INAMI", "Veuillez entrer un numéro INAMI composé de 11 chiffres uniquement");
                        }
                    }

                    if(specialiteCbx.getSelectedItem().toString().isEmpty()){
                        throw new ChampsVideException("Spécialité");
                    }
                    if(specialiteCbx.getSelectedItem().toString().length() > 50){
                        throw new CaracteresLimiteException("Spécialité");
                    }

                    soignantModifié = new Soignant(
                            null,
                            numeroNationalField.getText(),
                            new String(motDePasseField.getPassword()),
                            nomField.getText(),
                            prenomField.getText(),
                            numTelField.getText(),
                            (numeroINAMIField.getText().isEmpty()?null:numeroINAMIField.getText()),
                            specialiteCbx.getSelectedItem().toString()
                    );

                    controller.updateSoignant(soignantModifié, utilisateur.getId());

                    frameContainer.removeAll();
                    frameContainer.add(new MenuUtilisateur(frameContainer, controller.getSoignant(utilisateur.getId())));
                    frameContainer.revalidate();
                    frameContainer.repaint();

                    JOptionPane.showMessageDialog(null, "Vos modifications ont bien été prises en compte", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (AccesDBException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (CodeInvalideException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (ChampsVideException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (ChampsExistantException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (FormatNombreException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (CaracteresLimiteException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(event.getSource() == annulerBouton){
                frameContainer.removeAll();
                frameContainer.add(new MenuUtilisateur(frameContainer, utilisateur));
                frameContainer.revalidate();
                frameContainer.repaint();
            }

            if(event.getSource() == supprimerBoutton){
                try{
                    int reponse = JOptionPane.showConfirmDialog(PanneauProfil.this, "Confirmer la suppression de votre compte ?", "Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if(reponse == JOptionPane.YES_OPTION){
                        controller.deleteSoignant(utilisateur.getId());

                        frameContainer.removeAll();
                        frameContainer.add(new PageConnexion(frameContainer));
                        frameContainer.revalidate();
                        frameContainer.repaint();

                        JOptionPane.showMessageDialog(null, "Votre compte a bien été supprimé", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch (AccesDBException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}