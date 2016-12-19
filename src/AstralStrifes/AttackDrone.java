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
public class AttackDrone extends Drone implements Runnable{
    public Thread thread; 
    public AttackDrone(Player p) {
        super(p);
        this.thread = new Thread(this);
        String link= "resources/gameSprites/attack2.png";
        super.loadImage(link);
        thread.start();
    }
    public void power(){
    
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
