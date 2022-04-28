package com.andre.helpdesk.services;

import java.time.LocalDate;
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
		Tecnico tec2 = new Tecnico(null, "André Santana", "817.602.482-15", "andre@mail.com", "123");
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "718.881.310-80", "torvalds@mail.com", "123");
		Cliente cli2 = new Cliente(null, "Karleane Cleica Lobato de Vasconcelos", "062.201.190-10", "nane@mail.com", "123");
		
		Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,"Chamdo 01", "Primeiro chamado", cli1, tec1);
		Chamado cha2 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO,"Chamdo 02", "Segundo chamado", cli2, tec2);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		tecnicoRepository.saveAll(Arrays.asList(tec2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		clienteRepository.saveAll(Arrays.asList(cli2));
		chamadoRepository.saveAll(Arrays.asList(cha1));
		chamadoRepository.saveAll(Arrays.asList(cha2));
	}
}
