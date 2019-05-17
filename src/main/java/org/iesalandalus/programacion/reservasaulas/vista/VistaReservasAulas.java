/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.vista;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.OperationNotSupportedException;
//import modelo.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.vista.Consola;
import org.iesalandalus.programacion.reservasaulas.controlador.ControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;

/**
 *
 * @author Manuel
 */
public class VistaReservasAulas implements IVistaReservasAulas {

	
	private static final String ERROR = "ERROR: ";
	private static final String NOMBRE_VALIDO = "Manuel";
	private static final String CORREO_VALIDO = "manuel.alcala@caritasalmeria.es";
	
       private IControladorReservasAulas controlador;
        

	public VistaReservasAulas() {
		Opcion.setVista(this);
	}

	
    @Override
	public void comenzar() {
	int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

    @Override
	public void salir() {
                controlador.salir();
		
	}
        
        
        
        
    @Override
        public void setControlador(IControladorReservasAulas controlador) {
		this.controlador = controlador;
	}
        
        
        
//aulas
    @Override
	public void insertarAula() {
		Consola.mostrarCabecera("Insertar aula");
		try {
			Aula aula = Consola.leerAula();
			controlador.insertarAula(aula);
			System.out.println("Aula insertada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

    @Override
	public void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
			Aula aula= Consola.leerAula();
                        controlador.borrarAula(aula);
			System.out.println("Aula borrada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

    @Override
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		Aula aula = null;
		try {
			aula = Consola.leerAula();
			aula = controlador.buscarAula(aula);
			if (aula != null) {
				System.out.println("El aula buscado es: " + aula);
			} else {
				System.out.println("No existe ningún aula con dicho nombre.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

    @Override
	public void listarAulas() {
		Consola.mostrarCabecera("Listar aulas");
		List<String> aulas = controlador.representarAulas();
		if (aulas.size() > 0) {
			for (String aula : aulas) {
				System.out.println(aula);
			}
		} else {
			System.out.println("No hay aulas que listar.");
		}
	}
//profesores    
        /*
    @Override
        public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
                        controlador.insertarProfesor(profesor);                   	
                       System.out.println("Profesor insertado correctamente.");
                }catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}*/
        
        
        
        @Override
        public void insertarProfesor() {
    
            try {
                Consola.mostrarCabecera("Insertar profesor");
                
                Profesor profesor = Consola.leerProfesor();
          
                controlador.insertarProfesor(profesor);
            
                System.out.println("Profesor insertado correctamente.");
            } catch (OperationNotSupportedException | IllegalArgumentException ex) {
                Logger.getLogger(VistaReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
            }
           
                
	}
        
        

    @Override
	public void borrarProfesor() {
                String nombre="";
		Consola.mostrarCabecera("Borrar profesor");
		try {
                        nombre=Consola.leerNombreProfesor();
			Profesor profesor =new Profesor(nombre,CORREO_VALIDO);
			controlador.borrarProfesor(profesor);
			System.out.println("Profesor borrado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

    @Override
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		Profesor profesor = null;
                String nombre="";
		try {
			nombre = Consola.leerNombreProfesor();
                        profesor=new Profesor(nombre,CORREO_VALIDO);
			profesor = controlador.buscarProfesor(profesor);
			if (profesor != null) {
				System.out.println("El profesor buscado es: " + profesor);
			} else {
				System.out.println("No existe ningún profesor con dicho nombre.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

    @Override
	public void listarProfesores() {
		Consola.mostrarCabecera("Listar profesores");
		List<String> profesores = controlador.representarProfesores();
		if (profesores.size() > 0) {
			for (String profesor : profesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesores que listar.");
		}
	}
       
            
            
        



// código profesor
private Reserva leerReserva(Profesor profesor) {
                
                Reserva reserva =null;
                
                Aula aula=Consola.leerAula();
                aula=controlador.buscarAula(aula);
                Permanencia permanencia=Consola.leerPermanencia();
                
                try{
                    
                   reserva = new Reserva(profesor, aula, permanencia);
                } catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
                }
                return reserva;
                
                
}        
                //código del profe
    @Override
    public void realizarReserva() {
	Consola.mostrarCabecera("Realizar reserva");
		
                     
                String nombre = Consola.leerNombreProfesor();
			Profesor profesor = controlador.buscarProfesor(new Profesor(nombre, CORREO_VALIDO));
			/*if (profesor == null) {
				throw new IllegalArgumentException();
			} else {  */
				
                        try {
                             Reserva reserva = leerReserva(profesor);
				if (reserva != null) {
                                    try {
                                        controlador.realizarReserva(reserva);
                                        System.out.println("Reserva Realizada");
                                    } catch (OperationNotSupportedException e) {
                                        //Logger.getLogger(VistaReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
                                     System.out.println("ERROR: "+ e.getMessage());
                                    }
				                       }

            
        } catch (Exception e) {
                            System.out.println("ERROR: "+ e.getMessage());
        }
                        


		
	}




  
    @Override
    public void anularReserva() {
		Consola.mostrarCabecera("ANULAR RESERVA");
		Profesor profesorABuscar = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
		boolean lecturaCorrecta = true;
		if(controlador.buscarProfesor(profesorABuscar)==null) {
			System.out.println("El profesor introducido no existe.");
			lecturaCorrecta = false;
		}
		Reserva reserva = null;
		if(lecturaCorrecta) {
			reserva = leerReserva(profesorABuscar);
			if(reserva==null)
				System.out.println("El aula introducida no existe.");
		}
		if(reserva==null)
			System.out.println("La reserva no se pudo anular.");
		else {
			try {
				controlador.anularReserva(reserva);
                                System.out.println("Reserva anulada correctamente.");
			} catch (OperationNotSupportedException e) {
				System.out.println(ERROR + e.getMessage());
			}
			
		}
	}


    @Override
    public void listarReservas() {
		Consola.mostrarCabecera("LISTAR RESERVAS");
		List<String> reservas = controlador.representarReservas();
		if(reservas.isEmpty())
			System.out.println("No hay ninguna reserva hecha.");
		else
			for ( String reserva :reservas)
                            {
                                System.out.println(reserva);
                            }
	}
        

    @Override
    public void listarReservasAula() {
		Consola.mostrarCabecera("LISTAR RESERVAS AULA");
		Aula aula = new Aula(Consola.leerAula());
		boolean lecturaCorrecta = true;
		if(controlador.buscarAula(aula)==null){
			System.out.println(ERROR + "El aula introducida no existe.");
			lecturaCorrecta = false;
		}
		List<Reserva> reservas = controlador.getReservasAula(aula);
		if(lecturaCorrecta && reservas.isEmpty())
			System.out.println("El aula indicada no está reservada.");
		if(lecturaCorrecta) {
                    reservas.forEach((_item) -> {
                        System.out.println(reservas);
                    });
		}
	}


    @Override
    public void listarReservasProfesor() {
		Consola.mostrarCabecera("LISTAR RESERVAS PROFESOR");
		Profesor profesor = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
		boolean lecturaCorrecta = true;
		if(controlador.buscarProfesor(profesor)==null){
			System.out.println(ERROR + "El profesor introducida no existe.");
			lecturaCorrecta = false;
		}
		List<Reserva> reservas = controlador.getReservasProfesor(profesor);
		if(lecturaCorrecta && reservas.isEmpty())
			System.out.println("El profesor indicado no tiene ningún aula reservada.");
		if(lecturaCorrecta) {
	 reservas.forEach((_item) -> {
                        System.out.println(reservas);
                    });
		}
	}

    @Override
    public void listarReservasPermanencia() {
		Consola.mostrarCabecera("LISTAR RESERVAS PERMANENCIA");
		Permanencia permanencia = Consola.leerPermanencia();
		List<Reserva> reservas = controlador.getReservasPermanencia(permanencia);
		if(reservas.isEmpty())
			System.out.println("En ese tramo no hay ningún aula reservada.");
		reservas.forEach((_item) -> {
                        System.out.println(reservas);
                    });
		}
	

    @Override
    public void consultarDisponibilidad() {
		Consola.mostrarCabecera("CONSULTAR DISPONIBILIDAD");
		Aula aula = new Aula(Consola.leerAula());
		boolean lecturaCorrecta = true;
		if(controlador.buscarAula(aula) == null) {
			System.out.println(ERROR + "El aula indicada no existe.");
			lecturaCorrecta = false;
		}
		if(lecturaCorrecta) {
			Permanencia permanencia =Consola.leerPermanencia();
			boolean disponible = controlador.consultarDisponibilidad(aula, permanencia);
			if(disponible)
				System.out.println("El aula consultada está disponible para ese momento");
			else
				System.out.println("El aula consultada no está disponible para ese momento.");
		}
	}

    
}




