package com.desafio.serasa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.opencsv.bean.CsvBindByName;

@Entity
@Table(name = "notas")
public class Nota {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CsvBindByName
	@Column
	@NotNull
	private Integer periodo;
	
	@CsvBindByName(column = "numero_nota")
	@Column(name = "numero_nota")
	@NotNull
	private Integer numeroNota;
	
	@CsvBindByName(column = "tp_nota")
	@Column(name = "tp_nota")
	@NotNull
	private String tipoNota;
	
	@CsvBindByName
	@NotNull
	private Long id_empresa;
	
	public Nota() {
	}

	public Nota(Long id, @NotNull Integer periodo, @NotNull Integer numeroNota, @NotNull String tipoNota,
			@NotNull Long id_empresa) {
		super();
		this.id = id;
		this.periodo = periodo;
		this.numeroNota = numeroNota;
		this.tipoNota = tipoNota;
		this.id_empresa = id_empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(Integer numeroNota) {
		this.numeroNota = numeroNota;
	}

	public String getTipoNota() {
		return tipoNota;
	}

	public void setTipoNota(String tipoNota) {
		this.tipoNota = tipoNota;
	}

	public Long getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Long id_empresa) {
		this.id_empresa = id_empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
