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
public class GameFrame extends JFrame implements ActionListener,KeyListener,MouseMotionListener {
    private GamePanel gamePanel;

    private static final int HEIGHT = 900;
    private static final int WIDTH = 900;
    
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int y = (dim.width-WIDTH)/2;
    private int x = (dim.height-HEIGHT)/2;
   


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
        setTitle("Astral Strifes");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setLocation(y, x);
        gamePanel = new GamePanel();
        setContentPane(gamePanel); // met deze method zet je uw main content panel van uw jframe (dit kan maar 1 panel zijn max, als je meerdere dingen wilt moet je panels nesten)
        pack(); // de pack method zegt aan uw layoutmanager ik ben klaar, zet alle layouts maar goed
        setVisible(true);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        gamePanel.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gamePanel.mouseMoved(e);
    }

    
}
