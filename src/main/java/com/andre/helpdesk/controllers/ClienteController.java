package com.andre.helpdesk.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andre.helpdesk.domain.dtos.ClienteDTO;
import com.andre.helpdesk.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<Set<ClienteDTO>> findAll(){
		Set<ClienteDTO> clientes = clienteService.findAll();
		return ResponseEntity.ok().body(clientes); 
	}
}
