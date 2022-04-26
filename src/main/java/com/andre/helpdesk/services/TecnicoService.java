package com.andre.helpdesk.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.helpdesk.domain.Tecnico;
import com.andre.helpdesk.domain.dtos.TecnicoDTO;
import com.andre.helpdesk.repositories.TecnicoRepository;
import com.andre.helpdesk.services.exceptions.DataIntegrityViolationException;
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

	public Tecnico update(Long id, @Valid TecnicoDTO objDTO) {
		Tecnico tecnico = findById(id);
		validaCPF.validaCPF(tecnico);
		validaEmail.validaEmail(tecnico);
		updateData(tecnico, objDTO);
		tecnicoRepository.save(tecnico);
		return tecnico;
	}

	private void updateData(Tecnico tecnico, @Valid TecnicoDTO objDTO) {
		tecnico.setNome(objDTO.getNome());
		tecnico.setCpf(objDTO.getCpf());
		tecnico.setEmail(objDTO.getEmail());
		tecnico.setSenha(objDTO.getSenha());
	}
	
	public void delete(TecnicoDTO tecnicoDTO) {
		Tecnico tecnico = findById(tecnicoDTO.getId());
		if(tecnico.getChamados().size() > 0){
			throw new DataIntegrityViolationException("Técnico com chamados em aberto. Não é possível excluí-lo.");
		}
		tecnicoRepository.delete(tecnico);
	}
	
}	
