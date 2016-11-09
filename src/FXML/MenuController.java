/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import geometrywars.Game;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.*;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JorreVynckier
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
    */
    
    
    @FXML
    private Label label;
   
    
    @FXML
    private void handleLoginButton() throws IOException{
        Stage appStage = Game.stage;
        System.out.println("handleLoginButton");
        Parent loginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Game.borderPane.setCenter(loginParent);
        //Scene loginScene = new Scene(loginParent, 900, 900);  
        //appStage.setScene(loginScene);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}