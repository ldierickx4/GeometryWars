/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.GamePanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import AstralStrifes.Enemy;

/**
 *
 * @author Jens
 */
public class NormalEnemy implements Enemy{
    private static final double SPEED = 0.2;

    private static final int VALUE = 150;

    private final String type ="normal";

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
    private Rectangle enemyBounds;
    private boolean alive;
    

    public NormalEnemy() {
        this.alive = true;
        Random r = new Random(); 
        this.x = rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
        this.y = rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
        loadImage();
        createBoundries();
        
    }
    public void createBoundries()
    {
        enemyBounds = new Rectangle((int)(this.x),(int)(this.y), width, height);
    }
    public void loadImage(){
        BufferedImage i = null;
        String path = "resources/gameSprites/planet1.png";
        try {
            i = ImageIO.read(new File(path));
        } catch (IOException ex) {
            ex.getMessage();
        }
        image = i;
    }
    
    public void moveTo(double x, double y){
        double MoveToX = x;
        double MoveToY = y;
        double angle = Math.atan2(y - this.y, x - this.x);
        double Yvelocity = (SPEED) * Math.cos(angle);
        double Xvelocity = (SPEED) * Math.sin(angle);
        this.x += Yvelocity;
        this.y += Xvelocity;
        enemyBounds.setBounds((int)(this.x),(int)(this.y),image.getWidth(),image.getHeight());
    }
    public void draw(Graphics g){
        g.drawImage(image, (int)(x), (int)(y), gp);
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
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
    public Rectangle getBounds()
    {
        return this.enemyBounds;
    }
    public boolean getStatus(){
        return this.getStatus();
    }
    public void die(){
        this.alive = false;
    }

    public int getValue() {
        //System.out.println(this.VALUE+"in enemy");
        return this.VALUE;
    }
    @Override
    public String getType()
    {
        return this.type;
    }
}
