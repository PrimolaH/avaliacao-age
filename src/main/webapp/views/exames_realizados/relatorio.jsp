<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Relatório de Exames Realizados</title>
<link rel="stylesheet" href="/avaliacao/static/css/custom-styles.css">
<link rel="stylesheet"
	href="/avaliacao/static/js/jquery-ui-1.12.1.custom/jquery-ui.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="/avaliacao/static/js/jquery-ui-1.12.1.custom/external/jquery/jquery.js"></script>
<script src="/avaliacao/static/js/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script src="/avaliacao/static/js/table2excel.js" type="text/javascript"></script>
</head>
<body>
	<script>
        function consulta(pagina) {
            var queryParams = "";

            var dtInicio = document.getElementById("dtInicio").value;
            var dtFim = document.getElementById("dtFim").value;
            var registrosPorPagina = document.getElementById("registrosPorPagina").value;

            if (dtInicio) {
                queryParams += "&dtInicio=" + dtInicio;
            }
            
            if (dtFim) {
                queryParams += "&dtFim=" + dtFim.trim();
            }
            
            if (registrosPorPagina) {
                queryParams += "&qtdPagina=" + registrosPorPagina.trim();
            }
            
            if (!dtInicio || !dtFim) {
            	return;
            }

            window.location.href = "relatorio?pagina=" + pagina + queryParams;
        }

        function limparCampos() {
            document.getElementById('dtInicio').value = '';
            document.getElementById('dtFim').value = '';

            window.location.href = "relatorio?";
        }
        function downloadRelatorio(){
        	$("#tblCustomers").table2excel({
                filename: "relatorioExame.xls"
            });
        }
        
        $(function() {
            $("#dtInicio").datepicker({ dateFormat: 'dd/mm/yy' });
            $("#dtFim").datepicker({ dateFormat: 'dd/mm/yy' });
        	
        });
    </script>
	<%@include file="/layout/navbar.jsp"%>
	<div class="container"
		style="padding: 70px; box-shadow: 5px 0px 50px 10px #00000029; margin-top: 70px; border-radius: 20px; background: #FFF">
		<div
			style="display: flex; justify-content: space-between; margin-right: 10px">
			<h2>Relatório de Exames Realizados</h2>
			<button id="downloadRelatorio" type="button"
				class="btn btn-outline-success" onclick="downloadRelatorio()">Download</button>
		</div>

		<form class="form-inline mb-3 mt-3"
			style="display: flex; justify-content: space-between;">
			<div class="form-group mr-3">
				<label for="dtInicio" class="mr-2">Início:</label>
				<s:textfield name="relatorioExamesRealizadosModel.dtInicio"
					id="dtInicio" class="form-control" />
			</div>

			<div class="form-group mr-3">
				<label for="dtFim" class="mr-2">Fim:</label>
				<s:textfield name="relatorioExamesRealizadosModel.dtFim" id="dtFim"
					class="form-control" />
			</div>

			<div class="form-group mr-3">
				<label for="registrosPorPagina" class="mr-2">Qtd por Página:</label>
				<s:select class="form-control" id="registrosPorPagina"
					name="relatorioExamesRealizadosModel.registrosPorPagina"
					list="#{'5':'5', '10':'10', '25':'25', '50':'50'}"
					value="relatorioExamesRealizadosModel.registrosPorPagina" />
			</div>

			<button id="consultar" type="button"
				class="btn btn-outline-primary mr-3" onclick="consulta(1)">Pesquisar</button>
			<button id="limpar" type="button"
				class="btn btn-outline-secondary mr-3" onclick="limparCampos()">Limpar</button>
		</form>
		<s:if test="relatorioExamesRealizadosModel.totalPaginas > 0">
			<table id="tblCustomers" cellspacing="0" cellpadding="0"
				class="table">
				<thead>
					<tr>
						<th>Id Funcionário</th>
						<th>Funcionário</th>
						<th>Id Exame</th>
						<th>Nome do Exame</th>
						<th>Detalhe Exame</th>
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
							<td><s:property value="nm_exame" /></td>
							<td><s:date name="dt_realizacao" format="dd/MM/yyyy" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<s:if test="relatorioExamesRealizadosModel.totalPaginas > 1">
				<nav aria-label="Page navigation"
					class="d-flex justify-content-center">
					<ul class="pagination">
						<li class="page-item"><s:if
								test="relatorioExamesRealizadosModel.paginaAtual > 1">
								<button class="page-link" onclick="consulta(1)"
									aria-label="Previous">
									<span aria-hidden="true">«« Primeira</span>
								</button>
							</s:if></li>

						<li class="page-item"><s:if
								test="relatorioExamesRealizadosModel.paginaAtual > 1">
								<button class="page-link"
									onclick="consulta(${relatorioExamesRealizadosModel.paginaAtual - 1})"
									aria-label="Previous">
									<span aria-hidden="true">&laquo; Anterior</span>
								</button>
							</s:if></li>

						<s:iterator begin="relatorioExamesRealizadosModel.primeiraPagina"
							end="relatorioExamesRealizadosModel.ultimaPagina" var="i">
							<li class="page-item"><s:if
									test="relatorioExamesRealizadosModel.paginaAtual eq i">
									<span class="page-link active">${i}</span>
								</s:if> <s:else>
									<button class="page-link" onclick="consulta(${i})"
										aria-label="Previous">${i}</button>
								</s:else></li>
						</s:iterator>

						<li class="page-item"><s:if
								test="relatorioExamesRealizadosModel.paginaAtual < relatorioExamesRealizadosModel.totalPaginas">
								<button class="page-link"
									onclick="consulta(${relatorioExamesRealizadosModel.paginaAtual + 1})"
									aria-label="Previous">
									<span aria-hidden="true">Próxima &raquo;</span>
								</button>
							</s:if></li>

						<li class="page-item"><s:if
								test="relatorioExamesRealizadosModel.paginaAtual < relatorioExamesRealizadosModel.totalPaginas">
								<button class="page-link"
									onclick="consulta(${relatorioExamesRealizadosModel.totalPaginas})"
									aria-label="Previous">
									<span aria-hidden="true">Última »»</span>
								</button>
							</s:if></li>
					</ul>
				</nav>
			</s:if>
		</s:if>
	</div>
	<%@include file="/layout/footer.jsp"%>
</body>
</html>