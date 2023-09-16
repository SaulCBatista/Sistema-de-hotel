package br.com.SaulProgramador.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.SaulProgramador.hotel.modelo.Reserva;

public class ReservasDAO {

	private Connection conexao;
	
	public ReservasDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public Integer registrar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas(data_entrada, data_saida, valor, forma_pagamento) VALUES(?, ?, ?, ?)";
			
			try(PreparedStatement declaracao = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				declaracao.setDate(1, new java.sql.Date(reserva.getDataEntrada().getTime()));
				declaracao.setDate(2, new java.sql.Date(reserva.getDataSaida().getTime()));
				declaracao.setDouble(3, reserva.getValor());
				declaracao.setString(4, reserva.getFormaPagamento());
				
				declaracao.execute();
				
				try(ResultSet resultado = declaracao.getGeneratedKeys()) {
					while(resultado.next()) {
						reserva.setId(resultado.getInt(1));
					}
				}
			}
			return reserva.getId();
		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Reserva> listar(){
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT * FROM reservas";
			
			try(PreparedStatement declaracao = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				declaracao.execute();
				
				trasformarResultSetEmReservas(reservas, declaracao);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarPorId(Integer id){
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT * FROM reservas WHERE id = ?";
			
			try(PreparedStatement declaracao = conexao.prepareStatement(sql)) {
				declaracao.setInt(1, id);
				declaracao.execute();
				
				trasformarResultSetEmReservas(reservas, declaracao);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}
		
	}
	
	public List<Reserva> buscarPorSobrenomeDeHospede(String sobrenome) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT * FROM reservas WHERE id = (SELECT hospedes.id_reservaFK FROM hospedes WHERE sobrenome = ?);";
			
			try(PreparedStatement declaracao = conexao.prepareStatement(sql)) {
				declaracao.setString(1, sobrenome);
				declaracao.execute();
				
				trasformarResultSetEmReservas(reservas, declaracao);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}
	}
	
	public void atualiazar(Reserva reserva) {
		try {
			String sql = "UPDATE reservas SET data_entrada = ?, data_saida = ?, valor = ?, forma_pagamento = ? WHERE id = ?";
			
			try(PreparedStatement declaracao = conexao.prepareStatement(sql)) {
				declaracao.setDate(1, new java.sql.Date(reserva.getDataEntrada().getTime()));
				declaracao.setDate(2, new java.sql.Date(reserva.getDataSaida().getTime()));
				declaracao.setDouble(3, reserva.getValor());
				declaracao.setString(4, reserva.getFormaPagamento());
				declaracao.setInt(5, reserva.getId());
				
				declaracao.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deletar(Integer id) {
		try {
			String sql = "DELETE FROM reservas WHERE id = ?";
			
			try(PreparedStatement declaracao = conexao.prepareStatement(sql)) {
				declaracao.setInt(1, id);
				declaracao.execute();
			}
		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void trasformarResultSetEmReservas(List<Reserva> reservas, PreparedStatement declaracao) throws SQLException {
		try(ResultSet resultado = declaracao.getResultSet()) {
			while(resultado.next()) {
				Reserva reserva = new Reserva(resultado.getInt(1), resultado.getDate(2), resultado.getDate(3), resultado.getDouble(4), resultado.getString(5));
				
				reservas.add(reserva);
			}
		}
		
	}

	
}
