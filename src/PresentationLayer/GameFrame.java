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
public class GameFrame extends JFrame implements ActionListener,KeyListener,MouseMotionListener,MouseListener {
    private SingleGamePanel gamePanel;
    private static final int HEIGHT = 800;
    private static final int WIDTH = 1000;
    private Background bg;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int y = (dim.width-WIDTH)/2;
    private int x = (dim.height-HEIGHT)/2;
    private JLabel scoreP1;
    private JLabel scoreP2;
    private JLabel adhdPowerupP1;
    private JLabel adhdPowerupP2;
    public static void main(String[] args){
        GameFrame gf = new GameFrame();   
    }
    //private JLabel score;
    public GameFrame(){
        initUi(); // HIER
    }

    private void initUi() {
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setTitle("Astral Strifes");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setLocation(y, x);
        gamePanel = new SingleGamePanel(this); 
        setScoresAndPowerups();
        setContentPane(gamePanel);
        pack(); // de pack method zegt aan uw layoutmanager ik ben klaar, zet alle layouts maar goed
        setVisible(true);
    }
    
    public void setScoresAndPowerups(){
        
        scoreP1 = new JLabel();
        scoreP1.setText("Score Player 1: ");
        scoreP1.setFont(new Font("Baskerville Old Face",Font.PLAIN,18));
        scoreP1.setForeground(Color.WHITE);
        gamePanel.add(scoreP1);
        adhdPowerupP1 = new JLabel();   
        adhdPowerupP1.setText("ADHD Powerups Player 1: ");
        adhdPowerupP1.setFont(new Font("Baskerville Old Face",Font.PLAIN,18));
        adhdPowerupP1.setForeground(Color.WHITE);
        gamePanel.add(adhdPowerupP1);
        
        if(gamePanel.getType() == "Multi"){
            scoreP2 = new JLabel();
            scoreP2.setText("Score Player 2: ");
            scoreP2.setFont(new Font("Baskerville Old Face",Font.PLAIN,18));
            scoreP2.setForeground(Color.WHITE);
            gamePanel.add(scoreP2);
            adhdPowerupP2 = new JLabel();   
            adhdPowerupP2.setText("ADHD Powerups Player 2: ");
            adhdPowerupP2.setFont(new Font("Baskerville Old Face",Font.PLAIN,18));
            adhdPowerupP2.setForeground(Color.WHITE);
            gamePanel.add(adhdPowerupP2);
        } 
    }
  
    public void updateScoreP1(String score)
    {
        String add = "Score Player 1: " + score + "|";
        this.scoreP1.setText(add);
    }
    
    public void updateScoreP2(String score)
    {
        String add = "Score Player 2: " + score + "|";
        this.scoreP2.setText(add);   
    }
    
    public void updateAdhdPowerupsP1(String amount){
        String add = "ADHD Powerups Player 1: " + amount + "     ";
        this.adhdPowerupP1.setText(add);
    }
    
    public void updateAdhdPowerupsP2(String amount){
        String add = "ADHD Powerups Player 2: " + amount;
        this.adhdPowerupP2.setText(add);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gamePanel.keyReleased(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        gamePanel.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gamePanel.mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gamePanel.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    public int getHeight(){
        return this.HEIGHT;
    }
        public int getWidth(){
        return this.WIDTH;
    }
}
