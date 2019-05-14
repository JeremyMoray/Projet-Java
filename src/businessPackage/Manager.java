package businessPackage;

import java.util.*;
import exceptionPackage.*;
import modelPackage.*;
import dataAccessPackage.*;

import javax.swing.*;

public class Manager {

    private DataAccess dao;
    private static HashMap<String, Integer> collectionMedicaments = new HashMap<String, Integer>();;

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

    public ArrayList<Medicament> getAllMedicamentsDates(Integer soignant_id, GregorianCalendar dateMin, GregorianCalendar dateMax) throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException{
        return dao.getAllMedicamentsDates(soignant_id, dateMin, dateMax);
    }

    public Medicament getMedicament(Integer medicament_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        return dao.getMedicament(medicament_id);
    }

    public void deleteMedicament(Integer medicament_id) throws AccesDBException {
        dao.deleteMedicament(medicament_id);
    }

    public void updateMedicament(Medicament medicament) throws AccesDBException{
        dao.updateMedicament(medicament);
    }

    public void addPatient(Patient patient) throws AccesDBException, FormatNombreException {
        if(patient.getNbEnfants() > 50){
            throw new FormatNombreException("Nombre d'enfants");
        }
        if(patient.getPrimeAnuelle() > 10000){
            throw new FormatNombreException("Prime anuelle");
        }
        dao.addPatient(patient);
    }

    public ArrayList<Patient> getAllPatients() throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        return dao.getAllPatients();
    }

    public ArrayList<Patient> getAllPatientsConsultes(Integer soignant_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        return dao.getAllPatientsConsultes(soignant_id);
    }

    public ArrayList<Patient> getAllPrimesPatient(Integer soignant_id, GregorianCalendar dateConsultation) throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        return dao.getAllPrimesPatient(soignant_id, dateConsultation);
    }

    public Patient getPatient(Integer patient_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException{
        return dao.getPatient(patient_id);
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

    public void addTraitement(Traitement traitement) throws AccesDBException, ObjetExistantException, AllergieAMedicamentException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException{
        if(!collectionMedicaments.containsKey(getMedicament(traitement.getMedicament_id()).getNom())){
            collectionMedicaments.put(getMedicament(traitement.getMedicament_id()).getNom(), 1);
        }
        else{
            collectionMedicaments.replace(getMedicament(traitement.getMedicament_id()).getNom(), collectionMedicaments.get(getMedicament(traitement.getMedicament_id()).getNom())+1);
        }
        dao.addTraitement(traitement);
    }

    public void addReaction(Reaction reaction) throws AccesDBException, ObjetExistantException{
        dao.addReaction(reaction);
    }

    public void addProche(Proche proche) throws AccesDBException, ObjetExistantException{
        dao.addProche(proche);
    }

    public ArrayList<Proche> getAllProches() throws AccesDBException, ChampsVideException, CaracteresLimiteException{
        return dao.getAllProches();
    }

    public ArrayList<Proche> getAllProchesUrgence(Integer patient_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException{
        return dao.getAllProchesUrgence(patient_id);
    }

    public void deleteProche(Integer proche_id) throws AccesDBException{
        dao.deleteProche(proche_id);
    }

    public void afficherTopMedicaments(){

        String listeMedicaments = "";
        int i = 0;
        HashMap listeTriee = sortHashMapByValues(collectionMedicaments);
        Iterator<Map.Entry<String, Integer>> iterator = listeTriee.entrySet().iterator();
        while (iterator.hasNext() && i<5) {
            Map.Entry<String, Integer> element = iterator.next();

            listeMedicaments +=  element.getKey() + " utilisé (" + element.getValue() + ") fois \n";
        }

        JOptionPane.showMessageDialog(null, "Le top 5 des médicaments attribué cette session est : \n" + listeMedicaments, "Top 5 médicaments", JOptionPane.INFORMATION_MESSAGE);
    }

    public LinkedHashMap<String, Integer> sortHashMapByValues(
            HashMap<String, Integer> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues, Collections.reverseOrder());
        Collections.sort(mapKeys, Collections.reverseOrder());

        LinkedHashMap<String, Integer> sortedMap =
                new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp1 = passedMap.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    public void setDao(DBAccess dao) {
        this.dao = dao;
    }
}