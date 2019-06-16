package br.com.code.traumatec.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import br.com.code.traumatec.model.Farmacia;
import br.com.code.traumatec.model.Hospital;

@Service
public class Servicos {

	public Hospital buscaUpas(String cod) {
		String str = null;
		try {
			URL url = new URL(
					"http://i3geo.saude.gov.br/i3geo/ogc.php?service=WFS&version=1.0.0&request=GetFeature&typeName=upa_funcionamento_cnes&outputFormat=JSON");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			if (con.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : " + con.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

			str = br.readLine();

			JSONObject json = new JSONObject(str);
			JSONArray jArray = json.getJSONArray("features");
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject obj = jArray.getJSONObject(i);
				String aux = obj.getJSONArray("properties").toString().replace("{", "").replace("}", "");
				aux = aux.replace("[", "{").replace("]", "}");
				if ((jArray.length() - 1) != i) {
					aux = aux + ",";
				}
				JSONObject novojson = new JSONObject(aux);
				if (novojson.get("gid").toString().equals(cod)) {
					Hospital hospital = new Hospital();
					hospital.setId_hospital(Integer.parseInt(novojson.get("gid").toString()));
					hospital.setNome(novojson.get("no_fantasia").toString());
					hospital.setCidade(novojson.get("cidade").toString());
					hospital.setBairro(novojson.get("no_bairro").toString());
					hospital.setLogradouro(novojson.get("no_logradouro").toString());
					hospital.setNumero(novojson.get("nu_endereco").toString());
					return hospital;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return null;
	}

	public Farmacia buscaFarmacia(String cod) throws IOException {
		Farmacia farmacia = new Farmacia();
		BufferedReader br = new BufferedReader(new FileReader("ws/FarmaciasSP.txt"));
		String str = br.readLine();
		str = str.replace("[", "").replace("],", "###,").replace("]", "FFF").replace("\"", "");
		String vet[] = str.split("###,");
		for (int i = 0; i < vet.length; i++) {
			String vetTwo[] = vet[i].split("FFF");
			for (int j = 0; j < vetTwo.length; j++) {
				String uvet[] = vetTwo[j].split(",");
				String cnpj = uvet[uvet.length - 2].replace("\\", "");
				if (cnpj.equals(cod)) {
					farmacia.setUf(uvet[1]);
					farmacia.setNome(uvet[3]);
					farmacia.setCnpj(cnpj);
					farmacia.setCep(uvet[6]);
					URL url = new URL(
							"http://api.postmon.com.br/v1/cep/18081000");
					HttpURLConnection con = (HttpURLConnection) url.openConnection();

					con.setRequestMethod("GET");

					if (con.getResponseCode() != 200) {
						throw new RuntimeException("HTTP error code : " + con.getResponseCode());
					}

					BufferedReader bufr = new BufferedReader(new InputStreamReader((con.getInputStream())));

					str = bufr.readLine();
					JSONObject json = new JSONObject(str);
					farmacia.setCidade(json.getString("cidade"));
					farmacia.setLogradouro(json.getString("logradouro"));
					farmacia.setBairro(json.getString("bairro"));
					
					return farmacia;
				}
			}
		}
		return null;
	}
	
	//Esse metodo busca a farmacia pelo serviço web do governo, porem, ele não está sendo utilizado 
	//pois, ele demora muito para dar um retorno, logo jogamos os dados dele em um arquivo txt no método acima 
	public Farmacia buscaFarmaciaWs(String cod) throws IOException {
		Farmacia farmacia = new Farmacia();
		URL url = new URL(
				"http://api.postmon.com.br/v1/cep/18081000");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");

		if (con.getResponseCode() != 200) {
			throw new RuntimeException("HTTP error code : " + con.getResponseCode());
		}

		BufferedReader bufr = new BufferedReader(new InputStreamReader((con.getInputStream())));

		String str = bufr.readLine();
		JSONObject json = new JSONObject(str);
		
		str = str.replace("[", "").replace("],", "###,").replace("]", "FFF").replace("\"", "");
		String vet[] = str.split("###,");
		for (int i = 0; i < vet.length; i++) {
			String vetTwo[] = vet[i].split("FFF");
			for (int j = 0; j < vetTwo.length; j++) {
				String uvet[] = vetTwo[j].split(",");
				String cnpj = uvet[uvet.length - 2].replace("\\", "");
				if (cnpj.equals(cod)) {
					farmacia.setUf(uvet[1]);
					farmacia.setNome(uvet[3]);
					farmacia.setCnpj(cnpj);
					farmacia.setCep(uvet[6]);
					URL urlCep = new URL(
							"http://api.postmon.com.br/v1/cep/18081000");
					HttpURLConnection conCep = (HttpURLConnection) urlCep.openConnection();

					conCep.setRequestMethod("GET");

					if (conCep.getResponseCode() != 200) {
						throw new RuntimeException("HTTP error code : " + conCep.getResponseCode());
					}

					BufferedReader brCep = new BufferedReader(new InputStreamReader((conCep.getInputStream())));

					String strCep = brCep.readLine();
					JSONObject jsons = new JSONObject(strCep);
					farmacia.setCidade(json.getString("cidade"));
					farmacia.setLogradouro(json.getString("logradouro"));
					farmacia.setBairro(json.getString("bairro"));
					
					return farmacia;
				}
			}
		}
		return null;
	}
}
