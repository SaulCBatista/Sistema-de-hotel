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

	public void registrar(Date dataEntrada, Date dataSaida, String formaPagamento) {

		Double valor = calcularValor(dataEntrada, dataSaida);
		
		Reserva reserva = new Reserva(null, dataEntrada, dataSaida, valor, formaPagamento);
		reservasDAO.registrar(reserva);
		Reserva.registrar(reserva);
	}
	
	public List<Reserva> listar(){
		this.vicularComBancoDeDados();
		return Reserva.listar();
	}
	
	public List<Reserva> buscarPorId(Integer id) {
		return Reserva.buscarPorId(id);
	}
	
	public void deletar(Integer id, Integer idHospede) {
		new HospedesController().deletar(idHospede, id);
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
	
	public void cancelarReserva() {
		reservasDAO.deletar(Reserva.cancelarReserva());
	}
	
	public void vicularComBancoDeDados() {
		List<Reserva> reservas = this.reservasDAO.listar();
		Reserva.vincularComBancoDeDados(reservas);
	}

}
