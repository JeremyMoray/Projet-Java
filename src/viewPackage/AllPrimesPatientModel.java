package viewPackage;

import modelPackage.Patient;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllPrimesPatientModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<Patient> patients;

    public AllPrimesPatientModel(ArrayList<Patient> patients) {
        columnNames = new ArrayList<>();
        columnNames.add("Prénom");
        columnNames.add("Nom");
        columnNames.add("Prime annuelle");
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
        switch(column){
            case 0 : return patient.getPrenom();
            case 1: return patient.getNom();
            case 2: return patient.getPrimeAnuelle();
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
            default:
                c = String.class;
        }
        return c;
    }

    public void removeRow(int position){
        patients.remove(position);
        fireTableRowsDeleted(position, position);
    }
}
