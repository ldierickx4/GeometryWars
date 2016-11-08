/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import PresentationLayer.GamePanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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
    private double originX;
    private double originY; 
    private GamePanel gp;
    private double angle;
    
    
    public Bullet(double originX , double originY , double destX  , double destY,double angel,GamePanel gp){
        bulletVelocity = 3.0;
        this.originX=originX;
        this.originY=originY;
        this.destX = destX;
        this.destY = destY;
        this.angle =angle;
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
        double angle1 = Math.atan2(originY - destY, originX - destX);
        double xVelocity = (bulletVelocity) * Math.cos(angle1);
        double yVelocity = (bulletVelocity) * Math.sin(angle1);
        
        this.originX -= xVelocity;
        this.originY -= yVelocity;
    }    
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform reset = new AffineTransform();
        g2.rotate(angle,originX+20, originY+20);
        g.drawImage(image,(int)originX,(int)originY,40,40,gp);
        g2.setTransform(reset);

    }
    public double giveAngle(){
        return this.angle;
    }
    public double getX(){
        return originX;
    }
    public double getY(){
    return originY;
    }
    
    
}
