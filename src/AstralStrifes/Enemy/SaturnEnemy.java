/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Enemy;

import PresentationLayer.GamePanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author laurensdierickx
 */

public class SaturnEnemy implements Enemy{
    private BufferedImage image;
    private double x;
    private double y;
    private String type;
    private int rangeMin = 50;
    private int rangeMax = 800;
    private Rectangle bounds;
    private boolean right;
    private boolean alive;
    private Random r;
    private GamePanel gp;
    private int damage;
    private int multiplier;
    private int score;
    private double speed;
    
    public SaturnEnemy(GamePanel gp){
        this.gp = gp;
        alive=true;
        right=true;
        this.type="saturn";
        this.r = new Random(); 
        this.x = randomInt();
        this.y = randomInt();
        loadImage();
        createBoundries();
        this.damage= gp.getDiff().getsDamage();
        this.score = gp.getDiff().getSeScore();
        this.multiplier = gp.getDiff().getSeMultie();
        this.speed= gp.getDiff().getSeMove();
    }

    @Override
    public void createBoundries(){
        this.bounds = new Rectangle((int)x, (int)y, image.getWidth(), image.getHeight());
    }
    public int randomInt(){
        return rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
    }
    public void slide(){
        if(right&&x<1000){
            x+=speed;
        }
        else{
            x-=speed;
            right = false;
            if(x<=0){
                right = true;
            }   
        }
        bounds.setBounds((int)(this.x),(int)(this.y),image.getWidth(),image.getHeight());
    }

    @Override
    public void loadImage() {
        BufferedImage i = null;
        String path = "resources/gameSprites/planet6.png";
        try {
            i = ImageIO.read(new File(path));
        } catch (IOException ex) {
            ex.getMessage();
        }
        image = i;
    }

    @Override
    public String getType() {
        return this.type;
    }
    
    @Override
    public void draw(Graphics g){
        g.drawImage(image,(int)(this.x),(int)(this.y),null);
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public int getWidth() {
        return this.image.getWidth();
    }

    @Override
    public int getHeight() {
        return this.getHeight();
    }

    @Override
    public Rectangle getBounds() {
        return this.bounds;
    }

    @Override
    public boolean getStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void die() {
        this.alive=false;
    }

    @Override
    public int getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Manna getManna() {
        return new Manna(score,multiplier,(int)this.x,(int)this.y);
    }
    public double getCenterX(){
        return x+image.getHeight()/2;
    }

    public double getCenterY() {
        return y+image.getWidth()/2;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void moveTo(double x, double y) {
    }
}
