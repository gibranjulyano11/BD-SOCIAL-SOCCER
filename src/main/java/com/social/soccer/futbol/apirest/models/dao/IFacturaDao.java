package com.social.soccer.futbol.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.soccer.futbol.apirest.models.entity.Factura;

public interface IFacturaDao extends JpaRepository<Factura, Long>{

}
