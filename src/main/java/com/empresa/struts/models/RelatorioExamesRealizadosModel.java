package com.empresa.struts.models;

import java.util.Date;

public class RelatorioExamesRealizadosModel extends PaginacaoModel {
    
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
