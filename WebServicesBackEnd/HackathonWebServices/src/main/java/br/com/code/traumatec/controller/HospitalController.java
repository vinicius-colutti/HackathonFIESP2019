package br.com.code.traumatec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.code.traumatec.dao.HospitalDao;
import br.com.code.traumatec.model.Especialidades;
import br.com.code.traumatec.model.EspecialidadesHospital;
import br.com.code.traumatec.model.EspecialidadesMedicosHospitais;
import br.com.code.traumatec.model.Hospital;
import br.com.code.traumatec.model.MedicosHospitais;

@Service
public class HospitalController {

	@Autowired
	private HospitalDao hospital;

	public List<Hospital> listaHospitais() throws Exception {
		return hospital.listaHospitais();
	}
	
	public List<MedicosHospitais> listaMedicosHospitais() throws Exception {
		return hospital.listaMedicosHospitais();
	}
	
	public List<EspecialidadesMedicosHospitais> listaEspecialidadesMedicosHospitais() throws Exception {
		return hospital.listaEspecialidadesMedicosHospitais();
	}
	
	public List<Especialidades> listaEspecialidades() throws Exception {
		return hospital.listaEspecialidades();
	}
	
	public List<EspecialidadesHospital> listaEspecialidadesHospitais() throws Exception {
		return hospital.listaEspecialidadesHospitais();
	}
	
}
