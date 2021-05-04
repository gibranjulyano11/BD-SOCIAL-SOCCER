package com.social.soccer.futbol.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.social.soccer.futbol.apirest.models.entity.Equipo;
import com.social.soccer.futbol.apirest.models.entity.Factura;
import com.social.soccer.futbol.apirest.models.entity.Multa;

public interface IEquipoService {
	
	public List<Equipo> findAll();
	
	public Page<Equipo> findAll(Pageable pageable);
	
	public Equipo findById(Long id);
	
	public Equipo save(Equipo equipo);
	
	public void delete(Long id);
	
	public Factura findFacturaById(Long id);
	
	public Factura saveFactura(Factura factura);
	
	public void deleteFacturaById(Long id);
	
	public List<Multa> findMultaByNombre(String term);

}
