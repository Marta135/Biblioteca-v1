package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;

public class Prestamos {

	/*********ATRIBUTOS*********/
	
	private List<Prestamo> coleccionPrestamos;
	
	
	/*******CONSTRUCTORES*******/
	
	/**
	 * Constructor con parámetros.
	 * @param capacidad
	 */
	public Prestamos() throws NullPointerException, IllegalArgumentException {
		coleccionPrestamos = new ArrayList<>();
	}

	/**
	 * Método que devuelve una copia profunda de la colección.
	 * @return copiaProfundaPrestamos
	 */
	public List<Prestamo> get() throws NullPointerException, IllegalArgumentException {
		return copiaProfundaPrestamos();
	}
	
	/**
	 * Método que devuelve una copia de la colección de alumnos.
	 * @return copiaAlumnos
	 */
	private List<Prestamo> copiaProfundaPrestamos() throws NullPointerException, IllegalArgumentException {
		List<Prestamo> copiaPrestamos = new ArrayList<>();
		for (Prestamo prestamo : coleccionPrestamos) {
			copiaPrestamos.add(new Prestamo(prestamo));
		}
		return copiaPrestamos;
	}
	
	/**
	 *  Método que devuelve el tamaño.
	 * @return tamano
	 */
	public int getTamano() {
		return coleccionPrestamos.size();
	}
	
	
	/**
	 * Método que devuelve los préstamos realizados por un alumno.
	 * @param alumno
	 * @return prestamosAlumno
	 */
	public List<Prestamo> get(Alumno alumno) throws NullPointerException, IllegalArgumentException {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		List<Prestamo> prestamosAlumno = new ArrayList<>();
		for (Prestamo prestamo : coleccionPrestamos) {
			if (prestamo.getAlumno().equals(alumno)) {
				prestamosAlumno.add(new Prestamo(prestamo));
			}
		}
		return prestamosAlumno;
	}
	
	/**
	 * Método que devuelve los préstamos realizados de un libro.
	 * @param libro
	 * @return
	 */
	public List<Prestamo> get(Libro libro) throws NullPointerException, IllegalArgumentException {
		if (libro == null) {
			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}
		List<Prestamo> prestamosLibro = new ArrayList<>();
		for (Prestamo prestamo : coleccionPrestamos) {
			if (prestamo.getLibro().equals(libro)) {
				prestamosLibro.add(new Prestamo(prestamo));
			}
		}
		return prestamosLibro;
	}
	
	/**
	 * Método que devuelve los préstamos realizados en una fecha determinada.
	 * @param fechaPrestamo
	 * @return prestamosFecha
	 */
	public List<Prestamo> get(LocalDate fechaPrestamo) throws NullPointerException, IllegalArgumentException {
		if (fechaPrestamo == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}
		List<Prestamo> prestamosFecha = new ArrayList<>();
		for (Prestamo prestamo : coleccionPrestamos) {
			if (mismoMes(prestamo.getFechaPrestamo(), fechaPrestamo)) {
				prestamosFecha.add(new Prestamo(prestamo));
			}
		}
		return prestamosFecha;
	}
	
	/**
	 * Método que devuelve true o false si las fechas de préstamo son iguales o no.
	 * @param fechaUno
	 * @param fechaDos
	 * @return true o false
	 */
	private boolean mismoMes(LocalDate fechaUno, LocalDate fechaDos) {
		boolean fechaIgual = false;
		int anoUno = fechaUno.getYear();
		int anoDos = fechaDos.getYear();
		if (anoUno == anoDos && fechaUno.getMonth().equals(fechaDos.getMonth())) {
			fechaIgual = true;
		}
		return fechaIgual;
	}
	

	/********OTROS MÉTODOS********/
	
	/**
	 * Método para insertar un préstamo a la colección.
	 * @param prestamo
	 * @throws OperationNotSupportedException
	 */
	public void prestar(Prestamo prestamo) throws OperationNotSupportedException, NullPointerException, IllegalArgumentException {
		if (prestamo == null) {
			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}
		int indice = coleccionPrestamos.indexOf(prestamo);
		if (indice == -1) {
			coleccionPrestamos.add(new Prestamo(prestamo));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un préstamo igual.");
		}
	}
	
	
	/**
	 * Método para devolver un préstamo. 
	 * @param prestamo
	 * @param fechaDevolucion
	 * @throws OperationNotSupportedException
	 */
	public void devolver(Prestamo prestamo, LocalDate fechaDevolucion) throws OperationNotSupportedException, NullPointerException, IllegalArgumentException {
		if (prestamo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un préstamo nulo.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}
		int indice = coleccionPrestamos.indexOf(prestamo);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
		} else {
			coleccionPrestamos.get(indice).devolver(fechaDevolucion);
		}
	}
	
	
	/**
	 * Método que permite buscar un préstamo en la colección.
	 * @param prestamo
	 * @return prestamo
	 */
	public Prestamo buscar(Prestamo prestamo) throws NullPointerException, IllegalArgumentException {
		if (prestamo == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un préstamo nulo.");
		}
		int indice = coleccionPrestamos.indexOf(prestamo);
		if (indice == -1) {
			return null;
		} else {
			return new Prestamo(coleccionPrestamos.get(indice));
		}
	}
	
	/**
	 * Método para borrar un préstamo de la colección.
	 * @param prestamo
	 * @throws OperationNotSupportedException
	 */
	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {
		if (prestamo == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un préstamo nulo.");
		}
		int indice = coleccionPrestamos.indexOf(prestamo);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
		} else {
			coleccionPrestamos.remove(indice);
		}
	}
	
}
