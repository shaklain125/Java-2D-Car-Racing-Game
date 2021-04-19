/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;
import static carsimulation.RacingTrack.isFire;
import static carsimulation.RacingTrack.isMedical;
import static carsimulation.RacingTrack.isPolice;
import static carsimulation.RacingTrack.isSport;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author Shaklain
 */
public class VehiclePicker extends Panel{

    /**
     * Creates new form VehiclePicker
     */
    
    private String user;
    
    public VehiclePicker(String _Username) {
        super();
        initComponents();
        user = _Username;
        Title(user);
        onLoad();
    }
    
    public void onLoad()
    {
        ArrayList<String> Cars = new ArrayList<>();        
        for(int i = 1; i<=14; i++)
        {
            Cars.add("car_"+i);
        }
        for(String vehicle : Cars)
        {
            VehicleOption Carbtn = new VehicleOption(vehicle+".png", 155, 75);
            Carbtn.setName(vehicle);
            Carbtn.setToolTipText(getVehicleType(vehicle) + " : " + vehicle );
            Carbtn.addActionListener(this);
            add(Carbtn);
        }
        addKeyListener(this);
    }
    
    public static String getVehicleType(String CarName)
    {
        GameSession session = new GameSession(0, null, CarName);
        if(isPolice(CarName))
        {
            return new PoliceCar(session).getCarType();
        }else if (isMedical(CarName))
        {
            return new MedicalCar(session).getCarType();
        }else if (isSport(CarName))
        {
            return new SportCar(session).getCarType();
        }else if (isFire(CarName))
        {
           return new FireEngine(session).getCarType();
        }else
        {
            return new Car(session).getCarType();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e); //To change body of generated methods, choose Tools | Templates.
        if(!jTextField1.getText().isEmpty())
        {
            if(isNumber(jTextField1.getText()))
            {
                String vehicleName = ((VehicleOption)e.getSource()).getName();
                GameSession session = new GameSession(1, user, vehicleName);
                RacingTrack Track1 = new RacingTrack(session, Integer.valueOf(jTextField1.getText()));
                change(Track1);                
            }
        }
    }
    
    private boolean isNumber(String txt)
    {
        try {
            int a = Integer.valueOf(txt);
            if(a <= 0)
            {
                return false;
            }else
            {
                if(a <= 300)
                {
                    return true;   
                }else
                {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    
    private void changePanel(Panel p)
    {
        Window window = SwingUtilities.getWindowAncestor(this);
        Frame frame = (Frame) window;        
        setVisible(false);
        frame.revalidate();
        frame.repaint();
        frame.showPanel(p);
        p.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        jLabel1.setText("set Timer (s<=300)");
        jLabel1.setToolTipText("");
        add(jLabel1);

        jTextField1.setColumns(3);
        jTextField1.setToolTipText("");
        add(jTextField1);
    }// </editor-fold>//GEN-END:initComponents


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ESCAPE)
        {
            //Msgbox("backpace");
            Username u = new Username();
            u.setText(CarSimulation.frame.getTitle());
            CarSimulation.frame.setTitle("");
            removeKeyListener(this);
            change(u);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
