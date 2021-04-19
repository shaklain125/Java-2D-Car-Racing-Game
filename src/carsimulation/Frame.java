/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author Shaklain
 */
public class Frame extends  JFrame implements  ActionListener, KeyListener{
    
    private Image bgImage;
    
    public Frame()
    {
        setFocusable(true);
        requestFocusInWindow();
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(bgImage, 0, 0, this);
    }
    
    public void setBackgroundImg(String backgroundImage)
    {
        bgImage = getBackground(backgroundImage);
    }
    
    private Image getBackground(String FileName)
    {
        ImageIcon image = new ImageIcon(getClass().getResource(FileName));
        return image.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
    }
    
    
    public void showAll(boolean visibility, Object comp) 
    {
         for(Component c: this.getContentPane().getComponents())
         {
             if(c.getClass() == comp.getClass())
             {
                 c.setVisible(visibility);    
             }  
         }   
    }
    
    public static String Input(Object Title,Object InputMsg)
    {
        return JOptionPane.showInputDialog(null,InputMsg,String.valueOf(Title),JOptionPane.QUESTION_MESSAGE);
    }
    
    public void showPanel(JPanel p)
    {
        setLayout(new BorderLayout());
        add(p, BorderLayout.CENTER);
    }
    
    public static void Msgbox(Object Msg)
    {
        String Title = "";
        JOptionPane.showMessageDialog(null, Msg, Title, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}   

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
