package com.empresa.struts.models.dto;

import java.util.Date;

public class ExameRealizadoDTO {

    private int cd_funcionario;
    private int cd_exame;
    private Date dt_realizacao;
    private String nm_exame;
    private String nm_funcionario;

    // Getters e Setters

    public int getCd_funcionario() {
        return cd_funcionario;
    }

    public void setCd_funcionario(int cd_funcionario) {
        this.cd_funcionario = cd_funcionario;
    }

    public int getCd_exame() {
        return cd_exame;
    }

    public void setCd_exame(int cd_exame) {
        this.cd_exame = cd_exame;
    }

    public Date getDt_realizacao() {
        return dt_realizacao;
    }

    public void setDt_realizacao(Date dt_realizacao) {
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
