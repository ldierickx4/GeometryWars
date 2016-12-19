/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

/**
 *
 * @author laurensdierickx
 */
public class HealDrone extends Drone implements Runnable{
    
    private Thread thread;
    public HealDrone(Player p) {
        super(p);
        String link= "resources/gameSprites/healdrone.png";
        super.loadImage(link);
        thread = new Thread(this);
        thread.start();
    }
    public void power(){
       super.p.heal(10);
    }

    @Override
    public void run() {
        while(true){
            try {
                thread.sleep(10000);
                power();
            } catch (InterruptedException ex) {
                ex.getStackTrace();
            }
        }
    }
    
}
