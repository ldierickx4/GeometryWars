/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import LoginScreen.*;
import PresentationLayer.GameFrame;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 *
 * @author Laurens
 */
public class Game extends Application{

    /**
     * @param primaryStage
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //GameFrame window = new GameFrame();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {   
        Parent mainPane = FXMLLoader.load(getClass().getResource("/LoginScreen/Login.fxml"));
        primaryStage.setTitle("AstralStrifes");
        primaryStage.setScene(new Scene(mainPane, 900,900));
        primaryStage.show();
        //Game t = new Game();
        //t.loadScreen(primaryStage);
    } 
}
