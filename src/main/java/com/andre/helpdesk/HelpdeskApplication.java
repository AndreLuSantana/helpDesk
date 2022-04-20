package com.andre.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andre.helpdesk.domain.Chamado;
import com.andre.helpdesk.domain.Cliente;
import com.andre.helpdesk.domain.Tecnico;
import com.andre.helpdesk.domain.enums.Perfil;
import com.andre.helpdesk.domain.enums.Prioridade;
import com.andre.helpdesk.domain.enums.Status;
import com.andre.helpdesk.repositories.ChamadoRepository;
import com.andre.helpdesk.repositories.ClienteRepository;
import com.andre.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
		
	}

}
