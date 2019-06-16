package br.com.code.traumatec.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Leitos implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean status_disponivel;
	private Hospital hospital;
	

}
