package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

public class Libro {

	/*********ATRIBUTOS*********/
	
	private static final int PAGINAS_PARA_RECOMPENSA = 25;
	private static final float PUNTOS_PREMIO = 0.5f;
	private String titulo;
	private String autor;
	private int numPaginas;
	
	
	/*******CONSTRUCTORES*******/
	/**
	 * Constructor con parámetros.
	 * @param titulo: Título del libro.
	 * @param autor: Autor del libro.
	 * @param numPaginas: Número de páginas del libro. 
	 */
	public Libro (String titulo, String autor, int numPaginas) throws NullPointerException, IllegalArgumentException {
		setTitulo(titulo);
		setAutor(autor);
		setNumPaginas(numPaginas);
	}
	
	/**
	 * Constructor copia. 
	 * @param copiaLibro: Copia del objeto Libro.
	 */
	public Libro (Libro copiaLibro) throws NullPointerException, IllegalArgumentException {
		if (copiaLibro == null) {
			throw new NullPointerException("ERROR: No es posible copiar un libro nulo.");
		}
		setTitulo(copiaLibro.getTitulo());
		setAutor(copiaLibro.getAutor());
		setNumPaginas(copiaLibro.getNumPaginas());
	}
	
	/**
	 * Método que devolverá un libro ficticio para usarlo en búsquedas y borrados.
	 * @param titulo
	 * @param autor
	 * @return título, autor y número de páginas. 
	 */
	public static Libro getLibroFicticio(String titulo, String autor) throws NullPointerException, IllegalArgumentException {
		return new Libro(titulo, autor, 100);
	}
	
	
	/*********GETTERS Y SETTERS**********/
	
	/**
	 * Método que devuelve el título del libro.
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Método que modifica el título del libro.
	 * @param titulo
	 */
	private void setTitulo(String titulo) {
		if(titulo == null) {
			throw new NullPointerException("ERROR: El título no puede ser nulo.");
		}
		if (titulo.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El título no puede estar vacío.");
		}
		this.titulo = titulo;
	}
	
	/**
	 * Método que devuelve el autor del libro.
	 * @return autor
	 */
	public String getAutor() {
		return autor;
	}
	
	/**
	 * Método que modifica el autor del libro.
	 * @param autor
	 */
	private void setAutor(String autor) {
		if(autor == null) {
			throw new NullPointerException("ERROR: El autor no puede ser nulo.");
		}
		if (autor.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El autor no puede estar vacío.");
		}
		this.autor = autor;
	}
	
	/**
	 * Método que devuelve el número de páginas del libro.
	 * @return numPaginas
	 */
	public int getNumPaginas() {
		return numPaginas;
	}
	
	/**
	 * Método que modifica el número de páginas del libro.
	 * @param numPaginas
	 */
	private void setNumPaginas(int numPaginas) {
		if (numPaginas <= 0) {
			throw new IllegalArgumentException("ERROR: El número de páginas debe ser mayor que cero.");
		}
		this.numPaginas = numPaginas;
	}
	
	
	/********OTROS MÉTODOS********/
	
	/**
	 * Método que devuelve los puntos obtenidos.	
	 * @return puntos
	 */
	public float getPuntos() {
		float puntos = (numPaginas / PAGINAS_PARA_RECOMPENSA + 1) * PUNTOS_PREMIO;
		return puntos;
	}

	/**
	 * Método hashCode.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	/**
	 * Método para comparar dos libros.
	 * Dos libros son el mismo si tienen el mismo título y autor.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	/**
	 * Método que muestra la información del libro:
	 * título, autor y número de páginas.
	 */
	@Override
	public String toString() {
		return String.format("título=%s, autor=%s, número de páginas=%s", titulo, autor, numPaginas);
	}

}
