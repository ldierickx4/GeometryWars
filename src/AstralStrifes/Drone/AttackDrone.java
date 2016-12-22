/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Drone;

import AstralStrifes.Bullet;
import AstralStrifes.Enemy.Enemy;
import AstralStrifes.Player;
import PresentationLayer.GamePanel;
import PresentationLayer.SingleGamePanel;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author laurensdierickx
 */
public class AttackDrone extends Drone implements Runnable{
    private Thread thread;
    private LinkedList<Bullet> bullets;
    private GamePanel gp;
    public AttackDrone(Player p ,GamePanel gp) {
        super(p);
        this.gp=gp;
        gp.setAttackdrone();
        this.thread = new Thread(this);
        String link= "resources/gameSprites/attack1.png";
        super.loadImage(link);
        bullets = new LinkedList<Bullet>();
        thread.start();
    }
    public void power(){
        Enemy e = gp.getEc().getRandomEnemy();
        System.out.println(e);
        if(e!=null){
            Bullet b = new Bullet(super.getX(), super.getY(), e.getX(), e.getY(), "drone");
            bullets.add(b);
        }
    }
    public void renderBullets(Graphics g){
        int size=bullets.size();
        if(size>0){
            for(int i=0;i<size;i++){
                bullets.get(i).draw(g);
            }
        }
    }
    @Override
    public void run() {
        while(true){
            try {
                thread.sleep(1000);
                this.power();
            } catch (InterruptedException ex) {
                ex.getStackTrace();
            }
        }
    }
    public LinkedList<Bullet> getBullets(){
        return this.bullets;
    }
    
    
}
