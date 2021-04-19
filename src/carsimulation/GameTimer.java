/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

import sun.util.logging.resources.logging;

/**
 *
 * @author Shaklain
 */
public class GameTimer extends Thread{
    
    int seconds = 0;
    int delay = 1000;
    int timeComplete = 0;
    private  boolean start;
    private  boolean  stop;
    
    @Override
    public void run()
    {
        start = true;
        stop = false;
        do {            
            try {
                if(start)
                {
                    seconds +=1;
                    if(timeComplete !=0)
                    {
                        if(timeComplete > 0)
                        {
                            timeComplete = timeComplete - 1;   
                        }else
                        {
                            timeComplete = 0;
                        }
                    }
                    sleep(delay);  
                }
            } catch (Exception e) {
            }
            
                    System.out.println(seconds + "\n");
        } while (!stop);

    }
    
    public void setTime(int val )
    {
        timeComplete = val;
    }
    
    public int getTime()
    {
        return timeComplete;
    }
    
    public boolean status()
    {
        return start;
    }
    
    public void pause()
    {
        start = false;
    }
    
    public void Resume()
    {
        start = true;
    }
    
    public void Stop()
    {
        stop = true;
    }
    
    public void reset()
    {
        start = false;
        seconds =0;
    }

}
