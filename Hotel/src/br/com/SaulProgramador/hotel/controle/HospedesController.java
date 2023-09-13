package br.com.SaulProgramador.hotel.controle;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.SaulProgramador.hotel.DAO.Conexao;
import br.com.SaulProgramador.hotel.DAO.HospedesDAO;
import br.com.SaulProgramador.hotel.DAO.ReservasDAO;
import br.com.SaulProgramador.hotel.modelo.Hospede;

public class HospedesController {

	private HospedesDAO hospedesDAO;

	public HospedesController() {
		Connection conexao = new Conexao().recuperarConexao();
		this.hospedesDAO = new HospedesDAO(conexao);
	}

	public void registrar(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone, Integer idDaReserva) {
		Hospede hospede = new Hospede(null, nome, sobrenome, dataNascimento, nacionalidade, telefone, idDaReserva);
		this.hospedesDAO.cadastrar(hospede);
	}
	
	public List<Hospede> listar(){
		return hospedesDAO.listar();
	}
	
	public List<Hospede> buscarPorIdDeReservaOuSobrenome(Integer idReserva, String sobrenome) {
		return hospedesDAO.buscarPorIdDeReservaOuSobrenome(idReserva, sobrenome);
	}
	
	public void deletar(Integer id, Integer idReserva) {
		hospedesDAO.deletar(id);
		new ReservasDAO(new Conexao().recuperarConexao()).deletar(idReserva);
	}

	public boolean verificarIdade(Date data) {
		GregorianCalendar dataAtual= new GregorianCalendar();
		GregorianCalendar dataNascimento = new GregorianCalendar();
		dataNascimento.setTime(data);
		int anoAtual = dataAtual.get(Calendar.YEAR);
		int anoDeNascimento = dataNascimento.get(Calendar.YEAR);
		int idade = anoAtual - anoDeNascimento;
		
		if(idade >= 18) {
			return true;
		}
		
		return false;
	}

}
