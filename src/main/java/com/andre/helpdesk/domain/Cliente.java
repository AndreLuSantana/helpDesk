package com.andre.helpdesk.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{

	private List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha, LocalDate dataCriacao) {
		super(id, nome, cpf, email, senha, dataCriacao);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	
}
