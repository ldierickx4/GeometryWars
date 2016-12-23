/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Controllers;

import AstralStrifes.PowerUp.AdhdPowerup;
import AstralStrifes.Player;
import AstralStrifes.PowerUp.Powerup;
import AstralStrifes.PowerUp.SwiftyPowerup;
import PresentationLayer.GamePanel;
import PresentationLayer.SingleGamePanel;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Jordy
 */
public class PowerupController{
    private Player player;
    private LinkedList<Powerup> powerups;
    private LinkedList<Powerup> usedPowers;
    private PlayerBulletController pbc;
    private GamePanel gp;
    private Thread t;
    private boolean spacebar = false;
    public boolean added=false;
    public boolean addedhd=false;

    public PowerupController(Player player,PlayerBulletController pbc,GamePanel gp) {
        this.powerups = new LinkedList<Powerup>();
        this.usedPowers = new LinkedList<Powerup>();
        this.player = player;
        this.gp=gp;
        this.pbc=pbc;
    }
    public void addUsed(Powerup p){
        usedPowers.add(p);
    }
    public void removePowerup(Powerup p){
        usedPowers.remove(p);
    }
    public void updatePowerups(){
        int kills = player.getManna().size();
        checkforPowerup(kills, 10);
    }
    public void checkforPowerup(int kills,int killcount){
        if(kills%killcount == 0 && kills!=0){
            SwiftyPowerup spowerup = new SwiftyPowerup(player,gp);
            powerups.add(spowerup);
            added = true;
        }
    }
    public void useADHD(){
        if(player.getAmountOfAdhdPowerups()!=0){
            player.reduceAdhd();
            AdhdPowerup powerup = new AdhdPowerup(pbc,gp);
            powerups.add(powerup);
            powerup.start();
            powerups.clear();
        }    
    }
    
    public void checkForPOwerUp(){
        for(int i=0;i<usedPowers.size();i++){
            Powerup pu = usedPowers.get(i);
            if(pu.getPickedUp()){
                try{
                    SwiftyPowerup sp = (SwiftyPowerup)(pu);
                    sp.start();
                }
                catch(Exception ex){
                }
            }
        }
    }
    public LinkedList<Powerup> getPowerups(){
        return this.powerups;
    }
    public void clear(){
        powerups.clear();
    }
    
    public void draw(Graphics g){
        if(powerups.size()>0){
            for(Powerup p : powerups){
                p.draw(g);
            }
        } 
    }
    
    public void removePowerups(Powerup p){
        powerups.remove(p);
    }
}
