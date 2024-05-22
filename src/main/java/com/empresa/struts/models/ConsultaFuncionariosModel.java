package com.empresa.struts.models;

public class ConsultaFuncionariosModel extends PaginacaoModel {
    private Integer cd_funcionario = null;
    private String nm_funcionario;

    public Integer getCd_funcionario() {
        return cd_funcionario;
    }

    public void setCd_funcionario(Integer cd_funcionario) {
        this.cd_funcionario = cd_funcionario;
    }

    public String getNm_funcionario() {
        return nm_funcionario;
    }

    public void setNm_funcionario(String nm_funcionario) {
        this.nm_funcionario = nm_funcionario;
    }
}