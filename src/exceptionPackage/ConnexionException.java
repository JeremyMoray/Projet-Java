package exceptionPackage;

public class ConnexionException extends Exception{

    public String getMessage(){
        return "Login ou mot de passe incorrect";
    }
}