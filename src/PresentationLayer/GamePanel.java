/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import geometrywars.Player;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Gebruiker
 */
public class GamePanel extends JPanel {
    
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;
    public Player player;
    
    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
    }
    
    
}
