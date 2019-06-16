package br.com.code.traumatec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.code.traumatec.dao.GravidadeDao;
import br.com.code.traumatec.model.Gravidade;

@Service
public class GravidadeController {

	@Autowired
	private GravidadeDao gDao;

	public List<Gravidade> listaGravidades() throws Exception {
		return gDao.listaGravidades();
	}

}
