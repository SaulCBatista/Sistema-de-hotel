package br.com.SaulProgramador.hotel.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva {

	private Integer id;
	private Date dataEntrada;
	private Date dateSaida;
	private Double valor;
	private String formaPagamento;

	private static List<Reserva> reservas = new ArrayList<Reserva>();

	public Reserva(Integer id, Date dataEntrada, Date dateSaida, Double valor, String formaPagamento) {
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dateSaida = dateSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dateSaida;
	}

	public void setDateSaida(Date dateSaida) {
		this.dateSaida = dateSaida;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public void setId(Integer id) {
		if (this.id == null) {
			this.id = id;
		}
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {

		return "id: " + this.getId() + ", data de entrada: " + this.getDataEntrada() + ", data de saida: "
				+ this.getDataSaida() + ", valor: " + this.getValor() + ", forma de pagamento: "
				+ this.getFormaPagamento();
	}

	public static void registrar(Reserva reserva) {
		reservas.add(reserva);
	}

	public static List<Reserva> listar() {
		return reservas;
	}

	public static List<Reserva> buscarPorId(Integer id) {
		List<Reserva> listaDeReservas = new ArrayList<Reserva>();
		
		for(int i = 0; i < Reserva.reservas.size(); i++) {
			if(Reserva.reservas.get(i).getId() == id) {
				listaDeReservas.add(reservas.get(i));
			}
		}
		return listaDeReservas;
	}
	
	public static void deletar(Integer id) {
		for(int i = 0; i < Reserva.reservas.size(); i++) {
			if(Reserva.reservas.get(i).getId() == id) {
				Reserva.reservas.remove(i);
			}
		}
	}

	public static Integer cancelarReserva() {
		Integer id = reservas.get(reservas.size() - 1).getId();
		reservas.remove(reservas.size() - 1);
		return id;
		
	}

	public static Integer vincularComHospede() {
		return reservas.get(reservas.size() - 1).getId();
	}

	public static void vincularComBancoDeDados(List<Reserva> reservas) {
		Reserva.reservas = reservas;

	}


}
