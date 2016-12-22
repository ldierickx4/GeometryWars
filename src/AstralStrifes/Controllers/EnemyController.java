/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Controllers;

import AstralStrifes.Enemy.Enemy;
import AstralStrifes.Enemy.Manna;
import AstralStrifes.Enemy.NormalEnemy;
import AstralStrifes.Player;
import AstralStrifes.Enemy.SaturnEnemy;
import AstralStrifes.Enemy.ShootingEnemy;
import Data.Database;
import PresentationLayer.GamePanel;
import PresentationLayer.SingleGamePanel;
import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
/**
 *
 * @author Jens
 */
public class EnemyController implements Runnable{
    private LinkedList<Enemy> toAddEnemies;
    private LinkedList<Enemy> enemies;
    private LinkedList<Manna> manna;
    private Enemy enemy;
    private Thread thread;
    private Player p;
    private int count;
    private int wave=1;
    private GamePanel gp;
    private Database db;
        
    public EnemyController(Player p ,GamePanel gp) {
        this.db = new Database();
        this.enemies = new LinkedList<Enemy>();
        this.manna = new LinkedList<Manna>();
        thread = new Thread(this);
        thread.start();
        this.p = p;
        this.gp = gp;
        generateEnemies();
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
    public void generateEnemies(){
        LinkedList<String> enemies = db.getEnemiesInWave(wave);
        if(enemies!=null){
            LinkedList<Enemy> genEnemies= new LinkedList<Enemy>();
            for(String e:enemies){
                if(e.equals("saturnenemy")){
                    genEnemies.add(new SaturnEnemy(gp));
                }
                else if(e.equals("shootingenemy")){
                    genEnemies.add(new ShootingEnemy(p,gp.getBulletControler(),gp));
                }
                else{
                    genEnemies.add(new NormalEnemy(gp));
                }
            }
            Collections.shuffle(genEnemies);
            this.toAddEnemies = genEnemies;
        }
        else{
            gp.setAttackdrone();
        }
    }
    public void makeNewEnemy()
    {   
        if(toAddEnemies.size()>0){
            enemies.add(toAddEnemies.removeFirst());
            count = toAddEnemies.size();
        }
        else{
            this.wave++;
            generateEnemies();
        }
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
    public int getEnemiesLeft(){
        return this.count;
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

    public Manna removeManna(Manna m) {
        Manna ma = null;
        for(int i = 0;i<manna.size();i++){
            if(manna.get(i)==m){
                ma = manna.remove(i);
            }
        }
        return ma;
    }
    public int getWave(){
        return this.wave;
    }
    public void removeAllManna(){
        for(int i = 0;i<manna.size();i++){
            Manna m = manna.remove(i);
            p.addManna(m);
        }
    }
}
