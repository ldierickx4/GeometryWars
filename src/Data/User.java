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

    public User(String username, String pw, String email) {
        this.username = username;
        this.pw = pw;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPw() {
        return pw;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return username + "@" + pw;
    }
    
    
}
