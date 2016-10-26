/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import java.awt.Dimension;
import javax.swing.*;
import geometrywars.*;
import java.awt.Graphics;
import java.awt.Toolkit;

/**
 *
 * @author Gebruiker
 */
public class GameFrame extends JFrame{
    private GamePanel gamePanel;
<<<<<<< HEAD
    private static final int HEIGHT = 1400;
    private static final int WIDTH = 1400;
    
=======
    private static final int HEIGHT = 1000;
    private static final int WIDTH = 1000;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int y = (dim.width-WIDTH)/2;
    private int x = (dim.height-HEIGHT)/2;

>>>>>>> origin/master
    public static void main(String[] args){
        GameFrame window = new GameFrame();
        //window.initUi(); // dit wordt al opgeroepen in je constructor waar 'HIER' staat, overbodig en zal voor bugs zorgen
    }

    public GameFrame(){
        initUi(); // HIER
    }

    private void initUi() {
        setTitle("Geometry Wars");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
<<<<<<< HEAD
        //setSize(new Dimension(800, 600));
        // layout manager werkt niet met setsize, enkel prefererd size, dan probeert hij die size te respecteren als hij kan
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //setLocationRelativeTo(null); //afgezet wegens irritant :D
=======
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setLocation(y,x);
>>>>>>> origin/master
        setVisible(true);
        gamePanel = new GamePanel();
        setContentPane(gamePanel); // met deze method zet je uw main content panel van uw jframe (dit kan maar 1 panel zijn max, als je meerdere dingen wilt moet je panels nesten)
        pack(); // de pack method zegt aan uw layoutmanager ik ben klaar, zet alle layouts maar goed
    }


}
