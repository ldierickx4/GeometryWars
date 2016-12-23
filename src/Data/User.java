/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Jens
 */
public class User {
    
    private String username;
    private String pw;
    private String email;
    private int xp;
    private int Highscore;
    private int rank_id;

    

    public User(String username, String pw, String email, int xp, int Highscore, int rank_id) {
        this.username = username;
        this.pw = pw;
        this.email = email;
        this.xp = xp;
        this.Highscore = Highscore;
        this.rank_id = rank_id;
    }
    


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getHighscore() {
        return Highscore;
    }

    public void setHighscore(int Highscore) {
        this.Highscore = Highscore;
    }

    public int getRank_id() {
        return rank_id;
    }

    public void setRank_id(int rank_id) {
        this.rank_id = rank_id;
    }

    

    @Override
    public String toString() {
        return username;
    }
    
    
}
