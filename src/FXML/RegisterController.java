/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;
import Data.*;
import geometrywars.Game;
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
        //System.out.println("handleLoginButton");
        Parent loginParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Game.borderPane.setCenter(loginParent);
        //registrerenGelukt.setVisible(false);
        //registrerenNietGelukt.setVisible(false);
        //Scene loginScene = new Scene(loginParent, 900, 900);  
        //appStage.setScene(loginScene);
    }
    
    @FXML
    private void registerButton(){
       System.out.println(registerUsername.getText());
       System.out.println(registerEmail.getText());
       System.out.println(passwordRegister.getText());
       if ( !(registerEmail.getText().isEmpty()) & !(passwordRegister.getText().isEmpty()) & !(registerUsername.getText().isEmpty())){
           if((db.addUser(registerUsername.getText(), passwordRegister.getText(), registerEmail.getText()))){
              registrerenGelukt.setVisible(true); 
           }
       } else{
           registrerenNietGelukt.setVisible(true);
       }  
    }
    
    @FXML
    private void tryAgainRegister(){
        registrerenNietGelukt.setVisible(false);
    }
    
    
    
}
