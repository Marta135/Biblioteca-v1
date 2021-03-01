package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;

public class Prestamos {

	/*********ATRIBUTOS*********/
	
	private int capacidad;
	private int tamano;
	private Prestamo[] coleccionPrestamos;
	
	
	/*******CONSTRUCTORES*******/
	
	/**
	 * Constructor con parámetros.
	 * @param capacidad
	 */
	public Prestamos (int capacidad) throws NullPointerException, IllegalArgumentException {
		if(capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionPrestamos = new Prestamo [capacidad];
		this.tamano = 0;
	}

	/**
	 * Método que devuelve una copia profunda de la colección.
	 * @return copiaProfundaPrestamos
	 */
	public Prestamo[] get() throws NullPointerException, IllegalArgumentException {
		return copiaProfundaPrestamos();
	}
	
	/**
	 * Método que devuelve una copia de la colección de alumnos.
	 * @return copiaAlumnos
	 */
	private Prestamo[] copiaProfundaPrestamos() throws NullPointerException, IllegalArgumentException {
		Prestamo[] copiaPrestamos = new Prestamo[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaPrestamos[i] = new Prestamo(coleccionPrestamos[i]);
		}
		return copiaPrestamos;
	}
	
	/**
	 * Método que devuelve los préstamos realizados por un alumno.
	 * @param alumno
	 * @return prestamosAlumno
	 */
	public Prestamo[] get(Alumno alumno) throws NullPointerException, IllegalArgumentException {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		Prestamo[] prestamosAlumno = new Prestamo[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionPrestamos[i].getAlumno().equals(alumno)) {
				prestamosAlumno[j++] = new Prestamo(coleccionPrestamos[i]);
			}
		}
		return prestamosAlumno;
	}
	
	/**
	 * Método que devuelve los préstamos realizados de un libro.
	 * @param libro
	 * @return
	 */
	public Prestamo[] get(Libro libro) throws NullPointerException, IllegalArgumentException {
		if (libro == null) {
			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}
		Prestamo[] prestamosLibro = new Prestamo[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionPrestamos[i].getLibro().equals(libro)) {
				prestamosLibro[j++] = new Prestamo(coleccionPrestamos[i]);
			}
		}
		return prestamosLibro;
	}
	
	/**
	 * Método que devuelve los préstamos realizados en una fecha determinada.
	 * @param fechaPrestamo
	 * @return prestamosFecha
	 */
	public Prestamo[] get(LocalDate fechaPrestamo) throws NullPointerException, IllegalArgumentException {
		if (fechaPrestamo == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}
		Prestamo[] prestamosFecha = new Prestamo[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (mismoMes(coleccionPrestamos[i].getFechaPrestamo(), fechaPrestamo)) {
				prestamosFecha[j++] = new Prestamo(coleccionPrestamos[i]);
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
		return fechaUno.equals(fechaDos);
	}
	
	
	/*********GETTERS Y SETTERS**********/
	
	/**
	 *  Método que devuelve el tamaño.
	 * @return tamano
	 */
	public int getTamano() {
		return tamano;
	}
	
	/**
	 *  Método que devuelve la capacidad.
	 * @return capacidad
	 */
	public int getCapacidad() {
		return capacidad;
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
		int indice = buscarIndice(prestamo);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más préstamos.");
		}
		if (tamanoSuperado(indice)) {
			coleccionPrestamos[indice] = new Prestamo(prestamo);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un préstamo igual.");
		}
	}
	
	/**
	 * Método que busca la posicion de un préstamo en la colección.
	 * @param prestamo
	 * @return indice
	 */
	private int buscarIndice(Prestamo prestamo) {
		int indice = 0;
		boolean prestamoEncontrado = false;
		while (!tamanoSuperado(indice) && !prestamoEncontrado) {
			if (coleccionPrestamos[indice].equals(prestamo)) {
				prestamoEncontrado = true;
			} else {
				indice++;
			}
		}
		return indice;
	}
	
	/**
	 * Método que devuelve true o false en función de si se ha superado el tamaño o no.
	 * @param indice
	 * @return true o false
	 */
	private boolean tamanoSuperado(int indice) {
		return indice >= tamano;
	}
	
	/**
	 * Método que devuelve true o false en función de si se ha superado la capacidad.
	 * @param indice
	 * @return true o false
	 */
	private boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
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
		int indice = buscarIndice(prestamo);
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
		} else {
			coleccionPrestamos[indice].devolver(fechaDevolucion);
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
		int indice = buscarIndice(prestamo);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Prestamo(coleccionPrestamos[indice]);
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
		int indice = buscarIndice(prestamo);
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
		} else {
			desplazaUnaPosicionHaciaIzquierda(indice);
		}
	}
	
	/**
	 * Método para desplazar una posición hacia la izquierda en la colección.
	 * @param indice
	 */
	private void desplazaUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = indice; !tamanoSuperado(i); i++) {
			coleccionPrestamos[i] = coleccionPrestamos[i+1];
		}
		coleccionPrestamos[i] = null;
		tamano--;
	}
	
}
