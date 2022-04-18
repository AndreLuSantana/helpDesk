package com.andre.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andre.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	
}
