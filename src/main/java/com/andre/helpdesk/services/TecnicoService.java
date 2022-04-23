package com.andre.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.andre.helpdesk.domain.Tecnico;
import com.andre.helpdesk.repositories.TecnicoRepository;
import com.andre.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	TecnicoRepository tecnicoRepository;
	
	public Tecnico findById(Long id) {
		
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID = " + id));
	}

}
