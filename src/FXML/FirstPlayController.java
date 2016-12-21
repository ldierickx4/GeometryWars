/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import AstralStrifes.Game;
import Data.UserPlay;
import PresentationLayer.GameFrame;
import java.awt.Button;
import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JorreVynckier
 */
public class FirstPlayController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private RadioButton onePlayer;
    
    @FXML
    private RadioButton twoPlayer;
    
    @FXML 
    private ToggleGroup players;
    
    @FXML 
    private Pane PlayerOneDrone;
    
    @FXML
    private Pane PlayerTwoDrone;
    
    @FXML
    private void handleBackButton() throws IOException{
        Stage appStage = Game.stage;
        if (UserPlay.getinstance().getU() != null){
            Parent loginParent = FXMLLoader.load(getClass().getResource("MenuLoggedIn.fxml"));
            Game.borderPane.setCenter(loginParent);
        } else{
            Parent loginParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Game.borderPane.setCenter(loginParent);
        }     
    }
    
    @FXML 
    private void handleStartButton() throws IOException{
        Stage appStage = Game.stage;        
        GameFrame gameframe = new GameFrame();
    } 
    
    @FXML
    private void changeSelectedRadio(){
        System.out.println(players.getSelectedToggle());
        if ("RadioButton[id=twoPlayer, styleClass=radio-button]'Two'".equals(players.getSelectedToggle().toString())){
           PlayerOneDrone.setVisible(true);
        }else{
           PlayerOneDrone.setVisible(false);
        }
    }

        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
