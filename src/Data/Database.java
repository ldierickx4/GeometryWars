/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import AstralStrifes.Controllers.PlayerBulletController;
import AstralStrifes.Enemy.*;
import AstralStrifes.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
    private static final String USER = "Jens";
    private static final String PWD = "jens";
    private static Connection con;
    private boolean userExist;
    private boolean userAdded;
    private String username;
    private int userid;
    private String pw;
    private String email;
    private int xp;
    private int highScore;
    private int astrals;
    private int gameId;
    private int rankId;
    private int amountEnemies;
    private int highScorePlayer;
    private String imgPath;
    private LinkedList<String> wave1 = new LinkedList<>();
    private String enemyName;
    private HashMap<String,Integer> difficultyParameters = new HashMap<>();
    private String difName;
    private int seScore;
    private int neScore;
    private int satEScore;
    private float neSpeed;
    private int seShoot;
    private float satEMove;
    private int seMulti;
    private int neMulti;
    private int satEMulti;
    private int swiftyDuration;
    private int adhdPower;
    private int spawnSpeed;
    private int playerShootSpeed;
    private int neDamage;
    private int seDamage;
    private int satEDamage;
    private int eBulletDamage;
    private HashMap<User,Integer> usersHS = new HashMap<>();
            
    
    
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
      
    public String getEnemyName(int enemy_id){
        try{
           String sql = "SELECT enemy_name FROM enemies WHERE enemy_id = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setInt(1, enemy_id);           
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               String enemy = rs.getString("enemy_name");
               this.enemyName = enemy;
           }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return enemyName;
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
    
    public int getUserid(String username){
        try{
           String sql = "SELECT userid FROM users WHERE username = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, username);           
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               int userid = rs.getInt("userid");
               this.userid = userid;
           }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return userid;
    }
    public boolean getUserExist(){
        return this.userExist;      
    }
    
    public String getPassword(String username){
        try{
           String sql = "SELECT password FROM users WHERE username = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, username);           
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               String userpw = rs.getString("password");
               this.pw = userpw;
           }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return pw;
    }
    
    public String getEmail(String username){
        try{
           String sql = "SELECT username FROM users WHERE username = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, username);           
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               String usermail = rs.getString("email");
               this.email = usermail;
           }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return email;
    }
    
    public int getXP(String username){
        try{
           String sql = "SELECT XP FROM users WHERE username = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, username);           
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               int intxp = rs.getInt("XP");
               this.xp = intxp;
           }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return xp;
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
    
    public int getAstrals(String username){
        try{
           String sql = "SELECT astrals from users where username = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, username);           
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               int astr = rs.getInt("astrals");
               this.astrals = astr;
           }
           pstmt.close();
           rs.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return astrals;
    }

    
    public int getGameid(String username){
        try{
           String sql = "SELECT game_id from users where username = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, username);           
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               int game = rs.getInt("game_id");
               this.gameId = game;
           }
           pstmt.close();
           rs.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return gameId;
    }
    
    public int getRankid(String username){
        try{
           String sql = "SELECT rank_id from users where username = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, username);           
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()){
               int rnk = rs.getInt("rank_id");
               this.rankId = rnk;
           }
           pstmt.close();
           rs.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return rankId;
    }
    
    public boolean getUserAdded(){
        return this.userAdded;
    }
    
   
    public LinkedList getEnemiesInWave(int wave_id){
        try{
           String sql = "SELECT * FROM wave_enemies WHERE wave_id = (?)";
           PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           pstmt.setInt(1, wave_id);           
           ResultSet rs = pstmt.executeQuery();
           while(rs.next()){
               //System.out.println(rs.getArray("enemy_id"));
               int enemy = rs.getInt("enemy_id");
               int amount = rs.getInt("amount");
               if(enemy == 1){
                   for(int i = 0;i < amount; i++){
                       wave1.add("shootingenemy");
                   }
               }
               if(enemy == 2){
                   for(int i = 0;i < amount; i++){
                       wave1.add("saturnenemy");
                   }
               }
               if(enemy == 3){
                   for(int i = 0;i < amount; i++){
                       wave1.add("normalenemy");
                   }
               }
           }
           pstmt.close();
           rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return wave1;
    }
    
    public String getDifficultyName(int difficultyId){
        try{
            String sql = "SELECT difficulty_name FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String name = rs.getString("difficulty_name");
                this.difName = name;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return difName;
    }
    
    public int getDifficultySEScore(int difficultyId){
        try{
            String sql = "SELECT shootingEnemyScore FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int SEScore = rs.getInt("shootingEnemyScore");
                this.seScore = SEScore;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return seScore;
    }
    
    public int getDifficultyNEScore(int difficultyId){
        try{
            String sql = "SELECT normalEnemyScore FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int NEScore = rs.getInt("normalEnemyScore");
                this.neScore = NEScore;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return neScore;
    }

    public int getDifficultySATScore(int difficultyId){
        try{
            String sql = "SELECT saturnEnemyScore FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int SATEScore = rs.getInt("saturnEnemyScore");
                this.satEScore = SATEScore;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return satEScore;
    }
    
    public float getDifficultyNESpeed(int difficultyId){
        try{
            String sql = "SELECT normalEnemySpeed FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                float NESpeed = rs.getFloat("normalEnemySpeed");
                this.neSpeed = NESpeed;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return neSpeed;
    }
    
    public int getDifficultySEShoot(int difficultyId){
        try{
            String sql = "SELECT shootingEnemyShoot FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int SEShoot = rs.getInt("shootingEnemyShoot");
                this.seShoot = SEShoot;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return seShoot;
    }
    
    public float getDifficultySATEMove(int difficultyId){
        try{
            String sql = "SELECT saturnEnemyMove FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                float SATEMove = rs.getFloat("saturnEnemyMove");
                this.satEMove = SATEMove;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return satEMove;
    }
    
    public int getDifficultySEMulti(int difficultyId){
        try{
            String sql = "SELECT shootingEnemyMulti FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int SEMulti = rs.getInt("shootingEnemyMulti");
                this.seMulti = SEMulti;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return seMulti;
    }
    
    public int getDifficultyNEMulti(int difficultyId){
        try{
            String sql = "SELECT notmalEnemyMulti FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int NEMulti = rs.getInt("notmalEnemyMulti");
                this.neMulti = NEMulti;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return neMulti;
    }
    
    public int getDifficultySATEMulti(int difficultyId){
        try{
            String sql = "SELECT saturnEnemyMulti FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int SATEMulti = rs.getInt("saturnEnemyMulti");
                this.satEMulti = SATEMulti;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return satEMulti;
    }
    
    public int getDifficultySwiftyDuration(int difficultyId){
        try{
            String sql = "SELECT swiftyduration FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int SwDur = rs.getInt("swiftyduration");
                this.swiftyDuration = SwDur;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return swiftyDuration;
    }
    
    public int getDifficultyAdhdPower(int difficultyId){
        try{
            String sql = "SELECT adhdPower FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int aPower = rs.getInt("adhdPower");
                this.adhdPower = aPower;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return adhdPower;
    }
    
    public int getDifficultySpawnSpeed(int difficultyId){
        try{
            String sql = "SELECT spawnSpeed FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int spSpeed = rs.getInt("spawnSpeed");
                this.spawnSpeed = spSpeed;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return spawnSpeed;
    }
    
    public int getDifficultyPlShootSpeed(int difficultyId){
        try{
            String sql = "SELECT playerShootSpeed FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int PlShSpeed = rs.getInt("playerShootSpeed");
                this.playerShootSpeed = PlShSpeed;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return playerShootSpeed;
    }
    
    public int getDifficultyNEDamage(int difficultyId){
        try{
            String sql = "SELECT normalEDamage FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int NEDamage = rs.getInt("normalEDamage");
                this.neDamage = NEDamage;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return neDamage;
    }
    
    public int getDifficultySEDamage(int difficultyId){
        try{
            String sql = "SELECT shootEDamage FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int SEDamage = rs.getInt("shootEDamage");
                this.seDamage = SEDamage;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return seDamage;
    }
    
    public int getDifficultySATEDamage(int difficultyId){
        try{
            String sql = "SELECT satEDamage FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int SATEDamage = rs.getInt("satEDamage");
                this.satEDamage = SATEDamage;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return satEDamage;
    }
    
    public int getDifficultyEBulletDamage(int difficultyId){
        try{
            String sql = "SELECT eBulletDamage FROM difficulties WHERE difficulty_id = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, difficultyId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int EBulletDamage = rs.getInt("eBulletDamage");
                this.eBulletDamage = EBulletDamage;
            }
            pstmt.close();
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return eBulletDamage;
    }
    
    
     
    
    public HashMap getUsers(){
        try{
            String sql = "SELECT * FROM users";
            Statement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String username = rs.getString("username");
                String pw = rs.getString("password");
                String email = rs.getString("email");
                int highScore = rs.getInt("highscore");
                int xp = rs.getInt("XP");
            
                User u = new User(username,pw,email,xp,highScore);
                this.usersHS.put(u, highScore);
                
                        
            }    
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return usersHS;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Database db = new Database();
        System.out.println(db.getDifficultySATEDamage(1));
        System.out.println(db.getDifficultyNEDamage(1));
        System.out.println(db.getDifficultySEDamage(1));
        System.out.println(db.getDifficultyEBulletDamage(1));
        

    }
    
    
}