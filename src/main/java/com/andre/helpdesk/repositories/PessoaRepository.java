package com.andre.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andre.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	

}
