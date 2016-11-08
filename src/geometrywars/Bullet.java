/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import PresentationLayer.GamePanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;

/**
 *
 * @author laurensdierickx
 */
public class Bullet{
    private BufferedImage image;
    private Graphics g;
    private double bulletVelocity;
    private double xVelocity;
    private double yVelocity;
    private double destX;
    private double destY;
    private double angle;
    private double originX;
    private double originY; 
    private GamePanel gp;
    
    
    public Bullet(double originX , double originY , double destX  , double destY,GamePanel gp){
        bulletVelocity = 1.0;
        this.originX=originX;
        this.originY=originY;
        this.destX = destX;
        this.destY = destY;
        calculateAngle();
        loadImage();
        this.gp=gp;
    }
    
    private void loadImage() {
        BufferedImage i = null;
        try {
           i = ImageIO.read(new File("resources/gameSprites/beam.png"));
        } catch (IOException ex) {
            ex.getMessage();
        }
           image = i;
    }
    
    public void calculateXvelo()
    {      
        double angle1 = Math.atan2(originX - destX, originY - destY);
        double tempx = originX - destX;
        double tempy = originY - destX;
        double xVelocity = (bulletVelocity) * Math.sin(angle1);
        double yVelocity = (bulletVelocity) * Math.cos(angle1);

        //double mag = (double) java.lang.Math.hypot(tempx, tempy);
        //tempy/=mag;
        //tempx/=mag;
        //tempy*=1.0;
        //tempx*=1.0;
        System.out.println(xVelocity);
        System.out.println(yVelocity);
        //this.originY += tempy;
        //this.originX += tempx;
        this.originX += xVelocity;
        this.originY += yVelocity;
    }
    private void calculateAngle()
    {
        double angle = Math.atan2(originX - destX, originY - destY);
        System.out.println(angle+"dit is de angle");
    }
    
    public void draw(Graphics g)
    {        
        g.drawImage(image,(int)originX,(int)originY,40,40,gp);
    }
    
    
}
