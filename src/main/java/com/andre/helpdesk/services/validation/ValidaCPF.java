package com.andre.helpdesk.services.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.andre.helpdesk.domain.Pessoa;
import com.andre.helpdesk.repositories.PessoaRepository;

@Service
public class ValidaCPF {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	private Integer tamanhoCPF = 11;

	public ValidaCPF() {
		super();
		
	}
	
	public Boolean validaCPF(Pessoa pessoa) {
		
		Optional<Pessoa> obj = pessoaRepository.findByCpf(pessoa.getCpf());
		if(obj.isPresent() && obj.get().getId() != pessoa.getId()) {
			throw new com.andre.helpdesk.services.exceptions.DataIntegrityViolationException("CPF já cadastrado no sistema.");
			
			
		}
		if( pessoa.getCpf().length() != tamanhoCPF) {
			throw new com.andre.helpdesk.services.exceptions.StringIndexOutOfBoundsException("O CPF deve conter 11 dígitos.");
		}
		return true;
	}
}
