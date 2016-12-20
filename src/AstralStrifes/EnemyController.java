/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.GamePanel;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;


/**
 *
 * @author Jens
 */
public class EnemyController implements Runnable{
    
    private LinkedList<Enemy> enemies;
    private LinkedList<Manna> manna;
    private Enemy enemy;
    private Thread thread;
    private Player p;
    private int count;
    private GamePanel gp;
        
    public EnemyController(Player p , GamePanel gp) {
        this.enemies = new LinkedList<Enemy>();
        this.manna = new LinkedList<Manna>();
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
            try {
                SaturnEnemy se = (SaturnEnemy) tempEnemy;
                se.slide();
            } catch (Exception e) {
            }
            
        }
    }
    public void render(Graphics g){
        Enemy tempEnemy;
        for(Enemy e: enemies){
            e.draw(g);
        }
        for(Manna m:manna){
            m.draw(g);
        }
    }
    public void makeNewEnemy()
    {
        
        Enemy toAddenemy = new NormalEnemy();
        if(count%2==0&&count!=0){
             toAddenemy = new SaturnEnemy();
        }
        else if(count%5==0&&count!=0){
            toAddenemy = new ShootingEnemy(p, gp.getBulletControler());
        }
        addEnemy(toAddenemy);
        this.count++;
    }
    @Override
    public void run() {
        Thread current = Thread.currentThread();
        while(current == thread)
        {            
            try {
		Thread.sleep(2500);
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
    public void killRandomEnemy(){
        int bounds = enemies.size();
            if(bounds > 0){
            Random r = new Random();    
            int rE = r.nextInt(enemies.size());
            Enemy e = enemies.get(rE);
            if(e!=null){
                removeEnemy(e);
            }
        }
    }
    public Enemy getRandomEnemy(){
        Enemy e = null;
        if(enemies.size()>0){
            Random r = new Random();
            int rE = r.nextInt(enemies.size());
            e = enemies.get(rE);   
        }  
        return e; 
    }
    
    public void addManna(Manna m)
    {
        manna.add(m);
    }
    public void removeEnemy(Enemy enemy)
    {
        enemies.remove(enemy);
    }
    public LinkedList<Manna> giveManna(){
        return this.manna;
    }

    public void removeManna(Manna m) {
        p.addManna(m);
        manna.remove(m);
    }
    public void removeAllManna(){
        for(int i = 0;i<manna.size();i++){
            Manna m = manna.remove(i);
            p.addManna(m);
        }
    }
}
