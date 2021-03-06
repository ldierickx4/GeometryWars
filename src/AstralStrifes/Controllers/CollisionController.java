/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Controllers;
import AstralStrifes.Bullet;
import AstralStrifes.Enemy.Enemy;
import AstralStrifes.Enemy.Manna;
import AstralStrifes.Player;
import AstralStrifes.PowerUp.Powerup;
import AstralStrifes.Sounds.SoundLoader;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author laurensdierickx
 */
public class CollisionController {
    private Player player;
    private LinkedList<Bullet> bullets;
    private LinkedList<Enemy> enemy;
    private LinkedList<Manna> manna;
    private LinkedList<Enemy> hitEnemies = new LinkedList<Enemy>();
    private EnemyController ec;
    private PlayerBulletController c; 
    private EnemyBulletController ebc;
    private PowerupController pc;
    private LinkedList<Powerup> powerups;
    private SoundLoader killedSound = new SoundLoader("Killed");
    private SoundLoader powerupSound = new SoundLoader("Powerup");
    private SoundLoader mannaSound = new SoundLoader("Manna");
    
    public CollisionController(Player p,PlayerBulletController c,EnemyController ec, EnemyBulletController ebc, PowerupController pc)
    {
        this.player = p;
        this.ec = ec;
        this.c = c;
        this.ebc = ebc;
        this.pc = pc;
    }
    
public void checkIfPowerupGetsPickedUp(){
        if(!(pc.getPowerups()).isEmpty()){
            this.powerups = pc.getPowerups();
            for(int i = 0; i< powerups.size(); i++){
                Powerup powerup = powerups.get(i);
                Rectangle powerupBounds = powerups.get(i).getBounds();
                if(powerupBounds.intersects(player.getBounds())){       
                    (powerupSound.getSound()).play();
                    powerup.setPickedUp();
                    pc.addUsed(powerup);
                    pc.clear();
                }
            }
        }    
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
                    (killedSound.getSound()).play();
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
            if(bulletBounds.intersects(player.getBounds())){
                bullets.get(i).setDead();
                player.resetMultiplier();
                player.reduceHealth(ebc.getDamage());
            }
        }
    }
    public void checkEnemyPlayerCollision(){
        this.enemy = ec.giveEnemies();
        for(int i=0;i<enemy.size();i++)
        {
            Enemy tempE = enemy.get(i);
            Rectangle enemyR = tempE.getBounds();
            if(enemyR.intersects(player.getBounds()))
            {
                if(!hitEnemies.contains(tempE)){
                    (killedSound.getSound()).play();
                    hitEnemies.add(tempE);
                    player.reduceHealth(tempE.getDamage());
                    player.resetMultiplier();
                    ec.removeEnemy(tempE);
                }
            }
        }
    }
    
    public void checkPlayerMannaPickup()
    {
        this.manna = ec.giveManna();
        Rectangle playerR = player.getBounds();
        for(int i=0;i<manna.size();i++){
            try {
                Manna m = manna.get(i);
                Rectangle mannaR =m.getBounds();
                if(playerR.intersects(mannaR)){
                    (mannaSound.getSound()).play();
                    Manna ma = ec.removeManna(m);
                    player.addManna(ma);
                    
                }
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            } 
        }
    }
}
