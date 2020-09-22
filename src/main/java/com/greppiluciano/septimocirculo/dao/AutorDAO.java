package com.greppiluciano.septimocirculo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greppiluciano.septimocirculo.model.Autor;

public interface AutorDAO extends JpaRepository<Autor, Long> {

}
