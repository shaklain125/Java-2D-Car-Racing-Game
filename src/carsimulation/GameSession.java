/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

/**
 *
 * @author Shaklain
 */
public class GameSession {
    
    private int Level;
    private String UserName;
    private String CarName;
    
    public GameSession(int level, String user, String cName)
    {
        Level = level;
        UserName = user;
        CarName = cName;
    }
        
    public String Level()
    {
        if(Level == 1)
        {
            return "track.png";
        }
        return null;
    }
    
    public int getLevel()
    {
        return Level;
    }
    
    public String user()
    {
        return UserName;
    }
        
    public String CarName()
    {
        return CarName;
    }
}
