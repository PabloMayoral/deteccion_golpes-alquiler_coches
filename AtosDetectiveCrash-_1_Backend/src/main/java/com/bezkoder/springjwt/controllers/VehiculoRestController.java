package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.vehiculos;
import com.bezkoder.springjwt.security.services.IVehiculoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")

public class VehiculoRestController {

	@Autowired
	private IVehiculoService vehiculoService;
	
	@GetMapping("/vehiculos")
	public List<vehiculos> index(){
		return vehiculoService.findAll();
	}
	
	@GetMapping("/vehiculos/{id}")
	public vehiculos show(@PathVariable Long id) {
		return vehiculoService.findById(id);
	}
	
	
	@PostMapping("/vehiculos")
	@ResponseStatus(HttpStatus.CREATED)
	public vehiculos create(@RequestBody vehiculos vehiculo) {
		return vehiculoService.save(vehiculo);
	}
	
	@PutMapping("/vehiculos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public vehiculos update(@RequestBody vehiculos vehiculo, @PathVariable Long id) {
		vehiculos vehiculoActual = vehiculoService.findById(id);
		vehiculoActual.setMatricula(vehiculo.getMatricula());
		vehiculoActual.setMarca(vehiculo.getMarca());
		vehiculoActual.setModelo(vehiculo.getModelo());
		vehiculoActual.setNum_bastidor(vehiculo.getNum_bastidor());
		vehiculoActual.setColor(vehiculo.getColor());
		vehiculoActual.setAlquilado(vehiculo.getAlquilado());
	
		return vehiculoService.save(vehiculoActual);
	}
	
	@DeleteMapping("/vehiculos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		vehiculoService.delete(id);
	}
}


