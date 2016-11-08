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
    

    public EnemyController() {
        this.enemies = new LinkedList<Enemy>();
    }
    
    public void addEnemy(Enemy e){
        enemies.add(e);
    }
    
    public void render(Graphics g){
        Enemy tempEnemy;
        for(int i=0 ;i < enemies.size();i++){
            tempEnemy = enemies.get(i);
            tempEnemy.draw(g);
        }
    }
    
    
}
