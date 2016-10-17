/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import geometrywars.Player;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author Laurens
 */
public class GamePanel extends JPanel{
    private Player player;
    
    public GamePanel(){ 
        createPlayer();
    }
    private void createPlayer()
    {
        player = new Player();
        repaint();

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(player.giveImage(),50, 50, this);
    }
   
    
}
