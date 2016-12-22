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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JorreVynckier
 */
public class MenuLoggedInController implements Initializable {

    @FXML
    private Hyperlink naam;
   
    @FXML
    private void handlePlayButton() throws IOException{
        Stage appStage = Game.stage;
        Parent loginParent = FXMLLoader.load(getClass().getResource("FirstPlay.fxml"));
        Game.borderPane.setCenter(loginParent);
    }
    
    @FXML
    private void handleLoginButton() throws IOException{
        Stage appStage = Game.stage;
        System.out.println("handleLoginButton");
        Parent loginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Game.borderPane.setCenter(loginParent);
        UserPlay.getinstance().setU(null);
    }
    
    @FXML
    private void handleRegisterButton() throws IOException{
        Stage appStage = Game.stage;
        Parent loginParent = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Game.borderPane.setCenter(loginParent);
    }
    
    @FXML
    private void handlePlayerInfoButton() throws IOException{
        Stage appStage = Game.stage;      
        Parent loginParent = FXMLLoader.load(getClass().getResource("PlayerInfo.fxml"));
        Game.borderPane.setCenter(loginParent);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        naam.setText(UserPlay.getinstance().getU().getUsername());   
    }   
}
