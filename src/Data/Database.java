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
    private static final String USER = "root";
    private static final String PWD = "root";
    private static Connection con;
    private List<User> users;
    private boolean userExist;
    private boolean userAdded;
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
    
    public boolean getUserExist(){
        System.out.println(this.userExist);
        return this.userExist;      
    }
    
    public boolean getUserAdded(){
        return this.userAdded;
    }
  
    public static void main(String[] args) {
        // TODO code application logic here
        Database db = new Database();
        db.checkUserDB("test","testjes"); 
    }
    
    
}
