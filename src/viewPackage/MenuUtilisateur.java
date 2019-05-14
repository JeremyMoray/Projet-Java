package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AccesDBException;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUtilisateur extends JPanel{

    private Container frameContainer;
    private Soignant utilisateur;
    private ApplicationController controller;
    private JMenuBar menuBar;
    private JMenu application, soignant, patient, medicament, allergie, mutualite, autres;
    private JMenuItem accueil, quitter, monProfil, rechercheProcheDePatient, rechercheMedicamentPatient, recherchePrimePatient,
            seDeconnecter, inscriptionPatient, chercherPatient, supprimerProche, ajouterMedicament, listeMedicament, topMedicaments,
            ajouterAllergie, listeAllergie, ajouterMutualite, listeMutualite, aide;
    private GridBagConstraints gbc = new GridBagConstraints();

    public MenuUtilisateur(Container frameContainer, Soignant utilisateur){
        this.frameContainer = frameContainer;
        this.utilisateur = utilisateur;

        setController(new ApplicationController());
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        setBackground(new Color(0xFFE8E2DB, true));

        menuBar = new JMenuBar();
        frameContainer.setLayout(new BorderLayout());
        frameContainer.add(menuBar, BorderLayout.NORTH);

        //Application

        application = new JMenu("Application");
        application.setMnemonic('A');
        menuBar.add(application);

        accueil = new JMenuItem("Accueil");
        application.add(accueil);
        AccueilListener accueilListener = new AccueilListener();
        accueil.addActionListener(accueilListener);

        application.addSeparator();

        quitter = new JMenuItem("Quitter");
        application.add(quitter);
        ExitListener exitListener = new ExitListener();
        quitter.addActionListener(exitListener);

        //Soignant

        soignant = new JMenu("Utilisateur");
        soignant.setMnemonic('U');
        menuBar.add(soignant);

        monProfil = new JMenuItem("Mon profil");
        soignant.add(monProfil);
        MonProfilListener monProfilListener = new MonProfilListener();
        monProfil.addActionListener(monProfilListener);

        soignant.addSeparator();

        //Cette recherche trouve les proches des patients consultés par le soignant à appeller en cas d'urgence
        rechercheProcheDePatient = new JMenuItem("Rechercher des proches d'un patient (Urgence)");
        soignant.add(rechercheProcheDePatient);
        RechercheListener rechercheProcheDePatientListener = new RechercheListener();
        rechercheProcheDePatient.addActionListener(rechercheProcheDePatientListener);


        soignant.addSeparator();

        //Cette recherche trouve les infos d'un médicament d'un traitement de tous les patients consultés. La date de début du traitement étant choisie entre 2 dates.
        rechercheMedicamentPatient = new JMenuItem("Rechercher les médicaments d'un patient");
        soignant.add(rechercheMedicamentPatient);
        RechercheListener rechercheMedicamentPatientListener = new RechercheListener();
        rechercheMedicamentPatient.addActionListener(rechercheMedicamentPatientListener);

        soignant.addSeparator();

        //Cette recherche trouve les infos d'un médicament d'un traitement de tous les patients consultés. La date de début du traitement étant choisie entre 2 dates.
        recherchePrimePatient = new JMenuItem("Rechercher les primes de vos patients consultés");
        soignant.add(recherchePrimePatient);
        RechercheListener recherchePrimePatientListener = new RechercheListener();
        recherchePrimePatient.addActionListener(recherchePrimePatientListener);

        soignant.addSeparator();

        seDeconnecter = new JMenuItem("Se déconnecter");
        soignant.add(seDeconnecter);
        SeDeconnecterListener seDeconnecterListener = new SeDeconnecterListener();
        seDeconnecter.addActionListener(seDeconnecterListener);

        //Patient

        patient = new JMenu("Patient");
        patient.setMnemonic('P');
        menuBar.add(patient);

        inscriptionPatient = new JMenuItem("Inscrire un patient");
        InscriptionListener inscriptionPatientListener = new InscriptionListener();
        inscriptionPatient.addActionListener(inscriptionPatientListener);
        patient.add(inscriptionPatient);

        patient.addSeparator();

        chercherPatient = new JMenuItem("Chercher un patient");
        ListeListener listePatientListener = new ListeListener();
        chercherPatient.addActionListener(listePatientListener);
        patient.add(chercherPatient);

        patient.addSeparator();

        supprimerProche = new JMenuItem("Supprimer le proche d'un patient");
        SuppressionListener supprimerProcheListener = new SuppressionListener();
        supprimerProche.addActionListener(supprimerProcheListener);
        patient.add(supprimerProche);

        //Médicament

        medicament = new JMenu("Médicament");
        medicament.setMnemonic('M');
        menuBar.add(medicament);

        ajouterMedicament = new JMenuItem("Ajouter un médicament");
        InscriptionListener inscriptionMedicamentListener = new InscriptionListener();
        ajouterMedicament.addActionListener(inscriptionMedicamentListener);
        medicament.add(ajouterMedicament);

        medicament.addSeparator();

        listeMedicament = new JMenuItem("Liste médicaments");
        ListeListener listeMedicamentListener = new ListeListener();
        listeMedicament.addActionListener(listeMedicamentListener);
        medicament.add(listeMedicament);

        medicament.addSeparator();

        topMedicaments = new JMenuItem("Top 5 médicaments");
        TopListener topMedicamentListener = new TopListener();
        topMedicaments.addActionListener(topMedicamentListener);
        medicament.add(topMedicaments);

        //Allergie

        allergie = new JMenu("Allergie");
        allergie.setMnemonic('r');
        menuBar.add(allergie);

        ajouterAllergie = new JMenuItem("Ajouter une allergie");
        InscriptionListener inscriptionAllergieListener = new InscriptionListener();
        ajouterAllergie.addActionListener(inscriptionAllergieListener);
        allergie.add(ajouterAllergie);

        allergie.addSeparator();

        listeAllergie = new JMenuItem("Liste allergies");
        ListeListener listeAllergieListener = new ListeListener();
        listeAllergie.addActionListener(listeAllergieListener);
        allergie.add(listeAllergie);

        //Mutualité

        mutualite = new JMenu("Mutualité");
        mutualite.setMnemonic('l');
        menuBar.add(mutualite);

        ajouterMutualite = new JMenuItem("Ajouter une mutualité");
        InscriptionListener inscriptionMutualiteListener = new InscriptionListener();
        ajouterMutualite.addActionListener(inscriptionMutualiteListener);
        mutualite.add(ajouterMutualite);

        mutualite.addSeparator();

        listeMutualite = new JMenuItem("Liste mutualités");
        ListeListener listeMutualiteListener = new ListeListener();
        listeMutualite.addActionListener(listeMutualiteListener);
        mutualite.add(listeMutualite);

        //Autres

        autres = new JMenu("Autres");
        autres.setMnemonic('t');
        menuBar.add(autres);

        aide = new JMenuItem("Aide");
        AideListener aideListener = new AideListener();
        aide.addActionListener(aideListener);
        autres.add(aide);

        this.add(new PanneauBienvenue(utilisateur), gbc);
    }

    private class AccueilListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            MenuUtilisateur.this.removeAll();
            MenuUtilisateur.this.add(new PanneauBienvenue(utilisateur), gbc);
            MenuUtilisateur.this.revalidate();
            MenuUtilisateur.this.repaint();
        }
    }

    private class ExitListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            try{
                controller.closeConnection();
            }
            catch (AccesDBException exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                System.exit(0);
            }
        }
    }

    private class MonProfilListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            MenuUtilisateur.this.removeAll();
            MenuUtilisateur.this.add(new PanneauProfil(frameContainer, utilisateur), gbc);
            MenuUtilisateur.this.revalidate();
            MenuUtilisateur.this.repaint();
        }
    }

    private class RechercheListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            if(event.getSource() == rechercheProcheDePatient){
                MenuUtilisateur.this.removeAll();
                MenuUtilisateur.this.add(new PanneauRechercheProches(frameContainer, utilisateur));
                MenuUtilisateur.this.revalidate();
                MenuUtilisateur.this.repaint();
            }

            if(event.getSource() == rechercheMedicamentPatient){
                MenuUtilisateur.this.removeAll();
                MenuUtilisateur.this.add(new PanneauRechercheMedicaments(frameContainer, utilisateur));
                MenuUtilisateur.this.revalidate();
                MenuUtilisateur.this.repaint();
            }

            if(event.getSource() == recherchePrimePatient){
                MenuUtilisateur.this.removeAll();
                MenuUtilisateur.this.add(new PanneauRecherchePrimes(frameContainer, utilisateur));
                MenuUtilisateur.this.revalidate();
                MenuUtilisateur.this.repaint();
            }
        }
    }

    private class SeDeconnecterListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            frameContainer.removeAll();
            frameContainer.add(new PageConnexion(frameContainer));
            frameContainer.revalidate();
            frameContainer.repaint();
        }
    }

    private class SuppressionListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            MenuUtilisateur.this.removeAll();
            MenuUtilisateur.this.add(new PanneauSuppressionProche(frameContainer, utilisateur));
            MenuUtilisateur.this.revalidate();
            MenuUtilisateur.this.repaint();
        }
    }

    private class TopListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            controller.afficherTopMedicaments();
        }
    }

    private class InscriptionListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            if(event.getSource() == inscriptionPatient){
                MenuUtilisateur.this.removeAll();
                JPanel patientPanel = new PanneauInscriptionPatient(frameContainer, utilisateur);
                JScrollPane scrollPane = new JScrollPane(patientPanel);
                scrollPane.setPreferredSize(new Dimension(600, 650));
                scrollPane.setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));
                MenuUtilisateur.this.add(scrollPane, gbc);
                MenuUtilisateur.this.revalidate();
                MenuUtilisateur.this.repaint();
            }

            if(event.getSource() == ajouterMutualite){
                MenuUtilisateur.this.removeAll();
                MenuUtilisateur.this.add(new PanneauInscriptionMutualite(frameContainer, utilisateur), gbc);
                MenuUtilisateur.this.revalidate();
                MenuUtilisateur.this.repaint();
            }

            if(event.getSource() == ajouterAllergie){
                MenuUtilisateur.this.removeAll();
                MenuUtilisateur.this.add(new PanneauInscriptionAllergie(frameContainer, utilisateur), gbc);
                MenuUtilisateur.this.revalidate();
                MenuUtilisateur.this.repaint();
            }

            if(event.getSource() == ajouterMedicament){
                MenuUtilisateur.this.removeAll();
                MenuUtilisateur.this.add(new PanneauInscriptionMedicament(frameContainer, utilisateur), gbc);
                MenuUtilisateur.this.revalidate();
                MenuUtilisateur.this.repaint();
            }
        }
    }

    private  class ListeListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            if(event.getSource() == chercherPatient){
                MenuUtilisateur.this.removeAll();
                MenuUtilisateur.this.add(new PanneauListePatient(frameContainer, utilisateur), gbc);
                MenuUtilisateur.this.revalidate();
                MenuUtilisateur.this.repaint();
            }

            if(event.getSource() == listeMutualite){
                MenuUtilisateur.this.removeAll();
                MenuUtilisateur.this.add(new PanneauListeMutualite(frameContainer, utilisateur), gbc);
                MenuUtilisateur.this.revalidate();
                MenuUtilisateur.this.repaint();
            }

            if(event.getSource() == listeAllergie){
                MenuUtilisateur.this.removeAll();
                MenuUtilisateur.this.add(new PanneauListeAllergie(frameContainer, utilisateur), gbc);
                MenuUtilisateur.this.revalidate();
                MenuUtilisateur.this.repaint();
            }

            if(event.getSource() == listeMedicament){
                MenuUtilisateur.this.removeAll();
                MenuUtilisateur.this.add(new PanneauListeMedicament(frameContainer, utilisateur), gbc);
                MenuUtilisateur.this.revalidate();
                MenuUtilisateur.this.repaint();
            }
        }
    }

    private class AideListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            new FenetreAide();
        }
    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }
}
