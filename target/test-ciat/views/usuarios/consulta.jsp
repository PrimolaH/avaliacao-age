<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Usuários</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <script>
        function consulta(pagina) {
            var queryParams = "";

            var login = document.getElementById("login").value;
            var role = document.getElementById("role").value;

            if (login) {
                queryParams += "&login=" + login.trim();
            }
            if (role === 'ADMIN' || role === 'USER') {
                queryParams += "&role=" + role;
            }

            window.location.href = "consulta?pagina=" + pagina + queryParams;
        }

        function limparCampos() {
            document.getElementById('login').value = '';
            document.getElementById('role').value = 'todos';

            window.location.href = "consulta?"
        }

        function novoUsuario() {
            window.location.href = "cadastro";
        }

        function editarUsuario(login) {
            window.location.href = "cadastro?login=" + login;
        }
    </script>

	<%@include file="/layout/navbar.jsp" %>
	
    <div class="container" style="padding: 70px; box-shadow: 5px 0px 50px 10px #00000029; margin-top: 70px;border-radius: 20px; background: #FFF">
        <h2>Consulta de Usuários</h2>

        <form class="form-inline mb-3 mt-3">
            <div class="form-group mr-3">
                <label for="login" class="mr-2">Login:</label>
                <s:textfield name="login" id="login" class="form-control" value="%{consultaUsuariosModel.nmLogin}" />
            </div>
            <div class="form-group mr-3">
                <label for="role" class="mr-2">Role:</label>
                <s:select name="role" id="role" class="form-control" list="#{'todos': 'Todos', 'ADMIN':'Admin', 'USER':'User'}" value="%{consultaUsuariosModel.nmRole}">
                </s:select>
            </div>

            <button type="button" class="btn btn-outline-primary mr-3" onclick="consulta(1)">Pesquisar</button>
            <button type="button" class="btn btn-outline-secondary mr-3" onclick="limparCampos()">Limpar</button>
            <button type="button" class="btn btn-outline-success" onclick="novoUsuario()">Novo Usuário</button>
        </form>

        <table class="table">
            <thead>
                <tr>
                    <th>Login</th>
                    <th>Role</th>
                    <th>Tempo Inatividade</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="listaUsuarios">
                    <tr>
                        <td><s:property value="nmLogin"/></td>
                        <td><s:property value="nmRole"/></td>
                        <td><s:property value="qtTempoInatividade"/></td>
                        <td>
                            <button type="button" class="btn btn-info btn-sm" onclick="editarUsuario('<s:property value="nmLogin"/>')">Editar</button>
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
		<s:if test="consultaUsuariosModel.totalPaginas > 1">
	        <nav aria-label="Page navigation" class="d-flex justify-content-center">
	            <ul class="pagination">
	                <li class="page-item">
	                    <s:if test="consultaUsuariosModel.paginaAtual > 1">
	                        <button class="page-link" onclick="consulta(1)" aria-label="Previous">
	                            <span aria-hidden="true">«« Primeira</span>
	                        </button>
	                    </s:if>
	                </li>
	
	                <li class="page-item">
	                    <s:if test="consultaUsuariosModel.paginaAtual > 1">
	                        <button class="page-link" onclick="consulta(${consultaUsuariosModel.paginaAtual - 1})" aria-label="Previous">
	                            <span aria-hidden="true">&laquo; Anterior</span>
	                        </button>
	                    </s:if>
	                </li>
	
	                <s:iterator begin="consultaUsuariosModel.primeiraPagina" end="consultaUsuariosModel.ultimaPagina" var="i">                        
	                    <li class="page-item">
	                        <s:if test="consultaUsuariosModel.paginaAtual eq i">
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
	                    <s:if test="consultaUsuariosModel.paginaAtual < consultaUsuariosModel.totalPaginas">
	                        <button class="page-link" onclick="consulta(${consultaUsuariosModel.paginaAtual + 1})" aria-label="Previous">
	                            <span aria-hidden="true">Próxima &raquo;</span>
	                        </button>
	                    </s:if>
	                </li>
	
	                <li class="page-item">
	                    <s:if test="consultaUsuariosModel.paginaAtual < consultaUsuariosModel.totalPaginas">                        
	                        <button class="page-link" onclick="consulta(${consultaUsuariosModel.totalPaginas})" aria-label="Previous">
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