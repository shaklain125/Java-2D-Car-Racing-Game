/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsimulation;

public class Vector {

    private double x, y, angle, radius;

    public Vector() {
        x = 0;
        y = 0; 
        angle = 0; 
        radius = 0;
    }

    public Vector(double scalar1, double scalar2) {
        radius = scalar1;
        angle = scalar2;
        x = radius * Math.cos(angle);
        y = radius * Math.sin(angle);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}