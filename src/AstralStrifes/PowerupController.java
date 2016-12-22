/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

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
    private GamePanel gp;

    public PowerupController(Player player, GamePanel gp) {
        this.powerups = new LinkedList<Powerup>();
        this.player = player;
        this.gp = gp;
    }
    
    public void updatePowerups(){
        score = player.getScore();
        if(score%150 == 0 && score!=0 && powerups.size() == 0){
            SwiftyPowerup powerup = new SwiftyPowerup("Swifty", gp);
            powerups.add(powerup);
        }
    }

    public LinkedList<Powerup> getPowerups(){
        return this.powerups;
    }
    
    public void draw(Graphics g){
        if(powerups.size() > 0){
            for(Powerup p : powerups){
                p.draw(g);
            }
        } 
    }
    
    public void clearPowerups(){
        powerups.clear();
    }   
    
}
