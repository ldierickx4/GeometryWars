/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Jordy
 */
public class swiftyPowerup extends powerup {
    
    public swiftyPowerup(int x, int y) {
        super(x, y);
        String link = "resources/gameSprites/swifty.png";
        super.loadImage(link);
    }
    
    
    
}