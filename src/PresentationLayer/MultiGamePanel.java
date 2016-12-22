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
import AstralStrifes.Enemy.NormalEnemy;
import AstralStrifes.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Controller;
/**
 *
 * @author Laurens
 */
public class MultiGamePanel extends JPanel implements KeyListener,Runnable,MouseMotionListener,MouseListener,GamePanel{
    private Player player;
    private Player player2;
    private Thread thread2;
    private Background background;
    private boolean running = true;
    private Thread thread;
    private boolean down1 = false;
    private boolean up1 = false;
    private boolean left1 = false;
    private boolean right1 = false;
    private boolean shoot1 = false;
    private double imageAngleRad = 0;
    private EnemyBulletController ebc;
    private LinkedList<Player> players;
    //player 1
    private PlayerBulletController controller;
    private CollisionController cc;
    private EnemyController ec;
    private PowerupController pc;
    private double mouseX;
    private double mouseY;
    //player 2
    private PlayerBulletController controller2;
    private PowerupController pc2;
    private Controller pscon;
    private CollisionController cc2;
    private boolean shoot2 = false;
    private boolean down2 = false;
    private boolean up2 = false;
    private boolean left2 = false;
    private boolean right2 = false;
    private double aimX;
    private double aimY;
    private String status = "playing";
    private JLabel score;
    private GameFrame gf;
    
    private Boolean attackDrone = false;
    private String type = "Multi";
    
    public MultiGamePanel(GameFrame gf,String drone1,String drone2){
        this.gf =gf;
        controllerConnection();
        createComponents(drone1,drone2);

        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        this.controller = new PlayerBulletController(player,this,mouseX,mouseY);
        this.controller2 = new PlayerBulletController(player2, this,aimX,aimY);
        this.ebc = new EnemyBulletController(players, this);
        this.ec = new EnemyController(player,this);
        this.pc = new PowerupController(player, this);
        this.pc2 = new PowerupController(player2,this);
        this.cc = new CollisionController(player,controller, ec, ebc, pc);
        this.cc2 = new CollisionController(player2,controller2, ec, ebc, pc2);
        thread = new Thread(this);
        thread.start();
        score = new JLabel();

    }
    public void setAttackdrone(){
        this.attackDrone = true;
    }
    public void createComponents(String drone1,String drone2)
    {
        background = new Background(gf);
        player = new Player(this,1);
        player2 = new Player(this,2);
        player.makeDrone(drone1);
        player2.makeDrone(drone2);
        players = new LinkedList<Player>();
        players.add(player);
        players.add(player2);
        repaint();
    }
    public void controllerConnection(){
        try{
            Controllers.create();
        }
        catch(LWJGLException e){
            e.getMessage();
        }

        pscon = Controllers.getController(7);
        Controllers.poll();
    }
    public void checkInput1(){
        if(down1){
            player.moveDown();
        }
        if(up1){
            player.moveUp();
        }
        if(left1){
            player.moveLeft();
        }
        if(right1){
            player.moveRight();
        }
    }
    public void checkInput2(){
        if(down2){
            player2.moveDown();
        }
        if(up2){
            player2.moveUp();
        }
        if(left2){
            player2.moveLeft();
        }
        if(right2){
            player2.moveRight();
        }    
    
    }
    public void checkShoot(boolean pshoot , PlayerBulletController controller)
    {
        if(pshoot)
        {
            if(controller.getStatus()==false)controller.setShooting();
        }
        else{
            if(controller.getStatus()==true)controller.setNotShooting();
        }
    }
    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D)gr;
        gr.drawImage(background.getBackground(), 0, 0, background.getWidth(), background.getHeight(), this);
        playerDraw(player , gr);
        playerDraw(player2 , gr);
        controller.render(gr);
        controller2.render(gr);
        ebc.render(gr);
        ec.render(gr);
        pc.draw(gr);
        pc2.draw(gr);
    }
    public void renderAttackDrone(Player p,Graphics gr){
        if(p.getAttackDroneStatus()){
            AttackDrone ad = (AttackDrone)(p.getDrone());
            ad.renderBullets(gr);
            cc.checkEnemyBulletCoulission(ad.getBullets());
        }
    }
    public void playerDraw(Player p , Graphics gr){
        p.draw(gr,this);
        p.getDrone().draw(gr);
        p.checkIfPlayerIsStillAlive();
        p.drawHealth(gr);
        renderAttackDrone(p, gr);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int typed = e.getKeyCode();
        processKey(typed, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int typed = e.getKeyCode();
        processKey(typed, false);   
    }
    private void processKey(int key,boolean keystate){
        switch(key){
            case KeyEvent.VK_S:
                down1 = keystate;
                break;
            case KeyEvent.VK_Z:
                up1 = keystate;
                break;
            case  KeyEvent.VK_Q:
                left1 = keystate;
                break;
            case KeyEvent.VK_D:
                right1 = keystate;
                break;
            case KeyEvent.VK_SPACE:
                pc.useADHD();
                break;
        }    
    }
    @Override
    public void run() {   
        while(true)
        {           
            try {
		Thread.sleep(4);
            }
            catch(Exception e) {
		e.printStackTrace();
            }
            checkStatus();
            checkShoot(shoot2,controller2);
            checkShoot(shoot1,controller);
            checkInput1();
            checkInput2();
            checkControllerInput();
            coullisionDetects();
            controller.update(mouseX,mouseY);
            controller2.update(aimX,aimY);
            ec.update();
            ebc.update();
            pc.updatePowerups();
            pc2.updatePowerups();
            pc.checkForPOwerUp();
            pc2.checkForPOwerUp();
            updatePlayer(player);
            updatePlayer(player2);
            gf.updateAdhdPowerupsP1(player.getAmountOfAdhdPowerups());
            gf.updateAdhdPowerupsP2(player2.getAmountOfAdhdPowerups());
            gf.updateMultiplierP1(player.getMultiplier());
            gf.updateMultiplierP2(player2.getMultiplier());
            gf.updateScoreP1(player.getScore());
            gf.updateScoreP2(player2.getScore());
            gf.updateWaves(ec.getWave());
            gf.updateEnemiesLeft(ec.getEnemiesLeft());
            repaint();
        }
    }    
    public void updatePlayer(Player p){
        p.updateHealth();
        p.updateBounds();
        p.getDrone().letOrbit();
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
        cc2.checkPlayerMannaPickup();
        cc.checkEnemyBulletCoulission(controller.giveBullets());
        cc.checkEnemyBulletCoulission(controller2.giveBullets());
        cc.checkEnemyPlayerCollision();
        cc2.checkEnemyPlayerCollision();
        cc2.checkIfPowerupGetsPickedUp();
        cc.checkIfPowerupGetsPickedUp();
        cc2.checkIfPlayerGetsHitByEnemyBullet();
        cc.checkIfPlayerGetsHitByEnemyBullet();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX=e.getX();
        mouseY=e.getY();
        player.calculatePlayerAngle(mouseX,mouseY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX=e.getX();
        mouseY=e.getY();
        player.calculatePlayerAngle(mouseX,mouseY);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
                
    }
    

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==e.BUTTON1)
        {
            shoot1=true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==e.BUTTON1)
        {
            shoot1=false;
        }
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
    public PlayerBulletController getBulletControler2()
    {
        return this.controller2;
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
    public void checkControllerInput() {
        pscon.poll();
        checkConMove();
        checkConAim();
        checkConShoot();
        checkUseAdhd();
       //System.out.println(pscon.isButtonPressed(3)+"driehoek");
       //System.out.println(pscon.isButtonPressed(0)+"vierkant");
       //System.out.println(pscon.isButtonPressed(1)+"kruis");
       //System.out.println(pscon.isButtonPressed(2)+"bol");
       //System.out.println(pscon.isButtonPressed(4)+"l1");
       //System.out.println(pscon.isButtonPressed(5)+"R1");
       //System.out.println(pscon.isButtonPressed(6)+"l2");
       //System.out.println(pscon.isButtonPressed(7)+"r2");
       //System.out.println(pscon.isButtonPressed(8)+"share");
       //System.out.println(pscon.isButtonPressed(9)+"options");
       //System.out.println(pscon.isButtonPressed(10)+"links stick push");
       //System.out.println(pscon.isButtonPressed(11)+"rechts stick push");
       //System.out.println(pscon.isButtonPressed(12)+"ps4knop");
       //System.out.println(pscon.isButtonPressed(13)+"touchpad");
    }
    private void checkUseAdhd(){
        if(pscon.isButtonPressed(1)){
            pc2.useADHD();
        }
    }
    private void checkConShoot(){
        if(pscon.isButtonPressed(7)){
            shoot2 = true;
        }
        else{
            shoot2 = false;
        }
    }
    private void checkConAim(){
        this.aimX = pscon.getAxisValue(1)*1000;
        this.aimY = pscon.getAxisValue(0)*1000;
        player2.calculatePlayerAngle(aimX,aimY);
    }

    private void checkConMove() {
        //right2 = (pscon.getAxisValue(3)>0.5);
        if(pscon.getAxisValue(3)>0.5)
        {
            right2 = true;
        }
        else{
            right2 = false;
        }
        if(pscon.getAxisValue(3)<-0.5){
            left2 = true;
        }
        else{
            left2 = false;
        }
        if(pscon.getAxisValue(2)>0.5){
            down2 = true;
        }
        else{
            down2 = false;
        }
        if(pscon.getAxisValue(2)<-0.5){
            up2 = true;
        }
        else{
            up2 = false;
        }
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void checkStatus(){
        switch(status){
            case "playing":
                break;
            case "gameover":
                System.out.println("GameOver");
                break;
            case "finished":
                System.out.println("gewonnen");
                break;
        }
    }
}
