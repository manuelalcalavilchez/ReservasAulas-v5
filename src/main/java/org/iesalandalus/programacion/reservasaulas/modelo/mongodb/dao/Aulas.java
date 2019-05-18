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
import org.iesalandalus.programacion.reservasaulas.modelo.mongodb.utilidades.MongoDB;

/**
 *
 * @author Manuel
 */
public class Aulas {
    
    
    private static final String COLECCION = "aulas";
    //declaracion de arraylist
    private MongoCollection<Document> coleccionAulas;
    
    
    //contructor por defecto
    
    public Aulas() {
		coleccionAulas = MongoDB.getBD().getCollection(COLECCION);
	}
    
    
         
   
    
	public List<Aula> getAulas() {
            
            List<Aula> aulas =new ArrayList<>();
            
            for(Document documentoAula:coleccionAulas.find()){
              
                aulas.add(MongoDB.obtenerAulaDesdeDocumento(documentoAula));
            }
	return aulas;
	}
	
    //getNumAulas
	public int getNumAulas() {
		return (int) coleccionAulas.countDocuments();
	}
	
    //insertar aula
	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede insertar un aula nula.");
		}
		if (buscar(aula) != null){
			throw new OperationNotSupportedException("El aula ya existe.");
                     
                } else {
                    coleccionAulas.insertOne(MongoDB.obtenerDocumentoDesdeAula(aula));
                }
                    
        }
                    
        
        
         
           
        //buscar aula
        public Aula buscar(Aula aula) {
		
            Document documentoAula=coleccionAulas.find().filter(eq(MongoDB.NOMBRE,aula.getNombre())).first();
            return MongoDB.obtenerAulaDesdeDocumento(documentoAula);
	}
        
       // borrar aula       

        public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede borrar un aula nula.");
		}
                
                if (buscar(aula)!=null)
                {
		
                coleccionAulas.deleteOne(eq(MongoDB.NOMBRE,aula.getNombre()));
                }
                else{
                    throw new OperationNotSupportedException("El aula ha borrar no existe.");
                }
	}
        
               
               
        public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for ( Aula aula : getAulas()){
			representacion.add(aula.toString());
		}
		return representacion;
	}
        
        /*        
        public void leer() {
		File ficheroAulas = new File(NOMBRE_FICHERO_AULAS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroAulas))) {
			Aula aula = null;
			do {
				aula = (Aula) entrada.readObject();
				insertar(aula);
			} while (aula != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fichero de aulas.");
		} catch (EOFException e) {
			System.out.println("Fichero aulas leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void escribir() {
		File ficheroAulas = new File(NOMBRE_FICHERO_AULAS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAulas))){
			for (Aula aula : coleccionAulas)
				salida.writeObject(aula);
			System.out.println("Fichero aulas escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de aulas");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}

	}
        
*/        
        
}
    
    


