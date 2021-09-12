<%@page import="aplicacao.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>InterClone | Dashboard</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <jsp:include page="/externo/sidebar.jsp" />
            <section class="home-section flex-column">
                <% Session sessaoCorrente = (Session) session.getAttribute("session"); %>                                        
                <div class="text">
                    <h1>Dashboard do Usuário</h1>
                    Bem-vindo <%= sessaoCorrente.getNomeUsuario() %>. <br> 
                    Selecione no menu lateral à esquerda o recurso que deseja consultar.
                    
                    <br><br><br>
                    Obs.: O balancete se encontra no botão circular azul dentro da tela de 
                    <a href="lancamentos"><b>Lançamentos</b></a>
                </div><
            </section>

        <%@include file="/base_scripts.html" %>
    </body>
</html>
