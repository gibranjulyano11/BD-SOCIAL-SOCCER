package com.social.soccer.futbol.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="facturas_items")
public class ItemFactura implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="multa_id") 
	private Multa multa;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getImporte() {
		return cantidad.doubleValue() * multa.getPrecio();
	}


	public Multa getMulta() {
		return multa;
	}


	public void setMulta(Multa multa) {
		this.multa = multa;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
