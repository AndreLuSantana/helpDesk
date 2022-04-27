package com.andre.helpdesk.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.helpdesk.domain.Cliente;
import com.andre.helpdesk.domain.dtos.ClienteDTO;
import com.andre.helpdesk.repositories.ClienteRepository;
import com.andre.helpdesk.services.exceptions.DataIntegrityViolationException;
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

	public Cliente update(Long id, @Valid ClienteDTO clienteDto) {
		Cliente cliente = findById(id);
		validaCPF.validaCPF(cliente);
		validaEmail.validaEmail(cliente);
		updateData(cliente, clienteDto);
		clienteRepository.save(cliente);
		return cliente;
	}

	private void updateData(Cliente cliente, @Valid ClienteDTO clienteDto) {
		cliente.setNome(clienteDto.getNome());
		cliente.setCpf(clienteDto.getCpf());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setSenha(cliente.getSenha());
	}

	public void delete(Long id) {
		Cliente cliente = findById(id);
		if(cliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente com chamado em aberto. Não é possível excluí-lo.");
		}
		
		clienteRepository.delete(cliente); 
	}
	
	
}
