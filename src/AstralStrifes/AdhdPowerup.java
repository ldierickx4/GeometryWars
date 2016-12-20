/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.SingleGamePanel;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jordy
 */
public class AdhdPowerup extends Powerup{
        public AdhdPowerup(String name, SingleGamePanel gp) {
        super(gp);
        this.name = name;
        String link = "resources/gameSprites/adhd.png";
        super.loadImage(link);
    }
    
}
