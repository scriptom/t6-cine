/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine;

/**
 *
 * @author Usuario Estandar
 */
public class Tequeño extends Producto {
    protected static int ventas = 0;
    public Tequeño() {
        super("Tequeño", 6000f);
    }
    
    @Override
    public void evento() {
        // No hay evento para tequeño. El comportamiento es el mismo
        System.out.println("El cliente se llevo a gusto sus Tequeños");
    }
    
    @Override
    public double vender() {
        this.ventas++;
        return precio;
    }
    
    public static int getVentas() {
        return ventas;
    }
}
