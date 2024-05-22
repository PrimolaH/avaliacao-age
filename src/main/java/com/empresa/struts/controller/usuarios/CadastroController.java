package com.empresa.struts.controller.usuarios;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.empresa.struts.facade.UsuarioFacade;
import com.empresa.struts.models.CadastroUsuarioModel;
import com.empresa.struts.models.Usuario;
import com.empresa.struts.models.dto.SituacaoExecucaoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class CadastroController extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CadastroController.class.getName());

	private CadastroUsuarioModel cadastroUsuarioModel;
	private UsuarioFacade usuarioFacade;
	private String login;

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public CadastroUsuarioModel getCadastroUsuarioModel() {
		return cadastroUsuarioModel;
	}

	public void setCadastroUsuarioModel(CadastroUsuarioModel cadastroUsuarioModel) {
		this.cadastroUsuarioModel = cadastroUsuarioModel;
	}

	public CadastroController() {
		this.usuarioFacade = new UsuarioFacade();
		this.cadastroUsuarioModel = new CadastroUsuarioModel();
	}

	public String cadastro() {
		logger.info("CadastroAction.form");

		logger.info("CadastroAction.this.id: " + this.login);

		if (this.login != null) {
			Usuario usuario = usuarioFacade.carregarUsuario(this.login);

			this.cadastroUsuarioModel = new CadastroUsuarioModel();
			this.cadastroUsuarioModel.setLoaded(true);
			this.cadastroUsuarioModel.setNmLogin(usuario.getNmLogin());
			this.cadastroUsuarioModel.setQtTempoInatividade(usuario.getQtTempoInatividade());
		}

		return INPUT;
	}

	@Override
	public String execute() {
		logger.info("CadastroUsuarioAction.execute");

		try {

			logger.info("CadastroUsuarioAction.cadastroUsuarioModel.isLoaded: " + this.cadastroUsuarioModel.isLoaded());
			logger.info(
					"CadastroUsuarioAction.cadastroUsuarioModel.getNmLogin: " + this.cadastroUsuarioModel.getNmLogin());

			HttpSession session = ServletActionContext.getRequest().getSession(true);
			Usuario currentUser = (Usuario) session.getAttribute("currentUser");
			if (currentUser.getNmLogin().equals(login)) {
				logger.info("CadastroUsuarioAction.is User exists");
				addActionError("Informe um Login diferente");
				return ERROR;
			}

			if (this.cadastroUsuarioModel.isLoaded() == true) {
				logger.info("CadastroUsuarioAction.isLoaded");
				if (usuarioFacade.alterarUsuario(cadastroUsuarioModel)) {
					addActionMessage("Usuário alterado com sucesso!");
					return SUCCESS;
				}
			} else {
				logger.info("CadastroUsuarioAction.is Not Loaded");
				if (cadastroUsuarioModel.getNmLogin() == null || cadastroUsuarioModel.getNmLogin().trim().isEmpty()) {
					addActionError("Informe o login");
					return ERROR;
				}

				if (cadastroUsuarioModel.getNmLogin().trim().contains(" ")) {
					addActionError("Login inválido. Não utilize espaços em branco.");
					return ERROR;
				}

				if (cadastroUsuarioModel.getDsSenha() == null || cadastroUsuarioModel.getDsSenha().trim().isEmpty()) {
					addActionError("Informe a senha");
					return ERROR;
				}

				SituacaoExecucaoDTO execucaoDTO = usuarioFacade.cadastrarUsuario(cadastroUsuarioModel);
				if (execucaoDTO.isSituacaoExecucao()) {
					addActionMessage("Usuário criado com sucesso!");
					return SUCCESS;
				} else {
					addActionError(execucaoDTO.getMessage());
					return ERROR;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		addActionError("Ocorreu um erro inesperado");
		return ERROR;
	}

	public String excluir() {
		logger.info("CadastroUsuarioAction.excluir.login: " + login);

		HttpSession session = ServletActionContext.getRequest().getSession(true);
		Usuario currentUser = (Usuario) session.getAttribute("currentUser");
		try {
			if (login != null && !currentUser.getNmLogin().equals(login)) {
				boolean sucesso = usuarioFacade.excluirUsuario(login);

				if (sucesso) {
					addActionMessage("Usuário excluído com sucesso.");
					return SUCCESS;
				} else {
					addActionError("Não foi possível excluir o usuário.");
					return ERROR;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		addActionError("Ocorreu um erro inesperado");
		return ERROR;
	}
}