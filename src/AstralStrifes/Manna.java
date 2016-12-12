/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author laurensdierickx
 */
public class Manna {
    private int score;
    private int multiplier;
    private BufferedImage image;
    private Rectangle bounds;
    private int x;
    private int y;
    
    public Manna(int score, int multiplier,int x,int y){
        loadImage();
        this.score = score;
        this.multiplier = multiplier;
        this.x = x-image.getWidth()/2;
        this.y = y-image.getHeight()/2;
        this.bounds = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public void loadImage() {
        BufferedImage i = null;
        String path = "resources/gameSprites/manna.png";
        try {
            i = ImageIO.read(new File(path));
        } catch (IOException ex) {
            ex.getMessage();
        }
        image = i;
    }
    public void draw(Graphics g){
        g.drawImage(image, (int)(this.x), (int)(this.y), null);
    }
    public Rectangle getBounds(){
        return this.bounds;
    }
    public int getScore(){
        return this.score;
    }
    
}
