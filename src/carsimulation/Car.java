/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 *
 * @author Shaklain
 */
public class Car extends  ImageObject implements KeyListener{

    public double speed = 0;
    private double acceleration = 0;
    
    private ArrayList<CoordinateTracker> Cotracker;
    
    private GameSession session;
    
    public Car(GameSession _session) {
        super(_session.CarName()+".png", 35, 18, 142, 405);
        session = _session;
        carType = getClass().getTypeName().replace("carsimulation.", "");
        Cotracker = new ArrayList<>();
        trackCoordinate();
    }
    
    private  void trackCoordinate()
    {
        Cotracker.add(new CoordinateTracker(_x,_y));
    }
    
    public void update(Image img1)
    {
        if(img1 != null)
        {
            Intersects(img1);   
        }
    }
    
    private void moveCar()
    {
        Vector v = new Vector(speed, direction / 180.0 * Math.PI);
        _x += v.getX();
        _y += v.getY();
    }
    
    public void Intersects(Image img1)
    {
        if (isGreen(img1)) 
        {            
            for(int i = Cotracker.size()-1; i >= Cotracker.size()-15; i--)
            {
                _x = Cotracker.get(i).x;
                _y = Cotracker.get(i).y;  
            }
            speed = -(speed/5);
        }else
        {
            trackCoordinate();
            moveCar();
        }
    }
    private String carType;
    
    public String getCarType()
    {
        return carType;
    }
    
    public void StartPosition()
    {
        _x = Cotracker.get(0).x;;
        _y = Cotracker.get(0).y;
        setDirection(0);
        speed = 0;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    public void setAcceleration(double val)
    {
        acceleration = val;
    }

    public double getAcceleration()
    {
        return acceleration;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            RacingTrack.track.gTimer.pause();
            if(Panel.YesOrNo("Game Paused","Exit the game?"))
            {
                RacingTrack.track.gTimer.Stop();
                RacingTrack.track.MainTimer.stop();
                RacingTrack.track.removeKeyListener(RacingTrack.track.getCar());
                RacingTrack.Title(session.user());
                VehiclePicker picker = new VehiclePicker(session.user());
                Window window = SwingUtilities.getWindowAncestor(RacingTrack.track);
                Frame frame = (Frame) window;        
                RacingTrack.track.setVisible(false);
                frame.showPanel(picker);
                //picker.setBackgroundImg("start.png");
                //picker.revalidate();
                //picker.repaint();
                picker.requestFocus();   
            }else
            {
                RacingTrack.track.gTimer.Resume();
            }
        }
        if (speed != 0) 
        {
            if (key == KeyEvent.VK_LEFT) {
                if (speed != 0) {
                    //speed -= 0.000002;
                    setDirection(direction -= (3));
                }

            } else if (key == KeyEvent.VK_RIGHT) {
                if (speed != 0)
                    //speed -= 0.000002;
                    setDirection(direction += (3));
            }
        }
        
        if (key == KeyEvent.VK_UP) {
            speed += (0.05 + (acceleration/2));
        }else if (key == KeyEvent.VK_DOWN) 
        {
            speed -= (0.05 + (acceleration/2));
        }
        if (key == KeyEvent.VK_CONTROL) {
            speed = -0.05;
        }
        
        if(key == KeyEvent.VK_S)
        {
            FileManager.Save(session);
        }
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP)
        {
            if(speed > 0)
            {
                speed -= (0.1);
            }else
            {
                speed=0;
            }
        }else if(key == KeyEvent.VK_DOWN)
        {
            if(speed < 0)
            {
                speed += (0.1);
            }else
            {
                speed = 0;
            }
        }
    }
    
    public boolean isGreen(Image img1)
    {
        ImageIcon icon = new ImageIcon(img1);
        BufferedImage bimage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img1, 0, 0, null);
        bGr.dispose();
        Color mycolor = new Color(bimage.getRGB((int)_x, (int)_y));
        //String hex = String.format("#%02x%02x%02x", mycolor.getRed(), mycolor.getGreen(),  mycolor.getBlue()); 
        if(mycolor.getRed() == 78)
        {
            if(mycolor.getGreen() == 100)
            {
                if(mycolor.getBlue()== 26)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean findWhite(Image img1)
    {
        ImageIcon icon = new ImageIcon(img1);
        BufferedImage bimage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img1, 0, 0, null);
        bGr.dispose();
        Color mycolor = new Color(bimage.getRGB((int)_x, (int)_y));
        //String hex = String.format("#%02x%02x%02x", mycolor.getRed(), mycolor.getGreen(),  mycolor.getBlue()); 
        //return mycolor.getRed() + "," + mycolor.getGreen() + "," + mycolor.getBlue();
        if(mycolor.getRed() == 255)
        {
            if(mycolor.getGreen() == 255)
            {
                if(mycolor.getBlue()== 255)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
}

class CoordinateTracker
{
    double x;
    double y;
    
    public CoordinateTracker(double _x, double _y)
    {
        x = _x;
        y = _y;
    }
}
