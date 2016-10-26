/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import geometrywars.Player;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author Laurens
 */
public class GamePanel extends JPanel implements KeyListener{
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
        g.drawImage(player.giveImage(),player.getx(), player.gety(),player.getWidth(),player.getHeight(), this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int typed = e.getKeyCode();
        if(typed == e.VK_DOWN){
            player.moveDown();
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
