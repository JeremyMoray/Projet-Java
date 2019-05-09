package viewPackage;

import exceptionPackage.CaracteresLimiteException;
import exceptionPackage.ChampsVideException;
import exceptionPackage.CodeInvalideException;
import exceptionPackage.FormatNombreException;
import modelPackage.Medicament;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllMedicamentsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Medicament> medicaments;

    public AllMedicamentsModel(ArrayList<Medicament> medicaments) {
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
            case 5 : return medicament.getCodeATC();
            case 6 : return medicament.getCaracteristique();
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

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try{
            Medicament medicament = medicaments.get(rowIndex);
            switch (columnIndex) {
                case 1:
                    if (aValue instanceof String) {
                        medicament.setCodeCNK(aValue.toString());
                    }
                    break;
                case 2:
                    if (aValue instanceof String) {
                        medicament.setNom(aValue.toString());
                    }
                    break;
                case 3:
                    if (aValue instanceof String) {
                        medicament.setFirme(aValue.toString());
                    }
                    break;
                case 4:
                    if (aValue instanceof String) {
                        medicament.setPrincipeActif(aValue.toString());
                    }
                    break;
                case 5:
                    if (aValue instanceof String) {
                        medicament.setCodeATC(aValue.toString());
                    }
                    break;
                case 6:
                    if (aValue instanceof String) {
                        medicament.setCaracteristique(aValue.toString());
                    }
                    break;
                case 7:
                    if (aValue instanceof Double) {
                        medicament.setTauxRemboursement(Double.parseDouble(aValue.toString()));
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
}

