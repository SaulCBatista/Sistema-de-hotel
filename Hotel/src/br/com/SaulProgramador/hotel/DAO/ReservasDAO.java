package br.com.SaulProgramador.hotel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.SaulProgramador.hotel.modelo.Reserva;

public class ReservasDAO {

	private Connection conexao;
	
	public ReservasDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public void registrar(Reserva reserva) {
		try {
			this.conexao.setAutoCommit(false);
			String sql = "INSERT INTO reservas(data_entrada, data_saida, valor, forma_pagamento) VALUES(?, ?, ?, ?)";
			
			try(PreparedStatement declaracao = conexao.prepareStatement(sql)){
				declaracao.setDate(1, new java.sql.Date(reserva.getDataEntrada().getTime()));
				declaracao.setDate(2, new java.sql.Date(reserva.getDataSaida().getTime()));
				declaracao.setDouble(3, reserva.getValor());
				declaracao.setString(4, reserva.getFormaPagamento());
				
				declaracao.execute();
				System.out.println("Finalizado");
				
//				try(ResultSet resultado = declaracao.getResultSet()) {
//					while(resultado.next()) {
//						reserva.setId(resultado.getInt(1));
//						System.out.println("finalizado");
//					}
//				}
			}
		} catch (Exception e) {
			new SQLException(e).printStackTrace();;
		}
	}
	
	public void commit() {
		try {			
			this.conexao.commit();
		} catch (Exception e) {
			new SQLException(e).printStackTrace();;
		}
	}
	
}
