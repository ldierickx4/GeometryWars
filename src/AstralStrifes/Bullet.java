/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.GamePanel;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
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
    private Rectangle borders;
    
            
    public Bullet(double originX , double originY , double destX  , double destY,GamePanel gp){
        bulletVelocity = 1.0;
        this.originX=originX;
        this.originY=originY;
        this.destX = destX;
        this.destY = destY;
        this.angle =Math.atan2(destY - originY, destX - originX);
        this.Yvelocity = (bulletVelocity) * Math.sin(angle);
        this.Xvelocity = (bulletVelocity) * Math.cos(angle);
        loadImage();
        this.gp=gp;
        this.alive = true;
        borders = new Rectangle((int)(originX) ,(int)(originY), (int)(image.getWidth()),(int)(image.getHeight()));

        calcBulletPos();
    }
    
    private void loadImage() {
        BufferedImage i = null;
        try {
           i = ImageIO.read(new File("resources/gameSprites/bullet2.png"));
        } catch (IOException ex) {
            ex.getMessage();
        }
           image = i;
    }
    public void draw(Graphics g){
        calcBulletPos();
        Graphics2D g2 = (Graphics2D)g;
        g.drawImage(image,(int)originX,(int)originY,gp);        
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
    public void calcBulletPos()
    {
        this.originX += Xvelocity;
        this.originY += Yvelocity; 
        borders.setBounds((int)(originX) ,(int)(originY),image.getWidth(),image.getHeight());
    }
    public Rectangle getBorders()
    {
        return this.borders;
    }
}
