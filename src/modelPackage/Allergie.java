package modelPackage;

import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;

public class Allergie {

    private Integer allergie_id;
    private String libelle;
    private String symptome;
    private String conditionnement;

    public Allergie(Integer allergie_id, String libelle, String symptome, String conditionnement) throws ChampsVideException, CaracteresLimiteException {
        setId(allergie_id);
        setLibelle(libelle);
        setSymptome(symptome);
        setConditionnement(conditionnement);
    }

    public void setId(Integer allergie_id){
        this.allergie_id = allergie_id;
    }

    public void setLibelle(String libelle) throws ChampsVideException, CaracteresLimiteException {
        if(libelle == null){
            throw new ChampsVideException("Libellé");
        }
        if(libelle.length() > 50){
            throw new CaracteresLimiteException("Libellé");
        }
        this.libelle = libelle;
    }

    public void setSymptome(String symptome) throws ChampsVideException, CaracteresLimiteException {
        if(symptome == null){
            throw new ChampsVideException("Symptôme");
        }
        if(libelle.length() > 250){
            throw new CaracteresLimiteException("Symptôme");
        }
        this.symptome = symptome;
    }

    public void setConditionnement(String conditionnement) throws ChampsVideException, CaracteresLimiteException {
        if(conditionnement == null){
            throw new ChampsVideException("Conditionnement");
        }
        if(libelle.length() > 250){
            throw new CaracteresLimiteException("Conditionnement");
        }
        this.conditionnement = conditionnement;
    }

    public Integer getId(){
        return allergie_id;
    }

    public String getLibelle(){
        return libelle;
    }

    public String getSymptome() {
        return symptome;
    }

    public String getConditionnement() {
        return conditionnement;
    }
}
