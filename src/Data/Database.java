/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import AstralStrifes.Enemy.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jens
 */
public class Database {
    
    
    private static final String URL = "jdbc:mysql://localhost/astralstrifes";
    private static final String USER = "root";
    private static final String PWD = "root";
    private static Connection con;
    private List<User> users;
    private boolean userExist;
    private boolean userAdded;
    private String username;
    private int amountEnemies;
    private int highScorePlayer;
    private String imgPath;
    private List<Enemy> wave1;
    
    public Database() {
        //Server registreren
        try{
            Class.forName("com.mysql.jdbc.Driver");
          
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        
        //Connectie maken
        try{
            this.con = DriverManager.getConnection(URL,USER,PWD);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void addUser(String username, String pwd, String email){
        try{
            String sql = "INSERT INTO users(username,password,email) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, pwd);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            pstmt.close();
            //User u = new User(username,pwd,email);
            //users.add(u);
            this.userAdded = true;
        } catch(SQLException ex){
            ex.printStackTrace();
            this.userAdded = false;
        }
    }
    
    public void deleteUser(String username){
        try{
            String sql = "DELETE FROM users WHERE username = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            pstmt.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void checkUserDB(String name,String pw){
        try{
            String sql = "SELECT username,password FROM users WHERE username = (?) AND password = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setString(2, pw);
            ResultSet rs =  pstmt.executeQuery();
            if(rs.next()){
                this.userExist = true;               
            }
            pstmt.close();
            rs.close();          
        }catch(SQLException ex){
            ex.printStackTrace();
        }       
    }
    
    public String getEnemyImage(String enemyName){
        //String imgPath = null;
        try {
            
            String sql = "select image from enemies where enemy_name = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, enemyName);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String img = rs.getString("image");
                this.imgPath = img.toString();
            }  
            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return imgPath;
    }
    
    public void setPlayerHighscore(int userid, int score){
        try{
           String sql = "UPDATE users SET highscore = (?) WHERE userid = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setInt(1, score);
           pstmt.setInt(2,userid);
           pstmt.executeUpdate();
           pstmt.close();
           
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public int getPlayerHighScore(String username){
        try{
           String sql = "SELECT highscore from users where username = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, username);           
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               int highScore = rs.getInt("highscore");
               this.highScorePlayer = highScore;
           }
           pstmt.close();
           rs.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return highScorePlayer;
    }
    
    public int getAmountOfTypeInWave(int enemyid){
        //wave1 = new LinkedList<Enemy>();
        try{
           String sql = "SELECT amount FROM wave_enemies WHERE enemy_id = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setInt(1, enemyid);           
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               int amount = rs.getInt("amount");
               String type = rs.getString("enemy_name");
               if(type == "shootingenemy" ){
                   //wave1.add(e);
               }
               this.amountEnemies = amount;
           }
           pstmt.close();
           rs.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return amountEnemies;
    }
    
    public String getUsername(String username){
        try{
           String sql = "SELECT username FROM users WHERE username = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, username);           
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               String usern = rs.getString("username");
               this.username = usern;
           }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return username;
    }
    public boolean getUserExist(){
        System.out.println(this.userExist);
        return this.userExist;      
    }
    
    public boolean getUserAdded(){
        return this.userAdded;
    }
    
    public String getUsername(String username){
        try{
           String sql = "SELECT * FROM users WHERE username = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, username);           
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               String usern = rs.getString("username");
               this.username = usern;
           }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return username;
    }
  
    public static void main(String[] args) {
        // TODO code application logic here
        Database db = new Database();
        //db.checkUserDB("AstralKing","astral123");
        //db.getUserExist();
        //db.setPlayerHighscore(5, 10000);
        //db.getPlayerHighScore("VangeelJ");
<<<<<<< HEAD
        System.out.println(db.getUsername("jorre"));
        
=======
        System.out.println(db.getEnemyImage("normalenemy"));
        System.out.println(db.getUsername("VangeelJ"));
        System.out.println(db.getUsername("AstralKing"));
>>>>>>> origin/master
    }
    
    
}
