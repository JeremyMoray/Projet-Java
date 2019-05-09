package exceptionPackage;

public class ChampsVideException extends Exception{

    private String champs;

    public ChampsVideException(String champs){
        this.champs = champs;
    }

    public String getMessage(){
        return "Le champs " + champs + " est vide";
    }
}