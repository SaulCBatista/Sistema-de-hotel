package br.com.SaulProgramador.hotel.controle;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.SaulProgramador.hotel.DAO.Conexao;
import br.com.SaulProgramador.hotel.DAO.HospedesDAO;
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
	
	public List<Hospede> buscarPorSobrenome(String sobrenome) {
		return hospedesDAO.buscarPorSobrenome(sobrenome);
	}
	
	public List<Hospede> buscarPorIdDeReserva(Integer idReserva) {
		return hospedesDAO.buscarPorIdDeReserva(idReserva);
	}
	
	public Integer buscarIdDeReserva(Integer id) {
		return hospedesDAO.buscarIdDeReserva(id);
	}
	
	public void atualizar(Integer id, String nome, String sobrenome, java.sql.Date dataNascimento, String nacionalidade,String telefone, Integer idReserva) {
		Hospede hospede = new Hospede(id, nome, sobrenome, dataNascimento, nacionalidade, telefone, idReserva);
		hospedesDAO.atualizar(hospede);
	}
	
	public void deletar(Integer id) {
		hospedesDAO.deletar(id);
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
