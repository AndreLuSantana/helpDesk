package com.andre.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andre.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long>{
	

}
