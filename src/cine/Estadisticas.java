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
public final class Estadisticas {
    protected static double ingresosTaquilla = 0;
    protected static double ingresosOnline = 0;
    protected static double comisionOnline = 0;
    protected static double totalIngresos = 0;
    protected static int cotufasCaidas = 0;
    protected static int refrescosBotados = 0;
    protected static int entradasPerdidas = 0;
    
    public static String resumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nIngresos por taquilla: " + ingresosTaquilla)
            .append("\nIngresos en linea: " + ingresosOnline) 
            .append("\nComision por ventas en linea: " + comisionOnline)
            .append("\nIngresos totales: " + totalIngresos)
            .append("\nCotufas caidas: " + cotufasCaidas)
            .append("\nRefrescos botados: " + refrescosBotados)
            .append("\nEntradas perdidas: " + entradasPerdidas);
        return sb.toString();
    }
    
    protected static void actualizarTotal() {
        totalIngresos = ingresosOnline + ingresosTaquilla;
    }
    
    public static void ventaTaquilla(double ingresos) {
        ingresosTaquilla += ingresos;
        actualizarTotal();
    }
    
    public static void ventaOnline(double ingresos) {
        ingresosOnline += ingresos;
        actualizarTotal();
    }
    
    public static void comisionesOnline(double comision) {
        comisionOnline += comision;
    }
    
    public static void cotufaCaida() {
        cotufasCaidas++;
    }
    
    public static void refrescoBotado() {
        refrescosBotados++;
    }
    
    public static void entradaPerdida() {
        entradasPerdidas++;
    }
}
