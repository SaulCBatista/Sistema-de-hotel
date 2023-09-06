package br.com.SaulProgramador.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.SaulProgramador.hotel.modelo.Reserva;

public class ReservasDAO {

	private Connection conexao;
	
	public ReservasDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public void registrar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas(data_entrada, data_saida, valor, forma_pagamento) VALUES(?, ?, ?, ?)";
			
			try(PreparedStatement declaracao = conexao.prepareStatement(sql)){
				declaracao.setDate(1, new java.sql.Date(reserva.getDataEntrada().getTime()));
				declaracao.setDate(2, new java.sql.Date(reserva.getDataSaida().getTime()));
				declaracao.setDouble(3, reserva.getValor());
				declaracao.setString(4, reserva.getFormaPagamento());
				
				declaracao.execute();
				
				try(ResultSet resultado = declaracao.getResultSet()) {
					while(resultado.next()) {
						reserva.setId(resultado.getInt(1));
					}
				}
			}
			
		} catch (Exception e) {
			new RuntimeException(e);
		}
	}
	
}
