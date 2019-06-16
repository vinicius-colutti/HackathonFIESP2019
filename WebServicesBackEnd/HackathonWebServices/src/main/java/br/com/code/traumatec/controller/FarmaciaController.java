package br.com.code.traumatec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.code.traumatec.dao.FarmaciaDao;
import br.com.code.traumatec.model.ConsultaMedicamentos;
import br.com.code.traumatec.model.Medicamentos;

@Service
public class FarmaciaController {

	@Autowired
	private FarmaciaDao farmaciaDao;
	
	public List<Medicamentos> listaMedicamentos() throws Exception {
		return farmaciaDao.listaMedicamentos();
	}
	
	public List<ConsultaMedicamentos> listaDadosFarmaceuticos() throws Exception {
		return farmaciaDao.listaDadosFarmaceuticos();
	}
	
	public String cadastraConsultaMedicamentos(ConsultaMedicamentos consultaMedicamentos) throws Exception {
		return farmaciaDao.cadastraConsultaMedicamentos(consultaMedicamentos);
	}
}
