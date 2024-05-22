package com.empresa.struts.controller.usuarios;

import java.util.List;

import com.empresa.struts.facade.UsuarioFacade;
import com.empresa.struts.models.ConsultaUsuariosModel;
import com.empresa.struts.models.Usuario;
import com.opensymphony.xwork2.ActionSupport;

public class ConsultaController extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private UsuarioFacade usuarioFacade;
    private List<Usuario> listaUsuarios;    
    private ConsultaUsuariosModel consultaUsuariosModel;
    
    public ConsultaController() {
        usuarioFacade = new UsuarioFacade();
        consultaUsuariosModel = new ConsultaUsuariosModel();
        consultaUsuariosModel.setTotalPaginas(usuarioFacade.getTotalPaginas(consultaUsuariosModel));
    }
    
    public ConsultaUsuariosModel getConsultaUsuariosModel() {
        return consultaUsuariosModel;
    }
    
    public void setPagina(int pagina) {
        this.consultaUsuariosModel.setPaginaAtual(pagina);
    }
    
    public void setLogin(String login) {
    	this.consultaUsuariosModel.setNmLogin(login);
    }
    
    public void setRole(String role) {
    	this.consultaUsuariosModel.setNmRole(role);
    }

    private void executarConsulta() {    	
        listaUsuarios = usuarioFacade.listarUsuarios(consultaUsuariosModel);
        consultaUsuariosModel.setTotalPaginas(usuarioFacade.getTotalPaginas(consultaUsuariosModel));
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
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
