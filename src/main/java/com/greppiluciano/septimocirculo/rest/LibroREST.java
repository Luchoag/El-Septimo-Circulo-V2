package com.greppiluciano.septimocirculo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greppiluciano.septimocirculo.dao.LibroDAO;
import com.greppiluciano.septimocirculo.model.Libro;

@RestController	//Esta anotación le dice a Spring Boot que esta clase va a ser un servicio REST
@RequestMapping("libros") //Aquí se define en qué URL se van a exponer todos los servicios o métodos que estén en esta clase.
public class LibroREST {

	@Autowired
	private LibroDAO libroDao;
	
	@GetMapping
	public ResponseEntity<List<Libro>> getLibro() {
		List<Libro> libros = libroDao.findAll();
		return ResponseEntity.ok(libros);
	}
	
	@RequestMapping(value="{autorId}")	// /libros/{librosID}/1
	public ResponseEntity<Libro> getLibroById(@PathVariable("libroId") Long libroId) {
		Optional<Libro> optionalLibro = libroDao.findById(libroId);
		if (optionalLibro.isPresent()) {
			return ResponseEntity.ok(optionalLibro.get());
		} else {
			return ResponseEntity.noContent().build();
		}	
	}
	
	@PostMapping
	public ResponseEntity<Libro> createAutor (@RequestBody Libro autor) {
		Libro newAutor = libroDao.save(autor);
		return ResponseEntity.ok(newAutor);
	}	
	
	@DeleteMapping(value="{autorId}")
	public ResponseEntity<Void> deleteAutor (@PathVariable("autorId") Long autorId) {
		libroDao.deleteById(autorId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping(value="{autorId}")
	public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro) {
		Optional<Libro> optionalLibro =	libroDao.findById(libro.getId());
		if (optionalLibro.isPresent()) {
			Libro updateLibro = optionalLibro.get();
			updateLibro.setTitulo(libro.getTitulo());
			libroDao.save(updateLibro);
			return ResponseEntity.ok(updateLibro);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

	
}
