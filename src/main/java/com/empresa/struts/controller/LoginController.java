package com.empresa.struts.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.empresa.struts.facade.UsuarioFacade;
import com.empresa.struts.models.Usuario;
import com.opensymphony.xwork2.ActionSupport;

public class LoginController extends ActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginController.class.getName());
	
	private Usuario usuario;
    private UsuarioFacade usuarioFacade;
    
    public Usuario getUsuario() {
    	return usuario;
    }
    
	public LoginController() {
    	logger.info("LoginAction");
    	    	    	
        this.usuario = new Usuario();
        this.usuarioFacade = new UsuarioFacade();
    } 
	
	public String loginForm() {
		logger.info("loginForm.input");
		return INPUT;		
	}

	@Override
	public String execute() {
		try {		
			logger.info("execute.validacao");
			if (usuario.getNmLogin() == null || usuario.getNmLogin().trim().isEmpty()) {
				addActionError("Informe o login");				
				return ERROR;
	        }			
			
			logger.info("execute.autenticacao");
            Usuario usuarioAutenticado = usuarioFacade.autenticar(usuario);

            if (usuarioAutenticado != null) {
            	logger.info("execute.usuarioAutenticado");
            	
            	HttpSession session = ServletActionContext.getRequest().getSession(true);
            	session.setMaxInactiveInterval(usuario.getQtTempoInatividade() == 0 ? 3600 : usuario.getQtTempoInatividade());
                session.setAttribute("currentUser", usuario);
            	
                return SUCCESS;
            } else {

                addActionError("Usuário ou senha inválidos");
                return ERROR;
            }

        } catch (IllegalArgumentException e) {
        	addActionError(e.getMessage());
    		return ERROR;
    		
		} catch (Exception e) {
            e.printStackTrace();
            addActionError("Usuário ou senha inválidos");
            return ERROR;
        }
	}
	
	public String logout() {
	    HttpSession session = ServletActionContext.getRequest().getSession(false);
	    if (session != null) {
	        session.invalidate(); 
	    }
	    return SUCCESS;
	}

}