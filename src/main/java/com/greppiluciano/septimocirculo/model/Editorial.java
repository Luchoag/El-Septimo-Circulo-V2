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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Editorial")
public class Editorial {
	
	@Id
	@Column(name="Cod_editorial")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	@Column(name="nombre", nullable=false, length=30)
	private String nombre;

	@OneToMany (mappedBy = "editorial", cascade = CascadeType.ALL)
	private List <Libro> libros = new ArrayList<>();
	
	/*
	 *  Getters y Setters
	 */
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

	

	
	
	
}
