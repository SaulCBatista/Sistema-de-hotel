package br.com.SaulProgramador.hotel.modelo;

import java.util.Date;

public class Hospede {

	private Integer id;
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String nacionalidade;
	private String telefone;
	private Integer idReserva;

	public Hospede(Integer id, String nome, String sobrenome, Date dataNascimento, String nacionalidade,
			String telefone, Integer idReserva) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.idReserva = idReserva;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNacionalidade() {
		return this.nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		if (this.id == null) {
			this.id = id;
		}
	}

	public Integer getIdReserva() {
		return this.idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		if (this.id == null) {
			this.idReserva = idReserva;
		}
	}

	@Override
	public String toString() {
		return "id: " + this.getId() + ", nome: " + this.getNome() + ", sobrenome: " + this.getNome()
		+ ", data de nascimento: " + this.getDataNascimento() + ", nascionalidade: " + this.getNacionalidade()
		+ ", telefone: " + this.getTelefone() + ", id da reserva: " + this.getIdReserva();
	}
	
}
