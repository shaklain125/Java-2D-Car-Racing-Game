/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Shaklain
 */
public class VehicleOption extends  JButton{
    
    private ImageObject car1;
    
    public VehicleOption(String name, int _width, int _height)
    {
         car1 = new ImageObject(name, _width, _height,-40,42);
         Dimension size = new Dimension();
         size.width = _height;
         size.height = _width;
         setPreferredSize(size);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D) g;
        car1.initiate(-90, g2, this);
    }
}
