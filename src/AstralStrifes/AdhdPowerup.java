/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

<<<<<<< HEAD
import PresentationLayer.GamePanel;
=======
import AstralStrifes.Controllers.PlayerBulletController;
import PresentationLayer.GamePanel;
import PresentationLayer.SingleGamePanel;
>>>>>>> origin/master
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jordy
 */
<<<<<<< HEAD
public class AdhdPowerup extends Powerup{
        public AdhdPowerup(String name, GamePanel gp) {
        super(gp);
        this.name = name;
        String link = "resources/gameSprites/adhd.png";
        super.loadImage(link);
=======

public class AdhdPowerup extends Powerup implements Runnable{
    
    private Thread t;
    private GamePanel gp;
    private PlayerBulletController pbc;
       
    public AdhdPowerup(String name, GamePanel gp) {
    super(gp);
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
        //System.out.println("Test");
        pbc = gp.getBulletControler();
        System.out.println("PBC: " + pbc);
        pbc.boostFiringRate();
    }
    
    public void endBoost(){
        pbc = gp.getBulletControler();
        pbc.resetFiringRate();
>>>>>>> origin/master
    }
    
}
