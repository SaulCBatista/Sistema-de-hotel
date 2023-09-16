package br.com.SaulProgramador.hotel.controle;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.SaulProgramador.exception.DataImpossivelException;
import br.com.SaulProgramador.hotel.DAO.Conexao;
import br.com.SaulProgramador.hotel.DAO.ReservasDAO;
import br.com.SaulProgramador.hotel.modelo.Reserva;

public class ReservasController {
	
	private ReservasDAO reservasDAO;

	public ReservasController() {
		Connection conexao = new Conexao().recuperarConexao();
		this.reservasDAO = new ReservasDAO(conexao);
	}

	public Integer registrar(Date dataEntrada, Date dataSaida, String formaPagamento) {

		Double valor = calcularValor(dataEntrada, dataSaida);
		
		Reserva reserva = new Reserva(null, dataEntrada, dataSaida, valor, formaPagamento);
		return reservasDAO.registrar(reserva);
	}
	
	public List<Reserva> listar(){
		return reservasDAO.listar();
	}
	
	public List<Reserva> buscarPorId(Integer id) {
		return reservasDAO.buscarPorId(id);
	}
	
	public List<Reserva> buscarPorSobrenomeDeHospede(String sobrenome) {
		return reservasDAO.buscarPorSobrenomeDeHospede(sobrenome);
	}
	
	public void atualizar(Integer id, Date dataEntrada, Date dataSaida, Double valor, String formaPagamento) {
		Reserva reserva = new Reserva(id, dataEntrada, dataSaida, valor, formaPagamento);
		reservasDAO.atualiazar(reserva);
	}
	
	public void deletar(Integer id) {
		reservasDAO.deletar(id);
	}

	public void cancelarReserva(Integer idDaReserva) {
		reservasDAO.deletar(idDaReserva);
	}
	
	public Double calcularValor(Date dataEntrada, Date dataSaida) {
		Double valor = 0.0;
		if(verificarData(dataEntrada, dataSaida)) {
			long diferencaTempo = dataSaida.getTime() - dataEntrada.getTime();
			long dias = TimeUnit.DAYS.convert(diferencaTempo, TimeUnit.MILLISECONDS);
			valor = (double) (dias * 50);
		} else {
			throw new DataImpossivelException();
		}
		
		return valor;
	}
	
	public boolean verificarData(Date dataEntrada, Date dataSaida) {
		return dataEntrada.before(dataSaida);
	}

}
