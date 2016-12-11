/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;
import AstralStrifes.NormalEnemy;
import AstralStrifes.*;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author laurensdierickx
 */
public class ColissionController {
    private Player p;
    private LinkedList<Bullet> bullets;
    private LinkedList<Enemy> enemy;
    private EnemyController ec;
    
    
    public ColissionController(Player p,LinkedList<Bullet> bullets,EnemyController ec)
    {
        this.p = p;
        this.bullets = bullets;
        this.enemy = enemy;
        this.ec = ec;
    }
    public void CheckEnemyBulletCoulission()
    {
        this.enemy = ec.giveEnemies();
        for(int i=0;i<enemy.size();i++){
            Enemy tempe = enemy.get(i);
            Rectangle enemyR = tempe.getBounds();
            for(int index = 0; index<bullets.size();index++){
                Rectangle tempb = bullets.get(index).getBorders();
                if(enemyR.intersects(tempb)){
                    p.raiseScore(tempe.getValue());
                    p.reduceHealth(10);
                    tempe.die();
                    ec.removeEnemy(tempe);
                }
            }
        }   
    }
    
  
}
