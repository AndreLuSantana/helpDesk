package com.andre.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.andre.helpdesk.domain.Chamado;
import com.andre.helpdesk.domain.Cliente;
import com.andre.helpdesk.domain.Tecnico;
import com.andre.helpdesk.domain.enums.Prioridade;
import com.andre.helpdesk.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ChamadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDate dataAbertura;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechameno;

	private Prioridade prioridade;
	private Status status;
	private String titulo;
	private String observacoes;
	private Cliente cliente;
	private Tecnico tecnico;
	
	public ChamadoDTO() {
		super();
		
	}
	public ChamadoDTO(Chamado chamado) {
		super();
		this.id = chamado.getId();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechameno = chamado.getDataFechameno();
		this.prioridade = chamado.getPrioridade();
		this.status = chamado.getStatus();
		this.titulo = chamado.getTitulo();
		this.observacoes = chamado.getObservacoes();
		this.cliente = chamado.getCliente();
		this.tecnico = chamado.getTecnico();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public LocalDate getDataFechameno() {
		return dataFechameno;
	}
	public void setDataFechameno(LocalDate dataFechameno) {
		this.dataFechameno = dataFechameno;
	}
	public Prioridade getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Tecnico getTecnico() {
		return tecnico;
	}
	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	
}
