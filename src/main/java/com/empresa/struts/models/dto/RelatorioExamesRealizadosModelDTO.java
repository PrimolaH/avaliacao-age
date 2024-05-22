package com.empresa.struts.models.dto;

import java.util.Date;

public class RelatorioExamesRealizadosModelDTO extends PaginacaoModelDTO {
    
    private Date dtInicio;
    private Date dtFim;
    
	public Date getDtInicio() {
		return dtInicio;
	}
	
	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}
	
	public Date getDtFim() {
		return dtFim;
	}
	
	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}
            
}
