/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

import com.sun.glass.events.KeyEvent;
import java.awt.Window;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author sa370
 */
public class Username extends Panel{

    /**
     * Creates new form Username
     */
    
    private String name;
    private String rightInput = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-_*";
    
    public Username() {
        super();
        initComponents();
    }
    
    private void setUName(String txt)
    {
        name = txt;
    }
    
    public void setText(String txt)
    {
        jTextField1.setText(txt);
    }
    
    public String getUName()
    {
        return name;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jTextField1.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel1.setText("User name:");

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));

        jButton1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jButton1.setText("Go Back [ESC]");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(235, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        int key = evt.getKeyCode();
        if(key == KeyEvent.VK_ENTER)
        {
            if(!jTextField1.getText().isEmpty())
            {
                if(isRightInput(jTextField1.getText()))
                {
                    setUName(jTextField1.getText());
                    ShowVehiclePicker();
                }else
                {
                   jLabel2.setText("Username contains invalid characters"); 
                }
            }else
            {
                jLabel2.setText("Username cannot null");
            }
        }else if(key == KeyEvent.VK_ESCAPE)
        {  
            Back();
        }else
        {
            jLabel2.setText("");
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void Back()
    {
        setVisible(false);
        CarSimulation.frame.revalidate();
        CarSimulation.frame.repaint();
        CarSimulation.frame.setTitle("");
        CarSimulation.frame.addKeyListener(CarSimulation.frame);
        CarSimulation.frame.showAll(true, new JButton()); 
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Back();
    }//GEN-LAST:event_jButton1ActionPerformed

    private boolean isRightInput(String txt)
    {
        for(char c: txt.toCharArray())
        {
            if(!rightInput.contains(String.valueOf(c)))
            {
                return false;
            }
        }
        return true;
    }
    
    private void ShowVehiclePicker()
    {
        removeKeyListener(this);
        VehiclePicker picker = new VehiclePicker(name);
        Window window = SwingUtilities.getWindowAncestor(this);
        Frame frame = (Frame) window;        
        setVisible(false);
        frame.showPanel(picker);
        //picker.setBackgroundImg("start.png");
        //picker.revalidate();
        //picker.repaint();
        picker.requestFocus();  
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
