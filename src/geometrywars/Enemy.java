/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import PresentationLayer.GamePanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Jens
 */
public class Enemy {
    private static final int SPEED = 1;
    private BufferedImage image;
    private Graphics g;
    private int x;
    private int y;
    private int width;
    private int height;
    private int rangeMin = 0;
    private int rangeMax = 900;
    //private int reward;
    //private int multiplier;
    //private Player player;
    private GamePanel gp;
    

    public Enemy() {
        Random r = new Random(); 
        this.x = rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
        this.y = rangeMin + r.nextInt( rangeMax - rangeMin + 1 );
        this.width = 30;
        this.height = 30;
        loadImage();
    }
    
    
    public void loadImage(){
        BufferedImage i = null;
        try {
            //ImageIcon ii =  new ImageIcon("resources/gameSprites/warship.png"); // change the path & folder
            i = ImageIO.read(new File("resources/gameSprites/enemy_4.png"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        image = i;
        
    }
    
    public void moveTo(int x, int y){
        int MoveToX = x;
        int MoveToY = y;

        float distance = (float) Math.sqrt(Math.pow(MoveToX - x, 2) + Math.pow(MoveToY - y, 2));

        float amountToMoveX = (((MoveToX - x) / distance) * SPEED);
        float amountToMoveY = (((MoveToY - y) / distance) * SPEED);

        this.x += amountToMoveX;
        this.y += amountToMoveY;
    }
    
    public void draw(Graphics g){
        g.drawImage(image, x, y, width, height, gp);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

   
    
    
}
