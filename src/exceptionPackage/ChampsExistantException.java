package exceptionPackage;

public class ChampsExistantException extends Exception {

    private String champs;

    public ChampsExistantException(String champs){
        this.champs = champs;
    }

    public String getMessage(){
        return "Le champs " + champs + " existe déjà !";
    }
}