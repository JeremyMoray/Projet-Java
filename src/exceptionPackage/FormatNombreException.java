package exceptionPackage;

public class FormatNombreException extends Exception{

    private String champs;

    public FormatNombreException(String champs){
        this.champs = champs;
    }

    public String getMessage(){
        return "Veuillez entrer un nombre valide pour le champs " + champs;
    }
}