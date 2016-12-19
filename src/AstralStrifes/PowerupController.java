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
    private LinkedList<Powerup> powerups= new LinkedList<Powerup>();
    private GamePanel gp;

    public PowerupController(Player player, GamePanel gp) {
        this.player = player;
        this.gp = gp;
    }
    
    public void updatePowerups(){
        score = player.getScore();
        if(score%150 == 0 && score!=0 &&powerups.size()==0){
            Powerup powerup = new SwiftyPowerup();
            powerups.add(powerup);
            
        }
        if(score%20000 == 0 && score!=0&&powerups.size()==0){
            Powerup powerup = new AdhdPowerup();
            powerups.add(powerup);
            System.out.println("ADHD");
        }
    }
    
    public void draw(Graphics g){
        if(powerups.size() > 0){
            for(Powerup p : powerups){
            p.draw(g);
            }
        } 
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
