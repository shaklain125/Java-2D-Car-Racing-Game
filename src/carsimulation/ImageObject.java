/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;

/**
 *
 * @author Shaklain
 */
public class ImageObject {
    
    private BufferedImage img;
    private Graphics2D g2d;
    private ImageObserver o;
    private AffineTransform at;
    public double direction = 0;
    public double _x,_y;
    public int width, height;
    
    public ImageObject(String ImageName, int _width, int _height)
    {
        setImage(ImageName, _width, _height);
    }
    
    public ImageObject(String ImageName, int _width, int _height, double x, double y)
    {
        setImage(ImageName, _width, _height);
        _x = x;
        _y = y;
    }
    
    public void initiate(Graphics2D g2, ImageObserver _o)
    {
        properties(g2, _o);
        setDirection(direction);
    }
    
    public void initiate(double initial, Graphics2D g2, ImageObserver _o)
    {
        properties(g2, _o);
        rotate(initial);
        applyrotation();
        direction = initial;
    }
    
    public void setDirection(double angle) {
        rotate(angle);
        direction = angle;
        applyrotation();
    }
    
    public void setImage(String imgName, int w, int h)
    {
        img = getImage(imgName);
        width = w;
        height = h;
        img = resize(img, width, height);
    }
    
    public BufferedImage getImage()
    {
        return img;
    }
    
    private void properties(Graphics2D g2, ImageObserver _o)
    {
        g2d = g2;
        o = _o;
    }
    
    private BufferedImage resize(BufferedImage bimg, int _width, int _height) {
        Image tmp = bimg.getScaledInstance(_width, _height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resized.createGraphics();
        g2.drawImage(tmp, 0, 0, null);
        g2.dispose();
        return resized;
    }
    
    private void applyrotation()
    {
        g2d.drawImage(img, at, o);
        g2d.rotate(Math.toRadians(direction));
    }
    
    private void rotate(double degrees)
    {
        /*
        ImageIcon icon = new ImageIcon(img);
        BufferedImage blankcanvas = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) blankcanvas.getGraphics();
        g2.rotate(Math.toRadians(degrees), icon.getIconWidth()/2, icon.getIconHeight()/2);
        g2.drawImage(img, 0,0, o);
        img = blankcanvas;
        */
        at = AffineTransform.getTranslateInstance(_x, _y);
        at.rotate(Math.toRadians(degrees), img.getWidth() /2, img.getHeight() /2);
    }
    
    private BufferedImage getImage(String FileName)
    {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(getClass().getResourceAsStream(FileName));
        } catch (Exception e) {
        }
        return bimg;
    }
     
    public void draw()
    {
        g2d.drawImage(img, (int)_x, (int)_y, (int)width, (int)height, o);
    }
    
    public BufferedImage buffer(Image bimg)
    {
        if (bimg instanceof BufferedImage)
        {
            return (BufferedImage) bimg;
        }
        BufferedImage bimage = new BufferedImage(bimg.getWidth(o), bimg.getHeight(o), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(bimg, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
}
