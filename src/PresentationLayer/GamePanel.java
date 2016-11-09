/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;


import geometrywars.Background;

import geometrywars.Bullet;
import geometrywars.Controller;
import geometrywars.Enemy;
import geometrywars.EnemyController;

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
    private boolean shoot = false;
    private double imageAngleRad = 0;
    private Controller controller;

    private EnemyController ec;
    private Enemy enemy;

    private double mouseX;
    private double mouseY;
    

   
    
    public GamePanel(){ 
        createComponents();
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        this.controller = new Controller(player);
        this.enemy = new Enemy();
        this.ec = new EnemyController(enemy);
        addEnemy(enemy);
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
        else if(up){
            player.moveUp();
        }
        else if(left){
            player.moveLeft();
        }
        else if(right){
            player.moveRight();
        }


    }
    public void checkShoot()
    {
        if(shoot)
        {
            shootBullet(mouseX, mouseY);
        }
    
    }
    
   
    
    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        gr.drawImage(background.getBackground(), 0, 0, background.getWidth(), background.getHeight(), this); //Moet hier anders draait de achtergrond mee
        Graphics2D g = (Graphics2D)gr;
        player.draw(gr,this);
        controller.render(gr);

        ec.render(gr);

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
        else if(typed== e.VK_Z)
        {
            up = true;
        }
        else if(typed== e.VK_Q)
        {
            left = true;
        }
        else if(typed== e.VK_D)
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
        else if(typed== e.VK_Z)
        {            
            up = false;
        }
        else if(typed== e.VK_Q)
        {            
            left = false;
        }
        else if(typed== e.VK_D)
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
            checkShoot();
            controller.update();
            ec.update();
            repaint();
        }
    }
    public void shootBullet(double destX,double destY){
        Bullet b = new Bullet(player.getx(),player.gety(),destX,destY,this);
        controller.addBullet(b);
    }
    
    public void addEnemy(Enemy e){
        ec.addEnemy(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double angle = Math.atan2(player.gety() - e.getY(), player.getx() - e.getX());
        player.setPlayerAngle(angle);
        mouseX=e.getX();
        mouseY=e.getY();
                
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
    }
    

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==e.BUTTON1)
        {
            shoot=true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==e.BUTTON1)
        {
            shoot=false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

   
}
