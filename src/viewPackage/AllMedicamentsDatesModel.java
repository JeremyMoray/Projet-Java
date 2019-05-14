package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AccesDBException;
import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;
import modelPackage.Medicament;
import modelPackage.Traitement;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class AllMedicamentsDatesModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Medicament> medicaments;
    private ApplicationController controller;

    public AllMedicamentsDatesModel(ArrayList<Medicament> medicaments) {
        columnNames = new ArrayList<>();
        columnNames.add("Id");
        columnNames.add("Code CNK");
        columnNames.add("Nom");
        columnNames.add("Firme");
        columnNames.add("Principe Actif");
        columnNames.add("Code ATC");
        columnNames.add("Caractéristique");
        columnNames.add("Taux de remboursement");
        this.medicaments = medicaments;
        setController(new ApplicationController());
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return medicaments.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt (int row, int column){
        Medicament medicament = medicaments.get(row);

        switch(column){
            case 0 : return medicament.getId();
            case 1: return medicament.getCodeCNK();
            case 2: return medicament.getNom();
            case 3 : return medicament.getFirme();
            case 4 : return medicament.getPrincipeActif();
            case 5: return medicament.getCodeATC();
            case 6: return medicament.getCaracteristique();
            case 7 : return medicament.getTauxRemboursement();
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
        medicaments.remove(position);
        fireTableRowsDeleted(position, position);
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }
}