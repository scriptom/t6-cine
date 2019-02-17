/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine;

import java.util.Scanner;
import java.util.LinkedList;

/**
 *
 * @author Usuario Estandar
 */
public class Cine {
    public static Menu menu = new Menu(null);
    public static Scanner scanner = new Scanner(System.in);
    public static LinkedList<Pedido> pedidos = new LinkedList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while(true){
            System.out.println("Bienvenido al sistema de Cine");
            menu.set("Que desea hacer?", "Pedido en linea", "Pedido por taquilla");
            System.out.println(menu);
            int eleccion = menu.getEleccion();
            System.out.println("Ingrese la cantidad de productos que comprara");
            int cantProductos = 0;
            do {
                cantProductos = scanner.nextInt();
                if (cantProductos < 0 )
                    System.out.println("No se puede comprar una cantidad negativa de productos");
            } while(cantProductos < 0);
            Pedido pedido = new Pedido(cantProductos);
            
            if ( eleccion == 0 ) {
                System.out.println("Ha escogido pedido en linea");
                pedido.setFactorAumento(8);
            }
            else
                System.out.println("Ha escogido pedido por taquilla");
            for (int i = 0; i < cantProductos;) {
                menu.set("Escoja uno de los productos siguientes", "Entradas", "Refrescos", "Cotufas", eleccion == 1 ? "Tequeños" : "");
                System.out.println(menu);
                switch(menu.getEleccion()) {
                    case 0: 
                        System.out.println("Introduzca la cantidad de entradas a comprar");
                        int numEntradas;
                        do {
                            numEntradas = scanner.nextInt();
                            if (numEntradas < 0)
                                System.out.println("No se puede comprar un numero negativo. Vuelva a intentar");
                            else if (numEntradas > Entrada.MAX_ENTRADAS)
                                System.out.println("No se pueden comprar mas de 200 entradas! Vuelva a intentar");
                            else if (numEntradas > cantProductos - i)
                                System.out.println("No puede comprar esta cantidad porque excede el limite de este pedido");
                            else if (numEntradas > Entrada.restantes()) {
                                System.out.println("No quedan tantas entradas. Se le daran las restantes");
                                numEntradas = Entrada.restantes();
                            }
                        } while (numEntradas < 0 || numEntradas > Entrada.MAX_ENTRADAS || numEntradas > cantProductos - i );
                        // La cantidad de entradas es valida para este punto. Las añadimos al pedido
                        for (int j = 0; j < numEntradas; j++)
                            pedido.addProducto(new Entrada());
                        i += numEntradas;
                        break;
                    case 1:
                        System.out.println("Introduzca la cantidad de refrescos a comprar");
                        int numRefrescos;
                        do {
                            numRefrescos = scanner.nextInt();
                            if (numRefrescos < 0)
                                System.out.println("No se puede comprar un numero negativo. Vuelva a intentar");
                            else if (numRefrescos > cantProductos - i) 
                                System.out.println("No puede comprar esta cantidad porque excede el limite de este pedido");
                        } while (numRefrescos < 0 || numRefrescos > cantProductos - i);
                        for (int j = 0; j < numRefrescos; j++){
                            menu.set("Escoja el sabor de su refresco (" + (j+1) + ")", "Pepsi", "7UP", "Nestea");
                            System.out.println(menu);
                            switch(menu.getEleccion()) {
                                case 0:
                                    pedido.addProducto(new Refresco("pepsi"));
                                    break;
                                case 1:
                                    pedido.addProducto(new Refresco("7up"));
                                    break;
                                case 2:
                                    pedido.addProducto(new Refresco("nestea"));
                                    break;
                            }
                        }
                        i += numRefrescos;
                        break;
                    case 2:
                        System.out.println("Introduzca la cantidad de cotufas a comprar");
                        int numCotufas;
                        do {
                            numCotufas = scanner.nextInt();
                            if (numCotufas < 0)
                                System.out.println("No se puede comprar un numero negativo. Vuelva a intentar");
                            else if (numCotufas > cantProductos - i)
                                System.out.println("No puede comprar esta cantidad porque excede el limite de este pedido");
                        } while (numCotufas < 0 || numCotufas > cantProductos - i );
                        for (int j = 0; j < numCotufas; j++) {
                            menu.set("Escoja el tamaño de sus cotufas (" + (j+1) + ")", "Medianas", "Grandes");
                            System.out.println(menu);
                            pedido.addProducto(new Cotufa( menu.getEleccion() == 0 ? Cotufa.Tamaño.MEDIANA : Cotufa.Tamaño.GRANDE ));
                        }
                        i += numCotufas;
                        break;
                    case 3:
                        System.out.println("Introduzca la cantidad de tequeños a comprar");
                        int numTequeños;
                        do {
                            numTequeños = scanner.nextInt();
                            if (numTequeños < 0)
                                System.out.println("No se puede comprar un numero negativo. Vuelva a intentar");
                            else if (numTequeños > cantProductos - i)
                                System.out.println("No puede comprar esta cantidad porque excede el limite de este pedido");
                        } while (numTequeños < 0 || numTequeños > cantProductos - i );
                        for (int j = 0; j < numTequeños; j++)
                            pedido.addProducto(new Tequeño());
                        i += numTequeños;
                        break;
                }
            }
            if (esFibonacci(pedidos.size())) {
                System.out.println("Felicidades! Su orden es un numero de Fibonacci. Su compra le salra a mitad de precio");
                pedido.setFactorDescuento(50);
            }
            System.out.println("Resumen de su compra (#" + pedidos.size() + ")");
            System.out.println(pedido);
            menu.set("Seguro que desea realizar la compra?", "No", "Si");
            System.out.println(menu);
            if ( menu.getEleccion() == 1 ) {
                if (eleccion == 0) {
                    Estadisticas.ventaOnline(pedido.getPrecioFinal());
                    Estadisticas.comisionesOnline(pedido.aplicarAumento(pedido.getPrecioNeto()));
                } else
                    Estadisticas.ventaTaquilla(pedido.getPrecioFinal());
                for (Producto producto: pedido.getProductos()) {
                    producto.vender();
                    double rand = 0;
                    do
                        rand = Math.random() * 100;
                    while (rand > 30);
                    if ( esPrimo((int)rand) )
                        producto.evento();
                    else 
                        System.out.println("El cliente se llevo a gusto su " + producto.getNombre());
                }
                pedidos.add(pedido);
            } else
                System.out.println("Vale! Ha rechazado la compra");
            menu.set("Que desea hacer ahora?", "Agregar otro cliente", "Terminar con el dia");
            System.out.println(menu);
            if (menu.getEleccion() == 1)
                break;
        }
        StringBuilder resumen = new StringBuilder("Resumen del dia\n");
        resumen.append("Total articulos vendidos").append(Producto.getVentas());
        resumen.append("\nEntradas vendidas: ").append(Entrada.getVentas());
        resumen.append("\nRefrescos totales vendidos: ").append(Refresco.getVentas());
        resumen.append("\nPepsis vendidos: ").append(Refresco.getVentasPepsi());
        resumen.append("\n7up vendidos: ").append(Refresco.getVentas7up());
        resumen.append("\nNestea vendidos: ").append(Refresco.getVentasNestea());
        resumen.append("\nCotufas totales vendidas: ").append(Cotufa.getVentas());
        resumen.append("\nCotufas grandes vendidas: ").append(Cotufa.getVentasGrandes());
        resumen.append("\nCotufas medianas vendidas: ").append(Cotufa.getVentasMedianas());
        resumen.append("\nTequeños vendidos: ").append(Tequeño.getVentas());
        System.out.println(resumen.toString());
        System.out.println(Estadisticas.resumen());
        System.out.println("Hasta luego!");
    }
    
    public static boolean esFibonacci(int num) {
        if (num == 0 || num == 1) return true;
        int fibo1 = 0, fibo2 = 1, fibon = 1;
        while (fibon < num) {
            fibon = fibo1 + fibo2;
            fibo2 = fibo1;
            fibo1 = fibon;
        }
        
        return fibon == num;
    }
    
    public static boolean esPrimo(int n) {
        for (int i = 2; i < n/2; i++)
            if ( i % 2 == 0 )
                return false;
        return true;
    }
}
