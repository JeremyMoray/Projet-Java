package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Patient;
import modelPackage.Soignant;

import javax.swing.*;
import java.awt.*;

public class PanneauListePatient extends JPanel{

    private Container frameContainer;
    private Soignant utilisateur;
    private ApplicationController controller ;
    private ListSelectionModel listSelect;
    private JButton modifierButton, supprimerButton;
    private AllMedicamentsModel model;
    private JTable table;
    private Patient patient;

    public PanneauListePatient(Container frameContainer, Soignant utilisateur){
        this.frameContainer = frameContainer;
        this.utilisateur = utilisateur;
        setController(new ApplicationController());

    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }
}