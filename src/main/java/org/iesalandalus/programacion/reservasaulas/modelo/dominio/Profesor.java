/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Manuel
 */
public class Profesor implements Serializable{
    //variables
static final String ER_TELEFONO="[69]([0-9]{8})";
static final String ER_CORREO="[.a-zA-Z0-9]+@[.a-zA-Z0-9]+.[a-zAZ]{2,4}";
private String nombre;
private String telefono;
private String correo;


//métodos
   private void setNombre(String nombre) {
    if (nombre == null) {
			throw new IllegalArgumentException("El nombre del profesor no puede ser nulo.");
		}
		if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("El nombre del profesor no puede estar vacío.");
		}
		this.nombre = nombre;
    }    

   public String getNombre()  {
       return nombre;
   }
     /*      asi genera errores los test
   public void setTelefono(String telefono) {
                     
            if (telefono!=null && !telefono.matches(ER_TELEFONO))
            {
                throw new IllegalArgumentException("El teléfono del profesor no es válido.");
            } 
            this.telefono = telefono;
   }    
   */
        // de esta forma puedo asignar un valor null al telefono y construir un objeto Profesor
   public void setTelefono(String telefono) {
                     
            if (telefono == null) 
                  this.telefono = null;
            else {
                       if (!telefono.matches(ER_TELEFONO))
                            throw new IllegalArgumentException("El teléfono del profesor no es válido.");
                         else{
                           this.telefono=telefono;
                              } 
           
                }
   }
   

   public String getTelefono() {
        return telefono;
    }

   public void setCorreo(String correo) {
        if ( correo ==null) throw new IllegalArgumentException("El correo del profesor no puede ser nulo.");
              
            if (correo.matches(ER_CORREO))
            {
            this.correo = correo;
            }else throw new IllegalArgumentException("El correo del profesor no es válido.");
    }    
   

   public String getCorreo() {
        return correo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        return hash;
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
        final Profesor other = (Profesor) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        
		if(getTelefono()==null){ 
                    return "[nombre=" + nombre + ", correo=" + correo + "]";
                }
		
                else  
                    return "[nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono + "]";
        
       
    }

        public Profesor(String nombre, String correo, String telefono ){
        
      setNombre(nombre);
      setTelefono(telefono);
      setCorreo(correo);
        
    }

    public Profesor(String nombre, String correo) {
        setNombre (nombre);
        setCorreo (correo);
    }

        public Profesor (Profesor profesor) {
        if (profesor == null) {
			throw new IllegalArgumentException("No se puede copiar un profesor nulo.");
		}
		setNombre(profesor.nombre);
		setTelefono(profesor.telefono);
		setCorreo(profesor.correo);
	}


}