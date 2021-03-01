package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;

public class Alumnos {

	/*********ATRIBUTOS*********/
	
	private int capacidad;
	private int tamano;
	private Alumno[] coleccionAlumnos;
	
		
	/*******CONSTRUCTORES*******/
	
	/**
	 * Constructor con parámetros.
	 * @param capacidad
	 */
	public Alumnos (int capacidad) throws NullPointerException, IllegalArgumentException {
		if(capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionAlumnos = new Alumno [capacidad];
		this.tamano = 0;
	}

	/**
	 * Método que devuelve una copia profunda de la colección.
	 * @return copiaProfundaAlumnos
	 */
	public Alumno[] get() throws NullPointerException, IllegalArgumentException {
		return copiaProfundaAlumnos();
	}
	
	/**
	 * Método que devuelve una copia de la colección de alumnos.
	 * @return copiaAlumnos
	 */
	private Alumno[] copiaProfundaAlumnos() throws NullPointerException, IllegalArgumentException {
		Alumno[] copiaAlumnos = new Alumno[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
		}
		return copiaAlumnos;
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
	 * Método para insertar un alumno a la colección.
	 * @param alumno
	 * @throws OperationNotSupportedException
	 */
	public void insertar(Alumno alumno) throws OperationNotSupportedException, NullPointerException, IllegalArgumentException {
		if (alumno == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}
		int indice = buscarIndice(alumno);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
		}
		if (tamanoSuperado(indice)) {
			coleccionAlumnos[indice] = new Alumno(alumno);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese correo.");
		}
	}
	
	
	/**
	 * Método que busca la posicion de un alumno en la colección por su correo.
	 * @param alumno
	 * @return indice
	 */
	private int buscarIndice(Alumno alumno) {
		int indice = 0;
		boolean alumnoEncontrado = false;
		while (!tamanoSuperado(indice) && !alumnoEncontrado) {
			if (coleccionAlumnos[indice].getCorreo().equals(alumno.getCorreo())) {
				alumnoEncontrado = true;
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
	 * Método que permite buscar un alumno en la colección.
	 * @param alumno
	 * @return alumno
	 */
	public Alumno buscar(Alumno alumno) throws NullPointerException, IllegalArgumentException {
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}
		int indice = buscarIndice(alumno);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Alumno(coleccionAlumnos[indice]);
		}
	}
	
	/**
	 * Método para borrar un alumno de la colección.
	 * @param alumno
	 * @throws OperationNotSupportedException
	 */
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}
		int indice = buscarIndice(alumno);
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese correo.");
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
			coleccionAlumnos[i] = coleccionAlumnos[i+1];
		}
		coleccionAlumnos[i] = null;
		tamano--;
	}
}
