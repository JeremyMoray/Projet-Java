package modelPackage;

import exceptionPackage.*;

import java.util.GregorianCalendar;

public class Patient {

    private Integer patient_id;
    private String numeroNational;
    private String nom;
    private String prenom;
    private Integer nbEnfants;
    private GregorianCalendar dateNaissance;
    private String numTelFixe;
    private String numTelMobile;
    private String remarque;
    private String aSurveiller;
    private String conseils;
    private boolean donnerEtat;
    private boolean besoinAval;
    private boolean acharnementTherapeuthique;
    private String causeDecesPere;
    private String causeDecesMere;
    private double primeAnuelle;
    private Integer mutualite_id;

    public Patient(Integer patient_id, String numeroNational, String nom, String prenom, Integer nbEnfants,
                   GregorianCalendar dateNaissance, String numTelFixe, String numTelMobile, String remarque,
                   String aSurveiller, String conseils, boolean donnerEtat, boolean besoinAval,
                   boolean acharnementTherapeuthique, String causeDecesPere, String causeDecesMere,
                   double primeAnuelle, Integer mutualite_id) throws ChampsVideException, FormatNombreException, CodeInvalideException, CaracteresLimiteException{
        setPatient_id(patient_id);
        setNumeroNational(numeroNational);
        setNom(nom);
        setPrenom(prenom);
        setNbEnfants(nbEnfants);
        setDateNaissance(dateNaissance);
        setNumTelFixe(numTelFixe);
        setNumTelMobile(numTelMobile);
        setRemarque(remarque);
        setASurveiller(aSurveiller);
        setConseils(conseils);
        setDonnerEtat(donnerEtat);
        setBesoinAval(besoinAval);
        setAcharnementTherapeuthique(acharnementTherapeuthique);
        setCauseDecesPere(causeDecesPere);
        setCauseDecesMere(causeDecesMere);
        setPrimeAnuelle(primeAnuelle);
        setMutualite_id(mutualite_id);
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public void setNumeroNational(String numeroNational) throws ChampsVideException, FormatNombreException, CodeInvalideException{
        if(numeroNational.isEmpty()){
            throw new ChampsVideException("Numéro national");
        }
        if(!numeroNational.matches("[0-9]*")){
            throw new FormatNombreException("Numéro national");
        }
        if(numeroNational.length() != 11){
            throw new CodeInvalideException("Numéro national", "Veuillez entrer un numéro national composé de 11 chiffres uniquement");
        }
        this.numeroNational = numeroNational;
    }

    public void setNom(String nom) throws ChampsVideException, CaracteresLimiteException{
        if(nom.isEmpty()){
            throw new ChampsVideException("Nom");
        }
        if(nom.length() > 30){
            throw new CaracteresLimiteException("Nom");
        }
        this.nom = nom;
    }

    public void setPrenom(String prenom) throws ChampsVideException, CaracteresLimiteException{
        if(prenom.isEmpty()){
            throw new ChampsVideException("Prénom");
        }
        if(prenom.length() > 30){
            throw new CaracteresLimiteException("Prénom");
        }
        this.prenom = prenom;
    }

    public void setNbEnfants(Integer nbEnfants) {
        this.nbEnfants = nbEnfants;
    }

    public void setDateNaissance(GregorianCalendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setNumTelFixe(String numTelFixe) {
        this.numTelFixe = numTelFixe;
    }

    public void setNumTelMobile(String numTelMobile) {
        this.numTelMobile = numTelMobile;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public void setASurveiller(String aSurveiller) {
        this.aSurveiller = aSurveiller;
    }

    public void setConseils(String conseils) {
        this.conseils = conseils;
    }

    public void setDonnerEtat(boolean donnerEtat) {
        this.donnerEtat = donnerEtat;
    }

    public void setBesoinAval(boolean besoinAval) {
        this.besoinAval = besoinAval;
    }

    public void setAcharnementTherapeuthique(boolean acharnementTherapeuthique) {
        this.acharnementTherapeuthique = acharnementTherapeuthique;
    }

    public void setCauseDecesPere(String causeDecesPere) {
        this.causeDecesPere = causeDecesPere;
    }

    public void setCauseDecesMere(String causeDecesMere) {
        this.causeDecesMere = causeDecesMere;
    }

    public void setPrimeAnuelle(double primeAnuelle) {
        this.primeAnuelle = primeAnuelle;
    }

    public void setMutualite_id(Integer mutualite_id) {
        this.mutualite_id = mutualite_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public String getNumeroNational() {
        return numeroNational;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Integer getNbEnfants() {
        return nbEnfants;
    }

    public GregorianCalendar getDateNaissance() {
        return dateNaissance;
    }

    public String getNumTelFixe() {
        return numTelFixe;
    }

    public String getNumTelMobile() {
        return numTelMobile;
    }

    public String getRemarque() {
        return remarque;
    }

    public String getASurveiller() {
        return aSurveiller;
    }

    public String getConseils() {
        return conseils;
    }

    public boolean isDonnerEtat() {
        return donnerEtat;
    }

    public boolean isBesoinAval() {
        return besoinAval;
    }

    public boolean isAcharnementTherapeuthique() {
        return acharnementTherapeuthique;
    }

    public String getCauseDecesPere() {
        return causeDecesPere;
    }

    public String getCauseDecesMere() {
        return causeDecesMere;
    }

    public double getPrimeAnuelle() {
        return primeAnuelle;
    }

    public Integer getMutualite_id() {
        return mutualite_id;
    }
}
