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

import com.greppiluciano.septimocirculo.dao.EditorialDAO;
import com.greppiluciano.septimocirculo.model.Editorial;

@RestController	//Esta anotación le dice a Spring Boot que esta clase va a ser un servicio REST
@RequestMapping("editorial") //Aquí se define en qué URL se van a exponer todos los servicios o métodos que estén en esta clase.
public class EditorialREST {

	//MÉTODOS HTTP - SOLICITUD AL SERVIDOR
	/*
	 * Estado 200 (se realizó correctamente)
	 * Estado 505 (falla en la lógica de programación)
	 * Estado 404 (ruta mal especificada)
	 */
	
	
	@Autowired	
	private EditorialDAO editorialDao;
	
	@GetMapping
	public ResponseEntity<List<Editorial>> getEditorial() {
		List<Editorial> editoriales = editorialDao.findAll();
		return ResponseEntity.ok(editoriales);
	}
	
	@RequestMapping(value="{editorialId}")	// /editorial/{editorialID}/1
	public ResponseEntity<Editorial> getEditorialById(@PathVariable("editorialID") Integer editorialID) {
		Optional<Editorial> optionalEditorial =	editorialDao.findById(editorialID);
		if (optionalEditorial.isPresent()) {
			return ResponseEntity.ok(optionalEditorial.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Editorial> createEditorial (@RequestBody Editorial editorial) {
		Editorial newEditorial = editorialDao.save(editorial);
		return ResponseEntity.ok(newEditorial);
	}	
	
	@DeleteMapping(value="{editorialId}")
	public ResponseEntity<Void> deleteEditorial (@PathVariable("editorialID") Integer editorialID) {
		editorialDao.deleteById(editorialID);
		return ResponseEntity.ok(null);
	}
	
	
	@PutMapping
	public ResponseEntity<Editorial> updateProduct(@RequestBody Editorial editorial) {
		Optional<Editorial> optionalEditorial =	editorialDao.findById(editorial.getId());
		if (optionalEditorial.isPresent()) {
			Editorial updateEditorial = optionalEditorial.get();
			updateEditorial.setNombre(editorial.getNombre());
			editorialDao.save(updateEditorial);
			return ResponseEntity.ok(updateEditorial);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
}
