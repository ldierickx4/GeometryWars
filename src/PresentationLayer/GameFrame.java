/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

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
    private GamePanel gamePanel;

    private static final int HEIGHT = 800;
    private static final int WIDTH = 1000;
    
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int y = (dim.width-WIDTH)/2;
    private int x = (dim.height-HEIGHT)/2;
    private JLabel score;
    
    //private JLabel score;
    


    public static void main(String[] args){
        GameFrame window = new GameFrame();
        
        //window.initUi(); // dit wordt al opgeroepen in je constructor waar 'HIER' staat, overbodig en zal voor bugs zorgen
    }

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
        gamePanel = new GamePanel(this);
        score = new JLabel();
        //scoreTitle.setLocation(500, 500);
        score.setText("Score: ");
        score.setFont(new Font("Baskerville Old Face",Font.PLAIN,18));
        score.setForeground(Color.WHITE);
        //JLabel score = new JLabel();
        //score.setFont(new Font("Baskerville Old Face",Font.PLAIN,18));
        //score.setForeground(Color.WHITE);
        gamePanel.add(score);
        //gamePanel.add(score);
        setContentPane(gamePanel);
        pack(); // de pack method zegt aan uw layoutmanager ik ben klaar, zet alle layouts maar goed
        setVisible(true);
    }
    public void updateScore(String score)
    {
        String add = "Score : "+score;
        this.score.setText(add);
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
