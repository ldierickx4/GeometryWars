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
    private EnemyBulletController ebc;
    public CollisionController(Player p,PlayerBulletController c,EnemyController ec, EnemyBulletController ebc)
    {
        this.p = p;
        this.ec = ec;
        this.c = c;
        this.ebc = ebc;
    }
    public void checkEnemyBulletCoulission(LinkedList<Bullet> bullets)
    {
        this.enemy = ec.giveEnemies();
        for(int i=0;i<enemy.size();i++){
            Enemy tempe = enemy.get(i);
            Rectangle enemyR = tempe.getBounds();
            for(int index = 0; index<bullets.size();index++){
                Rectangle tempb = bullets.get(index).getBorders();
                if(enemyR.intersects(tempb)){
                    ec.addManna(tempe.getManna());
                    ec.removeEnemy(tempe);
                    bullets.get(index).setDead();
                }
            }
        }    
    }
    
    public void checkIfPlayerGetsHitByEnemyBullet(){
        this.bullets = ebc.giveBullets();
        for(int i = 0; i<bullets.size(); i++){
            Bullet temp = bullets.get(i);
            Rectangle bulletBounds = temp.getBorders();
            if(bulletBounds.intersects(p.getBounds())){
                bullets.get(i).setDead();
                p.reduceHealth(5);
                System.out.println("Hit!"); 
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
                if(!hitEnemies.contains(tempE)){
                    hitEnemies.add(tempE);
                    p.reduceHealth(10);
                    ec.removeEnemy(tempE);
                }
            }
        }
    
    }
    public void checkPlayerMannaPickup()
    {
        this.manna = ec.giveManna();
        Rectangle playerR = p.getBounds();
        for(int i=0;i<manna.size();i++){
            try {
                Manna m = manna.get(i);
                Rectangle mannaR =m.getBounds();
                if(playerR.intersects(mannaR)){
                ec.removeManna(m);
                }
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
            
        }
    }
    public void checkDroneEnemy(){
        
    }
}
