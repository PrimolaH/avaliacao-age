<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><s:if test="id > 0">Edição de Funcionário</s:if><s:else>Cadastro de Funcionário</s:else></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@12.6.10/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
    <script>
        function toUppercase(element) {
            element.value = element.value.toUpperCase();
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

        function excluirFuncionario(id) {
            console.log(id);
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
                    window.location.href = 'excluir?id=' + id;
                }
            });
        }

    </script>
    <%@include file="/layout/navbar.jsp" %>
    <div class="container" style="padding: 70px; box-shadow: 5px 0px 50px 10px #00000029; margin-top: 70px;border-radius: 20px; background: #FFF">
        <div class="card p-4 w-100">
            <h2 class="mb-4"><s:if test="id > 0">Edição de Funcionário</s:if><s:else>Cadastro de Funcionário</s:else></h2>
            <form action="executeCadastro" method="post">
                <s:if test="funcionario.cdFuncionario > 0">
                    <div class="form-group">
                        <label for="funcionario.cdFuncionario">Código do Funcionário:</label>
                        <s:property value="funcionario.cdFuncionario" />
                        <s:hidden name="funcionario.cdFuncionario" />
                    </div>
                </s:if>
                <div class="form-group">
                    <label for="funcionario.nmFuncionario">Nome do Funcionário</label>
                    <s:textfield class="form-control" name="funcionario.nmFuncionario" maxlength="255" oninput="toUppercase(this)"/>
                    <s:fielderror fieldName="funcionario.nmFuncionarioError"/>
                </div>
                <s:if test="hasActionErrors()">
                    <div class="alert alert-danger" role="alert">
                        <s:actionerror />
                    </div>
                </s:if>
                <s:if test="hasActionMessages()">
                    <div id="mensagem" class="alert alert-success" style="display:none;">
                        <s:actionmessage/>
                    </div>
                </s:if>
                <button type="submit" class="btn btn-outline-primary"><s:if test="id > 0">Salvar Alterações</s:if><s:else>Cadastrar</s:else></button>
                <s:if test="id > 0">
                    <button type="button" class="btn btn-outline-danger" onclick="excluirFuncionario(<s:property value="%{funcionario.cdFuncionario}" />)">Excluir</button>
                </s:if>
            </form>
        </div>
    </div>
	<%@include file="/layout/footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>
