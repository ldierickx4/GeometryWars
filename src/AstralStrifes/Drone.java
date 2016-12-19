/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.imageio.ImageIO;

/**
 *
 * @author laurensdierickx
 */
public class Drone{
    public Player p;
    private String type;
    private double orbitX;
    private double orbitY; 
    private double timeInterval=1;
    private int powerTime=10000;
    private final double orbitRadius = 50;
    private final double ORBITSPEED = Math.PI / 150;
    private final double SPHERERADIUS = 10;
    private int x;
    private int y;
    private BufferedImage image;
    private boolean alive;
    private Thread thread;
    private double radian= ORBITSPEED * timeInterval;
    private Method power;
    
    public Drone(Player p){
        this.p=p;
    }
    public void loadImage(String link){
        BufferedImage i = null;
        try {
            i = ImageIO.read(new File(link));
        } catch (IOException ex) {
            ex.getMessage();
        }
        this.image = i;  
    }
    public void draw(Graphics g){
        g.drawImage(image,(int)(this.x),(int)(this.y),null);
    }
    public void letOrbit(){
        this.timeInterval+=1;
        this.radian = ORBITSPEED * timeInterval;
        this.x = (int)(p.getx() + orbitRadius * Math.cos(radian));
        this.y =(int)(p.gety() + orbitRadius * Math.sin(radian));
    } 
    public int getY(){
        return this.y;
    }
    public int getX(){
        return this.x;
    }
}
