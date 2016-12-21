/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Controllers;

import AstralStrifes.AdhdPowerup;
import AstralStrifes.Player;
import AstralStrifes.Powerup;
import AstralStrifes.SwiftyPowerup;
import PresentationLayer.GamePanel;
import PresentationLayer.MultiGamePanel;
import PresentationLayer.SingleGamePanel;
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
    private PlayerBulletController pbc;
    private GamePanel gp;
    private Thread t;
    private boolean spacebar = false;

    public PowerupController(Player player, GamePanel gp) {
        this.powerups = new LinkedList<Powerup>();
        this.usedPowers = new LinkedList<Powerup>();
        this.player = player;
        this.gp = gp;        
        getPbc();
    }
    public void addUsed(Powerup p){
        usedPowers.add(p);
    }
    public void removePowerup(Powerup p){
        usedPowers.remove(p);
    }
    
    public void updatePowerups(){
        score = player.getScore();
        if(score%150 == 0 && score!=0 && powerups.size() == 0){
            SwiftyPowerup powerup = new SwiftyPowerup("Swifty", gp,player);
            powerups.add(powerup);
        }   
    }
    public void getPbc(){
        this.pbc = gp.getBulletControler();
        if(player.getPlayerStatus()==2){
            MultiGamePanel mg = (MultiGamePanel)(gp);
            this.pbc = mg.getBulletControler2();
        }
    }
    public void useADHD(){
        if(player.getAmountOfAdhdPowerups() != 0){
            AdhdPowerup powerup = new AdhdPowerup("ADHD" ,gp,pbc);
            powerups.add(powerup);
            powerup.start();
            player.reduceAdhd();
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
                    //System.out.println(ex.getMessage());
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
