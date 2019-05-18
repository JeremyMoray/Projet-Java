package viewPackage;

import modelPackage.InfosConsultation;
import modelPackage.Patient;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class AllConsultationsModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<InfosConsultation> consultations;

    public AllConsultationsModel(ArrayList<InfosConsultation> consultations) {
        columnNames = new ArrayList<>();
        columnNames.add("Date consultation");
        columnNames.add("Nom");
        columnNames.add("Prénom");
        columnNames.add("Date naissance");
        columnNames.add("Mutualité");

        this.consultations = consultations;
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return (consultations==null)?0:consultations.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt (int row, int column){
        InfosConsultation consultation = consultations.get(row);
        switch(column){
            case 0 :
                DateFormat dateConsultationFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date dateConsultationFormatDate = consultation.getDateConsultation().getTime();
                return dateConsultationFormat.format(dateConsultationFormatDate);
            case 1: return consultation.getNomPatient();
            case 2: return consultation.getPrenomPatient();
            case 3:
                DateFormat dateNaissanceFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date dateNaissanceFormatDate = consultation.getDateNaissance().getTime();
                return dateNaissanceFormat.format(dateNaissanceFormatDate);
            case 4: return consultation.getLibelle();
            default : return null;
        }
    }

    public Class getColumnClass (int column)
    {
        Class c;
        switch (column)
        {
            case 0:
                c = DateFormat.class;
                break;
            case 1:
                c = String.class;
                break;
            case 2:
                c = String.class;
                break;
            case 3:
                c = DateFormat.class;
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
        consultations.remove(position);
        fireTableRowsDeleted(position, position);
    }
}
