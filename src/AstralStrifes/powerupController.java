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
public class powerupController implements Runnable{
    private int score;
    private Player player;
    private powerup powerup;
    private LinkedList<powerup> powerups= new LinkedList<powerup>();
    private GamePanel gp;

    public powerupController(Player player, GamePanel gp) {
        this.player = player;
        this.gp = gp;
    }
    
    public void updatePowerups(){
        score = player.getScore();
        if(score%1 == 0 && score!=0){
            System.out.println("Swifty");
            powerup = new swiftyPowerup(500,500);
            powerups.add(powerup);
            
        }
        if(score%20000 == 0 && score!=0){
            powerup = new adhdPowerup(500,500);
            powerups.add(powerup);
            System.out.println("ADHD");
        }
    }
    
    public void draw(Graphics g){
        if(powerups.size() > 0){
            for(powerup p : powerups){
            p.draw(g);
            }
        } 
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
