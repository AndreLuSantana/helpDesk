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

import com.andre.helpdesk.domain.Cliente;
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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id){
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(cliente));
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody Cliente cliente){
		ClienteDTO clienteDTO = clienteService.create(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(clienteDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value= "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDto){
		Cliente cliente = clienteService.update(id, clienteDto);
		return ResponseEntity.ok().body(new ClienteDTO(cliente));
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Long id){
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
