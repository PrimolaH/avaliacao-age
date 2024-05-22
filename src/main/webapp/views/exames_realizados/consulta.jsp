<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Exames Realizados</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <script>
        function consulta(pagina) {
            var queryParams = "";

            var funcionario = document.getElementById("funcionario").value;
            var exame = document.getElementById("exame").value;

            if (funcionario) {
                queryParams += "&funcionario=" + funcionario.trim();
            }
            
            if (exame) {
                queryParams += "&exame=" + exame.trim();
            }

            window.location.href = "consulta?pagina=" + pagina + queryParams;
        }

        function limparCampos() {
            document.getElementById('funcionario').value = '';
            document.getElementById('exame').value = '';

            window.location.href = "consulta?";
        }

        function novoExame() {
            window.location.href = "cadastro"; // Redireciona para a tela de cadastro
        }
        
        function relatorio() {
            window.location.href = "relatorio"; // Redireciona para a tela de cadastro
        }
    </script>
    
    <%@include file="/layout/navbar.jsp" %>

    <div class="container" style="padding: 54px; box-shadow: 5px 0px 50px 10px #00000029; margin-top: 70px;border-radius: 20px; background: #FFF">
        <h2>Consulta de Exames Realizados</h2>

        <form class="form-inline mb-3 mt-3">
            <div class="form-group mr-3">
                <label for="codigo" class="mr-2">Exame:</label>
                <s:textfield name="exame" id="exame" class="form-control" value="%{consultaExamesRealizadosModel.nm_exame}" />
            </div>
            <div class="form-group mr-3">
                <label for="nome" class="mr-2">Funcionário:</label>
                <s:textfield name="funcionario" id="funcionario" class="form-control" value="%{consultaExamesRealizadosModel.nm_funcionario}" />
            </div>

            <button type="button" class="btn btn-outline-primary mr-3" onclick="consulta(1)">Pesquisar</button>
            <button type="button" class="btn btn-outline-secondary mr-3" onclick="limparCampos()">Limpar</button>
            <button type="button" class="btn btn-outline-success mr-3" onclick="novoExame()">Novo Exame</button>
            <button type="button" class="btn btn-outline-info" onclick="relatorio()">Relatório</button>
        </form>

        <table class="table">
            <thead>
                <tr>
                	<th>Id Funcionário</th>
                    <th>Funcionário</th>
                    <th>Id Exame</th>
                    <th>Nome do Exame</th>
                    <th>Data</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="listaExamesRealizados">
                    <tr>
                    	<td><s:property value="cd_funcionario" /></td>
		                <td><s:property value="nm_funcionario" /></td>
		                <td><s:property value="cd_exame" /></td>
		                <td><s:property value="nm_exame" /></td>
		                <td><s:date name="dt_realizacao" format="dd/MM/yyyy" /></td>
		            </tr>
                </s:iterator>
            </tbody>
        </table>
        <s:if test="consultaExamesRealizadosModel.totalPaginas > 1">
	        <nav aria-label="Page navigation" class="d-flex justify-content-center">
	            <ul class="pagination">
	                <li class="page-item">
	                    <s:if test="consultaExamesRealizadosModel.paginaAtual > 1">
	                        <button class="page-link" onclick="consulta(1)" aria-label="Previous">
	                            <span aria-hidden="true">«« Primeira</span>
	                        </button>
	                    </s:if>
	                </li>
	
	                <li class="page-item">
	                    <s:if test="consultaExamesRealizadosModel.paginaAtual > 1">
	                        <button class="page-link" onclick="consulta(${consultaExamesRealizadosModel.paginaAtual - 1})" aria-label="Previous">
	                            <span aria-hidden="true">&laquo; Anterior</span>
	                        </button>
	                    </s:if>
	                </li>
	
	                <s:iterator begin="consultaExamesRealizadosModel.primeiraPagina" end="consultaExamesRealizadosModel.ultimaPagina" var="i">
	                    <li class="page-item">
	                        <s:if test="consultaExamesRealizadosModel.paginaAtual eq i">
	                            <span class="page-link active">${i}</span>
	                        </s:if>
	                        <s:else>
	                            <button class="page-link" onclick="consulta(${i})" aria-label="Previous">
	                                ${i}
	                            </button>
	                        </s:else>
	                    </li>
	                </s:iterator>
	
	                <li class="page-item">
	                    <s:if test="consultaExamesRealizadosModel.paginaAtual < consultaExamesRealizadosModel.totalPaginas">
	                        <button class="page-link" onclick="consulta(${consultaExamesRealizadosModel.paginaAtual + 1})" aria-label="Previous">
	                            <span aria-hidden="true">Próxima &raquo;</span>
	                        </button>
	                    </s:if>
	                </li>
	
	                <li class="page-item">
	                    <s:if test="consultaExamesRealizadosModel.paginaAtual < consultaExamesRealizadosModel.totalPaginas">
	                        <button class="page-link" onclick="consulta(${consultaExamesRealizadosModel.totalPaginas})" aria-label="Previous">
	                            <span aria-hidden="true">Última »»</span>
	                        </button>
	                    </s:if>
	                </li>
	            </ul>
	        </nav>
		</s:if>
    </div>
    <%@include file="/layout/footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>