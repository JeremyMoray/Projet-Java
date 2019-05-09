package exceptionPackage;

public class FormatDateException extends Exception{

    public String getMessage(){
        return "Veuillez entrer une date correcte";
    }
}
