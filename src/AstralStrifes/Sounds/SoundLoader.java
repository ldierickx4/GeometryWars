/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Sounds;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jordy
 */
public class SoundLoader {
    private String soundLoaderName;
    private String SPACESHIP_GUN_FIRE_PATH = "file:C:/Users/Jordy/Documents/Howest/2TI/Semester 3/Project/GeometryWars/resources/gameSounds/shot3.wav";
    private String ENEMY_STEALTH_FIRE_PATH = "";
    private String ENEMY_RAINBOW_FIRE_PATH = "";
    private String ENEMY_SATURN_FIRE_PATH = "";
    private String STANDARD_ENEMY_FIRE_PATH = "file:C:/Users/Jordy/Documents/Howest/2TI/Semester 3/Project/GeometryWars/resources/gameSounds/shot4.wav";
    private AudioClip shot;
    

    public SoundLoader(String soundLoaderName) {
        makeSound(soundLoaderName);
    }
    
    public void loadSound(String sound){
        try {
            URL url = new URL(sound);
            shot = Applet.newAudioClip(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SoundLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSoundPath(String name) {
        switch(name){
            case "Spaceship" :
                return SPACESHIP_GUN_FIRE_PATH;
            case "Stealth" :
                return ENEMY_STEALTH_FIRE_PATH;
            case "Rainbow" :
                return ENEMY_RAINBOW_FIRE_PATH;
            case "Saturn" :
                return ENEMY_SATURN_FIRE_PATH;
            case "Enemy" :
                return STANDARD_ENEMY_FIRE_PATH;
            default: 
                return SPACESHIP_GUN_FIRE_PATH;
        }
    }
    
    public void makeSound(String soundName){
        String path = getSoundPath(soundName);
        loadSound(path);       
    }
    
    public AudioClip getSound(){
        return this.shot;
    }
}
