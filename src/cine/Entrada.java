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
public class Entrada extends Producto {
    public static final int MAX_ENTRADAS = 200;
    protected static int ventas = 0;
    public Entrada() {
        super("Entrada", 2750f);
    }
    
    @Override
    public double vender() {
        this.ventas++;
        return precio;
    }
    
    public static int getVentas() {
        return ventas;
    }
    
    public static int restantes() {
        return MAX_ENTRADAS - ventas;
    }
    
    @Override
    public void evento() {
        System.out.println("Ups! El cliente ha perdido una entrada!");
        Estadisticas.entradaPerdida();
    }
}
