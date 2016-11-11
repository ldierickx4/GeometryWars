/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Jens
 */
public class Background {
    private BufferedImage background;
    private int width;
    private int height;

    public Background() {
        this.width = 900;
        this.height = 900;
        loadImage();
    }
    
    public void loadImage(){
        BufferedImage i = null;
        try {
            //ImageIcon ii =  new ImageIcon("resources/gameSprites/warship.png"); // change the path & folder
            i = ImageIO.read(new File("resources/gameSprites/backgroundspace.gif"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        background = i;
        
    }

    public Image getBackground() {
        return background;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
    
    
    
}
