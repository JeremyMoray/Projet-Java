package viewPackage;


import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class AllTraitementsModel extends AbstractTableModel{

    private ArrayList<String> columnNames;
    private ArrayList<ArrayList<String>>  traitements;

    public AllTraitementsModel(ArrayList<ArrayList<String>> traitements) {
        columnNames = new ArrayList<>();
        columnNames.add("Nom soigant");
        columnNames.add("Prénom soignant");
        columnNames.add("Fréquence");
        columnNames.add("Nom médicament");
        columnNames.add("Firme médicament");
        columnNames.add("Nom patient");
        columnNames.add("Prénom patient");
        this.traitements = traitements;
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return (traitements==null)?0:traitements.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt (int row, int column){
        switch(column){
            case 0 : return traitements.get(row).get(0);
            case 1: return traitements.get(row).get(1);
            case 2: return traitements.get(row).get(2);
            case 3 : return traitements.get(row).get(3);
            case 4 : return traitements.get(row).get(4);
            case 5 : return traitements.get(row).get(5);
            case 6 : return traitements.get(row).get(6);
            default : return null;
        }
    }

    public Class getColumnClass (int column)
    {
        Class c;
        switch (column)
        {
            case 0:
                c = String.class;
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
            case 6:
                c = Boolean.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }

    public void removeRow(int position){
        traitements.remove(position);
        fireTableRowsDeleted(position, position);
    }
}
