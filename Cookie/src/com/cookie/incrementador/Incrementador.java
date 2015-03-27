package com.cookie.incrementador;

public class Incrementador {
	
	/* Inalteraveis */
	private Long id;
	private String nome;
//	private String icone;
//	private String som;
	private Long valorIncremento;
	
	/* Alteráveis */
	private Long preco;
	private Long quantidadeComprada;

	public void compra() {
		preco *=2;
		quantidadeComprada++;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getValorIncremento() {
		return valorIncremento;
	}

	public void setValorIncremento(Long valorIncremento) {
		this.valorIncremento = valorIncremento;
	}

	public Long getPreco() {
		return preco;
	}

	public void setPreco(Long preco) {
		this.preco = preco;
	}

	public Long getQuantidadeComprada() {
		return quantidadeComprada;
	}

	public void setQuantidadeComprada(Long quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}
	
	
	
}
