package com.bezkoder.springjwt.models;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "alquileres")
public class Alquiler implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_alquiler;

	@Column(nullable = true)
	private String matricula_coche;

	@Column(nullable = true)
	private Long id_coche;

	@NotBlank
	@Column(nullable = false)
	private String id_cliente;

	@Temporal(TemporalType.DATE)
	private Date fecha_recogida;

	@Basic
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm")
	private Date hora_recogida;

	@Temporal(TemporalType.DATE)
	private Date fecha_devolucion;

	@Basic
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm")
	private Date hora_devolucion;

	@NotBlank
	@Column(nullable = false)
	private String ciudadAlquiler;

	@NotBlank
	@Column(nullable = false)
	private String ciudadAlquilerDevolucion;

	@NotBlank
	@Column(nullable = false)
	private String num_puertas;

	@NotBlank
	@Column(nullable = false)
	private String codRefrencia;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

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
	private byte[] imagen_inicio;

	@Lob
	private byte[] imagen_final;

	private String golpe_grave_inicio;
	private String golpe_medio_inicio;
	private String golpe_leve_inicio;

	private String golpe_grave_final;
	private String golpe_medio_final;
	private String golpe_leve_final;

	public String getGolpe_grave_inicio() {
		return golpe_grave_inicio;
	}

	public void setGolpe_grave_inicio(String golpe_grave_inicio) {
		this.golpe_grave_inicio = golpe_grave_inicio;
	}

	public String getGolpe_medio_inicio() {
		return golpe_medio_inicio;
	}

	public void setGolpe_medio_inicio(String golpe_medio_inicio) {
		this.golpe_medio_inicio = golpe_medio_inicio;
	}

	public String getGolpe_leve_inicio() {
		return golpe_leve_inicio;
	}

	public void setGolpe_leve_inicio(String golpe_leve_inicio) {
		this.golpe_leve_inicio = golpe_leve_inicio;
	}

	public String getGolpe_grave_final() {
		return golpe_grave_final;
	}

	public void setGolpe_grave_final(String golpe_grave_final) {
		this.golpe_grave_final = golpe_grave_final;
	}

	public String getGolpe_medio_final() {
		return golpe_medio_final;
	}

	public void setGolpe_medio_final(String golpe_medio_final) {
		this.golpe_medio_final = golpe_medio_final;
	}

	public String getGolpe_leve_final() {
		return golpe_leve_final;
	}

	public void setGolpe_leve_final(String golpe_leve_final) {
		this.golpe_leve_final = golpe_leve_final;
	}

	public Long getId_alquiler() {
		return id_alquiler;
	}

	public void setId_alquiler(Long id_alquiler) {
		this.id_alquiler = id_alquiler;
	}

	public String getMatricula_coche() {
		return matricula_coche;
	}

	public void setMatricula_coche(String matricula_coche) {
		this.matricula_coche = matricula_coche;
	}

	public Long getId_coche() {
		return id_coche;
	}

	public void setId_coche(Long id_coche) {
		this.id_coche = id_coche;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Date getFecha_recogida() {
		return fecha_recogida;
	}

	public void setFecha_recogida(Date fecha_recogida) {
		this.fecha_recogida = fecha_recogida;
	}

	public Date getHora_recogida() {
		return hora_recogida;
	}

	public void setHora_recogida(Date hora_recogida) {
		this.hora_recogida = hora_recogida;
	}

	public Date getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(Date fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}

	public Date getHora_devolucion() {
		return hora_devolucion;
	}

	public void setHora_devolucion(Date hora_devolucion) {
		this.hora_devolucion = hora_devolucion;
	}

	public String getCiudadAlquiler() {
		return ciudadAlquiler;
	}

	public void setCiudadAlquiler(String ciudadAlquiler) {
		this.ciudadAlquiler = ciudadAlquiler;
	}

	public String getCiudadAlquilerDevolucion() {
		return ciudadAlquilerDevolucion;
	}

	public void setCiudadAlquilerDevolucion(String ciudadAlquilerDevolucion) {
		this.ciudadAlquilerDevolucion = ciudadAlquilerDevolucion;
	}

	public String getNum_puertas() {
		return num_puertas;
	}

	public void setNum_puertas(String num_puertas) {
		this.num_puertas = num_puertas;
	}

	public String getCodRefrencia() {
		return codRefrencia;
	}

	public void setCodRefrencia(String codRefrencia) {
		this.codRefrencia = codRefrencia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getImagen_inicio() {
		return imagen_inicio;
	}

	public void setImagen_inicio(byte[] imagen_inicio) {
		this.imagen_inicio = imagen_inicio;
	}

	public byte[] getImagen_final() {
		return imagen_final;
	}

	public void setImagen_final(byte[] imagen_final) {
		this.imagen_final = imagen_final;
	}

	private static final long serialVersionUID = 1L;
}
