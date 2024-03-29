package com.andre.helpdesk.domain.enums;

public enum Perfil {

	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");
	
	private Integer código;
	private String descricao;
	
	private Perfil(int código, String descricao) {
		this.código = código;
		this.descricao = descricao;
	}

	public int getCódigo() {
		return código;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for (Perfil p : Perfil.values()) {
			if (cod.equals(p.getCódigo())) {
				return p;
			}
		}
		throw new IllegalArgumentException("Perfil Inválido");
	}
}
