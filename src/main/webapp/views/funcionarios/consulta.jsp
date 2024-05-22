<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Funcionários</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <script>
        function consulta(pagina) {
            var queryParams = "";

            var codigo = document.getElementById("codigo").value;
            var nome = document.getElementById("nome").value;            

            if (codigo && !isNaN(codigo)) {
                queryParams += "&codigo=" + codigo;
            }
            if (nome) {
                queryParams += "&nome=" + nome.trim();
            }            

            window.location.href = "consulta?pagina=" + pagina + queryParams;
        }

        function limparCampos() {
            document.getElementById('codigo').value = '';
            document.getElementById('nome').value = '';

            window.location.href = "consulta?"
        }

        function novoFuncionario() {
            window.location.href = "cadastro"; // Redireciona para a tela de cadastro
        }

        function editarFuncionario(id) {
            window.location.href = "cadastro?id=" + id; // Redireciona para a tela de edição
        }
    </script>
	<%@include file="/layout/navbar.jsp" %>
    <div class="container" style="padding: 63px; box-shadow: 5px 0px 50px 10px #00000029; margin-top: 70px;border-radius: 20px; background: #FFF">
        <h2>Consulta de Funcionários</h2>

        <form class="form-inline mb-3 mt-3">
            <div class="form-group mr-3">
                <label for="codigo" class="mr-2">Código:</label>
                <s:textfield name="codigo" id="codigo" class="form-control" value="%{consultaFuncionariosModel.cd_funcionario}" />
            </div>
            <div class="form-group mr-3">
                <label for="nome" class="mr-2">Nome:</label>
                <s:textfield name="nome" id="nome" class="form-control" value="%{consultaFuncionariosModel.nm_funcionario}" />
            </div>

            <button type="button" class="btn btn-outline-primary mr-3" onclick="consulta(1)">Pesquisar</button>
            <button type="button" class="btn btn-outline-secondary mr-3" onclick="limparCampos()">Limpar</button>
            <button type="button" class="btn btn-outline-success" onclick="novoFuncionario()">Novo Funcionário</button>
        </form>

        <table class="table">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome do Funcionário</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="listaFuncionarios">
                    <tr>
                        <td><s:property value="cdFuncionario"/></td>
                        <td><s:property value="nmFuncionario"/></td>
                        <td>
                            <button type="button" class="btn btn-info btn-sm" onclick="editarFuncionario('<s:property value="cdFuncionario"/>')">Editar</button>
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
        <s:if test="consultaFuncionariosModel.totalPaginas > 1">
	        <nav aria-label="Page navigation" class="d-flex justify-content-center">
	            <ul class="pagination">
	                <li class="page-item">
	                    <s:if test="consultaFuncionariosModel.paginaAtual > 1">
	                        <button class="page-link" onclick="consulta(1)" aria-label="Previous">
	                            <span aria-hidden="true">«« Primeira</span>
	                        </button>
	                    </s:if>
	                </li>
	
	                <li class="page-item">
	                    <s:if test="consultaFuncionariosModel.paginaAtual > 1">
	                        <button class="page-link" onclick="consulta(${consultaFuncionariosModel.paginaAtual - 1})" aria-label="Previous">
	                            <span aria-hidden="true">&laquo; Anterior</span>
	                        </button>
	                    </s:if>
	                </li>      
	
	
	                <s:iterator begin="consultaFuncionariosModel.primeiraPagina" end="consultaFuncionariosModel.ultimaPagina" var="i">                    
	                    <li class="page-item">
	                        <s:if test="consultaFuncionariosModel.paginaAtual eq i">
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
	                    <s:if test="consultaFuncionariosModel.paginaAtual < consultaFuncionariosModel.totalPaginas">
	                        <button class="page-link" onclick="consulta(${consultaFuncionariosModel.paginaAtual + 1})" aria-label="Previous">
	                            <span aria-hidden="true">Próxima &raquo;</span>
	                        </button>
	                    </s:if>
	                </li>
	
	                <li class="page-item">
	                    <s:if test="consultaFuncionariosModel.paginaAtual < consultaFuncionariosModel.totalPaginas">                        
	                        <button class="page-link" onclick="consulta(${consultaFuncionariosModel.totalPaginas})" aria-label="Previous">
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
