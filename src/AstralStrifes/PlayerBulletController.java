/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.GamePanel;
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
    
    public PlayerBulletController(Player player, GamePanel gp)
    {   
        this.bullets = new LinkedList<Bullet>();
        this.player = player;
        this.gp = gp;
    }
    public void addBullet(Bullet b){
        bullets.add(b);
    }
    public void update(){
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
		Thread.sleep(100);
		}
            catch(Exception e) {
		e.printStackTrace();
		}
            Bullet b = new Bullet(player.getx(),player.gety(),gp.getMouseX(),gp.getMouseY(),"player");
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
}
