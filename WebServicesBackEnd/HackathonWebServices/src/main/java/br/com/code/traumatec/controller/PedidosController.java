package br.com.code.traumatec.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.code.traumatec.dao.PedidosDao;
import br.com.code.traumatec.model.PedidosEmergenciais;

@Service
public class PedidosController {

	@Autowired
	private PedidosDao pedidosDao;
	
	public String cadastraPedidosEmergenciais(String request) throws Exception {
		PedidosEmergenciais pedidosEmergenciais = new PedidosEmergenciais();
		JSONObject json = new JSONObject(request);
		pedidosEmergenciais.setId_especialidade(json.getInt("id_especialidade"));
		pedidosEmergenciais.setId_paramedico(json.getInt("id_paramedico"));
		boolean situacao = false;
		if(json.getInt("situacao") == 1) {
			situacao = true;
		}
		pedidosEmergenciais.setSituacao(situacao);
		pedidosEmergenciais.setIdade_paciente(json.getString("idade_paciente"));
		pedidosEmergenciais.setGenero_paciente(json.getString("genero_paciente"));
		pedidosEmergenciais.setTraumas(json.getString("traumas"));
		pedidosEmergenciais.setIdentificacao_paciente(json.getString("identificacao_paciente"));
		pedidosEmergenciais.setId_gravidade(json.getInt("id_gravidade"));
		return pedidosDao.cadastraPedidosEmergenciais(pedidosEmergenciais);
	}
}
