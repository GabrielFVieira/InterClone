<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>InterClone | Categorias</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <%@page import="java.util.ArrayList"%>
        <%@page import="aplicacao.Categoria"%>
        <%@page import="java.util.Iterator"%> 

        <jsp:include page="/interno/sidebar.jsp" />
        <section class="home-section">
            <div class="page-container">
                <h2>Listagem de Categorias</h2>
                
                <a class="table-button btn text-white" onClick="gerarURL('categorias', 'cadastrar');">Nova Categoria</a>    
                
                <table class="table table-sm table-hover table-bordered default-table mt-2">
                    <thead>
                        <th class="col-sm-1">ID</th>
                        <th class="col-sm-8 text-left">Descrição</th>
                        <th class="col-sm-2">Ações</th>
                    </thead>
                    <tbody>
                        <%
                        if(request.getAttribute("categorias") != null) {
                            ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) request.getAttribute("categorias");
                            for (int i = 0; i < listaCategorias.size(); i++) {
                                Categoria categoria = listaCategorias.get(i);
                        %>
                        <tr>
                            <td><%=categoria.getId()%></td>
                            <td class="text-left"><%=categoria.getDescricao()%></td>
                            <td>
                                <div class="d-flex justify-content-center align-items-center">
                                    <a onClick="gerarURL('categorias', 'editar', <%=categoria.getId()%>);" class="btn btn-primary float-right mr-2 text-white">Editar</a>
                                    <a onClick="gerarURL('categorias', 'excluir', <%=categoria.getId()%>);" class="btn btn-danger float-right text-white">Excluir</a>                                    
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
