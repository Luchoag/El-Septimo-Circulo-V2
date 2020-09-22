package com.greppiluciano.septimocirculo.model;

import java.util.ArrayList;
import java.util.List;
import com.greppiluciano.septimocirculo.model.Libro;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="autor")
public class Autor {

	@Id
	@Column(name="Cod_autor")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre", nullable=false, length=45)
	private String nombre;

	@OneToOne(cascade = { CascadeType.ALL})
	@JoinColumn(name = "Cod_pais")
	private Pais pais;
	
	@OneToMany (mappedBy = "autor", cascade = CascadeType.ALL)
	private List <Libro> libros = new ArrayList<>();
	
	public Autor() {
		
	}

	public Autor(Long id, String nombre, Pais pais) {
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", pais=" + pais + "]";
	}
	
	
	
	
	
	
}
