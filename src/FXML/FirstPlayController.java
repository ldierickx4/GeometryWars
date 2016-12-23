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
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private RadioButton healPl1;
    
    @FXML
    private RadioButton healPl2;
    
    @FXML
    private RadioButton attackPl1;
    
    @FXML
    private RadioButton attackPl2;
    
    @FXML
    private RadioButton killPl1;
    
    @FXML
    private RadioButton killPl2;
    
    @FXML
    private RadioButton onePlayer;
    
    @FXML
    private RadioButton twoPlayer;
    
    @FXML 
    private ToggleGroup players;
    
    @FXML
    private ToggleGroup Drone1;
    
    @FXML
    private ToggleGroup Drone2;
    
    @FXML 
    private Pane PlayerOneDrone;
    
    @FXML
    private Pane PlayerTwoDrone;
    
    @FXML
    private RadioButton easy;
    
    @FXML
    private RadioButton medium;
    
    @FXML
    private RadioButton hard;
    
    @FXML
    private ToggleGroup Difficulty;
    
    @FXML
    private AnchorPane controllerNotPluggedIn;
    
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
    private void handleStartButton() throws IOException, Exception{
        String drone1 = Drone1.getSelectedToggle().getUserData().toString();
        String drone2 = Drone2.getSelectedToggle().getUserData().toString();
        String difficulty = Difficulty.getSelectedToggle().getUserData().toString();
        int difficultyInt = getDifficulty(difficulty);
        if ("two".equals(players.getSelectedToggle().getUserData().toString())){
            try{          
                Stage appStage = Game.stage;
                GameFrame gf = new GameFrame(2, drone1 , drone2, difficultyInt);
                appStage.setHeight(1);
                appStage.setWidth(1);                      
            } catch(Exception ex){
                controllerNotPluggedIn.setVisible(true);
                ex.printStackTrace();               
            }           
        }else{  
            Stage appStage = Game.stage;  
            GameFrame gf = new GameFrame(1, drone1, difficultyInt);
            appStage.setHeight(1);
            appStage.setWidth(1);
        }      
    }
    
    @FXML
    private void handleBackControllerButton(){
        controllerNotPluggedIn.setVisible(false);
    }
    
    @FXML
    private void changeSelectedRadio(){      
        if ("two".equals(players.getSelectedToggle().getUserData().toString())){
           PlayerOneDrone.setVisible(true);
        }else{
           PlayerOneDrone.setVisible(false);
        }      
    }
    
    public int getDifficulty(String difficulty){
        switch(difficulty){
            case "hard":
                return 3;     
            
            case "medium":
                return 2;
                
            case "easy":
                return 1;
                
            default:
                return 1;
        }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onePlayer.setUserData("one");
        twoPlayer.setUserData("two");    
        healPl1.setUserData("heal");
        healPl2.setUserData("heal");
        attackPl1.setUserData("attack");
        attackPl2.setUserData("attack");
        killPl1.setUserData("kill");
        killPl2.setUserData("kill"); 
        easy.setUserData("easy");
        medium.setUserData("medium");
        hard.setUserData("hard");
    }       
}
