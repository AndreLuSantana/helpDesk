package com.andre.helpdesk.controllers;

import java.net.URI;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andre.helpdesk.domain.Chamado;
import com.andre.helpdesk.domain.dtos.ChamadoDTO;
import com.andre.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Long id){
		Chamado chamado = chamadoService.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
	}
	
	@GetMapping
	public ResponseEntity<Set<ChamadoDTO>> findAll(){
		Set<ChamadoDTO> chamadoDTO = chamadoService.findAll();
		return ResponseEntity.ok().body(chamadoDTO);
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO){
		Chamado chamado = chamadoService.create(chamadoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> update(@PathVariable Long id, @RequestBody ChamadoDTO chamadoDTO){
		Chamado chamado = chamadoService.update(id, chamadoDTO);
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
	}
	
	/*@DeleteMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> delete (@PathVariable Long id){
		chamadoService.delete(id);
		return ResponseEntity.noContent().build();
	}*/
}
