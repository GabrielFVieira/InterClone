<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>InterClone | Administradores</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <%@page import="java.util.ArrayList"%>
        <%@page import="aplicacao.Administrador"%>
        <%@page import="java.util.Iterator"%> 

        <jsp:include page="/interno/sidebar.jsp" />
        <section class="home-section">
            <div class="page-container">
                <h2>Listagem de Administradores</h2>
                
                <a class="table-button" href="administradores?acao=cadastrar">Novo Administrador</a>    

                <table class="table table-sm table-hover table-bordered default-table">
                    <thead>
                        <th class="col-sm-1">ID</th>
                        <th class="col-sm-3 text-left">CPF</th>
                        <th class="col-sm-5 text-left">Nome</th>
                        <th class="col-sm-2">Ações</th>
                    </thead>
                    <tbody>
                        <%
                        if(request.getAttribute("administradores") != null) {
                            ArrayList<Administrador> listaAdmins = (ArrayList<Administrador>) request.getAttribute("administradores");
                            for (int i = 0; i < listaAdmins.size(); i++) {
                                Administrador admin = listaAdmins.get(i);
                        %>
                        <tr>
                            <td><%=admin.getId()%></td>
                            <td class="text-left"><%=admin.getCpf()%></td>
                            <td class="text-left"><%=admin.getNome()%></td>
                            <td>
                                <div class="d-flex justify-content-center align-items-center">
                                    <a href="<%="administradores?acao=editar&id="+ admin.getId() %>" class="btn btn-primary float-right mr-2">Editar</a>
                                    <a href="<%="administradores?acao=excluir&id="+ admin.getId() %>" class="btn btn-danger float-right">Excluir</a>                                    
                                </div>
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
