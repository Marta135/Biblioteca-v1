package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;

public class Libros {

	/*********ATRIBUTOS*********/
	
	private List<Libro> coleccionLibros;
	
	
	/*******CONSTRUCTORES*******/
	
	/**
	 * Constructor con parámetros.
	 * @param capacidad
	 */
	public Libros() throws NullPointerException, IllegalArgumentException {
		coleccionLibros = new ArrayList<>();
	}
	
	/**
	 * Método que devuelve una copia profunda de la colección.
	 * @return copiaProfundaLibros
	 */
	public List<Libro> get() throws NullPointerException, IllegalArgumentException {
		return copiaProfundaLibros();
	}
	
	/**
	 * Método que devuelve una copia de la colección de libros.
	 * @return copiaLibros
	 */
	private List<Libro> copiaProfundaLibros() throws NullPointerException, IllegalArgumentException {
		List<Libro> copiaLibros = new ArrayList<>();
		for (Libro libro : coleccionLibros) {
			copiaLibros.add(new Libro(libro));
		}
		return copiaLibros;
	}
	
	
	/**
	 * Método que devuelve el tamaño.
	 * @return tamano
	 */
	public int getTamano() {
		return coleccionLibros.size();
	}
	

	/********OTROS MÉTODOS********/
	
	/**
	 * Método para insertar un libro a la colección.
	 * @param libro
	 * @throws OperationNotSupportedException
	 */
	public void insertar(Libro libro) throws OperationNotSupportedException, NullPointerException, IllegalArgumentException {
		if (libro == null) {
			throw new NullPointerException("ERROR: No se puede insertar un libro nulo.");
		}
		int indice = coleccionLibros.indexOf(libro);
		if (indice == -1) {
			coleccionLibros.add(new Libro(libro));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un libro con ese título y autor.");
		}
	}
	
	
	/**
	 * Método que permite buscar un libro en la colección.
	 * @param libro
	 * @return libro
	 */
	public Libro buscar(Libro libro) {
		if (libro == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un libro nulo.");
		}
		int indice = coleccionLibros.indexOf(libro);
		if (indice == -1) {
			return null;
		} else {
			return new Libro(coleccionLibros.get(indice));
		}
	}
	
	/**
	 * Método para borrar un libro de la colección.
	 * @param libro
	 * @throws OperationNotSupportedException
	 */
	public void borrar(Libro libro) throws OperationNotSupportedException {
		if (libro == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un libro nulo.");
		}
		int indice = coleccionLibros.indexOf(libro);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún libro con ese título y autor.");
		} else {
			coleccionLibros.remove(indice);
		}
	}

}
