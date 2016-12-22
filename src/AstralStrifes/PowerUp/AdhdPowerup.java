/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.PowerUp;

import PresentationLayer.GamePanel;
import AstralStrifes.Controllers.PlayerBulletController;
import PresentationLayer.GamePanel;
import PresentationLayer.SingleGamePanel;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jordy
 */
public class AdhdPowerup extends Powerup implements Runnable{
    private int boost;
    private Thread t;
    private GamePanel gp;
    private PlayerBulletController pbc;
     
    public AdhdPowerup(String name, GamePanel gp) {
        super(gp);
        this.gp=gp;
        this.pbc = pbc;
        this.boost=gp.getDiff().getAdhd();
        this.name = name;
        String link = "resources/gameSprites/adhd.png";
        super.loadImage(link);
        t = new Thread(this);
    
}

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        long end = time +3000;
        while(System.currentTimeMillis()<= end) {
            boostGun();                          
        }
        endBoost();
    }
    
    public void start(){
        t.start();
    }
    
    public void boostGun(){
        pbc = gp.getBulletControler();
        pbc.boostFiringRate(this.boost);
    }
    
    public void endBoost(){
        pbc = gp.getBulletControler();
        pbc.resetFiringRate();
    }
    
}
