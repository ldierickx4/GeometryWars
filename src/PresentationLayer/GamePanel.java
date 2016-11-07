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
    private Thread thread ;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;
    
    
    
    public GamePanel(){ 
        createPlayer();
        addKeyListener(this);
        thread = new Thread(this);
        thread.start();
        
    }
    private void createPlayer()
    {
        player = new Player();
        //repaint();
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
        }
        if(typed== e.VK_Z)
        {
            player.moveUp();
        }
        if(typed== e.VK_Q)
        {
            player.moveLeft();
        }
        if(typed== e.VK_D)
        {
            player.moveRight();
        }
        //repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void run() {
        
        long start;
	long elapsed;
	long wait;
        Thread current = Thread.currentThread();
        while(current == thread)
        {            
            //System.out.println(elapsed);
            
            try {
		Thread.sleep(0);
		}
            catch(Exception e) {
		e.printStackTrace();
		}
            repaint();
        
        }
    }
}
