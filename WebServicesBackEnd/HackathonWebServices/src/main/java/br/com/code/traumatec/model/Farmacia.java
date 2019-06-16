package br.com.code.traumatec.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Farmacia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String uf;
	private String cep;
	private String cidade;
	private String nome;
	private String logradouro;
	private String bairro;
	private String email;
	private String cnpj;
	
	
	
	
}
