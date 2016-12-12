/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;



import AstralStrifes.NormalEnemy;
import AstralStrifes.*;
import java.awt.Color;
import java.awt.Font;
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
    private PlayerBulletController controller;
    private CollisionController cc;
    private EnemyController ec;
    private JLabel score;
    private double mouseX;
    private double mouseY;
    private GameFrame gf;
    private EnemyBulletController ebc;
    private int FPS = 60;
    
    
    public GamePanel(GameFrame gf){ 
        this.gf =gf;
        createComponents();
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        this.controller = new PlayerBulletController(player,this);
        this.ebc = new EnemyBulletController(player, this);
        this.ec = new EnemyController(player,this);
        thread = new Thread(this);
        thread.start();
        this.cc = new CollisionController(player,controller, ec);
        score = new JLabel();   
        
    }
    private void createComponents()
    {
        player = new Player();
        background = new Background(gf);
        repaint();
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
    public void checkShoot()
    {
        if(shoot)
        {
            if(controller.getStatus()==false)controller.setShooting();
        }
        else{
            if(controller.getStatus()==true)controller.setNotShooting();
        }
    
    }
    
   
    
    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        gr.drawImage(background.getBackground(), 0, 0, background.getWidth(), background.getHeight(), this); //Moet hier anders draait de achtergrond mee
        Graphics2D g = (Graphics2D)gr;
        player.draw(gr,this);
        //player.drawHealthBar(gr, this);
        player.drawHealth(gr, this);
        controller.render(gr);
        ebc.render(gr);
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
        long previous = System.currentTimeMillis();
        while(current == thread)
        {            
            try {
		Thread.sleep(4);
		}
            catch(Exception e) {
		e.printStackTrace();
		}
            player.updateBounds();
            checkShoot();
            checkInput();
            controller.update();
            ec.update();
            cc.checkPlayerMannaPickup();
            cc.CheckEnemyBulletCoulission();
            ebc.update();
            cc.checkEnemyPlayercollision();
            gf.updateScore(player.getScore()+"");
            player.updateHealth();
            repaint();
            if(System.currentTimeMillis()-previous >= 1000/FPS){
                        System.out.println(System.currentTimeMillis()-previous);
                        previous=System.currentTimeMillis();
            }
        }
    }    
    public double getMouseX(){
        return this.mouseX;
    }
    public double getMouseY(){
        return this.mouseY;
    }
    public Player getPlayer(){
        return this.player;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        mouseX=e.getX();
        mouseY=e.getY();       
        player.calculatePlayerAngle(mouseX,mouseY);
        
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
    public PlayerBulletController getBulletControler()
    {
        return this.controller;
    }
    public CollisionController getCC()
    {
        return this.cc;
    }
    public EnemyController getEc()
    {
        return this.ec;
    }

    public JLabel getScore() {
        return score;
    }                
}
