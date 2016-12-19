/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jordy
 */
public class adhdPowerup extends powerup{
    
    public adhdPowerup(int x, int y) {
        super(x, y);
        String link = "resources/gameSprites/adhd.png";
        super.loadImage(link);
    }
    
}
