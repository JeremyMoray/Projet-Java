package exceptionPackage;

public class ObjetExistantException extends Exception{

    private String message;

    public ObjetExistantException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
