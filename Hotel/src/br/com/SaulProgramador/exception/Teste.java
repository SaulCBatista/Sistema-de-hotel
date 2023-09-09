package br.com.SaulProgramador.exception;

import java.text.ParseException;
import java.util.List;

import br.com.SaulProgramador.hotel.controle.HospedesController;
import br.com.SaulProgramador.hotel.controle.ReservasController;
import br.com.SaulProgramador.hotel.modelo.Hospede;
import br.com.SaulProgramador.hotel.modelo.Reserva;

public class Teste {

	public static void main(String[] args) throws ParseException {
		ReservasController reservas = new ReservasController();
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//		Date data1 = formato.parse("2023-08-17");
//		Date data2 = formato.parse("2023-09-21");
//		
//		reservas.registrar(data1, data2, "teste");
//		
		HospedesController hospedesController = new HospedesController();
//		String nome = "teste";
//		String sobrenome = "teste";
//		Date dataNas = formato.parse("1997-02-03");
//		String nascionalidade = "Frances";
//		String telefone = "8971513521";
//		
//		hospedesController.registrar(nome, sobrenome, dataNas, nascionalidade, telefone);
	
		hospedesController.deletar(8, 23);
		
		List<Reserva> listaR = reservas.listar();
		List<Hospede> listaH = hospedesController.listar();
		
		listaR.forEach(reserva -> System.out.println(reserva));
		listaH.forEach(hospede -> System.out.println(hospede));
		
	}
	
}
