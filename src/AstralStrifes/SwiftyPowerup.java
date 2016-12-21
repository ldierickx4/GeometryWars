/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.GamePanel;
<<<<<<< HEAD
=======
import PresentationLayer.SingleGamePanel;
>>>>>>> origin/master
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
    public void endBoost(){
        Player p = gp.getPlayer();
        p.boostSpeed(2);
    }
    @Override
    public void run() {
<<<<<<< HEAD
            boolean pickedUp = super.getPickedUp();
            System.out.println(pickedUp);
            if(pickedUp){  
            //Boost(4);
                try {
                    long time = System.currentTimeMillis();
                    long end = time +1500;
                    while(time <= end) {
                      Boost();
                      System.out.println("Swifty Active");
                    }
                    System.out.println("swifty inactive");
                pickedUp = false;
                endBoost();
                } catch (Exception ex) {
                    Logger.getLogger(SwiftyPowerup.class.getName()).log(Level.SEVERE, null, ex);
                }
=======
        super.used = true;
        while(super.pickedUp){
            long time = System.currentTimeMillis();
            long end = time +3000;
            while(System.currentTimeMillis()<= end) {
                Boost();                  
            }
        endBoost();
        super.pickedUp=false;
>>>>>>> origin/master
        }
    }  
}
