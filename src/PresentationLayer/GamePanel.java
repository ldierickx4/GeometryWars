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
public class GamePanel extends JPanel implements KeyListener,Runnable{
    private Player player;
    private boolean running= true;
    private Thread thread;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;
    
    
    
    public GamePanel(){ 
        createPlayer();
        addKeyListener(this);
        
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

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int typed = e.getKeyCode();
        if(typed== e.VK_S){
            player.moveDown();
            System.out.println(player.gety());
        }
        if(typed== e.VK_Z)
        {
            System.out.println(player.gety());
            player.moveUp();
        }
        if(typed== e.VK_Q)
        {
            System.out.println(player.getx());
            player.moveLeft();
        }
        if(typed== e.VK_D)
        {
            System.out.println(player.getx());
            player.moveRight();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void run() {
        
        long start;
	long elapsed;
	long wait;
        
        while(running)
        {
            start = System.nanoTime();
            
            repaint();
            
            elapsed = System.nanoTime() - start;
            System.out.println(elapsed);
            
            wait = targetTime - elapsed / 1000000;
            if(wait < 0) wait = 5;
            
            try {
		Thread.sleep(wait);
		}
            catch(Exception e) {
		e.printStackTrace();
		}
        
        
        }
    }
}
