/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;

/**
 *
 * @author Manuel
 */
public class PermanenciaPorTramo extends Permanencia implements Serializable{
    
//declaración de variables

    private static final int PUNTOS=10;
    private Tramo tramo;
    
//constructor con dos parametros
    public PermanenciaPorTramo (LocalDate dia,Tramo tramo){
        super(dia);
        setTramo(tramo);

    }

    public PermanenciaPorTramo(String dia,Tramo tramo) {
		
        super(dia);
	setTramo(tramo);
	}
        
    public PermanenciaPorTramo(PermanenciaPorTramo permanenciaPorTramo) {
		//¿no hay que hacer comprobacion de null?
                super();
                if(permanenciaPorTramo==null)	throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
                setDia(permanenciaPorTramo.getDia());
                setTramo(permanenciaPorTramo.getTramo());
        }
        
           
        
        //getter y setter Tramo
        public Tramo getTramo() {
            return tramo;
        }

        private void setTramo(Tramo tramo) {
            if(tramo == null)
			throw new IllegalArgumentException("El tramo de una permanencia no puede ser nulo.");
        this.tramo = tramo;
    }
       //get Puntos

    public int getPuntos() {
        return PUNTOS;
    }
   
        
        
        //hascode y equal

	public int hashCode() {
		return Objects.hash(dia, tramo, PUNTOS);
	}

    

    
    
    
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PermanenciaPorTramo other = (PermanenciaPorTramo) obj;
        if (this.tramo == other.tramo && this.dia==other.dia) {
            return true;
        }
        return true;
    }
    
        

  
	
    
        //toString

    @Override
    public String toString() {
         
         return "[dia=" + getDia().format(FORMATO_DIA)+',' + " tramo=" + tramo +"]";
    }
               
}

