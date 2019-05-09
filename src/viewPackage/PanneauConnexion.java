package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauConnexion extends JPanel{

    private Container frameContainer;
    private JLabel numNationalLabel, motDePasseLabel;
    private JTextField numNationalField;
    private JPasswordField motDePasseField;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JButton connexionBouton, inscriptionBouton, fermerBouton;
    private ApplicationController controller;
    private Soignant utilisateur;

    public PanneauConnexion(Container frameContainer){
        setController(new ApplicationController());
        this.frameContainer = frameContainer;
        this.setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));
        this.setLayout(new GridBagLayout());
        numNationalLabel = new JLabel("Login (?)");
        numNationalLabel.setToolTipText("Votre numéro national, chiffres uniquements");
        numNationalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,0,5,0);
        gbc.ipadx = 50;
        gbc.ipady = 5;
        this.add(numNationalLabel, gbc);

        numNationalField = new JTextField("11111111111");
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(numNationalField, gbc);

        motDePasseLabel = new JLabel("Mot de passe");
        motDePasseLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(motDePasseLabel, gbc);

        motDePasseField = new JPasswordField("premier");
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(motDePasseField, gbc);

        connexionBouton = new JButton("Se connecter");
        ButtonListener connexionListener = new ButtonListener();
        connexionBouton.addActionListener(connexionListener);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.insets = new Insets(10,10,5,10);
        this.add(connexionBouton, gbc);

        inscriptionBouton = new JButton("S'inscrire");
        ButtonListener inscriptionListener = new ButtonListener();
        inscriptionBouton.addActionListener(inscriptionListener);
        gbc.gridx = 1;
        gbc.gridy = 4;
        this.add(inscriptionBouton, gbc);

        fermerBouton = new JButton("Fermer l'application");
        ButtonListener fermerListener = new ButtonListener();
        fermerBouton.addActionListener(fermerListener);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        this.add(fermerBouton, gbc);
    }

    public void setController(ApplicationController applicationController){
        controller = applicationController;
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event) {
            if(event.getSource() == connexionBouton){
                try{
                    if(numNationalField.getText().isEmpty()){
                        throw new ChampsVideException("Login");
                    }
                    if(new String(motDePasseField.getPassword()).isEmpty()){
                        throw new ChampsVideException("Mot de passe");
                    }

                    utilisateur = controller.getSoignant(numNationalField.getText(), new String(motDePasseField.getPassword()));

                    frameContainer.removeAll();
                    frameContainer.add(new MenuUtilisateur(frameContainer, utilisateur));
                    frameContainer.revalidate();
                    frameContainer.repaint();
                }
                catch (AccesDBException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch(ChampsVideException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch(ConnexionException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch(CaracteresLimiteException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch(FormatNombreException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (CodeInvalideException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(event.getSource() == inscriptionBouton){
                frameContainer.removeAll();
                frameContainer.add(new PanneauInscriptionSoignant(frameContainer));
                frameContainer.revalidate();
                frameContainer.repaint();
            }

            if(event.getSource() == fermerBouton){
                System.exit(0);
            }
        }
    }
}
