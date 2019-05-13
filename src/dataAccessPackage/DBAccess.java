package dataAccessPackage;

import java.util.*;
import exceptionPackage.*;
import modelPackage.*;
import java.sql.*;

public class DBAccess implements DataAccess{

    public void closeConnection() throws AccesDBException{
        try{
            SingletonConnection.close();
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void addSoignant(Soignant soignant) throws AccesDBException, ChampsExistantException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "select numeroNational from soignant where numeroNational = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, soignant.getNumeroNational());

            ResultSet data = statement.executeQuery();

            if (data.next()) {
                throw new ChampsExistantException("Numéro national");
            }

            String sql2 = "INSERT INTO Soignant values(?, ?, ?, ?, ?, ?, ?, ?);";
            statement = connection.prepareStatement(sql2);

            statement.setNull(1, Types.INTEGER);
            statement.setString(2, soignant.getNumeroNational());
            statement.setString(3, soignant.getMotDePasse());
            statement.setString(4, soignant.getNom());
            statement.setString(5, soignant.getPrenom());
            statement.setString(6, soignant.getNumTel());
            if(soignant.getNumeroINAMI() == null){
                statement.setNull(7, Types.VARCHAR);
            }
            else{
                statement.setString(7, soignant.getNumeroINAMI());
            }
            statement.setString(8, soignant.getSpecialite());

            statement.executeUpdate();
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public Soignant getSoignant(String numNat, String motDePasse) throws AccesDBException, ConnexionException, ChampsVideException, CaracteresLimiteException, FormatNombreException, CodeInvalideException{
        try{
            Connection connection = SingletonConnection.getInstance();
            String sql = "select * from soignant where numeroNational = ? and motDePasse = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, numNat);
            statement.setString(2, motDePasse);

            ResultSet data = statement.executeQuery();

            if(!data.next()){
                throw new ConnexionException();
            }

            Soignant utilisateur = new Soignant(
                    data.getInt(1),
                    data.getString(2),
                    data.getString(3),
                    data.getString(4),
                    data.getString(5),
                    data.getString(6),
                    data.getString(7),
                    data.getString(8));
            return utilisateur;
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public Soignant getSoignant(Integer soignant_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException, FormatNombreException, CodeInvalideException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String sql = "select * from soignant where soignant_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, soignant_id);

            ResultSet data = statement.executeQuery();

            data.next();

            Soignant utilisateur = new Soignant(
                    data.getInt(1),
                    data.getString(2),
                    data.getString(3),
                    data.getString(4),
                    data.getString(5),
                    data.getString(6),
                    data.getString(7),
                    data.getString(8));
            return utilisateur;
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void updateSoignant (Soignant soignantModifié, Integer soignant_id) throws AccesDBException, ChampsExistantException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "select * from soignant where soignant_id != ? and numeroNational = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, soignant_id);
            statement.setString(2, soignantModifié.getNumeroNational());

            ResultSet data = statement.executeQuery();

            if (data.next()) {
                throw new ChampsExistantException("Numéro national");
            }

            String sql2 = "UPDATE Soignant set numeroNational = ?, motDePasse = ?, nom = ?, prenom = ?, numTel = ?, numeroINAMI = ?, specialite = ? where soignant_id = ?;";
            statement = connection.prepareStatement(sql2);

            statement.setString(1, soignantModifié.getNumeroNational());
            statement.setString(2, soignantModifié.getMotDePasse());
            statement.setString(3, soignantModifié.getNom());
            statement.setString(4, soignantModifié.getPrenom());
            statement.setString(5, soignantModifié.getNumTel());
            if(soignantModifié.getNumeroINAMI() == null){
                statement.setNull(6, Types.VARCHAR);
            }
            else{
                statement.setString(6, soignantModifié.getNumeroINAMI());
            }
            statement.setString(7, soignantModifié.getSpecialite());
            statement.setInt(8, soignant_id);

            statement.executeUpdate();
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void deleteSoignant(Integer soignant_id) throws AccesDBException {
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "delete from consultation where soignant_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, soignant_id);

            statement.executeUpdate();

            String sql2 = "delete from traitement where soignant_id = ?;";

            statement = connection.prepareStatement(sql2);

            statement.setInt(1, soignant_id);

            statement.executeUpdate();

            String sql3 = "delete from soignant where soignant_id = ?;";

            statement = connection.prepareStatement(sql3);

            statement.setInt(1, soignant_id);

            statement.executeUpdate();
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void addMutualite(Mutualite mutualite) throws AccesDBException {
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "INSERT INTO Mutualite values(?, ?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setNull(1, Types.INTEGER);
            statement.setString(2, mutualite.getLibelle());
            statement.setString(3, mutualite.getAffiliationPolitique());
            statement.setString(4, mutualite.getDiminutif());

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public ArrayList<Mutualite> getAllMutualites() throws AccesDBException, ChampsVideException, CaracteresLimiteException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String sql = "select * from mutualite;";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet data = statement.executeQuery();

            ArrayList<Mutualite> mutualites = new ArrayList<>();

            while(data.next()) {

                Mutualite mutualite = new Mutualite(
                        data.getInt(1),
                        data.getString(2),
                        data.getString(3),
                        data.getString(4)
                );
                mutualites.add(mutualite);
            }

            return mutualites;
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public Mutualite getMutualite(Integer mutualite_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String sql = "select * from mutualite where mutualite_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mutualite_id);

            ResultSet data = statement.executeQuery();

            data.next();

            Mutualite mutualite = new Mutualite(
                    data.getInt(1),
                    data.getString(2),
                    data.getString(3),
                    data.getString(4)
                );

            return mutualite;
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void deleteMutualite(Integer mutualite_id) throws AccesDBException {
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "delete from mutualite where mutualite_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, mutualite_id);

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void updateMutualite(Mutualite mutualite) throws AccesDBException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "update mutualite set libelle = ?, affiliationPolitique = ?, diminutif = ? where mutualite_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, mutualite.getLibelle());
            statement.setString(2, mutualite.getAffiliationPolitique());
            statement.setString(3, mutualite.getDiminutif());
            statement.setInt(4, mutualite.getId());

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void addAllergie(Allergie allergie) throws AccesDBException {
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "INSERT INTO Allergie values(?, ?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setNull(1, Types.INTEGER);
            statement.setString(2, allergie.getLibelle());
            statement.setString(3, allergie.getSymptome());
            statement.setString(4, allergie.getConditionnement());

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public ArrayList<Allergie> getAllAllergies() throws AccesDBException, ChampsVideException, CaracteresLimiteException{
        try{
            Connection connection = SingletonConnection.getInstance();
            String sql = "select * from allergie;";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet data = statement.executeQuery();

            ArrayList<Allergie> allergies = new ArrayList<>();

            while(data.next()) {

                Allergie allergie = new Allergie(
                        data.getInt(1),
                        data.getString(2),
                        data.getString(3),
                        data.getString(4)
                );
                allergies.add(allergie);
            }

            return allergies;
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void deleteAllergie(Integer allergie_id) throws AccesDBException {
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "delete from souffrance where allergie_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, allergie_id);

            statement.executeUpdate();

            String sql2 = "delete from reaction where allergie_id = ?;";

            statement = connection.prepareStatement(sql2);

            statement.setInt(1, allergie_id);

            statement.executeUpdate();

            String sql3 = "delete from allergie where allergie_id = ?;";

            statement = connection.prepareStatement(sql3);

            statement.setInt(1, allergie_id);

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void updateAllergie(Allergie allergie) throws AccesDBException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "update allergie set libelle = ?, symptome = ?, conditionnement = ? where allergie_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, allergie.getLibelle());
            statement.setString(2, allergie.getSymptome());
            statement.setString(3, allergie.getConditionnement());
            statement.setInt(4, allergie.getId());

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void addMedicament(Medicament medicament) throws AccesDBException {
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "INSERT INTO medicament values(?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setNull(1, Types.INTEGER);
            statement.setString(2, medicament.getCodeCNK());
            statement.setString(3, medicament.getNom());
            statement.setString(4, medicament.getFirme());
            statement.setString(5, medicament.getPrincipeActif());
            statement.setString(6, medicament.getCodeATC());
            statement.setString(7, medicament.getCaracteristique());
            statement.setDouble(8, medicament.getTauxRemboursement());

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public ArrayList<Medicament> getAllMedicaments() throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException{
        try{
            Connection connection = SingletonConnection.getInstance();
            String sql = "select * from medicament;";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet data = statement.executeQuery();

            ArrayList<Medicament> medicaments = new ArrayList<>();

            while(data.next()) {

                Medicament medicament = new Medicament(
                        data.getInt(1),
                        data.getString(2),
                        data.getString(3),
                        data.getString(4),
                        data.getString(5),
                        data.getString(6),
                        data.getString(7),
                        data.getInt(8)
                );
                medicaments.add(medicament);
            }

            return medicaments;
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void deleteMedicament(Integer medicament_id) throws AccesDBException {
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "delete from traitement where medicament_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, medicament_id);

            statement.executeUpdate();

            String sql2 = "delete from reaction where medicament_id = ?;";

            statement = connection.prepareStatement(sql2);

            statement.setInt(1, medicament_id);

            statement.executeUpdate();

            String sql3 = "delete from medicament where medicament_id = ?;";

            statement = connection.prepareStatement(sql3);

            statement.setInt(1, medicament_id);

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void updateMedicament(Medicament medicament) throws AccesDBException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "update medicament set codeCNK = ?, nom = ?, firme = ?, principeActif = ?," +
                    "codeATC = ?, caracteristique = ?, tauxRemboursement = ? where medicament_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, medicament.getCodeCNK());
            statement.setString(2, medicament.getNom());
            statement.setString(3, medicament.getFirme());
            statement.setString(4, medicament.getPrincipeActif());
            statement.setString(5, medicament.getCodeATC());
            statement.setString(6, medicament.getCaracteristique());
            statement.setDouble(7, medicament.getTauxRemboursement());
            statement.setInt(8, medicament.getId());

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void addPatient(Patient patient) throws AccesDBException {
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "INSERT INTO patient values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(sql);


            statement.setNull(1, Types.INTEGER);
            statement.setString(2, patient.getNumeroNational());
            statement.setString(3, patient.getNom());
            statement.setString(4, patient.getPrenom());
            statement.setInt(5, patient.getNbEnfants());
            statement.setDate(6, new java.sql.Date(patient.getDateNaissance().getTimeInMillis()));
            statement.setString(7, patient.getNumTelFixe());
            statement.setString(8, patient.getNumTelMobile());
            statement.setString(9, patient.getRemarque());
            statement.setString(10, patient.getASurveiller());
            statement.setString(11, patient.getConseils());
            statement.setBoolean(12, patient.isDonnerEtat());
            statement.setBoolean(13, patient.isBesoinAval());
            statement.setBoolean(14, patient.isAcharnementTherapeutique());
            statement.setString(15, patient.getCauseDecesPere());
            statement.setString(16, patient.getCauseDecesMere());
            statement.setDouble(17, patient.getPrimeAnuelle());
            if(patient.getMutualite_id() == null){
                statement.setNull(18, Types.INTEGER);
            }
            else{
                statement.setInt(18, patient.getMutualite_id());
            }

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public ArrayList<Patient> getAllPatients() throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String sql = "select * from patient;";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet data = statement.executeQuery();

            ArrayList<Patient> patients = new ArrayList<>();

            while(data.next()) {
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(data.getDate(6));
                Patient patient = new Patient(
                        data.getInt(1),
                        data.getString(2),
                        data.getString(3),
                        data.getString(4),
                        data.getInt(5),
                        calendar,
                        data.getString(7),
                        data.getString(8),
                        data.getString(9),
                        data.getString(10),
                        data.getString(11),
                        data.getBoolean(12),
                        data.getBoolean(13),
                        data.getBoolean(14),
                        data.getString(15),
                        data.getString(16),
                        data.getDouble(17),
                        (data.getInt(18) == 0)?null:data.getInt(18)
                );
                patients.add(patient);
            }
            return patients;
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public Patient getPatient(Integer patient_id) throws AccesDBException, ChampsVideException, CaracteresLimiteException, CodeInvalideException, FormatNombreException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String sql = "select * from patient where patient_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, patient_id);

            ResultSet data = statement.executeQuery();

            data.next();

            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(data.getDate(6));

            Patient patient = new Patient(
                    data.getInt(1),
                    data.getString(2),
                    data.getString(3),
                    data.getString(4),
                    data.getInt(5),
                    calendar,
                    data.getString(7),
                    data.getString(8),
                    data.getString(9),
                    data.getString(10),
                    data.getString(11),
                    data.getBoolean(12),
                    data.getBoolean(13),
                    data.getBoolean(14),
                    data.getString(15),
                    data.getString(16),
                    data.getDouble(17),
                    (data.getInt(18) == 0)?null:data.getInt(18)
                );

            return patient;
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void deletePatient(Integer patient_id) throws AccesDBException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "delete from consultation where patient_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, patient_id);

            statement.executeUpdate();

            String sql2 = "delete from souffrance where patient_id = ?;";

            statement = connection.prepareStatement(sql2);

            statement.setInt(1, patient_id);

            statement.executeUpdate();

            String sql3 = "delete from traitement where patient_id = ?;";

            statement = connection.prepareStatement(sql3);

            statement.setInt(1, patient_id);

            statement.executeUpdate();

            String sql4 = "delete from proche where patient_id = ?;";

            statement = connection.prepareStatement(sql4);

            statement.setInt(1, patient_id);

            statement.executeUpdate();

            String sql5 = "delete from patient where patient_id = ?;";

            statement = connection.prepareStatement(sql5);

            statement.setInt(1, patient_id);

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void updatePatient(Patient patient) throws AccesDBException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "update patient set numeroNational = ?, nom = ?, prenom = ?, nbEnfants = ?," +
                    "dateNaissance = ?, numTelFixe = ?, numTelMobile = ?, remarque = ?, aSurveiller = ?, conseils = ?, " +
                    "donnerEtat = ?, besoinAval = ?, acharnementTherapeutique = ?, causeDecesPere = ?, causeDecesMere = ?, " +
                    "primeAnuelle = ?, mutualite_id = ? where patient_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, patient.getNumeroNational());
            statement.setString(2, patient.getNom());
            statement.setString(3, patient.getPrenom());
            statement.setInt(4, patient.getNbEnfants());
            statement.setDate(5, new java.sql.Date(patient.getDateNaissance().getTimeInMillis()));
            statement.setString(6, patient.getNumTelFixe());
            statement.setString(7, patient.getNumTelMobile());
            statement.setString(8, patient.getRemarque());
            statement.setString(9, patient.getASurveiller());
            statement.setString(10, patient.getConseils());
            statement.setBoolean(11, patient.isDonnerEtat());
            statement.setBoolean(12, patient.isBesoinAval());
            statement.setBoolean(13, patient.isAcharnementTherapeutique());
            statement.setString(14, patient.getCauseDecesPere());
            statement.setString(15, patient.getCauseDecesMere());
            statement.setDouble(16, patient.getPrimeAnuelle());
            if(patient.getMutualite_id() == null){
                statement.setNull(17, Types.INTEGER);
            }
            else{
                statement.setInt(17, patient.getMutualite_id());
            }
            statement.setInt(18, patient.getPatient_id());

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void addConsultation(Consultation consultation) throws AccesDBException, ObjetExistantException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "select dateConsultation from consultation where dateConsultation = ? and soignant_id = ? and patient_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(consultation.getDateConsultation().getTimeInMillis()));
            statement.setInt(2, consultation.getSoignant_id());
            statement.setInt(3, consultation.getPatient_id());

            ResultSet data = statement.executeQuery();

            if (data.next()) {
                throw new ObjetExistantException("Cette consultation existe déjà");
            }

            String sql2 = "INSERT INTO consultation values(?, ?, ?, ?);";

            statement = connection.prepareStatement(sql2);

            statement.setDate(1, new java.sql.Date(consultation.getDateConsultation().getTimeInMillis()));
            if(consultation.getObservations() == null){
                statement.setNull(2, Types.VARCHAR);
            }
            else{
                statement.setString(2, consultation.getObservations());
            }
            statement.setInt(3, consultation.getSoignant_id());
            statement.setInt(4, consultation.getPatient_id());

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void addSouffrance(Souffrance souffrance) throws AccesDBException, ObjetExistantException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "select allergie_id from souffrance where patient_id = ? and allergie_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, souffrance.getPatient_id());
            statement.setInt(2, souffrance.getAllergie_id());

            ResultSet data = statement.executeQuery();

            if (data.next()) {
                throw new ObjetExistantException("Ce patient souffre déjà de cette allergie");
            }

            String sql2 = "INSERT INTO souffrance values(?, ?);";

            statement = connection.prepareStatement(sql2);

            statement.setInt(1, souffrance.getPatient_id());
            statement.setInt(2, souffrance.getAllergie_id());

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void addTraitement(Traitement traitement) throws AccesDBException, ObjetExistantException, AllergieAMedicamentException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "select soignant_id from traitement where patient_id = ? and soignant_id = ? and medicament_id = ? and dateDeDebut = ? and dateDeFin = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, traitement.getPatient_id());
            statement.setInt(2, traitement.getSoignant_id());
            statement.setInt(3, traitement.getMedicament_id());
            statement.setDate(4, new java.sql.Date(traitement.getDateDeDebut().getTimeInMillis()));
            statement.setDate(5, new java.sql.Date(traitement.getDateDeFin().getTimeInMillis()));

            ResultSet data = statement.executeQuery();

            if (data.next()) {
                throw new ObjetExistantException("Vous avez déjà attribué ce traitement à ce patient à cette date");
            }

            String sql2 = "select medicament_id from reaction, souffrance where reaction.medicament_id = ? and souffrance.patient_id = ? and reaction.allergie_id = souffrance.allergie_id;";

            statement = connection.prepareStatement(sql2);
            statement.setInt(1, traitement.getMedicament_id());
            statement.setInt(2, traitement.getPatient_id());

            ResultSet data2 = statement.executeQuery();

            if (data2.next()) {
                throw new AllergieAMedicamentException();
            }

            String sql3 = "INSERT INTO traitement values(?, ?, ?, ?, ?, ?);";

            statement = connection.prepareStatement(sql3);

            statement.setDate(1, new java.sql.Date(traitement.getDateDeDebut().getTimeInMillis()));
            statement.setDate(2, new java.sql.Date(traitement.getDateDeFin().getTimeInMillis()));
            statement.setString(3, traitement.getFrequence());
            statement.setInt(4, traitement.getPatient_id());
            statement.setInt(5, traitement.getSoignant_id());
            statement.setInt(6, traitement.getMedicament_id());

            statement.executeUpdate();
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void addReaction(Reaction reaction) throws AccesDBException, ObjetExistantException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "select allergie_id from reaction where allergie_id = ? and medicament_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, reaction.getAllergie_id());
            statement.setInt(2, reaction.getMedicament_id());

            ResultSet data = statement.executeQuery();

            if (data.next()) {
                throw new ObjetExistantException("Ce médicament provoque déjà cette allergie");
            }

            String sql2 = "INSERT INTO reaction values(?, ?);";

            statement = connection.prepareStatement(sql2);

            statement.setInt(1, reaction.getAllergie_id());
            statement.setInt(2, reaction.getMedicament_id());

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void addProche(Proche proche) throws AccesDBException, ObjetExistantException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "select proche_id from proche where patient_id = ? and numTel = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, proche.getPatient_id());
            statement.setString(2, proche.getNumTel());

            ResultSet data = statement.executeQuery();

            if (data.next()) {
                throw new ObjetExistantException("Vous avez déjà ajouté ce proche à ce patient");
            }

            String sql2 = "INSERT INTO proche values(?, ?, ?, ?, ?, ?, ?, ?);";

            statement = connection.prepareStatement(sql2);

            statement.setNull(1, Types.INTEGER);
            statement.setString(2, proche.getNom());
            statement.setString(3, proche.getPrenom());
            statement.setString(4, proche.getNumTel());
            if(proche.getRemarques() == null){
                statement.setNull(5, Types.VARCHAR);
            }
            else{
                statement.setString(5, proche.getRemarques());
            }
            statement.setBoolean(6, proche.isAAccesInfosMedicales());
            statement.setBoolean(7, proche.isAAppellerSiUrgence());
            statement.setInt(8, proche.getPatient_id());

            statement.executeUpdate();
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public ArrayList<Proche> getAllProches() throws AccesDBException, ChampsVideException, CaracteresLimiteException{
        try{
            Connection connection = SingletonConnection.getInstance();
            String sql = "select * from proche;";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet data = statement.executeQuery();

            ArrayList<Proche> proches = new ArrayList<>();

            while(data.next()) {

                Proche proche = new Proche(
                        data.getInt(1),
                        data.getString(2),
                        data.getString(3),
                        data.getString(4),
                        data.getString(5),
                        data.getBoolean(6),
                        data.getBoolean(7),
                        data.getInt(8)
                );
                proches.add(proche);
            }

            return proches;
        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }

    public void deleteProche(Integer proche_id) throws AccesDBException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String sql = "delete from proche where proche_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, proche_id);

            statement.executeUpdate();

        }
        catch(SQLException exception){
            throw new AccesDBException(exception.getMessage());
        }
    }
}