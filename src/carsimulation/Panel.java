/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Shaklain
 */
public class Panel extends JPanel implements ActionListener,KeyListener{
    
    public int width;
    public int height;
    public Image bgImage;
    
    public Panel()
    {
        super();
        setFocusable(true);
        requestFocusInWindow();
        setSize(640, 480);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(bgImage, 0, 0, this);
    }
    
    public void setBackgroundImg(String FileName)
    {
        ImageIcon image = new ImageIcon(getClass().getResource(FileName));
        bgImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public  static void showAll(Container parent, boolean visibility) 
    {
        if (parent instanceof JFrame) 
        {
            JFrame frame = (JFrame) parent;
            for(Component c: frame.getContentPane().getComponents())
            {
                c.setVisible(visibility);
            }
        }else
        {
            for(Component c: parent.getComponents())
            {
                c.setVisible(visibility);
            }
        }
    }
    
    public static void Msgbox(Object Msg)
    {
        String Title = "";
        JOptionPane.showMessageDialog(null, Msg, Title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static String Input(Object Title,Object InputMsg)
    {
        return JOptionPane.showInputDialog(null,InputMsg,String.valueOf(Title),JOptionPane.QUESTION_MESSAGE);
    }
    
    public  static boolean YesOrNo(Object Title, Object Msg)
    {
        int result = JOptionPane.showConfirmDialog(null, Msg, String.valueOf(Title), JOptionPane.YES_NO_OPTION);
        if(result== JOptionPane.YES_OPTION)
        {
            return true;
        }else
        {
            return false;
        }
    }
    
    public void setSize(int _width, int _height)
    {
       width = _width;
       height = _height;
       setBounds(0, 0, width , height);
    }
    
    public void change(Panel p)
    {
        setVisible(false);
        CarSimulation.frame.revalidate();
        CarSimulation.frame.repaint();
        CarSimulation.frame.showPanel(p);
        p.requestFocus();
    }
    
    public static void Title(String txt)
    {
        //Frame frame = (Frame) SwingUtilities.getWindowAncestor();
        //frame.setTitle(txt);
        CarSimulation.frame.setTitle(txt);
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
