package br.com.SaulProgramador.exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.SaulProgramador.hotel.controle.ReservasController;

public class Teste {

	public static void main(String[] args) throws ParseException {
		ReservasController reservas = new ReservasController();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date data1 = formato.parse("2023-09-07");
		Date data2 = formato.parse("2023-09-12");
		
		reservas.registrar(data1, data2, "Cartão");
		reservas.commit();
//		System.out.println("Id da reserva: " +id);
	}
	
}
