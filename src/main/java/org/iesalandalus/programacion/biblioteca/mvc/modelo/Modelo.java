package org.iesalandalus.programacion.biblioteca.mvc.modelo;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Libros;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Prestamos;

public class Modelo {

	/*********ATRIBUTOS*********/
	
	private static final int CAPACIDAD = 50;
	private Alumnos alumnos;
	private Libros libros;
	private Prestamos prestamos;
	
	
	/*******CONSTRUCTOR*******/
	/**
	 * Constructor por defecto:
	 */
	public Modelo() {
		alumnos = new Alumnos(CAPACIDAD);
		libros = new Libros(CAPACIDAD);
		prestamos = new Prestamos(CAPACIDAD);
	}
	

	/********OTROS MÉTODOS********/
	
	/**
	 * Método que permite insertar un alumno.
	 * @param alumno
	 * @throws OperationNotSupportedException
	 */
	public void insertar(Alumno alumno) throws OperationNotSupportedException, NullPointerException {
		alumnos.insertar(alumno);
	}
	
	/**
	 * Método que permite insertar un libro.
	 * @param libro
	 * @throws OperationNotSupportedException
	 */
	public void insertar(Libro libro) throws OperationNotSupportedException, NullPointerException  {
		libros.insertar(libro);
	}
	
	/**
	 * Método que permite hacer un préstamo de un libro a un alumno.
	 * @param prestamo
	 * @throws OperationNotSupportedException
	 */
	public void prestar(Prestamo prestamo) throws OperationNotSupportedException {
		if (prestamo ==  null) {
			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}
		Alumno alumno = alumnos.buscar(prestamo.getAlumno());
		if (alumno == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alumno del préstamo.");
		}
		Libro libro = libros.buscar(prestamo.getLibro());
		if (libro == null) {
			throw new OperationNotSupportedException("ERROR: No existe el libro del préstamo.");
		}
		prestamos.prestar(new Prestamo(alumno, libro, prestamo.getFechaPrestamo()));
	}
	
	/**
	 * Método que premite devolver un libro.
	 * @param prestamo
	 * @param fechaDevolucion
	 * @throws OperationNotSupportedException
	 */
	public void devolver(Prestamo prestamo, LocalDate fechaDevolucion) throws OperationNotSupportedException, NullPointerException, IllegalArgumentException {
		if (prestamos.buscar(prestamo) == null) {
			throw new OperationNotSupportedException("ERROR: No se puede devolver un préstamo no prestado.");
		}
		prestamos.devolver(prestamo, fechaDevolucion);
	}
	
	/**
	 * Método que permite buscar un alumno determinado.
	 * @param alumno
	 * @return alumno
	 */
	public Alumno buscar(Alumno alumno) throws NullPointerException, IllegalArgumentException {
		return alumnos.buscar(alumno);
	}
	
	/**
	 * Método que permite buscar un libro determinado.
	 * @param libro
	 * @return libro
	 */
	public Libro buscar(Libro libro) throws NullPointerException, IllegalArgumentException {
		return libros.buscar(libro);
	}
	
	/**
	 * Método que permite buscar un préstamo determinado.
	 * @param prestamo
	 * @return prestamo
	 */
	public Prestamo buscar(Prestamo prestamo) throws NullPointerException, IllegalArgumentException {
		return prestamos.buscar(prestamo);
	}
	
	/**
	 * Método que permite eliminar un alumno de la colección.
	 * @param alumno
	 * @throws OperationNotSupportedException
	 */
	public void borrar(Alumno alumno) throws OperationNotSupportedException, IllegalArgumentException {
		alumnos.borrar(alumno);
	}
	
	/**
	 *  Método que permite eliminar un libro de la colección.
	 * @param libro
	 * @throws OperationNotSupportedException
	 */
	public void borrar(Libro libro) throws OperationNotSupportedException, IllegalArgumentException {
		libros.borrar(libro);
	}
	
	/**
	 *  Método que permite borrar un préstamo.
	 * @param prestamo
	 * @throws OperationNotSupportedException
	 */
	public void borrar(Prestamo prestamo) throws OperationNotSupportedException, IllegalArgumentException {
		prestamos.borrar(prestamo);
	}
	
	/**
	 * Método que devuelve los datos de los alumnos.
	 * @return alumnos
	 */
	public Alumno[] getAlumnos() {
		return alumnos.get();
	}
	
	/**
	 * Método que devuelve los datos de los libros prestados.
	 * @return libros
	 */
	public Libro[] getLibros() {
		return libros.get();
	}
	
	/**
	 * Método que devuelve todos los préstamos realizados.
	 * @return prestamos
	 */
	public Prestamo[] getPrestamos() {
		return prestamos.get();
	}
	
	/**
	 * Método que devuelve los préstamos de un alumno determinado.
	 * @param alumno
	 * @return prestamos.get(alumno)
	 */
	public Prestamo[] getPrestamos(Alumno alumno) {
		return prestamos.get(alumno);
	}
	
	/**
	 * Método que devuelve los préstamos de un libro determinado.
	 * @param libro
	 * @return prestamos.get(libro)
	 */
	public Prestamo[] getPrestamos(Libro libro) {
		return prestamos.get(libro);
	}
	
	/**
	 * Método que devuelve los préstamos realizados en una fecha determinada.
	 * @param fechaPrestamo
	 * @return prestamos.get(fechaPrestamo)
	 */
	public Prestamo[] getPrestamos(LocalDate fechaPrestamo) {
		return prestamos.get(fechaPrestamo);
	}
	
	
	
}
