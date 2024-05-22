package com.empresa.struts.facade;

import com.empresa.struts.dao.FuncionarioDAO;
import com.empresa.struts.models.ConsultaFuncionariosModel;
import com.empresa.struts.models.Funcionario;

import java.util.List;

public class FuncionarioFacade {
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioFacade() {
        funcionarioDAO = new FuncionarioDAO();
    }

    public List<Funcionario> getListaFuncionariosPaginada(ConsultaFuncionariosModel consultaFuncionariosModel) {
        return funcionarioDAO.listarFuncionarios(
                consultaFuncionariosModel.getPaginaAtual(),
                consultaFuncionariosModel.getRegistrosPorPagina(),
                consultaFuncionariosModel.getNm_funcionario(),
                consultaFuncionariosModel.getCd_funcionario());
    }
    
    public List<Funcionario> getListaFuncionariosOrdenada() {
        return funcionarioDAO.listarFuncionarios();
    }

    public int getTotalPaginas(ConsultaFuncionariosModel consultaFuncionariosModel) {
        int totalRegistros = funcionarioDAO.contarFuncionarios(
                consultaFuncionariosModel.getNm_funcionario(),
                consultaFuncionariosModel.getCd_funcionario());

        return (int) Math.ceil((double) totalRegistros / consultaFuncionariosModel.getRegistrosPorPagina());
    }

    public Funcionario incluirFuncionario(Funcionario funcionario) {
        int id = funcionarioDAO.incluirFuncionario(funcionario);

        if (id > 0) {
            return carregarFuncionario(id);
        }

        return null;
    }

    public boolean alterarFuncionario(Funcionario funcionario) {
        return funcionarioDAO.alterarFuncionario(funcionario);
    }

    public Funcionario carregarFuncionario(int id) {
        return funcionarioDAO.getFuncionarioById(id);
    }

    public boolean excluirFuncionario(int id) {
        try {
            return funcionarioDAO.excluirFuncionario(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
