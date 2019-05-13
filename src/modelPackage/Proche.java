package modelPackage;

import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;

public class Proche {

    private Integer proche_id;
    private String nom;
    private String prenom;
    private String numTel;
    private String remarques;
    private boolean aAccesInfosMedicales;
    private boolean aAppellerSiUrgence;
    private Integer patient_id;

    public Proche(Integer proche_id, String nom, String prenom, String numTel, String remarques, boolean aAccesInfosMedicales, boolean aAppellerSiUrgence, Integer patient_id) throws ChampsVideException, CaracteresLimiteException{
        setProche_id(proche_id);
        setNom(nom);
        setPrenom(prenom);
        setNumTel(numTel);
        setRemarques(remarques);
        setAAccesInfosMedicales(aAccesInfosMedicales);
        setAAppellerSiUrgence(aAppellerSiUrgence);
        setPatient_id(patient_id);
    }

    public void setProche_id(Integer proche_id) {
        this.proche_id = proche_id;
    }

    public void setNom(String nom) throws ChampsVideException, CaracteresLimiteException{
        if(nom == null){
            throw new ChampsVideException("Nom");
        }
        if(nom.length() > 50){
            throw new CaracteresLimiteException("Nom");
        }
        this.nom = nom;
    }

    public void setPrenom(String prenom) throws ChampsVideException, CaracteresLimiteException{
        if(prenom == null){
            throw new ChampsVideException("Prénom");
        }
        if(prenom.length() > 50){
            throw new CaracteresLimiteException("Prénom");
        }
        this.prenom = prenom;
    }

    public void setNumTel(String numTel) throws ChampsVideException, CaracteresLimiteException{
        if(prenom == null){
            throw new ChampsVideException("Numéro de téléphone");
        }
        if(prenom.length() > 20){
            throw new CaracteresLimiteException("Numéro de téléphone");
        }
        this.numTel = numTel;
    }

    public void setRemarques(String remarques) throws CaracteresLimiteException{
        if(remarques != null){
            if(remarques.length() > 250){
                throw new CaracteresLimiteException("Remarque");
            }
        }
        this.remarques = remarques;
    }

    public void setAAccesInfosMedicales(Boolean aAccesInfosMedicales) {
        this.aAccesInfosMedicales = aAccesInfosMedicales;
    }

    public void setAAppellerSiUrgence(Boolean aAppellerSiUrgence) {
        this.aAppellerSiUrgence = aAppellerSiUrgence;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public Integer getProche_id() {
        return proche_id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumTel() {
        return numTel;
    }

    public String getRemarques() {
        return remarques;
    }

    public boolean isAAccesInfosMedicales() {
        return aAccesInfosMedicales;
    }

    public boolean isAAppellerSiUrgence() {
        return aAppellerSiUrgence;
    }

    public Integer getPatient_id() {
        return patient_id;
    }
}
