/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;

/**
 *
 * @author Laurens
 */
public class Player {
    private static final int SPEED = 10;
    private String naam;
    private int y;
    private int x;
    private Image image;
    private AffineTransform trans;
    private Graphics g;
    private int width;
    private int height;
    
    
    public Player(){
        this.x = 170;
        this.y = 150;
        this.width=30;
        this.height=30;
        loadImage();
    }

    private void loadImage() {
        ImageIcon ii =  new ImageIcon("resources/gameSprites/warship.png"); // change the path & folder
        image = ii.getImage();
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
}
