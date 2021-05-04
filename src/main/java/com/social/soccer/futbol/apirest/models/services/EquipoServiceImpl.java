package com.social.soccer.futbol.apirest.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.social.soccer.futbol.apirest.models.dao.IEquipoDao;
import com.social.soccer.futbol.apirest.models.dao.IFacturaDao;
import com.social.soccer.futbol.apirest.models.dao.IMultaDao;
import com.social.soccer.futbol.apirest.models.entity.Equipo;
import com.social.soccer.futbol.apirest.models.entity.Factura;
import com.social.soccer.futbol.apirest.models.entity.Multa;

@Service
public class EquipoServiceImpl implements IEquipoService {

	@Autowired
	private IEquipoDao equipoDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Autowired
	private IMultaDao multaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findAll() {
		return (List<Equipo>) equipoDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Equipo> findAll(Pageable pageable) {
		return equipoDao.findAll(pageable);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Equipo findById(Long id) {
		return equipoDao.findById(id).orElse(null);
	}
	
	
	@Override
	@Transactional
	public Equipo save(Equipo equipo) {
		return equipoDao.save(equipo);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		equipoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Factura saveFactura(Factura factura) {
		return facturaDao.save(factura);
	}

	@Override
	@Transactional
	public void deleteFacturaById(Long id) {
		facturaDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Multa> findMultaByNombre(String term) {
		return multaDao.findByNombre(term);
		
	}

	

}
