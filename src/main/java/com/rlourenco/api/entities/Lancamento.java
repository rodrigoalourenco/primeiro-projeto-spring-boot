package com.rlourenco.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rlourenco.api.enums.TipoEnum;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lancamento")
public class Lancamento implements Serializable {

	private static final long serialVersionUID = -4314865497279832420L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", nullable = false)
	private Date data;

	@Column(name = "descricao", nullable = true)
	private String descricao;

	@Column(name = "localizacao", nullable = true)
	private String localizacao;

	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;

	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private TipoEnum tipo;

	@ManyToOne(fetch = FetchType.EAGER)
	private Funcionario funcionario;

	public Lancamento() {
	}

	@PreUpdate
	public void PreUpdate() {
		dataAtualizacao = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		this.dataCriacao = atual;
		this.dataAtualizacao = atual;
	}

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", data=" + data + ", descricao=" + descricao + ", localizacao=" + localizacao
				+ ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", tipo=" + tipo
				+ ", funcionario=" + funcionario + "]";
	}

}
