<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>InterClone | Lançamentos</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <%@page import="java.util.ArrayList"%>
        <%@page import="aplicacao.Lancamento"%>
        <%@page import="java.util.Iterator"%> 

        <jsp:include page="/externo/sidebar.jsp" />
        <section class="home-section">
            <div class="page-container">
                <h2>Listagem de Lançamentos</h2>
                
                <a class="table-button" href="lancamentos?acao=cadastrar">Novo Lançamento</a>    

                <table class="table table-sm table-hover table-bordered default-table">
                    <thead>
                        <th class="col-sm-1">ID</th>
                        <th class="col-sm-8 text-left">Descrição</th>
                        <th class="col-sm-3">Ações</th>
                    </thead>
                    <tbody>
                        <%
                        if(request.getAttribute("lancamentos") != null) {
                            ArrayList<Lancamento> listaLancamentos = (ArrayList<Lancamento>) request.getAttribute("lancamentos");
                            for (int i = 0; i < listaLancamentos.size(); i++) {
                                Lancamento lancamento = listaLancamentos.get(i);
                        %>
                        <tr>
                            <td><%=lancamento.getId()%></td>
                            <td class="text-left"><%= lancamento.getDescricao()== null ? "" : lancamento.getDescricao() %></td>
                            <td class="d-flex justify-content-around">
                                <a href="<%="lancamentos?acao=editar&id="+ lancamento.getId() %>" class="btn btn-primary float-right">Editar</a>
                                <a href="<%="lancamentos?acao=excluir&id="+ lancamento.getId() %>" class="btn btn-danger float-right">Excluir</a>
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
