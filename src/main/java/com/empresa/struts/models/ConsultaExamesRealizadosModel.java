package com.empresa.struts.models;

public class ConsultaExamesRealizadosModel extends PaginacaoModel {
    private Integer cd_funcionario = null;
    private Integer cd_exame = null;
    private String dt_realizacao;
    private String nm_exame;
    private String nm_funcionario;

    public Integer getCd_funcionario() {
        return cd_funcionario;
    }

    public void setCd_funcionario(Integer cd_funcionario) {
        this.cd_funcionario = cd_funcionario;
    }

    public Integer getCd_exame() {
        return cd_exame;
    }

    public void setCd_exame(Integer cd_exame) {
        this.cd_exame = cd_exame;
    }

    public String getDt_realizacao() {
        return dt_realizacao;
    }

    public void setDt_realizacao(String dt_realizacao) {
        this.dt_realizacao = dt_realizacao;
    }

    public String getNm_exame() {
        return nm_exame;
    }

    public void setNm_exame(String nm_exame) {
        this.nm_exame = nm_exame;
    }

    public String getNm_funcionario() {
        return nm_funcionario;
    }

    public void setNm_funcionario(String nm_funcionario) {
        this.nm_funcionario = nm_funcionario;
    }
}