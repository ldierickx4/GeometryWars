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
    private String SPACESHIP_GUN_FIRE_PATH = "spaceship";
    private String ENEMY_RAINBOW_FIRE_PATH = "rainbow";
    private String ENEMY_SATURN_FIRE_PATH = "";
    private String ENEMY_KILLED_PATH = "killed2";
    private String POWERUP_ACTIVATED = "powerup";
    private String MANNA_PICKED_UP = "manna";
    private String STANDARD_ENEMY_FIRE_PATH = "spaceship";
    private AudioClip shot;
    private String KOEKOEK_JONGUH = "koekoek";
    

    public SoundLoader(String soundLoaderName) {
        makeSound(soundLoaderName);    
    }

    public void loadSound(String sound){
        try {
            URL url = new URL("file:resources/gameSounds/" + sound + ".wav");
            shot = Applet.newAudioClip(url);

        } catch (MalformedURLException ex) {
            Logger.getLogger(SoundLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSoundPath(String name) {
        switch(name){
            case "Spaceship" :
                return SPACESHIP_GUN_FIRE_PATH;
            case "Rainbow" :
                return ENEMY_RAINBOW_FIRE_PATH;
            case "Saturn" :
                return ENEMY_SATURN_FIRE_PATH;
            case "Enemy" :
                return STANDARD_ENEMY_FIRE_PATH;
            case "Killed" :
                return ENEMY_KILLED_PATH;
            case "Powerup" :
                return POWERUP_ACTIVATED;
            case "Koekoek" :
                return KOEKOEK_JONGUH;
            case "Manna" : 
                return MANNA_PICKED_UP;
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
