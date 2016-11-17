/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;
import geometrywars.*;
import AstralStrifes.*;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author laurensdierickx
 */
public class CollisionController {
    private Player p;
    private LinkedList<Bullet> bullets;
    private LinkedList<Enemy> enemy;
    private EnemyController ec;
    private BulletController c; 
    public CollisionController(Player p,BulletController c,EnemyController ec)
    {
        this.p = p;
        this.ec = ec;
        this.c = c;
    }
    public void CheckEnemyBulletCoulission()
    {
        this.enemy = ec.giveEnemies();
        this.bullets = c.giveBullets();
        for(int i=0;i<enemy.size();i++){
            Enemy tempe = enemy.get(i);
            Rectangle enemyR = tempe.getBounds();
            for(int index = 0; index<bullets.size();index++){
                Rectangle tempb = bullets.get(index).getBorders();
                if(enemyR.intersects(tempb)){
                    p.raiseScore(tempe.getValue());
                    tempe.die();                   
                    ec.removeEnemy(tempe);
                    bullets.get(index).setDead();
                }
            }
        }    
    }
    public void checkEnemyPlayercollision(){
        this.enemy = ec.giveEnemies();
        for(int i=0;i<enemy.size();i++)
        {
            Enemy tempE = enemy.get(i);
            Rectangle enemyR = tempE.getBounds();
            if(enemyR.intersects(p.getBounds()))
            {
                System.out.println("coulission");
            }
        }
    
    }
}
