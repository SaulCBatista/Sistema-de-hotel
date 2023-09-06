package br.com.SaulProgramador.hotel.controle;

import java.sql.Connection;
import java.util.List;

import br.com.SaulProgramador.hotel.DAO.AdministradorDAO;
import br.com.SaulProgramador.hotel.DAO.Conexao;
import br.com.SaulProgramador.hotel.modelo.Administrador;

public class AdministradorController {

	private AdministradorDAO administradorDAO;
	
	public AdministradorController() {
		Connection conexao = new Conexao().recuperarConexao();
		this.administradorDAO = new AdministradorDAO(conexao); 
	}
	
	public boolean auteticar(String login, String senha) {
		boolean autetico = false;
		List<Administrador> administradores= this.administradorDAO.buscar();
		
		for(Administrador administrador : administradores) {
			if(login.equals(administrador.getLogin()) && senha.equals(administrador.getSenha())) {
				autetico = true;
			}
		}
		
		return autetico;
	}
	
}
