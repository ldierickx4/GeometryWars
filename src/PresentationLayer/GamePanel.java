/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import geometrywars.Player;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import javax.swing.*;

/**
 *
 * @author Laurens
 */
public class GamePanel extends JPanel implements KeyListener,Runnable,MouseMotionListener{
    private Player player;
    private boolean running= true;
    private Thread thread ;
    private boolean down = false;
    private boolean up = false;
    private boolean left = false;
    private boolean right = false;
    private double imageAngleRad = 0;
    
   
    
    public GamePanel(){ 
        createPlayer();
        addKeyListener(this);
        addMouseMotionListener(this);
        thread = new Thread(this);
        thread.start();
        
    }
    private void createPlayer()
    {
        player = new Player();
        //repaint();
    }
    public void checkInput(){
        if(down){
            player.moveDown();
        }
        if(up){
            player.moveUp();
        }
        if(left){
            player.moveLeft();
        }
        if(right){
            player.moveRight();
        }
    }
    
    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D)gr;
        g.rotate(imageAngleRad);
        g.translate(player.getWidth(),player.getWidth());
        double locationX = player.getWidth() / 2;
        double locationY = player.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(imageAngleRad, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(player.giveImage(),tx,this);
        //g.drawImage(player.giveImage(),tx,player.getx(), player.gety(),player.getWidth(),player.getHeight(), this);
       
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int typed = e.getKeyCode();
        if(typed== e.VK_S)
        {
            down = true;
        }
        if(typed== e.VK_Z)
        {
            up = true;
        }
        if(typed== e.VK_Q)
        {
            left = true;
        }
        if(typed== e.VK_D)
        {
            right = true;            
        }        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int typed = e.getKeyCode();
        if(typed== e.VK_S){
            down = false;
        }
        if(typed== e.VK_Z)
        {            
            up = false;
        }
        if(typed== e.VK_Q)
        {            
            left = false;
        }
        if(typed== e.VK_D)
        {            
            right = false;            
        }
    }

    @Override
    public void run() {
        
        Thread current = Thread.currentThread();
        while(current == thread)
        {            
            checkInput();
            //System.out.println(elapsed);
            try {
		Thread.sleep(4);
		}
            catch(Exception e) {
		e.printStackTrace();
		}
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        e.getX();
        e.getY();
        double dx = e.getX() - player.getx();
        double dy = e.getY() - player.gety();
        System.out.println(dx+"  "+dy);
        imageAngleRad = Math.atan2(dy, dx);
    }

   
}
