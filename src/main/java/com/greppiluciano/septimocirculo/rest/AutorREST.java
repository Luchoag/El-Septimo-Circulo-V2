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

import com.greppiluciano.septimocirculo.dao.AutorDAO;
import com.greppiluciano.septimocirculo.model.Autor;

@RestController	//Esta anotación le dice a Spring Boot que esta clase va a ser un servicio REST
@RequestMapping("autores") //Aquí se define en qué URL se van a exponer todos los servicios o métodos que estén en esta clase.
public class AutorREST {

	@Autowired
	private AutorDAO autorDao;
	
	@GetMapping
	public ResponseEntity<List<Autor>> getAutor() {
		List<Autor> autores = autorDao.findAll();
		return ResponseEntity.ok(autores);
	}
	
	@RequestMapping(value="{autorId}")	// /autores/{autorID}/1
	public ResponseEntity<Autor> getAutorById(@PathVariable("autorId") Long autorId) {
		Optional<Autor> optionalAutor = autorDao.findById(autorId);
		if (optionalAutor.isPresent()) {
			return ResponseEntity.ok(optionalAutor.get());
		} else {
			return ResponseEntity.noContent().build();
		}	
	}
	
	@PostMapping
	public ResponseEntity<Autor> createAutor (@RequestBody Autor autor) {
		Autor newAutor = autorDao.save(autor);
		return ResponseEntity.ok(newAutor);
	}	
	
	@DeleteMapping(value="{autorId}")
	public ResponseEntity<Void> deleteAutor (@PathVariable("autorId") Long autorId) {
		autorDao.deleteById(autorId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping(value="{autorId}")
	public ResponseEntity<Autor> updateAutor(@RequestBody Autor autor) {
		Optional<Autor> optionalAutor =	autorDao.findById(autor.getId());
		if (optionalAutor.isPresent()) {
			Autor updateAutor = optionalAutor.get();
			updateAutor.setNombre(autor.getNombre());
			autorDao.save(updateAutor);
			return ResponseEntity.ok(updateAutor);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	
}
