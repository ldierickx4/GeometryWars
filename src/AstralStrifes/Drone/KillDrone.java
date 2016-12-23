/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Drone;

import AstralStrifes.Controllers.EnemyController;
import AstralStrifes.Player;
import PresentationLayer.GamePanel;
import PresentationLayer.SingleGamePanel;

/**
 *
 * @author laurensdierickx
 */
public class KillDrone extends Drone implements Runnable{
    private Thread thread;
    private EnemyController ec;
    
    public KillDrone(Player p,GamePanel gp) {
        super(p,10000);
        this.ec = gp.getEc();
        String link = "resources/gameSprites/attack2.png";
        super.loadImage(link);
        this.thread = new Thread(this);
        thread.start();
    }
    public void power(){
        ec.killRandomEnemy();
    }
    @Override
    public void run() {
        while(true){
            try {
                thread.sleep(super.upgrade);
                power();
            } catch (InterruptedException ex) {
                ex.getStackTrace();
            }
        }
    }
    
}
