package com.andre.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.helpdesk.domain.Chamado;
import com.andre.helpdesk.domain.Cliente;
import com.andre.helpdesk.domain.Tecnico;
import com.andre.helpdesk.domain.dtos.ChamadoDTO;
import com.andre.helpdesk.domain.enums.Prioridade;
import com.andre.helpdesk.domain.enums.Status;
import com.andre.helpdesk.repositories.ChamadoRepository;
import com.andre.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteServece;
	
	public Chamado findById(Long id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		return chamado.orElseThrow(()-> new ObjectNotFoundException("Chamado não encontrado."));
	}

	public Set<ChamadoDTO> findAll() {
		List<Chamado> chamados = chamadoRepository.findAll();
		return chamados.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toSet());
	}

	public Chamado create(@Valid ChamadoDTO chamadoDTO) {
		
		return chamadoRepository.save(newChamado(chamadoDTO));
	}
	
	public Chamado newChamado(ChamadoDTO chamadoDTO) {
		Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico().getId());
		Cliente cliente = clienteServece.findById(chamadoDTO.getCliente().getId());
		
		Chamado chamado = new Chamado();
		if (chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setDataAbertura(LocalDate.now());
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade().getCodigo()));
		chamado.setStatus(Status.toEnum(chamadoDTO.getStatus().getCodigo()));
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacoes(chamadoDTO.getObservacoes());
		
		return chamado;
		
	}
}
