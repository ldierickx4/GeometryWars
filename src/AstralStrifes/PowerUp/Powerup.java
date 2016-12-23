/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.PowerUp;

import AstralStrifes.Controllers.PlayerBulletController;
import AstralStrifes.Player;
import PresentationLayer.GamePanel;
import PresentationLayer.SingleGamePanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Jordy
 */
public class Powerup{
    private int x;
    private int y; 
    private Rectangle bounds;
    private int rangeMin = 50;
    private int rangeMax = 750;
    private Thread t;
    private GamePanel gp;
    public boolean used = false;
    public boolean pickedUp = false;
    public BufferedImage image;
    public String link;
    public String name;
    

    public Powerup(GamePanel gp) {
        Random r = new Random(); 
        this.x = rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
        this.y = rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
    } 
    
    
    public void loadImage(String link) {
        BufferedImage i = null;
        try {
            i = ImageIO.read(new File(link));
        } catch (IOException ex) {
            ex.getMessage();
        }
        image = i; 
        this.bounds = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }
    
    public void draw(Graphics g){
        g.drawImage(image, (int)(this.x), (int)(this.y), null);
    }
    
    public Rectangle getBounds(){
        return this.bounds;
    }

    public String getName() {
        return name;
    }
    
    public void setPickedUp(){
        this.pickedUp = true;
    }  
    
    public boolean getPickedUp(){
        return this.pickedUp;
    }
}
