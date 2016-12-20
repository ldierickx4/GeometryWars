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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import net.java.games.input.Component;
import net.java.games.input.Controller;

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
    private boolean down = false;
    private boolean up = false;
    private boolean left = false;
    private boolean right = false;
    private boolean shoot = false;
    private double imageAngleRad = 0;
    //player 1
    private PlayerBulletController controller;
    private CollisionController cc;
    private EnemyController ec;
    private PowerupController pc;
    private EnemyBulletController ebc;
    //player 2
    private PlayerBulletController controller2;
    private Controller Pscon;
    
    private JLabel score;
    private double mouseX;
    private double mouseY;
    private GameFrame gf;
    
    private Boolean attackDrone = false;
    public MultiGamePanel(GameFrame gf){
        this.gf =gf;
        controllerConnection();
        createComponents();
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        this.controller = new PlayerBulletController(player,this);
        this.controller2 = new PlayerBulletController(player2, this);
        this.ebc = new EnemyBulletController(player, this);
        this.ec = new EnemyController(player,this);
        this.pc = new PowerupController(player, this);
        this.cc = new CollisionController(player,controller, ec, ebc, pc);
        thread = new Thread(this);
        thread.start();
        score = new JLabel();
        player.makeDrone("attack");
        player2.makeDrone("heal");
    }
    public void setAttackdrone(){
        this.attackDrone = true;
    }
    public void createComponents()
    {
        background = new Background(gf);
        player = new Player(this,1);
        player2 = new Player(this,2);
        repaint();
    }
    public void controllerConnection(){
    
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
        ebc.render(gr);
        ec.render(gr);
        pc.draw(gr);
        player.drawHealth(gr);
        if(attackDrone){
            AttackDrone ad = (AttackDrone)(player.getDrone());
            ad.renderBullets(gr);        
        }
    }
    public void playerDraw(Player p , Graphics gr){
        p.draw(gr,this);
        p.getDrone().draw(gr);

        p.checkIfPlayerIsStillAlive();
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
        while(true)
        {           
            try {
		Thread.sleep(4);
            }
            catch(Exception e) {
		e.printStackTrace();
            }
            player.updateBounds();
            player.getDrone().letOrbit();
            player.updateBounds();
            player2.getDrone().letOrbit();
            checkShoot();
            checkInput();
            coullisionDetects();
            controller.update();
            ec.update();
            ebc.update();
            pc.updatePowerups();
            pc.checkForPOwerUp();
            gf.updateScore(player.getScore()+"");
            player.updateHealth();
            repaint();
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
        if(attackDrone){
            AttackDrone ad = (AttackDrone)(player.getDrone());
            cc.checkEnemyBulletCoulission(ad.getBullets());
        }
        cc.checkEnemyPlayerCollision();
        cc.checkIfPowerupGetsPickedUp();
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
            shoot=true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==e.BUTTON1)
        {
            shoot=false;
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
}
