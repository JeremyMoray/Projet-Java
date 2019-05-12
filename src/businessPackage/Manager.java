package businessPackage;

import java.util.*;
import exceptionPackage.*;
import modelPackage.*;
import dataAccessPackage.*;

public class Manager {

    private DataAccess dao;

    public Manager() {
        setDao(new DBAccess()) ;
    }

    public void closeConnection() throws AccesDBException{
        dao.closeConnection();
    }

    public void addSoignant (Soignant soignant) throws AccesDBException, ChampsExistantException {
        dao.addSoignant(soignant);
    }

    public Soignant getSoignant(String numNat, String motDePasse) throws AccesDBException, ConnexionException, ChampsVideException, CaracteresLimiteException, FormatNombreException, CodeInvalideException{
        return dao.getSoignant(numNat, motDePasse);
    }

    public Soignant getSoignant(Integer soignant_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException, FormatNombreException, CodeInvalideException {
        return dao.getSoignant(soignant_id);
    }

    public void updateSoignant(Soignant soignantModifié, Integer soignant_id) throws AccesDBException, ChampsExistantException {
        dao.updateSoignant(soignantModifié, soignant_id);
    }

    public void deleteSoignant(Integer soignant_id) throws AccesDBException {
        dao.deleteSoignant(soignant_id);
    }

    public void addMutualite(Mutualite mutualite) throws AccesDBException {
        dao.addMutualite(mutualite);
    }

    public ArrayList<Mutualite> getAllMutualites( ) throws AccesDBException, ChampsVideException, CaracteresLimiteException {
        return dao.getAllMutualites( );
    }

    public Mutualite getMutualite(Integer mutualite_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException {
        return dao.getMutualite(mutualite_id);
    }

    public void deleteMutualite(Integer mutualite_id) throws AccesDBException {
        dao.deleteMutualite(mutualite_id);
    }

    public void updateMutualite(Mutualite mutualite) throws AccesDBException{
        dao.updateMutualite(mutualite);
    }

    public void addAllergie(Allergie allergie) throws AccesDBException {
        dao.addAllergie(allergie);
    }

    public ArrayList<Allergie> getAllAllergies() throws AccesDBException, ChampsVideException, CaracteresLimiteException{
        return dao.getAllAllergies( );
    }

    public void deleteAllergie(Integer allergie_id) throws AccesDBException {
        dao.deleteAllergie(allergie_id);
    }

    public void updateAllergie(Allergie allergie) throws AccesDBException{
        dao.updateAllergie(allergie);
    }

    public void addMedicament(Medicament medicament) throws AccesDBException {
        dao.addMedicament(medicament);
    }

    public ArrayList<Medicament> getAllMedicaments() throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        return dao.getAllMedicaments();
    }

    public void deleteMedicament(Integer medicament_id) throws AccesDBException {
        dao.deleteMedicament(medicament_id);
    }

    public void updateMedicament(Medicament medicament) throws AccesDBException{
        dao.updateMedicament(medicament);
    }

    public void addPatient(Patient patient) throws AccesDBException {
        dao.addPatient(patient);
    }

    public ArrayList<Patient> getAllPatients() throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        return dao.getAllPatients();
    }

    public void deletePatient(Integer patient_id) throws AccesDBException{
        dao.deletePatient(patient_id);
    }

    public void updatePatient(Patient patient) throws AccesDBException{
        dao.updatePatient(patient);
    }

    public void addConsultation(Consultation consultation) throws AccesDBException, ObjetExistantException{
        dao.addConsultation(consultation);
    }

    public void addSouffrance(Souffrance souffrance) throws AccesDBException, ObjetExistantException{
        dao.addSouffrance(souffrance);
    }

    public void setDao(DBAccess dao) {
        this.dao = dao;
    }
}