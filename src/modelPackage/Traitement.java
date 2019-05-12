package modelPackage;

import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;

import java.util.GregorianCalendar;

public class Traitement {

    private GregorianCalendar dateDeDebut;
    private GregorianCalendar dateDeFin;
    private String frequence;
    private Integer patient_id;
    private Integer soignant_id;
    private Integer medicament_id;

    public Traitement(GregorianCalendar dateDeDebut, GregorianCalendar dateDeFin, String frequence, Integer patient_id, Integer soignant_id, Integer medicament_id) throws ChampsVideException, CaracteresLimiteException{
        setDateDeDebut(dateDeDebut);
        setDateDeFin(dateDeFin);
        setFrequence(frequence);
        setPatient_id(patient_id);
        setSoignant_id(soignant_id);
        setMedicament_id(medicament_id);
    }

    public void setDateDeDebut(GregorianCalendar dateDeDebut) throws ChampsVideException {
        if(dateDeDebut == null){
            throw new ChampsVideException("Date de début");
        }
        this.dateDeDebut = dateDeDebut;
    }

    public void setDateDeFin(GregorianCalendar dateDeFin) throws ChampsVideException  {
        if(dateDeFin == null){
            throw new ChampsVideException("Date de fin");
        }
        this.dateDeFin = dateDeFin;
    }

    public void setFrequence(String frequence) throws CaracteresLimiteException{
        if(frequence != null){
            if(frequence.length() > 100){
                throw new CaracteresLimiteException("Frequence");
            }
        }
        this.frequence = frequence;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public void setSoignant_id(Integer soignant_id) {
        this.soignant_id = soignant_id;
    }

    public void setMedicament_id(Integer medicament_id) {
        this.medicament_id = medicament_id;
    }

    public GregorianCalendar getDateDeDebut(){
        return dateDeDebut;
    }

    public GregorianCalendar getDateDeFin(){
        return dateDeFin;
    }

    public String getFrequence(){
        return frequence;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public Integer getSoignant_id() {
        return soignant_id;
    }

    public Integer getMedicament_id() {
        return medicament_id;
    }
}
