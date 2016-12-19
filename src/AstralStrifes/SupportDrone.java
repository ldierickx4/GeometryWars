/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.GamePanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author laurensdierickx
 */
public class SupportDrone implements Runnable{
    private Player p;
    private double orbitX;
    private double orbitY; 
    private double timeInterval=1;
    private final double orbitRadius = 50;
    private final double ORBITSPEED = Math.PI / 16;
    private final double SPHERERADIUS = 10;
    private int x;
    private int y;
    private BufferedImage image;
    private boolean alive;
    private Thread thread;
    
    public SupportDrone(Player p){
        this.p=p;
        loadImage();        
        this.alive = true;
        this.thread = new Thread(this);
        this.thread.start();
    }
    public void loadImage(){
        BufferedImage i = null;
        try {
            i = ImageIO.read(new File("resources/gameSprites/healdrone.png"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        this.image = i;    
    }
    public void draw(Graphics g){
        g.drawImage(image,(int)(this.x),(int)(this.y),null);
    }

    @Override
    public void run() {
        while(alive){
            try {
                thread.sleep(40);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        
        }
    }
    public void letOrbit(){
        double radian = ORBITSPEED * timeInterval;
        //System.out.println(radian);
        double drawX = p.getx() + orbitRadius * Math.cos(radian);
        double drawY = p.gety() + orbitRadius * Math.sin(radian);
    }
    
}
