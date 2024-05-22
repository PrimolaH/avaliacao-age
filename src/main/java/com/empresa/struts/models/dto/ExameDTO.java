package com.empresa.struts.models.dto;

public class ExameDTO {
    private int cdExame;
    private String nmExame;
    private boolean icAtivo;
    private String dsDetalheExame;
    private String dsDetalheExame1;

    public int getCdExame() {
        return cdExame;
    }

    public void setCdExame(int cdExame) {
        this.cdExame = cdExame;
    }

    public String getNmExame() {
        return nmExame;
    }

    public void setNmExame(String nmExame) {
        this.nmExame = nmExame;
    }

    public boolean isIcAtivo() {
        return icAtivo;
    }

    public void setIcAtivo(boolean icAtivo) {
        this.icAtivo = icAtivo;
    }

    public String getDsDetalheExame() {
        return dsDetalheExame;
    }

    public void setDsDetalheExame(String dsDetalheExame) {
        this.dsDetalheExame = dsDetalheExame;
    }

    public String getDsDetalheExame1() {
        return dsDetalheExame1;
    }

    public void setDsDetalheExame1(String dsDetalheExame1) {
        this.dsDetalheExame1 = dsDetalheExame1;
    }
}
