package com.empresa.struts.models.dto;

public class CadastroUsuarioModelDTO {    
    private String nmLogin;
    private String dsSenha;
    private int qtTempoInatividade;
    private boolean loaded = false;

    public String getNmLogin() {
        return nmLogin;
    }

    public void setNmLogin(String nmLogin) {
        this.nmLogin = nmLogin;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }

    public int getQtTempoInatividade() {
        return qtTempoInatividade;
    }

    public void setQtTempoInatividade(int qtTempoInatividade) {
        this.qtTempoInatividade = qtTempoInatividade;
    }
    
    public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
    
    public UsuarioDTO makeUsuario(boolean setPassword) {
    	UsuarioDTO usuario = new UsuarioDTO();
    	
    	usuario.setNmLogin(this.nmLogin); 
    	usuario.setQtTempoInatividade(this.qtTempoInatividade);
    	
    	if (setPassword) {
    		usuario.setDsSenha(this.dsSenha);
    	}
    	
    	return usuario;
    }	
}