package viewPackage;

import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;
import modelPackage.Allergie;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllAllergiesModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Allergie> allergies;

    public AllAllergiesModel(ArrayList<Allergie> allergies) {
        columnNames = new ArrayList<>();
        columnNames.add("Id");
        columnNames.add("Nom");
        columnNames.add("Symptôme");
        columnNames.add("Conditionnement");
        this.allergies = allergies;
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return allergies.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt (int row, int column){
        Allergie allergie = allergies.get(row);
        switch(column){
            case 0 : return allergie.getId();
            case 1: return allergie.getLibelle();
            case 2: return allergie.getSymptome();
            case 3 : return allergie.getConditionnement();
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
        allergies.remove(position);
        fireTableRowsDeleted(position, position);
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        try {
            Allergie allergie = allergies.get(rowIndex);

            switch (columnIndex) {
                case 1:
                    if (aValue instanceof String) {
                        allergie.setLibelle(aValue.toString());
                    }
                    break;
                case 2:
                    if (aValue instanceof String) {
                        allergie.setSymptome(aValue.toString());
                    }
                    break;
                case 3:
                    if (aValue instanceof String) {
                        allergie.setConditionnement(aValue.toString());
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

    }
}

