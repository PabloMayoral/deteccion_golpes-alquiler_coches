package com.bezkoder.springjwt.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Alquiler;

@Repository("Alquiler")
public interface alquilerRepository extends JpaRepository<Alquiler, Serializable>{
		@Query(value = "SELECT * FROM alquileres WHERE cod_refrencia = ?1", nativeQuery = true)
		List<Alquiler> buscaPorCodigoRef(String cod_ref);
}
