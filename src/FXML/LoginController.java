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
public class LoginController {

    /**
     * Initializes the controller class.
     */
    private Database db = new Database();
    
    @FXML
    public TextField usernameLogin;
    public TextField passwordLogin;
    public AnchorPane notLoggedIn;
    
    @FXML
    private void handleBackButton() throws IOException{
        Stage appStage = Game.stage;
        Parent loginParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Game.borderPane.setCenter(loginParent);
    }
    
    @FXML
    private void handleLoginButton() throws IOException{
        System.out.println(passwordLogin.isVisible());
        System.out.println(usernameLogin.getText());
        this.db.checkUserDB(usernameLogin.getText(), passwordLogin.getText());
        if (this.db.getUserExist()){
            Stage appStage = Game.stage;
            Parent loginParent = FXMLLoader.load(getClass().getResource("MenuLoggedIn.fxml"));
            Game.borderPane.setCenter(loginParent);
        } else{
            notLoggedIn.setVisible(true);
        }    
    }
    
    @FXML
    private void handleNotLoggedInButton(){
        notLoggedIn.setVisible(false);
    }
    
    
    
}
