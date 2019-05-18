/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio.ficheros.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import java.time.LocalDate;


/**
 *
 * @author Manuel
 */
public class Reservas {
    
    private static final String NOMBRE_FICHERO_RESERVAS = "ficheros/reservas.dat";
        
     //declaracion arraylist
   private List<Reserva> coleccionReservas;
   private static final float MAX_PUNTOS_PROFESOR_MES=200;
    
    //contructor por defecto
    
    public Reservas() {
		this.coleccionReservas = new ArrayList<Reserva>();
	}
    
    //constructor copia
	public Reservas (Reservas reservas) {
		this.setReservas(reservas);
	}
         
    //implementar set
    
    private void setReservas(Reservas reservas) {
		if (reservas == null) {
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		}
		this.coleccionReservas = this.copiaProfundaReservas(reservas.coleccionReservas);
		
    }
    //copiaProfunda

    private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> otrasReservas = new ArrayList<>() ;
		for (Reserva reserva : reservas) {
			otrasReservas.add(new Reserva(reserva));
		}
		return otrasReservas;
    }
    
    //get
	public List<Reserva> getReservas() {
		return this.copiaProfundaReservas(this.coleccionReservas);
	}
	
    //getNumReservas
	public int getNumReservas() {
		return coleccionReservas.size();
	}
	//insertar
        
        
        public void insertar(Reserva reserva) throws OperationNotSupportedException {
           
              //si es nula
            if (reserva == null) {
                        throw new IllegalArgumentException("No se puede realizar una reserva nula.");
                    }                     
            //si ya existe
            if (this.coleccionReservas.contains(reserva)) {
		        throw new OperationNotSupportedException("La reserva ya existe.");
                    }
            //si no es para el próximo mes
            if (!esMesSiguienteOPosterior(reserva)) {		
                        throw new OperationNotSupportedException("Sólo se pueden hacer reservas para el mes que viene o posteriores.");
                    }
            //si no quedan puntos al profesor
            if (getPuntosGastadosReserva(reserva) > MAX_PUNTOS_PROFESOR_MES) {
		        throw new OperationNotSupportedException("Esta reserva excede los puntos máximos por mes para dicho profesor.");
                    }
         
            	Reserva reservaRealizada = getReservaDia(reserva.getPermanencia().getDia());
		
            if (reservaRealizada != null) {
		    if (reservaRealizada.getPermanencia() instanceof PermanenciaPorTramo && reserva.getPermanencia() instanceof PermanenciaPorHora) {
                                throw new OperationNotSupportedException("Ya se ha realizado una reserva por tramo para este día y aula.");
			}
                    if (reservaRealizada.getPermanencia() instanceof PermanenciaPorHora && reserva.getPermanencia() instanceof PermanenciaPorTramo) {
                        	throw new OperationNotSupportedException("Ya se ha realizado una reserva por hora para este día y aula.");
			}
		}		
                coleccionReservas.add(new Reserva(reserva));
	}

        
        
    

              
        //buscar 
        public Reserva buscar(Reserva reserva) {
		int indice = coleccionReservas.indexOf(reserva);
		
		if (indice != -1) {
			return new Reserva (coleccionReservas.get(indice));
		} else {
			return null;
		}
	}
	
        //borrar
        
        public void borrar(Reserva reserva) throws OperationNotSupportedException {
            if (reserva == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		}
		if ( coleccionReservas.contains(reserva)){
                        coleccionReservas.remove(reserva);
		}
		else {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}
        
        
        /*
        public void borrar(Reserva reserva) throws OperationNotSupportedException {
                if (reserva == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		}
                
                
                if (this.coleccionReservas.contains(reserva)) {
			this.coleccionReservas.remove(reserva);
		} else {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
             
	}
        */
        
       
        
        public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			representacion.add(reserva.toString());
		}
		return representacion;
	}
            /*    
         //getReservasAula
        public List<Reserva> getReservasAula(Aula aula) {
              //  if(aula==null)
		//	throw new IllegalArgumentException("No se pueden comprobar las reservas realizadas sobre un aula nula.");
		List<Reserva> reservaAula = new ArrayList<>();
		
		for (Reserva reserva : coleccionReservas) {
                  if( reserva.getAula().equals(aula)){
                          	
				reservaAula.add(new Reserva(reserva));
				
			}
                }
		return reservaAula;
	}
    */
        
        
         public List<Reserva> getReservasAula( Aula aula){
            
                if(aula==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas realizadas sobre un aula nula.");
		List <Reserva>reservasAula= new ArrayList<>();
		
		for(Reserva reserva:coleccionReservas) {
                    
			if(reserva.getAula().equals(aula) ) 
                        {
				reservasAula.add(new Reserva(reserva));                                
                        }
		}
		return reservasAula;
        }
        
        
        //getReservasProfesor
          
       public List<Reserva> getReservasProfesor(Profesor profesor) {
		
                if(profesor==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas de un profesor nulo.");
		
                List<Reserva>reservasProfesor = new ArrayList<>();
		
		for(Reserva reserva:coleccionReservas ){
			if( reserva.getProfesor().equals(profesor))
                        {
                            reservasProfesor.add(new Reserva(reserva));
                        }
		}
		return reservasProfesor;
	}


        
        //getReservasPermanencia
       
       public List<Reserva> getReservasPermanencia ( Permanencia permanencia){
                
            if(permanencia==null)
		throw new IllegalArgumentException("No se pueden comprobar las reservas de una permanencia nula.");
	        List<Reserva> reservasPermanencia= new ArrayList<>();
                	for(Reserva reserva: coleccionReservas) {
			if(reserva.getPermanencia().equals(permanencia)) {
                            reservasPermanencia.add(new Reserva(reserva));
		}
		}
            return reservasPermanencia;
        
        }
       
       
       /* 
       public List<Reserva> getReservasPermanencia ( Permanencia permanencia){
                
                if(permanencia==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas de una permanencia nula.");
		
                List<Reserva> reservasPermanencia= new ArrayList<>();
                	
		for(Reserva reserva: coleccionReservas) {
			if(reserva.getPermanencia().equals(permanencia)) {
                            reservasPermanencia.add(new Reserva(reserva));
		        }
		}
		return reservasPermanencia;
        
        }
       */
        //consultarDiponibilidad  //revisar si que que poner && o ||
        
        public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if(aula==null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
		if(permanencia==null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
		for(Reserva reserva: coleccionReservas) {
			if(reserva.getAula().equals(aula)&& reserva.getPermanencia().equals(permanencia)){
                         
                         return false;
                            
                        }
                       }
		return true;
	}


    private boolean esMesSiguienteOPosterior(Reserva reserva) {
       if(reserva==null)throw new IllegalArgumentException("No se puede realizar una reserva nula.");
       // si añoReserva > añoActual
        if (reserva.getPermanencia().getDia().getYear() > LocalDate.now().getYear())
                return true;//reserva ok       

        //si añoReserva = añoActual
        if (reserva.getPermanencia().getDia().getYear() == LocalDate.now().getYear()){
                    //si mesReserva>mes Actual 
                    if (reserva.getPermanencia().getDia().getMonthValue()> LocalDate.now().getMonthValue())
                            return true;       //reserva ok
                    else
                        return false;      //sino no se puede reservar
                    }
        else return false;  //sino no se puede reservar
        
    }
  
        /*
         private boolean esMesSiguienteOPosterior ( Reserva reserva){
            
        	boolean correcto = false;
		int annioReserva = reserva.getPermanencia().getDia().getYear();
		int annioActual = LocalDate.now().getYear();
		int mesReserva = reserva.getPermanencia().getDia().getMonthValue();
		int mesActual = LocalDate.now().getMonthValue();
		
                if (annioReserva > annioActual) {
			correcto = true;
		} else {
			if ( mesReserva > mesActual) {
				correcto = true;
			}
		}
		return correcto;
      
	}
        */
  

  /*
 private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate dia) {
	            
		
		List<Reserva> reservasProfesorMes = new ArrayList<>();
		
		for (Reserva reserva : coleccionReservas) {
                    
                int annio=reserva.getPermanencia().getDia().getYear();
		int mes=reserva.getPermanencia().getDia().getMonthValue();
                
			if (reserva.getProfesor().equals(profesor) && reserva.getProfesor().equals(mes) && reserva.getProfesor().equals(annio))
				reservasProfesorMes.add(new Reserva(reserva));
			}
		
		return reservasProfesorMes;
 }
  */
         
         
         private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate dia) {
		if(profesor==null) throw new IllegalArgumentException("No se puede realizar una reserva con profesor nulo.");//no encuentro cual deberia ser el mensaje correcto para esta excepción
                if(dia==null) throw new IllegalArgumentException("No se puede realizar una reserva para un dia nulo.");//no encuentro cual deberia ser el mensaje correcto para esta excepción
                        List<Reserva> reservasProfesor = new ArrayList<>();
                    for (Reserva reserva : coleccionReservas) {
		    if (reserva.getProfesor().equals(profesor)&& reserva.getPermanencia().getDia().getMonthValue() == dia.getMonthValue()
                    && reserva.getPermanencia().getDia().getYear() == dia.getYear()) {
		    reservasProfesor.add(new Reserva(reserva));
		}
                }
		return reservasProfesor;
	}
         
         
         
/*  
private float getPuntosGastadosReserva(Reserva reserva){
                 //creo variable e inicializo
        	float puntosGastadosReserva=0; 
        	 //creo un arraylist con las reservas del mes de un profesor
                List<Reserva> puntosGastadosMes =getReservasProfesorMes(reserva.getProfesor(), reserva.getPermanencia().getDia());
                for (Reserva nuevaReservas : puntosGastadosMes) {
                    //suma de todos los puntos que lleva gastados en el mes
                    puntosGastadosReserva = puntosGastadosReserva + nuevaReservas.getPuntos();
                            }
	
    return puntosGastadosReserva;

                }   
*/
         private float getPuntosGastadosReserva(Reserva reserva) {
             if(reserva==null) throw new IllegalArgumentException("No se puede realizar una reserva nula.");
            //creo variable e inicializo
            float puntosGastadosReserva = 0;
             //creo un arraylist con las reservas del mes de un profesor
            List<Reserva> listaReservasProfesorMes = this.getReservasProfesorMes(reserva.getProfesor(), reserva.getPermanencia().getDia());
			
                for (Reserva puntosGastadosMes : listaReservasProfesorMes) {
			 //suma de todos los puntos que lleva gastados en el mes
                    puntosGastadosReserva = puntosGastadosReserva + puntosGastadosMes.getPuntos();
		}
    return puntosGastadosReserva + reserva.getPuntos();
		}

private Reserva getReservaDia(LocalDate dia) {
		if (dia == null) {
                                throw new IllegalArgumentException("No se puede buscar una reserva para un día nulo.");
		}
		Reserva reservaDia=null;
		for (Reserva reserva : coleccionReservas) {
			if (reserva.getPermanencia().getDia().equals(dia)) {
				reservaDia = new Reserva(reserva);
			}
		}
		return reservaDia;

            }

 public void leer() {
		File ficheroReservas = new File(NOMBRE_FICHERO_RESERVAS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroReservas))) {
			Reserva reserva = null;
			do {
				reserva = (Reserva) entrada.readObject();
				insertar(reserva);
			} while (reserva != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fichero de reservas.");
		} catch (EOFException e) {
			System.out.println("Fichero reservas leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void escribir() {
		File ficheroReservas = new File(NOMBRE_FICHERO_RESERVAS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroReservas))){
			for (Reserva reserva : coleccionReservas)
				salida.writeObject(reserva);
			System.out.println("Fichero reservas escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de reservas");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}

}