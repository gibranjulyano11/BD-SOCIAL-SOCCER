package com.social.soccer.futbol.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.social.soccer.futbol.apirest.models.entity.Multa;

public interface IMultaDao extends JpaRepository<Multa, Long>{
	
	@Query("select m from Multa m where m.nombre like %?1%")
	public List<Multa> findByNombre(String term);
	
	public List<Multa> findByNombreContainingIgnoreCase(String term);
	
	public List<Multa> findByNombreStartingWithIgnoreCase(String term);

}
