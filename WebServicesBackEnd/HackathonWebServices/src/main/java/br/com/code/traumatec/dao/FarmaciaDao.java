package br.com.code.traumatec.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.code.traumatec.model.ConsultaMedicamentos;
import br.com.code.traumatec.model.Farmacia;
import br.com.code.traumatec.model.Hospital;
import br.com.code.traumatec.model.Medicamentos;
import br.com.code.traumatec.model.MedicamentosFarmacia;
import br.com.code.traumatec.services.Servicos;

@Service
public class FarmaciaDao {

	@Autowired
	private Servicos servicos;

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public List<Medicamentos> listaMedicamentos() throws Exception {
		List<Medicamentos> list = new ArrayList<Medicamentos>();
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn.prepareStatement("Select * From tbl_medicamentos");
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			Medicamentos medicamentos = new Medicamentos();
			medicamentos.setId_medicamentos(rs.getInt("id_medicamentos"));
			medicamentos.setNome_medicamento(rs.getString("nome_medicamento"));
			list.add(medicamentos);
		}
		Conexao.desconectar(conn);
		return list;
	}

	public List<Farmacia> listaFarmacias() throws Exception {
		List<Farmacia> list = new ArrayList<Farmacia>();
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn.prepareStatement("Select * From tbl_farmacia");
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			Farmacia farmacia = new Farmacia();
			farmacia.setCnpj(rs.getString("id_farmacia"));
			list.add(farmacia);
		}
		Conexao.desconectar(conn);
		return list;
	}

	public List<MedicamentosFarmacia> listaMedicamentosFarmacia() throws Exception {
		List<MedicamentosFarmacia> list = new ArrayList<MedicamentosFarmacia>();
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn.prepareStatement("Select * From tbl_farmacia");
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			MedicamentosFarmacia medicamentosFarmacia = new MedicamentosFarmacia();
			medicamentosFarmacia.setId_medicamentos_farmacia(rs.getInt("id_medicamentos_farmacia"));
			medicamentosFarmacia.setId_medicamento(rs.getInt("id_medicamento"));
			medicamentosFarmacia.setId_farmacia(rs.getString("id_farmacia"));
			boolean disponivel = false;
			if (rs.getInt("disponivel") == 1)
				disponivel = true;
			medicamentosFarmacia.setDisponivel(disponivel);
			list.add(medicamentosFarmacia);
		}
		Conexao.desconectar(conn);
		return list;
	}

	public int buscaUltimoId() throws Exception {
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn
				.prepareStatement("Select max(id_consultas_medicamentos) From tbl_consultas_medicamentos");
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			return rs.getInt("id_consultas_medicamentos");
		}
		Conexao.desconectar(conn);
		return 0;
	}

	public List<Medicamentos> listaMedicamentoConsultas(int id_consulta) throws Exception {
		List<Medicamentos> list = new ArrayList<Medicamentos>();
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn.prepareStatement(
				"Select * From tbl_medicamentos_consulta inner join tbl_medicamentos on tbl_medicamentos_consulta.id_medicamentos = tbl_medicamentos.id_medicamentos where id_consulta = ?");
		pstm.setInt(1, id_consulta);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			Medicamentos medicamentos = new Medicamentos();
			medicamentos.setId_medicamentos(rs.getInt("id_medicamentos"));
			medicamentos.setNome_medicamento(rs.getString("nome_medicamento"));
			list.add(medicamentos);
		}
		Conexao.desconectar(conn);
		return list;
	}

	public List<ConsultaMedicamentos> listaDadosFarmaceuticos() throws Exception {
		List<ConsultaMedicamentos> list = new ArrayList<ConsultaMedicamentos>();
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn.prepareStatement("Select * From tbl_consultas_medicamentos");
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			ConsultaMedicamentos consulta = new ConsultaMedicamentos();
			consulta.setId_consultas_medicamentos(rs.getInt("id_consultas_medicamentos"));
			consulta.setIdentificador(rs.getString("identificador"));
			consulta.setNome_paciente(rs.getString("nome_paciente"));
			consulta.setListaMedicamentos(listaMedicamentoConsultas(consulta.getId_consultas_medicamentos()));
			list.add(consulta);
		}
		Conexao.desconectar(conn);
		return list;
	}

//	public Farmacia pegaFarmacia(String cnpj) throws Exception {
//		Farmacia farmacia = new Farmacia();
//		for(Farmacia farm : listaFarmacias()) {
//			if(farm.getCnpj().equals(cnpj)) {
//				return farmacia;
//			}
//		}
//		return farmacia;
//	}
//	

	// Este serviço busca as farmacias mais proximas, porem, não possuímos verba
	// para utilizar essa API
	public Farmacia buscaFarmacia(String cod) {
		String str = null;
		Farmacia farmacia = new Farmacia();
		try {
			URL url = new URL(
					"https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Washington,DC&destinations=New+York+City,NY&key=YOUR_API_KEY");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			if (con.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : " + con.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

			str = br.readLine();

			return farmacia;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return null;
	}

	public String cadastraConsultaMedicamentos(ConsultaMedicamentos consultaMedicamentos) throws Exception {
		Farmacia farma = new Farmacia();
		for (Medicamentos medicamento : consultaMedicamentos.getListaMedicamentos()) {
			for (MedicamentosFarmacia medicamentoFarmacia : listaMedicamentosFarmacia()) {
				if (medicamento.getId_medicamentos() == medicamentoFarmacia.getId_medicamento()) {
					farma.setCnpj(medicamentoFarmacia.getId_farmacia());
					break;
				}
			}
		}
		if (farma.getCnpj() != "") {
			String hash = randomAlphaNumeric(4);
			farma = servicos.buscaFarmacia(farma.getCnpj());
			Connection conn = Conexao.conectar();
			PreparedStatement pstm = conn.prepareStatement(
					"Insert into tbl_consultas_medicamentos(nome_paciente, doc_paciente, endereco_da_consulta, id_farmacia, finalizado, observacoes, identificador) values (?,?,?,?,?,?,?)");
			pstm.setString(1, consultaMedicamentos.getNome_paciente());
			pstm.setString(2, consultaMedicamentos.getDoc_paciente());
			pstm.setString(3, consultaMedicamentos.getEndereco_da_consulta());
			pstm.setString(4, farma.getCnpj());
			pstm.setBoolean(5, consultaMedicamentos.isFinalizado());
			pstm.setString(6, consultaMedicamentos.getObservacoes());
			pstm.setString(7, hash);
			pstm.execute();
			pstm.close();

			for (Medicamentos m : listaMedicamentos()) {
				PreparedStatement pstm2 = conn.prepareStatement(
						"Insert into tbl_medicamentos_consulta(id_medicamentos, id_consulta) values (?,?)");
				pstm2.setInt(1, m.getId_medicamentos());
				pstm2.setInt(2, buscaUltimoId());
				pstm2.execute();
				pstm2.close();
			}
			return "{ \"message\": \"Solitação enviada!\", \"farmacia\": " + farma.getNome() + ",\"endereco\": \""
					+ farma.getLogradouro() + "\", \"bairro\": \"" + farma.getBairro() + "\",\"identificador\": \""
					+ hash + "\"}";
		} else {
			return "\"message\":\"medicamento não encontrado!\"";
		}
	}

}
