/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine;


/**
 * Un pedido es una coleccion de productos, con un precio final asignado
 * @author Usuario Estandar
 */
public class Pedido extends Contable {
    private Producto[] productos;
    private int indice = 0;
    
    public Pedido(int cantProductos) {
        productos = new Producto[cantProductos];
    }
    
    public void addProducto(Producto producto) {
        productos[indice++] = producto;
        precio += producto.getPrecioNeto();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Producto producto: productos)
            sb.append(producto).append("\n");
        sb.append("Precio neto: ").append(getPrecioNeto()).append("\n");
        if (factorAumento*100 != 0)
            sb.append("Tiene un aumento de ").append(factorAumento*100).append("%\n");
        if (factorDescuento*100 != 0)
            sb.append("Tiene un descuento de ").append(factorDescuento*100).append("%\n");
        sb.append("Total: ").append(getPrecioFinal()).append("\n");
        return sb.toString();
    }
    
    public Producto[] getProductos() {
        return productos;
    }

}
