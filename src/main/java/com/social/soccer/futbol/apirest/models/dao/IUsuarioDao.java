package com.social.soccer.futbol.apirest.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.social.soccer.futbol.apirest.models.entity.Equipo;
import com.social.soccer.futbol.apirest.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario findByUsername2(String username);
	
	@Query("from Equipo")
	public List<Equipo> findAllEquipos();

	public Page<Usuario> findAll(Pageable pageable);

	
}
