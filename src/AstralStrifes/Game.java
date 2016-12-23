/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;
import PresentationLayer.GameFrame;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
    public static Stage stage;
    public static Game game;
    public static BorderPane borderPane = new BorderPane();

    public Game()
    {
        game = this;
    }
    
    public static void main(String[] args) throws IOException {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception { 
        this.stage = primaryStage;
        primaryStage.setTitle("AstralStrifes");
        primaryStage.setResizable(false);
        Scene scene = new Scene(borderPane,1000,800);
        Parent mainPane = FXMLLoader.load(getClass().getResource("/FXML/Menu.fxml"));
        this.borderPane.setCenter(mainPane);
        scene.getStylesheets().add("@../../FXML/FXMLSS.css");
        primaryStage.setScene(scene);
        primaryStage.show(); 
        //primaryStage.hide();
    }    
}
