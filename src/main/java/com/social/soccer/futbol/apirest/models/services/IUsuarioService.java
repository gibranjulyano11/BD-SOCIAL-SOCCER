package com.social.soccer.futbol.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.social.soccer.futbol.apirest.models.entity.Equipo;
import com.social.soccer.futbol.apirest.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);

	public Usuario findByUsername(String username);
	
	public List<Equipo> findAllEquipos();

	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);
}
