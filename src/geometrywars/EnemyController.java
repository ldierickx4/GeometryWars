/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Jens
 */
public class EnemyController {
    
    private LinkedList<Enemy> enemies;
    private Enemy enemy;

    public EnemyController(Enemy e) {
        this.enemies = new LinkedList<Enemy>();
        this.enemy = e;
    }
    
    public void addEnemy(Enemy e){
        enemies.add(e);
    }
     public void update(){
        Enemy tempEnemy;
        for(int i=0; i<enemies.size();i++){
            tempEnemy = enemies.get(i);
            tempEnemy.moveTo(123,150);
        }
    }
    public void render(Graphics g){
        Enemy tempEnemy;
        for(int i=0; i < enemies.size(); i++){
            tempEnemy = enemies.get(i);
            tempEnemy.draw(g);
        }
    }
    
    
}
