/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import javax.swing.JFrame;

/**
 *
 * @author Laurens
 */
public class GameBoard extends JFrame{
    JFrame gameBoard;
            
    public GameBoard(){
        initGameBoard();
    
    }
    public void initGameBoard()
    {
        gameBoard = new JFrame("GeometryWars");
        gameBoard.setSize(1000, 800);
        gameBoard.setVisible(true);
        gameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
