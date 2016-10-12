/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Gebruiker
 */
public class GameFrame extends JFrame{
    
    public GameFrame(){
        setTitle("Geometry Wars");
        //pack();
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args){
        GameFrame window = new GameFrame();
    }
}
