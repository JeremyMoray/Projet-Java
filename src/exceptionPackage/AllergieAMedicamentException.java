package exceptionPackage;

public class AllergieAMedicamentException extends Exception{

    public String getMessage(){
        return "Ce patient est allergique à ce médicament !";
    }
}
