/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;
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
    private LinkedList<Manna> manna;
    private LinkedList<Enemy> hitEnemies = new LinkedList<Enemy>();
    private EnemyController ec;
    private PlayerBulletController c; 
    public CollisionController(Player p,PlayerBulletController c,EnemyController ec)
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
                    tempe.die();
                    ec.addManna(tempe.getManna());
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
            //System.out.println(enemyR.intersects(p.getBounds()));
            if(enemyR.intersects(p.getBounds()))
            {
                if(!hitEnemies.contains(tempE)){
                    hitEnemies.add(tempE);
                    p.reduceHealth(10);
                    System.out.println(p.updateHealth());
                }
                //System.out.println("Leven kwijt");
            } else {
                //hitEnemies = new LinkedList<Enemy>();
                //System.out.println(intersect);
            }
        }
    
    }
    public void checkPlayerMannaPickup()
    {
        this.manna = ec.giveManna();
        Rectangle playerR = p.getBounds();
        for(int i=0;i<manna.size();i++){
            Manna m = manna.get(i);
            Rectangle mannaR =m.getBounds();
            if(playerR.intersects(mannaR)){
                ec.removeManna(m);
            }
        }
    }
}
