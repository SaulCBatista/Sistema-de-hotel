package br.com.SaulProgramador.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.SaulProgramador.hotel.modelo.Hospede;

public class HospedesDAO {

	private Connection conexao;

	public HospedesDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public void cadastrar(Hospede hospede) {
		try {
			String sql = "INSERT INTO hospedes(nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reservaFK) VALUES(?, ?, ?, ?, ?, ?)";

			try (PreparedStatement declaracao = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				declaracao.setString(1, hospede.getNome());
				declaracao.setString(2, hospede.getSobrenome());
				declaracao.setDate(3, new java.sql.Date(hospede.getDataNascimento().getTime()));
				declaracao.setString(4, hospede.getNacionalidade());
				declaracao.setString(5, hospede.getTelefone());
				declaracao.setInt(6, hospede.getIdReserva());

				declaracao.execute();

				try (ResultSet resultado = declaracao.getGeneratedKeys()) {
					while (resultado.next()) {
						hospede.setId(resultado.getInt(1));
					}
				}
			}

		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Hospede> listar() {
		List<Hospede> hospedes = new ArrayList<Hospede>();
		try {
			String sql = "SELECT * FROM hospedes";

			try (PreparedStatement declaracao = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				declaracao.execute();

				trasformarResultSetEmHospede(hospedes, declaracao);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Hospede> buscarPorSobrenome(String sobrenome) {
		List<Hospede> hospedes = new ArrayList<Hospede>();
		try {
			String sql = "SELECT * FROM hospedes WHERE sobrenome = ?"; 
			try(PreparedStatement declaracao = conexao.prepareStatement(sql)) {
				declaracao.setString(1, sobrenome);
				declaracao.execute();
				
				trasformarResultSetEmHospede(hospedes, declaracao);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public List<Hospede> buscarPorIdDeReserva(Integer idDeReserva) {
		List<Hospede> hospedes = new ArrayList<Hospede>();
		try {
			String sql = "SELECT * FROM hospedes WHERE id_reservaFK = ?"; 
			try(PreparedStatement declaracao = conexao.prepareStatement(sql)) {
				declaracao.setInt(1, idDeReserva);
				declaracao.execute();
				
				trasformarResultSetEmHospede(hospedes, declaracao);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Integer buscarIdDeReserva(Integer id) {
		Integer idDeReserva = null;
		try {
			String sql = "SELECT * FROM hospedes WHERE id = ?"; 
			try(PreparedStatement declaracao = conexao.prepareStatement(sql)) {
				declaracao.setInt(1, id);
				declaracao.execute();	
				
				try(ResultSet resultado = declaracao.getResultSet()) {
					while(resultado.next()) {
						idDeReserva = resultado.getInt(7);
					}
				}
			}
			return idDeReserva;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizar(Hospede hospede) {
		try {
			String sql = "UPDATE hospedes SET nome = ?, sobrenome = ?, data_nascimento = ?, nacionalidade = ?, telefone = ? WHERE id = ?";

			try (PreparedStatement declaracao = conexao.prepareStatement(sql)) {
				declaracao.setString(1, hospede.getNome());
				declaracao.setString(2, hospede.getSobrenome());
				declaracao.setDate(3, new java.sql.Date(hospede.getDataNascimento().getTime()));
				declaracao.setString(4, hospede.getNacionalidade());
				declaracao.setString(5, hospede.getTelefone());
				declaracao.setInt(6, hospede.getId());

				declaracao.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Integer id) {
		try {
			String sql = "DELETE FROM hospedes WHERE id = ? OR id_reservaFK = ?";

			try (PreparedStatement declaracao = conexao.prepareStatement(sql)) {
				declaracao.setInt(1, id);
				declaracao.setInt(2, id);
				declaracao.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void trasformarResultSetEmHospede(List<Hospede> hospedes, PreparedStatement declaracao) throws SQLException {
		try (ResultSet resultado = declaracao.getResultSet()) {
			while (resultado.next()) {
				Hospede hospede = new Hospede(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getDate(4), resultado.getString(5), resultado.getString(6), resultado.getInt(7));

				hospedes.add(hospede);
			}
		}

	}


}
