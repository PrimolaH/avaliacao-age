package com.empresa.struts.controller;

import java.util.logging.Logger;

import com.empresa.struts.facade.UsuarioFacade;
import com.empresa.struts.models.CadastroUsuarioModel;
import com.opensymphony.xwork2.ActionSupport;

public class UsuarioAdminController extends ActionSupport {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private static final Logger logger = Logger.getLogger(UsuarioAdminController.class.getName());
	
	private CadastroUsuarioModel usuario;	
    private UsuarioFacade usuarioFacade;
    private String errorMessage;    
    private boolean registerAdmin; 

    public boolean isRegisterAdmin() {
        return registerAdmin;
    }    
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public CadastroUsuarioModel getUsuario() {
    	return usuario;
    }

    public UsuarioAdminController() {
    	logger.info("UsuarioAdminAction");
    	
    	registerAdmin = true;
    	errorMessage = null;
    	
        this.usuario = new CadastroUsuarioModel();
        this.usuarioFacade = new UsuarioFacade();
    } 
    
    @Override
    public String execute() {
    	try {
			logger.info("cadastrarUsuarioAdmin");
					
			if (usuario.getNmLogin() == null || usuario.getNmLogin().trim().isEmpty()) {
				errorMessage = "Informe o login";
				return ERROR;
	        }
					
			usuarioFacade.cadastrarUsuario(usuario);
			
			return SUCCESS;
    	} catch (IllegalArgumentException e) {
    		errorMessage = e.getMessage();
    		return ERROR;
    		
		} catch (Exception e) {
			logger.info("cadastrarUsuario.catch");
			e.printStackTrace();
		}

    	errorMessage = "Ocorreu um erro ao cadastrar o usuário.";
		return ERROR;
    }
}
