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

import com.greppiluciano.septimocirculo.dao.PaisDAO;
import com.greppiluciano.septimocirculo.model.Pais;

@RestController	//Esta anotación le dice a Spring Boot que esta clase va a ser un servicio REST
@RequestMapping("pais") //Aquí se define en qué URL se van a exponer todos los servicios o métodos que estén en esta clase.
public class PaisREST {
	
	@Autowired	
	private PaisDAO paisDao;
	
	@GetMapping
	public ResponseEntity<List<Pais>> getPais() {
		List<Pais> paises = paisDao.findAll();
		return ResponseEntity.ok(paises);
	}
	/*
	@GetMapping(value = "/pais1")
	public String getPais1() {
		
		return paisDao.findById(1L).get().getNombre().toString();
	}
	*/
	@RequestMapping(value="{paisId}")	// /products/{productID}/1
	public ResponseEntity<Pais> getPaisById(@PathVariable("paisId") Long paisId) {
		Optional<Pais> optionalPais = paisDao.findById(paisId);
		if (optionalPais.isPresent()) {
			return ResponseEntity.ok(optionalPais.get());
		} else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Pais> createPais (@RequestBody Pais pais) {
		Pais newPais = paisDao.save(pais);
		return ResponseEntity.ok(newPais);
	}	
	
	@DeleteMapping(value="{paisId}")
	public ResponseEntity<Void> deletePais (@PathVariable("paisId") Long paisId) {
		paisDao.deleteById(paisId);
		return ResponseEntity.ok(null);
	}
	
	
	@PutMapping(value="{paisId}")
	public ResponseEntity<Pais> updatePais(@RequestBody Pais pais) {
		Optional<Pais> optionalPais =	paisDao.findById(pais.getId());
		if (optionalPais.isPresent()) {
			Pais updatePais = optionalPais.get();
			updatePais.setNombre(pais.getNombre());
			paisDao.save(updatePais);
			return ResponseEntity.ok(updatePais);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}


}
