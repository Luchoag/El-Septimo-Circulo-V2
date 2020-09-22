package com.greppiluciano.septimocirculo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name="Libro")
public class Libro {

	@Id
	@Column(name="Cod_Libro")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="Titulo", nullable=false, length=45)
	private String titulo;
	
	@Column(name="Anio", nullable=false, length=45)
	private String año;

	@ManyToOne(cascade = { CascadeType.ALL})
	@JoinColumn(name = "Cod_pais")
	private Pais pais;
	
	@ManyToOne(cascade = { CascadeType.ALL})
	@JoinColumn(name = "Cod_editorial")
	private Editorial editorial;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Cod_Autor")
	private Autor autor;
	
	
	public Libro() {
		
	}

	public Libro(Long id, String titulo, String año, Pais pais, Editorial editorial, Autor autor) {
		this.id = id;
		this.titulo = titulo;
		this.año = año;
		this.pais = pais;
		this.editorial = editorial;
		this.autor = autor;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAño() {
		return año;
	}

	public void setAño(String año) {
		this.año = año;
	}
	
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	
	
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", año=" + año + ", pais=" + pais + ", editorial=" + editorial
				+ ", autor=" + autor + "]";
	}
	
	
	
}
