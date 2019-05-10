package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Mutualite;
import modelPackage.Patient;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

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
        columnNames.add("Archarnement thérapeuthique");
        columnNames.add("Cause décès du père");
        columnNames.add("Cause décès de la mère");
        columnNames.add("Prime anuelle");
        columnNames.add("Mutualité");
        this.patients = patients;
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
            mutualite = controller.getMutualite(patient.getMutualite_id());
        }
        catch (ChampsVideException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (CaracteresLimiteException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (AccesDBException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        switch(column){
            case 0 : return patient.getPatient_id();
            case 1: return patient.getNumeroNational();
            case 2: return patient.getNom();
            case 3 : return patient.getPrenom();
            case 4 : return patient.getNbEnfants();
            case 5 : return patient.getDateNaissance();
            case 6 : return patient.getNumTelFixe();
            case 7 : return patient.getNumTelMobile();
            case 8 : return patient.getRemarque();
            case 9 : return patient.getASurveiller();
            case 10 : return patient.getConseils();
            case 11 : return patient.isDonnerEtat();
            case 12 : return patient.isBesoinAval();
            case 13 : return patient.isAcharnementTherapeuthique();
            case 14 : return patient.getCauseDecesPere();
            case 15 : return patient.getCauseDecesMere();
            case 16 : return patient.getPrimeAnuelle();
            case 17 : return mutualite;
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
                c = String.class;
                break;
            case 5:
                c = String.class;
                break;
            case 6:
                c = String.class;
                break;
            case 7:
                c = Double.class;
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
                        patient.setCodeCNK(aValue.toString());
                    }
                    break;
                case 2:
                    if (aValue instanceof String) {
                        patient.setNom(aValue.toString());
                    }
                    break;
                case 3:
                    if (aValue instanceof String) {
                        patient.setFirme(aValue.toString());
                    }
                    break;
                case 4:
                    if (aValue instanceof String) {
                        patient.setPrincipeActif(aValue.toString());
                    }
                    break;
                case 5:
                    if (aValue instanceof String) {
                        patient.setCodeATC(aValue.toString());
                    }
                    break;
                case 6:
                    if (aValue instanceof String) {
                        patient.setCaracteristique(aValue.toString());
                    }
                    break;
                case 7:
                    if (aValue instanceof Double) {
                        patient.setTauxRemboursement(Double.parseDouble(aValue.toString()));
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



