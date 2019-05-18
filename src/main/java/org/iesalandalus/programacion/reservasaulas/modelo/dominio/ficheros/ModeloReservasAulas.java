/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio.ficheros;

import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.IModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.ficheros.dao.Aulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.ficheros.dao.Profesores;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.ficheros.dao.Reservas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
//prueba para comprobacion de paquetes
/**
 *
 * @author Manuel
 */
public class ModeloReservasAulas implements IModeloReservasAulas {
    
	
	private Profesores profesores;
	private Aulas aulas;
	private Reservas reservas;
	
	public ModeloReservasAulas() {
		this.profesores = new Profesores();
		this.aulas = new Aulas();
		this.reservas = new Reservas();
	}
	
    @Override
	public List<Aula> getAulas() {
		return aulas.getAulas();
	}
	
    @Override
	public int getNumAulas() {
		return aulas.getNumAulas();
	}
	
    @Override
	public List<String> representarAulas() {
		return aulas.representar();
	}
	
    @Override
	public Aula buscarAula(Aula buscar) {
		return aulas.buscar(buscar);
	}
	
    @Override
	public void insertarAula(Aula insertar) throws OperationNotSupportedException {
		aulas.insertar(insertar);
	}
	
    @Override
	public void borrarAula(Aula borrar) throws OperationNotSupportedException {
		aulas.borrar(borrar);
	}
	
    @Override
	public List<Profesor> getProfesores() {
		return profesores.getProfesores();
	}
	
    @Override
	public int getNumProfesores() {
		return profesores.getNumProfesores();
	}
	
    @Override
	public List<String> representarProfesores() {
		return profesores.representar();
	}
	
    @Override
	public Profesor buscarProfesor(Profesor buscar) {
		return profesores.buscar(buscar);
	}
	
    @Override
	public void insertarProfesor(Profesor insertar) throws OperationNotSupportedException {
		profesores.insertar(insertar);
	}
	
    @Override
	public void borrarProfesor(Profesor borrar) throws OperationNotSupportedException {
		profesores.borrar(borrar);
	}
	
    @Override
	public List<Reserva> getReservas() {
		return reservas.getReservas();
	}
	
    @Override
	public int getNumReservas() {
		return reservas.getNumReservas();
	}
	
    @Override
	public List<String> representarReservas() {
		return reservas.representar();
	}
	
    @Override
	public Reserva buscarReserva(Reserva buscar) {
		return reservas.buscar(buscar);
	}
	
    @Override
	public void realizarReserva(Reserva realizar) throws OperationNotSupportedException {
		reservas.insertar(realizar);
	}
	
    @Override
	public void anularReserva(Reserva anular) throws OperationNotSupportedException {
		reservas.borrar(anular);
	}
	
    @Override
	public List<Reserva> getReservasAula(Aula aula) {
		return reservas.getReservasAula(aula);
	}
	
    @Override
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		return reservas.getReservasProfesor(profesor);
	}
	
    @Override
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		return reservas.getReservasPermanencia(permanencia);
	}
	
    @Override
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}

        @Override
	public void leerAulas() {
		aulas.leer();
	}
	
	@Override
	public void escribirAulas() {
		aulas.escribir();
	}

        @Override
	public void leerProfesores() {
		profesores.leer();
	}
	
	@Override
	public void escribirProfesores() {
		profesores.escribir();
	}

                @Override
	public void leerReservas() {
		reservas.leer();
	}
	
	@Override
	public void escribirReservas() {
		reservas.escribir();
	}



}





