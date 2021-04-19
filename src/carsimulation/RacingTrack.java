/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

import static carsimulation.Frame.Msgbox;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Shaklain
 */
public class RacingTrack extends Panel{
    
    public GameTimer gTimer;
    Timer MainTimer;
    private Car car1;
    private boolean running = false;
    private GameSession session;
    private int timeComplete =0;
    
    public RacingTrack(GameSession _Session, int Time)
    {
        super();
        session = _Session;
        if(isPolice(session.CarName()))
        {
            car1 = new PoliceCar(session);
        }else if (isMedical(session.CarName()))
        {
            car1 = new MedicalCar(session);
        }else if (isSport(session.CarName()))
        {
            car1 = new SportCar(session);
        }else if (isFire(session.CarName()))
        {
            car1 = new FireEngine(session);
        }else
        {
            car1 = new Car(session);
        }
        addKeyListener(car1);
        setBackgroundImg(session.Level());
        MainTimer = new Timer(10, this);
        MainTimer.start();
        gTimer = new GameTimer();
        timeComplete = Time;
        gTimer.setTime(timeComplete);
        gTimer.start();
        track = this;
    }
    
    public Car getCar()
    {
        return car1;
    }

    public static boolean  isSport(String CarName)
    {
        CarName+=".";
        ArrayList<String> sport = new ArrayList<>(Arrays.asList(new String[]{"_1.","_2.","_3.","_6."}));
        for(String v : sport)
        {
            if(CarName.endsWith(v))
            {
                return true;
            }
        }
        return false;
    }
    
    public static boolean  isMedical(String CarName)
    {
        CarName+=".";
        ArrayList<String> medical = new ArrayList<>(Arrays.asList(new String[]{"_9.","_13."}));
        for(String v : medical)
        {
            if(CarName.endsWith(v))
            {
                return true;
            }
        }
        return false;
    }
    public static boolean  isPolice(String CarName)
    {
        CarName+=".";
        ArrayList<String> police = new ArrayList<>(Arrays.asList(new String[]{"_4.","_5.","_12."}));
        for(String v : police)
        {
            if(CarName.endsWith(v))
            {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isFire(String CarName)
    {
        CarName+=".";
        if(CarName.endsWith("_14."))
        {
            return true;
        }
        return false;
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if(!running)
        {
            car1.initiate(0,g2, this);
            running = true;
        }else
        {
            car1.initiate(g2, this);
        }
    }
    
    public static RacingTrack track;

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        car1.update(bgImage);
        if(!gTimer.status())
        {
            return;
        }
        //Title(session.user() +" > "+ " " + session.CarName() + " > " + car1.getClass().getTypeName().replace("carsimulation.", "")+" : "+(int)car1._x + ", " + (int) car1._y + "  D:" + car1.direction + " Speed: "+ Math.round(car1.speed) + " isGreen: " +car1.isGreen(bgImage) + " timer: " + gTimer.seconds);
        Title(String.format("Time_Left: %s | USER: %s | Car: %s", gTimer.getTime(), session.user(),car1.getCarType()));
        repaint();
        if(gTimer.seconds > (timeComplete/2))
        {
            if(car1.findWhite(bgImage))
            {
                int points = 0;
                if(gTimer.getTime() > 0 && gTimer.getTime() < 10)
                {
                    points = 110;
                }else if(gTimer.getTime() > 10 && gTimer.getTime() < 20)
                {
                    points = 200;
                }else if(gTimer.getTime() > 20 && gTimer.getTime() < 30)
                {
                    points = 320;
                }else if(gTimer.getTime() > 30 && gTimer.getTime() < 40)
                {
                    points = 400;   
                }else if(gTimer.getTime() > 40 && gTimer.getTime() < 50)
                {
                    points = 520;   
                }
                TryAgainMsg("You won " + points);
            }else
            {
                if((gTimer.getTime()) == 0)
                {
                    gTimer.setTime(timeComplete);
                    TryAgainMsg("Game Over");   
                }
            }
        }
    }
    
    private void TryAgainMsg(String Title)
    {
        RacingTrack.track.gTimer.pause();
        if(YesOrNo(Title,"Try Again?"))
        {
            car1.StartPosition();
            gTimer.seconds=0;
            gTimer.Resume();
        }else
        {
             gTimer.Stop();
             MainTimer.stop();
             removeKeyListener(getCar());
             VehiclePicker picker = new VehiclePicker(session.user());
             Window window = SwingUtilities.getWindowAncestor(this);
             Frame frame = (Frame) window;        
             setVisible(false);
             Title(session.user());
             frame.showPanel(picker);
             //picker.setBackgroundImg("start.png");
             //picker.revalidate();
             //picker.repaint();
             picker.requestFocus();    
        }
    }
}
