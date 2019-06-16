package br.com.code.traumatec.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Medicamentos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id_medicamentos;
	private String nome_medicamento;

}
