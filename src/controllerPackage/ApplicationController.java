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

    public ArrayList<Soignant> getAllSoignants() throws AccesDBException, ChampsVideException, CaracteresLimiteException, FormatNombreException, CodeInvalideException {
        return manager.getAllSoignants();
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

    public ArrayList<Medicament> getAllMedicamentsDates(Integer soignant_id, GregorianCalendar dateMin, GregorianCalendar dateMax) throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException{
        return manager.getAllMedicamentsDates(soignant_id, dateMin, dateMax);
    }

    public void deleteMedicament(Integer medicament_id) throws AccesDBException {
        manager.deleteMedicament(medicament_id);
    }

    public void updateMedicament(Medicament medicament) throws AccesDBException{
        manager.updateMedicament(medicament);
    }

    public void addPatient(Patient patient) throws AccesDBException, FormatNombreException {
        manager.addPatient(patient);
    }

    public ArrayList<Patient> getAllPatients() throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        return manager.getAllPatients();
    }

    public Patient getPatient(Integer patient_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException{
        return manager.getPatient(patient_id);
    }

    public void deletePatient(Integer patient_id) throws AccesDBException{
        manager.deletePatient(patient_id);
    }

    public void updatePatient(Patient patient) throws AccesDBException{
        manager.updatePatient(patient);
    }

    public void addConsultation(Consultation consultation) throws AccesDBException, ObjetExistantException{
        manager.addConsultation(consultation);
    }

    public ArrayList<InfosConsultation> getAllConsultations(Integer soignant_id) throws AccesDBException {
        return manager.getAllConsultations(soignant_id);
    }

    public void addSouffrance(Souffrance souffrance) throws AccesDBException, ObjetExistantException{
        manager.addSouffrance(souffrance);
    }

    public void addTraitement(Traitement traitement) throws AccesDBException, ObjetExistantException, AllergieAMedicamentException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException{
        manager.addTraitement(traitement);
    }

    public ArrayList<ArrayList<String>> getAllTraitements(Integer mutualite_id) throws AccesDBException{
        return manager.getAllTraitements(mutualite_id);
    }

    public void addReaction(Reaction reaction) throws AccesDBException, ObjetExistantException{
        manager.addReaction(reaction);
    }

    public void addProche(Proche proche) throws AccesDBException, ObjetExistantException{
        manager.addProche(proche);
    }

    public ArrayList<Proche> getAllProches() throws AccesDBException, ChampsVideException, CaracteresLimiteException{
        return manager.getAllProches();
    }

    public void deleteProche(Integer proche_id) throws AccesDBException{
        manager.deleteProche(proche_id);
    }

    public void afficherTopMedicaments(){
        manager.afficherTopMedicaments();
    }

    public Double getTotalPrimesAnuelles() throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        return manager.getTotalPrimesAnuelles();
    }

    public void setManager(Manager manager){
        this.manager = manager;
    }
}
