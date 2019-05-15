package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Mutualite;
import modelPackage.Patient;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class AllPatientsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Patient> patients;
    private ApplicationController controller;
    private Mutualite mutualite;

    public AllPatientsModel(ArrayList<Patient> patients) {
        columnNames = new ArrayList<>();
        columnNames.add("Id");
        columnNames.add("Numéro national");
        columnNames.add("Nom");
        columnNames.add("Prénom");
        columnNames.add("Nb. d'enfants");
        columnNames.add("Date de naissance");
        columnNames.add("Num. tel. fixe");
        columnNames.add("Num. tel. mobile");
        columnNames.add("Remarque");
        columnNames.add("A surveiller");
        columnNames.add("Conseils");
        columnNames.add("Donner l'état");
        columnNames.add("Besoin d'aval");
        columnNames.add("Archarnement thérapeutique");
        columnNames.add("Cause décès du père");
        columnNames.add("Cause décès de la mère");
        columnNames.add("Prime anuelle");
        columnNames.add("Mutualité");
        this.patients = patients;
        setController(new ApplicationController());
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return patients.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt (int row, int column){
        Patient patient = patients.get(row);

        try{
            if(patient.getMutualite_id() != null){
                mutualite = controller.getMutualite(patient.getMutualite_id());
            }
            else{
                mutualite = null;
            }
        }
        catch (AccesDBException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (ChampsVideException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (CaracteresLimiteException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        switch(column){
            case 0 : return patient.getPatient_id();
            case 1: return patient.getNumeroNational();
            case 2: return patient.getNom();
            case 3 : return patient.getPrenom();
            case 4 : return patient.getNbEnfants();
            case 5 :
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date dateFormatDate = patient.getDateNaissance().getTime();
                return dateFormat.format(dateFormatDate);
            case 6 : return (patient.getNumTelFixe() == null)?"":patient.getNumTelFixe();
            case 7 : return (patient.getNumTelMobile() == null)?"":patient.getNumTelMobile();
            case 8 : return (patient.getRemarque() == null)?"":patient.getRemarque();
            case 9 : return (patient.getASurveiller() == null)?"":patient.getASurveiller();
            case 10 : return (patient.getConseils() == null)?"":patient.getConseils();
            case 11 : return (patient.isDonnerEtat()?"Oui":"Non");
            case 12 : return (patient.isBesoinAval())?"Oui":"Non";
            case 13 : return (patient.isAcharnementTherapeutique())?"Oui":"Non";
            case 14 : return (patient.getCauseDecesPere() == null)?"":patient.getCauseDecesPere();
            case 15 : return (patient.getCauseDecesMere() == null)?"":patient.getCauseDecesMere();
            case 16 : return patient.getPrimeAnuelle();
            case 17 :
                return (mutualite == null)?"Aucune":mutualite.getLibelle() + " (" + mutualite.getDiminutif() + ")";
            default : return null;
        }
    }

    public Class getColumnClass (int column)
    {
        Class c;
        switch (column)
        {
            case 0:
                c = Integer.class;
                break;
            case 1:
                c = String.class;
                break;
            case 2:
                c = String.class;
                break;
            case 3:
                c = String.class;
                break;
            case 4:
                c = Integer.class;
                break;
            case 5:
                c = GregorianCalendar.class;
                break;
            case 6:
                c = String.class;
                break;
            case 7:
                c = String.class;
                break;
            case 8:
                c = String.class;
                break;
            case 9:
                c = String.class;
                break;
            case 10:
                c = String.class;
                break;
            case 11:
                c = String.class;
                break;
            case 12:
                c = String.class;
                break;
            case 13:
                c = String.class;
                break;
            case 14:
                c = String.class;
                break;
            case 15:
                c = String.class;
                break;
            case 16:
                c = Double.class;
                break;
            case 17:
                c = Integer.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }

    public void removeRow(int position){
        patients.remove(position);
        fireTableRowsDeleted(position, position);
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try{
            Patient patient = patients.get(rowIndex);
            switch (columnIndex) {
                case 1:
                    if (aValue instanceof String) {
                        patient.setNumeroNational(aValue.toString());
                    }
                    break;
                case 2:
                    if (aValue instanceof String) {
                        patient.setNom(aValue.toString());
                    }
                    break;
                case 3:
                    if (aValue instanceof String) {
                        patient.setPrenom(aValue.toString());
                    }
                    break;
                case 4:
                    if (aValue instanceof Integer) {
                        patient.setNbEnfants(Integer.parseInt(aValue.toString()));
                    }
                    break;
                case 5:
                    if (aValue instanceof GregorianCalendar) {
                        patient.setDateNaissance((GregorianCalendar)aValue);
                    }
                    break;
                case 6:
                    if (aValue instanceof String) {
                        patient.setNumTelFixe(aValue.toString());
                    }
                    else{
                        if (aValue == null) {
                            patient.setNumTelFixe(null);
                        }
                    }
                    break;
                case 7:
                    if (aValue instanceof String) {
                        patient.setNumTelMobile(aValue.toString());
                    }
                    else{
                        if (aValue == null) {
                            patient.setNumTelMobile(null);
                        }
                    }
                    break;
                case 8:
                    if (aValue instanceof String) {
                        patient.setRemarque(aValue.toString());
                    }
                    else{
                        if (aValue == null) {
                            patient.setRemarque(null);
                        }
                    }
                    break;
                case 9:
                    if (aValue instanceof String) {
                        patient.setASurveiller(aValue.toString());
                    }
                    else{
                        if (aValue == null) {
                            patient.setASurveiller(null);
                        }
                    }
                    break;
                case 10:
                    if (aValue instanceof String) {
                        patient.setConseils(aValue.toString());
                    }
                    else{
                        if (aValue == null) {
                            patient.setConseils(null);
                        }
                    }
                    break;
                case 11:
                    if (aValue instanceof Boolean) {
                        patient.setDonnerEtat(aValue.toString().equals("true"));
                    }
                    break;
                case 12:
                    if (aValue instanceof Boolean) {
                        patient.setBesoinAval(aValue.toString().equals("true"));
                    }
                    break;
                case 13:
                    if (aValue instanceof Boolean) {
                        patient.setAcharnementTherapeutique(aValue.toString().equals("true"));
                    }
                    break;
                case 14:
                    if (aValue instanceof String) {
                        patient.setCauseDecesPere(aValue.toString());
                    }
                    else{
                        if (aValue == null) {
                            patient.setCauseDecesPere(null);
                        }
                    }
                    break;
                case 15:
                    if (aValue instanceof String) {
                        patient.setCauseDecesMere(aValue.toString());
                    }
                    else{
                        if (aValue == null) {
                            patient.setCauseDecesMere(null);
                        }
                    }
                    break;
                case 16:
                    if (aValue instanceof Double) {
                        patient.setPrimeAnuelle(Double.parseDouble(aValue.toString()));
                    }
                    break;
                case 17:
                    if (aValue instanceof Integer ) {
                        patient.setMutualite_id(Integer.parseInt(aValue.toString()));
                    }
                    else{
                        if (aValue == null) {
                            patient.setMutualite_id(null);
                        }
                    }
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }
        catch (ChampsVideException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (CaracteresLimiteException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (FormatNombreException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (CodeInvalideException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }
}



