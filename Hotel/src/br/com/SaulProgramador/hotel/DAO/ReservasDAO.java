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
	
	public void registrar(Reserva reserva) {
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
		} catch (Exception e) {
			new SQLException(e).printStackTrace();;
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
		} 
		catch (SQLException e) {
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
		} catch (Exception e) {
			new SQLException(e).printStackTrace();
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
