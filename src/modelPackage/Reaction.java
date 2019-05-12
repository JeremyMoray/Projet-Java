package modelPackage;

public class Reaction {

    private Integer allergie_id;
    private Integer medicament_id;

    public Reaction(Integer allergie_id, Integer medicament_id){
        setAllergie_id(allergie_id);
        setMedicament_id(medicament_id);
    }

    public void setAllergie_id(Integer allergie_id) {
        this.allergie_id = allergie_id;
    }

    public void setMedicament_id(Integer medicament_id) {
        this.medicament_id = medicament_id;
    }

    public Integer getAllergie_id() {
        return allergie_id;
    }

    public Integer getMedicament_id() {
        return medicament_id;
    }
}
