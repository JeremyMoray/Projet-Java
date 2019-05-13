package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.Patient;
import modelPackage.Proche;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllProchesModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Proche> proches;
    private ApplicationController controller;
    private Patient patient;

    public AllProchesModel(ArrayList<Proche> proches) {
        columnNames = new ArrayList<>();
        columnNames.add("Id du proche");
        columnNames.add("Prénom du patient");
        columnNames.add("Nom du patient");
        columnNames.add("Prénom du proche");
        columnNames.add("Nom du proche");
        this.proches = proches;
        setController(new ApplicationController());
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return proches.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt (int row, int column){
        Proche proche = proches.get(row);

        try{
            patient = controller.getPatient(proche.getPatient_id());
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
        catch (FormatNombreException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        catch (CodeInvalideException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        switch(column){
            case 0 : return proche.getProche_id();
            case 1 : return patient.getPrenom();
            case 2: return patient.getNom();
            case 3: return proche.getPrenom();
            case 4 : return proche.getNom();
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
            default:
                c = String.class;
        }
        return c;
    }

    public void removeRow(int position){
        proches.remove(position);
        fireTableRowsDeleted(position, position);
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }
}
