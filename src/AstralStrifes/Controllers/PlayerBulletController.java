/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Controllers;

import AstralStrifes.Bullet;
import AstralStrifes.Player;
import PresentationLayer.GamePanel;
import PresentationLayer.SingleGamePanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laurensdierickx
 */
public class PlayerBulletController implements Runnable{
    private GamePanel gp;
    private LinkedList<Bullet> bullets;
    private Player player;
    private Thread thread;
    private boolean shooting= false;
    private int FIRING_RATE = 100;
    private double aimX;
    private double aimY;
    
    public PlayerBulletController(Player player,GamePanel gp,double aimx,double aimy)
    {   
        this.bullets = new LinkedList<Bullet>();
        this.player = player;
        this.gp = gp;
        this.aimX = aimX;
        this.aimY = aimY;
    }
    public void addBullet(Bullet b){
        bullets.add(b);
    }
    public void update(double aimX , double aimY){
        this.aimX = aimX;
        this.aimY = aimY;
        Bullet tempBullet;
        for(int i=0;i<bullets.size();i++){
            tempBullet = bullets.get(i);
            tempBullet.calcBulletPos();
            if(!(tempBullet.checkAlive()))
            {
                bullets.remove(tempBullet);
            }            
        }
    }
    public void render(Graphics g){
        Bullet tempBullet;
        for(int i=0;i<bullets.size();i++){
            tempBullet = bullets.get(i);     
            tempBullet.draw(g);                
        }
    } 
    public void setShooting()
    {    
        this.shooting=true;
        this.thread = new Thread(this);        
        thread.start();
    }
    public void setNotShooting()
    {
        this.shooting=false;
    }

    @Override
    public void run() {
        while(this.shooting)
        {            
            try {
		Thread.sleep(FIRING_RATE);
		}
            catch(Exception e) {
		e.printStackTrace();
		}
            Bullet b = new Bullet(player.getx(),player.gety(),aimX,aimY,"player");
            addBullet(b);
        }
        thread=null;
    }
    public boolean getStatus(){
        return this.shooting;
    }
    public LinkedList<Bullet> giveBullets(){
        return this.bullets;    
    }
    
    public int getFiringRate(){
        return this.FIRING_RATE;
    }
    
    public void boostFiringRate(int fireBoost){
        this.FIRING_RATE = fireBoost;
    }
    
    public void resetFiringRate(){
        this.FIRING_RATE = 100;
    }
}
