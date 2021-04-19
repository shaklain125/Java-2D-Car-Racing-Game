/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

/**
 *
 * @author Shaklain
 */
public class SportCar extends  Car{
    
    public SportCar(GameSession session) {
        super(session);
        setAcceleration(0.02);
    }
    
}
