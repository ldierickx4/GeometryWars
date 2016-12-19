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
public class KillDrone extends Drone implements Runnable{
    private Thread thread;
    private EnemyController ec;
    public KillDrone(Player p,EnemyController ec) {
        super(p);
        this.ec = ec;
        String link="resources/gameSprites/attack2.png";
        super.loadImage(link);
        this.thread = new Thread(this);
        thread.start();
    }
    public void power(){
        System.out.println(ec);
        ec.killRandomEnemy();
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