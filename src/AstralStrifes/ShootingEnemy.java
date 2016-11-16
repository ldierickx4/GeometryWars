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
    private static final double SPEED = 0.2;
    private BufferedImage image;
    private Graphics g;
    private double x;
    private double y;
    private int width;
    private int height;
    private int rangeMin = 0;
    private int rangeMax = 900;
    //private int reward;
    //private int multiplier;
    //private Player player;
    private GamePanel gp;
    private Rectangle enemyBounds;
    private boolean alive;
    private Thread thread;
    private LinkedList<Bullet> bullets;
    private Player p;
    
    
    public ShootingEnemy(Player p,GamePanel gp)
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
        this.gp = gp;
    }
    public void newBullet()
    {
        Bullet b = new Bullet(this.x,this.y,p.getx(),p.gety(),gp,"enemy");
        bullets.add(b);
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
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int)(x), (int)(y), gp);
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public Rectangle getBounds() {
        return this.enemyBounds;
    }

    @Override
    public boolean getStatus() {
        return this.alive;
    }

    @Override
    public void die() {
        this.alive = false;
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        while(current == thread)
        {            
            try {
		Thread.sleep(4000);
		}
            catch(Exception e) {
		e.printStackTrace();
		}
            newBullet();
        }   
    }
    public LinkedList<Bullet> getBullets()
    {
        return this.bullets;
    }
    
}
