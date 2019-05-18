/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.mongodb.dao;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.ficheros.dao.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.bson.Document;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.mongodb.utilidades.MongoDB;

/**
 *
 * @author Manuel
 */
public class Profesores {
    
    private static final String COLECCION = "profesores";
    
    //declaracion arraylist
private MongoCollection<Document> coleccionProfesores;
    
    //contructor por defecto
    
    public Profesores() {
		coleccionProfesores = MongoDB.getBD().getCollection(COLECCION);
	}
    
    
    
    
	public List<Profesor> getProfesores() {
            
            List<Profesor> profesores =new ArrayList<>();
            
            for(Document documentoProfesor:coleccionProfesores.find()){
              
                profesores.add(MongoDB.obtenerProfesorDesdeDocumento(documentoProfesor));
            }
	return profesores;
	}
	
    //getNumProfesores
	public int getNumProfesores() {
		return (int) coleccionProfesores.countDocuments();
	}
        
    //insertar profesores
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
		}
                
		if (buscar(profesor)!= null){
                        {
                            throw new OperationNotSupportedException("El profesor ya existe.");
                        }
                } else {
                    coleccionProfesores.insertOne(MongoDB.obtenerDocumentoDesdeProfesor(profesor));
                }
                
		
	}

        
        //buscar profesor
        public Profesor buscar(Profesor profesor) {
		 Document documentoProfesor=coleccionProfesores.find().filter(eq(MongoDB.NOMBRE,profesor.getNombre())).first();
            return MongoDB.obtenerProfesorDesdeDocumento(documentoProfesor);
	}

        //borrar profesor
       public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new IllegalArgumentException("No se puede borrar un profesor nula.");
		}
                
                if (buscar(profesor)!=null)
                {
		
                coleccionProfesores.deleteOne(eq(MongoDB.NOMBRE,profesor.getNombre()));
                }
                else{
                    throw new OperationNotSupportedException("El profesor ha borrar no existe.");
                }
	}
        
       //Representar
        
       public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for ( Profesor profesor : getProfesores()){
			representacion.add(profesor.toString());
		}
		return representacion;
	
	}
         
/*       
        public void leer() {
		File ficheroProfesores = new File(NOMBRE_FICHERO_PROFESORES);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroProfesores))) {
			Profesor profesor = null;
			do {
				profesor = (Profesor) entrada.readObject();
				insertar(profesor);
			} while (profesor != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fichero de profesores.");
		} catch (EOFException e) {
			System.out.println("Fichero profesores leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void escribir() {
		File ficheroProfesores = new File(NOMBRE_FICHERO_PROFESORES);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroProfesores))){
			for (Profesor profesor : coleccionProfesores)
				salida.writeObject(profesor);
			System.out.println("Fichero profesores escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de profesores");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}
*/
}

        
