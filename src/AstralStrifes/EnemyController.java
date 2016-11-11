/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

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
        
    public EnemyController(Player p) {
        this.enemies = new LinkedList<Enemy>();
        thread = new Thread(this);
        thread.start();
        this.p = p;
    }
    
    public void addEnemy(Enemy e){
        enemies.add(e);
    }
     public void update(){
        Enemy tempEnemy;
        for(int i=0; i<enemies.size();i++){
            tempEnemy = enemies.get(i);
            tempEnemy.moveTo(p.getx()-14,p.gety()-15);
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
        Enemy toAddenemy = new Enemy();
        addEnemy(toAddenemy);
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        while(current == thread)
        {            
            try {
		Thread.sleep(2000);
		}
            catch(Exception e) {
		e.printStackTrace();
		}
            makeNewEnemy();
        }
    }


    
}
