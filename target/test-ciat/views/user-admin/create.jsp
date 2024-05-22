<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Usuário</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="card p-4">
            <h2 class="mb-4">Cadastro de Administrador</h2>
            <form action="create" method="post">
                <div class="form-group">
                    <label for="usuario.nmLogin">Username</label>                    
                    <s:textfield  class="form-control" name="usuario.nmLogin" />
					<s:fielderror fieldName="usuario.nmLogin" />
                </div>
                <div class="form-group">
                    <label for="usuario.dsSenha">Password</label>
                    <s:password class="form-control" name="usuario.dsSenha" />
					<s:fielderror fieldName="usuario.dsSenha" />
                </div>
                <div class="form-group">
                    <label for="role">Role</label>
                    <select class="form-control" id="role" name="usuario.nmRole" disabled>
                        <option value="ADMIN" selected>ADMIN</option>
                    </select>
                </div>                
			    <s:if test="errorMessage != null">
			    	<div class="alert alert-danger" role="alert">
			        	<s:property value="errorMessage" />
			        </div>
			    </s:if>				
                <button type="submit" class="btn btn-outline-primary">Cadastrar</button>
            </form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>