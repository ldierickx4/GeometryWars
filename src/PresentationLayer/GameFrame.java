/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import AstralStrifes.Background;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Gebruiker
 */
//implements ActionListener,KeyListener,MouseMotionListener,MouseListener
public class GameFrame extends JFrame implements KeyListener {
    private SingleGamePanel singleGamePanel;
    private MultiGamePanel multiGamePanel;
    private static final int HEIGHT = 800;
    private static final int WIDTH = 1000;
    private Background bg;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int y = (dim.width-WIDTH)/2;
    private int x = (dim.height-HEIGHT)/2;
    private JLabel wave;
    private JLabel scoreP1;
    private JLabel scoreP2;
    private JLabel adhdPowerupP1;
    private JLabel adhdPowerupP2;
    private JLabel enemiesleft;
    private JLabel multiplierP1;
    private JLabel multiplierP2;
    private int playerCount;
    private String drone1;
    private String drone2;
    public static void main(String[] args){
        GameFrame gf = new GameFrame(2,"attack","heal");   
    }
    //private JLabel score;
    public GameFrame(int playerCount,String drone1, String drone2){
        this.playerCount = playerCount;
        this.drone1 = drone1;
        this.drone2 = drone2;
        initUi(); // HIER
    }
    public GameFrame(int playerCount,String drone1){
        this.playerCount = playerCount;
        this.drone1 = drone1;
        initUi(); // HIER
    }
        
    private void initUi() {
        //this.gamePanel = new SingleGamePanel(this);
        addKeyListener(this);
        //addMouseMotionListener(this);
        //addMouseListener(this);
        setTitle("Astral Strifes");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setLocation(y, x);
        //scoreTitle.setLocation(500, 500);
        //JLabel score = new JLabel();
        //score.setFont(new Font("Baskerville Old Face",Font.PLAIN,18));
        //gamePanel.add(score);
        //gamePanel = new MultiGamePanel(this); 
        creatGame();
        pack(); // de pack method zegt aan uw layoutmanager ik ben klaar, zet alle layouts maar goed
        setVisible(true);
    }
    public void creatGame(){
        if(playerCount == 2){
            this.multiGamePanel = new MultiGamePanel(this,drone1,drone2);
            setContentPane(multiGamePanel);
            setScoresAndPowerups2();
    }   else{
            this.singleGamePanel = new SingleGamePanel(this,drone1);
            setContentPane(singleGamePanel);
            setScoresAndPowerups1();
    }
    
    }
    public void setScoresAndPowerups2(){ 
        JPanel jp = new JPanel();
        scoreP1 = new JLabel();
        adhdPowerupP1 = new JLabel();
        scoreP2 = new JLabel();
        adhdPowerupP2 = new JLabel();
        wave = new JLabel();
        enemiesleft = new JLabel();
        multiplierP1 = new JLabel();
        multiplierP2 = new JLabel();
        wave.setForeground(Color.WHITE);
        enemiesleft.setForeground(Color.WHITE);
        scoreP1.setForeground(Color.WHITE);
        adhdPowerupP1.setForeground(Color.WHITE);
        scoreP2.setForeground(Color.WHITE);
        adhdPowerupP2.setForeground(Color.WHITE);
        multiplierP1.setForeground(Color.WHITE);
        multiplierP2.setForeground(Color.WHITE);
        multiGamePanel.add(wave);
        multiGamePanel.add(enemiesleft);
        multiGamePanel.add(multiplierP1);
        multiGamePanel.add(scoreP1);
        multiGamePanel.add(adhdPowerupP1);
        multiGamePanel.add(multiplierP2);
        multiGamePanel.add(scoreP2);
        multiGamePanel.add(adhdPowerupP2);
        
        
    }
    public void setScoresAndPowerups1(){
        scoreP1 = new JLabel();
        wave = new JLabel();
        adhdPowerupP1 = new JLabel();
        enemiesleft = new JLabel();
        multiplierP1 = new JLabel();
        //scoreP1.setText("Score Player 1: ");
        scoreP1.setForeground(Color.WHITE);
        wave.setForeground(Color.WHITE);
        adhdPowerupP1.setForeground(Color.WHITE);
        enemiesleft.setForeground(Color.WHITE);
        multiplierP1.setForeground(Color.WHITE);
        singleGamePanel.add(wave);
        singleGamePanel.add(enemiesleft);
        multiGamePanel.add(multiplierP1);
        singleGamePanel.add(scoreP1);
        singleGamePanel.add(adhdPowerupP1);
        
    }
  
    public void updateScoreP1(int score)
    {
        String add = "Score Player 1: " + score + "|";
        this.scoreP1.setText(add);
    }
    
    public void updateScoreP2(int score)
    {
        String add = "Score Player 2: " + score + "|";
        this.scoreP2.setText(add);   
    }
    public void updateMultiplierP1(int multiplier){
        String add = "multiplier Player 1: " + multiplier + "|";
        this.multiplierP1.setText(add);
    }
    public void updateMultiplierP2(int multiplier){
        String add = "multiplier Player 2: " + multiplier + "|";
        this.multiplierP2.setText(add);
    }
    public void updateAdhdPowerupsP1(int amount){
        String add = "ADHD Powerups Player 1: " + amount + "     ";
        this.adhdPowerupP1.setText(add);
    }
    
    public void updateAdhdPowerupsP2(int amount){
        String add = "ADHD Powerups Player 2: " + amount;
        this.adhdPowerupP2.setText(add);
    }
    public void updateWaves(int amount){
        String add = "Wave: " + amount;
        this.wave.setText(add);
    }
    public void updateEnemiesLeft(int amount){
        String add = "enemies left: "+amount;
        this.enemiesleft.setText(add);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(multiGamePanel==null){
            singleGamePanel.keyPressed(e);
        }
        else{
            multiGamePanel.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(multiGamePanel==null){
            singleGamePanel.keyReleased(e);
        }
        else{
            multiGamePanel.keyReleased(e);
        }
        
    }
    
    public int getHeight(){
        return this.HEIGHT;
    }
        public int getWidth(){
        return this.WIDTH;
    }
}
