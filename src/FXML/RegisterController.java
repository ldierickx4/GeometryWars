/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;
import Data.*;
import AstralStrifes.Game;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JorreVynckier
 */
public class RegisterController  {

    private Database db = new Database();


    
    
    @FXML
    public TextField registerUsername;
    public TextField registerEmail;
    public TextField passwordRegister;
    public TextField repeatPasswordRegister;
    public AnchorPane registrerenGelukt;
    public AnchorPane registrerenNietGelukt;
   
    @FXML
    private void handleBackButton() throws IOException{
        Stage appStage = Game.stage;
        Parent loginParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Game.borderPane.setCenter(loginParent);
    }
    
    @FXML
    private void registerButton(){
       System.out.println(registerUsername.getText());
       System.out.println(registerEmail.getText());
       System.out.println(passwordRegister.getText());
       if (!(registerEmail.getText().isEmpty()) & !(passwordRegister.getText().isEmpty()) & !(registerUsername.getText().isEmpty())){
            System.out.println("Dit lukt wel");
            this.db.addUser(registerUsername.getText(), passwordRegister.getText(), registerEmail.getText());
            if(this.db.getUserAdded()){  
                System.out.println("Dit lukt ook wel");
            registrerenGelukt.setVisible(true); 
            } else{
                System.out.println("Dit lukt niet");
            }
       } else{
           registrerenNietGelukt.setVisible(true);
       }  
    }
    
    @FXML
    private void tryAgainRegister(){
        registrerenNietGelukt.setVisible(false);
<<<<<<< HEAD
=======
        registrerenNietGeluktBcsPass.setVisible(false);
    }
    
    @FXML
    private boolean checkFilledIn(){
        if (!(registerEmail.getText().isEmpty()) & !(passwordRegister.getText().isEmpty()) & !(registerUsername.getText().isEmpty())){
            return true;
        } else{
            return false;
        }
    }
    
    @FXML
    private boolean checkPasswords(){
        if(passwordRegister.getText().equals(repeatPasswordRegister.getText())){
            return true;    
        } else{
            return false;
        }
>>>>>>> origin/master
    }
    
    
    
}
