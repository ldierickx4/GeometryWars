/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Sounds;

/**
 *
 * @author Jordy
 */
public class Sound {
    private String name;

    public Sound(String name) {
        this.name = name;
        SoundLoader sl = new SoundLoader(name);
        String soundPath = sl.getSoundPath(name);
        sl.loadSound(soundPath);
    }
}
