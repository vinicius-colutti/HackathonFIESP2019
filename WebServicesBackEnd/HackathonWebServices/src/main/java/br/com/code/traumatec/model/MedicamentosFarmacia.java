package br.com.code.traumatec.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class MedicamentosFarmacia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id_medicamentos_farmacia;
	private int id_medicamento;
	private String id_farmacia;
	private boolean disponivel;
	
}
