package com.social.soccer.futbol.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;



@Entity
@Table(name="equipos")
public class Equipo implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false, unique=true)
	private String nombre;
	@Column(nullable=false)
	private String categoria;
	private String dirigente;
	
	@NotNull
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	private String foto;
	
	@JsonIgnoreProperties(value ={"equipo", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipo", cascade=CascadeType.ALL)
	private List<Factura> facturas;
	
	
	
	public Equipo() {
		this.facturas = new ArrayList<>();
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDirigente() {
		return dirigente;
	}

	public void setDirigente(String dirigente) {
		this.dirigente = dirigente;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}



	private static final long serialVersionUID = 1L;

}
