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
public class Aula implements Serializable {
    
    //variables
    	private static final float PUNTOS_POR_PUESTO = 0.5f;
	private static final int MIN_PUESTOS = 10;
	private static final int MAX_PUESTOS = 100;
        private int puestos;
    
        private String nombre;    
    
    //métodos
    
    // contructor
    public Aula (String nombre, int puestos)
    {
        setNombre(nombre); 
        setPuestos(puestos);
    }
    
    //copia
    public Aula (Aula aula)
    {
        if (aula == null) {
			throw new IllegalArgumentException("No se puede copiar un aula nula.");
		}
		setNombre(aula.nombre);
                setPuestos(aula.puestos);
    }
    
    private void setNombre(String nombre) {
		if (nombre == null) {
			throw new IllegalArgumentException("El nombre del aula no puede ser nulo.");
		}
		if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("El nombre del aula no puede estar vacío.");
		}
		this.nombre = nombre;
	}

    public String getNombre() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nombre);
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
        final Aula other = (Aula) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }



    

    @Override
	public String toString() {
		return "[nombre=" + nombre + ", puestos=" + puestos + "]";
	}
    
//getter y setter puestos
    public int getPuestos() {
        return puestos;
    }

  private void setPuestos(int puestos) {
        if (puestos<MIN_PUESTOS || puestos>MAX_PUESTOS)
            throw new IllegalArgumentException("El número de puestos no es correcto.");
                
        this.puestos = puestos;
    }
 
    public float getPuntos() {
    return getPuestos()*PUNTOS_POR_PUESTO;
    
    }
}