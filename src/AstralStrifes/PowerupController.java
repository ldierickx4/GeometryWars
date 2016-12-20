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
public class PowerupController implements Runnable{
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

        if(score%1650 == 0 && score!=0 && powerups.size() == 0){
            Powerup powerup = new SwiftyPowerup("Swifty");
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

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
