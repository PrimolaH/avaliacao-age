<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><s:if test="id > 0">Edição de Exame</s:if><s:else>Cadastro de Exame</s:else></title>
    <link rel="stylesheet" href="/avaliacao/static/css/custom-styles.css">
    <link rel="stylesheet" href="/avaliacao/static/js/jquery-ui-1.12.1.custom/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@12.6.10/dist/sweetalert2.min.css">
    <script src="/avaliacao/static/js/jquery-ui-1.12.1.custom/external/jquery/jquery.js"></script>
    <script src="/avaliacao/static/js/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
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

		function excluirExame(id) {
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
            <h2 class="mb-4"><s:if test="id > 0">Edição de Exame</s:if><s:else>Cadastro de Exame</s:else></h2>
            <form action="executeCadastro" method="post">
            	<s:if test="exame.cdExame > 0">
            		<div class="form-group">
				    	<label for="exame.cdExame">Código do Exame:</label>				    
				        <s:property value="exame.cdExame" />
				        <s:hidden name="exame.cdExame" />				    
					</div>
				</s:if>            
                <div class="form-group">
                    <label for="exame.nmExame">Nome do Exame</label>
                    <s:textfield class="form-control" name="exame.nmExame"  maxlength="255" oninput="toUppercase(this)"/>
                    <s:fielderror fieldName="exame.nmExameError"/>
                </div>
                <div class="form-group w-25">
                    <label for="exame.icAtivo">Ativo</label>
                    <s:select class="form-control" name="exame.icAtivo" list="#{'true':'Sim', 'false':'Não'}" value="exame.icAtivo"/>
                    <s:fielderror fieldName="exame.icAtivo" />
                </div>
                <div class="form-group">
                    <label for="exame.dsDetalheExame">Detalhe do Exame</label>
                    <s:textarea class="form-control" name="exame.dsDetalheExame" rows="4" />
                    <s:fielderror fieldName="exame.dsDetalheExame" />
                </div>
                <div class="form-group">
                    <label for="exame.dsDetalheExame1">Outro Detalhe do Exame</label>
                    <s:textarea class="form-control" name="exame.dsDetalheExame1" rows="4"/>
                    <s:fielderror fieldName="exame.dsDetalheExame1" />
                </div>
                <s:if test="hasActionErrors()">
				    <div class="alert alert-danger" role="alert">
				        <s:actionmessage />
				    </div>
				</s:if>
				<s:if test="hasActionMessages()">
				    <div id="mensagem" class="alert alert-success" style="display:none;">
				        <s:actionmessage/>
				    </div>
				</s:if>				
                <button type="submit" class="btn btn-outline-primary"><s:if test="id > 0">Salvar Alterações</s:if><s:else>Cadastrar</s:else></button>
                <s:if test="id > 0">
				    <button type="button" class="btn btn-outline-danger" onclick="excluirExame(<s:property value="%{exame.cdExame}" />)">Excluir</button>
				</s:if>
				                
            </form>
        </div>
    </div>
    <%@include file="/layout/footer.jsp" %>
</body>
</html>
