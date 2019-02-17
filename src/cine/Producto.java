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
public abstract class Producto extends Contable {
    private String nombre;
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.factorAumento = 0;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    @Override 
    public String toString() {
        return getNombre() + " - " + precio;
    }
    
    public abstract double vender();
    
    /**
     * Devuelve la cantidad de ventas de una clase de producto. Debe ser re-implentado
     * @return 
     */
    public static int getVentas(){
        return 0;
    };
    
    public abstract void evento();
    
    
    
}
