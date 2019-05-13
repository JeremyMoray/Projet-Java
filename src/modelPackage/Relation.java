package modelPackage;

public class Relation {

    private Integer proche_id;
    private Integer patient_id;

    public Relation(Integer proche_id, Integer patient_id) {
        this.proche_id = proche_id;
        this.patient_id = patient_id;
    }

    public void setProche_id(Integer proche_id) {
        this.proche_id = proche_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public Integer getProche_id() {
        return proche_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }
}
