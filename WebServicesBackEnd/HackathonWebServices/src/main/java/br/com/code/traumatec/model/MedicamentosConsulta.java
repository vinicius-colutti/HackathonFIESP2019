package br.com.code.traumatec.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class MedicamentosConsulta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id_medicamentos_consulta;
	private int id_medicamentos;
	private int id_consulta;
}
