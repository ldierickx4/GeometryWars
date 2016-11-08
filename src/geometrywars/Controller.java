/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

/**
 *
 * @author laurensdierickx
 */
public class Controller {
    private LinkedList<Bullet> bullets;
    private Player player;
    
    public Controller(Player player)
    {
        this.bullets = new LinkedList<Bullet>();
        this.player = player;
    }
    public void addBullet(Bullet b){
        bullets.add(b);
    }
    public void update(){
        Bullet tempBullet;
        for(int i=0;i<bullets.size();i++){
            tempBullet = bullets.get(i);
            tempBullet.calculateXvelo();
        }
    }
    public void render(Graphics g){
        Bullet tempBullet;
        for(int i=0;i<bullets.size();i++){
            tempBullet = bullets.get(i);     
            
            
            tempBullet.draw(g);
        }
    }
    
}
