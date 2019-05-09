package exceptionPackage;

public class AucuneSelectionException extends Exception{

    public String getMessage(){
        return "Rien n'a été sélectionné !";
    }
}