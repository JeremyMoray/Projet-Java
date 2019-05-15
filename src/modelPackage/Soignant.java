package modelPackage;

import exceptionPackage.ChampsVideException;
import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.FormatNombreException;
import exceptionPackage.CodeInvalideException;

public class Soignant {

    private Integer soignant_id;
    private String numeroNational;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String numTel;
    private String numeroINAMI;
    private String specialite;

    public Soignant(Integer soignant_id, String numeroNational, String motDePasse, String nom, String prenom, String numTel, String numeroINAMI, String specialite) throws ChampsVideException, CaracteresLimiteException, FormatNombreException, CodeInvalideException{
        setId(soignant_id);
        setNumeroNational(numeroNational);
        setMotDePasse(motDePasse);
        setNom(nom);
        setPrenom(prenom);
        setNumTel(numTel);
        setNumeroINAMI(numeroINAMI);
        setSpecialite(specialite);
    }

    public void setId(Integer soignant_id){
        this.soignant_id = soignant_id;
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

    public void setMotDePasse(String motDePasse) throws ChampsVideException, CaracteresLimiteException{
        if(motDePasse.isEmpty()){
            throw new ChampsVideException("Mot de passe");
        }
        if(motDePasse.length() > 128){
            throw new CaracteresLimiteException("Mot de passe");
        }
        this.motDePasse = motDePasse;
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

    public void setNumTel(String numTel) throws ChampsVideException, CaracteresLimiteException{
        if(numTel.isEmpty()){
            throw new ChampsVideException("Numéro de téléphone");
        }
        if(numTel.length() > 20){
            throw new CaracteresLimiteException("Numéro de téléphone");
        }
        this.numTel = numTel;
    }

    public void setNumeroINAMI(String numeroINAMI) throws FormatNombreException, CodeInvalideException{
        if(numeroINAMI != null){
            if(!numeroINAMI.matches("[0-9]+")){
                throw new FormatNombreException("Numéro INAMI");
            }
            if(numeroINAMI.length() != 11){
                throw new CodeInvalideException("Numéro INAMI", "Veuillez entrer un numéro INAMI composé de 11 chiffres uniquement");
            }
        }
        this.numeroINAMI = numeroINAMI;
    }

    public void setSpecialite(String specialite) throws ChampsVideException, CaracteresLimiteException{
        if(specialite.isEmpty()){
            throw new ChampsVideException("Spécialité");
        }
        if(specialite.length() > 50){
            throw new CaracteresLimiteException("Spécialité");
        }
        this.specialite = specialite;
    }

    public Integer getId(){
        return soignant_id;
    }

    public String getNumeroNational(){
        return numeroNational;
    }

    public String getMotDePasse() {
        return motDePasse;
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

    public String getNumeroINAMI() {
        return numeroINAMI;
    }

    public String getSpecialite() {
        return specialite;
    }
}
