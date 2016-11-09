/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import PresentationLayer.GamePanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Jens
 */
public class Enemy {
    private static final double SPEED = 0.4;
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
    private Rectangle playerBounds;
    

    public Enemy() {
        Random r = new Random(); 
        this.x = rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
        this.y = rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
        this.width = 30;
        this.height = 30;
        loadImage();
        playerBounds = new Rectangle();
    }
    
    
    public void loadImage(){
        BufferedImage i = null;
        try {
            //ImageIcon ii =  new ImageIcon("resources/gameSprites/warship.png"); // change the path & folder
            i = ImageIO.read(new File("resources/gameSprites/enemy_3.png"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        image = i;
        
    }
    
    public void moveTo(double x, double y){
        double MoveToX = x;
        double MoveToY = y;

        double angle = Math.atan2(y - this.y, x - this.x);
       
        double Yvelocity = (SPEED) * Math.cos(angle);
        double Xvelocity = (SPEED) * Math.sin(angle);
        
        this.x += Yvelocity;
        this.y += Xvelocity;
        
        playerBounds.setBounds((int)(this.x),(int)(this.y), this.width, this.height);
        //System.out.println(playerBounds.getBounds2D());
    }
    
    public void draw(Graphics g){
        g.drawImage(image, (int)(x), (int)(y), width, height, gp);
    }
     
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }  
}
