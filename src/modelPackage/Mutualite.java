package modelPackage;

import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;

public class Mutualite {

    private Integer mutualite_id;
    private String libelle;
    private String affiliationPolitique;
    private String diminutif;

    public Mutualite(Integer mutualite_id, String libelle, String affiliationPolitique, String diminutif) throws ChampsVideException, CaracteresLimiteException {
        setId(mutualite_id);
        setLibelle(libelle);
        setAffiliationPolitique(affiliationPolitique);
        setDiminutif(diminutif);
    }

    public void setId(Integer mutualite_id){
        this.mutualite_id = mutualite_id;
    }

    public void setLibelle(String libelle) throws ChampsVideException, CaracteresLimiteException {
        if(libelle.isEmpty()){
            throw new ChampsVideException("Libellé");
        }
        if(libelle.length() > 50){
            throw new CaracteresLimiteException("Libellé");
        }
        this.libelle = libelle;
    }

    public void setAffiliationPolitique(String affiliationPolitique) throws ChampsVideException, CaracteresLimiteException {
        if(affiliationPolitique.isEmpty()){
            throw new ChampsVideException("Affiliation politique");
        }
        if(affiliationPolitique.length() > 50){
            throw new CaracteresLimiteException("Affiliation politique");
        }
        this.affiliationPolitique = affiliationPolitique;
    }

    public void setDiminutif(String diminutif) throws ChampsVideException, CaracteresLimiteException {
        if(diminutif.isEmpty()){
            throw new ChampsVideException("Diminutif");
        }
        if(diminutif.length() > 50){
            throw new CaracteresLimiteException("Diminutif");
        }
        this.diminutif = diminutif;
    }

    public Integer getId(){
        return mutualite_id;
    }

    public String getLibelle(){
        return libelle;
    }

    public String getAffiliationPolitique() {
        return affiliationPolitique;
    }

    public String getDiminutif() {
        return diminutif;
    }
}
