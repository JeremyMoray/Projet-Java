package modelPackage;

import exceptionPackage.*;

import java.util.Calendar;
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
    private boolean acharnementTherapeutique;
    private String causeDecesPere;
    private String causeDecesMere;
    private double primeAnuelle;
    private Integer mutualite_id;

    public Patient(Integer patient_id, String numeroNational, String nom, String prenom, Integer nbEnfants,
                   GregorianCalendar dateNaissance, String numTelFixe, String numTelMobile, String remarque,
                   String aSurveiller, String conseils, boolean donnerEtat, boolean besoinAval,
                   boolean acharnementTherapeutique, String causeDecesPere, String causeDecesMere,
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
        setAcharnementTherapeutique(acharnementTherapeutique);
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

    public void setNbEnfants(Integer nbEnfants) throws ChampsVideException, FormatNombreException{
        if(nbEnfants == null){
            throw new ChampsVideException("Nb. d'enfants");
        }
        try{
            if(nbEnfants < 0){
                throw new FormatNombreException("Nb. d'enfants");
            }
        }
        catch(NumberFormatException exception){
            throw new FormatNombreException("Nb. d'enfants");
        }
        this.nbEnfants = nbEnfants;
    }

    public void setDateNaissance(GregorianCalendar dateNaissance) throws ChampsVideException{
        if(dateNaissance == null){
            throw new ChampsVideException("Date de naissance");
        }
        this.dateNaissance = dateNaissance;
    }

    public void setNumTelFixe(String numTelFixe) throws CaracteresLimiteException {
        if(remarque != null){
            if(numTelFixe.length() > 20){
                throw new CaracteresLimiteException("Numéro tel. fixe");
            }
        }
        this.numTelFixe = numTelFixe;
    }

    public void setNumTelMobile(String numTelMobile) throws CaracteresLimiteException {
        if(remarque != null){
            if(numTelMobile.length() > 20){
                throw new CaracteresLimiteException("Numéro tel. mobile");
            }
        }
        this.numTelMobile = numTelMobile;
    }

    public void setRemarque(String remarque) throws CaracteresLimiteException {
        if(remarque != null){
            if(remarque.length() > 250){
                throw new CaracteresLimiteException("Remarque");
            }
        }
        this.remarque = remarque;
    }

    public void setASurveiller(String aSurveiller) throws CaracteresLimiteException {
        if(aSurveiller != null){
            if(aSurveiller.length() > 250){
                throw new CaracteresLimiteException("A surveiller");
            }
        }
        this.aSurveiller = aSurveiller;
    }

    public void setConseils(String conseils) throws CaracteresLimiteException {
        if(conseils != null) {
            if (conseils.length() > 250) {
                throw new CaracteresLimiteException("Conseils");
            }
        }
        this.conseils = conseils;
    }

    public void setDonnerEtat(boolean donnerEtat) {
        this.donnerEtat = donnerEtat;
    }

    public void setBesoinAval(boolean besoinAval) {
        this.besoinAval = besoinAval;
    }

    public void setAcharnementTherapeutique(boolean acharnementTherapeutique) {
        this.acharnementTherapeutique = acharnementTherapeutique;
    }

    public void setCauseDecesPere(String causeDecesPere) throws CaracteresLimiteException{
        if(causeDecesPere != null) {
            if (causeDecesPere.length() > 250) {
                throw new CaracteresLimiteException("Cause décès père");
            }
        }
        this.causeDecesPere = causeDecesPere;
    }

    public void setCauseDecesMere(String causeDecesMere) throws CaracteresLimiteException{
        if(causeDecesMere != null) {
            if (causeDecesMere.length() > 250) {
                throw new CaracteresLimiteException("Cause décès mère");
            }
        }
        this.causeDecesMere = causeDecesMere;
    }

    public void setPrimeAnuelle(double primeAnuelle) throws FormatNombreException{
        try{
            if(primeAnuelle < 0){
                throw new FormatNombreException("Prime anuelle");
            }
        }
        catch(NumberFormatException exception){
            throw new FormatNombreException("Prime anuelle");
        }
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

    public boolean isAcharnementTherapeutique() {
        return acharnementTherapeutique;
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
