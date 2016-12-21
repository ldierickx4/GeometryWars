/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.GamePanel;
import PresentationLayer.SingleGamePanel;
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
    private Player p;
    //private boolean pickedUp = false;
    
    public SwiftyPowerup(String name,GamePanel gp,Player p) {
        super(gp);
        this.gp = gp;
        this.p = p;
        this.name = name;
        String link = "resources/gameSprites/swifty.png";
        super.loadImage(link); 
        t = new Thread(this);
    }
    public void Boost(){
        p.boostSpeed(4);
    }
    public void endBoost(){
        p.boostSpeed(2);
    }
    @Override
    public void run() {
        super.used = true;
        while(super.pickedUp){
            long time = System.currentTimeMillis();
            long end = time +3000;
            while(System.currentTimeMillis()<= end) {
                Boost();                  
            }
        endBoost();
        super.pickedUp=false;
        }
    }  
    public void start(){
        t.start();
    }
}
