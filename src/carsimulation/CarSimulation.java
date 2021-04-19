/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 *
 * @author Shaklain
 */
public class CarSimulation extends Frame{
    
    public CarSimulation() {
        initComponents();
       // LoadBtn.setVisible(rootPaneCheckingEnabled);
        //StartBtn.setVisible(rootPaneCheckingEnabled);
        onLoad();
        //repaint();
    }
    
    private void onLoad()
    {
        //setBackgroundImg("start.png");
        setLocationRelativeTo(null);
        setVisible(true);
        addKeyListener(this);
        setFrame();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StartBtn = new javax.swing.JButton();
        LoadBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        StartBtn.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        StartBtn.setText("New Game");
        StartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartBtnActionPerformed(evt);
            }
        });

        LoadBtn.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        LoadBtn.setText("Load Game");
        LoadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LoadBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addComponent(StartBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(369, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(StartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

    }//GEN-LAST:event_formComponentResized

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved

    }//GEN-LAST:event_formMouseMoved

    private void StartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartBtnActionPerformed
        NewSession();
    }//GEN-LAST:event_StartBtnActionPerformed

    private void LoadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadBtnActionPerformed
        JFileChooser chooser = new JFileChooser(FileManager.CurrentDir());
        int returnVal = chooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if(file.getPath().endsWith(".bin"))
            {
                OpenSession(chooser.getSelectedFile().getAbsolutePath());
            }
        }
    }//GEN-LAST:event_LoadBtnActionPerformed
    
    private void NewSession()
    {
        removeKeyListener(this);
        Username user = new Username();
        showPanel(user);
        showAll(false, new JButton());
    }
    
    private void OpenSession(String FileName)
    {
        removeKeyListener(this);
        GameSession session = FileManager.Load(FileName);
        if(session != null)
        {
            RacingTrack Track1 = new RacingTrack(session, 60);
            showPanel(Track1);
            showAll(false, new JButton());
            Track1.requestFocus();   
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.
        int key = e.getKeyCode();
        Msgbox("Frame key pressed");
        if(key == KeyEvent.VK_ESCAPE)
        {
            
        }
    }
    
    public static void main(String[] args) {
        new CarSimulation();
    }
    
    public static Frame frame;
    public static JButton startBtn;
    public static JButton loadBtn;
    
    private void setFrame()
    {
        frame = this;
        startBtn = StartBtn;
        loadBtn = LoadBtn;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoadBtn;
    private javax.swing.JButton StartBtn;
    // End of variables declaration//GEN-END:variables

}
