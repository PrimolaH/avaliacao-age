<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="d-flex justify-content-center align-items-center vh-100">
    <div class="card p-4">
        <h2 class="text-center mb-4">Login</h2>
        <form action="execute" method="post">
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
            <s:if test="hasActionErrors()">
			    <div class="alert alert-danger" role="alert">
			        <s:actionerror />
			    </div>
			</s:if>

            <button type="submit" class="btn btn-outline-primary btn-block">Entrar</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>