/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Controllers;

import AstralStrifes.Player;
import AstralStrifes.Powerup;
import AstralStrifes.SwiftyPowerup;
import PresentationLayer.GamePanel;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Jordy
 */
public class PowerupController{
    private int score;
    private Player player;
    private LinkedList<Powerup> powerups;
    private LinkedList<Powerup> usedPowers;
    private GamePanel gp;
    private Thread t;

    public PowerupController(Player player, GamePanel gp) {
        this.powerups = new LinkedList<Powerup>();
        this.usedPowers = new LinkedList<Powerup>();
        this.player = player;
        this.gp = gp;
    }
    public void addUsed(Powerup p){
        usedPowers.add(p);
    }
    public void removePowerup(Powerup p){
        usedPowers.remove(p);
    }
    
    public void updatePowerups(){
        score = player.getScore();
        if(score%3000 == 0 && score!=0 && powerups.size() == 0){
            SwiftyPowerup powerup = new SwiftyPowerup("Swifty", gp);
            powerups.add(powerup);
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
        if(powerups.size() > 0){
            for(Powerup p : powerups){
                p.draw(g);
            }
        } 
    }
    
    public void removePowerups(Powerup p){
        powerups.remove(p);
    }
}
