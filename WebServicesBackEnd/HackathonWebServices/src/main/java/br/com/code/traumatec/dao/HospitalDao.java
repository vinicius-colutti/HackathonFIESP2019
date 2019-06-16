package br.com.code.traumatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.code.traumatec.model.Especialidades;
import br.com.code.traumatec.model.EspecialidadesHospital;
import br.com.code.traumatec.model.EspecialidadesMedicosHospitais;
import br.com.code.traumatec.model.Hospital;
import br.com.code.traumatec.model.MedicosHospitais;

@Service
public class HospitalDao {

	public List<Hospital> listaHospitais() throws Exception {
		List<Hospital> list = new ArrayList<Hospital>();
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn.prepareStatement("Select * From tbl_hospitais");
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			Hospital hospital = new Hospital();
			hospital.setId_hospital(rs.getInt("id_hospital"));
			list.add(hospital);
		}
		Conexao.desconectar(conn);
		return list;
	}

	public List<MedicosHospitais> listaMedicosHospitais() throws Exception {
		List<MedicosHospitais> list = new ArrayList<MedicosHospitais>();
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn.prepareStatement("Select * From tbl_medicos_hospitais");
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			MedicosHospitais medicos_hospitais = new MedicosHospitais();
			medicos_hospitais.setId_medicos_hospitais(rs.getInt("id_medicos_hospitais"));
			medicos_hospitais.setNome_medico(rs.getString("nome_medico"));
			medicos_hospitais.setDisponivel(rs.getBoolean("disponivel"));
			list.add(medicos_hospitais);
		}
		Conexao.desconectar(conn);
		return list;
	}
	
	public List<EspecialidadesMedicosHospitais> listaEspecialidadesMedicosHospitais() throws Exception {
		List<EspecialidadesMedicosHospitais> list = new ArrayList<EspecialidadesMedicosHospitais>();
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn.prepareStatement("Select * From tbl_especialidades_medicos_hospitais");
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			EspecialidadesMedicosHospitais especialidadesMedicosHospitais = new EspecialidadesMedicosHospitais();
			especialidadesMedicosHospitais.setId_especialidades_medicos_hospitais(rs.getInt("id_especialidades_medicos_hospitais"));
			especialidadesMedicosHospitais.setId_especialidade(rs.getInt("id_especialidade"));
			especialidadesMedicosHospitais.setId_medico(rs.getInt("id_medico"));
			list.add(especialidadesMedicosHospitais);
		}
		Conexao.desconectar(conn);
		return list;
	}
	
	public List<Especialidades> listaEspecialidades() throws Exception {
		List<Especialidades> list = new ArrayList<Especialidades>();
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn.prepareStatement("Select * From tbl_especialidades");
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			Especialidades especialidades = new Especialidades();
			especialidades.setId_especialidade(rs.getInt("id_especialidade"));
			especialidades.setNome_especialidade(rs.getString("nome_especialidade"));
			list.add(especialidades);
		}
		Conexao.desconectar(conn);
		return list;
	}
	
	public List<EspecialidadesHospital> listaEspecialidadesHospitais() throws Exception {
		List<EspecialidadesHospital> list = new ArrayList<EspecialidadesHospital>();
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn.prepareStatement("Select * From tbl_especialidades_hospitais");
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			EspecialidadesHospital especialidadesHospitais = new EspecialidadesHospital();
			especialidadesHospitais.setId_especialidade_hospital(rs.getInt("id_especialidade_hospital"));
			especialidadesHospitais.setId_hospital(rs.getInt("id_hospital"));
			especialidadesHospitais.setId_especialidade(rs.getInt("id_especialidade"));
			list.add(especialidadesHospitais);
		}
		Conexao.desconectar(conn);
		return list;
	}

}
