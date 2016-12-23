/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import AstralStrifes.Drone.AttackDrone;
import AstralStrifes.Controllers.EnemyController;
import AstralStrifes.Controllers.PowerupController;
import AstralStrifes.Controllers.CollisionController;
import AstralStrifes.Controllers.PlayerBulletController;
import AstralStrifes.Controllers.EnemyBulletController;
import AstralStrifes.*;
import AstralStrifes.Difficulty.Difficulty;
import Data.Database;
import Data.UserPlay;
<<<<<<< HEAD
=======
import java.awt.Color;
import java.awt.Font;
>>>>>>> origin/master
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import javax.swing.*;

/**
 *
 * @author Laurens
 */
public class SingleGamePanel extends JPanel implements KeyListener,Runnable,MouseMotionListener,MouseListener,GamePanel{
    private Player player;
    private Background background;
    private boolean game = true;
    private boolean running = true;
    private Thread thread;
    private boolean down = false;
    private boolean up = false;
    private boolean left = false;
    private boolean right = false;
    private boolean shoot = false;
    private double imageAngleRad = 0;
    PlayerBulletController controller;
    private CollisionController cc;
    private EnemyController ec;
    private PowerupController pc;
    private JLabel score;
    private double mouseX;
    private double mouseY;
    private Difficulty diff;
    private GameFrame gf;
    private EnemyBulletController ebc;
    private Boolean attackDrone = false;
    private LinkedList<Player> players;
    private String status = "playing";
    private GameOver end;
    public SingleGamePanel(GameFrame gf,String drone,Difficulty diff){ 
        this.gf =gf;
        this.diff = diff;
        createComponents();
        this.ec = new EnemyController(player,this);
        player.makeDrone(drone);
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        this.controller = new PlayerBulletController(player,this,mouseX,mouseY);
        this.ebc = new EnemyBulletController(players, this);
        this.pc = new PowerupController(player, controller,this);
        this.cc = new CollisionController(player,controller, ec, ebc, pc);
        thread = new Thread(this);
        thread.start();
        score = new JLabel();
    }
    public void setAttackdrone(){
        this.attackDrone = true;
    }
    public void createComponents()
    {   
        players = new LinkedList<Player>();
        background = new Background(gf);
        player = new Player(this,1);
        players.add(player);
        repaint();
    }
    public void checkInput(){
        if(down){
            player.moveDown();
        }
        if(up){
            player.moveUp();
        }
        if(left){
            player.moveLeft();
        }
        if(right){
            player.moveRight();
        }
    }
    public void checkShoot()
    {
        if(shoot)
        {
            if(!(controller.getStatus()))controller.setShooting();
        }
        else{
            if(controller.getStatus())controller.setNotShooting();
        }
    }
    @Override
    public void paintComponent(Graphics gr){
            super.paintComponent(gr);
            gr.drawImage(background.getBackground(), 0, 0, background.getWidth(), background.getHeight(), this); //Moet hier anders draait de achtergrond me
            playerDraw(player , gr);
            controller.render(gr);
            ebc.render(gr);
            ec.render(gr);
            pc.draw(gr);
    }
    public void playerDraw(Player p , Graphics gr){
        player.draw(gr,this);
        player.getDrone().draw(gr);
        player.getDrone().renderBullets(gr);
        player.drawHealth(gr);
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e){
        int typed = e.getKeyCode();
        processKey(typed, true);
    }
    @Override
    public void keyReleased(KeyEvent e){
        int typed = e.getKeyCode();
        processKey(typed, false);
    }
     private void processKey(int key,boolean keystate){
        switch(key){
            case KeyEvent.VK_S:
                down = keystate;
                break;
            case KeyEvent.VK_Z:
                up = keystate;
                break;
            case  KeyEvent.VK_Q:
                left = keystate;
                break;
            case KeyEvent.VK_D:
                right = keystate;
                break;
            case KeyEvent.VK_SPACE:
                pc.useADHD();
                break;
        }    
     }
    @Override
    public void run() {
        while(game)
        {       
            try {
		Thread.sleep(4);
                player.checkIfPlayerIsStillAlive();
                ec.update();
                player.updateHealth();
                player.updateBounds();
                player.getDrone().letOrbit();
                checkShoot();
                checkInput();
                coullisionDetects();
                controller.update(mouseX,mouseY);
                ebc.update();
                pc.updatePowerups();
                pc.checkForPOwerUp();               
                gf.updateScoreP1(player.getScore());
                gf.updateAdhdPowerupsP1(player.getAmountOfAdhdPowerups());
                gf.updateMultiplierP1(player.getMultiplier());
                gf.updateWaves(ec.getWave());
                gf.updateEnemiesLeft(ec.getEnemiesLeft());
                repaint();
                checkStatus();
            }
            catch(Exception e) {
		e.printStackTrace();
            }
        }
    }    
    public double getMouseX(){
        return this.mouseX;
    }
    public double getMouseY(){
        return this.mouseY;
    }
    public Player getPlayer(){
        return this.player;
    }
    public void coullisionDetects(){
        cc.checkPlayerMannaPickup();
        cc.checkEnemyBulletCoulission(controller.giveBullets());
        cc.checkEnemyBulletCoulission(player.getDrone().getBullets());
        cc.checkEnemyPlayerCollision();
        cc.checkIfPowerupGetsPickedUp();
        cc.checkIfPlayerGetsHitByEnemyBullet();
    } 
    @Override
    public void mouseDragged(MouseEvent e) {
        calcPlayer(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        calcPlayer(e);
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
                
    }
    

    @Override
    public void mousePressed(MouseEvent e) {
        shoot=e.getButton()==e.BUTTON1;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shoot=!(e.getButton()==e.BUTTON1);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    public PlayerBulletController getBulletControler()
    {
        return this.controller;
    }
    public CollisionController getCC()
    {
        return this.cc;
    }
    public EnemyController getEc()
    {
        return this.ec;
    }
    public JLabel getScore() {
        return score;
    } 
    public Background getBackGround(){
        return this.background;
    }
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void checkStatus() {
        
        

        switch(status){
            case "playing":
                break;
                
            case "gameover":        
                this.game=false;
                Database db = new Database();
                if(UserPlay.getinstance().getU() != null && db.getPlayerHighScore(UserPlay.getinstance().getU().getUsername()) < player.getScore()){
                    db.setPlayerHighscore(UserPlay.getinstance().getU().getUsername(), player.getScore());
                }
                end = new GameOver(gf,status);
                end.setScore(player.getScore());
                gf.setPanel(end);
                break;
                
            case "finished":            
                this.game=false;
                Database da = new Database();
                if(UserPlay.getinstance().getU() != null && da.getPlayerHighScore(UserPlay.getinstance().getU().getUsername()) < player.getScore()){
                    da.setPlayerHighscore(UserPlay.getinstance().getU().getUsername(), player.getScore());
                }
                end = new GameOver(gf,status);
                end.setScore(player.getScore());
                gf.setPanel(end);
                break;
        }
        
    }
    @Override
    public LinkedList<Player> getPlayers(){
        return this.players;
    }    
    @Override
    public Difficulty getDiff() {
        return this.diff;
    }
    @Override
    public boolean getGameLoop() {
        return this.game;
    }

    @Override
    public void calcPlayer(MouseEvent e) {
        mouseX=e.getX();
        mouseY=e.getY();
        player.calculatePlayerAngle(mouseX,mouseY);
    }
}
