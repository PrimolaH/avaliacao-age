package com.empresa.struts.controller.exames_realizados;

import java.util.List;

import com.empresa.struts.facade.ExameRealizadoFacade;
import com.empresa.struts.models.ConsultaExamesRealizadosModel;
import com.empresa.struts.models.ExameRealizado;
import com.opensymphony.xwork2.ActionSupport;

public class ConsultaController extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private ExameRealizadoFacade exameRealizadoFacade;
    private List<ExameRealizado> listaExamesRealizados;    
    private ConsultaExamesRealizadosModel consultaExamesRealizadosModel;
    
    public ConsultaController() {
        exameRealizadoFacade = new ExameRealizadoFacade();
        consultaExamesRealizadosModel = new ConsultaExamesRealizadosModel();
        consultaExamesRealizadosModel.setTotalPaginas(exameRealizadoFacade.getTotalPaginas(consultaExamesRealizadosModel));
    }
    
    public ConsultaExamesRealizadosModel getConsultaExamesRealizadosModel() {
        return consultaExamesRealizadosModel;
    }
    
    public void setPagina(int pagina) {
        this.consultaExamesRealizadosModel.setPaginaAtual(pagina);
    }
    
    public void setExame(String exame) {
    	this.consultaExamesRealizadosModel.setNm_exame(exame);
    }
    
    public void setFuncionario(String functionario) {
    	this.consultaExamesRealizadosModel.setNm_funcionario(functionario);
    }    

    private void executarConsulta() {    	
        listaExamesRealizados = exameRealizadoFacade.getListaExamesRealizadosPaginada(consultaExamesRealizadosModel);
        consultaExamesRealizadosModel.setTotalPaginas(exameRealizadoFacade.getTotalPaginas(consultaExamesRealizadosModel));
    }

    public List<ExameRealizado> getListaExamesRealizados() {
        return listaExamesRealizados;
    }    

    @Override
    public String execute() {
        try {
            executarConsulta();
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return SUCCESS;
    }
}
