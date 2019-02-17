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
public class Cotufa extends Producto {
    public enum Tamaño {
        MEDIANA, GRANDE
    };
    
    private Tamaño tamaño;
    protected static int ventas = 0;
    protected static int ventasGrandes = 0;
    protected static int ventasMedianas = 0;
    
    public Cotufa(Tamaño tamaño) {
        super("Cotufas ".concat(tamaño.toString().toLowerCase().concat("s")), (tamaño == Tamaño.MEDIANA ? 3500f : 4800));
        this.tamaño = tamaño;
    }
    
    public Tamaño getTamaño() {
        return tamaño;
    }
    
    public void setTamano(Tamaño tamaño) {
        this.tamaño = tamaño;
    }
    
    @Override
    public double vender() {
        this.ventas++;
        if (getTamaño() == Tamaño.GRANDE)
            ventasGrandes++;
        else
            ventasMedianas++;
        return precio;
    }
    
    public static int getVentas() {
        return ventas;
    }
    
    public static int getVentasGrandes() {
        return ventasGrandes;
    }
    
    public static int getVentasMedianas() {
        return ventasMedianas;
    }
    
    @Override
    public void evento() {
        System.out.println("Ups! Al cliente se le han caido las cotufas!");
        Estadisticas.cotufaCaida();
    }
    
}
