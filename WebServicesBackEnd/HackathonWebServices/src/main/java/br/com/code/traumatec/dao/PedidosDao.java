package br.com.code.traumatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.code.traumatec.controller.HospitalController;
import br.com.code.traumatec.model.EspecialidadesHospital;
import br.com.code.traumatec.model.EspecialidadesMedicosHospitais;
import br.com.code.traumatec.model.Hospital;
import br.com.code.traumatec.model.MedicosHospitais;
import br.com.code.traumatec.model.PedidosEmergenciais;
import br.com.code.traumatec.services.Servicos;

@Service
public class PedidosDao {

	@Autowired
	private Servicos servicosWs;

	@Autowired
	private HospitalController hpController;

	public int buscaMedico(int id_especialidade) throws Exception {
		for (EspecialidadesMedicosHospitais medicoEspecialidade : hpController.listaEspecialidadesMedicosHospitais()) {
			if (medicoEspecialidade.getId_especialidade() == id_especialidade) {

				return medicoEspecialidade.getId_medico();
			}
		}
		return -1;
	}

	public MedicosHospitais retornaMedicoDisponivel(int id_medico) throws Exception {
		for (MedicosHospitais medico : hpController.listaMedicosHospitais()) {
			if (medico.getId_medicos_hospitais() == id_medico && medico.isDisponivel()) {

				return medico;
			}
		}
		return null;
	}

	public EspecialidadesHospital buscaEspecialidadesHospital(int id_especialidade) throws Exception {
		EspecialidadesHospital espRetorno = new EspecialidadesHospital();
		for (EspecialidadesHospital esp : hpController.listaEspecialidadesHospitais()) {
			if (esp.getId_especialidade() == id_especialidade) {
				espRetorno = esp;
			}
		}
		return espRetorno;
	}

	public String cadastraPedidosEmergenciais(PedidosEmergenciais pedidos) throws Exception {
		EspecialidadesHospital hospital = new EspecialidadesHospital();
		if (buscaEspecialidadesHospital(pedidos.getId_especialidade()) != null) {
			if (buscaMedico(pedidos.getId_especialidade()) != -1) {
				hospital = buscaEspecialidadesHospital(pedidos.getId_especialidade());
			} else {
				return "Não há médicos para essa especialidade";
			}
		} else {
			return "Não há hospitais para essa especialidade";
		}
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn.prepareStatement(
				"Insert into tbl_pedidos_emergencias(id_paramedico, id_hospital, situacao, idade_paciente, genero_paciente, traumas, identificacao_paciente, id_gravidade,data_insercao) values (?,?,?,?,?,?,?,?,?)");
		pstm.setInt(1, pedidos.getId_paramedico());
		pstm.setInt(2, hospital.getId_hospital());
		pstm.setBoolean(3, pedidos.isSituacao());
		pstm.setString(4, pedidos.getIdade_paciente());
		pstm.setString(5, pedidos.getGenero_paciente());
		pstm.setString(6, pedidos.getTraumas());
		pstm.setString(7, pedidos.getIdentificacao_paciente());
		pstm.setInt(8, pedidos.getId_gravidade());
		pstm.setString(9, new Date().toString());
		pstm.execute();
		Conexao.desconectar(conn);
		Hospital hp = servicosWs.buscaUpas(hospital.getId_hospital() + "");

		return "{ \"message\": \"Solitação enviada!\", \"hospital\": " + hp.getNome() + ",\"endereco\": \""
				+ hp.getLogradouro() + "\", \"bairro\": \"" + hp.getBairro() + "\"}";

		// return "{ \"message\": \"Ocorreu um erro ao inserir!\"}";
	}

}
