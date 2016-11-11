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
    private double Yvelocity;
    private double Xvelocity;
    private boolean alive;
    
    public Bullet(double originX , double originY , double destX  , double destY,GamePanel gp){
        bulletVelocity = 2.0;
        this.originX=originX;
        this.originY=originY;
        this.destX = destX;
        this.destY = destY;
        this.angle =Math.atan2(originY - destY, originX - destX);
        this.Yvelocity = (bulletVelocity) * Math.sin(angle);
        this.Xvelocity = (bulletVelocity) * Math.cos(angle);
        loadImage();
        this.gp=gp;
        this.alive = true;
    }
    
    private void loadImage() {
        BufferedImage i = null;
        try {
           i = ImageIO.read(new File("resources/gameSprites/bullet.png"));
        } catch (IOException ex) {
            ex.getMessage();
        }
           image = i;
           System.out.println(image.getWidth());
    }
    
    public void calculatePos()
    {   
        
        this.originX -= Xvelocity;
        this.originY -= Yvelocity;
        
    }    
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform reset = new AffineTransform();
        System.out.println(image.getWidth());
        System.out.println(image.getHeight());
        g2.rotate(angle,originX,originY);
        g.drawImage(image,(int)originX,(int)originY,gp);
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
    public boolean alive()
    {
        return this.alive;
    }
    public boolean checkAlive()
    {
        int x = (int)(originX);
        int y = (int)(originY);
            if(x<0||x>900||y<0||y>900){
                this.alive = false;
            } 
        return this.alive;
    }
    
    
}
