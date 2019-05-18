package modelPackage;

import java.util.GregorianCalendar;

public class InfosConsultation {
    private GregorianCalendar dateConsultation;
    private String nomPatient;
    private String prenomPatient;
    private GregorianCalendar dateNaissance;
    private String libelleMutualite;

    public InfosConsultation(GregorianCalendar dateConsultation, String nomPatient, String prenomPatient, GregorianCalendar dateNaissance, String libelleMutualite) {
        setDateConsultation(dateConsultation);
        setNomPatient(nomPatient);
        setPrenomPatient(prenomPatient);
        setDateNaissance(dateNaissance);
        setLibelleMutualite(libelleMutualite);
    }

    public GregorianCalendar getDateConsultation() {
        return dateConsultation;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public String getPrenomPatient() {
        return prenomPatient;
    }

    public GregorianCalendar getDateNaissance() {
        return dateNaissance;
    }

    public String getLibelle() {
        return libelleMutualite;
    }

    public void setDateConsultation(GregorianCalendar dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public void setPrenomPatient(String prenomPatient) {
        this.prenomPatient = prenomPatient;
    }

    public void setDateNaissance(GregorianCalendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setLibelleMutualite(String libelleMutualite) {
        this.libelleMutualite = libelleMutualite;
    }
}

