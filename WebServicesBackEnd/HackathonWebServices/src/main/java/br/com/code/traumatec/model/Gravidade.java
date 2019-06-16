package br.com.code.traumatec.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Gravidade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id_gravidade;
	private String nome_gravidade;
}
