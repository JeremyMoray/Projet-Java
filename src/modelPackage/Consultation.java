package modelPackage;

import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;

import java.util.GregorianCalendar;

public class Consultation {
    private Integer soignant_id;
    private Integer patient_id;
    private String observations;
    private GregorianCalendar dateConsultation;

    public Consultation(Integer soignant_id, Integer patient_id, String observations, GregorianCalendar dateConsultation) throws CaracteresLimiteException, ChampsVideException {
        setSoignant_id(soignant_id);
        setPatient_id(patient_id);
        setObservations(observations);
        setDateConsultation(dateConsultation);
    }

    public Integer getSoignant_id() {
        return soignant_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public String getObservations() {
        return observations;
    }

    public GregorianCalendar getDateConsultation() {
        return dateConsultation;
    }

    public void setSoignant_id(Integer soignant_id) {
        this.soignant_id = soignant_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public void setObservations(String observations) throws CaracteresLimiteException{
        if(observations != null){
            if(observations.length() > 250){
                throw new CaracteresLimiteException("Observations");
            }
        }
        this.observations = observations;
    }

    public void setDateConsultation(GregorianCalendar dateConsultation) throws ChampsVideException {
        if(dateConsultation == null){
            throw new ChampsVideException("Date de consultation");
        }
        this.dateConsultation = dateConsultation;
    }
}
