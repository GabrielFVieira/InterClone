<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
            <title>InterClone | Dashboard</title>
            <%@include file="/header.html" %>
	</head>
	<body>
            <jsp:include page="/externo/sidebar.jsp" />
            <section class="home-section">
                <div class="text">
                    <h1>Dashboard do Usuário</h1>
                    Bem-vindo a Dashboard temporária do usuário. <br> 
                    Para essa entrega o único botão funcional na barra à esquerda é o <b>Sair</b> <br><br> 
                    Caso deseje acessar a página temporária com os links para os formulários de cadastro de Conta-Corrente e Lançamentos <a href="externo">clique aqui</a>
                </div>
            </section>

            <%@include file="/base_scripts.html" %>
	</body>
</html>
