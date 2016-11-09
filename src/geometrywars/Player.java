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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Laurens
 */
public class Player {
    private static final int SPEED = 2;
    private String naam;
    private int y;
    private int x;
    private BufferedImage image;
    private AffineTransform trans;
    private Graphics g;
    private int width;
    private int height;
    private double angle;
    
    
    public Player(){
        this.x = 170;
        this.y = 150;
        this.width=30;
        this.height=30;
        loadImage();
    }

    private void loadImage() {
        BufferedImage i = null;
        try {
            //ImageIcon ii =  new ImageIcon("resources/gameSprites/warship.png"); // change the path & folder
            i = ImageIO.read(new File("resources/gameSprites/warship.png"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        image = i;
    }
    public void moveUp()
    {
        this.y-=SPEED;
    }
    public void moveDown()
    {
        this.y+=SPEED;
    }
    public void moveLeft()
    {
        this.x-=SPEED;
    }
    public void moveRight()
    {
        this.x+=SPEED;
    }
    public Image giveImage()
    {
        return image;
    }
    public int getx()
    {
        return x;
    }
    public int gety()
    {
        return y;
    }
    public int getHeight()
    {
        return height;
    }
    public int getWidth(){
        return width;
    }
    public void setPlayerAngle(double angle)
    {
        this.angle=angle;
    }
    public double getPlayerAngle()
    {
        return this.angle;
    }
    public void draw(Graphics g,GamePanel gp)
    {
        AffineTransform reset = new AffineTransform();
        Graphics2D g2 = (Graphics2D)g;
        g2.rotate(getPlayerAngle(),getx()+getWidth()/2,gety()+getHeight()/2);
        g2.drawImage(giveImage(),getx(), gety(),getWidth(),getHeight(),gp);
        g2.setTransform(reset);
        
    }
}
