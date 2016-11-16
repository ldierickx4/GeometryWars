/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.GamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.*;

/**
 *
 * @author Laurens
 */
public class Player {
    private static final int SPEED = 2;
    private static final int HEALTH = 100;
    private String naam;
    private double y;
    private double x;
    private BufferedImage image;
    private AffineTransform trans;
    private Graphics g;
    private double width;
    private double height;
    private double angle;
    private int score;
    private Rectangle playerBounds;
    
    public Player(){
        this.width=28;
        this.height=30;
        this.x = 170.0;
        this.y = 150.0;
        this.score = 0;
        loadImage();
        createBounds();
    }
    private void createBounds()
    {
        this.playerBounds = new Rectangle((int)(this.x),(int)(this.y), image.getWidth(), image.getHeight());
    }

    private void loadImage() {
        BufferedImage i = null;
        try {
            i = ImageIO.read(new File("resources/gameSprites/ship.png"));
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
    public double getx()
    {
        return this.x;
    }
    public double gety()
    {
        return this.y;
    }
    public double getHeight()
    {
        return image.getHeight();
    }
    public double getWidth(){
        return image.getWidth();
    }
    public void setPlayerAngle(double angle)
    {
        this.angle=angle;
    }
    public double getPlayerAngle()
    {
        return this.angle;
    }
    public int getPlayerCenterX()
    {
        return (int)(x)-image.getHeight()/2;
    }
    public int getPlayerCenterY()
    { 
        return (int)(y)-image.getWidth()/2;
    }
    public void draw(Graphics g,GamePanel gp)
    {
        AffineTransform reset = new AffineTransform();
        Graphics2D g2 = (Graphics2D)g;
        g2.rotate(getPlayerAngle(),getx(),gety());
        g2.drawImage(giveImage(),getPlayerCenterX(), getPlayerCenterY(),image.getWidth(),image.getHeight(),gp);
        g2.setTransform(reset);
    }

    public void calculatePlayerAngle(double mouseX, double mouseY) {
        this.angle = Math.atan2(gety() - mouseY, getx() - mouseX);
    }
    
    public void drawHealthBar(Graphics g,GamePanel gp){
        AffineTransform reset = new AffineTransform();
        Graphics2D g2 = (Graphics2D)g;
        g2.drawRect(20, 20, HEALTH, 40);
        g2.setColor(Color.green);
        g2.fillRect(20, 20, HEALTH, 40);
        g2.setTransform(reset);
    }

    public int getScore() {
        return score;
    }
    
    public void raiseScore(int points){
        this.score += points;
    }
    public Rectangle getBounds(){
        return this.playerBounds;
    }

    public void updateBounds() {
        playerBounds.setBounds((int)(this.x),(int)(this.y), image.getWidth(), image.getHeight());
    }
    
   
    
    
}
