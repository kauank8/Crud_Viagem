package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Viagem {
	private int codigo;
	private Onibus onibus;
	private Motorista motorista;
	private int hora_saida;
	private int hora_chegada;
	private String partida;
	private String destino;
}
