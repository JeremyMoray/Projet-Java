package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AccesDBException;
import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;
import modelPackage.Mutualite;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauInscriptionMutualite extends JPanel{

    private JLabel mutualiteLabel, libelleLabel, affiliationPolitiqueLabel, diminutifLabel;
    private JTextField libelleField, affiliationPolitiqueField, diminutifField;
    private JButton inscriptionBouton, reinitialiserBouton, retourBouton;
    private Container frameContainer;
    private ApplicationController controller;
    private Mutualite mutualite;
    private GridBagConstraints gbc = new GridBagConstraints();

    public PanneauInscriptionMutualite(Container frameContainer){
        this.frameContainer = frameContainer;
        setController(new ApplicationController());
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));

        mutualiteLabel = new JLabel("Inscrivez une mutualité");
        mutualiteLabel.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 0;
        gbc.ipady = 10;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(mutualiteLabel, gbc);

        libelleLabel = new JLabel("Nom*");
        libelleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        this.add(libelleLabel, gbc);

        affiliationPolitiqueLabel = new JLabel("Affiliation politique*");
        affiliationPolitiqueLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 2;
        this.add(affiliationPolitiqueLabel, gbc);

        diminutifLabel = new JLabel("Diminutif*");
        diminutifLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridy = 3;
        this.add(diminutifLabel, gbc);

        libelleField = new JTextField();
        gbc.ipadx = 200;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(libelleField, gbc);

        affiliationPolitiqueField = new JTextField();
        gbc.gridy = 2;
        this.add(affiliationPolitiqueField, gbc);

        diminutifField = new JTextField();
        gbc.gridy = 3;
        this.add(diminutifField, gbc);

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
                    if(libelleField.getText().isEmpty()){
                        throw new ChampsVideException("Nom");
                    }
                    if(libelleField.getText().length() > 50){
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
                            null,
                            libelleField.getText(),
                            affiliationPolitiqueField.getText(),
                            diminutifField.getText()
                    );

                    controller.addMutualite(mutualite);

                    frameContainer.removeAll();
                    frameContainer.add(new MenuUtilisateur(frameContainer));
                    frameContainer.revalidate();
                    frameContainer.repaint();
                    JOptionPane.showMessageDialog(null, "La mutualité a été ajoutée", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
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


            if(event.getSource() == reinitialiserBouton){
                libelleField.setText("");
                affiliationPolitiqueField.setText("");
                diminutifField.setText("");
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
