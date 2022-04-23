package com.andre.helpdesk.services.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andre.helpdesk.domain.Pessoa;
import com.andre.helpdesk.repositories.PessoaRepository;

@Service
public class ValidaEmail {

	@Autowired
	PessoaRepository pessoaRepository;

	public Boolean validaEmail(Pessoa pessoa) {
		
		Optional<Pessoa> obj = pessoaRepository.findByEmail(pessoa.getEmail());
		if(obj.isPresent() && obj.get().getId() != pessoa.getId()) {
			throw new com.andre.helpdesk.services.exceptions.DataIntegrityViolationException("E-mail já cadastrado no sistema.");
		}
		
		return true;
	}
}
