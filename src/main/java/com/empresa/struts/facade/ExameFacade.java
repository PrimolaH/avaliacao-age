package com.empresa.struts.facade;

import java.util.List;

import com.empresa.struts.dao.ExameDAO;
import com.empresa.struts.models.ConsultaExamesModel;
import com.empresa.struts.models.Exame;

public class ExameFacade {
	
    private ExameDAO exameDAO;
    
    public ExameFacade() {
		this.exameDAO = new ExameDAO();
	}

	public List<Exame> getListaExamesPaginada(ConsultaExamesModel consultaExamesModel) {
        return exameDAO.listarExames(
        		consultaExamesModel.getPaginaAtual(), 
        		consultaExamesModel.getRegistrosPorPagina(), 
        		consultaExamesModel.getNm_exame() , 
        		consultaExamesModel.getCd_exame(), 
        		consultaExamesModel.getIc_ativoValue());
    }
    
    public List<Exame> getListaExamesAtivosOrdenada() {
        return exameDAO.listarExamesAtivos();
    }

    public int getTotalPaginas(ConsultaExamesModel consultaExamesModel) {
        int totalRegistros = exameDAO.contarExames(
        		consultaExamesModel.getNm_exame(), 
        		consultaExamesModel.getCd_exame(), 
        		consultaExamesModel.getIc_ativoValue());

        return (int) Math.ceil((double) totalRegistros / consultaExamesModel.getRegistrosPorPagina());
    }
    
    public Exame incluirExame(Exame exame) {
    	int id = exameDAO.incluirExame(exame);
    	
    	if (id > 0) {
    		return carregarExame(id);
    	}
    	
    	return null;
    }
    
    public boolean alterarExame(Exame exame) {
    	return exameDAO.alterarExame(exame);    	
    }

    public Exame carregarExame(int id) {
        return exameDAO.getExameById(id);
    }
    
    public boolean excluirExame(int id) {
    	return exameDAO.excluirExame(id);
    }


}