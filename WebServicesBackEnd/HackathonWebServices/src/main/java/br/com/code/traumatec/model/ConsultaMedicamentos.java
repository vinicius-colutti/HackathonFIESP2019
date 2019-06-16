package br.com.code.traumatec.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ConsultaMedicamentos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id_consultas_medicamentos;
	private String nome_paciente;
	private String doc_paciente;
	private String endereco_da_consulta;
	private int id_farmacia;
	private boolean finalizado;
	private String observacoes;
	private String identificador;
	private List<Medicamentos> listaMedicamentos;
}
