/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Jens
 */
public class Database {
    
    private static final String URL = "jdbc:mysql://localhost/astralstrifes";
    private static final String USER = "Jens";
    private static final String PWD = "jens";
    private static Connection con;
    private List<User> users;
    private boolean userExist;
    private String username;
    
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
    
    public void addUser(String username,String pwd, String email){
        try{
            String sql = "INSERT INTO users(username,password,email) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, pwd);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            pstmt.close();
            User u = new User(username,pwd,email);
            users.add(u);
        } catch(SQLException ex){
            ex.printStackTrace();
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
    
    public void checkUserDB(String username,String pw){
        try{
            String sql = "SELECT username,password FROM users WHERE username = (?) & password = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, username);
            pstmt.setString(2, pw);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                //String user = rs.getString("username") + " @ " + rs.getString("password");
                //System.out.println("Jups bestoat");
                //this.username = rs.getString("username") + " @ " + rs.getString("password");
                if(username == rs.getString("username") && pw == rs.getString("password")){
                    userExist = true;
                } else {
                    userExist = false;
                }
                
            }
            //userExist = true;
            
            rs.close();
            pstmt.close();
            System.out.println("Jups bestoat");
        }catch(SQLException ex){
            ex.printStackTrace();
            //userExist = false; 
        }
        
    }
    
    public void checkUserExist(String username,String pw){
        if(userExist){
            System.out.println("User bestaat");
        }else{
            System.out.println("User bestaat niet");
        }
        
    }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Database db = new Database();
        db.checkUserExist("AstralKing", "astral123");
        
    }
    
    
}
