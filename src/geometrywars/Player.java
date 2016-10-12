/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Laurens
 */
public class Player {
    private String naam;
    private int y;
    private int x;
    private Image image;
    public Player(){
        this.x = 170;
        this.y = 560;
        loadImage();
    }

    private void loadImage() {
        ImageIcon ii =  new ImageIcon("warship.png");
        image = ii.getImage();
    }
    public void moveUp()
    {
        this.y++;
    }
    public void moveDown()
    {
        this.y--;
    }
    public void moveLeft()
    {
        this.x++;
    }
    public void moveRight()
    {
        this.x--;
    }
    
    
    
}
