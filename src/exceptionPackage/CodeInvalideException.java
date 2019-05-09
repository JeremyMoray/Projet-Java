package exceptionPackage;

public class CodeInvalideException extends Exception{

    private String description;
    private String champs;

    public CodeInvalideException(String champs, String description){
        this.description = description;
        this.champs = champs;
    }

    public String getMessage(){
        return "Erreur dans le format du champs " + champs + " :\n" + description;
    }
}