package com.social.soccer.futbol.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.soccer.futbol.apirest.models.entity.Equipo;

public interface IEquipoDao extends JpaRepository<Equipo, Long>{

}
