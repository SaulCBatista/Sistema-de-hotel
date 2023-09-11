package br.com.SaulProgramador.hotel.controle;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import br.com.SaulProgramador.hotel.DAO.Conexao;
import br.com.SaulProgramador.hotel.DAO.HospedesDAO;
import br.com.SaulProgramador.hotel.DAO.ReservasDAO;
import br.com.SaulProgramador.hotel.modelo.Hospede;
import br.com.SaulProgramador.hotel.modelo.Reserva;

public class HospedesController {

	private HospedesDAO hospedesDAO;

	public HospedesController() {
		Connection conexao = new Conexao().recuperarConexao();
		this.hospedesDAO = new HospedesDAO(conexao);
	}

	public Integer registrar(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone) {

		Hospede hospede = new Hospede(null, nome, sobrenome, dataNascimento, nacionalidade, telefone, Reserva.vincularComHospede());
		this.hospedesDAO.cadastrar(hospede);
		Hospede.registrar(hospede);
		return hospede.getIdReserva();
	}
	
	public List<Hospede> listar(){
		this.vicularComBancoDeDados();
		return Hospede.listar();
	}
	
	public List<Hospede> buscarPorIdDeReservaOuSobrenome(Integer idReserva, String sobrenome) {
		return Hospede.buscarPorIdDeReservaOuSobrenome(idReserva, sobrenome);
	}
	
	public void deletar(Integer id, Integer idReserva) {
		Hospede.deletar(id);
		Reserva.deletar(idReserva);
		hospedesDAO.deletar(id);
		new ReservasDAO(new Conexao().recuperarConexao()).deletar(idReserva);
	}

	private void vicularComBancoDeDados() {
		List<Hospede> hospedes = this.hospedesDAO.listar();
		Hospede.vincularComBancoDeDados(hospedes);
	}

}
