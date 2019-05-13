package viewPackage;

import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;
import modelPackage.Proche;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.*;

public class AllProchesAAppellerModel extends AbstractTableModel{

    private ArrayList<String> columnNames;
    private ArrayList<Proche> proches;

    public AllProchesAAppellerModel(ArrayList<Proche> proches) {
        columnNames = new ArrayList<>();
        columnNames.add("Id");
        columnNames.add("Nom");
        columnNames.add("Prenom");
        columnNames.add("Num. tel");
        columnNames.add("Remarques");
        columnNames.add("A accès aux infos médicales");
        this.proches = proches;
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return (proches==null)?0:proches.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt (int row, int column){
        Proche proche = proches.get(row);
        switch(column){
            case 0 : return proche.getProche_id();
            case 1: return proche.getNom();
            case 2: return proche.getPrenom();
            case 3 : return proche.getNumTel();
            case 4 : return proche.getRemarques();
            case 5 : return (proche.isAAccesInfosMedicales())?"Oui":"Non";
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
                c = Boolean.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }

    public void removeRow(int position){
        proches.remove(position);
        fireTableRowsDeleted(position, position);
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        try {
            Proche proche = proches.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    if (aValue instanceof Integer) {
                        proche.setPatient_id(Integer.parseInt(aValue.toString()));
                    }
                    break;
                case 1:
                    if (aValue instanceof String) {
                        proche.setNom(aValue.toString());
                    }
                    break;
                case 2:
                    if (aValue instanceof String) {
                        proche.setPrenom(aValue.toString());
                    }
                    break;
                case 3:
                    if (aValue instanceof String) {
                        proche.setNumTel(aValue.toString());
                    }
                    break;
                case 4:
                    if (aValue instanceof String) {
                        proche.setRemarques(aValue.toString());
                    }
                    break;
                case 5:
                    if (aValue instanceof Boolean) {
                        proche.setAAccesInfosMedicales(aValue.toString().equals("true"));
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
