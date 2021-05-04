package com.social.soccer.futbol.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.social.soccer.futbol.apirest.models.entity.Factura;
import com.social.soccer.futbol.apirest.models.entity.Multa;
import com.social.soccer.futbol.apirest.models.services.IEquipoService;

@CrossOrigin(origins= {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {
	
	@Autowired
	private IEquipoService equipoService;
	
	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Factura show(@PathVariable Long id) {
		return equipoService.findFacturaById(id);
	}

	
	@DeleteMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		equipoService.deleteFacturaById(id);
	}
	
	
	@GetMapping("/facturas/filtrar-multas/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Multa> filtrarMulta(@PathVariable String term){
		return equipoService.findMultaByNombre(term);
	}
	
	
	@PostMapping("/facturas")
	@ResponseStatus(HttpStatus.CREATED)
	public Factura crear(@RequestBody Factura factura) {
		return equipoService.saveFactura(factura);
		
	}
}
