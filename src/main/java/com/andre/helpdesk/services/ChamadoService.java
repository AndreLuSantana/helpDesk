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
import com.andre.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.andre.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;
	
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
	
	public Chamado update(Long id, ChamadoDTO chamadoDTO) {
		chamadoDTO.setId(id);
		Chamado oldObj = findById(id);
		oldObj = newChamado(chamadoDTO);
		return chamadoRepository.save(oldObj);
	}
	
	public Chamado newChamado (ChamadoDTO chamadoDTO) {
		Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico().getId());
		Cliente cliente = clienteService.findById(chamadoDTO.getCliente().getId());
		
		Chamado chamado = new Chamado();
		if (chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		if(chamado.getStatus() == null) {
			chamado.setDataAbertura(LocalDate.now());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade().getCodigo()));
		chamado.setStatus(Status.toEnum(chamadoDTO.getStatus().getCodigo()));
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacoes(chamadoDTO.getObservacoes());
		
		if(chamado.getStatus().getCodigo().equals(2)) {
			chamado.setDataFechameno(LocalDate.now());
		}
		
		return chamado;
		
	}

	/*public void delete(Long id) {
		Chamado chamado = findById(id);
		if(chamado.getStatus().getCodigo() != 2) {
			throw new DataIntegrityViolationException("Chamado não está fechado. Não é possível excluí-lo.");
		}
		chamadoRepository.delete(chamado);
	}*/
}
