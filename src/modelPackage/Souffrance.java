package modelPackage;

public class Souffrance {

    private Integer patient_id;
    private Integer allergie_id;

    public Souffrance(Integer patient_id, Integer allergie_id){
        setPatient_id(patient_id);
        setAllergie_id(allergie_id);
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public void setAllergie_id(Integer allergie_id) {
        this.allergie_id = allergie_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public Integer getAllergie_id() {
        return allergie_id;
    }
}
