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
    private static final int SPEED = 1;
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
    

    public Enemy() {
        Random r = new Random(); 
        this.x = 400; //rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
        this.y = 400; //rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
        this.width = 30;
        this.height = 30;
        loadImage();
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

        double angle = Math.atan2(y - MoveToY, x - MoveToX);
        System.out.println(MoveToX);
        System.out.println(MoveToY);
        System.out.println(y+"enemy y");
        System.out.println(x+"enemy X");
        
        double Yvelocity = (SPEED) * Math.sin(angle);
        double Xvelocity = (SPEED) * Math.cos(angle);

        this.x += Yvelocity;
        this.y += Xvelocity;
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
