package com.empresa.struts.models.dto;

public class ConsultaExamesModelDTO extends PaginacaoModelDTO {
    private Integer cd_exame = null;
    private String nm_exame;
    private String ic_ativo = null;

    public Integer getCd_exame() {
        return cd_exame;
    }

    public void setCd_exame(Integer cd_exame) {
        this.cd_exame = cd_exame;
    }

    public String getNm_exame() {
        return nm_exame;
    }

    public void setNm_exame(String nm_exame) {
        this.nm_exame = nm_exame;
    }

    public String getIc_ativo() {
        return ic_ativo;
    }

    public Boolean getIc_ativoValue() {
        if (ic_ativo == null || ic_ativo.isEmpty()) {
            return null;
        }

        return ("sim".equals(ic_ativo.toLowerCase()));
    }

    public void setIc_ativo(String ic_ativo) {
        this.ic_ativo = ic_ativo;
    }
}