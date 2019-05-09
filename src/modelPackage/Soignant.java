package modelPackage;

public class Soignant {

    private Integer soignant_id;
    private String numeroNational;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String numTel;
    private String numeroINAMI;
    private String specialite;

    public Soignant(Integer soignant_id, String numeroNational, String motDePasse, String nom, String prenom, String numTel, String numeroINAMI, String specialite) {
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

    public void setNumeroNational(String numeroNational){
            this.numeroNational = numeroNational;
    }

    public void setMotDePasse(String motDePasse){
            this.motDePasse = motDePasse;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public void setNumTel(String numTel){
        this.numTel = numTel;
    }

    public void setNumeroINAMI(String numeroINAMI){
        this.numeroINAMI = numeroINAMI;
    }

    public void setSpecialite(String specialite){
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
