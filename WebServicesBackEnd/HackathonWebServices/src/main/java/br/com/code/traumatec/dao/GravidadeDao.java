package br.com.code.traumatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.code.traumatec.model.Gravidade;

@Service
public class GravidadeDao {

	public List<Gravidade> listaGravidades() throws Exception {
		List<Gravidade> list = new ArrayList<Gravidade>();
		Connection conn = Conexao.conectar();
		PreparedStatement pstm = conn.prepareStatement("Select * From tbl_gravidade");
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			Gravidade gravidade = new Gravidade();
			gravidade.setId_gravidade(rs.getInt("id_gravidade"));
			gravidade.setNome_gravidade(rs.getString("nome_gravidade"));
			list.add(gravidade);
		}
		Conexao.desconectar(conn);
		return list;
	}

	
}
