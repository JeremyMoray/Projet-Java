package exceptionPackage;

import java.sql.SQLException;

public class AccesDBException extends Exception{
    private String message;

    public AccesDBException(String message){
        this.message = message;
    }

    public String getMessage(){
        return "Echec de la connexion au serveur : " + message;
    }
}