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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JorreVynckier
 */
public class PlayerInfoController implements Initializable{

    private Database db = new Database();
    
    @FXML
    private Button back;
    
    @FXML
    private Label email;
    
    @FXML
    private Label rank;
    
    @FXML
    private Label highscore;
    
    @FXML
    private Label username;
    
    
    @FXML
    private void handleBackController() throws IOException{
        Stage appStage = Game.stage;
        Parent loginParent = FXMLLoader.load(getClass().getResource("MenuLoggedIn.fxml"));
        Game.borderPane.setCenter(loginParent);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User u = UserPlay.getinstance().getU();
        username.setText(u.getUsername());
        email.setText(u.getEmail());
        String highscores = db.getPlayerHighScore(u.getUsername())+"";
        highscore.setText(highscores);
        String rankid = db.getRankid(u.getUsername())+"";;
        rank.setText(rankid);
    }
}


