package com.empresa.struts.models;

public class ConsultaUsuariosModel extends PaginacaoModel {    
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