/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author laurensdierickx
 */
public interface Enemy{
    public void createBoundries();
    public void loadImage();   

    public void draw(Graphics g);
    public double getX();
    public double getY();
    public Image getImage();
    public int getWidth();
    public int getHeight();
    public Rectangle getBounds();
    public boolean getStatus();
    public void die();
    
}
