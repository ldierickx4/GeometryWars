/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AstralStrifes;

import PresentationLayer.GamePanel;
import AstralStrifes.Drone.AttackDrone;
import AstralStrifes.Drone.Drone;
import AstralStrifes.Drone.HealDrone;
import AstralStrifes.Drone.KillDrone;
import AstralStrifes.Enemy.Manna;
import PresentationLayer.GamePanel;
import PresentationLayer.SingleGamePanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
/**
 *
 * @author Laurens
 */
public class Player {
    private int SPEED = 2;
    private int health = 100;
    private int maxHealth = 100;
    private String naam;
    private double y;
    private double x;
    private BufferedImage image;
    private AffineTransform trans;
    private Graphics g;
    private double width;
    private double height;
    private double angle;
    private int score;
    private Rectangle playerBounds;
    private LinkedList<Manna> manna;
    private Drone sd;
    private GamePanel gp;
    private int bgheight;
    private int bgwidth;
    private int healthX;
    private int healthY;

    private int amountOfAdhdPowerups;

    private int playerstatus;

    
    public Player(GamePanel gp,int playerstatus){

        this.gp = gp;
        this.playerstatus = playerstatus;
        this.width=28;
        this.height=30;
        this.x = 170.0;
        this.y = 150.0;
        this.score = 0;
        loadImage();
        createBounds();
        this.bgheight = gp.getBackGround().getHeight()-40;
        this.bgwidth = gp.getBackGround().getWidth()-25;
        manna= new LinkedList<Manna>();
        amountOfAdhdPowerups = 1;
    }
    public void makeDrone(String drone){
        if(drone.equals("heal")){
            this.sd = new HealDrone(this);
        }
        else if(drone.equals("attack")){
            this.sd = new AttackDrone(this, gp);
            gp.setAttackdrone();
        }
        else{
            this.sd = new KillDrone(this, gp);
        }
    }
    public Drone getDrone(){
        return this.sd;
    }
    private void createBounds()
    {
        this.playerBounds = new Rectangle(getPlayerCenterX(),getPlayerCenterY(), image.getWidth(), image.getHeight());
    }
    private void loadImage() {
        String link = "resources/gameSprites/ship2.png";
        healthX=880;
        healthY=20;
        if(playerstatus==1){
            healthX=20;
            healthY=20;
            link = "resources/gameSprites/ship.png";
        }
        BufferedImage i = null;
        try {
            i = ImageIO.read(new File(link));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        image = i;
    }
    public void moveUp()
    {
        if(y>25){
            this.y-=SPEED;
        }
    }
    public void moveDown()
    {
        if(y<bgheight){
           this.y+=SPEED;
        }
    }
    public void moveLeft()
    {
        if(x>25){
            this.x-=SPEED;
        }
    }
    public void moveRight()
    {
        if(x<bgwidth){
            this.x+=SPEED;
        }
    }
    public Image giveImage()
    {
        return image;
    }
    public double getx()
    {
        return this.x;
    }
    public double gety()
    {
        return this.y;
    }
    public double getHeight()
    {
        return image.getHeight();
    }
    public double getWidth(){
        return image.getWidth();
    }
    public void setPlayerAngle(double angle)
    {
        this.angle=angle;
    }
    public double getPlayerAngle()
    {
        return this.angle;
    }
    public int getPlayerCenterX()
    {
        return (int)(x)-image.getHeight()/2;
    }
    public int getPlayerCenterY()
    { 
        return (int)(y)-image.getWidth()/2;
    }
    public void draw(Graphics g,GamePanel gp)
    {
        AffineTransform reset = new AffineTransform();
        Graphics2D g2 = (Graphics2D)g;
        g2.rotate(getPlayerAngle(),getx(),gety());
        g2.drawImage(giveImage(),getPlayerCenterX(), getPlayerCenterY(),image.getWidth(),image.getHeight(),null);
        g2.setTransform(reset);
    }

    public void calculatePlayerAngle(double mouseX, double mouseY) {
        this.angle = Math.atan2(gety() - mouseY, getx() - mouseX);
    }
    
    public void drawHealth(Graphics g){     
        g.drawRect(healthX, healthY, maxHealth, 40);
        g.setColor(Color.red);
        g.fillRect(healthX, healthY, maxHealth, 40);  
        g.drawRect(healthX, healthY, health, 40);
        g.setColor(Color.green);
        g.fillRect(healthX, healthY, health, 40);
    }
    
    public void checkIfPlayerIsStillAlive(){ //TODO CONTINUE HIHI
        if(health <= 0){
            System.out.println("Player iz dead");
        }
    }
    public int getHealth()
    {
        return this.health;
    }
  
    public int getScore() {
        int score = 0;
        for(Manna m : manna){
            score += m.getScore();
        }
        return score;
    }
    
    public void addScore(int score){
        this.score += score;
    }
    
    public Rectangle getBounds(){
        return this.playerBounds;
    }

    public void updateBounds() {
        playerBounds.setBounds(getPlayerCenterX(),getPlayerCenterY(), image.getWidth(), image.getHeight());
    }
    
   public void reduceHealth(int amount){
       this.health -= amount;
   }

    public int updateHealth(){
        
        return health;
    }
    
    public void addManna(Manna m){
        this.manna.add(m);
    }
    public GamePanel getgp(){
        return this.gp;
    }
    public void heal(int heal){
        this.health+=heal;
    }
    
    public void boostSpeed(int speed){
        this.SPEED = speed;
    }
    
    public int getAmountOfAdhdPowerups(){
        return this.amountOfAdhdPowerups;
    }
    
    public void setAmoundOfAdhdPowerups(int amount){
        this.amountOfAdhdPowerups = amount;
    }
    
    public void addOneAdhdPowerup(){
        this.amountOfAdhdPowerups += 1;
    }
    
    public void reduceAdhd(){
        this.amountOfAdhdPowerups-= 1;
    }
}
