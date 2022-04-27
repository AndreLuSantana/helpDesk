package com.andre.helpdesk.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.helpdesk.domain.Cliente;
import com.andre.helpdesk.domain.dtos.ClienteDTO;
import com.andre.helpdesk.repositories.ClienteRepository;
import com.andre.helpdesk.services.exceptions.ObjectNotFoundException;
import com.andre.helpdesk.services.validation.ValidaCPF;
import com.andre.helpdesk.services.validation.ValidaEmail;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ValidaCPF validaCPF;
	@Autowired
	private ValidaEmail validaEmail;
	
	public Set<ClienteDTO> findAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toSet());
	}
	
	public Cliente findById(Long id) {
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		return cliente.orElseThrow(()-> new ObjectNotFoundException("Cliente não encontrado ID : " + id));
	}
	
	public ClienteDTO create(Cliente obj) {
		obj.setId(null);
		validaCPF.validaCPF(obj);
		validaEmail.validaEmail(obj);
		Cliente cliente = clienteRepository.save(obj);
		return new ClienteDTO(cliente);
	}
	
	
}
