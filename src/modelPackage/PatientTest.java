package modelPackage;

import exceptionPackage.ChampsVideException;
import exceptionPackage.FormatNombreException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

public class PatientTest {

    private Patient patient;

    @Before
    public void setUp() throws Exception {
        GregorianCalendar dateNaissance = new GregorianCalendar();
        dateNaissance.set(GregorianCalendar.DAY_OF_MONTH, 5);
        dateNaissance.set(GregorianCalendar.MONTH, 6);
        dateNaissance.set(GregorianCalendar.YEAR, 1998);

        patient = new Patient(null,
                "85062575469",
                "Fongemie",
                "Baptiste",
                3,
                dateNaissance,
                null,
                "0473 27 91 38",
                "Très instable",
                null,
                "Etre délicat",
                false,
                true,
                true,
                "Mort dans un accident",
                null,
                25.24,
                1
        );
    }

    @Test
    public void setNbEnfants() throws ChampsVideException, FormatNombreException{
        patient.setNbEnfants(2);
    }

    @Test (expected = ChampsVideException.class)
    public void setNbEnfantsNull() throws ChampsVideException, FormatNombreException {
        patient.setNbEnfants(null);
    }

    @Test (expected = FormatNombreException.class)
    public void setNbEnfantsNegatif() throws ChampsVideException, FormatNombreException {
        patient.setNbEnfants(-15);
    }
}