/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import AstralStrifes.Game;
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
    private void handleBackButton() throws IOException{
        Stage appStage = Game.stage;
        Parent loginParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Game.borderPane.setCenter(loginParent);
    }
    
    @FXML 
    private void handleStartButton() throws IOException{
        Stage appStage = Game.stage;        
        GameFrame gameframe = new GameFrame();
    } 

    /**
    @FXML
    private void changeSelectedRadio(){
        if ("RadioButton[id=twoPlayer, styleClass=radio-button]'Two'".equals(players.getSelectedToggle().toString())){
           PlayerOne.setVisible(false);
        }else{
           PlayerOne.setVisible(true);
        }
        //System.out.println(players.getSelectedToggle().toString());
    }
    **/
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
