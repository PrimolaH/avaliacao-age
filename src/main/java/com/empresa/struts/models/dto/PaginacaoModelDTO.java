package com.empresa.struts.models.dto;

public class PaginacaoModelDTO {	
	private int paginaAtual = 1;
    private int totalPaginas;
    private int registrosPorPagina = 10;
    private int primeiraPagina;

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        if (paginaAtual < 1) {
            this.paginaAtual = 1;
            return;
        }

        this.paginaAtual = paginaAtual;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public int getPrimeiraPagina() {
        return primeiraPagina = Math.max(1, this.paginaAtual - 2);
    }

    public int getUltimaPagina() {
        return Math.min(this.totalPaginas, this.primeiraPagina + 4);
    }

    public int getRegistrosPorPagina() {
        return registrosPorPagina;
    }

    public void setRegistrosPorPagina(int registrosPorPagina) {
        this.registrosPorPagina = registrosPorPagina;
    }
}