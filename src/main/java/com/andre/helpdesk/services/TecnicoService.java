package com.andre.helpdesk.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.helpdesk.domain.Tecnico;
import com.andre.helpdesk.domain.dtos.TecnicoDTO;
import com.andre.helpdesk.repositories.TecnicoRepository;
import com.andre.helpdesk.services.exceptions.ObjectNotFoundException;
import com.andre.helpdesk.services.validation.ValidaCPF;
import com.andre.helpdesk.services.validation.ValidaEmail;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ValidaCPF validaCPF;
	@Autowired
	private ValidaEmail validaEmail;
	
	public Set<TecnicoDTO> findAll() {
		List<Tecnico> tecnicos = tecnicoRepository.findAll();
		return tecnicos.stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toSet());
	}
	
	public Tecnico findById(Long id) {
		
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID = " + id));
	}

	public TecnicoDTO create(Tecnico obj) {
		obj.setId(null);
		validaCPF.validaCPF(obj);
		validaEmail.validaEmail(obj);
		Tecnico tecnico = tecnicoRepository.save(obj);
		
		return new TecnicoDTO(tecnico);
	}
	
}
