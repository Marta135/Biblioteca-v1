package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;

public class Libros {

	/*********ATRIBUTOS*********/
	
	private int capacidad;
	private int tamano;
	private Libro[] coleccionLibros;
	
	
	/*******CONSTRUCTORES*******/
	
	/**
	 * Constructor con parámetros.
	 * @param capacidad
	 */
	public Libros(int capacidad) throws NullPointerException, IllegalArgumentException {
		if(capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionLibros = new Libro[capacidad];
		this.tamano = 0;
	}
	
	/**
	 * Método que devuelve una copia profunda de la colección.
	 * @return copiaProfundaLibros
	 */
	public Libro[] get() throws NullPointerException, IllegalArgumentException {
		return copiaProfundaLibros();
	}
	
	/**
	 * Método que devuelve una copia de la colección de libros.
	 * @return copiaLibros
	 */
	private Libro[] copiaProfundaLibros() throws NullPointerException, IllegalArgumentException {
		Libro[] copiaLibros = new Libro[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaLibros[i] = new Libro(coleccionLibros[i]);
		}
		return copiaLibros;
	}
	
	
	/*********GETTERS Y SETTERS**********/
	
	/**
	 * Método que devuelve el tamaño.
	 * @return tamano
	 */
	public int getTamano() {
		return tamano;
	}
	
	/**
	 * Método que devuelve la capacidad.
	 * @return capacidad
	 */
	public int getCapacidad() {
		return capacidad;
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
		int indice = buscarIndice(libro);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más libros.");
		}
		if (tamanoSuperado(indice)) {
			coleccionLibros[indice] = new Libro(libro);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un libro con ese título y autor.");
		}
	}
	
	/**
	 * Método que busca la posicion de un libro en la colección.
	 * @param libro
	 * @return indice
	 */
	private int buscarIndice(Libro libro) {
		int indice = 0;
		boolean libroEncontrado = false;
		while (!tamanoSuperado(indice) && !libroEncontrado) {
			if (coleccionLibros[indice].equals(libro)) {
				libroEncontrado = true;
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
	 * Método que permite buscar un libro en la colección.
	 * @param libro
	 * @return libro
	 */
	public Libro buscar(Libro libro) {
		if (libro == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un libro nulo.");
		}
		int indice = buscarIndice(libro);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Libro(coleccionLibros[indice]);
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
		int indice = buscarIndice(libro);
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún libro con ese título y autor.");
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
			coleccionLibros[i] = coleccionLibros[i+1];
		}
		coleccionLibros[i] = null;
		tamano--;
	}

}
