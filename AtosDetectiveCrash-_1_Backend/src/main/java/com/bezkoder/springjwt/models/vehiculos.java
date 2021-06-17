package com.bezkoder.springjwt.models;

import java.io.Serializable;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "vehiculos")
public class vehiculos implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String matricula;

	@NotBlank
	@Column(nullable = false)
	private String marca;

	@NotBlank
	@Column(nullable = false)
	private String modelo;

	@NotBlank
	@Column(nullable = false)
	private String num_bastidor;

	@NotBlank
	@Column(nullable = false)
	private String color;

	@NotBlank
	@Column(nullable = false)
	private String alquilado;

	@NotBlank
	@Column(nullable = false)
	private String num_puertas;

	@NotBlank
	@Column(nullable = false)
	private String gama;

	public String getGama() {
		return gama;
	}

	public void setGama(String gama) {
		this.gama = gama;
	}

	@Lob
	private byte[] image;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNum_bastidor() {
		return num_bastidor;
	}

	public void setNum_bastidor(String num_bastidor) {
		this.num_bastidor = num_bastidor;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAlquilado() {
		return alquilado;
	}

	public void setAlquilado(String alquilado) {
		this.alquilado = alquilado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getNum_puertas() {
		return num_puertas;
	}

	public void setNum_puertas(String num_puertas) {
		this.num_puertas = num_puertas;
	}

	private static final long serialVersionUID = 1L;

}
