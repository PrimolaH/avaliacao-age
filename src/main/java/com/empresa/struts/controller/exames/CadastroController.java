package com.empresa.struts.controller.exames;

import java.util.logging.Logger;

import com.empresa.struts.facade.ExameFacade;
import com.empresa.struts.models.Exame;
import com.opensymphony.xwork2.ActionSupport;

public class CadastroController extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CadastroController.class.getName());

	private int id;
	private Exame exame;
	private ExameFacade exameFacade;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Exame getExame() {
		return this.exame;
	}

	public CadastroController() {
		logger.info("CadastroAction");
		this.exameFacade = new ExameFacade();
		this.exame = new Exame();
	}

	public String cadastro() {
		logger.info("CadastroAction.form");

		logger.info("CadastroAction.this.id: " + this.id);

		if (this.id > 0) {
			exame = exameFacade.carregarExame(this.id);
			if (exame == null) {
				addActionError("Não existe exame com o id informado");
			}
		}

		return INPUT;
	}

	@Override
	public String execute() {
		try {
			logger.info("cadastrarExame");

			if (exame.getNmExame() == null || exame.getNmExame().trim().isEmpty()) {
				addFieldError("exame.nmExameError", "O nome do exame é obrigatório.");
				return ERROR;
			}

			if (this.exame.getCdExame() > 0) {

				if (exameFacade.alterarExame(exame)) {
					addActionMessage("Exame alterado com sucesso!");
					return SUCCESS;
				}
			} else {
				Exame exameResult = exameFacade.incluirExame(this.exame);

				if (exameResult != null && exameResult.getCdExame() > 0) {
					this.id = exameResult.getCdExame();
					this.exame.setCdExame(id);
					addActionMessage("Exame criado com sucesso!");
					return SUCCESS;
				}
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

	public String excluir() {
		logger.info("CadastroAction.excluir.id: " + id);

		try {
			if (id > 0) {
				boolean sucesso = exameFacade.excluirExame(id);

				if (sucesso) {
					addActionMessage("Exame excluído com sucesso.");
					return SUCCESS;
				} else {
					this.exame = exameFacade.carregarExame(id);
					addActionError("Não foi possível excluir o exame.");
					return ERROR;
				}
			}

		} catch (IllegalArgumentException e) {
			addActionError(e.getMessage());

			if (id > 0) {
				this.exame = this.exameFacade.carregarExame(id);
			}

			return ERROR;

		} catch (Exception e) {
			e.printStackTrace();
		}

		addActionError("Ocorreu um erro inexperado");
		return ERROR;
	}

}
