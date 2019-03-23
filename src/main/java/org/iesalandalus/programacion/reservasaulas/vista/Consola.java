/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static java.time.temporal.TemporalQueries.localDate;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Tramo;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.utilidades.Entrada;

/**
 *
 * @author Manuel
 */
public class Consola {

	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    

//Para evitar que se cree el constructor por defecto
private Consola() {
		
}

public static void mostrarMenu() {
		mostrarCabecera("Gestión para profesores de reserva de aula");
		int i=0;
                for (Opcion opcion: Opcion.values()) {
                    System.out.println(i + "." + opcion);
                    //System.out.println( i  opcion);                                               
                        i++;
		}
}

public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String cadena = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(cadena, 0).replace("0", "-"));
	}
	
public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.println("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
}

public static Aula leerAula() {
		System.out.println("Introduce el nombre del aula: ");
		String nombreAula = Entrada.cadena();
                System.out.println("Introduce el número de puestos: ");
                int puestos=Entrada.entero();
		return new Aula(nombreAula, puestos);
	}

public static String leerNombreAula() {
		String aula;
		do {
			System.out.println("Introduce el aula: ");
			aula = Entrada.cadena();
		} while (aula.trim().equals(""));
		
		return aula;
        }            

public static Profesor leerProfesor() {
		System.out.println("Introduce el nombre: ");
		String nombre = Entrada.cadena();
          	System.out.println("Introduce el correo: ");
		String correo = Entrada.cadena();
		System.out.println("Introduce el teléfono: ");
		String telefono = Entrada.cadena();

		return new Profesor(nombre, correo,telefono);
	}

public static String leerNombreProfesor() {
		String nombre;
		do {
			System.out.println("Introduce el nombre: ");
			nombre = Entrada.cadena();
		} while (nombre.trim().equals(""));
		
		return nombre;
	}


public static Tramo leerTramo() {
		//System.out.println("Introduzca turno de mañana o de tarde: ");
                Tramo turno = Tramo.MANANA;//la inicializo por peticion del IDE, no era mi idea dar nungún valor inicial, pero me quita el error.
                int opcion;
            do 
        {
            System.out.println("Introduzca turno de 1-mañana o 2-tarde(: ");
            opcion = Entrada.entero();
	} while (opcion < 1 || opcion > 2);
            
            switch (opcion) 
        {
            
            case 1:
               
                turno = Tramo.MANANA;
                           
           
                break;      
            case 2:                        
                turno = Tramo.TARDE;
                break;
            }    
                      
                       
		
            return  turno;
}
            
public static String leerDia() {
		System.out.println("Introduce la fecha (dd/mm/aaaa):");
		return Entrada.cadena();
	}


public static String leerHora() {
		System.out.println("Introduce la hora (hh:mm):");
		return Entrada.cadena();
	}

public static Permanencia leerPermanencia() {
		Permanencia permanencia = null;
		int indice = elegirPermanencia();
		if(indice == 1) {
			permanencia = new PermanenciaPorTramo(leerDia(), leerTramo());
		}
		if (indice == 2) {
			permanencia = new PermanenciaPorHora(leerDia(), leerHora());
		}
		return permanencia;
	}
	
	public static int elegirPermanencia() {
		int indice;
                
                System.out.println("Eliga reserva por tramo(1) o por hora(2)");
                indice = Entrada.entero();
		while (indice < 1 && indice > 2) 
                        {
			System.out.println("Opcion no valida:1-Tramo ");
                        System.out.println("1-Por tramo ");
                        System.out.println("2-Por hora ");
			indice = Entrada.entero();
                        }
		return indice;
	}


}





