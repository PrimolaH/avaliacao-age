<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><s:if test="cadastroUsuarioModel.loaded">Edição de Usuário</s:if><s:else>Cadastro de Usuário</s:else></title>
<link rel="stylesheet" href="/avaliacao/static/css/custom-styles.css">
<link rel="stylesheet"
	href="/avaliacao/static/js/jquery-ui-1.12.1.custom/jquery-ui.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/sweetalert2@12.6.10/dist/sweetalert2.min.css">
<script
	src="/avaliacao/static/js/jquery-ui-1.12.1.custom/external/jquery/jquery.js"></script>
<script src="/avaliacao/static/js/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
        function toLowerCase(element) {
            element.value = element.value.toLowerCase();
        }

        document.addEventListener('DOMContentLoaded', function() {
            var mensagemElement = document.getElementById('mensagem');
            if (mensagemElement) {
                mensagemElement.style.display = 'block';
                setTimeout(function() {
                    mensagemElement.style.display = 'none';
                }, 5000);
            }
        });

        function excluirUsuario(login) {
            console.log(login);
            Swal.fire({
                title: 'Tem certeza?',
                text: 'Esta ação não poderá ser desfeita.',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                confirmButtonText: 'Sim, excluir'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = 'excluir?login=' + login;
                }
            });
        }

    </script>
</head>
<body>
	<%@include file="/layout/navbar.jsp"%>
	<div class="container"
		style="padding: 70px; box-shadow: 5px 0px 50px 10px #00000029; margin-top: 70px; border-radius: 20px; background: #FFF">
		<div class="card p-4 w-100">
			<h2 class="mb-4">
				<s:if test="cadastroUsuarioModel.loaded">Edição de Usuário</s:if>
				<s:else>Cadastro de Usuário</s:else>
			</h2>
			<form action="executeCadastro" method="post">
				<s:if test="cadastroUsuarioModel.loaded">
					<div class="form-group">
						<label for="cadastroUsuarioModel.nmLogin">Login:</label>
						<div class="form-control"
							style="background-color: #e9ecef; border: 1px solid #ced4da;">
							<s:property value="cadastroUsuarioModel.nmLogin" />
						</div>
					</div>
					<s:hidden name="cadastroUsuarioModel.nmLogin" />
					<s:hidden name="cadastroUsuarioModel.loaded" />
				</s:if>
				<s:else>
					<div class="form-group">
						<label for="cadastroUsuarioModel.nmLogin">Nome de Login</label>
						<s:textfield class="form-control"
							name="cadastroUsuarioModel.nmLogin" maxlength="50"
							oninput="toLowerCase(this)" />
						<s:fielderror fieldName="cadastroUsuarioModel.nmLogin" />
					</div>
					<div class="form-group">
						<label for="cadastroUsuarioModel.dsSenha">Senha</label>
						<s:password class="form-control"
							name="cadastroUsuarioModel.dsSenha" maxlength="20" />
						<s:fielderror fieldName="cadastroUsuarioModel.dsSenha" />
					</div>
				</s:else>
				<div class="form-group">
					<label for="cadastroUsuarioModel.qtTempoInatividade">Tempo
						de Inatividade</label>
					<s:textfield class="form-control"
						name="cadastroUsuarioModel.qtTempoInatividade" />
					<s:fielderror fieldName="cadastroUsuarioModel.qtTempoInatividade" />
				</div>
				<s:if test="hasActionErrors()">
					<div class="alert alert-danger" role="alert">
						<s:actionerror />
					</div>
				</s:if>
				<s:if test="hasActionMessages()">
					<div id="mensagem" class="alert alert-success"
						style="display: none;">
						<s:actionmessage />
					</div>
				</s:if>
				<button type="submit" class="btn btn-outline-primary">
					<s:if test="cadastroUsuarioModel.loaded">Salvar Alterações</s:if>
					<s:else>Cadastrar</s:else>
				</button>
				<s:if test="cadastroUsuarioModel.loaded">
					<button type="button" class="btn btn-outline-danger"
						onclick="excluirUsuario('<s:property value="%{cadastroUsuarioModel.nmLogin}" />')">Excluir</button>
				</s:if>
			</form>
		</div>
	</div>
	<%@include file="/layout/footer.jsp"%>
</body>
</html>