/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine;

/**
 * Clase abstracta para objetos que tienen un valor contable,
 * es decir, tienen un precio
 * @author Usuario Estandar
 */
public abstract class Contable {
    protected double precio;
    protected double factorAumento = 0;
    protected double factorDescuento = 0;
    
    public void setPrecio(double precio) {
        if (precio > 0f)
            this.precio = precio;
    }
    
    public double getPrecioNeto() {
        return precio;
    }
    
    public double getFactorAumento() {
        return factorAumento;
    }
    
    public void setFactorAumento(int factorAumento) {
        if ( factorAumento >= 0 && factorAumento <= 100 )
            this.factorAumento = (double)factorAumento / 100;
    }
    
    public double aplicarAumento(double precio) {
        return precio * factorAumento;
    }
    
    public double getFactorDescuento() {
        return factorDescuento;
    }
    
    public void setFactorDescuento(int factorDescuento) {
        if ( factorDescuento >= 0 && factorDescuento <= 100 )
            this.factorDescuento = (double)factorDescuento / 100;
    }
    
    public double aplicarDescuento(double precio) {
        return precio * factorDescuento;
    }
    
    public double getPrecioFinal() {
        return precio + aplicarAumento(precio) - aplicarDescuento(precio);
    }
}
