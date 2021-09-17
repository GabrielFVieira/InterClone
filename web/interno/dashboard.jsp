<%@page import="aplicacao.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
            <title>InterClone | Dashboard</title>
            <%@include file="/header.html" %>
	</head>
	<body>
            <jsp:include page="sidebar.jsp" />
            <section class="home-section">
                <% Session sessaoCorrente = (Session) session.getAttribute("session"); %>                                        
                <div class="text">
                    <h1>Dashboard do Administrador</h1>
                    Bem-vindo <%= sessaoCorrente.getNomeUsuario() %>. <br> 
                    Selecione no menu lateral à esquerda o recurso que deseja consultar.
                </div>
            </section>

            <%@include file="/base_scripts.html" %>
	</body>
</html>
