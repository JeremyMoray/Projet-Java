package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AccesDBException;

import javax.swing.*;

public class ThreadClass extends Thread{

    public void run(){
        try{
            FenetreFermeture fenetreFermeture = new FenetreFermeture();
            for(int i = 5; i > 0; i--){
                fenetreFermeture.setSeconde(i);
                Thread.sleep(1000);
                if(!fenetreFermeture.isActive()){
                    throw new InterruptedException();
                }
            }
            ApplicationController controller = new ApplicationController();
            controller.closeConnection();
            System.exit(0);
        }
        catch(InterruptedException exception){
            JOptionPane.showMessageDialog(null, "Fermeture annulée !", "Erreur fermeture", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (AccesDBException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
