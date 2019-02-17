/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine;

import java.util.Scanner;
import java.io.StringReader;
import java.io.IOException;

/**
 * Menu de seleccion simple.
 * @author Tomás El Fakih
 */
public class Menu {
    private String planteamiento;
    private String[] opciones;
    private int eleccion;
    
    public final int OPCION_INVALIDA = -1;
    /**
     * Constructor de la clase. De una vez inicia el menu
     * @param planteamiento
     * @param opciones 
     */
    public Menu(String planteamiento, String ...opciones) {
        set(planteamiento, opciones);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(planteamiento);
        for (int i = 0; i < opciones.length; i++)
            if (!opciones[i].isEmpty())
                sb.append("\n\t").append(i).append(" ").append(opciones[i]);
            
        return sb.toString();
    }
    
    /**
     * Asegura que la entrada del usuario sea un numero de opcion valido
     * @param entrada
     * @return 
     * @throws java.io.IOException 
     */
    protected boolean validarEntrada(String entrada) {
        StringReader sr = new StringReader(entrada);
        int currentChar;
        eleccion = 0;
        // Nos detenemos cuando la lectura sea -1
        try {
            while( (currentChar = sr.read()) != -1 ) {
                // si no es un numero, regresamos false de una vez
                if ( currentChar < 48 || currentChar > 57 )
                    return false;
                // si es un numero, lo sumamos a la opcion que tenemos (Restamos 48 por ser este el 0 en ASCII
                eleccion = eleccion * 10 + (currentChar - 48 );
            }
        } catch (IOException e) {
            sr.close();
            return false;
        }
        
        // Si llegamos hasta aca, deberiamos tener un numero concreto. Validemos que este en las opciones
        return ( eleccion >= 0 && eleccion < opciones.length );
    }
    
    /**
     * Garantiza la devolucion de una opcion valida del menu
     * @return int eleccion La seleccion del usuario
     */
    public int getEleccion() {
        Scanner scanner = new Scanner(System.in);
        boolean valid;
        do {
            if ( ! (valid = validarEntrada(scanner.next()) ) )
                System.out.println("Opcion invalida. Vuelva a intentar");
        } while ( ! valid );
//        scanner.close();
        return eleccion;
    }
    
    /**
     * Coloca un nuevo planteamiento
     * @param planteamiento
     * @param opciones 
     */
    public void set(String planteamiento, String ...opciones) {
        this.planteamiento = planteamiento;
        this.opciones = opciones;
    }
}