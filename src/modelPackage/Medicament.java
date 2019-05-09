package modelPackage;

import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;
import exceptionPackage.CodeInvalideException;
import exceptionPackage.FormatNombreException;

public class Medicament {
    private Integer medicament_id;
    private String codeCNK;
    private String nom;
    private String firme;
    private String principeActif;
    private String codeATC;
    private String caracteristique;
    private double tauxRemboursement;

    public Medicament(Integer medicament_id, String codeCNK, String nom, String firme, String principeActif, String codeATC, String caracteristique, double tauxRemboursement) throws ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        setId(medicament_id);
        setCodeCNK(codeCNK);
        setNom(nom);
        setFirme(firme);
        setPrincipeActif(principeActif);
        setCodeATC(codeATC);
        setCaracteristique(caracteristique);
        setTauxRemboursement(tauxRemboursement);
    }

    public void setId(Integer medicament_id) {
        this.medicament_id = medicament_id;
    }

    public void setCodeCNK(String codeCNK) throws ChampsVideException, CodeInvalideException, FormatNombreException {
        if(codeCNK.isEmpty()){
            throw new ChampsVideException("Code CNK");
        }
        if(codeCNK.length() != 7) {
            throw new CodeInvalideException("Code CNK", "Veuillez entrer un code CNK composé de 7 chiffres uniquement");
        }
        if(!codeCNK.matches("[0-9]*")){
            throw new FormatNombreException("Code CNK");
        }
        this.codeCNK = codeCNK;
    }

    public void setNom(String nom) throws ChampsVideException, CaracteresLimiteException {
        if(nom.isEmpty()){
            throw new ChampsVideException("Nom");
        }
        if(nom.length() > 50){
            throw new CaracteresLimiteException("Nom");
        }
        this.nom = nom;
    }

    public void setFirme(String firme) throws ChampsVideException, CaracteresLimiteException {
        if(firme.isEmpty()){
            throw new ChampsVideException("Firme");
        }
        if(firme.length() > 50){
            throw new CaracteresLimiteException("Firme");
        }
        this.firme = firme;
    }

    public void setPrincipeActif(String principeActif) throws ChampsVideException, CaracteresLimiteException {
        if(principeActif.isEmpty()){
            throw new ChampsVideException("Principe actif");
        }
        if(principeActif.length() > 50){
            throw new CaracteresLimiteException("Principe actif");
        }
        this.principeActif = principeActif;
    }

    public void setCodeATC(String codeATC) throws ChampsVideException, CodeInvalideException {
        if(codeATC.isEmpty()){
            throw new ChampsVideException("Code ATC");
        }
        if(codeATC.length() != 8){
            throw new CodeInvalideException("Code ATC", "Veuillez entrer un code ATC composé de 8 caractères uniquement");
        }
        this.codeATC = codeATC;
    }

    public void setCaracteristique(String caracteristique) throws ChampsVideException, CaracteresLimiteException {
        if(caracteristique.isEmpty()){
            throw new ChampsVideException("Caractéristique");
        }
        if(caracteristique.length() > 250){
            throw new CaracteresLimiteException("Caractéristique");
        }
        this.caracteristique = caracteristique;
    }

    public void setTauxRemboursement(double tauxRemboursement) throws FormatNombreException {
        try{
            if(tauxRemboursement < 0||tauxRemboursement > 100){
                throw new FormatNombreException("Taux de remboursement");
            }
        }
        catch(NumberFormatException exception){
            throw new FormatNombreException("Taux de remboursement");
        }
        this.tauxRemboursement = tauxRemboursement;
    }

    public Integer getId() {
        return medicament_id;
    }

    public String getCodeCNK() {
        return codeCNK;
    }

    public String getNom() {
        return nom;
    }

    public String getFirme() {
        return firme;
    }

    public String getPrincipeActif() {
        return principeActif;
    }

    public String getCodeATC() {
        return codeATC;
    }

    public String getCaracteristique() {
        return caracteristique;
    }

    public double getTauxRemboursement() {
        return tauxRemboursement;
    }
}
