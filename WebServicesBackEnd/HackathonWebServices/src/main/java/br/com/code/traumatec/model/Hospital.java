package br.com.code.traumatec.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data

public class Hospital implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id_hospital;
	private String nome;
	private String cidade;
	private String bairro;
	private String logradouro;
	private String numero;
	private List<Leitos> leitos = new ArrayList<Leitos>();

}
