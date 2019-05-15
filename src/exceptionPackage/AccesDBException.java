package exceptionPackage;

public class AccesDBException extends Exception{
    private String message;

    public AccesDBException(String message){
        this.message = message;
    }

    public String getMessage(){
        return "Echec de la connexion au serveur : " + message;
    }
}