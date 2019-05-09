package viewPackage;

import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;
import modelPackage.Mutualite;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllMutualitesModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Mutualite> mutualites;

    public AllMutualitesModel(ArrayList<Mutualite> mutualites) {
        columnNames = new ArrayList<>();
        columnNames.add("Id");
        columnNames.add("Nom");
        columnNames.add("Affiliation politique");
        columnNames.add("Diminutif");
        this.mutualites = mutualites;
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return mutualites.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt (int row, int column){
        Mutualite mutualite = mutualites.get(row);
        switch(column){
            case 0 : return mutualite.getId();
            case 1: return mutualite.getLibelle();
            case 2: return mutualite.getAffiliationPolitique();
            case 3 : return mutualite.getDiminutif();
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
        mutualites.remove(position);
        fireTableRowsDeleted(position, position);
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try{
            Mutualite mutualite = mutualites.get(rowIndex);
            switch (columnIndex) {
                case 1:
                    if (aValue instanceof String) {
                        mutualite.setLibelle(aValue.toString());
                    }
                    break;
                case 2:
                    if (aValue instanceof String) {
                        mutualite.setAffiliationPolitique(aValue.toString());
                    }
                    break;
                case 3:
                    if (aValue instanceof String) {
                        mutualite.setDiminutif(aValue.toString());
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
