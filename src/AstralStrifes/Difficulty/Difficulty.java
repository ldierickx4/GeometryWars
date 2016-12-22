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
    private int spawnSpeed;
    private int pbulletSpeed;
    private int adhdPower;
    private int swiftyPower;
    private double neMove;
    private int neScore;
    private int neMultie;
    private double seMove;
    private int seScore;
    private int seMultie;
    private int shshoot;
    private int shScore;
    private int shMultie;
    private int nDamage;
    private int sDamage;
    private int hDamage;
    private int eBdamage;
    
    public Difficulty(int difficulty){
        db = new Database();
        this.difficulty = difficulty;
        setUp();
    }
    public void getEnemySpawnSpeed(){
       this.spawnSpeed= db.getDifficultySpawnSpeed(difficulty);
    }
    public void getPlayerBulletSpeed(){
        this.pbulletSpeed= db.getDifficultyPlShootSpeed(difficulty);
    }
    public void getAdhdPower(){
        this.adhdPower= db.getDifficultyAdhdPower(difficulty);
    }
    public void getSwiftyPower(){
        this.swiftyPower= db.getDifficultySwiftyDuration(difficulty);
    }
    //normal
    public void getNormalEnemyMovement(){
        this.neMove = (double)(db.getDifficultyNESpeed(difficulty));
    }
    public void getNormalEnemyScore(){
        this.neScore= db.getDifficultyNEScore(difficulty);
    }
    public void getNormalEnemymultiplier(){
        this.neMultie= db.getDifficultyNEMulti(difficulty);
    }
    //saturn
    public void getSaturnEnemyscore(){
        this.seScore= db.getDifficultySATScore(difficulty);
    }
    public void getSaturnEnemyMovement(){
        this.seMove= (double)(db.getDifficultySATEMove(difficulty));
    }
    public void getSaturnEnemyMultie(){
        this.seMultie= db.getDifficultySATEMulti(difficulty);
    }
    //shooting    
    public void getShootingEnemyShootingSpeed(){
        this.shshoot = db.getDifficultySEShoot(difficulty);
    }
    public void getShootingEnemyScore(){
        this.shScore = db.getDifficultySEScore(difficulty);
    }
    public void getShootingEnemyMultie(){
        this.shMultie= db.getDifficultySEMulti(difficulty);
    }
    
    public int getSpawnSpeed() {
        return spawnSpeed;
    }

    public int getPbulletSpeed() {
        return pbulletSpeed;
    }

    public double getNeMove() {
        return neMove;
    }

    public int getNeScore() {
        return neScore;
    }

    public int getNeMultie() {
        return neMultie;
    }

    public double getSeMove() {
        return seMove;
    }

    public int getSeScore() {
        return seScore;
    }

    public int getSeMultie() {
        return seMultie;
    }

    public int getShshoot() {
        return shshoot;
    }

    public int getShScore() {
        return shScore;
    }

    public int getShMultie() {
        return shMultie;
    }
    public int getAdhd(){
        return adhdPower;
    }
    public int getSwifty(){
        return swiftyPower;
    }
    
    public int getnDamage() {
        return nDamage;
    }

    public int getsDamage() {
        return sDamage;
    }

    public int gethDamage() {
        return hDamage;
    }
    public void setUp(){
        getEnemySpawnSpeed();
        getNormalEnemyMovement();
        getPlayerBulletSpeed();
        getAdhdPower();
        getSwiftyPower();
        getNormalEnemyMovement();
        getNormalEnemyScore();
        getNormalEnemymultiplier();
        getSaturnEnemyscore();
        getSaturnEnemyMovement();
        getSaturnEnemyMultie();
        getShootingEnemyShootingSpeed();
        getShootingEnemyScore();
        getShootingEnemyMultie();       
    }
    
}
