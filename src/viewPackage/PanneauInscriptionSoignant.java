package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauInscriptionSoignant extends JPanel{

    private JLabel inscriptionLabel, numeroNationalLabel, motDePasseLabel, nomLabel, prenomLabel, numTelLabel, numeroINAMILabel, specialiteLabel;
    private JTextField numeroNationalField, nomField, prenomField, numTelField, numeroINAMIField;
    private JPasswordField motDePasseField;
    private JComboBox specialiteCbx;
    private JButton inscriptionBouton, reinitialiserBouton, retourBouton;
    private Container frameContainer;
    private Soignant soignant;
    private ApplicationController controller;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PanneauInscriptionSoignant(Container frameContainer){
        setController(new ApplicationController());
        this.frameContainer = frameContainer;
        setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));

        inscriptionLabel = new JLabel("Inscrivez-vous ici : ");
        inscriptionLabel.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 0;
        gbc.ipady = 10;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(inscriptionLabel, gbc);

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

        numeroNationalField = new JTextField();
        gbc.ipadx = 200;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(numeroNationalField, gbc);

        motDePasseField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(motDePasseField, gbc);

        nomField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(nomField, gbc);

        prenomField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 4;
        this.add(prenomField, gbc);

        numTelField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(numTelField, gbc);

        numeroINAMIField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 6;
        this.add(numeroINAMIField, gbc);

        String[ ] values = { "Docteur", "Psychiatre", "Chirurgien", "Autre (Veuillez spécifier)" };
        specialiteCbx = new JComboBox(values);
        specialiteCbx.setSelectedItem("Docteur");
        specialiteCbx.setEditable(true);
        gbc.ipadx = 35;
        gbc.ipady = 5;
        gbc.gridx = 1;
        gbc.gridy = 7;
        this.add(specialiteCbx, gbc);

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
        gbc.gridy = 8;
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

                    soignant = new Soignant(
                            null,
                            numeroNationalField.getText(),
                            new String(motDePasseField.getPassword()),
                            nomField.getText(),
                            prenomField.getText(),
                            numTelField.getText(),
                            (numeroINAMIField.getText().isEmpty()?null:numeroINAMIField.getText()),
                            specialiteCbx.getSelectedItem().toString()
                    );

                    controller.addSoignant(soignant);

                    frameContainer.removeAll();

                    new PageConnexion(frameContainer);
                    JOptionPane.showMessageDialog(null, "Vous avez bien été inscrit", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    frameContainer.revalidate();
                    frameContainer.repaint();
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


            if(event.getSource() == reinitialiserBouton){
                numeroNationalField.setText("");
                motDePasseField.setText("");
                nomField.setText("");
                prenomField.setText("");
                numTelField.setText("");
                numeroINAMIField.setText("");
                specialiteCbx.setSelectedItem("Docteur");
            }

            if(event.getSource() == retourBouton){
                frameContainer.removeAll();
                frameContainer.add(new PageConnexion(frameContainer));
                frameContainer.revalidate();
                frameContainer.repaint();
            }
        }
    }
}
