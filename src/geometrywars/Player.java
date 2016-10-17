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
    private String naam;
    private int y;
    private int x;
    private Image image;
    private AffineTransform trans;
    private Graphics g;
    
    
    public Player(){
        this.x = 170;
        this.y = 560;
        loadImage();
    }

    private void loadImage() {
        ImageIcon ii =  new ImageIcon("resources/gameSprites/warship.png"); // change the path & folder
        image = ii.getImage();
    }
    public void Draw(Graphics g) {
        g.drawImage(image,x,y,null);
    }
    public void moveUp()
    {
        this.y++;
    }
    public void moveDown()
    {
        this.y--;
    }
    public void moveLeft()
    {
        this.x++;
    }
    public void moveRight()
    {
        this.x--;
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
    
    
}
