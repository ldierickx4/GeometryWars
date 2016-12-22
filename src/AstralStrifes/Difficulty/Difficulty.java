/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes.Difficulty;

import Data.Database;

/**
 *
 * @author laurensdierickx
 */
public class Difficulty {
    private int difficulty;
    private Database db;
    public Difficulty(int difficulty){
        db = new Database();
        this.difficulty = difficulty;
    }
    
    public int getEnemySpawnSpeed(){
       return db.getDifficultySpawnSpeed(difficulty);
    }
    public int getPlayerBulletSpeed(){
        return db.getDifficultyPlShootSpeed(difficulty);
    }
    public int getAdhdPower(){
        return db.getDifficultyAdhdPower(difficulty);
    }
    public int getSwiftyPower(){
        return db.getDifficultySwiftyDuration(difficulty);
    }
    public double getNormalEnemyMovement(){
        return (double)(db.getDifficultyNESpeed(difficulty));
    }
    public int getNormalEnemySccore(){
        return db.getDifficultyNEScore(difficulty);
    }
    public int getNormalEnemymultiplier(){
        return 3;
    }
    
    public int getSaturnEnemyMovement(){
        return 3;
    }
    public int getShootingEnemyShootingSpeed(){
        return 3;
    }
}
