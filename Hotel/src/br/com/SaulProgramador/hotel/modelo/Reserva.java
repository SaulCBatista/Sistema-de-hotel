package br.com.SaulProgramador.hotel.modelo;

import java.util.Date;

public class Reserva {

	private Integer id;
	private Date dataEntrada;
	private Date dataSaida;
	private Double valor;
	private String formaPagamento;

	public Reserva(Integer id, Date dataEntrada, Date dataSaida, Double valor, String formaPagamento) {
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
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
		return dataSaida;
	}

	public void setDateSaida(Date dateSaida) {
		this.dataSaida = dateSaida;
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
}
