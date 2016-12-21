/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author JorreVynckier
 */
public class UserPlay {
    
    private static UserPlay instance = null;
    private User u;
    
    public static UserPlay getinstance(){
        if (instance == null) {
            instance = new UserPlay();         
        }
        return instance;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    
    
}
