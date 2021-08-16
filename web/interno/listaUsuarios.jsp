<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>InterClone | Usuários</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <%@page import="java.util.ArrayList"%>
        <%@page import="aplicacao.Usuario"%>
        <%@page import="java.util.Iterator"%> 

        <jsp:include page="/interno/sidebar.jsp" />
        <section class="home-section">
            <div class="page-container">
                <h2>Listagem de Usuários</h2>
                
                <div class="d-flex flex-row justify-content-between align-items-baseline">
                    <a class="table-button" href="usuarios?acao=cadastrar">Novo Usuário</a>    
                    <p>Obs.: Para suspender um usuário clique em <b>Editar</b></p>
                </div>
                
                <table class="table table-sm table-hover table-bordered default-table">
                    <thead>
                        <th class="col-sm-1">ID</th>
                        <th class="col-sm-3 text-left">CPF</th>
                        <th class="col-sm-4 text-left">Nome</th>
                        <th class="col-sm-1">Suspenso</th>
                        <th class="col-sm-3">Ações</th>
                    </thead>
                    <tbody>
                        <%
                        if(request.getAttribute("usuarios") != null) {
                            ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
                            for (int i = 0; i < listaUsuarios.size(); i++) {
                                Usuario usuario = listaUsuarios.get(i);
                        %>
                        <tr class="">
                            <td><%=usuario.getId()%></td>
                            <td class="text-left"><%=usuario.getCpf()%></td>
                            <td class="text-left"><%=usuario.getNome()%></td>
                            <td><%=usuario.getSuspenso()%></td>
                            <td class="d-flex justify-content-around">
                                <a href="<%="usuarios?acao=editar&id="+ usuario.getId() %>" class="btn btn-primary float-right">Editar</a>
                                <a href="<%="usuarios?acao=excluir&id="+ usuario.getId() %>" class="btn btn-danger float-right">Excluir</a>
                            </td>
                        </tr>
                        <%
                            }
                        }
                        %>
                    </tbody>
                </table>		
            </div>
        </section>
        <%@include file="/base_scripts.html" %>
    </body>
</html>
