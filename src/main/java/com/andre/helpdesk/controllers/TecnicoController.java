package com.andre.helpdesk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andre.helpdesk.domain.Tecnico;
import com.andre.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value="/tecnico")
public class TecnicoController {

	@Autowired
	TecnicoService tecnicoService;
	
	public ResponseEntity<Tecnico> findById(@PathVariable Long id){
		
		Tecnico obj = tecnicoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
