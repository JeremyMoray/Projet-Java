package controllerPackage;

import java.util.*;
import businessPackage.*;
import exceptionPackage.*;
import modelPackage.*;

public class ApplicationController {

    private Manager manager;

    public ApplicationController(){
        setManager(new Manager());
    }

    public void closeConnection() throws AccesDBException{
        manager.closeConnection();
    }

    public void addSoignant(Soignant soignant) throws AccesDBException, ChampsExistantException {
        manager.addSoignant(soignant);
    }

    public Soignant getSoignant(String numNat, String motDePasse) throws AccesDBException, ConnexionException, ChampsVideException, CaracteresLimiteException, FormatNombreException, CodeInvalideException{
        return manager.getSoignant(numNat, motDePasse);
    }

    public Soignant getSoignant(Integer soignant_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException, FormatNombreException, CodeInvalideException {
        return manager.getSoignant(soignant_id);
    }

    public void updateSoignant(Soignant soignantModifié, Integer soignant_id) throws AccesDBException, ChampsExistantException {
        manager.updateSoignant(soignantModifié, soignant_id);
    }

    public void deleteSoignant(Integer soignant_id) throws AccesDBException {
        manager.deleteSoignant(soignant_id);
    }

    public void addMutualite(Mutualite mutualite) throws AccesDBException {
        manager.addMutualite(mutualite);
    }

    public ArrayList<Mutualite> getAllMutualites() throws AccesDBException, ChampsVideException, CaracteresLimiteException {
        return manager.getAllMutualites();
    }

    public Mutualite getMutualite(Integer mutualite_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException {
        return manager.getMutualite(mutualite_id);
    }

    public void deleteMutualite(Integer mutualite_id) throws AccesDBException {
        manager.deleteMutualite(mutualite_id);
    }

    public void updateMutualite(Mutualite mutualite) throws AccesDBException{
        manager.updateMutualite(mutualite);
    }

    public void addAllergie(Allergie allergie) throws AccesDBException {
        manager.addAllergie(allergie);
    }

    public ArrayList<Allergie> getAllAllergies() throws AccesDBException, ChampsVideException, CaracteresLimiteException {
        return manager.getAllAllergies();
    }

    public void deleteAllergie(Integer allergie_id) throws AccesDBException {
        manager.deleteAllergie(allergie_id);
    }

    public void updateAllergie(Allergie allergie) throws AccesDBException{
        manager.updateAllergie(allergie);
    }

    public void addMedicament(Medicament medicament) throws AccesDBException {
        manager.addMedicament(medicament);
    }

    public ArrayList<Medicament> getAllMedicaments() throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        return manager.getAllMedicaments();
    }

    public void deleteMedicament(Integer medicament_id) throws AccesDBException {
        manager.deleteMedicament(medicament_id);
    }

    public void updateMedicament(Medicament medicament) throws AccesDBException{
        manager.updateMedicament(medicament);
    }

    public void addPatient(Patient patient) throws AccesDBException {
        manager.addPatient(patient);
    }

    public ArrayList<Patient> getAllPatients() throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        return manager.getAllPatients();
    }

    public void setManager(Manager manager){
        this.manager = manager;
    }
}
