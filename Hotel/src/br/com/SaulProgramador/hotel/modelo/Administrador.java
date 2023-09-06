package br.com.SaulProgramador.hotel.modelo;

public class Administrador {

	private Integer id;
	private String login;
	private String senha;

	public Administrador(Integer id, String login, String senha) {
		this.id = id;
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getId() {
		return this.id;
	}

}
