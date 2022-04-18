package com.andre.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andre.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long>{
	

}
