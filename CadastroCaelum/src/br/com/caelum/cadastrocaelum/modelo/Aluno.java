package br.com.caelum.cadastrocaelum.modelo;

import java.io.Serializable;

public class Aluno implements Serializable {
	
	private Long id;
	private String nome;
	private String site;
	private String endereco;
	private String telefone;
	private Double nota;
	private String foto;
	
	
	@Override
	public String toString() {
		return getNome();
	}
	
	public Aluno makeCopy() {
		Aluno copia = new Aluno();
		copia.setId(id);
		copia.setNome(nome);
		copia.setSite(site);
		copia.setEndereco(endereco);
		copia.setTelefone(telefone);
		copia.setNota(nota);
		copia.setFoto(foto);
		return copia;
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
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
}
