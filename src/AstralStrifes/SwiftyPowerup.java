/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.GamePanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Jordy
 */
public class SwiftyPowerup extends Powerup implements Runnable{
    
    private Thread t;
    private GamePanel gp;
    //private boolean pickedUp = false;
    
    public SwiftyPowerup(String name,GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.name = name;
        String link = "resources/gameSprites/swifty.png";
        super.loadImage(link); 
        t = new Thread(this);
        t.start();      
    }
    
    public void Boost(){
        Player p = gp.getPlayer();
        p.boostSpeed(40);
    }

    @Override
    public void run() {
        while(true){
            boolean pickedUp = super.getPickedUp();
            //System.out.println(pickedUp + " swifty ");
            //System.out.println("Swifty picked up");
            if(pickedUp){  
            //Boost(4);
                try {
                    long time = System.currentTimeMillis();
                    long end = time +15000;
                    while(System.currentTimeMillis() < end) {
                      Boost();
                      System.out.println("Swifty Active");
                    }  
                } catch (Exception ex) {
                    Logger.getLogger(SwiftyPowerup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        pickedUp = false;
        }
    }  
}
