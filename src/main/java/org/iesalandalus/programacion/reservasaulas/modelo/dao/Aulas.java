/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;

/**
 *
 * @author Manuel
 */
public class Aulas {
    //declaracion de arraylist
    private List<Aula> coleccionAulas;
    
    
    //contructor por defecto
    
    public Aulas() {
		coleccionAulas = new ArrayList<>();
	}
    
    //constructor copia
	public Aulas (Aulas aulas) {
		setAulas(aulas);
	}
         
    //implementar setAulas
    
    private void setAulas(Aulas aulas) {
		if (aulas == null) {
			throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
		}
		coleccionAulas = copiaProfundaAulas(aulas.coleccionAulas);
		
    }
    //copiaProfundaAulas

    private List<Aula> copiaProfundaAulas(List<Aula> aulas) {
		List<Aula> otrasAulas = new ArrayList<>();
                for (Aula aula : aulas) {
                    otrasAulas.add(new Aula(aula));
                }
		return otrasAulas;
    }
    
         
    //getAulas
	public List<Aula> getAulas() {
		return copiaProfundaAulas(coleccionAulas);
	}
	
    //getNumAulas
	public int getNumAulas() {
		return coleccionAulas.size();
	}
	
    //insertar aula
	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede insertar un aula nula.");
		}
		if (coleccionAulas.contains(aula)){
			throw new OperationNotSupportedException("El aula ya existe.");
                     
                } else coleccionAulas.add(new Aula(aula));
        }
                    
        
        
         
           
        //buscar aula
        public Aula buscar(Aula aula) {
		int indice = coleccionAulas.indexOf(aula);
		
		if (indice !=-1){
			return new Aula(coleccionAulas.get(indice));
		} else {
			return null;
		}
	}
        
       // borrar aula       

        public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede borrar un aula nula.");
		}
                
                if (!coleccionAulas.remove(aula)) {
			throw new OperationNotSupportedException("El aula a borrar no existe.");
		}
	}
        
               
               
        public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for ( Aula aula : coleccionAulas){
			representacion.add(aula.toString());
		}
		return representacion;
	}
        
                
        
}
    
    


