package br.com.lbomfim.finance.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * @author Lucas Bomfim 
 */

@Entity
public class Despesa {
	
	public enum Categoria {
		EDUCACAO,
		ALIMENTACAO,
		LAZER,
		FASTFOOD,
		MORADIA,
		SAUDE,
		SERVICO
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private Long id;
	
	@Column
	private String descricao;
	
	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = false)
	private int quantidade;
	
	@Column
	private LocalDateTime data;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	// CONSTRUTOR VAZIO
	public Despesa() {
		
	}
	
	// CONSTRUTOR PADRAO
	public Despesa(String descricao, Double valor, int quantidade, LocalDateTime data, Categoria categoria, Usuario usuario) {
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.data= data;
		this.categoria = categoria;
		this.usuario = usuario;
	}
	
	// GETTER E SETTER
	public Long getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor * quantidade;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	// METODO PARA RETORNAR O VALOR TOTAL DA DESPESA
	public Double getValorTotal() {
		return valor * quantidade;
	}
	
}
