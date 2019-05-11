package dataAccessPackage;

import java.util.*;
import exceptionPackage.*;
import modelPackage.*;

public interface DataAccess {

    void closeConnection() throws AccesDBException;
    void addSoignant(Soignant soignant) throws AccesDBException, ChampsExistantException;
    Soignant getSoignant(String numNat, String motDePasse) throws AccesDBException, ConnexionException, ChampsVideException, CaracteresLimiteException, FormatNombreException, CodeInvalideException;
    Soignant getSoignant(Integer soignant_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException, FormatNombreException, CodeInvalideException;
    void updateSoignant(Soignant soignant, Integer soignant_id) throws AccesDBException, ChampsExistantException;
    void deleteSoignant(Integer soignant_id) throws AccesDBException;

    void addMutualite(Mutualite mutualite) throws AccesDBException;
    ArrayList<Mutualite> getAllMutualites() throws AccesDBException, ChampsVideException, CaracteresLimiteException;
    Mutualite getMutualite(Integer mutualite_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException;
    void deleteMutualite(Integer mutualite_id) throws AccesDBException;
    void updateMutualite(Mutualite mutualite) throws AccesDBException;

    void addAllergie(Allergie allergie) throws AccesDBException;
    ArrayList<Allergie> getAllAllergies() throws AccesDBException, ChampsVideException, CaracteresLimiteException;
    void deleteAllergie(Integer allergie_id) throws AccesDBException;
    void updateAllergie(Allergie allergie) throws AccesDBException;

    void addMedicament(Medicament medicament) throws AccesDBException;
    ArrayList<Medicament> getAllMedicaments() throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException;
    void deleteMedicament(Integer medicament_id) throws AccesDBException;
    void updateMedicament(Medicament medicament) throws AccesDBException;

    void addPatient(Patient patient) throws AccesDBException;
    ArrayList<Patient> getAllPatients() throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException;
    void deletePatient(Integer patient_id) throws AccesDBException;
    void updatePatient(Patient patient) throws AccesDBException;
}