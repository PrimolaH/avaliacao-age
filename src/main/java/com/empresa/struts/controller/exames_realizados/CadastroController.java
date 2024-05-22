package com.empresa.struts.controller.exames_realizados;

import java.util.List;
import java.util.logging.Logger;

import com.empresa.struts.facade.ExameFacade;
import com.empresa.struts.facade.ExameRealizadoFacade;
import com.empresa.struts.facade.FuncionarioFacade;
import com.empresa.struts.models.Exame;
import com.empresa.struts.models.ExameRealizado;
import com.empresa.struts.models.Funcionario;
import com.opensymphony.xwork2.ActionSupport;

public class CadastroController extends ActionSupport {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private static final Logger logger = Logger.getLogger(CadastroController.class.getName());
	
	private ExameFacade exameFacade;
    private FuncionarioFacade funcionarioFacade;
    private ExameRealizadoFacade exameRealizadoFacade;
	
	private ExameRealizado exameRealizado;
	private List<Funcionario> listaFuncionarios;
    private List<Exame> listaExames;
    
     
    public ExameRealizado getExameRealizado() {
    	return exameRealizado;
    }
    
    public List<Exame> getListaExames() {
    	return this.listaExames;
    }
    
    public List<Funcionario> getListaFuncionarios() {
    	return this.listaFuncionarios;
    }    

    public CadastroController() {
    	logger.info("CadastroAction");
    	
    	this.exameRealizado = new ExameRealizado();
    	    	        
        this.exameFacade = new ExameFacade();
        this.funcionarioFacade = new FuncionarioFacade();
        
        this.listaExames = this.exameFacade.getListaExamesAtivosOrdenada();
        this.listaFuncionarios = this.funcionarioFacade.getListaFuncionariosOrdenada();
        
        this.exameRealizadoFacade = new ExameRealizadoFacade();
    }
    
    public String cadastro() {
		logger.info("CadastroAction.form");		
		
		return INPUT;		
	}
    
    @Override
    public String execute() {
    	try {
			logger.info("cadastrarExame");
			
			if (this.exameRealizadoFacade.incluirExameRealizado(this.exameRealizado)) {
				return SUCCESS;
			}			
    	} catch (IllegalArgumentException e) {
    		addActionError(e.getMessage());
    		return ERROR;
    		
		} catch (Exception e) {
			logger.info("cadastrarExame.catch");
			e.printStackTrace();
		}

    	
    	addActionError("Ocorreu um erro inesperado");
		return ERROR;
    }
}
