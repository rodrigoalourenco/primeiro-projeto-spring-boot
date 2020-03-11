package com.rlourenco.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.rlourenco.api.enums.PerfilEnum;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "funcionario")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 5611717562753497068L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "cpf", nullable = false)
	private String cpf;
	
	@Column(name = "valor_hora", nullable = true)
	private BigDecimal valorHora;
	
	@Column(name = "qtd_horas_trabalhado_dia", nullable = true)
	private Double qtdHorasTrabalhadoDia;
	
	@Column(name = "qtd_horas_almoco", nullable = true)
	private Double qtdHorasAlmoco;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	private PerfilEnum perifl;
	
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Empresa empresa;
	
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lancamento> lancamentos;
	
	public Funcionario() {
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
		return "Funcionario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", valorHora=" + valorHora + ", qtdHorasTrabalhadoDia=" + qtdHorasTrabalhadoDia + ", qtdHorasAlmoco="
				+ qtdHorasAlmoco + ", perifl=" + perifl + ", dataCriacao=" + dataCriacao + ", dataAtualizacao="
				+ dataAtualizacao + ", empresa=" + empresa + "]";
	}

	

}
