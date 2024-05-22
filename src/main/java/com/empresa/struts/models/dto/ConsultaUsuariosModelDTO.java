package com.empresa.struts.models.dto;

public class ConsultaUsuariosModelDTO extends PaginacaoModelDTO {    
    private String nmLogin;
    private String nmRole;
    
	public String getNmLogin() {
		return nmLogin;
	}
	public void setNmLogin(String nmLogin) {
		this.nmLogin = nmLogin;
	}
	public String getNmRole() {
		return nmRole;
	}
	public void setNmRole(String nmRole) {
		this.nmRole = nmRole;
	}    
}