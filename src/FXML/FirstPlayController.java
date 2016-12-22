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
<<<<<<< Updated upstream
        //Stage appStage = Game.stage;        
        //GameFrame gameframe = new GameFrame();
        System.out.println(Drone1.getSelectedToggle());
        System.out.println(Drone2.getSelectedToggle());
        System.out.println(players.getSelectedToggle());
        if ("RadioButton[id=twoPlayer, styleClass=radio-button]'Two'".equals(players.getSelectedToggle().toString())){
            GameFrame gf = new GameFrame(2,"kill","attack",2);
        }else{
            GameFrame gf = new GameFrame(1,"kill",2);
        }
        
        
=======
        String drone1 = Drone1.getSelectedToggle().getUserData().toString();
        String drone2 = Drone2.getSelectedToggle().getUserData().toString();
        String difficulty = Difficulty.getSelectedToggle().getUserData().toString();
        int difficultyInt = getDifficulty(difficulty);
        System.out.println(difficultyInt);
        if ("two".equals(players.getSelectedToggle().getUserData().toString())){
            GameFrame gf = new GameFrame(2, drone1 , drone2);
        }else{
            GameFrame gf = new GameFrame(1, drone1);
        }      
>>>>>>> Stashed changes
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
