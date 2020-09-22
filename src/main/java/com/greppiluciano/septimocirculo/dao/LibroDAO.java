package com.greppiluciano.septimocirculo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greppiluciano.septimocirculo.model.Libro;

public interface LibroDAO extends JpaRepository<Libro, Long> {

}
