/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.GamePanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author laurensdierickx
 */
public class ShootingEnemy implements Enemy,Runnable{
    private final String type ="shooting";
    private static final double SPEED = 0.2;
    private int VALUE = 200;
    private BufferedImage image;
    private Graphics g;
    private double x;
    private double y;
    private int width;
    private int height;
    private int rangeMin =50;
    private int rangeMax = 850;
    //private int reward;
    //private int multiplier;
    //private Player player;
    private BulletController bC;
    private Rectangle enemyBounds;
    private boolean alive;
    private Thread thread;
    private LinkedList<Bullet> bullets;
    private Player p;
    
    
    public ShootingEnemy(Player p,BulletController bC)
    {
        this.alive = true;
        Random r = new Random(); 
        this.x = rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
        this.y = rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
        loadImage();
        createBoundries();
        this.bullets = new LinkedList<Bullet>();
        thread = new Thread(this);
        thread.start();
        this.p = p ;
        this.bC = bC;
    }
    public void newBullet()
    {
        Bullet b = new Bullet(this.x,this.y,p.getx(),p.gety(),"enemy");
        bC.addBullet(b);
    }
    @Override
    public void createBoundries() {
        enemyBounds = new Rectangle((int)(this.x),(int)(this.y), image.getWidth(), image.getHeight());
    }
    @Override
    public void loadImage() {
        BufferedImage i = null;
        String path = "resources/gameSprites/planet4.png";
        try {
            i = ImageIO.read(new File(path));
        } catch (IOException ex) {
            ex.getMessage();
        }
        image = i;
    }
    public void draw(Graphics g){
        g.drawImage(image, (int)(this.x), (int)(this.y), null);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public Rectangle getBounds() {
        return this.enemyBounds;
    }

    public boolean getStatus() {
        return this.alive;
    }

    public void die() {
        this.alive = false;
    }
    @Override
    public void run() {
        while(alive)
        {            
            try {
		Thread.sleep(5000);
		}
            catch(Exception e) {
		e.printStackTrace();
		}
            Bullet b = new Bullet(this.x,this.y,p.getx(),p.gety(),"enemy");
            bC.addBullet(b);
        }   
    }
    public LinkedList<Bullet> getBullets()
    {
        return this.bullets;
    }


    @Override
    public int getValue() {
        return this.VALUE;
    }

  

    public String getType()
    {
        return this.type;
    }

    
}
