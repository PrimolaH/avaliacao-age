package com.empresa.struts.controller.funcionarios;

import com.opensymphony.xwork2.ActionSupport;

import java.util.logging.Logger;

import com.empresa.struts.facade.FuncionarioFacade;
import com.empresa.struts.models.Funcionario;

public class CadastroController extends ActionSupport {
    private static final long serialVersionUID = 1L;   
    private static final Logger logger = Logger.getLogger(CadastroController.class.getName());
    
    private int id;
    private Funcionario funcionario;
    private FuncionarioFacade funcionarioFacade;
    
    public int getId() {
        return this.id;
    }  
    
    public void setId(int id) {
        this.id = id;
    }   
        
    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public CadastroController() {
        logger.info("CadastroAction");
        this.funcionario = new Funcionario();
        this.funcionarioFacade = new FuncionarioFacade();
    }
    
    public String cadastro() {
        logger.info("CadastroAction.form");
        
        logger.info("CadastroAction.this.id: " + this.id );
        
        if (this.id > 0) {
            funcionario = funcionarioFacade.carregarFuncionario(this.id);
            if (funcionario != null) {
                return INPUT;
            }
        }
        
        return INPUT;        
    }
    
    @Override
    public String execute() {
        try {
            logger.info("cadastrarFuncionario");
            
            if (funcionario.getNmFuncionario() == null || funcionario.getNmFuncionario().trim().isEmpty()) {
                addFieldError("funcionario.nmFuncionarioError", "O nome do funcionario e obrigatorio.");
                return ERROR;
            }
            
            if (this.funcionario.getCdFuncionario() > 0) {
                
                if (funcionarioFacade.alterarFuncionario(funcionario)) {
                    addActionMessage("Funcionario alterado com sucesso!");
                    return SUCCESS;
                }
            } else {            
                Funcionario funcionarioResult = funcionarioFacade.incluirFuncionario(this.funcionario);
                
                if (funcionarioResult != null && funcionarioResult.getCdFuncionario() > 0) {
                    this.id = funcionarioResult.getCdFuncionario();
                    this.funcionario.setCdFuncionario(id);
                    addActionMessage("Funcionario criado com sucesso!");
                    return SUCCESS;
                }
            }
            
        } catch (IllegalArgumentException e) {
            addActionError(e.getMessage());
            return ERROR;
            
        } catch (Exception e) {
            logger.info("cadastrarFuncionario.catch");
            e.printStackTrace();
        }

        
        addActionError("Ocorreu um erro inesperado");
        return ERROR;
    }
    
    public String excluir() {
        logger.info("CadastroAction.excluir.id: " + id);
        if (id > 0) {
            boolean sucesso = funcionarioFacade.excluirFuncionario(id);

            if (sucesso) {
                addActionMessage("Funcionario excluido com sucesso.");
            } else {
                addActionError("Nao foi possível excluir o funcionario.");
                return ERROR;
            }
        }

        return SUCCESS;
    }

}
