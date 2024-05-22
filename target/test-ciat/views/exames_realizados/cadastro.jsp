<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Exame Realizado</title>
    <link rel="stylesheet" href="/avaliacao/static/css/custom-styles.css">
    <link rel="stylesheet" href="/avaliacao/static/js/jquery-ui-1.12.1.custom/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="/avaliacao/static/js/jquery-ui-1.12.1.custom/external/jquery/jquery.js"></script>
    <script src="/avaliacao/static/js/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
    <script>
        $(function() {
            $("#dt_realizacao").datepicker({ dateFormat: 'dd/mm/yy' });
        });
    </script>
</head>
<body>
	<%@include file="/layout/navbar.jsp" %>
    <div class="container" style="padding: 70px; box-shadow: 5px 0px 50px 10px #00000029; margin-top: 70px;border-radius: 20px; background: #FFF">
        <h2>Cadastro de Exame Realizado</h2>

        <form action="executeCadastro" method="post">

            <div class="form-group">
                <label for="funcionario">Funcionário:</label>
                <s:select name="exameRealizado.cd_funcionario" list="listaFuncionarios" listKey="cdFuncionario" listValue="nmFuncionario" cssClass="form-control" />
            </div>

            <div class="form-group">
                <label for="exame">Exame:</label>
                <s:select name="exameRealizado.cd_exame" list="listaExames" listKey="cdExame" listValue="nmExame" cssClass="form-control" />
            </div>

            <div class="form-group w-25">
                <label for="data">Data:</label>
                <s:textfield name="exameRealizado.dt_realizacao" id="dt_realizacao" cssClass="form-control" />
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
            <button type="submit" class="btn btn-outline-primary">Cadastrar</button>
        </form>
    </div>
    <%@include file="/layout/footer.jsp" %>
</body>
</html>