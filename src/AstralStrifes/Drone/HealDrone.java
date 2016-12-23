/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Drone;

import AstralStrifes.Player;

/**
 *
 * @author laurensdierickx
 */
public class HealDrone extends Drone implements Runnable{
    
    private Thread thread;
    public HealDrone(Player p) {
        super(p,10000);
        String link= "resources/gameSprites/healdrone.png";
        super.loadImage(link);
        thread = new Thread(this);
        thread.start();
    }
    public void power(){
        if(super.p.getHealth()<100){
            super.p.heal(10);
        }
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
