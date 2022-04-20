package com.andre.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.helpdesk.domain.Chamado;
import com.andre.helpdesk.domain.Cliente;
import com.andre.helpdesk.domain.Tecnico;
import com.andre.helpdesk.domain.enums.Perfil;
import com.andre.helpdesk.domain.enums.Prioridade;
import com.andre.helpdesk.domain.enums.Status;
import com.andre.helpdesk.repositories.ChamadoRepository;
import com.andre.helpdesk.repositories.ClienteRepository;
import com.andre.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

public void instanciaDB() {
		
		
		Tecnico tec1 = new Tecnico(null, "Valdir César", "614.371.150-04", "valdir@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "718.881.310-80", "torvalds@mail.com", "123");
		
		Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,"Chamdo 01", "Primeiro chamado", cli1, tec1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(cha1));
	}
}