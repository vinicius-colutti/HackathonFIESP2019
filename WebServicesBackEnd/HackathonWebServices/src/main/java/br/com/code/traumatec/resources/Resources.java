package br.com.code.traumatec.resources;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.code.traumatec.controller.FarmaciaController;
import br.com.code.traumatec.controller.GravidadeController;
import br.com.code.traumatec.controller.HospitalController;
import br.com.code.traumatec.controller.PedidosController;
import br.com.code.traumatec.model.ConsultaMedicamentos;
import br.com.code.traumatec.model.EspecialidadesHospital;
import br.com.code.traumatec.model.Farmacia;
import br.com.code.traumatec.model.Gravidade;
import br.com.code.traumatec.model.Hospital;
import br.com.code.traumatec.model.Medicamentos;
import br.com.code.traumatec.services.RoboServico;
import br.com.code.traumatec.services.Servicos;

@RestController
@RequestMapping("/servicos")
public class Resources {

	@Autowired
	private Servicos service;

	@Autowired
	private RoboServico roboServico;

	@Autowired
	private HospitalController hospitalController;
	
	@Autowired 
	private GravidadeController gravidadeController;
	
	@Autowired
	private PedidosController pedidosController;
	
	@Autowired
	private FarmaciaController farmaciaController;
	
	@PostMapping("busca-upa")
	public Hospital buscaUpa(@RequestBody String request) {
		JSONObject json = new JSONObject(request);
		return service.buscaUpas(json.get("id_upa").toString());
	}

	@PostMapping("busca-farmacia")
	public Farmacia buscaFarmarcia(@RequestBody String request) throws JSONException, IOException {
		JSONObject json = new JSONObject(request);
		return service.buscaFarmacia(json.get("id_farmacia").toString());
	}

	@PostMapping("inicia-robo")
	public String iniciaRobo() {
		return roboServico.robo("08050040", "08050030");
	}

	@PostMapping("lista-hospitais")
	public List<Hospital> listaHospitais() throws Exception {
		return hospitalController.listaHospitais();
	}
	
	@PostMapping("lista-gravidades")
	public List<Gravidade> listaGravidades() throws Exception{
		return gravidadeController.listaGravidades();
	}

	@PostMapping("lista-especialidades")
	public List<EspecialidadesHospital> listaEspecialidades() throws Exception{
		return hospitalController.listaEspecialidadesHospitais();
	}
	
	@PostMapping("cadastra-pedidos-emergenciais")
	public String cadastraPedidosEmergenciais(@RequestBody String request) throws Exception {
		return pedidosController.cadastraPedidosEmergenciais(request);
	}

	@PostMapping("lista-medicamentos")
	public List<Medicamentos> listaMedicamentos() throws Exception {
		return farmaciaController.listaMedicamentos();
	}
	
	@PostMapping("lista-dados-farmaceuticos")
	public List<ConsultaMedicamentos> listaDadosFarmaceuticos() throws Exception {
		return farmaciaController.listaDadosFarmaceuticos();
	}
	
	@PostMapping("cadastra-consulta-medicamentos")
	public String cadastraConsultaMedicamentos(ConsultaMedicamentos consultaMedicamentos) throws Exception {
		return farmaciaController.cadastraConsultaMedicamentos(consultaMedicamentos);
	}
}
