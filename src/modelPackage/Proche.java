package modelPackage;

public class Proche {

    private Integer proche_id;
    private String nom;
    private String prenom;
    private String numTel;
    private String remarques;
    private Boolean aAccesInfosMedicales;
    private Boolean aAppellerSiUrgence;

    public Proche(Integer proche_id, String nom, String prenom, String numTel, String remarques, Boolean aAccesInfosMedicales, Boolean aAppellerSiUrgence) {
        setProche_id(proche_id);
        setNom(nom);
        setPrenom(prenom);
        setNumTel(numTel);
        setRemarques(remarques);
        setAAccesInfosMedicales(aAccesInfosMedicales);
        setAAppellerSiUrgence(aAppellerSiUrgence);
    }

    public void setProche_id(Integer proche_id) {
        this.proche_id = proche_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public void setAAccesInfosMedicales(Boolean aAccesInfosMedicales) {
        this.aAccesInfosMedicales = aAccesInfosMedicales;
    }

    public void setAAppellerSiUrgence(Boolean aAppellerSiUrgence) {
        this.aAppellerSiUrgence = aAppellerSiUrgence;
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

    public Boolean getaAccesInfosMedicales() {
        return aAccesInfosMedicales;
    }

    public Boolean getaAppellerSiUrgence() {
        return aAppellerSiUrgence;
    }
}
