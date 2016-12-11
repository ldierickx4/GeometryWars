/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.GamePanel;
import java.awt.Graphics;
import java.util.LinkedList;


/**
 *
 * @author Jens
 */
public class EnemyController implements Runnable{
    
    private LinkedList<Enemy> enemies;
    private Enemy enemy;
    private Thread thread;
    private Player p;
    private int count;
    private GamePanel gp;
        
    public EnemyController(Player p , GamePanel gp) {
        this.enemies = new LinkedList<Enemy>();
        thread = new Thread(this);
        thread.start();
        this.p = p;
        this.gp = gp;
    }
    
    public void addEnemy(Enemy e){
        enemies.add(e);
    }
     public void update(){
        Enemy tempEnemy;
        for(int i=0; i<enemies.size();i++){           
            tempEnemy = enemies.get(i);
            try{
                NormalEnemy ne = (NormalEnemy) tempEnemy;
                ne.moveTo(p.getPlayerCenterX(),p.getPlayerCenterY());
            }
            catch(ClassCastException e)
            {      
                
            }
        }
    }
    public void render(Graphics g){
        Enemy tempEnemy;
        for(int i=0; i < enemies.size(); i++){
            tempEnemy = enemies.get(i);
            tempEnemy.draw(g);
        }
    }
    public void makeNewEnemy()
    {
        
        Enemy toAddenemy = new NormalEnemy();
        if(count%2==0&&count!=0){
            toAddenemy = new ShootingEnemy(p, gp.getBulletControler());
        }
        addEnemy(toAddenemy);
        this.count++;
    }
    public void letShoot(){
        for(Enemy enemie: enemies){
            if(enemie.getType().equals("shooting")){
                ShootingEnemy se =(ShootingEnemy) enemie;
                se.newBullet();
            }
        }
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        while(current == thread)
        {            
            try {
		Thread.sleep(2000);
		}
            catch(InterruptedException e) {
		e.printStackTrace();
		}
            makeNewEnemy();
        }
    }
    public LinkedList<Enemy> giveEnemies(){
        return this.enemies;
    }
    public void removeEnemy(Object enemy)
    {
        enemies.remove(enemy);
    }
}
