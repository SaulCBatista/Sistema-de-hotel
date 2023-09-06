package br.com.SaulProgramador.hotel.modelo;

import java.util.Date;

public class Reserva {

	private Integer id;
	private Date dataEntrada;
	private Date dateSaida;
	private Double valor;
	private String formaPagamento;

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
		if(this.id.equals(null)) {
			this.id = id;
		}
	}
	
	public Integer getId() {
		return id;
	}

}
