/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import AstralStrifes.Game;
import Data.*;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JorreVynckier
 */
public class LeaderboardController implements Initializable {

    /**
     * Initializes the controller class.
    */
    private Database db = new Database();
   
    @FXML
    private TableView naam;
    
    @FXML
    private TableColumn<User,String> Username;
    
    @FXML
    private TableColumn<User,Integer> Highscore;
    
    @FXML 
    private TableColumn<User,Integer> Rank;
    
    //private ObservableList<User> data = FXCollections.observableArrayList(new User("jorre", "jeep", "email", 20, 2000), new User("jorre", "jeep", "email", 20, 2000));
    
    
    @FXML
    private void handleBackButton() throws IOException{
        Stage appStage = Game.stage;
        Parent loginParent = FXMLLoader.load(getClass().getResource("MenuLoggedIn.fxml"));
        Game.borderPane.setCenter(loginParent);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        Highscore.setCellValueFactory(new PropertyValueFactory<User,Integer>("Highscore"));
        Rank.setCellValueFactory(new PropertyValueFactory<User,Integer>("rank_id"));
        ObservableList<User> data = db.getUsers();
        naam.setItems(data);
    }    
    
}
