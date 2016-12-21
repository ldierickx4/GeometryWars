/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Controllers;

import AstralStrifes.Bullet;
import AstralStrifes.Enemy.Enemy;
import AstralStrifes.Player;
import AstralStrifes.Enemy.SaturnEnemy;
import AstralStrifes.Enemy.ShootingEnemy;
import PresentationLayer.GamePanel;
import PresentationLayer.SingleGamePanel;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author laurensdierickx
 */
public class EnemyBulletController implements Runnable {
    private GamePanel gp;
    private LinkedList<Bullet> bullets;
    private LinkedList<Player> players;
    private Thread thread;
    private Random r = new Random();
    
    public EnemyBulletController(LinkedList<Player> players, GamePanel gp)
    {   
        this.bullets = new LinkedList<Bullet>();
        this.players = players;
        this.gp = gp;
        this.thread = new Thread(this);        
        thread.start();
    }
    public void addBullet(Bullet b){
        bullets.add(b);
    }
    public void update(){
        Bullet tempBullet;
        for(int i=0;i<bullets.size();i++){
            tempBullet = bullets.get(i);
            tempBullet.calcBulletPos();
            if(!(tempBullet.checkAlive()))
            {
                bullets.remove(tempBullet);
            }            
        }
    }
    public void render(Graphics g){
        Bullet tempBullet;
        for(int i=0;i<bullets.size();i++){
            tempBullet = bullets.get(i);     
            tempBullet.draw(g);
        }
    }
    @Override
    public void run() {
        while(true)
        {            
            try {
		Thread.sleep(1000);
		}
            catch(Exception e) {
		e.printStackTrace();
		}
            updateEnemyBullets();
        }
    }
    public LinkedList<Bullet> giveBullets(){
        return this.bullets;    
    }
    public Player getRandomPlayer(){
        int ran = 0;
        int aantal = players.size();
        if(aantal>1){
            ran = r.nextInt(aantal);
        }
        Player p = players.get(ran);
        return p;
    }
    public void updateEnemyBullets()
    {   
        Player p = getRandomPlayer();
        LinkedList<Enemy> enemy = gp.getEc().giveEnemies();
        for(int i=0; i<enemy.size();i++)
        {
            Enemy e = enemy.get(i);
            if(e.getType().equals("shooting"))
            {
                ShootingEnemy sE = (ShootingEnemy) e;
                Bullet b = new Bullet(sE.getCenterX(),sE.getCenterY(), p.getx(), p.gety(), "enemy");
                addBullet(b);
            }
            else if(e.getType().equals("saturn")){
                SaturnEnemy sE = (SaturnEnemy) e;
                Bullet b = new Bullet(sE.getCenterX(),sE.getCenterY(), sE.randomInt(), sE.randomInt(), "senemy");
                addBullet(b);
            }
        }
        
    }
   
}
