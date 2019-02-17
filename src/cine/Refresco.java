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
public class Refresco extends Producto {
    private String tipo;
    protected static int ventas = 0;
    protected static int ventas7up = 0;
    protected static int ventasNestea = 0;
    protected static int ventasPepsi = 0;
    public Refresco(String tipo) {
        super("Refresco", 5000f);
        setTipo(tipo);
    }
    
    public void setTipo(String tipo) {
        if ( "7up".equalsIgnoreCase(tipo) || "pepsi".equalsIgnoreCase(tipo) || "nestea".equalsIgnoreCase(tipo) )
            this.tipo = tipo;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    @Override
    public double vender() {
        switch(getTipo()) {
            case "7up":
                ventas7up++;
                break;
            case "nestea":
                ventasNestea++;
                break;
            case "pepsi":
                ventasPepsi++;
                break;
        }
        this.ventas++;
        return precio;
    }
    
    public static int getVentas() {
        return ventas;
    }
    
    public static int getVentasPepsi() {
        return ventasPepsi;
    }
    
    public static int getVentas7up() {
        return ventas7up;
    }
    
    public static int getVentasNestea() {
        return ventasNestea;
    }
    
    @Override
    public String getNombre() {
        String str = super.getNombre();
        if ("nestea".equalsIgnoreCase(getTipo()))
            str += " de limon";
        return str;
    }
   
    @Override
    public void evento() {
        if (getTipo().equalsIgnoreCase("nestea"))
            System.out.println("El cliente retiro a gusto su nestea de durazno");
        else
            System.out.println("Al cliente se le cayo su refresco! Que desgracia...");
        Estadisticas.refrescoBotado();
    }
}
