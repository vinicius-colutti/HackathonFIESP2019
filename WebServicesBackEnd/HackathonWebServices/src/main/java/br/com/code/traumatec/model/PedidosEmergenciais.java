package br.com.code.traumatec.model;

import lombok.Data;

@Data
public class PedidosEmergenciais {

	private int int_pedidos_emergenciais;
	private int id_paramedico;
	private int id_hospital;
	private boolean situacao;
	private String idade_paciente;
	private String genero_paciente;
	private String traumas;
	private String identificacao_paciente;
	private int id_gravidade;
	private int id_especialidade;
}
