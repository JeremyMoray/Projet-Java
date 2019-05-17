package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUtilisateur extends JPanel{

    private static Container frameContainerActuel;
    private static Soignant utilisateurActuel;
    private ApplicationController controller;
    private JMenuBar menuBar;
    private JMenu application, soignant, patient, medicament, allergie, mutualite, autres;
    private JMenuItem accueil, quitter, monProfil, rechercheProcheDePatient, rechercheMedicamentPatient, recherchePrimePatient,
            seDeconnecter, inscriptionPatient, chercherPatient, supprimerProche, ajouterMedicament, listeMedicament, topMedicaments,
            totalPrimesAnuelles, ajouterAllergie, listeAllergie, ajouterMutualite, listeMutualite, aide, fermetureThread;
    private GridBagConstraints gbc = new GridBagConstraints();

    public MenuUtilisateur(){
        setController(new ApplicationController());
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        setBackground(new Color(0xFFE8E2DB, true));

        menuBar = new JMenuBar();
        frameContainerActuel.setLayout(new BorderLayout());
        frameContainerActuel.add(menuBar, BorderLayout.NORTH);

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

        //Cette recherche donne des infos sur tous les traitements ayant un patient sous la mutualité sélectionnée
        rechercheProcheDePatient = new JMenuItem("Rechercher les traitements prescrit sous cette mutualité");
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

        patient.addSeparator();

        totalPrimesAnuelles = new JMenuItem("Total primes anuelles des patients");
        TotalPrimesListener totalPrimesListener = new TotalPrimesListener();
        totalPrimesAnuelles.addActionListener(totalPrimesListener);
        patient.add(totalPrimesAnuelles);

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

        autres.addSeparator();

        fermetureThread = new JMenuItem("Fermer dans 5 secondes");
        CloseListener closeListener = new CloseListener();
        fermetureThread.addActionListener(closeListener);
        autres.add(fermetureThread);

        this.add(new PanneauBienvenue(), gbc);
    }

    private class AccueilListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            addPanel(new PanneauBienvenue());
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
            addPanel(new PanneauProfil());
        }
    }

    private class RechercheListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            if(event.getSource() == rechercheProcheDePatient){
                addPanel(new PanneauRechercheTraitements());
            }

            if(event.getSource() == rechercheMedicamentPatient){
                addPanel(new PanneauRechercheMedicaments());
            }

            if(event.getSource() == recherchePrimePatient){
                addPanel(new PanneauRechercheConsultations());
            }
        }
    }

    private class SeDeconnecterListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            frameContainerActuel.removeAll();
            frameContainerActuel.add(new PageConnexion(frameContainerActuel));
            frameContainerActuel.revalidate();
            frameContainerActuel.repaint();
        }
    }

    private class SuppressionListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            addPanel(new PanneauSuppressionProche());
        }
    }

    private class TopListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            controller.afficherTopMedicaments();
        }
    }

    private class TotalPrimesListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            try{
                JOptionPane.showMessageDialog(null, "Le total des primes annuelles des patients est de : " + controller.getTotalPrimesAnuelles() + " euros", "Total primes annuelles", JOptionPane.INFORMATION_MESSAGE);
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
            catch (CodeInvalideException exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            catch (FormatNombreException exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private class InscriptionListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            if(event.getSource() == inscriptionPatient){
                MenuUtilisateur.this.removeAll();
                JPanel patientPanel = new PanneauInscriptionPatient();
                JScrollPane scrollPane = new JScrollPane(patientPanel);
                scrollPane.setPreferredSize(new Dimension(600, 650));
                scrollPane.setBorder(BorderFactory.createLineBorder(FenetreMenu.getBorderTheme(), 3, true));
                MenuUtilisateur.this.add(scrollPane, gbc);
                MenuUtilisateur.this.revalidate();
                MenuUtilisateur.this.repaint();
            }

            if(event.getSource() == ajouterMutualite){
                addPanel(new PanneauInscriptionMutualite());
            }

            if(event.getSource() == ajouterAllergie){
                addPanel(new PanneauInscriptionAllergie());
            }

            if(event.getSource() == ajouterMedicament){
                addPanel(new PanneauInscriptionMedicament());
            }
        }
    }

    private  class ListeListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event) {
            if(event.getSource() == chercherPatient){
                addPanel(new PanneauListePatient());
            }

            if(event.getSource() == listeMutualite){
                addPanel(new PanneauListeMutualite());
            }

            if(event.getSource() == listeAllergie){
                addPanel(new PanneauListeAllergie());
            }

            if(event.getSource() == listeMedicament){
                addPanel(new PanneauListeMedicament());
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

    private class CloseListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            ThreadClass thread = new ThreadClass();
            thread.start();
        }
    }

    public static Soignant getUtilisateurActuel(){
        return utilisateurActuel;
    }

    public static void setUtilisateurActuel(Soignant utilisateur){
        utilisateurActuel = utilisateur;
    }

    public static Container getFrameContainerActuel(){
        return frameContainerActuel;
    }

    public static void setFrameContainerActuel(Container frameContainer){
        frameContainerActuel = frameContainer;
    }

    private void addPanel(JPanel panel){
        MenuUtilisateur.this.removeAll();
        MenuUtilisateur.this.add(panel, gbc);
        MenuUtilisateur.this.revalidate();
        MenuUtilisateur.this.repaint();
    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }
}
