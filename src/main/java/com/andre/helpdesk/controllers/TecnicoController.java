package com.andre.helpdesk.controllers;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andre.helpdesk.domain.Tecnico;
import com.andre.helpdesk.domain.dtos.TecnicoDTO;
import com.andre.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

	@Autowired
	TecnicoService tecnicoService;
	
	
	@GetMapping
	public ResponseEntity<Set<TecnicoDTO>> findAll(){
		Set<TecnicoDTO> tecnicos = tecnicoService.findAll();
		return ResponseEntity.ok().body(tecnicos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Long id){
		Tecnico obj = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@RequestBody Tecnico tecnico){
		TecnicoDTO tecnicoDTO = tecnicoService.create(tecnico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(tecnicoDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
