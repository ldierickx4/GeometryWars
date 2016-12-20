/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.SingleGamePanel;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jordy
 */
<<<<<<< HEAD
public class AdhdPowerup extends Powerup implements Runnable{
        public AdhdPowerup(String name, GamePanel gp) {
=======
public class AdhdPowerup extends Powerup{
        public AdhdPowerup(String name, SingleGamePanel gp) {
>>>>>>> origin/master
        super(gp);
        this.name = name;
        String link = "resources/gameSprites/adhd.png";
        super.loadImage(link);
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        long end = time +3000;
        while(System.currentTimeMillis()<= end) {
                System.out.println("ADHD JONGUH");              
        }
    }
    
}
