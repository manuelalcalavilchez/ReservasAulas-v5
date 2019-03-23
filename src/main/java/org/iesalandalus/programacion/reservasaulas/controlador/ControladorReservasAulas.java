/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.controlador;

import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.IModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.vista.VistaReservasAulas;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;


/**
 *
 * @author Manuel
 */
public class ControladorReservasAulas implements IControladorReservasAulas {
    
       
       //public IModeloReservasAulas modelo;
       public IVistaReservasAulas vista;
       public IModeloReservasAulas modelo;                
	
	
	public ControladorReservasAulas(IModeloReservasAulas modelo, IVistaReservasAulas vista) {
		this.modelo = modelo;
		this.vista = vista;
		vista.setControlador(this);
	}

    /*public ControladorReservasAulas(IModeloReservasAulas modelo, IVistaReservasAulas vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

        
    @Override
        public void comenzar(){
            modelo.leerAulas();
            modelo.leerProfesores();
            modelo.leerReservas();
            this.vista.comenzar();
        }
       
     @Override
        public void salir(){
            modelo.escribirAulas();
            modelo.escribirProfesores();
            modelo.escribirReservas();
            System.out.println("Finalizado el programa");
            
        }    
   
        
    @Override
	public List<String> representarAulas() {
		return modelo.representarAulas();
	}
	
    @Override
	public Aula buscarAula(Aula buscar) {
		return modelo.buscarAula(buscar);
	}
	
    @Override
	public void insertarAula(Aula insertar) throws OperationNotSupportedException {
		modelo.insertarAula(insertar);
	}
	
    @Override
	public void borrarAula(Aula borrar) throws OperationNotSupportedException {
		modelo.borrarAula(borrar);
	}
	
    @Override
	public List<String> representarProfesores() {
		return modelo.representarProfesores();
	}
	
    @Override
	public Profesor buscarProfesor(Profesor buscar) {
		return modelo.buscarProfesor(buscar);
	}
	
    @Override
	public void insertarProfesor(Profesor insertar) throws OperationNotSupportedException {
		modelo.insertarProfesor(insertar);
	}
	
    @Override
	public void borrarProfesor(Profesor borrar) throws OperationNotSupportedException {
		modelo.borrarProfesor(borrar);
	}
	
    @Override
	public List<String> representarReservas() {
		return modelo.representarReservas();
	}
	
    @Override
	public void realizarReserva(Reserva realizar) throws OperationNotSupportedException {
		modelo.realizarReserva(realizar);
	}
	
    @Override
	public void anularReserva(Reserva anular) throws OperationNotSupportedException {
		modelo.anularReserva(anular);
	}
	
    @Override
	public List<Reserva> getReservasAula(Aula aula) {
		return modelo.getReservasAula(aula);
	}
	
    @Override
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		return modelo.getReservasProfesor(profesor);
	}
	
    @Override
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		return modelo.getReservasPermanencia(permanencia);
	}
	
    @Override
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return modelo.consultarDisponibilidad(aula, permanencia);
	}

}


