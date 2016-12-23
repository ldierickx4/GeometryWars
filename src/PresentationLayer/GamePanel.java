/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import AstralStrifes.Background;
import AstralStrifes.Controllers.CollisionController;
import AstralStrifes.Controllers.EnemyBulletController;
import AstralStrifes.Controllers.EnemyController;
import AstralStrifes.Controllers.PlayerBulletController;
import AstralStrifes.Controllers.PowerupController;
import AstralStrifes.Difficulty.Difficulty;
import AstralStrifes.Drone.AttackDrone;
import AstralStrifes.Player;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author laurensdierickx
 */
public interface GamePanel{
    public void setAttackdrone();
    public void paintComponent(Graphics gr);
    public void playerDraw(Player p , Graphics gr);
    public void keyTyped(KeyEvent e);
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);
    public void run();
    public double getMouseX();
    public double getMouseY();
    public Player getPlayer();
    public void coullisionDetects();
    public void mouseDragged(MouseEvent e);
    public void mouseMoved(MouseEvent e);
    public void mouseClicked(MouseEvent e);
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);   
    public void mouseEntered(MouseEvent e);
    public void mouseExited(MouseEvent e);
    public PlayerBulletController getBulletControler();
    public CollisionController getCC();
    public EnemyController getEc();
    public Background getBackGround();
    public void setStatus(String status);
    public void checkStatus();
    public Difficulty getDiff();
    public LinkedList<Player> getPlayers();
}
