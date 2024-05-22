package com.empresa.struts.models.dto;

public class SituacaoExecucaoDTO {

	private boolean situacaoExecucao;
	private String message;
	public SituacaoExecucaoDTO() {
	}
	
	public SituacaoExecucaoDTO(boolean situacaoExecucao, String message) {
		this.situacaoExecucao = situacaoExecucao;
		this.message = message;
	}

	public boolean isSituacaoExecucao() {
		return situacaoExecucao;
	}
	public void setSituacaoExecucao(boolean situacaoExecucao) {
		this.situacaoExecucao = situacaoExecucao;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
