package br.com.SaulProgramador.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.SaulProgramador.hotel.modelo.Administrador;

public class AdministradorDAO {

	private Connection conexão;
	
	public AdministradorDAO(Connection conexao) {
		this.conexão = conexao;
	}
	
	public List<Administrador> buscar() {
		List<Administrador> administradores = new ArrayList<Administrador>();
		
		try {
			String sql = "SELECT * FROM administradores";
			
			try(PreparedStatement declaracao = conexão.prepareStatement(sql)) {
				declaracao.execute();
				
				transformarResultSetEmAdministrador(administradores, declaracao);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return administradores;
	}

	private void transformarResultSetEmAdministrador(List<Administrador> administradores, PreparedStatement declaracao) throws SQLException {
		try(ResultSet resultado = declaracao.getResultSet()) {
			while(resultado.next()) {
				Administrador administrador = new Administrador(resultado.getInt(1), resultado.getString(2), resultado.getString(3));
				administradores.add(administrador);
			}
		}
	}
	
}
