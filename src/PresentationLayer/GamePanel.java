/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;


import geometrywars.Background;

import geometrywars.Bullet;
import geometrywars.Controller;

import geometrywars.Player;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Laurens
 */
public class GamePanel extends JPanel implements KeyListener,Runnable,MouseMotionListener,MouseListener{
    private Player player;
    private Background background;
    private boolean running = true;
    private Thread thread;
    private boolean down = false;
    private boolean up = false;
    private boolean left = false;
    private boolean right = false;
    private double imageAngleRad = 0;
    private Controller controller;
    
   
    
    public GamePanel(){ 
        createComponents();
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        this.controller = new Controller(player);
        thread = new Thread(this);
        thread.start();
        
    }
    private void createComponents()
    {
        player = new Player();
        background = new Background();
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
        gr.drawImage(background.getBackground(), 0, 0, background.getWidth(), background.getHeight(), this); //Moet hier anders draait de achtergrond mee
        Graphics2D g = (Graphics2D)gr;
        AffineTransform reset = new AffineTransform();
        Graphics2D g2 = (Graphics2D)g;
        g2.rotate(player.getPlayerAngle(),player.getx()+player.getWidth()/2, player.gety()+player.getHeight()/2);
        //draw the image here
        g2.drawImage(player.giveImage(),player.getx(), player.gety(),player.getWidth(),player.getHeight(), this);
        g2.setTransform(reset);
        controller.render(gr);
        
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
            try {
		Thread.sleep(4);
		}
            catch(Exception e) {
		e.printStackTrace();
		}
            controller.update();
            repaint();
        }
    }
    public void shootBullet(double destX,double destY,double shootAngle){
        Bullet b = new Bullet(player.getx(),player.gety(),destX,destY,shootAngle,this);
        controller.addBullet(b);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        float angle = (float)(Math.atan2(player.gety() - e.getY(), player.getx() - e.getX()));
        player.setPlayerAngle(angle);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==e.BUTTON1)
        {   double shootAngle = Math.atan2(player.gety() - e.getY() , player.getx() - e.getX());
            System.out.println(shootAngle);
            shootBullet(e.getX(),e.getY(),shootAngle);
        }
        
    }
    

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

   
}
