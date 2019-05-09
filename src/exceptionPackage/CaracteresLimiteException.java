package exceptionPackage;

public class CaracteresLimiteException extends Exception{
    private String champs;

    public CaracteresLimiteException(String champs){
        this.champs = champs;
    }

    public String getMessage(){
        return "Le champs " + champs + " contient un trop grand nombre de caractères !";
    }
}